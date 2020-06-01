package com.yaoshun.util;

import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* IP工具类
* @author King
* @since  2019/7/25 14:42
*/
public class IpUtils {

    private static final String OS_WINDOW = "Windows";

    private static final String IP_REX_EXP = "((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)";


    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
     *
     * @return ip
     */
    public static String getRealIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if( ip.indexOf(",")!=-1 ){
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

    public static void validateIp(String ip) {
        Assert.notNull(ip, "ip null");
        Pattern pattern = Pattern.compile(IP_REX_EXP);
        Matcher matcher = pattern.matcher(ip);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("ip illegal");
        }
    }

    public static long ipToLong(String ip) {
        validateIp(ip);
        String[] ips = ip.split("\\.");
        long value = 0L;
        for (String s : ips) {
            value = value << 8 | Integer.parseInt(s);
        }
        return value;
    }

    public static String longToIP(long longIp){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append((longIp >>> 24));
        stringBuilder.append(".");
        stringBuilder.append(((longIp & 16777215L) >>> 16));
        stringBuilder.append(".");
        stringBuilder.append(((longIp & 65535L) >>> 8));
        stringBuilder.append(".");
        stringBuilder.append((longIp & 255L));
        return stringBuilder.toString();
    }

    public static String getMacAddress(String ip) throws Exception{
        String osSystem = System.getProperty("os.name");
        String result;
        if (osSystem.startsWith(OS_WINDOW)) {
            ShellUtils.exec("ping " + ip + " -a 2");
        } else  {
            ShellUtils.exec("ping " + ip + " -c 2");
        }
        result = ShellUtils.exec("arp -a " + ip);
        final String regExp = "([0-9A-Fa-f]{2})([-:][0-9A-Fa-f]{2}){5}";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(result);
        StringBuilder mac = new StringBuilder();
        while (matcher.find()) {
            String temp = matcher.group();
            mac.append(temp);
        }
        return mac.toString();
    }

    public static boolean ipExistsInSection(String ip, String ipSection) {
        validateIp(ip);
        Assert.notNull(ipSection, "ipSection null");
        final String ipSectionReg = IP_REX_EXP + "\\-" + IP_REX_EXP;
        Pattern pattern = Pattern.compile(ipSectionReg);
        Matcher matcher = pattern.matcher(ipSection);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("ipSection illegal");
        }
        long ipValue = ipToLong(ip);
        String[] sections = ipSection.split("-");
        long begin = ipToLong(sections[0]);
        long end = ipToLong(sections[1]);
        return begin <= ipValue && ipValue <= end;
    }

    /**
     * 检测起始ip、终止ip
     * @param beginIp 起始ip
     * @param endIp 终止ip
     * @return true 终止ip大于等于起始ip，false 终止ip小于起始ip
     */
    public static boolean checkBeginIpEndIp(String beginIp, String endIp) {
        long start = ipToLong(beginIp);
        long end = ipToLong(endIp);
        return start <= end;
    }

    public static List<String> getLocalIps() throws SocketException {
        List<String> ips = new ArrayList<>();
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                if (inetAddress.isLoopbackAddress()) {
                    //回路地址
                } else if (inetAddress.isLinkLocalAddress()) {
                    //链接地址
                } else {
                    ips.add(inetAddress.getHostAddress());
                }
            }
        }
        return ips;
    }

	/**
	 * 获取所有网卡，注意或过滤掉回路地址、链接地址\ipv6
	 * @return 网卡列表
	 * @throws SocketException 异常
	 */
	public static List<InetAddress> getLocalHostLANAddress() throws SocketException {
        List<InetAddress> addresses = new ArrayList<>();
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                if (inetAddress.isLoopbackAddress()) {
                    //回路地址
                } else if (inetAddress.isLinkLocalAddress()) {
                    //链接地址
                } else {
                    //非链接和回路真实ip
                    if(inetAddress.getHostAddress().contains(":")) {
                        //过滤ipv6
                        continue;
                    }
                    addresses.add(inetAddress);
                }
            }
        }
        return addresses;
    }

    public static InetAddress getInetAddress(String ip) throws SocketException {
        validateIp(ip);
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                if(inetAddress.getHostAddress().contains(ip)) {
                    return inetAddress;
                }
            }
        }
        throw new IllegalArgumentException("ip invalid, ip = " + ip);
    }

    public static String getMACAddress(InetAddress ia) throws SocketException {
        // 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
        // 下面代码是把mac地址拼装成String
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mac.length; i++) {
            if (i != 0) {
                sb.append("-");
            }
            // mac[i] & 0xFF 是为了把byte转化为正整数
            String s = Integer.toHexString(mac[i] & 0xFF);
            sb.append(s.length() == 1 ? 0 + s : s);
        }
        // 把字符串所有小写字母改为大写成为正规的mac地址并返回
        return sb.toString().toUpperCase();
    }

    public static List<String> getMACAddress(List<InetAddress> addresses) throws SocketException {
        List<String> macs = new ArrayList<>();
        for (InetAddress address : addresses) {
            macs.add(getMACAddress(address));
        }
        return macs;
    }

}

package com.black.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description: NTP时间同步工具类
 * @author: duanwei
 * @create: 2020-05-28 13:57
 **/
public class NtpDateUtil {
    private static Logger logger = LoggerFactory.getLogger(NtpDateUtil.class);
    public  static int sycNtpDate(String domain) throws Exception {
        String cmdStr = "killall ntp 2>/dev/null;killall ntpd 2>/dev/null;ntpdate "+domain+" > /dev/null";
        String[] cmds = {"/bin/sh","-c",cmdStr};
        logger.debug("**********************"+cmdStr);
        Process pro = Runtime.getRuntime().exec(cmds);
        int result = pro.waitFor();
        logger.debug("ntpdate cmd return msg===========================>:{}"+result);
        return result;
    }
}

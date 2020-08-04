package com.black.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @description: http https工具类 原生
 * @author: duanwei
 * @create: 2019-06-03 11:19
 **/
public class HttpUtil {
    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

    // 连接超时时间
    public static final int CONNECTION_TIMEOUT = 5000;

    // 请求超时时间
    public static final int CONNECTION_REQUEST_TIMEOUT = 5000;

    // 数据读取等待超时
    public static final int SOCKET_TIMEOUT = 10000;

    // http
    public static final String HTTP = "http";

    // https
    public static final String HTTPS = "https";

    // http端口
    public static final int DEFAULT_HTTP_PORT = 80;

    // https端口
    public static final int DEFAULT_HTTPS_PORT = 443;

    // 默认编码
    public static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * get请求(1.处理http请求;2.处理https请求,信任所有证书)[默认编码:UTF-8]
     *
     * @param url (参数直接拼接到URL后面，即http://test.com?a=1&b=2的形式)
     * @return
     */
    public static String get(String url) throws IOException, URISyntaxException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        return get(url, null, DEFAULT_ENCODING);
    }

    /**
     * get请求(1.处理http请求;2.处理https请求,信任所有证书)[默认编码:UTF-8]
     *
     * @param url    (url不带参数，例：http://test.com)
     * @param reqMap (参数放置到一个map中)
     * @return
     */
    public static String get(String url, Map<String, Object> reqMap) throws IOException, URISyntaxException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        return get(url, reqMap, DEFAULT_ENCODING);
    }

    /**
     *  根据请求头选择相应的client
     *  https HttpUtil.createSSLInsecureClient
     *  http  createDefault
     * @param url    (url不带参数，例：http://test.com)
     * @return CloseableHttpClient
     */

    public static CloseableHttpClient getHttpClient(String url){
        CloseableHttpClient httpClient = null;
        try {
            if (url.startsWith(HTTPS)) {
                // 创建一个SSL信任所有证书的httpClient对象
                httpClient = HttpUtil.createSSLInsecureClient();
            } else {
                httpClient = HttpClients.createDefault();
            }
        }catch (Exception e){
            log.error("请求client 初始化失败 请检查地址是否正确,url={}",url,e);
            throw new RuntimeException(e);
        }
        return httpClient;
    }

    /**
     *  获取post请求头
     * @param url    (url不带参数，例：http://test.com)
     * @return HttpPost
     */
    public static HttpPost getHttpPost(String url)
    {
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(CONNECTION_TIMEOUT)
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT)
                .setRedirectsEnabled(true)
                .build();
        httpPost.setConfig(requestConfig);
        return httpPost;
    }

    /**
     * get请求(1.处理http请求;2.处理https请求,信任所有证书)
     *
     * @param url      (只能是http或https请求)
     * @param encoding
     * @return
     */
    public static String get(String url, Map<String, Object> reqMap, String encoding) throws IOException, URISyntaxException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        Assert.hasText(url, "url invalid");
        String result = "";
        // 处理参数
        List<NameValuePair> params = buildParams(reqMap);
        CloseableHttpResponse response = null;
        HttpGet httpGet;
        CloseableHttpClient httpClient=null;
        try {
             httpClient=getHttpClient(url);
            if (params != null && params.size() > 0) {
                URIBuilder builder = new URIBuilder(url);
                builder.setParameters(params);
                httpGet = new HttpGet(builder.build());
            } else {
                httpGet = new HttpGet(url);
            }

            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(CONNECTION_TIMEOUT)
                    .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                    .setSocketTimeout(SOCKET_TIMEOUT)
                    //默认允许自动重定向
                    .setRedirectsEnabled(true)
                    .build();
            httpGet.setConfig(requestConfig);

            // 发送请求，并接收响应
            response = httpClient.execute(httpGet);
            result = handleResponse(url, encoding, response);
        } finally {
            ExtendedIOUtils.closeQuietly(httpClient);
            ExtendedIOUtils.closeQuietly(response);
        }

        return result;
    }

    /**
     * post请求(1.处理http请求;2.处理https请求,信任所有证书)[默认编码:UTF-8]
     *
     * @param url
     * @param reqMap
     * @return
     */
    public static String post(String url, Map<String, Object> reqMap) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        return post(url, reqMap, DEFAULT_ENCODING);
    }

    /**
     * post请求(1.处理http请求;2.处理https请求,信任所有证书)
     *
     * @param url
     * @param reqMap   入参是个map
     * @param encoding
     * @return
     */
    public static String post(String url, Map<String, Object> reqMap, String encoding) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        Assert.hasText(url, "url invalid");
        String result="";
        // 添加参数
        List<NameValuePair> params = buildParams(reqMap);
        CloseableHttpClient httpClient=null;
        CloseableHttpResponse response = null;
        try {
            httpClient=getHttpClient(url);
            HttpPost httpPost = getHttpPost(url);
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            httpPost.setEntity(new UrlEncodedFormEntity(params, encoding));
            // 发送请求，并接收响应
            response = httpClient.execute(httpPost);
            result = handleResponse(url, encoding, response);
            log.info("http调用完成,返回数据:{}", result);
        }finally {
            ExtendedIOUtils.closeQuietly(httpClient);
            ExtendedIOUtils.closeQuietly(response);
        }

        return result;
    }

    /**
     * post请求(1.处理http请求;2.处理https请求,信任所有证书)
     *
     * @param url
     * @param jsonParams 入参是个json字符串
     * @return
     */
    public static String post(String url, String jsonParams) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        log.info("----->调用请求 url:{} ---->json参数:{}",url,jsonParams);
        return post(url, jsonParams, DEFAULT_ENCODING);
    }

    /**
     * post请求(1.处理http请求;2.处理https请求,信任所有证书)
     *
     * @param url
     * @param jsonParams 入参是个json字符串
     * @param encoding
     * @return
     */
    public static String post(String url, String jsonParams, String encoding) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        Assert.hasText(url, "url invalid");
        String result;
        CloseableHttpClient httpClient;
        if (url.startsWith(HTTPS)) {
            // 创建一个SSL信任所有证书的httpClient对象
            httpClient = HttpUtil.createSSLInsecureClient();
        } else {
            httpClient = HttpClients.createDefault();
        }
        CloseableHttpResponse response = null;

        try {
            HttpPost httpPost = getHttpPost(url);
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(jsonParams, ContentType.create("application/json", encoding)));

            // 发送请求，并接收响应
            response = httpClient.execute(httpPost);
            result = handleResponse(url, encoding, response);
           // result= JSONObject.parseObject(result).getString("data");
        } finally {
            ExtendedIOUtils.closeQuietly(httpClient);
            ExtendedIOUtils.closeQuietly(response);
        }
        return result;
    }

    /**
     * 创建一个SSL信任所有证书的httpClient对象
     *
     * @return
     */
    public static CloseableHttpClient createSSLInsecureClient() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        // 默认信任所有证书
        HostnameVerifier hostnameVerifier = (hostname, session) -> true;
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, (TrustStrategy) (chain, authType) -> true).build();
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
        return HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
    }

    /**
     * 处理响应，获取响应报文
     *
     * @param url
     * @param encoding
     * @param response
     * @return
     * @throws IOException
     */
    private static String handleResponse(String url, String encoding, CloseableHttpResponse response) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            if (response != null) {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    // 获取响应实体
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        br = new BufferedReader(new InputStreamReader(entity.getContent(), encoding));
                        String s;
                        while ((s = br.readLine()) != null) {
                            sb.append(s);
                        }
                    }
                    // 释放entity
                    EntityUtils.consume(entity);
                } else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
                    log.info("-----> get请求404,未找到资源. url:" + url);
                } else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                    log.info("-----> get请求500,服务器端异常. url:" + url);
                }
            }
        } finally {
            ExtendedIOUtils.closeQuietly(br);
        }
        return sb.toString();
    }

    /**
     * 采用绕过验证的方式处理https请求
     *
     * @param url
     * @param reqMap
     * @param encoding
     * @return
     */
    public static String postSSLUrl(String url, Map<String, Object> reqMap, String encoding) throws IOException, KeyManagementException, NoSuchAlgorithmException {
        String result;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        // 添加参数
        List<NameValuePair> params = buildParams(reqMap);
        try {
            //采用绕过验证的方式处理https请求
            HostnameVerifier hostnameVerifier = (hostname, session) -> true;
            SSLContext sslcontext = createIgnoreVerifySSL();
            //设置协议http和https对应的处理socket链接工厂的对象
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", new SSLConnectionSocketFactory(sslcontext, hostnameVerifier))
                    .build();
            PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            //创建自定义的httpclient对象
            httpClient = HttpClients.custom().setConnectionManager(connManager).build();
            //创建post方式请求对象
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(params, encoding));
            //指定报文头Content-type、User-Agent
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            //执行请求操作，并拿到结果（同步阻塞）
            response = httpClient.execute(httpPost);
            result = handleResponse(url, encoding, response);
        } finally {
            ExtendedIOUtils.closeQuietly(httpClient);
            ExtendedIOUtils.closeQuietly(response);
        }
        return result;
    }

    private static List<NameValuePair> buildParams(Map<String, Object> reqMap) {
        List<NameValuePair> params = new ArrayList<>();
        if (reqMap != null && reqMap.keySet().size() > 0) {
            Iterator<Map.Entry<String, Object>> iter = reqMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, Object> entity = iter.next();
                params.add(new BasicNameValuePair(entity.getKey(), entity.getValue().toString()));
            }
        }
        return params;
    }

    /**
     * 绕过验证
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("SSLv3");
        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate, String paramString) {}

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate, String paramString) {}

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }
        };
        sc.init(null, new TrustManager[]{trustManager}, new java.security.SecureRandom());
        return sc;
    }

}

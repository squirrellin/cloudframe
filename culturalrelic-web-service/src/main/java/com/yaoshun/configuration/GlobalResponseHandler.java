package com.yaoshun.configuration;

import com.alibaba.fastjson.JSON;
import com.yaoshun.util.ExceptionResult;
import com.yaoshun.util.JwtResult;
import com.yaoshun.util.Response;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.LinkedList;

/**
 * @description: 全局统一响应处理
 * @author: duanwei
 * @create: 2019-08-28 20:07
 **/
@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {


    final static String SWAGGER_URL = "/v2/api-docs";
    final static String SWAGGER_RESOURCES = "swagger";
    final static String CSRF = "csrf";

    static LinkedList<String> filterUrl = new LinkedList<>();

    {
        filterUrl.add(SWAGGER_URL);
        filterUrl.add(SWAGGER_RESOURCES);
        filterUrl.add(CSRF);
    }


    /**
     * 针对方法以及对象
     *
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // 获取当前处理请求的controller的方法
        String methodName = methodParameter.getMethod().getName();
        // 不拦截/不需要处理返回值 的方法
        String method = "loginCheck";
        // 拦截 false
        // return !method.equals(methodName);
        return true;
    }

    /**
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        for (String s : filterUrl) {
            if (request.getURI().toString().contains(s)) {
                return body;
            }
        }


        Response res = new Response(body);
        //兼容原来的接口返回
        if (body instanceof JwtResult || body instanceof ExceptionResult) {
            return body;
        }
        //保证null情况下 对象不会转换错误
        if (null == body) {
            return JSON.toJSONString(body);
        }
        //处理返回值是String的情况 对象不会转换错误
        if (body instanceof String) {
            return JSON.toJSONString(res);
        }
        return res;
    }

}

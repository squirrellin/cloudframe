package com.shaolin.common;/*
package com.test.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

*/
/**
 * @description: 日志全局统一拦截AOP
 **//*


@Aspect
@Component
public class WebLogAcpect {

    private Logger logger = LoggerFactory.getLogger(WebLogAcpect.class);

    */
/**
     * 定义切入点，切入点为com.example.aop下的所有函数
     *//*

    @Pointcut("execution(public * com.test.base..*.*(..))")
    public void webLog(){}

    */
/**
     * 前置通知：在连接点之前执行的通知
     * @param joinPoint
     * @throws Throwable
     *//*

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret",pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
    }


    @AfterThrowing(throwing="ex",pointcut = "webLog()")
    public void doAfterReturningEx(Throwable ex) throws Throwable {
        logger.info("RESPONSE Ex: " + ex);
    }


    @After("webLog()")
    public void after() {
        logger.info("已经记录下操作日志@After 方法执行后");
    }

}
*/

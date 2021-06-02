package com.shaolin.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.shaolin.exception.errorEnum.BaseExceptionEnum;
import com.shaolin.handler.ClientBlockHandler;
import com.shaolin.util.ExceptionResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @description:
 * @author: duanwei
 * @create: 2020-05-31 10:37
 **/
@RestController
@Slf4j
public class TestController {


    @Autowired
    RestTemplate restTemplate;


    @Value("${service-url.nacos-user-service}")
    private String serverUrl;


    /**
     * 流控规则和异常回调都存在的时候 异常回调大于流控规则
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/constomer/web/user/{id}")
    @SentinelResource(value = "getUserList", fallback = "handlerFallback", blockHandler = "blockExcetpion")
    public String getUserList(@PathVariable("id") String id) {

        //模拟 除零 运行时异常
        // int a = 10 / 0;
        return restTemplate.getForObject(serverUrl + "/web/user/" + id, String.class);
    }


    /**
     * 单配置错误回调
     *
     * @param blockException
     * @return
     */
    public String blockExcetpion(BlockException blockException) {
        return "-----------错误啦 o(╥﹏╥)o";
    }

    /**
     * 热点key降级案例 续配合sentinel热点 testHotKey 参数0
     *
     * @param p1 p1热点
     * @param p2
     * @return
     */
    @GetMapping(value = "/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "error_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1, @RequestParam(value = "p2",
            required = false) String p2) {
        return "testHotKey";
    }


    public String error_testHotKey(String p1, String p2, BlockException blockException) {
        return "-----------错误啦 o(╥﹏╥)o";
    }


    /**
     * 全局降级案例
     *
     * @return
     */
    @GetMapping("/rateLimit/exceptionResult")
    @SentinelResource(value = "exceptionResult", blockHandlerClass = ClientBlockHandler.class,
            blockHandler = "handlerExceptionBusy")
    public ExceptionResult exceptionResult() {
        return new ExceptionResult(BaseExceptionEnum.REQUEST_TIME_OUT.getCode(),
                BaseExceptionEnum.REQUEST_TIME_OUT.getMsg());
    }


    public ExceptionResult handlerFallback(@PathVariable Integer id, Throwable e) {
        return new ExceptionResult(BaseExceptionEnum.PARAMETER_ERROR.getCode(),
                BaseExceptionEnum.PARAMETER_ERROR.getMsg());
    }

}

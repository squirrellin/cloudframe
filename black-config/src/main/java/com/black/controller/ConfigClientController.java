package com.black.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: nacos配置中心测试案例
 * @author: duanwei
 * @create: 2020-05-31 11:01
 **/

@RestController
@RefreshScope //支持动态刷新
public class ConfigClientController {


    @Value("${config.info}")
    private String configInfo;


    @GetMapping(value = "/config/info")
    public String getConfigInfo() {
        return configInfo;
    }


}

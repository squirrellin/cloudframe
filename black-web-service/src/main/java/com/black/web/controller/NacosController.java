package com.black.web.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户表
 *
 * @author duanwei
 * @date 2020-05-29
 */
@RestController
@RequestMapping("/web/nacos")
public class NacosController {


    @Value("${server.port}")
    String serverPort;

    /**
     * 新增用户表
     *
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String test(@PathVariable("id") Integer id) {
        return "nacos registry,serverPort" + serverPort + " id:" + id;
    }


}



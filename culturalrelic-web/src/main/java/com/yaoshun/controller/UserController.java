package com.yaoshun.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yaoshun.cloud.UserServiceCloud;
import com.yaoshun.handler.ClientBlockHandler;
import com.yaoshun.util.Response;
import com.yaoshun.web.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @description: 用户服务调用
 * @author: duanwei
 * @create: 2020-05-31 10:37
 **/
@RestController
@RequestMapping(value = "/web/user")
@Slf4j
public class UserController {


    @Autowired
    UserServiceCloud userServiceCloud;

    /**
     * 客户端调用微服务
     *
     * @param pageNum
     * @param pageSize
     * @param user
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @SentinelResource(value = "webUserListPageCloud", blockHandlerClass = ClientBlockHandler.class,
            blockHandler = "handlerExceptionBusy")
    public Object listPage(String pageNum, String pageSize, User user) {
        Object response = userServiceCloud.listPage(pageNum, pageSize, user);
        Response response1 = userServiceCloud.selectById(1L);


        return response;
    }

    /**
     * 客户端调用微服务
     *
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object selectById(@PathVariable Long id) {
        Object user = userServiceCloud.selectById(id);
        return user;
    }



}

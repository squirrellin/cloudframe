package com.black.cloud.fallback;


import com.black.cloud.UserServiceCloud;
import com.black.exception.errorEnum.BaseExceptionEnum;
import com.black.util.Response;
import com.black.web.bean.User;
import org.springframework.stereotype.Component;


/**
 * @description: 客户端-降级熔断
 * @author: duanwei
 * @create: 2020-05-31 15:33
 **/
@Component
public class UserFallbackServiceCloud implements UserServiceCloud {


    public Response listPage(String pageNum, String pageSize, User user) {
        return new Response(BaseExceptionEnum.SYSTEM_BUSY.getCode(), BaseExceptionEnum.SYSTEM_BUSY.getMsg());
    }

    public Response selectById(Long id) {
        return new Response(BaseExceptionEnum.SYSTEM_BUSY.getCode(), BaseExceptionEnum.SYSTEM_BUSY.getMsg());
    }

}

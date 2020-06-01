package com.yaoshun.cloud.fallback;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yaoshun.cloud.UserServiceCloud;
import com.yaoshun.exception.BaseException;
import com.yaoshun.exception.errorEnum.BaseExceptionEnum;
import com.yaoshun.util.Response;
import com.yaoshun.web.bean.User;
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

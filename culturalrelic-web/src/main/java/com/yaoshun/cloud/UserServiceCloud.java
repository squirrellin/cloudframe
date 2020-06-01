package com.yaoshun.cloud;

import com.yaoshun.cloud.fallback.UserFallbackServiceCloud;
import com.yaoshun.util.Response;
import com.yaoshun.web.bean.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;


/**
 * @description:
 * @author: duanwei
 * @create: 2020-05-31 15:26
 **/
@FeignClient(path = "producer-server", value = "producer-server", fallback = UserFallbackServiceCloud.class)
public interface UserServiceCloud {


    /**
     * 请求用户列表
     *
     * @param pageNum
     * @param pageSize
     * @param user
     * @return
     */
    @RequestMapping(value = "/web/user/list", method = RequestMethod.GET)
    Response listPage(@RequestParam("pageNum") String pageNum, @RequestParam("pageSize") String pageSize, @SpringQueryMap User user);


    @RequestMapping(value = "/web/user/{id}", method = RequestMethod.GET)
    Response selectById(@PathVariable(value = "id") Long id);


}

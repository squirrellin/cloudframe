package com.shaolin.cloud;

import com.shaolin.cloud.fallback.UserFallbackServiceCloud;
import com.shaolin.util.Response;
import com.shaolin.web.bean.User;
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

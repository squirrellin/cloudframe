package com.black.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.black.util.NameUtils;
import com.black.web.bean.User;
import com.black.web.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * 用户表
 *
 * @author duanwei
 * @date 2020-05-29
 */
@RestController
@RequestMapping("/web/user")
@Api(tags = "用户管理")
public class UserController {




    @Autowired
    IUserService iUserService;


    @Value("${server.port}")
    String port;

    /**
     * 新增用户表
     *
     * @return
     */
    @ApiOperation(value = "添加用户", notes = "根据用户信息进行添加")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public boolean saveUser(@RequestBody @ApiParam(name = "用户对象", value = "传入json格式", required = true) User user) {
        return iUserService.save(user);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据id删除", notes = "根据id删除用户信息")
    public boolean deleteById(
            @ApiParam(name = "id", value = "传入id", required = true) @PathVariable Long id) {
        return iUserService.removeById(id);
    }


    /**
     * 修改用户表
     *
     * @return
     */
    @RequestMapping(value = "/updateById", method = RequestMethod.PUT)
    public boolean updateByIdUser(@RequestBody User user) {
        return iUserService.updateById(user);
    }


    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User selectById(@PathVariable Long id) {
        System.out.println("请求port:" + port);
        return iUserService.getById(id);
    }


    /**
     * 列表分页查询
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    @SentinelResource(value = "webUserListPage", blockHandlerClass = ClientBlockHandler.class,
//            blockHandler = "handlerExceptionBusy")
    public IPage<User> listPage(@RequestParam(defaultValue = "1") String pageNum, @RequestParam(defaultValue = "10") String pageSize,
                                User user) {

        Page<User> pageBean = new Page<>(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        Class<? extends User> aClass = user.getClass();
        Arrays.stream(aClass.getDeclaredFields()).forEach(field -> {
            try {
                field.setAccessible(true);
                Object o = field.get(user);
                if (o != null) {
                    Class<?> type = field.getType();
                    String name = NameUtils.camel2Underline(field.getName());
                    if (type.equals(Integer.class)) {
                        Integer fileValue = (Integer) field.get(user);
                        userQueryWrapper.eq(name, fileValue);
                    } else if (type.equals(Long.class)) {
                        Long fileValue = (Long) field.get(user);
                        userQueryWrapper.eq(name, fileValue);
                    } else if (type.equals(String.class)) {
                        String fileValue = (String) field.get(user);
                        userQueryWrapper.eq(name, fileValue);
                    } else {
                        String fileValue = (String) field.get(user);
                        userQueryWrapper.eq(name, fileValue);
                    }
                }
            } catch (Exception e) {
            }
        });
        IPage<User> page = iUserService.page(pageBean, userQueryWrapper);
        return page;
    }
}



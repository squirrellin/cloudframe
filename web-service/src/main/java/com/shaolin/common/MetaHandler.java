package com.shaolin.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.shaolin.util.LocalDateTimeUtils;
import com.shaolin.util.RedisUtil;
import com.shaolin.util.SpringUtils;
import com.shaolin.web.bean.User;
import com.shaolin.web.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description: 处理mybatis业务处理,新增和更新的基础数据填充，配合BaseEntity和MyBatisPlusConfig使用
 * @author: duanwei
 * @create: 2020-05-28 13:57
 **/
@Component
@Slf4j
public class MetaHandler implements MetaObjectHandler {


    /**
     * 新增数据执行
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        RedisUtil redisUtil = (RedisUtil) SpringUtils.getBean("redisUtil");
        IUserService iUserService = (IUserService) SpringUtils.getBean("userServiceImpl");

        String username = "";
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();
            username = userDetails.getUsername();
        } catch (Exception e) {
            log.error("MetaHandler Get UserInfo Error");
        }

        if (!redisUtil.hasKey(username)) {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", username);
            User user = iUserService.getOne(queryWrapper);
            redisUtil.set(username, user.getId());
        }
        String id = String.valueOf(redisUtil.get(username));
        Date currentDate = new Date();
        LocalDateTime localDateTime = LocalDateTimeUtils.convertDateToLDT(currentDate);

        this.setFieldValByName("ctime", localDateTime, metaObject);
        this.setFieldValByName("cuser", id, metaObject);
        this.setFieldValByName("utime", localDateTime, metaObject);
        this.setFieldValByName("uuser", id, metaObject);
    }

    /**
     * 更新数据执行
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {

        RedisUtil redisUtil = (RedisUtil) SpringUtils.getBean("redisUtil");
        IUserService iUserService = (IUserService) SpringUtils.getBean("userServiceImpl");

        String username = "";
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();
            username = userDetails.getUsername();
        } catch (Exception e) {
            log.error("MetaHandler Get UserInfo Error");
        }

        if (!redisUtil.hasKey(username)) {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", username);
            User user = iUserService.getOne(queryWrapper);
            redisUtil.set(username, user.getId());
        }
        String id = String.valueOf(redisUtil.get(username));
        Date currentDate = new Date();
        LocalDateTime localDateTime = LocalDateTimeUtils.convertDateToLDT(currentDate);
        this.setFieldValByName("utime", localDateTime, metaObject);
        this.setFieldValByName("uuser", id, metaObject);
    }

}
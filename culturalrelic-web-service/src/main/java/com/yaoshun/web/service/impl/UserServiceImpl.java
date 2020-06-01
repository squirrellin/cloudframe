package com.yaoshun.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yaoshun.web.bean.User;
import com.yaoshun.web.mapper.UserMapper;
import com.yaoshun.web.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * 用户表 服务实现类
 *
 * @author duanwei
 * @date 2020-05-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}

package com.black.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.black.web.bean.User;
import com.black.web.mapper.UserMapper;
import com.black.web.service.IUserService;
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

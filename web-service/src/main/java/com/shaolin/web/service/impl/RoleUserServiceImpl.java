package com.shaolin.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shaolin.web.bean.RoleUser;
import com.shaolin.web.mapper.RoleUserMapper;
import com.shaolin.web.service.IRoleUserService;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author duanwei
 * @date 2020-05-29
 */
@Service
public class RoleUserServiceImpl extends ServiceImpl<RoleUserMapper, RoleUser> implements IRoleUserService {

}

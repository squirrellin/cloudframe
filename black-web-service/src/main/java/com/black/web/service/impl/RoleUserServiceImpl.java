package com.black.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.black.web.bean.RoleUser;
import com.black.web.mapper.RoleUserMapper;
import com.black.web.service.IRoleUserService;
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

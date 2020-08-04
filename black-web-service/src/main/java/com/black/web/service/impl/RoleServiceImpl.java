package com.black.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.black.web.bean.Role;
import com.black.web.mapper.RoleMapper;
import com.black.web.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author duanwei
 * @date 2020-05-29
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}

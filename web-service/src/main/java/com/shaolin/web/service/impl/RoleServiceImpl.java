package com.shaolin.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shaolin.web.bean.Role;
import com.shaolin.web.mapper.RoleMapper;
import com.shaolin.web.service.IRoleService;
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

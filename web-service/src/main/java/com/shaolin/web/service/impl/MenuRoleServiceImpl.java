package com.shaolin.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shaolin.web.bean.MenuRole;
import com.shaolin.web.mapper.MenuRoleMapper;
import com.shaolin.web.service.IMenuRoleService;
import org.springframework.stereotype.Service;

/**
 * 菜单、权限表 服务实现类
 *
 * @author duanwei
 * @date 2020-05-29
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

}

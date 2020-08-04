package com.black.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.black.web.bean.MenuRole;
import com.black.web.mapper.MenuRoleMapper;
import com.black.web.service.IMenuRoleService;
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

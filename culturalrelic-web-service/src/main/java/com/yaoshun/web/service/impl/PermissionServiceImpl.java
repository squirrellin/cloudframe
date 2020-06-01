package com.yaoshun.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yaoshun.web.bean.Permission;
import com.yaoshun.web.mapper.PermissionMapper;
import com.yaoshun.web.service.IPermissionService;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author duanwei
 * @date 2020-05-29
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}

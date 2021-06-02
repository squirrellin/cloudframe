package com.shaolin.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shaolin.web.bean.Permission;
import com.shaolin.web.mapper.PermissionMapper;
import com.shaolin.web.service.IPermissionService;
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

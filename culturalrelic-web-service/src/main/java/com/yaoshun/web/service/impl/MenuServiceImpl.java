package com.yaoshun.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yaoshun.web.bean.Menu;
import com.yaoshun.web.mapper.MenuMapper;
import com.yaoshun.web.service.IMenuService;
import org.springframework.stereotype.Service;

/**
 * 菜单表 服务实现类
 *
 * @author duanwei
 * @date 2020-05-29
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}

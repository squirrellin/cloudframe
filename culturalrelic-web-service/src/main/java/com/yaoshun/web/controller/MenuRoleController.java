package com.yaoshun.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yaoshun.util.NameUtils;
import com.yaoshun.web.bean.MenuRole;
import com.yaoshun.web.service.IMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


/**
 * 菜单、权限表
 *
 * @author duanwei
 * @date 2020-05-29
 */
@RestController
@RequestMapping("/web/menu-role")
public class MenuRoleController {

  @Autowired
  IMenuRoleService iMenuRoleService;


    /**
    * 新增菜单、权限表
    * @return
    */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public boolean saveMenuRole(HttpServletRequest request, @RequestBody MenuRole menuRole){
    return iMenuRoleService.save(menuRole);
    }

    /**
    * 根据id删除
    * @param id
    * @return
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteById(HttpServletRequest request, @PathVariable Long id){
    return iMenuRoleService.removeById(id);
    }


    /**
    * 修改菜单、权限表
    * @return
    */
    @RequestMapping(value = "/updateById", method = RequestMethod.PUT)
    public boolean updateByIdMenuRole(HttpServletRequest request, @RequestBody MenuRole menuRole){
    return iMenuRoleService.updateById(menuRole);
    }



    /**
   * 根据id查询
   * @param id
   * @return
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public MenuRole selectById(HttpServletRequest request, @PathVariable Long id){
      return iMenuRoleService.getById(id);
   }



  /**
  * 列表分页查询
  * @return
  */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public IPage<MenuRole> listPage(@RequestParam(required = true, defaultValue = "1")  String pageNum, @RequestParam(required = true, defaultValue = "10") String pageSize,
    MenuRole menuRole){

    Page<MenuRole> pageBean = new Page<>(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
    QueryWrapper<MenuRole> menuRoleQueryWrapper = new QueryWrapper<>();
    Class<? extends MenuRole> aClass = menuRole.getClass();
    Arrays.stream(aClass.getDeclaredFields()).forEach(field -> {
        try {
            field.setAccessible(true);
            Object o = field.get(menuRole);
            if (o != null) {
                Class<?> type = field.getType();
                String name = NameUtils.camel2Underline(field.getName());
                if (type.equals(Integer.class)) {
                Integer fileValue = (Integer) field.get(menuRole);
                menuRoleQueryWrapper.eq(name, fileValue);
                } else if (type.equals(Long.class)) {
                Long fileValue = (Long) field.get(menuRole);
                menuRoleQueryWrapper.eq(name, fileValue);
                } else if (type.equals(String.class)) {
                String fileValue = (String) field.get(menuRole);
                menuRoleQueryWrapper.eq(name, fileValue);
                } else {
                String fileValue = (String) field.get(menuRole);
                menuRoleQueryWrapper.eq(name, fileValue);
            }
          }
        }catch (Exception e) {
        }
    });
    IPage<MenuRole> page = iMenuRoleService.page(pageBean, menuRoleQueryWrapper);
    return page;
  }
}



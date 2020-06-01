package com.yaoshun.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yaoshun.util.NameUtils;
import com.yaoshun.web.bean.PermissionRole;
import com.yaoshun.web.service.IPermissionRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


/**
 * 
 *
 * @author duanwei
 * @date 2020-05-29
 */
@RestController
@RequestMapping("/web/permission-role")
public class PermissionRoleController {

  @Autowired
  IPermissionRoleService iPermissionRoleService;


    /**
    * 新增
    * @return
    */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public boolean savePermissionRole(HttpServletRequest request, @RequestBody PermissionRole permissionRole){
    return iPermissionRoleService.save(permissionRole);
    }

    /**
    * 根据id删除
    * @param id
    * @return
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteById(HttpServletRequest request, @PathVariable Long id){
    return iPermissionRoleService.removeById(id);
    }


    /**
    * 修改
    * @return
    */
    @RequestMapping(value = "/updateById", method = RequestMethod.PUT)
    public boolean updateByIdPermissionRole(HttpServletRequest request, @RequestBody PermissionRole permissionRole){
    return iPermissionRoleService.updateById(permissionRole);
    }



    /**
   * 根据id查询
   * @param id
   * @return
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public PermissionRole selectById(HttpServletRequest request, @PathVariable Long id){
      return iPermissionRoleService.getById(id);
   }



  /**
  * 列表分页查询
  * @return
  */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public IPage<PermissionRole> listPage(@RequestParam(required = true, defaultValue = "1")  String pageNum, @RequestParam(required = true, defaultValue = "10") String pageSize,
    PermissionRole permissionRole){

    Page<PermissionRole> pageBean = new Page<>(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
    QueryWrapper<PermissionRole> permissionRoleQueryWrapper = new QueryWrapper<>();
    Class<? extends PermissionRole> aClass = permissionRole.getClass();
    Arrays.stream(aClass.getDeclaredFields()).forEach(field -> {
        try {
            field.setAccessible(true);
            Object o = field.get(permissionRole);
            if (o != null) {
                Class<?> type = field.getType();
                String name = NameUtils.camel2Underline(field.getName());
                if (type.equals(Integer.class)) {
                Integer fileValue = (Integer) field.get(permissionRole);
                permissionRoleQueryWrapper.eq(name, fileValue);
                } else if (type.equals(Long.class)) {
                Long fileValue = (Long) field.get(permissionRole);
                permissionRoleQueryWrapper.eq(name, fileValue);
                } else if (type.equals(String.class)) {
                String fileValue = (String) field.get(permissionRole);
                permissionRoleQueryWrapper.eq(name, fileValue);
                } else {
                String fileValue = (String) field.get(permissionRole);
                permissionRoleQueryWrapper.eq(name, fileValue);
            }
          }
        }catch (Exception e) {
        }
    });
    IPage<PermissionRole> page = iPermissionRoleService.page(pageBean, permissionRoleQueryWrapper);
    return page;
  }
}



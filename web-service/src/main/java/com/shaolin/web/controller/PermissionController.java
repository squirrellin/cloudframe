package com.shaolin.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shaolin.util.NameUtils;
import com.shaolin.web.bean.Permission;
import com.shaolin.web.service.IPermissionService;
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
@RequestMapping("/web/permission")
public class PermissionController {

  @Autowired
  IPermissionService iPermissionService;


    /**
    * 新增
    * @return
    */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public boolean savePermission(HttpServletRequest request, @RequestBody Permission permission){
    return iPermissionService.save(permission);
    }

    /**
    * 根据id删除
    * @param id
    * @return
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteById(HttpServletRequest request, @PathVariable Long id){
    return iPermissionService.removeById(id);
    }


    /**
    * 修改
    * @return
    */
    @RequestMapping(value = "/updateById", method = RequestMethod.PUT)
    public boolean updateByIdPermission(HttpServletRequest request, @RequestBody Permission permission){
    return iPermissionService.updateById(permission);
    }



    /**
   * 根据id查询
   * @param id
   * @return
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public Permission selectById(HttpServletRequest request, @PathVariable Long id){
      return iPermissionService.getById(id);
   }



  /**
  * 列表分页查询
  * @return
  */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public IPage<Permission> listPage(@RequestParam(required = true, defaultValue = "1")  String pageNum, @RequestParam(required = true, defaultValue = "10") String pageSize,
    Permission permission){

    Page<Permission> pageBean = new Page<>(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
    QueryWrapper<Permission> permissionQueryWrapper = new QueryWrapper<>();
    Class<? extends Permission> aClass = permission.getClass();
    Arrays.stream(aClass.getDeclaredFields()).forEach(field -> {
        try {
            field.setAccessible(true);
            Object o = field.get(permission);
            if (o != null) {
                Class<?> type = field.getType();
                String name = NameUtils.camel2Underline(field.getName());
                if (type.equals(Integer.class)) {
                Integer fileValue = (Integer) field.get(permission);
                permissionQueryWrapper.eq(name, fileValue);
                } else if (type.equals(Long.class)) {
                Long fileValue = (Long) field.get(permission);
                permissionQueryWrapper.eq(name, fileValue);
                } else if (type.equals(String.class)) {
                String fileValue = (String) field.get(permission);
                permissionQueryWrapper.eq(name, fileValue);
                } else {
                String fileValue = (String) field.get(permission);
                permissionQueryWrapper.eq(name, fileValue);
            }
          }
        }catch (Exception e) {
        }
    });
    IPage<Permission> page = iPermissionService.page(pageBean, permissionQueryWrapper);
    return page;
  }
}



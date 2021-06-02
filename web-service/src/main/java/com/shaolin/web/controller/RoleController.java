package com.shaolin.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shaolin.util.NameUtils;
import com.shaolin.web.bean.Role;
import com.shaolin.web.service.IRoleService;
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
@RequestMapping("/web/role")
public class RoleController {

  @Autowired
  IRoleService iRoleService;


    /**
    * 新增
    * @return
    */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public boolean saveRole(HttpServletRequest request, @RequestBody Role role){
    return iRoleService.save(role);
    }

    /**
    * 根据id删除
    * @param id
    * @return
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteById(HttpServletRequest request, @PathVariable Long id){
    return iRoleService.removeById(id);
    }


    /**
    * 修改
    * @return
    */
    @RequestMapping(value = "/updateById", method = RequestMethod.PUT)
    public boolean updateByIdRole(HttpServletRequest request, @RequestBody Role role){
    return iRoleService.updateById(role);
    }



    /**
   * 根据id查询
   * @param id
   * @return
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public Role selectById(HttpServletRequest request, @PathVariable Long id){
      return iRoleService.getById(id);
   }



  /**
  * 列表分页查询
  * @return
  */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public IPage<Role> listPage(@RequestParam(required = true, defaultValue = "1")  String pageNum, @RequestParam(required = true, defaultValue = "10") String pageSize,
    Role role){

    Page<Role> pageBean = new Page<>(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
    QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
    Class<? extends Role> aClass = role.getClass();
    Arrays.stream(aClass.getDeclaredFields()).forEach(field -> {
        try {
            field.setAccessible(true);
            Object o = field.get(role);
            if (o != null) {
                Class<?> type = field.getType();
                String name = NameUtils.camel2Underline(field.getName());
                if (type.equals(Integer.class)) {
                Integer fileValue = (Integer) field.get(role);
                roleQueryWrapper.eq(name, fileValue);
                } else if (type.equals(Long.class)) {
                Long fileValue = (Long) field.get(role);
                roleQueryWrapper.eq(name, fileValue);
                } else if (type.equals(String.class)) {
                String fileValue = (String) field.get(role);
                roleQueryWrapper.eq(name, fileValue);
                } else {
                String fileValue = (String) field.get(role);
                roleQueryWrapper.eq(name, fileValue);
            }
          }
        }catch (Exception e) {
        }
    });
    IPage<Role> page = iRoleService.page(pageBean, roleQueryWrapper);
    return page;
  }
}



package com.shaolin.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shaolin.util.NameUtils;
import com.shaolin.web.bean.RoleUser;
import com.shaolin.web.service.IRoleUserService;
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
@RequestMapping("/web/role-user")
public class RoleUserController {

  @Autowired
  IRoleUserService iRoleUserService;


    /**
    * 新增
    * @return
    */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public boolean saveRoleUser(HttpServletRequest request, @RequestBody RoleUser roleUser){
    return iRoleUserService.save(roleUser);
    }

    /**
    * 根据id删除
    * @param id
    * @return
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteById(HttpServletRequest request, @PathVariable Long id){
    return iRoleUserService.removeById(id);
    }


    /**
    * 修改
    * @return
    */
    @RequestMapping(value = "/updateById", method = RequestMethod.PUT)
    public boolean updateByIdRoleUser(HttpServletRequest request, @RequestBody RoleUser roleUser){
    return iRoleUserService.updateById(roleUser);
    }



    /**
   * 根据id查询
   * @param id
   * @return
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public RoleUser selectById(HttpServletRequest request, @PathVariable Long id){
      return iRoleUserService.getById(id);
   }



  /**
  * 列表分页查询
  * @return
  */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public IPage<RoleUser> listPage(@RequestParam(required = true, defaultValue = "1")  String pageNum, @RequestParam(required = true, defaultValue = "10") String pageSize,
    RoleUser roleUser){

    Page<RoleUser> pageBean = new Page<>(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
    QueryWrapper<RoleUser> roleUserQueryWrapper = new QueryWrapper<>();
    Class<? extends RoleUser> aClass = roleUser.getClass();
    Arrays.stream(aClass.getDeclaredFields()).forEach(field -> {
        try {
            field.setAccessible(true);
            Object o = field.get(roleUser);
            if (o != null) {
                Class<?> type = field.getType();
                String name = NameUtils.camel2Underline(field.getName());
                if (type.equals(Integer.class)) {
                Integer fileValue = (Integer) field.get(roleUser);
                roleUserQueryWrapper.eq(name, fileValue);
                } else if (type.equals(Long.class)) {
                Long fileValue = (Long) field.get(roleUser);
                roleUserQueryWrapper.eq(name, fileValue);
                } else if (type.equals(String.class)) {
                String fileValue = (String) field.get(roleUser);
                roleUserQueryWrapper.eq(name, fileValue);
                } else {
                String fileValue = (String) field.get(roleUser);
                roleUserQueryWrapper.eq(name, fileValue);
            }
          }
        }catch (Exception e) {
        }
    });
    IPage<RoleUser> page = iRoleUserService.page(pageBean, roleUserQueryWrapper);
    return page;
  }
}



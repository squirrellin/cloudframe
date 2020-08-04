package com.black.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.black.util.NameUtils;
import com.black.web.bean.Menu;
import com.black.web.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


/**
 * 菜单表
 *
 * @author duanwei
 * @date 2020-05-29
 */
@RestController
@RequestMapping("/web/menu")
public class MenuController {

  @Autowired
  IMenuService iMenuService;





    /**
    * 新增菜单表
    * @return
    */

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public boolean saveMenu(HttpServletRequest request, @RequestBody Menu menu){
    return iMenuService.save(menu);
    }

    /**
    * 根据id删除
    * @param id
    * @return
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteById(HttpServletRequest request, @PathVariable Long id){
    return iMenuService.removeById(id);
    }


    /**
    * 修改菜单表
    * @return
    */
    @RequestMapping(value = "/updateById", method = RequestMethod.PUT)
    public boolean updateByIdMenu(HttpServletRequest request, @RequestBody Menu menu){
    return iMenuService.updateById(menu);
    }



    /**
   * 根据id查询
   * @param id
   * @return
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public Menu selectById(HttpServletRequest request, @PathVariable Long id){
      return iMenuService.getById(id);
   }



  /**
  * 列表分页查询
  * @return
  */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public IPage<Menu> listPage(@RequestParam(required = true, defaultValue = "1")  String pageNum, @RequestParam(required = true, defaultValue = "10") String pageSize,
    Menu menu){

    Page<Menu> pageBean = new Page<>(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
    QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<>();
    Class<? extends Menu> aClass = menu.getClass();
    Arrays.stream(aClass.getDeclaredFields()).forEach(field -> {
        try {
            field.setAccessible(true);
            Object o = field.get(menu);
            if (o != null) {
                Class<?> type = field.getType();
                String name = NameUtils.camel2Underline(field.getName());
                if (type.equals(Integer.class)) {
                Integer fileValue = (Integer) field.get(menu);
                menuQueryWrapper.eq(name, fileValue);
                } else if (type.equals(Long.class)) {
                Long fileValue = (Long) field.get(menu);
                menuQueryWrapper.eq(name, fileValue);
                } else if (type.equals(String.class)) {
                String fileValue = (String) field.get(menu);
                menuQueryWrapper.eq(name, fileValue);
                } else {
                String fileValue = (String) field.get(menu);
                menuQueryWrapper.eq(name, fileValue);
            }
          }
        }catch (Exception e) {
        }
    });
    IPage<Menu> page = iMenuService.page(pageBean, menuQueryWrapper);
    return page;
  }
}



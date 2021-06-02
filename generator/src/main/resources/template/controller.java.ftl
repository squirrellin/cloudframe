package ${package.Controller};

import org.springframework.web.bind.annotation.RequestMapping;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

import ${package.Service}.${table.serviceName};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.Entity}.${entity};
import com.yaoshun.util.NameUtils;
import java.lang.reflect.Field;
import java.util.Arrays;


/**
 * ${table.comment!}
 *
 * @author ${author}
 * @date ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

  @Autowired
  ${table.serviceName} ${table.serviceName ?uncap_first};


    /**
    * 新增${table.comment}
    * @return
    */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public boolean save${entity}(HttpServletRequest request, @RequestBody ${entity} ${entity ?uncap_first}){
    return ${table.serviceName ?uncap_first}.save(${entity ?uncap_first});
    }

    /**
    * 根据id删除
    * @param id
    * @return
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteById(HttpServletRequest request, @PathVariable Long id){
    return ${table.serviceName ?uncap_first}.removeById(id);
    }


    /**
    * 修改${table.comment}
    * @return
    */
    @RequestMapping(value = "/updateById", method = RequestMethod.PUT)
    public boolean updateById${entity}(HttpServletRequest request, @RequestBody ${entity} ${entity ?uncap_first}){
    return ${table.serviceName ?uncap_first}.updateById(${entity ?uncap_first});
    }



    /**
   * 根据id查询
   * @param id
   * @return
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public ${entity} selectById(HttpServletRequest request, @PathVariable Long id){
      return ${table.serviceName ?uncap_first}.getById(id);
   }



  /**
  * 列表分页查询
  * @return
  */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public IPage<${entity}> listPage(@RequestParam(defaultValue = "1")  String pageNum, @RequestParam(defaultValue = "10") String pageSize,
    ${entity} ${entity ?uncap_first}){

    Page<${entity}> pageBean = new Page<>(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
    QueryWrapper<${entity}> ${entity ?uncap_first}QueryWrapper = new QueryWrapper<>();
    Class<? extends ${entity}> aClass = ${entity ?uncap_first}.getClass();
    Arrays.stream(aClass.getDeclaredFields()).forEach(field -> {
        try {
            field.setAccessible(true);
            Object o = field.get(${entity ?uncap_first});
            if (o != null) {
                Class<?> type = field.getType();
                String name = NameUtils.camel2Underline(field.getName());
                if (type.equals(Integer.class)) {
                Integer fileValue = (Integer) field.get(${entity ?uncap_first});
                ${entity ?uncap_first}QueryWrapper.eq(name, fileValue);
                } else if (type.equals(Long.class)) {
                Long fileValue = (Long) field.get(${entity ?uncap_first});
                ${entity ?uncap_first}QueryWrapper.eq(name, fileValue);
                } else if (type.equals(String.class)) {
                String fileValue = (String) field.get(${entity ?uncap_first});
                ${entity ?uncap_first}QueryWrapper.eq(name, fileValue);
                } else {
                String fileValue = (String) field.get(${entity ?uncap_first});
                ${entity ?uncap_first}QueryWrapper.eq(name, fileValue);
            }
          }
        }catch (Exception e) {
        }
    });
    IPage<${entity}> page = ${table.serviceName ?uncap_first}.page(pageBean, ${entity ?uncap_first}QueryWrapper);
    return page;
  }
}


</#if>

package com.black.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * @description: 名称映射转换类
 * @author: duanwei
 * @create: 2019-08-28 20:07
 **/
@Component
@Slf4j
public class BeanNameConversion implements InitializingBean {
    /**
     * 项目路径
     */
    private String projectPath = "/com/test/";

    @Override
    public void afterPropertiesSet() throws Exception {
        Set<Class<?>> classes = ScanPack.getClasses("com.test");
        for (Class<?> aClass : classes) {
            Package aPackage = aClass.getPackage();
            String name = aPackage.getName();
            if (name.endsWith("bean")) {
                //存储全部小写的类名 和 实际的位置
                GlobalCache.tableNameBeanMap.put(aClass.getSimpleName().toLowerCase(), aClass.getName());
            }
            if (name.endsWith("impl") && aClass.getSimpleName().endsWith("Impl")) {
                //存储全部小写的类名 和 小写后的serviceImpl名称
                GlobalCache.serviceNameMap.put(toLowerCaseFirstOne(aClass.getSimpleName().replace("ServiceImpl", "")).toLowerCase(), toLowerCaseFirstOne(aClass.getSimpleName()));
            }
        }

        log.info("beanName:{}", GlobalCache.tableNameBeanMap.toString());
        log.info("serviceName:{}", GlobalCache.serviceNameMap.toString());


//        boolean isWindows = System.getProperty("os.name").toLowerCase().contains("windows");
//
//        /**
//         * 是windows的情况下去创建 并加载Map
//         * 是linux的情况下去请求外部文件
//         */
//        if (!isWindows) {
//            List<File> fileBeanList = new ArrayList<>();
//            List<File> fileServiceImplList = new ArrayList<>();
//            String path = this.getClass().getResource(projectPath).getPath();
//            log.info("path----------->:{}", path);
//
//            File filePath = new File(path);
//            fileToLine(filePath, fileBeanList, fileServiceImplList);
//            for (File file : fileBeanList) {
//                String absolutePath = file.getAbsolutePath();
//                String info = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.lastIndexOf(file.getName()));
//                info = info.replace("\\", ".") + file.getName().replace(".class", "");
//                GlobalCache.tableNameBeanMap.put(file.getName().toLowerCase().replace(".class", ""),
//                        info);
//            }
//            for (File file : fileServiceImplList) {
//                String absolutePath = file.getName();
//                String serviceName = toLowerCaseFirstOne(absolutePath.replace(".class", ""));
//                GlobalCache.serviceNameMap.put(serviceName.replace("ServiceImpl", "").toLowerCase().replace(".class", ""),
//                        serviceName);
//            }
//
//            JSONObject beanJson = new JSONObject(GlobalCache.tableNameBeanMap);
//            JSONObject serviceJson = new JSONObject(GlobalCache.serviceNameMap);
//
//            File bean = new File("src/main/resources/beanJson.json");
//            bean.createNewFile();
//            FileOutputStream fosBean = new FileOutputStream(bean);
//            fosBean.write(beanJson.toString().getBytes());
//
//            File service = new File("src/main/resources/beanService.json");
//            service.createNewFile();
//            FileOutputStream fosService = new FileOutputStream(service);
//            fosService.write(serviceJson.toString().getBytes());
//            fosBean.close();
//            fosService.close();
//        } else {
//            File bean = new File("src/main/resources/beanJson.json");
//            File service = new File("src/main/resources/beanService.json");
//            String contentBean= FileUtils.readFileToString(bean,"UTF-8");
//            String contentService= FileUtils.readFileToString(service,"UTF-8");
//            JSONObject json1=JSONObject.parseObject(contentBean);
//            JSONObject json2=JSONObject.parseObject(contentService);
//            Map<String,Object> beanMap = (Map<String,Object>)json1;
//            Map<String,Object> serviceMap = (Map<String,Object>)json2;
//            GlobalCache.tableNameBeanMap=beanMap;
//            GlobalCache.serviceNameMap=serviceMap;
//        }
//        log.info("bean name:{}", GlobalCache.tableNameBeanMap.toString());
//        log.info("service名称:{}", GlobalCache.serviceNameMap.toString());


    }

    /**
     * 递归查找bean/service文件
     */
    public static void fileToLine(File dir, List<File> beanList, List<File> serviceImplList) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // 递归目录
                    fileToLine(file, beanList, serviceImplList);
                } else {
                    if (file.getParent().endsWith("bean")) {
                        if (file.getName().endsWith(".class")) {
                            beanList.add(file);
                        }
                    }
                    if (file.getParent().endsWith("impl")) {
                        if (file.getName().endsWith(".class")) {
                            serviceImplList.add(file);
                        }
                    }
                }
            }
        }
    }


    //首字母转小写
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }


    //首字母转大写
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }










}

package com.shaolin;


import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CodeGenerator {
    /**
     * 项目名称
     */
    static String projectName = "/web-rest/";

    /**
     * 项目路径
     */
    static String basePath = projectName + "src/main/java/com/shaolin/";

    /**
     * 代码生成路径
     */
    static String codeOutPath = System.getProperty("user.dir") + projectName + "/src/main/java";

    /**
     * 读取控制台内容
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 工程路径-最外层
        final String projectPath = System.getProperty("user.dir");
        // 代码输出目录
        gc.setOutputDir(codeOutPath);
        // 作者
        gc.setAuthor("shaolin");
        // 是否自动打开文件夹 建议开启
        gc.setOpen(false);
        // 开启Swagger2 注解支持
        gc.setSwagger2(true);
        // 注入mpg
        mpg.setGlobalConfig(gc);


        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/shaolin?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

        // 包配置
        final PackageConfig pc = new PackageConfig();
        // 填写对应模块
        pc.setModuleName(scanner("模块名"));
        // 实体路径
        pc.setParent("com.shaolin");
        pc.setEntity("bean");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 如果模板引擎是 freemarker
        String controller = "/template/controller.java.ftl";
        String entity = "/template/bean.java.ftl";
        String mapper = "/template/mapper.java.ftl";
        String service = "/template/service.java.ftl";
        String serviceImpl = "/template/serviceImpl.java.ftl";
        String mapperXml = "/template/mapper.xml.ftl";

        // 自定义配置会被优先输出
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig(controller) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String filePath = projectPath + basePath + pc.getModuleName()
                        + "/controller/" + tableInfo.getControllerName() + StringPool.DOT_JAVA;
                return filePath;
            }
        });

        focList.add(new FileOutConfig(entity) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String filePath = projectPath + basePath + pc.getModuleName()
                        + "/bean/" + tableInfo.getEntityName() + StringPool.DOT_JAVA;
                return filePath;
            }
        });

        focList.add(new FileOutConfig(mapper) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + basePath + pc.getModuleName()
                        + "/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_JAVA;
            }
        });

        focList.add(new FileOutConfig(service) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + basePath + pc.getModuleName()
                        + "/service/" + tableInfo.getServiceName() + StringPool.DOT_JAVA;
            }
        });

        focList.add(new FileOutConfig(serviceImpl) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + basePath + pc.getModuleName()
                        + "/service/impl/" + tableInfo.getServiceImplName() + StringPool.DOT_JAVA;
            }
        });


        focList.add(new FileOutConfig(mapperXml) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + projectName + "src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });


        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass("com.shaolin.base.BaseModel");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类-开启将导致swagger无效化
        //strategy.setSuperControllerClass("com.test.base.BaseController");
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id");
        // 建议以后开启 strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix();
        //去除表名前缀
        strategy.setTablePrefix("t_", "sys_", "other_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
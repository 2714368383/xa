package com.base.net.xa.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.boot.jdbc.DatabaseDriver;

import java.io.File;
import java.util.*;

/**
 * mybatis-plus 反向生成工具类
 *
 * @author hughes
 */
public class MybatisGenerator {

    private static String datasourceUrl = "jdbc:mysql://localhost:3306/xa?useSSL=false&serverTimezone=GMT%2B8";

    private static String datasourceUsername = "root";

    private static String datasourcePassword = "123456";

    private static String basePackage = "com.base.net.xa";

    private static String prefix = "EX_";

    private static String authorName = "zhaikaixuan";

    private static String projectPath;

    private static DbType dbType = DbType.MYSQL;

    private static String dbDriver = DatabaseDriver.MYSQL.getDriverClassName();

    private static String scanner(String inner) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(("请输入" + inner + "："));
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + inner + "！");
    }

    public static void main(String[] args) throws Exception {
        // 代码生成器
        AutoGenerator generator = new AutoGenerator();

        projectPath = new File("").getCanonicalPath();
        // 全局配置
        globalConfig(generator);

        // 数据源配置
        dbConfig(generator);

        // 包配置
        packgeConfig(generator);

        // 自定义配置
        InjectionConfig cfg = customerConfig();

        // 自定义输出配置
        customerOutConfig(generator, cfg);
        // 策略配置
        strategyConfig(generator);
        // 配置模板
        templateConfig(generator);
        generator.execute();
    }

    private static void templateConfig(AutoGenerator generator) {
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity("templates/entity.java.vm");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        generator.setTemplate(templateConfig);
    }

    private static void strategyConfig(AutoGenerator generator) {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        // 实体是否为lombok模型
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
//        strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
//        strategy.setSuperEntityColumns("id");
//        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(new String[]{prefix});

        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setEntityBuilderModel(true);

        List<TableFill> fills = new ArrayList<>();
        fills.add(new TableFill("create_date", FieldFill.INSERT));
        fills.add(new TableFill("update_date", FieldFill.INSERT_UPDATE));
        fills.add(new TableFill("create_by", FieldFill.INSERT));
        fills.add(new TableFill("update_by", FieldFill.INSERT_UPDATE));
        strategy.setTableFillList(fills);
        generator.setStrategy(strategy);
    }

    private static void customerOutConfig(AutoGenerator generator, InjectionConfig cfg) {
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/resources/mapper/generator/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        // entity配置
        focList.add(new FileOutConfig("/templates/entity.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return projectPath + "/src/main/resources/mapper/" + packageConfig.getModuleName()
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                return projectPath + "/src/main/java/com/base/net/xa/entity/" + tableInfo.getEntityName() + ".java";
            }
        });
        cfg.setFileOutConfigList(focList);
        generator.setCfg(cfg);
    }

    private static InjectionConfig customerConfig() {
        return new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
    }

    private static void packgeConfig(AutoGenerator generator) {
        PackageConfig packageConfig = new PackageConfig();
//        packageConfig.setModuleName(scanner("模块名"));
        packageConfig.setParent(basePackage);
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper.generator");
        packageConfig.setXml("mapper");
        generator.setPackageInfo(packageConfig);
    }

    private static void dbConfig(AutoGenerator generator) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(dbType);
        dataSourceConfig.setUrl(datasourceUrl);
        dataSourceConfig.setDriverName(dbDriver);
        dataSourceConfig.setUsername(datasourceUsername);
        dataSourceConfig.setPassword(datasourcePassword);
        generator.setDataSource(dataSourceConfig);
    }

    private static void globalConfig(AutoGenerator generator) {
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        // 输出目录
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setAuthor(authorName);
        // 生成后打开文件夹
        globalConfig.setOpen(false);
        globalConfig.setDateType(DateType.ONLY_DATE);
        // 是否覆盖文件
        globalConfig.setFileOverride(true);
        // 开启 activeRecord 模式
        globalConfig.setActiveRecord(true);
        // XML 二级缓存
        globalConfig.setEnableCache(false);
        // 自定义文件命名，注意 %s 会自动填充表实体属性
        globalConfig.setMapperName("%sMapper");
        globalConfig.setXmlName("%sMapper");


        globalConfig.setSwagger2(true);

        globalConfig.setIdType(IdType.UUID);
        globalConfig.setBaseColumnList(true);
        generator.setGlobalConfig(globalConfig);
    }
}

package com.edulab.utils;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.druid.DruidPlugin;

import javax.sql.DataSource;


/**
 * CREATED BY Yank
 * DATE : 2018/8/27
 * MAIL : YANK.TENYOND@GMAIL.COM
 * FUNCTION : Generate model file and base model, and automatically add mapping to _MappingKit
 */
public class EdulabGenerator {

    public static DataSource getDataSource() {
        PropKit.use("db.txt");
        DruidPlugin druidPlugin = new DruidPlugin(PropKit.use("db.txt").get("jdbcUrl"), PropKit.use("db.txt").get("username"), PropKit.use("db.txt").get("password"));
        druidPlugin.start();
        return druidPlugin.getDataSource();
    }

    public static void main(String[] args) {
        // base model 所使用的包名
        String baseModelPackageName = "com.edulab.model_base";
        // base model 文件保存路径
        String baseModelOutputDir = PathKit.getWebRootPath() + "/src/main/java/com/edulab/model_base";
        System.out.println(PathKit.getWebRootPath());
        // model 所使用的包名 (MappingKit 默认使用的包名)
        String modelPackageName = "com.edulab.model";
        // model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
        String modelOutputDir = PathKit.getWebRootPath() + "/src/main/java/com/edulab/model";

        // 创建生成器
        Generator generator = new Generator(getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
        // 设置是否生成链式 setter 方法
        generator.setGenerateChainSetter(false);
        // 添加不需要生成的表名
        generator.addExcludedTable("adv");
        // 设置是否在 Model 中生成 dao 对象
        generator.setGenerateDaoInModel(false);
        // 设置是否生成链式 setter 方法
        generator.setGenerateChainSetter(true);
        // 设置是否生成字典文件
        generator.setGenerateDataDictionary(false);
        // 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
        generator.setRemovedTableNamePrefixes("edu_");
        // 生成
        generator.generate();
    }

}

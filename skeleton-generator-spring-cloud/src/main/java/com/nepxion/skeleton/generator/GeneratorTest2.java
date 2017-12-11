package com.nepxion.skeleton.generator;

/**
 * <p>Title: Nepxion Skeleton Generator</p>
 * <p>Description: Nepxion Skeleton Generator For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import com.nepxion.skeleton.entity.SkeletonFileType;
import com.nepxion.skeleton.generator.server.PomXmlGenerator;
import com.nepxion.skeleton.generator.server.java.ServerApplicationClassGenerator;
import com.nepxion.skeleton.generator.server.resources.ApplicationPropertiesGenerator;
import com.nepxion.skeleton.property.SkeletonProperties;

// GeneratorTest1和GeneratorTest2输出结果一致
// GeneratorTest1方式的前提是Generator类必须和template模板文件放在同一目录下，使用较简单
// GeneratorTest2方式的前提是template模板文件在遵循一定规则下，可以放在任意位置，使用较复杂
// 推荐GeneratorTest1方式
public class GeneratorTest2 {
    public static void main(String[] args) {
        try {
            // 创建文件的输出的路径
            String generatePath = "E:/Download/Skeleton/";

            // 模板文件所在的上层路径
            // 模板文件的放置路径为{baseTemplatePath]/[projectType]/[fileType]
            // 1. projectType为工程模块名
            //    例如server，service，api，client
            // 2. fileType分为四种类型：
            //    1) java - java类文件创建
            //    2) resources - resources目录下文件创建
            //    3) docker - docker目录下文件创建
            //    4) project - 工程根目录下文件创建
            String baseTemplatePath = "com/nepxion/skeleton/generator/";

            // 描述规则的配置文件所在的路径
            String propertiesPath = "config/skeleton-data.properties";

            // 构造全局配置文件对象
            SkeletonProperties skeletonProperties = new SkeletonProperties(propertiesPath);

            // 创建Java类文件
            // 模板文件ServerApplication.java.template必须放在[baseTemplatePath]/server/java下
            new ServerApplicationClassGenerator(generatePath, "server", baseTemplatePath, skeletonProperties).generate();

            // 创建文件到resources目录下
            // 模板application.properties.template必须放在[baseTemplatePath]/server/resources下
            new ApplicationPropertiesGenerator(generatePath, "server", baseTemplatePath, SkeletonFileType.RESOURCES, skeletonProperties).generate();

            // 创建文件到目录下
            // 模板application.properties.template必须放在[baseTemplatePath]/server的根目录下
            new PomXmlGenerator(generatePath, "server", baseTemplatePath, SkeletonFileType.ROOT, skeletonProperties).generate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
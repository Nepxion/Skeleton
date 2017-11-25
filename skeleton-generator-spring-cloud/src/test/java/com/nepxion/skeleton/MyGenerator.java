package com.nepxion.skeleton;

/**
 * <p>Title: Nepxion Skeleton Generator</p>
 * <p>Description: Nepxion Skeleton Generator For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import com.nepxion.skeleton.context.SkeletonContext;
import com.nepxion.skeleton.property.SkeletonProperties;
import com.nepxion.skeleton.server.java.MyApplicationClassGenerator;
import com.nepxion.skeleton.service.resources.MybatisGeneratorXmlGenerator;

public class MyGenerator {
    public static void main(String[] args) {
        try {
            // 创建文件的输出的路径
            String generatePath = "E:/Download/Skeleton/";

            // 模板文件所在的上层路径，如果不设置，basePackagePath默认为template/
            // 模板文件的放置路径为{basePackagePath]/[projectType]/[fileType]
            // 1. projectType为工程模块名
            //    例如server，service，api，client
            // 2. fileType分为四种类型：
            //    1) java - java类文件创建
            //    2) resources - resources目录下文件创建
            //    3) docker - docker目录下文件创建
            //    4) project - 工程根目录下文件创建
            String basePackagePath = "com/nepxion/skeleton/";

            // 描述规则的配置文件所在的路径
            String propertiesPath = "properties/my-properties.properties";

            // 构造全局配置文件对象
            SkeletonProperties skeletonProperties = new SkeletonProperties(propertiesPath);

            // 构造server工程的上下文对象
            SkeletonContext serverSkeletonContext = new SkeletonContext("server", basePackagePath);
            // 根据MyApplicationClassGenerator的逻辑，MyApplication.java.template必须放在[basePackagePath]/server/java下
            new MyApplicationClassGenerator(generatePath, skeletonProperties, serverSkeletonContext).generate();

            // 构造service工程的上下文对象
            SkeletonContext serviceSkeletonContext = new SkeletonContext("service", basePackagePath);
            // 根据MybatisGeneratorXmlGenerator的逻辑，mybatis-generator.xml.template必须放在[basePackagePath]/service/resources下
            new MybatisGeneratorXmlGenerator(generatePath, skeletonProperties, serviceSkeletonContext).generate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
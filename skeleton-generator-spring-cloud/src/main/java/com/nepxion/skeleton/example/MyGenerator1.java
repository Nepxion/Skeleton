package com.nepxion.skeleton.example;

/**
 * <p>Title: Nepxion Skeleton Generator</p>
 * <p>Description: Nepxion Skeleton Generator For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import com.nepxion.skeleton.example.server.java.MyApplicationClassGenerator;
import com.nepxion.skeleton.example.service.resources.MybatisGeneratorXmlGenerator;
import com.nepxion.skeleton.property.SkeletonProperties;

// MyGenerator1和MyGenerator2输出结果一致
// MyGenerator1方式的前提是Generator类必须和template模板文件放在同一目录下，使用较简单
// MyGenerator2方式的前提是template模板文件在遵循一定规则下，可以放在任意位置，使用较复杂
// 推荐MyGenerator1方式
public class MyGenerator1 {
    public static void main(String[] args) {
        try {
            // 创建文件的输出的路径
            String generatePath = "E:/Download/Skeleton/";

            // 描述规则的配置文件所在的路径
            String propertiesPath = "config/skeleton-data.properties";

            // 构造全局配置文件对象
            SkeletonProperties skeletonProperties = new SkeletonProperties(propertiesPath);

            // 创建Java类文件
            // 模板文件MyApplication.java.template必须和MyApplicationClassGenerator放在同一个目录下
            new MyApplicationClassGenerator(generatePath, "server", skeletonProperties).generate();

            // 创建文件到resources目录下
            // 模板文件mybatis-generator.xml.template必须和MybatisGeneratorXmlGenerator放在同一个目录下
            new MybatisGeneratorXmlGenerator(generatePath, "service", skeletonProperties).generate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
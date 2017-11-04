package com.nepxion.skeleton.springcloud;

/**
 * <p>Title: Nepxion Skeleton Generator Spring Cloud</p>
 * <p>Description: Nepxion Skeleton Generator For Spring Cloud</p>
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
            String generatePath = "E:/Download/Skeleton/";
            String basePackagePath = "com/nepxion/skeleton/springcloud";
            String propertiesPath = "springcloud.properties";

            SkeletonProperties skeletonProperties = new SkeletonProperties(propertiesPath);

            SkeletonContext serverSkeletonContext = new SkeletonContext("server", basePackagePath);
            new MyApplicationClassGenerator(generatePath, skeletonProperties, serverSkeletonContext).generate();

            SkeletonContext serviceSkeletonContext = new SkeletonContext("service", basePackagePath);
            new MybatisGeneratorXmlGenerator(generatePath, skeletonProperties, serviceSkeletonContext).generate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package com.nepxion.skeleton.springcloud.service;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.io.IOException;

import com.nepxion.skeleton.engine.context.SkeletonContext;
import com.nepxion.skeleton.engine.exception.SkeletonException;
import com.nepxion.skeleton.engine.property.SkeletonProperties;
import com.nepxion.skeleton.framework.service.SkeletonService;
import com.nepxion.skeleton.springcloud.generator.server.PomXmlGenerator;
import com.nepxion.skeleton.springcloud.generator.server.java.ServerApplicationClassGenerator;
import com.nepxion.skeleton.springcloud.generator.server.java.ServerConfigClassGenerator;
import com.nepxion.skeleton.springcloud.generator.server.java.ServerControllerClassGenerator;
import com.nepxion.skeleton.springcloud.generator.server.java.TestServerApplicationClassGenerator;
import com.nepxion.skeleton.springcloud.generator.server.resources.ApplicationPropertiesGenerator;
import com.nepxion.skeleton.springcloud.generator.shared.resources.LogbackXmlGenerator;

import freemarker.template.TemplateException;

public class ServerProjectServiceImpl implements SkeletonService {
    @Override
    public void generate(String generatePath, SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) throws SkeletonException, TemplateException, IOException {
        // 创建Java类文件到main/java目录下
        new ServerApplicationClassGenerator(generatePath, skeletonContext, skeletonProperties).generate();
        new ServerControllerClassGenerator(generatePath, skeletonContext, skeletonProperties).generate();
        new ServerConfigClassGenerator(generatePath, skeletonContext, skeletonProperties).generate();

        // 创建Java类文件到test/java目录下
        new TestServerApplicationClassGenerator(generatePath, skeletonContext, skeletonProperties).generate();

        // 创建文件到main/resources目录下
        new ApplicationPropertiesGenerator(generatePath, skeletonContext, skeletonProperties).generate();
        new LogbackXmlGenerator(generatePath, skeletonContext, skeletonProperties, "server").generate();

        // 创建文件到根目录下
        new PomXmlGenerator(generatePath, skeletonContext, skeletonProperties).generate();
    }
}
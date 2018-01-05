package com.nepxion.skeleton.service.service;

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
import com.nepxion.skeleton.service.generator.server.PomXmlGenerator;
import com.nepxion.skeleton.service.generator.server.java.ServerApplicationClassGenerator;
import com.nepxion.skeleton.service.generator.server.java.ServerConfigClassGenerator;
import com.nepxion.skeleton.service.generator.server.java.ServerControllerClassGenerator;
import com.nepxion.skeleton.service.generator.server.java.TestServerApplicationClassGenerator;
import com.nepxion.skeleton.service.generator.server.resources.ApplicationPropertiesGenerator;
import com.nepxion.skeleton.service.generator.shared.resources.LogbackXmlGenerator;

import freemarker.template.TemplateException;

public class ServerProjectServiceImpl implements SkeletonService {
    @Override
    public void generate(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) throws SkeletonException, TemplateException, IOException {
        // 创建Java类文件到main/java目录下
        new ServerApplicationClassGenerator(skeletonContext, skeletonProperties).generate();
        new ServerControllerClassGenerator(skeletonContext, skeletonProperties).generate();
        new ServerConfigClassGenerator(skeletonContext, skeletonProperties).generate();

        // 创建Java类文件到test/java目录下
        new TestServerApplicationClassGenerator(skeletonContext, skeletonProperties).generate();

        // 创建文件到main/resources目录下
        new ApplicationPropertiesGenerator(skeletonContext, skeletonProperties).generate();
        new LogbackXmlGenerator(skeletonContext, skeletonProperties, "server").generate();

        // 创建文件到根目录下
        new PomXmlGenerator(skeletonContext, skeletonProperties).generate();
    }
}
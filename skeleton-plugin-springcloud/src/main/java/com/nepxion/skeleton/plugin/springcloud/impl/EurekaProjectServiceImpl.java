package com.nepxion.skeleton.plugin.springcloud.impl;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.io.IOException;

import com.nepxion.skeleton.engine.context.SkeletonContext;
import com.nepxion.skeleton.engine.exception.SkeletonException;
import com.nepxion.skeleton.engine.property.SkeletonProperties;
import com.nepxion.skeleton.framework.service.SkeletonService;
import com.nepxion.skeleton.plugin.springcloud.generator.eureka.PomXmlGenerator;
import com.nepxion.skeleton.plugin.springcloud.generator.eureka.java.EurekaApplicationClassGenerator;
import com.nepxion.skeleton.plugin.springcloud.generator.eureka.resources.ApplicationPropertiesGenerator;
import com.nepxion.skeleton.plugin.springcloud.generator.shared.resources.LogbackXmlGenerator;

import freemarker.template.TemplateException;

public class EurekaProjectServiceImpl implements SkeletonService {
    @Override
    public void generate(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) throws SkeletonException, TemplateException, IOException {
        // 创建Java类文件到main/java目录下
        new EurekaApplicationClassGenerator(skeletonContext, skeletonProperties).generate();
        new ApplicationPropertiesGenerator(skeletonContext, skeletonProperties).generate();

        // 创建文件到main/resources目录下
        new LogbackXmlGenerator(skeletonContext, skeletonProperties, "eureka").generate();

        // 创建文件到根目录下
        new PomXmlGenerator(skeletonContext, skeletonProperties).generate();
    }
}
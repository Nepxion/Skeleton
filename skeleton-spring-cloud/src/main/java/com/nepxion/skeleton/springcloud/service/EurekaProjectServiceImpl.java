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

import com.nepxion.skeleton.engine.exception.SkeletonException;
import com.nepxion.skeleton.engine.property.SkeletonProperties;
import com.nepxion.skeleton.framework.service.SkeletonService;
import com.nepxion.skeleton.springcloud.generator.eureka.PomXmlGenerator;
import com.nepxion.skeleton.springcloud.generator.eureka.java.EurekaApplicationClassGenerator;
import com.nepxion.skeleton.springcloud.generator.eureka.resources.ApplicationPropertiesGenerator;
import com.nepxion.skeleton.springcloud.generator.eureka.resources.LogbackXmlGenerator;

import freemarker.template.TemplateException;

public class EurekaProjectServiceImpl implements SkeletonService {
    @Override
    public void generate(String path, String prefixTemplateDirectory, String reducedTemplateDirectory, SkeletonProperties skeletonProperties) throws SkeletonException, TemplateException, IOException {
        String projectType = "eureka";

        // 创建Java类文件到main/java目录下
        new EurekaApplicationClassGenerator(path, projectType, prefixTemplateDirectory, reducedTemplateDirectory, skeletonProperties).generate();

        // 创建文件到main/resources目录下
        new ApplicationPropertiesGenerator(path, projectType, prefixTemplateDirectory, reducedTemplateDirectory, skeletonProperties).generate();

        // 创建文件到main/resources目录下
        new LogbackXmlGenerator(path, projectType, prefixTemplateDirectory, reducedTemplateDirectory, skeletonProperties).generate();

        // 创建文件到根目录下
        new PomXmlGenerator(path, projectType, prefixTemplateDirectory, reducedTemplateDirectory, skeletonProperties).generate();
    }
}
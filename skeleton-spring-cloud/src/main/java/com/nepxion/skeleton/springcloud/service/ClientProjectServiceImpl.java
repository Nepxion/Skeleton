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
import com.nepxion.skeleton.springcloud.generator.client.PomXmlGenerator;
import com.nepxion.skeleton.springcloud.generator.client.java.AbstractClientTestClassGenerator;
import com.nepxion.skeleton.springcloud.generator.client.java.ClientApplicationClassGenerator;
import com.nepxion.skeleton.springcloud.generator.client.java.ClientContextAwareClassGenerator;
import com.nepxion.skeleton.springcloud.generator.client.java.ClientControllerClassGenerator;
import com.nepxion.skeleton.springcloud.generator.client.java.ClientRestTestClassGenerator;
import com.nepxion.skeleton.springcloud.generator.client.java.ClientRpcTestClassGenerator;
import com.nepxion.skeleton.springcloud.generator.client.java.ClientServiceClassGenerator;
import com.nepxion.skeleton.springcloud.generator.client.resources.ApplicationPropertiesGenerator;
import com.nepxion.skeleton.springcloud.generator.shared.resources.LogbackXmlGenerator;

import freemarker.template.TemplateException;

public class ClientProjectServiceImpl implements SkeletonService {
    @Override
    public void generate(String generatePath, String prefixTemplatePath, String reducedTemplatePath, SkeletonProperties skeletonProperties) throws SkeletonException, TemplateException, IOException {
        String projectType = "client";
        String dependencyProjectType = "server";

        // 创建Java类文件到main/java目录下
        new ClientApplicationClassGenerator(generatePath, projectType, prefixTemplatePath, reducedTemplatePath, skeletonProperties).generate();

        // 创建Java类文件到main/java目录下
        new ClientContextAwareClassGenerator(generatePath, projectType, prefixTemplatePath, reducedTemplatePath, skeletonProperties).generate();

        // 创建Java类文件到main/java目录下
        new ClientControllerClassGenerator(generatePath, projectType, prefixTemplatePath, reducedTemplatePath, skeletonProperties).generate();

        // 创建Java类文件到main/java目录下
        new ClientServiceClassGenerator(generatePath, projectType, prefixTemplatePath, reducedTemplatePath, skeletonProperties).generate();

        // 创建Java类文件到main/java目录下
        new AbstractClientTestClassGenerator(generatePath, projectType, prefixTemplatePath, reducedTemplatePath, skeletonProperties).generate();

        // 创建Java类文件到main/java目录下
        new ClientRpcTestClassGenerator(generatePath, projectType, prefixTemplatePath, reducedTemplatePath, skeletonProperties).generate();

        // 创建Java类文件到main/java目录下
        new ClientRestTestClassGenerator(generatePath, projectType, prefixTemplatePath, reducedTemplatePath, skeletonProperties).generate();

        // 创建文件到main/resources目录下
        new ApplicationPropertiesGenerator(generatePath, projectType, dependencyProjectType, prefixTemplatePath, reducedTemplatePath, skeletonProperties).generate();

        // 创建文件到main/resources目录下
        new LogbackXmlGenerator(generatePath, projectType, prefixTemplatePath, reducedTemplatePath, skeletonProperties).generate();

        // 创建文件到根目录下
        new PomXmlGenerator(generatePath, projectType, prefixTemplatePath, reducedTemplatePath, skeletonProperties).generate();
    }
}
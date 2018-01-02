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
    public void generate(String generatePath, SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) throws SkeletonException, TemplateException, IOException {
        // 创建Java类文件到main/java目录下
        new ClientApplicationClassGenerator(generatePath, skeletonContext, skeletonProperties).generate();
        new ClientContextAwareClassGenerator(generatePath, skeletonContext, skeletonProperties).generate();
        new ClientControllerClassGenerator(generatePath, skeletonContext, skeletonProperties).generate();
        new ClientServiceClassGenerator(generatePath, skeletonContext, skeletonProperties).generate();
        new AbstractClientTestClassGenerator(generatePath, skeletonContext, skeletonProperties).generate();
        new ClientRpcTestClassGenerator(generatePath, skeletonContext, skeletonProperties).generate();
        new ClientRestTestClassGenerator(generatePath, skeletonContext, skeletonProperties).generate();

        // 创建文件到main/resources目录下
        new ApplicationPropertiesGenerator(generatePath, skeletonContext, skeletonProperties).generate();
        new LogbackXmlGenerator(generatePath, skeletonContext, skeletonProperties, "client").generate();

        // 创建文件到根目录下
        new PomXmlGenerator(generatePath, skeletonContext, skeletonProperties).generate();
    }
}
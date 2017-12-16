package com.nepxion.skeleton.generator;

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

import com.nepxion.skeleton.exception.SkeletonException;
import com.nepxion.skeleton.generator.server.PomXmlGenerator;
import com.nepxion.skeleton.generator.server.java.ServerApplicationClassGenerator;
import com.nepxion.skeleton.generator.server.resources.ApplicationPropertiesGenerator;
import com.nepxion.skeleton.property.SkeletonProperties;

import freemarker.template.TemplateException;

public class GeneratorService {
    public void generator(String path, String prefixTemplateDirectory, String reducedTemplateDirectory, SkeletonProperties skeletonProperties) throws SkeletonException, TemplateException, IOException {
        // 创建Java类文件
        new ServerApplicationClassGenerator(path, "server", prefixTemplateDirectory, reducedTemplateDirectory, skeletonProperties).generate();

        // 创建文件到resources目录下
        new ApplicationPropertiesGenerator(path, "server", prefixTemplateDirectory, reducedTemplateDirectory, skeletonProperties).generate();

        // 创建文件到目录下
        new PomXmlGenerator(path, "server", prefixTemplateDirectory, reducedTemplateDirectory, skeletonProperties).generate();
    }
}
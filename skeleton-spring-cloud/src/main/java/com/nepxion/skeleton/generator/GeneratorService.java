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
    public void generator(String path, String templateDirectory, SkeletonProperties skeletonProperties) throws SkeletonException, TemplateException, IOException {
        // 创建Java类文件
        // 模板文件ServerApplication.java.template必须和ServerApplicationClassGenerator.java放在同一个目录下，下同
        new ServerApplicationClassGenerator(path, "server", templateDirectory, skeletonProperties).generate();

        // 创建文件到resources目录下
        new ApplicationPropertiesGenerator(path, "server", templateDirectory, skeletonProperties).generate();

        // 创建文件到目录下
        new PomXmlGenerator(path, "server", templateDirectory, skeletonProperties).generate();
    }
}
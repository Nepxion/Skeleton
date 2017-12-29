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
import com.nepxion.skeleton.springcloud.generator.eureka.PomXmlGenerator;

import freemarker.template.TemplateException;

public class EurekaGenerator {
    public void generate(String path, String prefixTemplateDirectory, String reducedTemplateDirectory, SkeletonProperties skeletonProperties) throws SkeletonException, TemplateException, IOException {
        // 创建文件到根目录下
        new PomXmlGenerator(path, "eureka", prefixTemplateDirectory, reducedTemplateDirectory, skeletonProperties).generate();
    }
}
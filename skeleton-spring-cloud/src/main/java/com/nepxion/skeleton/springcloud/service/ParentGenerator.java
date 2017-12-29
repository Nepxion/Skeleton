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
import com.nepxion.skeleton.springcloud.generator.GitAttributesGenerator;
import com.nepxion.skeleton.springcloud.generator.GitIgnoreGenerator;
import com.nepxion.skeleton.springcloud.generator.InstallDockerBatGenerator;
import com.nepxion.skeleton.springcloud.generator.InstallDockerShGenerator;
import com.nepxion.skeleton.springcloud.generator.PomXmlGenerator;

import freemarker.template.TemplateException;

public class ParentGenerator {
    public void generate(String path, String prefixTemplateDirectory, String reducedTemplateDirectory, SkeletonProperties skeletonProperties) throws SkeletonException, TemplateException, IOException {
        // 创建文件到顶级目录下
        new PomXmlGenerator(path, null, prefixTemplateDirectory, reducedTemplateDirectory, skeletonProperties).generate();

        new InstallDockerBatGenerator(path, null, "server", prefixTemplateDirectory, reducedTemplateDirectory, skeletonProperties).generate();
        
        new InstallDockerShGenerator(path, null, "server", prefixTemplateDirectory, reducedTemplateDirectory, skeletonProperties).generate();        

        new GitAttributesGenerator(path, null, prefixTemplateDirectory, reducedTemplateDirectory, skeletonProperties).generate();

        new GitIgnoreGenerator(path, null, prefixTemplateDirectory, reducedTemplateDirectory, skeletonProperties).generate();
    }
}
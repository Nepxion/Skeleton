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
import com.nepxion.skeleton.springcloud.generator.GitAttributesGenerator;
import com.nepxion.skeleton.springcloud.generator.GitIgnoreGenerator;
import com.nepxion.skeleton.springcloud.generator.InstallDockerBatGenerator;
import com.nepxion.skeleton.springcloud.generator.InstallDockerShGenerator;
import com.nepxion.skeleton.springcloud.generator.PomXmlGenerator;

import freemarker.template.TemplateException;

public class ParentProjectServiceImpl implements SkeletonService {
    @Override
    public void generate(String generatePath, String prefixTemplateDirectory, String reducedTemplateDirectory, SkeletonProperties skeletonProperties) throws SkeletonException, TemplateException, IOException {
        String projectType = null;
        String subProjectType = "server";

        // 创建文件到顶级目录下
        new PomXmlGenerator(generatePath, projectType, prefixTemplateDirectory, reducedTemplateDirectory, skeletonProperties).generate();

        new InstallDockerBatGenerator(generatePath, projectType, subProjectType, prefixTemplateDirectory, reducedTemplateDirectory, skeletonProperties).generate();

        new InstallDockerShGenerator(generatePath, projectType, subProjectType, prefixTemplateDirectory, reducedTemplateDirectory, skeletonProperties).generate();

        new GitAttributesGenerator(generatePath, projectType, prefixTemplateDirectory, reducedTemplateDirectory, skeletonProperties).generate();

        new GitIgnoreGenerator(generatePath, projectType, prefixTemplateDirectory, reducedTemplateDirectory, skeletonProperties).generate();
    }
}
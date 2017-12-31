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
import com.nepxion.skeleton.springcloud.generator.InstallDockerShellGenerator;
import com.nepxion.skeleton.springcloud.generator.PomXmlGenerator;

import freemarker.template.TemplateException;

public class ParentProjectServiceImpl implements SkeletonService {
    @Override
    public void generate(String generatePath, String prefixTemplatePath, String reducedTemplatePath, SkeletonProperties skeletonProperties) throws SkeletonException, TemplateException, IOException {
        String projectType = null;

        // 创建文件到顶级目录下
        new PomXmlGenerator(generatePath, projectType, prefixTemplatePath, reducedTemplatePath, skeletonProperties).generate();

        new InstallDockerShellGenerator(generatePath, projectType, "eureka", "bat", null, prefixTemplatePath, reducedTemplatePath, skeletonProperties).generate();

        new InstallDockerShellGenerator(generatePath, projectType, "eureka", "sh", null, prefixTemplatePath, reducedTemplatePath, skeletonProperties).generate();

        new InstallDockerShellGenerator(generatePath, projectType, "server", "bat", null, prefixTemplatePath, reducedTemplatePath, skeletonProperties).generate();

        new InstallDockerShellGenerator(generatePath, projectType, "server", "sh", null, prefixTemplatePath, reducedTemplatePath, skeletonProperties).generate();

        new InstallDockerShellGenerator(generatePath, projectType, "client", "bat", skeletonProperties.getString("serviceName") + "-server", prefixTemplatePath, reducedTemplatePath, skeletonProperties).generate();

        new InstallDockerShellGenerator(generatePath, projectType, "client", "sh", skeletonProperties.getString("serviceName") + "-server", prefixTemplatePath, reducedTemplatePath, skeletonProperties).generate();

        new GitAttributesGenerator(generatePath, projectType, prefixTemplatePath, reducedTemplatePath, skeletonProperties).generate();

        new GitIgnoreGenerator(generatePath, projectType, prefixTemplatePath, reducedTemplatePath, skeletonProperties).generate();
    }
}
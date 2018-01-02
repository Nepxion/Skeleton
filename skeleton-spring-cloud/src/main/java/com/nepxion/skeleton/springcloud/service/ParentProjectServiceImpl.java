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
import com.nepxion.skeleton.springcloud.generator.GitAttributesGenerator;
import com.nepxion.skeleton.springcloud.generator.GitIgnoreGenerator;
import com.nepxion.skeleton.springcloud.generator.InstallDockerShellGenerator;
import com.nepxion.skeleton.springcloud.generator.PomXmlGenerator;

import freemarker.template.TemplateException;

public class ParentProjectServiceImpl implements SkeletonService {
    @Override
    public void generate(String generatePath, SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) throws SkeletonException, TemplateException, IOException {
        // 创建文件到顶级目录下
        new PomXmlGenerator(generatePath, skeletonContext, skeletonProperties).generate();
        new InstallDockerShellGenerator(generatePath, skeletonContext, skeletonProperties, "eureka", "bat", null).generate();
        new InstallDockerShellGenerator(generatePath, skeletonContext, skeletonProperties, "eureka", "sh", null).generate();
        new InstallDockerShellGenerator(generatePath, skeletonContext, skeletonProperties, "server", "bat", null).generate();
        new InstallDockerShellGenerator(generatePath, skeletonContext, skeletonProperties, "server", "sh", null).generate();
        new InstallDockerShellGenerator(generatePath, skeletonContext, skeletonProperties, "client", "bat", skeletonProperties.getString("serviceName") + "-server").generate();
        new InstallDockerShellGenerator(generatePath, skeletonContext, skeletonProperties, "client", "sh", skeletonProperties.getString("serviceName") + "-server").generate();
        new GitAttributesGenerator(generatePath, skeletonContext, skeletonProperties).generate();
        new GitIgnoreGenerator(generatePath, skeletonContext, skeletonProperties).generate();
    }
}
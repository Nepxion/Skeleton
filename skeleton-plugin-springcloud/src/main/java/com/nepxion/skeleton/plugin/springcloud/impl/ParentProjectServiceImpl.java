package com.nepxion.skeleton.plugin.springcloud.impl;

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
import com.nepxion.skeleton.plugin.springcloud.generator.GitAttributesGenerator;
import com.nepxion.skeleton.plugin.springcloud.generator.GitIgnoreGenerator;
import com.nepxion.skeleton.plugin.springcloud.generator.InstallDockerShellGenerator;
import com.nepxion.skeleton.plugin.springcloud.generator.PomXmlGenerator;

import freemarker.template.TemplateException;

public class ParentProjectServiceImpl implements SkeletonService {
    @Override
    public void generate(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) throws SkeletonException, TemplateException, IOException {
        // 创建文件到顶级目录下
        new PomXmlGenerator(skeletonContext, skeletonProperties).generate();
        new InstallDockerShellGenerator(skeletonContext, skeletonProperties, "eureka", "bat", null).generate();
        new InstallDockerShellGenerator(skeletonContext, skeletonProperties, "eureka", "sh", null).generate();
        new InstallDockerShellGenerator(skeletonContext, skeletonProperties, "server", "bat", null).generate();
        new InstallDockerShellGenerator(skeletonContext, skeletonProperties, "server", "sh", null).generate();
        new InstallDockerShellGenerator(skeletonContext, skeletonProperties, "client", "bat", skeletonProperties.getString("serviceName") + "-server").generate();
        new InstallDockerShellGenerator(skeletonContext, skeletonProperties, "client", "sh", skeletonProperties.getString("serviceName") + "-server").generate();
        new GitAttributesGenerator(skeletonContext, skeletonProperties).generate();
        new GitIgnoreGenerator(skeletonContext, skeletonProperties).generate();
    }
}
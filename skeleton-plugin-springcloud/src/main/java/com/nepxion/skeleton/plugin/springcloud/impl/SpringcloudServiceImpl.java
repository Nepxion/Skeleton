package com.nepxion.skeleton.plugin.springcloud.impl;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.nepxion.skeleton.engine.context.SkeletonContext;
import com.nepxion.skeleton.engine.exception.SkeletonException;
import com.nepxion.skeleton.engine.property.SkeletonProperties;
import com.nepxion.skeleton.framework.annotation.SkeletonPlugin;
import com.nepxion.skeleton.framework.service.SkeletonService;

import freemarker.template.TemplateException;

@Component
@SkeletonPlugin(name="springcloud")
public class SpringcloudServiceImpl implements SkeletonService {
    private SkeletonService parentProjectService = new ParentProjectServiceImpl();
    private SkeletonService eurekaProjectService = new EurekaProjectServiceImpl();
    private SkeletonService serverProjectService = new ServerProjectServiceImpl();
    private SkeletonService clientProjectService = new ClientProjectServiceImpl();

    @Override
    public void generate(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) throws SkeletonException, TemplateException, IOException {
        parentProjectService.generate(skeletonContext, skeletonProperties);
        eurekaProjectService.generate(skeletonContext, skeletonProperties);
        serverProjectService.generate(skeletonContext, skeletonProperties);
        clientProjectService.generate(skeletonContext, skeletonProperties);
    }
}
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

import freemarker.template.TemplateException;

public class SkeletonServiceImpl implements SkeletonService {
    @Override
    public void generate(String generatePath, String prefixTemplatePath, String reducedTemplatePath, SkeletonProperties skeletonProperties) throws SkeletonException, TemplateException, IOException {
        new ParentProjectServiceImpl().generate(generatePath, prefixTemplatePath, reducedTemplatePath, skeletonProperties);

        new EurekaProjectServiceImpl().generate(generatePath, prefixTemplatePath, reducedTemplatePath, skeletonProperties);

        new ServerProjectServiceImpl().generate(generatePath, prefixTemplatePath, reducedTemplatePath, skeletonProperties);

        new ClientProjectServiceImpl().generate(generatePath, prefixTemplatePath, reducedTemplatePath, skeletonProperties);
    }
}
package com.nepxion.skeleton.context;

/**
 * <p>Title: Nepxion Skeleton Generator</p>
 * <p>Description: Nepxion Skeleton Generator For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import com.nepxion.skeleton.config.SkeletonConfig;

public class SkeletonContext {
    private SkeletonPath skeletonPath;
    private SkeletonConfig javaConfig;
    private SkeletonConfig resourcesConfig;
    private SkeletonConfig dockerConfig;
    private SkeletonConfig projectConfig;
    
    public SkeletonContext(String projectType) {
        this(projectType, null);
    }

    public SkeletonContext(String projectType, String templatePath) {
        skeletonPath = new SkeletonPath(projectType, templatePath);
        javaConfig = new SkeletonConfig(skeletonPath.getJavaTemplatePath());
        resourcesConfig = new SkeletonConfig(skeletonPath.getResourcesTemplatePath());
        dockerConfig = new SkeletonConfig(skeletonPath.getDockerTemplatePath());
        projectConfig = new SkeletonConfig(skeletonPath.getProjectTemplatePath());
    }

    public SkeletonPath getSkeletonPath() {
        return skeletonPath;
    }

    public SkeletonConfig getJavaConfig() {
        return javaConfig;
    }

    public SkeletonConfig getResourcesConfig() {
        return resourcesConfig;
    }

    public SkeletonConfig getDockerConfig() {
        return dockerConfig;
    }

    public SkeletonConfig getProjectConfig() {
        return projectConfig;
    }
}
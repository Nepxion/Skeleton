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

import org.apache.commons.lang3.StringUtils;

import com.nepxion.skeleton.constant.SkeletonConstant;
import com.nepxion.skeleton.util.SkeletonUtil;

public class SkeletonPath {
    private String projectType;
    private String basePackagePath;

    public SkeletonPath(String projectType, String basePackagePath) {
        this.projectType = projectType;
        this.basePackagePath = basePackagePath;
    }

    public String getProjectType() {
        return projectType;
    }

    public String getBasePackagePath() {
        return basePackagePath;
    }

    public String generateTemplatePath() {
        if (StringUtils.isNotEmpty(basePackagePath)) {
            return SkeletonConstant.FILE_SEPARATOR + basePackagePath;
        }

        return SkeletonConstant.FILE_SEPARATOR + SkeletonConstant.TEMPLATE;
    }

    public String getJavaTemplatePath() {
        return SkeletonUtil.formatGeneratePath(generateTemplatePath()) + projectType + SkeletonConstant.FILE_SEPARATOR + SkeletonConstant.JAVA;
    }

    public String getResourcesTemplatePath() {
        return SkeletonUtil.formatGeneratePath(generateTemplatePath()) + projectType + SkeletonConstant.FILE_SEPARATOR + SkeletonConstant.RESOURCES;
    }

    public String getDockerTemplatePath() {
        return SkeletonUtil.formatGeneratePath(generateTemplatePath()) + projectType + SkeletonConstant.FILE_SEPARATOR + SkeletonConstant.DOCKER;
    }

    public String getProjectTemplatePath() {
        return SkeletonUtil.formatGeneratePath(generateTemplatePath()) + projectType + SkeletonConstant.FILE_SEPARATOR + SkeletonConstant.PROJECT;
    }
}
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
import com.nepxion.skeleton.constant.SkeletonConstant;
import com.nepxion.skeleton.entity.SkeletonFileType;
import com.nepxion.skeleton.util.SkeletonUtil;

public class SkeletonContext {
    private String projectType;

    private Class<?> generatorClass;

    private String baseTemplatePath;
    private SkeletonFileType fileType;

    private SkeletonConfig config;

    public SkeletonContext(String projectType, Class<?> generatorClass) {
        this.projectType = projectType;
        this.generatorClass = generatorClass;
        this.config = new SkeletonConfig(generateTemplatePath());
    }

    public SkeletonContext(String projectType, String baseTemplatePath, SkeletonFileType fileType) {
        this.projectType = projectType;
        this.baseTemplatePath = baseTemplatePath;
        this.fileType = fileType;
        this.config = new SkeletonConfig(generateTemplatePath());
    }

    public String getProjectType() {
        return projectType;
    }

    public Class<?> getGeneratorClass() {
        return generatorClass;
    }

    public String getBaseTemplatePath() {
        return baseTemplatePath;
    }

    public SkeletonFileType getFileType() {
        return fileType;
    }

    public SkeletonConfig getConfig() {
        return config;
    }

    private String generateTemplatePath() {
        if (generatorClass != null) {
            return SkeletonConstant.FILE_SEPARATOR + SkeletonUtil.formatGeneratePath(generatorClass);
        }

        return SkeletonConstant.FILE_SEPARATOR + SkeletonUtil.formatGeneratePath(baseTemplatePath) + projectType + SkeletonConstant.FILE_SEPARATOR + fileType;
    }
}
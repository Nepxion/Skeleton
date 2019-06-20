package com.nepxion.skeleton.engine.generator;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nepxion.skeleton.engine.constant.SkeletonConstant;
import com.nepxion.skeleton.engine.context.SkeletonContext;
import com.nepxion.skeleton.engine.entity.SkeletonFileType;
import com.nepxion.skeleton.engine.exception.SkeletonException;
import com.nepxion.skeleton.engine.property.SkeletonProperties;
import com.nepxion.skeleton.engine.util.SkeletonUtil;

public abstract class SkeletonJavaGenerator extends AbstractSkeletonGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(SkeletonJavaGenerator.class);

    protected String defaultBasePackage;
    protected String defaultTopBasePackage;
    protected String defaultOutputPath;

    public SkeletonJavaGenerator(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) {
        super(skeletonContext, skeletonProperties);

        initialize();
    }

    public SkeletonJavaGenerator(String generatePath, String projectType, String prefixTemplatePath, String reducedTemplatePath, Class<?> generatorClass, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, prefixTemplatePath, reducedTemplatePath, generatorClass, skeletonProperties);

        initialize();
    }

    public SkeletonJavaGenerator(String generatePath, String projectType, String baseTemplatePath, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, baseTemplatePath, SkeletonFileType.JAVA, skeletonProperties);

        initialize();
    }

    private void initialize() {
        String generatePath = skeletonContext.getGeneratePath();
        String projectType = skeletonContext.getProjectType();

        defaultBasePackage = SkeletonUtil.getBasePackagePath(projectType, skeletonProperties);
        defaultTopBasePackage = SkeletonUtil.getBasePackagePath(skeletonProperties);
        defaultOutputPath = SkeletonUtil.getOutputPath(generatePath, projectType, skeletonProperties);
    }

    public String getDefaultBasePackage() {
        return defaultBasePackage;
    }

    public String getDefaultTopBasePackage() {
        return defaultTopBasePackage;
    }

    public String getDefaultOutputPath() {
        return defaultOutputPath;
    }

    public Map<String, Object> getDefaultMapModel() {
        Map<String, Object> defaultMapModel = new HashMap<String, Object>();
        defaultMapModel.put(SkeletonConstant.TITLE, skeletonProperties.getString(SkeletonConstant.TITLE));
        defaultMapModel.put(SkeletonConstant.DESCRIPTION, skeletonProperties.getString(SkeletonConstant.DESCRIPTION));
        defaultMapModel.put(SkeletonConstant.COPYRIGHT, skeletonProperties.getString(SkeletonConstant.COPYRIGHT));
        defaultMapModel.put(SkeletonConstant.COMPANY, skeletonProperties.getString(SkeletonConstant.COMPANY));
        defaultMapModel.put(SkeletonConstant.AUTHOR, skeletonProperties.getString(SkeletonConstant.AUTHOR));
        defaultMapModel.put(SkeletonConstant.EMAIL, skeletonProperties.getString(SkeletonConstant.EMAIL));
        defaultMapModel.put(SkeletonConstant.VERSION, skeletonProperties.getString(SkeletonConstant.VERSION));

        return defaultMapModel;
    }

    @Override
    protected String getPath() throws SkeletonException {
        String className = null;
        String packagePath = null;
        String outputPath = null;
        boolean isMainCode = true;
        Object dataModel = null;

        try {
            className = getClassName();
            packagePath = getPackage();
            outputPath = getOutputPath();
            isMainCode = isMainCode();
            dataModel = getDataModel();
        } catch (Exception e) {
            throw new SkeletonException(e.getMessage(), e);
        }

        String fullTemplatePath = getFullTemplatePath();
        String fullOutputPath = SkeletonUtil.formatGeneratePath(outputPath) + (isMainCode ? SkeletonConstant.MAIN_JAVA_CODE_PATH : SkeletonConstant.TEST_JAVA_CODE_PATH) + packagePath.replace(".", SkeletonConstant.FILE_SEPARATOR) + SkeletonConstant.FILE_SEPARATOR + className + "." + SkeletonConstant.JAVA;

        LOG.debug("--------------- Java Generator Information ---------------");
        LOG.debug("Template Path : {}", fullTemplatePath);
        LOG.debug("Output Path : {}", fullOutputPath);
        LOG.debug("Package : {}", packagePath);
        LOG.debug("Data Model : {}", dataModel);
        LOG.debug("----------------------------------------------------------");

        return fullOutputPath;
    }

    protected String getPackage() {
        if (StringUtils.isEmpty(defaultBasePackage)) {
            throw new IllegalArgumentException("Default base package is null or empty");
        }

        return defaultBasePackage;
    }

    protected String getTopPackage() {
        if (StringUtils.isEmpty(defaultTopBasePackage)) {
            throw new IllegalArgumentException("Default top base package is null or empty");
        }

        return defaultTopBasePackage;
    }

    protected String getOutputPath() {
        if (StringUtils.isEmpty(defaultOutputPath)) {
            throw new IllegalArgumentException("Default output path is null or empty");
        }

        return defaultOutputPath;
    }

    protected abstract String getClassName();

    protected abstract boolean isMainCode();
}
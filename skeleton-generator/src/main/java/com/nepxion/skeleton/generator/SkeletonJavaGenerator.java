package com.nepxion.skeleton.generator;

/**
 * <p>Title: Nepxion Skeleton Generator</p>
 * <p>Description: Nepxion Skeleton Generator For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nepxion.skeleton.constant.SkeletonConstants;
import com.nepxion.skeleton.context.SkeletonContext;
import com.nepxion.skeleton.exception.SkeletonException;
import com.nepxion.skeleton.property.SkeletonProperties;
import com.nepxion.skeleton.util.SkeletonUtil;

public abstract class SkeletonJavaGenerator extends AbstractSkeletonGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(SkeletonJavaGenerator.class);

    protected String defaultBasePackage;
    protected String defaultOutputPath;

    public SkeletonJavaGenerator() {
        super();
    }

    public SkeletonJavaGenerator(String generatePath, SkeletonProperties skeletonProperties) {
        super(generatePath, skeletonProperties);
    }

    public SkeletonJavaGenerator(String generatePath, SkeletonProperties skeletonProperties, SkeletonContext skeletonContext) {
        super(generatePath, skeletonProperties, skeletonContext);

        defaultBasePackage = SkeletonUtil.getBasePackagePath(skeletonProperties, skeletonContext);
        defaultOutputPath = SkeletonUtil.getOutputPath(generatePath, skeletonProperties, skeletonContext);
    }

    public String getDefaultBasePackage() {
        return defaultBasePackage;
    }

    public String getDefaultOutputPath() {
        return defaultOutputPath;
    }

    public Map<String, Object> generateDataModel() {
        Map<String, Object> defaultDataModel = new HashMap<String, Object>();
        defaultDataModel.put(SkeletonConstants.TITLE, skeletonProperties.getString(SkeletonConstants.TITLE));
        defaultDataModel.put(SkeletonConstants.DESCRIPTION, skeletonProperties.getString(SkeletonConstants.DESCRIPTION));
        defaultDataModel.put(SkeletonConstants.COPYRIGHT, skeletonProperties.getString(SkeletonConstants.COPYRIGHT));
        defaultDataModel.put(SkeletonConstants.COMPANY, skeletonProperties.getString(SkeletonConstants.COMPANY));
        defaultDataModel.put(SkeletonConstants.AUTHOR, skeletonProperties.getString(SkeletonConstants.AUTHOR));
        defaultDataModel.put(SkeletonConstants.EMAIL, skeletonProperties.getString(SkeletonConstants.EMAIL));
        defaultDataModel.put(SkeletonConstants.VERSION, skeletonProperties.getString(SkeletonConstants.VERSION));

        return defaultDataModel;
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
            throw new SkeletonException("Get parameters error", e);
        }

        String fullPath = SkeletonUtil.formatGeneratePath(outputPath) + (isMainCode ? SkeletonConstants.MAIN_JAVA_CODE_PATH : SkeletonConstants.TEST_JAVA_CODE_PATH) + packagePath.replace(".", SkeletonConstants.FILE_SEPARATOR) + SkeletonConstants.FILE_SEPARATOR + className + "." + SkeletonConstants.JAVA;

        LOG.info("--------------- Java Generator Information ---------------");
        LOG.info("File Name : {}", className + ".java");
        LOG.info("Package : {}", packagePath);
        LOG.info("Path : {}", fullPath);
        LOG.info("Data Model : {}", dataModel);
        LOG.info("----------------------------------------------------------");

        return fullPath;
    }

    protected String getPackage() {
        if (StringUtils.isEmpty(defaultBasePackage)) {
            throw new IllegalArgumentException("Default base package is null or empty");
        }

        return defaultBasePackage;
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
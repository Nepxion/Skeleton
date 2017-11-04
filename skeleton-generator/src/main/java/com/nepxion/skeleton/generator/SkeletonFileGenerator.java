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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nepxion.skeleton.context.SkeletonContext;
import com.nepxion.skeleton.exception.SkeletonException;
import com.nepxion.skeleton.property.SkeletonProperties;
import com.nepxion.skeleton.util.SkeletonUtil;

public abstract class SkeletonFileGenerator extends AbstractSkeletonGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(SkeletonFileGenerator.class);

    protected String defaultOutputPath;

    public SkeletonFileGenerator() {
        super();
    }

    public SkeletonFileGenerator(String generatePath, SkeletonProperties skeletonProperties) {
        super(generatePath, skeletonProperties);
    }

    public SkeletonFileGenerator(String generatePath, SkeletonProperties skeletonProperties, SkeletonContext skeletonContext) {
        super(generatePath, skeletonProperties, skeletonContext);

        defaultOutputPath = SkeletonUtil.getOutputPath(generatePath, skeletonProperties, skeletonContext);
    }

    public String getDefaultOutputPath() {
        return defaultOutputPath;
    }

    @Override
    protected String getPath() throws SkeletonException {
        String fileName = null;
        String outputPath = null;
        Object dataModel = null;
        try {
            fileName = getFileName();
            outputPath = getOutputPath();
            dataModel = getDataModel();
        } catch (Exception e) {
            throw new SkeletonException("Get parameters error", e);
        }

        String fullPath = SkeletonUtil.formatGeneratePath(outputPath) + fileName;

        LOG.info("--------------- File Generator Information ---------------");
        LOG.info("File Name : {}", fileName);
        LOG.info("Path : {}", fullPath);
        LOG.info("Data Model : {}", dataModel);
        LOG.info("----------------------------------------------------------");

        return fullPath;
    }

    protected String getOutputPath() {
        if (StringUtils.isEmpty(defaultOutputPath)) {
            throw new IllegalArgumentException("Default output path is null or empty");
        }

        return defaultOutputPath;
    }

    protected abstract String getFileName();
}
package com.nepxion.skeleton.util;

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
import com.nepxion.skeleton.property.SkeletonProperties;

public class SkeletonUtil {
    public static String getOutputPath(String generatePath, SkeletonProperties skeletonProperties) {
        return getOutputPath(generatePath, null, skeletonProperties);
    }

    public static String getOutputPath(String generatePath, String projectType, SkeletonProperties skeletonProperties) {
        return formatGeneratePath(generatePath) + (StringUtils.isNotEmpty(projectType) ? getBaseDirectoryName(projectType, skeletonProperties) + "/" : "");
    }

    public static String getBaseDirectoryName(SkeletonProperties skeletonProperties) {
        return getBaseDirectoryName(null, skeletonProperties);
    }

    public static String getBaseDirectoryName(String projectType, SkeletonProperties skeletonProperties) {
        return skeletonProperties.getString(SkeletonConstant.MODULE_NAME) + (StringUtils.isNotEmpty(projectType) ? "-" + projectType : "");
    }

    public static String getBasePackagePath(SkeletonProperties skeletonProperties) {
        return getBasePackagePath(null, skeletonProperties);
    }

    public static String getBasePackagePath(String projectType, SkeletonProperties skeletonProperties) {
        String moduleName = skeletonProperties.getString(SkeletonConstant.MODULE_NAME);

        return skeletonProperties.getString(SkeletonConstant.BASE_PACKAGE) + "." + formatModuleName(moduleName) + (StringUtils.isNotEmpty(projectType) ? "." + projectType : "");
    }

    public static String getCanonicalPath(String generatePath, String directoryName, SkeletonProperties skeletonProperties) {
        return formatGeneratePath(generatePath) + directoryName + getBaseDirectoryName(skeletonProperties);
    }

    public static String formatGeneratePath(Class<?> generatorClass) {
        StringBuilder sb = new StringBuilder();
        sb.append(generatorClass.getCanonicalName());

        String path = sb.toString();
        path = path.substring(0, path.lastIndexOf("."));
        path = path.replace(".", SkeletonConstant.FILE_SEPARATOR);
        path += SkeletonConstant.FILE_SEPARATOR;

        return path;
    }

    public static String formatGeneratePath(String generatePath) {
        StringBuilder sb = new StringBuilder();
        sb.append(generatePath);

        String path = sb.toString();
        path = path.replace("\\", SkeletonConstant.FILE_SEPARATOR);
        if (!path.endsWith(SkeletonConstant.FILE_SEPARATOR)) {
            path += SkeletonConstant.FILE_SEPARATOR;
        }

        return path;
    }

    public static String formatModuleName(String moduleName) {
        StringBuilder sb = new StringBuilder();

        String[] splittedModuleNames = moduleName.split("-");
        for (String splittedModuleName : splittedModuleNames) {
            sb.append(splittedModuleName.trim()).append(".");
        }

        String formattedModuleName = sb.toString();

        return formattedModuleName.substring(0, formattedModuleName.lastIndexOf("."));
    }
}
package com.nepxion.skeleton.handler;

/**
 * <p>Title: Nepxion Skeleton Generator</p>
 * <p>Description: Nepxion Skeleton Generator For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.io.IOException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nepxion.skeleton.constant.SkeletonConstant;
import com.nepxion.skeleton.exception.SkeletonException;
import com.nepxion.skeleton.property.SkeletonProperties;

public class SkeletonHandler {
    private static final Logger LOG = LoggerFactory.getLogger(SkeletonHandler.class);

    private static String generatePath;
    private static SkeletonProperties skeletonDataProperties;
    private static SkeletonProperties skeletonDescriptionProperties;
    private static SkeletonProperties skeletonItemListProperties;

    public static String getGeneratePath() {
        if (StringUtils.isEmpty(generatePath)) {
            throw new SkeletonException("Generate path is null or empty");
        }

        return generatePath;
    }

    public static void setGeneratePath(String generatePath) {
        SkeletonHandler.generatePath = generatePath;
    }

    public static SkeletonProperties getSkeletonDataProperties() {
        if (skeletonDataProperties == null) {
            synchronized (SkeletonHandler.class) {
                if (skeletonDataProperties == null) {
                    try {
                        skeletonDataProperties = new SkeletonProperties(SkeletonConstant.SKELETON_DATA_FILE, SkeletonConstant.ENCODING_UTF_8);
                    } catch (ConfigurationException e) {
                        LOG.error("Parse properties failed", e);
                        e.printStackTrace();
                    } catch (IOException e) {
                        LOG.error("Parse properties failed", e);
                        e.printStackTrace();
                    }
                }
            }
        }

        return skeletonDataProperties;
    }

    public static SkeletonProperties getSkeletonDescriptionProperties() {
        if (skeletonDescriptionProperties == null) {
            synchronized (SkeletonHandler.class) {
                if (skeletonDescriptionProperties == null) {
                    try {
                        skeletonDescriptionProperties = new SkeletonProperties(SkeletonConstant.SKELETON_DESCRIPTION_FILE, SkeletonConstant.ENCODING_UTF_8);
                    } catch (ConfigurationException e) {
                        LOG.error("Parse properties failed", e);
                        e.printStackTrace();
                    } catch (IOException e) {
                        LOG.error("Parse properties failed", e);
                        e.printStackTrace();
                    }
                }
            }
        }

        return skeletonDescriptionProperties;
    }

    public static SkeletonProperties getSkeletonItemListProperties() {
        if (skeletonItemListProperties == null) {
            synchronized (SkeletonHandler.class) {
                if (skeletonItemListProperties == null) {
                    try {
                        skeletonItemListProperties = new SkeletonProperties(SkeletonConstant.SKELETON_ITEM_LIST_FILE, SkeletonConstant.ENCODING_UTF_8);
                    } catch (ConfigurationException e) {
                        LOG.error("Parse properties failed", e);
                        e.printStackTrace();
                    } catch (IOException e) {
                        LOG.error("Parse properties failed", e);
                        e.printStackTrace();
                    }
                }
            }
        }

        return skeletonItemListProperties;
    }
}
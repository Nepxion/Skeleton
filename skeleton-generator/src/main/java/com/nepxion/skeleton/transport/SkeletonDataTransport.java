package com.nepxion.skeleton.transport;

/**
 * <p>Title: Nepxion Skeleton Generator</p>
 * <p>Description: Nepxion Skeleton Generator For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.io.File;

import org.apache.commons.lang.StringUtils;

import com.nepxion.skeleton.constant.SkeletonConstant;
import com.nepxion.skeleton.exception.SkeletonException;
import com.nepxion.skeleton.property.SkeletonProperties;
import com.nepxion.skeleton.util.FileUtil;
import com.nepxion.skeleton.util.SkeletonUtil;
import com.nepxion.skeleton.util.ZipUtil;

public abstract class SkeletonDataTransport {
    public byte[] download(String generatePath, String directoryName, String config) {
        if (StringUtils.isEmpty(generatePath)) {
            throw new SkeletonException("Generate path is null or empty");
        }

        if (StringUtils.isEmpty(generatePath)) {
            throw new SkeletonException("Directory name is null or empty");
        }

        if (StringUtils.isEmpty(config)) {
            throw new SkeletonException("Config content is null or empty");
        }

        try {
            SkeletonProperties skeletonProperties = new SkeletonProperties(new StringBuilder(config), SkeletonConstant.ENCODING_UTF_8);

            String path = SkeletonUtil.getCanonicalPath(generatePath, directoryName, skeletonProperties);

            generate(path, skeletonProperties);

            String zipFilePath = ZipUtil.zip(path, null);
            File zipFile = new File(zipFilePath);

            return FileUtil.getBytes(zipFile);
        } catch (Exception e) {
            throw new SkeletonException(e.getMessage(), e);
        } finally {
            File directory = new File(generatePath);

            FileUtil.forceDeleteDirectory(directory, 5);
        }
    }

    public abstract void generate(String path, SkeletonProperties skeletonProperties) throws Exception;
}
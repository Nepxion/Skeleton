package com.nepxion.skeleton.property;

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
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;

import com.nepxion.skeleton.constant.SkeletonConstant;

public class SkeletonProperties extends PropertiesConfiguration {
    public SkeletonProperties() throws ConfigurationException {
        this(SkeletonConstant.SKELETON_DATA_FILE);
    }

    public SkeletonProperties(String path) throws ConfigurationException {
        super(path);
    }

    public SkeletonProperties(File file) throws ConfigurationException {
        super(file);
    }

    public SkeletonProperties(URL url) throws ConfigurationException {
        super(url);
    }

    public SkeletonProperties(String path, String encoding) throws IOException, ConfigurationException {
        this(new StringBuilder(new SkeletonContent(path, encoding).getContent()), encoding);
    }

    public SkeletonProperties(StringBuilder stringBuilder, String encoding) throws IOException, ConfigurationException {
        InputStream inputStream = null;
        try {
            inputStream = IOUtils.toInputStream(stringBuilder.toString(), encoding);
            load(inputStream, encoding);
        } finally {
            if (inputStream != null) {
                IOUtils.closeQuietly(inputStream);
            }
        }
    }
}
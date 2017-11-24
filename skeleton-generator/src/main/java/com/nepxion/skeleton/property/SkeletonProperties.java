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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;

import com.nepxion.skeleton.constant.SkeletonConstant;

public class SkeletonProperties extends PropertiesConfiguration {
    public SkeletonProperties(String path) throws ConfigurationException {
        super(path);
    }

    public SkeletonProperties(File file) throws ConfigurationException {
        super(file);
    }

    public SkeletonProperties(URL url) throws ConfigurationException {
        super(url);
    }

    public SkeletonProperties(String content, String encoding) throws IOException, ConfigurationException {
        InputStream inputStream = null;
        try {
            inputStream = IOUtils.toInputStream(content, encoding);
            load(inputStream, encoding);
        } finally {
            if (inputStream != null) {
                IOUtils.closeQuietly(inputStream);
            }
        }
    }

    public SkeletonProperties(StringBuilder stringBuilder) throws IOException, ConfigurationException {
        this(stringBuilder.toString(), SkeletonConstant.ENCODING_UTF_8);
    }

    public Map<String, String> convertMap() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (Iterator<String> iterator = getKeys(); iterator.hasNext();) {
            String key = iterator.next();
            String value = getString(key);
            // 排除Properties文件上的注释
            if (!key.startsWith("﻿#")) {
                map.put(key, value);
            }
        }

        return map;
    }
}
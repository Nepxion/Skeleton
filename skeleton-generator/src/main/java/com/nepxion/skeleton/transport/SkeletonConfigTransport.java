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

import java.io.IOException;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nepxion.skeleton.constant.SkeletonConstant;
import com.nepxion.skeleton.entity.SkeletonGroup;
import com.nepxion.skeleton.exception.SkeletonException;
import com.nepxion.skeleton.parser.SkeletonXmlParser;
import com.nepxion.skeleton.property.SkeletonProperties;
import com.nepxion.skeleton.util.SkeletonUtil;

public class SkeletonConfigTransport {
    private static final Logger LOG = LoggerFactory.getLogger(SkeletonConfigTransport.class);

    private static final String SKELETON_DATA_FILE = "config/skeleton-data.properties";
    private static final String SKELETON_DESCRIPTION_FILE = "config/skeleton-description.xml";

    private SkeletonProperties skeletonDataProperties;
    private SkeletonXmlParser skeletonXmlParser;

    public SkeletonConfigTransport() {
        try {
            skeletonDataProperties = new SkeletonProperties(SKELETON_DATA_FILE, SkeletonConstant.ENCODING_UTF_8);
        } catch (ConfigurationException e) {
            LOG.error("Parse properties failed", e);
            e.printStackTrace();
        } catch (IOException e) {
            LOG.error("Parse properties failed", e);
            e.printStackTrace();
        }

        try {
            skeletonXmlParser = new SkeletonXmlParser(skeletonDataProperties);
            skeletonXmlParser.parsePath(SKELETON_DESCRIPTION_FILE, SkeletonConstant.ENCODING_UTF_8);
        } catch (IOException e) {
            LOG.error("Parse xml failed", e);
            e.printStackTrace();
        } catch (DocumentException e) {
            LOG.error("Parse xml failed", e);
            e.printStackTrace();
        }
    }

    public SkeletonProperties getProperties(String config) {
        if (StringUtils.isEmpty(config)) {
            throw new SkeletonException("Config content is null or empty");
        }

        try {
            return new SkeletonProperties(new StringBuilder(config), SkeletonConstant.ENCODING_UTF_8);
        } catch (Exception e) {
            throw new SkeletonException(e.getMessage(), e);
        }
    }

    public String getCanonicalFileName(String directoryName, SkeletonProperties skeletonProperties) {
        return SkeletonUtil.getCanonicalFileName(directoryName, skeletonProperties);
    }

    public List<SkeletonGroup> getMetaData() {
        return skeletonXmlParser.getSkeletonGroups();
    }
}
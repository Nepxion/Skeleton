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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nepxion.skeleton.constant.SkeletonConstant;
import com.nepxion.skeleton.entity.SkeletonEntity;
import com.nepxion.skeleton.entity.SkeletonGroup;
import com.nepxion.skeleton.property.SkeletonProperties;

public class SkeletonConfigTransport {
    private static final Logger LOG = LoggerFactory.getLogger(SkeletonConfigTransport.class);

    private static final String SKELETON_DATA_FILE = "config/skeleton-data.properties";
    private static final String SKELETON_DESCRIPTION_FILE = "config/skeleton-description.properties";
    private static final String SKELETON_ITEM_LIST_FILE = "config/skeleton-item-list.properties";

    private SkeletonProperties skeletonDataProperties;
    private SkeletonProperties skeletonDescriptionProperties;
    private SkeletonProperties skeletonItemListProperties;

    public SkeletonConfigTransport() {
        try {
            skeletonDataProperties = new SkeletonProperties(SKELETON_DATA_FILE, SkeletonConstant.ENCODING_UTF_8);
            skeletonDescriptionProperties = new SkeletonProperties(SKELETON_DESCRIPTION_FILE, SkeletonConstant.ENCODING_UTF_8);
            skeletonItemListProperties = new SkeletonProperties(SKELETON_ITEM_LIST_FILE, SkeletonConstant.ENCODING_UTF_8);
        } catch (ConfigurationException e) {
            LOG.error("Parse properties failed", e);
        } catch (IOException e) {
            LOG.error("Parse properties failed", e);
        }
    }

    public Map<String, SkeletonGroup> getMetaData() {
        Map<String, SkeletonGroup> metaData = new LinkedHashMap<String, SkeletonGroup>();
        SkeletonGroup skeletonGroup = null;
        for (Iterator<String> descriptionIterator = skeletonDescriptionProperties.getKeys(); descriptionIterator.hasNext();) {
            String key = descriptionIterator.next();
            String label = skeletonDescriptionProperties.getString(key);

            if (!key.startsWith("﻿#")) {
                if (key.indexOf(SkeletonGroup.GROUP_PREFIX) > -1) {
                    String groupKey = key.substring(SkeletonGroup.GROUP_PREFIX.length(), key.length());
                    skeletonGroup = new SkeletonGroup();
                    skeletonGroup.setKey(groupKey);
                    skeletonGroup.setLabel(label);
                    skeletonGroup.setEntityList(new ArrayList<SkeletonEntity>());

                    metaData.put(groupKey, skeletonGroup);
                } else {
                    if (skeletonGroup == null) {
                        throw new IllegalArgumentException("No skeleton group has been initialized");
                    }
                    String value = skeletonDataProperties.getString(key);
                    SkeletonEntity skeletonEntity = new SkeletonEntity();
                    if (label.indexOf(SkeletonEntity.HIGHLIGHT_PREFIX) > -1) {
                        skeletonEntity.setHighlightable(true);
                        label = label.replace(SkeletonEntity.HIGHLIGHT_PREFIX, "");
                    }
                    if (label.indexOf(SkeletonEntity.DEFAULT_PREFIX) > -1) {
                        skeletonEntity.setDefaultable(true);
                        label = label.replace(SkeletonEntity.DEFAULT_PREFIX, "");
                    }
                    if (label.indexOf(SkeletonEntity.CAN_EMPTY_PREFIX) > -1) {
                        skeletonEntity.setEmptiable(true);
                        label = label.replace(SkeletonEntity.CAN_EMPTY_PREFIX, "");
                    }
                    if (label.indexOf(SkeletonEntity.NOT_EDITABLE_PREFIX) > -1) {
                        skeletonEntity.setEditable(false);
                        label = label.replace(SkeletonEntity.NOT_EDITABLE_PREFIX, "");
                    }
                    skeletonEntity.setKey(key);
                    skeletonEntity.setLabel(label);
                    skeletonEntity.setValue(value);
                    String data = skeletonItemListProperties.getString(key);
                    if (StringUtils.isNotEmpty(data)) {
                        skeletonEntity.setOptions(data.split(";"));
                    }
                    skeletonGroup.getEntityList().add(skeletonEntity);
                }
            }
        }

        return metaData;
    }
}
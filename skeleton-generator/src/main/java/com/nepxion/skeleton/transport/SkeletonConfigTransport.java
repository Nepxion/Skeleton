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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.nepxion.skeleton.entity.SkeletonEntity;
import com.nepxion.skeleton.entity.SkeletonGroup;
import com.nepxion.skeleton.exception.SkeletonException;
import com.nepxion.skeleton.handler.SkeletonHandler;
import com.nepxion.skeleton.property.SkeletonProperties;

public class SkeletonConfigTransport {
    private SkeletonProperties skeletonDataProperties;
    private SkeletonProperties skeletonDescriptionProperties;
    private SkeletonProperties skeletonItemListProperties;

    public SkeletonConfigTransport() {
        skeletonDataProperties = SkeletonHandler.getSkeletonDataProperties();
        skeletonDescriptionProperties = SkeletonHandler.getSkeletonDescriptionProperties();
        skeletonItemListProperties = SkeletonHandler.getSkeletonItemListProperties();
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
                        throw new SkeletonException("No skeleton group has been initialized");
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
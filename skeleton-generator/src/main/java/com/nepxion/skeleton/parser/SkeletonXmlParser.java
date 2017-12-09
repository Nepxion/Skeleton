package com.nepxion.skeleton.parser;

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
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nepxion.skeleton.constant.SkeletonConstant;
import com.nepxion.skeleton.entity.SkeletonEntity;
import com.nepxion.skeleton.entity.SkeletonEntityType;
import com.nepxion.skeleton.entity.SkeletonGroup;
import com.nepxion.skeleton.property.SkeletonProperties;
import com.nepxion.skeleton.xml.Dom4JParser;

public class SkeletonXmlParser extends Dom4JParser {
    private static final Logger LOG = LoggerFactory.getLogger(SkeletonXmlParser.class);

    private List<SkeletonGroup> skeletonGroups;
    private SkeletonProperties skeletonDataProperties;

    public SkeletonXmlParser(SkeletonProperties skeletonDataProperties) {
        this.skeletonDataProperties = skeletonDataProperties;
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected void parseRoot(Element element) {
        LOG.info("Start to parse xml...");

        skeletonGroups = new ArrayList<SkeletonGroup>();

        for (Iterator elementIterator = element.elementIterator(); elementIterator.hasNext();) {
            Object childElementObject = elementIterator.next();
            if (childElementObject instanceof Element) {
                Element childElement = (Element) childElementObject;

                if (StringUtils.equals(childElement.getName(), SkeletonConstant.GROUP)) {
                    SkeletonGroup skeletonGroup = new SkeletonGroup();

                    parseGroup(childElement, skeletonGroup);

                    skeletonGroups.add(skeletonGroup);
                }
            }
        }

        LOG.info("Skeleton groups={}", skeletonGroups);
    }

    @SuppressWarnings("rawtypes")
    private void parseGroup(Element element, SkeletonGroup skeletonGroup) {
        List<SkeletonEntity> skeletonEntities = new ArrayList<SkeletonEntity>();
        skeletonGroup.setEntityList(skeletonEntities);
        for (Iterator elementIterator = element.elementIterator(); elementIterator.hasNext();) {
            Object childElementObject = elementIterator.next();
            if (childElementObject instanceof Element) {
                Element childElement = (Element) childElementObject;
                String text = childElement.getTextTrim();

                if (StringUtils.equals(childElement.getName(), SkeletonConstant.KEY)) {
                    skeletonGroup.setKey(text);
                } else if (StringUtils.equals(childElement.getName(), SkeletonConstant.LABEL)) {
                    skeletonGroup.setLabel(text);
                } else if (StringUtils.equals(childElement.getName(), SkeletonConstant.DESCRIPTION)) {
                    skeletonGroup.setDescription(text);
                } else if (StringUtils.equals(childElement.getName(), SkeletonConstant.ENTITY)) {
                    SkeletonEntity skeletonEntity = new SkeletonEntity();

                    parseEntity(childElement, skeletonEntity);

                    skeletonEntities.add(skeletonEntity);
                }
            }
        }
    }

    @SuppressWarnings("rawtypes")
    private void parseEntity(Element element, SkeletonEntity skeletonEntity) {
        for (Iterator elementIterator = element.elementIterator(); elementIterator.hasNext();) {
            Object childElementObject = elementIterator.next();
            if (childElementObject instanceof Element) {
                Element childElement = (Element) childElementObject;
                String text = childElement.getTextTrim();

                if (StringUtils.equals(childElement.getName(), SkeletonConstant.KEY)) {
                    skeletonEntity.setKey(text);
                    skeletonEntity.setValue(skeletonDataProperties.getString(text));
                } else if (StringUtils.equals(childElement.getName(), SkeletonConstant.LABEL)) {
                    skeletonEntity.setLabel(text);
                } else if (StringUtils.equals(childElement.getName(), SkeletonConstant.DESCRIPTION)) {
                    skeletonEntity.setDescription(text);
                } else if (StringUtils.equals(childElement.getName(), SkeletonConstant.TYPE)) {
                    skeletonEntity.setType(SkeletonEntityType.fromString(text));
                } else if (StringUtils.equals(childElement.getName(), SkeletonConstant.OPTIONS)) {
                    skeletonEntity.setOptions(text.split(";"));
                } else if (StringUtils.equals(childElement.getName(), SkeletonConstant.HIGHLIGHTABLE)) {
                    skeletonEntity.setHighlightable(Boolean.parseBoolean(text));
                } else if (StringUtils.equals(childElement.getName(), SkeletonConstant.DEFAULTABLE)) {
                    skeletonEntity.setDefaultable(Boolean.parseBoolean(text));
                } else if (StringUtils.equals(childElement.getName(), SkeletonConstant.EMPTIABLE)) {
                    skeletonEntity.setEmptiable(Boolean.parseBoolean(text));
                } else if (StringUtils.equals(childElement.getName(), SkeletonConstant.EDITABLE)) {
                    skeletonEntity.setEditable(Boolean.parseBoolean(text));
                }
            }
        }
    }

    public List<SkeletonGroup> getSkeletonGroups() {
        return skeletonGroups;
    }
}
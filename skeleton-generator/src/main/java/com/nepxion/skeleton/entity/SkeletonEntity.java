package com.nepxion.skeleton.entity;

/**
 * <p>Title: Nepxion Skeleton Generator</p>
 * <p>Description: Nepxion Skeleton Generator For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SkeletonEntity implements Serializable {
    private static final long serialVersionUID = 4973597750877880158L;

    // 标识为高亮项
    public static final String HIGHLIGHT_PREFIX = "<*>";

    // 标识为默认项
    public static final String DEFAULT_PREFIX = "<#>";

    // 标识为留空项
    public static final String CAN_EMPTY_PREFIX = "<^>";

    // 标识为不可编辑项
    public static final String NOT_EDITABLE_PREFIX = "<$>";

    private String key;
    private String label;
    private String value;
    private SkeletonEntityType type = SkeletonEntityType.TEXTFIELD;
    private String[] options;
    private boolean highlightable = false;
    private boolean defaultable = false;
    private boolean emptiable = false;
    private boolean editable = true;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        if (StringUtils.equalsIgnoreCase(value, "true") || StringUtils.equalsIgnoreCase(value, "false")) {
            this.type = SkeletonEntityType.CHECKBOX;
        }
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
        this.type = SkeletonEntityType.COMBOBOX;
    }

    public SkeletonEntityType getType() {
        return type;
    }

    public boolean isHighlightable() {
        return highlightable;
    }

    public void setHighlightable(boolean highlightable) {
        this.highlightable = highlightable;
    }

    public boolean isDefaultable() {
        return defaultable;
    }

    public void setDefaultable(boolean defaultable) {
        this.defaultable = defaultable;
    }

    public boolean isEmptiable() {
        return emptiable;
    }

    public void setEmptiable(boolean emptiable) {
        this.emptiable = emptiable;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object object) {
        return EqualsBuilder.reflectionEquals(this, object);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
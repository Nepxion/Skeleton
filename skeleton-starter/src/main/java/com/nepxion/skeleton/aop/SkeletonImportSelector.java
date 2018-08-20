package com.nepxion.skeleton.aop;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import com.nepxion.matrix.selector.AbstractImportSelector;
import com.nepxion.matrix.selector.RelaxedPropertyResolver;
import com.nepxion.skeleton.annotation.EnableSkeleton;
import com.nepxion.skeleton.engine.constant.SkeletonConstant;

@Order(Ordered.LOWEST_PRECEDENCE - 100)
public class SkeletonImportSelector extends AbstractImportSelector<EnableSkeleton> {
    @Override
    protected boolean isEnabled() {
        return new RelaxedPropertyResolver(getEnvironment()).getProperty(SkeletonConstant.SKELETON_ENABLED, Boolean.class, Boolean.TRUE);
    }
}
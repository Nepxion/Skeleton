package com.nepxion.skeleton.framework.configuration;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.nepxion.skeleton.framework.aop.SkeletonBeanPostProcessor;
import com.nepxion.skeleton.framework.controller.SkeletonController;

@Configuration
// @ComponentScan(basePackages = { "com.nepxion.skeleton.framework.controller" })
@Import(SwaggerConfiguration.class)
public class SkeletonConfiguration {
    @Bean
    public SkeletonController skeletonController() {
        return new SkeletonController();
    }

    @Bean
    public SkeletonBeanPostProcessor skeletonBeanPostProcessor() {
        return new SkeletonBeanPostProcessor();
    }
}
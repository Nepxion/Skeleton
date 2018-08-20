package com.nepxion.skeleton.plugin.springcloud.configuration;

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

import com.nepxion.skeleton.framework.service.SkeletonService;
import com.nepxion.skeleton.plugin.springcloud.impl.SpringcloudServiceImpl;

@Configuration
public class SpringCloudPluginConfiguration {
    @Bean
    public SkeletonService springCloudService() {
        return new SpringcloudServiceImpl();
    }
}
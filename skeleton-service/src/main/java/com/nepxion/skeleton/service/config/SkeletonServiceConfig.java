package com.nepxion.skeleton.service.config;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.nepxion.skeleton.engine.service.SkeletonService;
import com.nepxion.skeleton.plugin.springcloud.impl.SpringcloudServiceImpl;

@Configuration
@Import({ com.nepxion.skeleton.framework.config.SkeletonWebConfig.class })
public class SkeletonServiceConfig {
    @Bean
    public Map<String, SkeletonService> skeletonServiceMap() {
        Map<String, SkeletonService> map = new LinkedHashMap<String, SkeletonService>();
        map.put("springcloud", new SpringcloudServiceImpl());

        return map;
    }
}
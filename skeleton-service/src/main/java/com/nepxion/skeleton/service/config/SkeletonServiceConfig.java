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

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ com.nepxion.skeleton.framework.config.SkeletonWebConfig.class })
@ComponentScan(basePackages = { "com.nepxion.skeleton" })
public class SkeletonServiceConfig {

}
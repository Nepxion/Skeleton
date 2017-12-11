package com.nepxion.skeleton.generator;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import org.springframework.stereotype.Component;

import com.nepxion.skeleton.generator.server.PomXmlGenerator;
import com.nepxion.skeleton.generator.server.java.ServerApplicationClassGenerator;
import com.nepxion.skeleton.generator.server.resources.ApplicationPropertiesGenerator;
import com.nepxion.skeleton.property.SkeletonProperties;
import com.nepxion.skeleton.service.SkeletonService;

@Component("skeletonServiceImpl")
public class SkeletonServiceImpl implements SkeletonService {
    @Override
    public void generator(String path, SkeletonProperties skeletonProperties) throws Exception {
        // 创建Java类文件
        // 模板文件ServerApplication.java.template必须和ServerApplicationClassGenerator.java放在同一个目录下，下同
        new ServerApplicationClassGenerator(path, "server", skeletonProperties).generate();

        // 创建文件到resources目录下
        new ApplicationPropertiesGenerator(path, "server", skeletonProperties).generate();

        // 创建文件到目录下
        new PomXmlGenerator(path, "server", skeletonProperties).generate();
    }
}
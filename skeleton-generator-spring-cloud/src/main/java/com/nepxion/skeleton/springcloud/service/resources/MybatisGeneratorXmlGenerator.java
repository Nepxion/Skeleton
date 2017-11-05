package com.nepxion.skeleton.springcloud.service.resources;

/**
 * <p>Title: Nepxion Skeleton Generator Spring Cloud</p>
 * <p>Description: Nepxion Skeleton Generator For Spring Cloud</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.io.IOException;

import com.nepxion.skeleton.config.SkeletonConfig;
import com.nepxion.skeleton.constant.SkeletonConstant;
import com.nepxion.skeleton.context.SkeletonContext;
import com.nepxion.skeleton.generator.SkeletonFileGenerator;
import com.nepxion.skeleton.property.SkeletonProperties;

import freemarker.template.Template;

public class MybatisGeneratorXmlGenerator extends SkeletonFileGenerator {
    public MybatisGeneratorXmlGenerator(String generatePath, SkeletonProperties skeletonProperties, SkeletonContext skeletonContext) {
        super(generatePath, skeletonProperties, skeletonContext);
    }

    @Override
    protected String getFileName() {
        return "mybatis-generator.xml";
    }

    @Override
    protected String getOutputPath() {
        return super.getOutputPath() + SkeletonConstant.MAIN_RESOURCES_FILE_PATH;
    }

    @Override
    protected Template getTemplate() throws IOException {
        SkeletonConfig skeletonConfig = skeletonContext.getResourcesConfig();

        return skeletonConfig.getTemplate("mybatis-generator.xml.template");
    }

    @Override
    protected Object getDataModel() {
        return null;
    }
}
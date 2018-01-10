package com.nepxion.skeleton.plugin.springcloud.generator.shared.resources;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017-2020</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.util.HashMap;
import java.util.Map;

import com.nepxion.skeleton.engine.constant.SkeletonConstant;
import com.nepxion.skeleton.engine.context.SkeletonContext;
import com.nepxion.skeleton.engine.generator.SkeletonFileGenerator;
import com.nepxion.skeleton.engine.property.SkeletonProperties;

public class LogbackXmlGenerator extends SkeletonFileGenerator {
    public LogbackXmlGenerator(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties, String projectType) {
        super(skeletonContext.clone(projectType, LogbackXmlGenerator.class), skeletonProperties);
    }

    @Override
    protected String getFileName() {
        return "logback.xml";
    }

    @Override
    protected String getTemplateName() {
        return "logback.xml.template";
    }

    @Override
    protected String getOutputPath() {
        return super.getOutputPath() + SkeletonConstant.MAIN_RESOURCES_FILE_PATH;
    }

    @Override
    protected Object getDataModel() {
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put("serviceName", skeletonProperties.getString("serviceName") + "-" + getSkeletonContext().getProjectType());

        return dataModel;
    }
}
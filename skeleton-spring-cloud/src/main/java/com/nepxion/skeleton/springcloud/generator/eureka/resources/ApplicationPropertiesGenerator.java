package com.nepxion.skeleton.springcloud.generator.eureka.resources;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.util.HashMap;
import java.util.Map;

import com.nepxion.skeleton.engine.constant.SkeletonConstant;
import com.nepxion.skeleton.engine.generator.SkeletonFileGenerator;
import com.nepxion.skeleton.engine.property.SkeletonProperties;

public class ApplicationPropertiesGenerator extends SkeletonFileGenerator {
    public ApplicationPropertiesGenerator(String generatePath, String projectType, String prefixTemplateDirectory, String reducedTemplateDirectory, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, prefixTemplateDirectory, reducedTemplateDirectory, ApplicationPropertiesGenerator.class, skeletonProperties);
    }

    @Override
    protected String getFileName() {
        return "application.properties";
    }

    @Override
    protected String getTemplateName() {
        return "application.properties.template";
    }

    @Override
    protected String getOutputPath() {
        return super.getOutputPath() + SkeletonConstant.MAIN_RESOURCES_FILE_PATH;
    }

    @Override
    protected Object getDataModel() {
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put("serviceName", skeletonProperties.getString("serviceName") + "-eureka");
        dataModel.put("port", skeletonProperties.getString("eurekaPort"));
        dataModel.put("eurekaUrl", skeletonProperties.getString("eurekaUrl"));

        return dataModel;
    }
}
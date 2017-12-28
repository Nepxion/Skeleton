package com.nepxion.skeleton.springcloud.generator.server.java;

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
import com.nepxion.skeleton.engine.generator.SkeletonJavaGenerator;
import com.nepxion.skeleton.engine.property.SkeletonProperties;

public class TestServerApplicationClassGenerator extends SkeletonJavaGenerator {
    public TestServerApplicationClassGenerator(String generatePath, String projectType, String prefixTemplateDirectory, String reducedTemplateDirectory, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, prefixTemplateDirectory, reducedTemplateDirectory, TestServerApplicationClassGenerator.class, skeletonProperties);
    }

    @Override
    protected String getClassName() {
        return "TestServerApplication";
    }

    @Override
    protected String getTemplateName() {
        return "TestServerApplication.java.template";
    }

    @Override
    protected boolean isMainCode() {
        return false;
    }

    @Override
    protected Object getDataModel() {
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put(SkeletonConstant.PACKAGE, getPackage());
        dataModel.put("eurekaEnabled", skeletonProperties.getString("eurekaEnabled"));

        return dataModel;
    }
}
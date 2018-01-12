package com.nepxion.skeleton.plugin.springcloud.generator.client;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.util.HashMap;
import java.util.Map;

import com.nepxion.skeleton.engine.context.SkeletonContext;
import com.nepxion.skeleton.engine.generator.SkeletonFileGenerator;
import com.nepxion.skeleton.engine.property.SkeletonProperties;
import com.nepxion.skeleton.engine.util.SkeletonUtil;
import com.nepxion.skeleton.engine.util.StringUtil;

public class PomXmlGenerator extends SkeletonFileGenerator {
    public PomXmlGenerator(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) {
        super(skeletonContext.clone("client", PomXmlGenerator.class), skeletonProperties);
    }

    @Override
    protected String getFileName() {
        return "pom.xml";
    }

    @Override
    protected String getTemplateName() {
        return "pom.xml.template";
    }

    @Override
    protected Object getDataModel() {
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put("parentPomArtifactId", skeletonProperties.getString("pomArtifactId"));
        dataModel.put("pomGroupId", skeletonProperties.getString("pomGroupId"));
        dataModel.put("pomArtifactId", skeletonProperties.getString("pomArtifactId") + "-" + getSkeletonContext().getProjectType());
        dataModel.put("pomName", skeletonProperties.getString("pomName") + " " + StringUtil.firstLetterToUpper(getSkeletonContext().getProjectType()));
        dataModel.put("pomVersion", skeletonProperties.getString("pomVersion"));
        dataModel.put("javaImageVersion", skeletonProperties.getString("javaImageVersion"));
        dataModel.put("mainClass", SkeletonUtil.getBasePackagePath(getSkeletonContext().getProjectType(), skeletonProperties) + "." + StringUtil.firstLetterToUpper(getSkeletonContext().getProjectType()) + "Application");
        dataModel.put("clientHystrixEnabled", skeletonProperties.getString("clientHystrixEnabled"));

        return dataModel;
    }
}
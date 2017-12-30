package com.nepxion.skeleton.springcloud.generator;

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

import com.nepxion.skeleton.engine.generator.SkeletonFileGenerator;
import com.nepxion.skeleton.engine.property.SkeletonProperties;
import com.nepxion.skeleton.engine.util.SkeletonUtil;

public class InstallDockerShGenerator extends SkeletonFileGenerator {
    private String subProjectType;

    public InstallDockerShGenerator(String generatePath, String projectType, String subProjectType, String prefixTemplateDirectory, String reducedTemplateDirectory, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, prefixTemplateDirectory, reducedTemplateDirectory, InstallDockerShGenerator.class, skeletonProperties);

        this.subProjectType = subProjectType;
    }

    @Override
    protected String getFileName() {
        return "install-docker.sh";
    }

    @Override
    protected String getTemplateName() {
        return "install-docker.sh.template";
    }

    @Override
    protected Object getDataModel() {
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put("projectName", SkeletonUtil.getBaseDirectoryName(subProjectType, skeletonProperties));
        dataModel.put("dockerHost", skeletonProperties.getString("dockerHost"));
        dataModel.put("dockerCertPath", skeletonProperties.getString("dockerCertPath"));
        dataModel.put("dockerCertEnabled", skeletonProperties.getString("dockerCertEnabled"));
        dataModel.put("imageName", skeletonProperties.getString("serviceName") + "-" + getSkeletonContext().getProjectType());
        dataModel.put("port", skeletonProperties.getString("serverPort"));

        return dataModel;
    }
}
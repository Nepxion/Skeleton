package com.nepxion.skeleton.service.generator;

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

import org.apache.commons.lang3.StringUtils;

import com.nepxion.skeleton.engine.context.SkeletonContext;
import com.nepxion.skeleton.engine.generator.SkeletonFileGenerator;
import com.nepxion.skeleton.engine.property.SkeletonProperties;
import com.nepxion.skeleton.engine.util.SkeletonUtil;

public class InstallDockerShellGenerator extends SkeletonFileGenerator {
    private String subProjectType;
    private String shellType;
    private String linkDocker;

    public InstallDockerShellGenerator(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties, String subProjectType, String shellType, String linkDocker) {
        super(skeletonContext.clone(null, InstallDockerShellGenerator.class), skeletonProperties);

        this.subProjectType = subProjectType;
        this.shellType = shellType;
        this.linkDocker = linkDocker;
    }

    @Override
    protected String getFileName() {
        return "install-" + subProjectType + "-docker." + shellType;
    }

    @Override
    protected String getTemplateName() {
        return "install-docker." + shellType + ".template";
    }

    @Override
    protected Object getDataModel() {
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put("projectName", SkeletonUtil.getBaseDirectoryName(subProjectType, skeletonProperties));
        dataModel.put("dockerHost", skeletonProperties.getString("dockerHost"));
        dataModel.put("dockerCertPath", skeletonProperties.getString("dockerCertPath"));
        dataModel.put("dockerCertEnabled", skeletonProperties.getString("dockerCertEnabled"));
        dataModel.put("dockerType", skeletonProperties.getString("dockerType"));
        dataModel.put("imageName", skeletonProperties.getString("serviceName") + "-" + subProjectType);
        dataModel.put("port", skeletonProperties.getString(subProjectType + "Port"));
        dataModel.put("linkDocker", StringUtils.isNotEmpty(linkDocker) ? linkDocker : "");

        return dataModel;
    }
}
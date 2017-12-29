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

import com.nepxion.skeleton.engine.generator.SkeletonFileGenerator;
import com.nepxion.skeleton.engine.property.SkeletonProperties;

public class GitIgnoreGenerator extends SkeletonFileGenerator {
    public GitIgnoreGenerator(String generatePath, String projectType, String prefixTemplateDirectory, String reducedTemplateDirectory, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, prefixTemplateDirectory, reducedTemplateDirectory, GitIgnoreGenerator.class, skeletonProperties);
    }

    @Override
    protected String getFileName() {
        return ".gitignore";
    }

    @Override
    protected String getTemplateName() {
        return "file.gitignore.template";
    }

    @Override
    protected Object getDataModel() {
        return null;
    }
}
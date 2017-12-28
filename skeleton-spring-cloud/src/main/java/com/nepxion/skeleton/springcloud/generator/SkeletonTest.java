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

import com.nepxion.skeleton.engine.constant.SkeletonConstant;
import com.nepxion.skeleton.engine.property.SkeletonProperties;
import com.nepxion.skeleton.engine.util.SkeletonUtil;
import com.nepxion.skeleton.framework.service.SkeletonService;
import com.nepxion.skeleton.springcloud.service.SkeletonServiceImpl;

public class SkeletonTest {
    public static void main(String[] args) {
        try {
            // 创建文件的输出的路径
            // 放在操作系统的临时目录下
            String generatePath = SkeletonUtil.getTempGeneratePath();
            // String generatePath = "E:/Download/skeleton/";

            // 如果prefixTemplateDirectory和reducedTemplateDirectory同时为null，那么Generator类目录和Template目录必须完全一致
            // 模板文件所在的前置目录名
            String prefixTemplateDirectory = "template";
            // String prefixTemplateDirectory = null;

            // 模板目录缩减
            String reducedTemplateDirectory = "com/nepxion/skeleton/springcloud/generator/";
            // String reducedTemplateDirectory = null;

            // 描述规则的配置文件所在的路径
            // 配置文件含中文，stringEncoding必须为GBK，readerEncoding必须为UTF-8，文本文件编码必须为ANSI
            String propertiesPath = "config/skeleton-data.properties";

            // 构造全局配置文件对象
            SkeletonProperties skeletonProperties = new SkeletonProperties(propertiesPath, SkeletonConstant.ENCODING_GBK, SkeletonConstant.ENCODING_UTF_8);

            // 输出脚手架文件
            SkeletonService skeletonService = new SkeletonServiceImpl();
            skeletonService.generator(generatePath, prefixTemplateDirectory, reducedTemplateDirectory, skeletonProperties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package com.nepxion.skeleton;

/**
 * <p>Title: Nepxion Skeleton Generator</p>
 * <p>Description: Nepxion Skeleton Generator For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.io.File;
import java.io.IOException;

import org.apache.commons.configuration.ConfigurationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nepxion.skeleton.constant.SkeletonConstant;
import com.nepxion.skeleton.demo.server.java.MyApplicationClassGenerator;
import com.nepxion.skeleton.demo.service.resources.MybatisGeneratorXmlGenerator;
import com.nepxion.skeleton.exception.SkeletonException;
import com.nepxion.skeleton.property.SkeletonProperties;
import com.nepxion.skeleton.util.FileUtil;
import com.nepxion.skeleton.util.SkeletonUtil;
import com.nepxion.skeleton.util.ZipUtil;

import freemarker.template.TemplateException;

@RestController
public class SkeletonController {
    @Value("${skeleton.generate.path}")
    private String skeletonGeneratePath;

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public byte[] download(@RequestBody String config) {
        try {
            SkeletonProperties skeletonProperties = new SkeletonProperties(config, SkeletonConstant.ENCODING_UTF_8);

            String path = SkeletonUtil.getCanonicalPath(skeletonGeneratePath, SkeletonConstant.SPRING_CLOUD_SKELETON, skeletonProperties);

            generate(path, skeletonProperties);

            String zipFilePath = ZipUtil.zip(path, null);
            File zipFile = new File(zipFilePath);

            return FileUtil.getBytes(zipFile);
        } catch (Exception e) {
            throw new SkeletonException(e.getMessage(), e);
        } finally {
            File directory = new File(skeletonGeneratePath);

            FileUtil.forceDeleteDirectory(directory, 5);
        }
    }

    private void generate(String path, SkeletonProperties skeletonProperties) throws ConfigurationException, SkeletonException, TemplateException, IOException {
        new MyApplicationClassGenerator(path, "server", skeletonProperties).generate();
        new MybatisGeneratorXmlGenerator(path, "service", skeletonProperties).generate();
    }
}
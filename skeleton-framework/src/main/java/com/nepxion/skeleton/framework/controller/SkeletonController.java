package com.nepxion.skeleton.framework.controller;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nepxion.skeleton.engine.entity.SkeletonGroup;
import com.nepxion.skeleton.engine.exception.SkeletonException;
import com.nepxion.skeleton.engine.service.SkeletonService;
import com.nepxion.skeleton.framework.transport.SkeletonTransport;

@RestController
@Api(tags = { "脚手架接口" })
public class SkeletonController {
    @Autowired
    private Map<String, SkeletonService> skeletonServiceMap;

    private Map<String, SkeletonTransport> skeletonTransportMap;

    private List<String> skeletonPlugins;

    @PostConstruct
    private void initialize() {
        if (MapUtils.isEmpty(skeletonServiceMap)) {
            throw new SkeletonException("Skeleton service map isn't injected or empty");
        }

        skeletonTransportMap = new HashMap<String, SkeletonTransport>(skeletonServiceMap.size());
        skeletonPlugins = new ArrayList<String>();
        for (Map.Entry<String, SkeletonService> entry : skeletonServiceMap.entrySet()) {
            String skeletonName = entry.getKey();
            SkeletonService skeletonService = entry.getValue();
            SkeletonTransport skeletonTransport = new SkeletonTransport(skeletonName, skeletonService);
            skeletonTransportMap.put(skeletonName, skeletonTransport);
            skeletonPlugins.add(skeletonName);
        }
    }

    @RequestMapping(value = "/getPlugins", method = RequestMethod.GET)
    @ApiOperation(value = "获取脚手架插件列表接口", notes = "获取脚手架插件列表接口", response = List.class, httpMethod = "GET")
    public List<String> getPlugins() {
        return skeletonPlugins;
    }

    @RequestMapping(value = "/getMetaData", method = RequestMethod.GET)
    @ApiOperation(value = "获取默认元数据接口", notes = "获取默认界面驱动的元数据接口", response = List.class, httpMethod = "GET")
    public List<SkeletonGroup> getMetaData() {
        return getSkeletonTransport(null).getMetaData();
    }

    @RequestMapping(value = "/getMetaData/{skeletonName}", method = RequestMethod.GET)
    @ApiOperation(value = "获取元数据接口", notes = "根据脚手架名称，获取对应的界面驱动的元数据接口", response = List.class, httpMethod = "GET")
    public List<SkeletonGroup> getMetaData(@PathVariable(value = "skeletonName") @ApiParam(value = "脚手架名称", required = true) String skeletonName) {
        return getSkeletonTransport(skeletonName).getMetaData();
    }

    @RequestMapping(value = "/downloadBytes", method = RequestMethod.POST)
    @ApiOperation(value = "下载默认脚手架", notes = "下载默认脚手架Zip文件的接口，返回Zip文件的byte数组类型", response = byte[].class, httpMethod = "POST")
    public byte[] downloadBytes(@RequestBody @ApiParam(value = "配置文件内容，可拷贝src/main/resources/config/skeleton-data.properties的内容", required = true) String config) {
        return getSkeletonTransport(null).downloadBytes(config);
    }

    @RequestMapping(value = "/downloadBytes/{skeletonName}", method = RequestMethod.POST)
    @ApiOperation(value = "下载脚手架", notes = "根据脚手架名称，下载脚手架Zip文件的接口，返回Zip文件的byte数组类型", response = byte[].class, httpMethod = "POST")
    public byte[] downloadBytes(@PathVariable(value = "skeletonName") @ApiParam(value = "脚手架名称", required = true) String skeletonName, @RequestBody @ApiParam(value = "配置文件内容，可拷贝src/main/resources/config/skeleton-data.properties的内容", required = true) String config) {
        return getSkeletonTransport(skeletonName).downloadBytes(config);
    }

    @RequestMapping(value = "/downloadResponse", method = RequestMethod.POST)
    @ApiOperation(value = "下载默认脚手架", notes = "下载默认脚手架Zip文件的接口，返回Zip文件的ResponseEntity类型", response = ResponseEntity.class, httpMethod = "POST")
    public ResponseEntity<Resource> downloadResponse(@RequestBody @ApiParam(value = "配置文件内容，可拷贝src/main/resources/config/skeleton-data.properties的内容", required = true) String config) {
        return getSkeletonTransport(null).downloadResponse(config);
    }

    @RequestMapping(value = "/downloadResponse/{skeletonName}", method = RequestMethod.POST)
    @ApiOperation(value = "下载脚手架", notes = "根据脚手架名称，下载脚手架Zip文件的接口，返回Zip文件的ResponseEntity类型", response = ResponseEntity.class, httpMethod = "POST")
    public ResponseEntity<Resource> downloadResponse(@PathVariable(value = "skeletonName") @ApiParam(value = "脚手架名称", required = true) String skeletonName, @RequestBody @ApiParam(value = "配置文件内容，可拷贝src/main/resources/config/skeleton-data.properties的内容", required = true) String config) {
        return getSkeletonTransport(skeletonName).downloadResponse(config);
    }

    private SkeletonTransport getSkeletonTransport(String skeletonName) {
        if (MapUtils.isEmpty(skeletonTransportMap)) {
            throw new SkeletonException("Skeleton service map isn't injected or empty");
        }

        SkeletonTransport skeletonTransport = skeletonTransportMap.get(skeletonName);
        if (skeletonTransport == null) {
            if (StringUtils.isEmpty(skeletonName)) {
                throw new SkeletonException("No default configuration existed for skeleton");
            } else {
                throw new SkeletonException("No configuration existed for skeleton name=" + skeletonName);
            }
        }

        return skeletonTransport;
    }
}
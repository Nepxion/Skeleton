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

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nepxion.skeleton.engine.entity.SkeletonGroup;
import com.nepxion.skeleton.framework.service.SkeletonService;
import com.nepxion.skeleton.framework.transport.SkeletonTransport;

@RestController
@Api(tags = { "脚手架接口" })
public class SkeletonController {
    @Autowired
    private SkeletonService skeletonService;

    private SkeletonTransport skeletonTransport;

    @PostConstruct
    private void initialize() {
        skeletonTransport = new SkeletonTransport(null, skeletonService);
    }

    @RequestMapping(value = "/getMetaData", method = RequestMethod.GET)
    @ApiOperation(value = "获取元数据接口", notes = "获取根据配置文件进行界面驱动的元数据接口", response = List.class, httpMethod = "GET")
    public List<SkeletonGroup> getMetaData() {
        return skeletonTransport.getMetaData();
    }

    @RequestMapping(value = "/downloadBytes", method = RequestMethod.POST)
    @ApiOperation(value = "下载脚手架", notes = "下载脚手架Zip文件的接口，返回Zip文件的byte数组类型", response = byte[].class, httpMethod = "POST")
    public byte[] downloadBytes(@RequestBody @ApiParam(value = "配置文件内容，可拷贝src/main/resources/config/skeleton-data.properties的内容", required = true) String config) {
        return skeletonTransport.downloadBytes(config);
    }

    @RequestMapping(value = "/downloadResponse", method = RequestMethod.POST)
    @ApiOperation(value = "下载脚手架", notes = "下载脚手架Zip文件的接口，返回Zip文件的ResponseEntity类型", response = ResponseEntity.class, httpMethod = "POST")
    public ResponseEntity<Resource> downloadResponse(@RequestBody @ApiParam(value = "配置文件内容，可拷贝src/main/resources/config/skeleton-data.properties的内容", required = true) String config) {
        return skeletonTransport.downloadResponse(config);
    }
}
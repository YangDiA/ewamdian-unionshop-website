package com.ewandian.unionshop.microservice.website.websitecore.resources;

import com.ewandian.microservices.platform.common.model.entity.PageEntity;
import com.ewandian.microservices.platform.common.model.entity.Result;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.dto.PageColumnDTO;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.dto.PageColumnMapDTO;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity.PageColumn;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity.PageColumnMap;
import com.ewandian.unionshop.microservice.website.websitecore.domain.service.IPageColumnMapService;
import com.ewandian.unionshop.microservice.website.websitecore.domain.service.IPageColumnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YangDi on 2017/6/28.
 */
@RestController
@RequestMapping("/v1/pagecolumnmap")
public class PageColumnMapController {
    private static final Logger logger = LoggerFactory.getLogger(PageColumnMapController.class);

    @Autowired
    DiscoveryClient client;

    @Autowired
    private IPageColumnMapService pageColumnMapService;

    @RequestMapping(value = "/select",method = RequestMethod.POST)
    public Result select(@RequestBody PageColumnMapDTO pageColumnMapDTO, HttpServletRequest request) {
        Result result = new Result();

        try {
            PageEntity<PageColumnMap> pageEntity = pageColumnMapService.getPageEntity(pageColumnMapDTO);
            result.setStatus(Result.Status.OK);
            result.setData(pageEntity);
            return result;
        } catch (Exception ex) {
            return new Result(Result.Status.ERROR,"查找失败!");
        }
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestBody PageColumnMapDTO pageColumnMapDTO, HttpServletRequest request) {
        PageColumnMap pageColumnMap = new PageColumnMap();
        BeanUtils.copyProperties(pageColumnMapDTO, pageColumnMap);
        try {
            pageColumnMapService.insert(pageColumnMap);
            return new Result(Result.Status.OK,"新增成功!");
        } catch (Exception ex) {
            logger.error( "新增失败", ex.getMessage());
            return new Result(Result.Status.ERROR,"新增失败!");
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody PageColumnMapDTO pageColumnMapDTO, HttpServletRequest request) {
        PageColumnMap pageColumnMap = new PageColumnMap();
        BeanUtils.copyProperties(pageColumnMapDTO, pageColumnMap);
        try {
            pageColumnMapService.update(pageColumnMap);
            return new Result(Result.Status.OK,"更新成功!");
        } catch (Exception ex) {
            logger.error( "更新失败", ex.getMessage());
            return new Result(Result.Status.ERROR,"更新失败!");
        }
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(@RequestBody String [] ids) {

        for (String i:ids){

            pageColumnMapService.deleteById(i);
        }
        return new ResponseEntity<>( HttpStatus.OK);
    }





}

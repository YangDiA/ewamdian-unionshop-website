package com.ewandian.unionshop.microservice.website.websitecore.resources;

import com.ewandian.microservices.platform.common.model.entity.Result;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.dto.PageDTO;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity.Page;
import com.ewandian.unionshop.microservice.website.websitecore.domain.service.IPageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by YangDi on 2017/7/14.
 */
@RestController
@RequestMapping("/v1/page")
public class PageController {
    private static final Logger logger = LoggerFactory.getLogger(PageController.class);

    @Autowired
    DiscoveryClient client;

    @Autowired
    private IPageService pageService;

    @RequestMapping(value = "/selectAllByVersionId",method = RequestMethod.POST)
    public Result selectAllByVersionId(@RequestBody PageDTO pageDTO, HttpServletRequest request) {
        Result result = new Result();

        try {
            result.setStatus(Result.Status.OK);
            result.setData(pageService.selectAllPageAndColumn(pageDTO.getVersionId()));
            return result;
        } catch (Exception ex) {
            result.setStatus(Result.Status.ERROR);
            result.setMessage("查找失败");
        }
        return result;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestBody PageDTO pageDTO, HttpServletRequest request) {
        Page page = new Page();
        BeanUtils.copyProperties(pageDTO, page);
        try {
            pageService.insert(page);
            return new Result(Result.Status.OK,"新增成功!");
        } catch (Exception ex) {
            logger.error( "新增失败", ex.getMessage());
            return new Result(Result.Status.ERROR,"新增失败!");
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody  PageDTO pageDTO, HttpServletRequest request) {
        Page page = new Page();
        BeanUtils.copyProperties(pageDTO, page);
        try {
            pageService.update(page);
            return new Result(Result.Status.OK,"更新成功!");
        } catch (Exception ex) {
            logger.error( "更新失败", ex.getMessage());
            return new Result(Result.Status.ERROR,"更新失败!");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(@RequestBody String [] ids) {

        pageService.deletePageAndColumnByIds(ids);
        return new ResponseEntity<>( HttpStatus.OK);
    }





}

package com.ewandian.unionshop.microservice.website.websitecore.resources;

import com.ewandian.microservices.platform.common.model.entity.PageEntity;
import com.ewandian.microservices.platform.common.model.entity.Result;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.dto.PageTemplateDTO;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.dto.ThemeDTO;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity.PageTemplate;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity.Theme;
import com.ewandian.unionshop.microservice.website.websitecore.domain.service.IPageTemplateService;
import com.ewandian.unionshop.microservice.website.websitecore.domain.service.IThemeService;
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
 * Created by YangDi on 2017/6/28.
 */
@RestController
@RequestMapping("/v1/pagetemplate")
public class PageTemplateController {
    private static final Logger logger = LoggerFactory.getLogger(PageTemplateController.class);

    @Autowired
    DiscoveryClient client;

    @Autowired
    private IPageTemplateService pageTemplateService;

    @RequestMapping(value = "/selectAllByThemeId",method = RequestMethod.POST)
    public Result selectAllByThemeId(@RequestBody PageTemplateDTO pageTemplateDTO, HttpServletRequest request) {
        Result result = new Result();

        try {
            result.setStatus(Result.Status.OK);
            result.setData(pageTemplateService.selectAllTemplateAndDetail(pageTemplateDTO.getThemeId()));
            return result;
        } catch (Exception ex) {
            result.setStatus(Result.Status.ERROR);
            result.setMessage("查找失败");
        }
        return result;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestBody PageTemplateDTO pageTemplateDTO, HttpServletRequest request) {
        PageTemplate pageTemplate = new PageTemplate();
        BeanUtils.copyProperties(pageTemplateDTO, pageTemplate);
        try {
            pageTemplateService.insert(pageTemplate);
            return new Result(Result.Status.OK,"新增成功!");
        } catch (Exception ex) {
            logger.error( "新增失败", ex.getMessage());
            return new Result(Result.Status.ERROR,"新增失败!");
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody  PageTemplateDTO pageTemplateDTO, HttpServletRequest request) {
        PageTemplate pageTemplate = new PageTemplate();
        BeanUtils.copyProperties(pageTemplateDTO, pageTemplate);
        try {
            pageTemplateService.update(pageTemplate);
            return new Result(Result.Status.OK,"更新成功!");
        } catch (Exception ex) {
            logger.error( "更新失败", ex.getMessage());
            return new Result(Result.Status.ERROR,"更新失败!");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(@RequestBody String [] ids) {

        pageTemplateService.deletePageTemplateAndDetailByIds(ids);
        return new ResponseEntity<>( HttpStatus.OK);
    }





}

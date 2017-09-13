package com.ewandian.unionshop.microservice.website.websitecore.resources;

import com.ewandian.microservices.platform.common.model.entity.Result;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.dto.PageTemplateDTO;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.dto.PageTemplateDetailDTO;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity.PageTemplate;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity.PageTemplateDetail;
import com.ewandian.unionshop.microservice.website.websitecore.domain.service.IPageTemplateDetailService;
import com.ewandian.unionshop.microservice.website.websitecore.domain.service.IPageTemplateService;
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
@RequestMapping("/v1/pagetemplatedetail")
public class PageTemplateDetailController {
    private static final Logger logger = LoggerFactory.getLogger(PageTemplateDetailController.class);

    @Autowired
    DiscoveryClient client;

    @Autowired
    private IPageTemplateDetailService pageTemplateDetailService;


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestBody PageTemplateDetailDTO pageTemplateDetailDTO, HttpServletRequest request) {
        PageTemplateDetail pageTemplateDetail = new PageTemplateDetail();
        BeanUtils.copyProperties(pageTemplateDetailDTO, pageTemplateDetail);
        try {
            pageTemplateDetailService.insert(pageTemplateDetail);
            return new Result(Result.Status.OK,"新增成功!");
        } catch (Exception ex) {
            logger.error( "新增失败", ex.getMessage());
            return new Result(Result.Status.ERROR,"新增失败!");
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody  PageTemplateDetailDTO pageTemplateDetailDTO, HttpServletRequest request) {
        PageTemplateDetail pageTemplateDetail = new PageTemplateDetail();
        BeanUtils.copyProperties(pageTemplateDetailDTO, pageTemplateDetail);
        try {
            pageTemplateDetailService.update(pageTemplateDetail);
            return new Result(Result.Status.OK,"更新成功!");
        } catch (Exception ex) {
            logger.error( "更新失败", ex.getMessage());
            return new Result(Result.Status.ERROR,"更新失败!");
        }
    }

    @RequestMapping(value = "/updateList",method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody List<PageTemplateDetailDTO> pageTemplateDetailDTOList, HttpServletRequest request) {
       List<PageTemplateDetail> updateList = new ArrayList<>();
        pageTemplateDetailDTOList.forEach(pageTemplateDetailDTO->{
            PageTemplateDetail pageTemplateDetail = new PageTemplateDetail();
            BeanUtils.copyProperties(pageTemplateDetailDTO, pageTemplateDetail);
            updateList.add(pageTemplateDetail);
        });
        try {
            pageTemplateDetailService.update(updateList);
            return new Result(Result.Status.OK,"更新成功!");
        } catch (Exception ex) {
            logger.error( "更新失败", ex.getMessage());
            return new Result(Result.Status.ERROR,"更新失败!");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(@RequestBody String [] ids) {

        for (String i:ids){

            pageTemplateDetailService.deleteById(i);
        }
        return new ResponseEntity<>( HttpStatus.OK);
    }





}

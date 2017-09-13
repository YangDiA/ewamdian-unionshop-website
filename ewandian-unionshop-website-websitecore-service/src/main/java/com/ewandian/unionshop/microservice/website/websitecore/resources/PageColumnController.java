package com.ewandian.unionshop.microservice.website.websitecore.resources;

import com.ewandian.microservices.platform.common.model.entity.Result;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.dto.PageColumnDTO;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.dto.PageTemplateDetailDTO;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity.PageColumn;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity.PageTemplateDetail;
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
@RequestMapping("/v1/pagecolumn")
public class PageColumnController {
    private static final Logger logger = LoggerFactory.getLogger(PageColumnController.class);

    @Autowired
    DiscoveryClient client;

    @Autowired
    private IPageColumnService pageColumnService;

    @RequestMapping(value = "/select",method = RequestMethod.POST)
    public Result select(@RequestBody PageColumnDTO pageColumnDTO, HttpServletRequest request) {
        Result result = new Result();

        try {
            //PageEntity<PageColumn> pageEntity = pageColumnService.getPageEntity(pageColumnDTO);
            result.setStatus(Result.Status.OK);
            //result.setData(pageEntity);
            return result;
        } catch (Exception ex) {
            return new Result(Result.Status.ERROR,"查找失败!");
        }
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestBody PageColumnDTO pageColumnDTO, HttpServletRequest request) {
        PageColumn pageColumn = new PageColumn();
        BeanUtils.copyProperties(pageColumnDTO, pageColumn);
        try {
            pageColumnService.insert(pageColumn);
            return new Result(Result.Status.OK,"新增成功!");
        } catch (Exception ex) {
            logger.error( "新增失败", ex.getMessage());
            return new Result(Result.Status.ERROR,"新增失败!");
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody PageColumnDTO pageColumnDTO, HttpServletRequest request) {
        PageColumn pageColumn = new PageColumn();
        BeanUtils.copyProperties(pageColumnDTO, pageColumn);
        try {
            pageColumnService.update(pageColumn);
            return new Result(Result.Status.OK,"更新成功!");
        } catch (Exception ex) {
            logger.error( "更新失败", ex.getMessage());
            return new Result(Result.Status.ERROR,"更新失败!");
        }
    }

    @RequestMapping(value = "/updateList",method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody List<PageColumnDTO> PageColumnDTOList, HttpServletRequest request) {
        List<PageColumn> updateList = new ArrayList<>();
        PageColumnDTOList.forEach(pageColumnDTO->{
            PageColumn pageColumn = new PageColumn();
            BeanUtils.copyProperties(pageColumnDTO, pageColumn);
            updateList.add(pageColumn);
        });
        try {
            pageColumnService.update(updateList);
            return new Result(Result.Status.OK,"更新成功!");
        } catch (Exception ex) {
            logger.error( "更新失败", ex.getMessage());
            return new Result(Result.Status.ERROR,"更新失败!");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(@RequestBody String [] ids) {

        for (String i:ids){

            pageColumnService.deleteById(i);
        }
        return new ResponseEntity<>( HttpStatus.OK);
    }





}

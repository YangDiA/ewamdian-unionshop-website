package com.ewandian.unionshop.microservice.website.websitecore.resources;

import com.ewandian.microservices.platform.common.model.entity.PageEntity;
import com.ewandian.microservices.platform.common.model.entity.Result;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.dto.ThemeDTO;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.dto.WebSiteDTO;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity.Theme;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity.WebSite;
import com.ewandian.unionshop.microservice.website.websitecore.domain.service.IThemeService;
import com.ewandian.unionshop.microservice.website.websitecore.domain.service.IWebSiteService;
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
@RequestMapping("/v1/theme")
public class ThemeController {
    private static final Logger logger = LoggerFactory.getLogger(ThemeController.class);

    @Autowired
    DiscoveryClient client;

    @Autowired
    private IThemeService themeService;

    @RequestMapping(value = "/select",method = RequestMethod.POST)
    public Result select(@RequestBody ThemeDTO themeDTO, HttpServletRequest request) {
        Result result = new Result();

        try {
            PageEntity<Theme> pageEntity = themeService.getPageEntity(themeDTO);
            result.setStatus(Result.Status.OK);
            result.setData(pageEntity);
            return result;
        } catch (Exception ex) {
            return new Result(Result.Status.ERROR,"查找失败!");
        }
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestBody ThemeDTO themeDTO, HttpServletRequest request) {
        Theme theme = new Theme();
        BeanUtils.copyProperties(themeDTO, theme);
        try {
            themeService.insert(theme);
            return new Result(Result.Status.OK,"新增成功!");
        } catch (Exception ex) {
            logger.error( "新增失败", ex.getMessage());
            return new Result(Result.Status.ERROR,"新增失败!");
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody ThemeDTO themeDTO, HttpServletRequest request) {
        Theme theme = new Theme();
        BeanUtils.copyProperties(themeDTO, theme);
        try {
            themeService.update(theme);
            return new Result(Result.Status.OK,"更新成功!");
        } catch (Exception ex) {
            logger.error( "更新失败", ex.getMessage());
            return new Result(Result.Status.ERROR,"更新失败!");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(@RequestBody String [] ids) {

        for (String i:ids){

            themeService.deleteById(i);
        }
        return new ResponseEntity<>( HttpStatus.OK);
    }



    @RequestMapping(value = "/getAllList",method = RequestMethod.POST)
    public Result getAllList(@RequestBody ThemeDTO themeDTO, HttpServletRequest request) {
        Result result = new Result();

        try {
            result.setStatus(Result.Status.OK);
            result.setData(this.themeService.queryAll());
            return result;
        } catch (Exception ex) {
            return new Result(Result.Status.ERROR,"查找失败!");
        }
    }

}

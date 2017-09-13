package com.ewandian.unionshop.microservice.website.ad.resources;

import com.ewandian.microservices.platform.common.model.entity.PageEntity;
import com.ewandian.microservices.platform.common.model.entity.Result;
import com.ewandian.unionshop.microservice.website.ad.domain.dto.AdDTO;
import com.ewandian.unionshop.microservice.website.ad.domain.entity.Ad;
import com.ewandian.unionshop.microservice.website.ad.domain.service.IAdService;
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
 * Created by YangDi on 2017/7/19.
 */
@RestController
@RequestMapping("/v1/ad")
public class AdController {
    private static final Logger logger = LoggerFactory.getLogger(AdController.class);
    @Autowired
    DiscoveryClient client;

    @Autowired
    private IAdService adService;

    @RequestMapping(value = "/select",method = RequestMethod.POST)
    public Result select(@RequestBody AdDTO adDTO, HttpServletRequest request) {
        Result result = new Result();

        try {
            PageEntity<Ad> pageEntity = adService.getPageEntity(adDTO);
            result.setStatus(Result.Status.OK);
            result.setData(pageEntity);
            return result;
        } catch (Exception ex) {
            return new Result(Result.Status.ERROR,"查找失败!");
        }
    }


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestBody AdDTO adDTO, HttpServletRequest request) {
        Ad ad = new Ad();
        BeanUtils.copyProperties(adDTO, ad);
        try {
            adService.insert(ad);
            return new Result(Result.Status.OK,"新增成功!");
        } catch (Exception ex) {
            return new Result(Result.Status.ERROR,"新增失败!");
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody AdDTO adDTO, HttpServletRequest request) {
        Ad ad = new Ad();
        BeanUtils.copyProperties(adDTO, ad);
        try {

            adService.update(ad);
            return new Result(Result.Status.OK,"更新成功!");
        } catch (Exception ex) {
            return new Result(Result.Status.ERROR,"更新失败!");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(@RequestBody String [] ids) {

        for (String i:ids){

            adService.deleteById(i);
        }
        return new ResponseEntity<>( HttpStatus.OK);
    }
}

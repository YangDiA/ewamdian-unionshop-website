package com.ewandian.unionshop.microservice.website.news.resources;

import com.ewandian.microservices.platform.common.model.entity.PageEntity;
import com.ewandian.microservices.platform.common.model.entity.Result;
import com.ewandian.unionshop.microservice.website.news.domain.dto.NewsDTO;
import com.ewandian.unionshop.microservice.website.news.domain.entity.News;
import com.ewandian.unionshop.microservice.website.news.domain.service.INewsService;
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
@RequestMapping("/v1/news")
public class NewsController {
    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);
    @Autowired
    DiscoveryClient client;

    @Autowired
    private INewsService newsService;

    @RequestMapping(value = "/select",method = RequestMethod.POST)
    public Result select(@RequestBody NewsDTO newsDTO, HttpServletRequest request) {
        Result result = new Result();

        try {
            PageEntity<News> pageEntity = newsService.getPageEntity(newsDTO);
            result.setStatus(Result.Status.OK);
            result.setData(pageEntity);
            return result;
        } catch (Exception ex) {
            return new Result(Result.Status.ERROR,"查找失败!");
        }
    }


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestBody NewsDTO newsDTO, HttpServletRequest request) {
        News news = new News();
        BeanUtils.copyProperties(newsDTO, news);
        try {
            newsService.insert(news);
            return new Result(Result.Status.OK,"新增成功!");
        } catch (Exception ex) {
            return new Result(Result.Status.ERROR,"新增失败!");
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody NewsDTO newsDTO, HttpServletRequest request) {
        News news = new News();
        BeanUtils.copyProperties(newsDTO, news);
        try {

            newsService.update(news);
            return new Result(Result.Status.OK,"更新成功!");
        } catch (Exception ex) {
            return new Result(Result.Status.ERROR,"更新失败!");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(@RequestBody String [] ids) {

        for (String i:ids){

            newsService.deleteById(i);
        }
        return new ResponseEntity<>( HttpStatus.OK);
    }


}

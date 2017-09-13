package com.ewandian.unionshop.microservice.website.api.service.resources;

import com.ewandian.microservices.platform.common.ServiceHelper;
import com.ewandian.microservices.platform.common.model.entity.Result;
import com.ewandian.unionshop.microservice.utils.HttpHeaderUtils;
import com.ewandian.unionshop.microservice.utils.SequenceUtils;
import com.ewandian.unionshop.microservice.website.api.service.dto.PageColumnDTO;
import com.ewandian.unionshop.microservice.website.api.service.dto.PageTemplateDetailDTO;
import com.ewandian.unionshop.microservice.website.api.service.dto.WebSiteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by YangDi on 2017/6/29.
 */
@RestController
@RequestMapping("/v1/api/web/pagecolumn/")
public class PageColumnServiceAPI {
    private static final Logger LOG = LoggerFactory.getLogger(PageColumnServiceAPI.class);


    @Autowired
    ServiceHelper serviceHelper;


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    DiscoveryClient client;


    @RequestMapping(value = "select" ,method = RequestMethod.POST)
    public Result select(@RequestBody PageColumnDTO pageColumnDTO) {
        String url = "http://website-websitecore-service/v1/pagecolumn/select";
        LOG.debug("add crm master from URL: {}", url);
        ResponseEntity<Result> result = restTemplate.postForEntity(url, pageColumnDTO, Result.class);
        LOG.info("selectBooking http-status: {}", result.getStatusCode());
        LOG.debug("select crm master body: {}", result.getBody());
        return result.getBody();
    }

    @RequestMapping(value = "add" ,method = RequestMethod.POST)
    public Result add(@RequestBody PageColumnDTO pageColumnDTO, HttpServletRequest request) {
        String url = "http://website-websitecore-service/v1/pagecolumn/add";
        String seqNo="MODULE-"+ UUID.randomUUID().toString().toUpperCase().substring(0,8);
        pageColumnDTO.setId(seqNo);
        ResponseEntity<Result> result = restTemplate.postForEntity(url, pageColumnDTO, Result.class);
        LOG.debug("select website  body: {}", result.getBody());
        return result.getBody();
    }

    @RequestMapping(value = "update" ,method = RequestMethod.POST)
    public Result update(@RequestBody PageColumnDTO pageColumnDTO) {
        String url = "http://website-websitecore-service/v1/pagecolumn/update";
        ResponseEntity<Result> result = restTemplate.postForEntity(url, pageColumnDTO, Result.class);
        LOG.debug("select website body: {}", result.getBody());
        return result.getBody();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(@RequestBody String [] id) {
        String url = "http://website-websitecore-service/v1/pagecolumn/delete";
        restTemplate.postForEntity(url, id, Collection.class);
        Result result = new Result();
        result.setStatus(Result.Status.OK);
        return  serviceHelper.createResponse(result, HttpStatus.OK);
    }

    @RequestMapping(value = "updateList" ,method = RequestMethod.POST)
    public Result update(@RequestBody List<PageColumnDTO> pageColumnDTOList) {
        String url = "http://website-websitecore-service/v1/pagecolumn/updateList";
        ResponseEntity<Result> result = restTemplate.postForEntity(url, pageColumnDTOList, Result.class);
        LOG.debug("select website body: {}", result.getBody());
        return result.getBody();
    }
}

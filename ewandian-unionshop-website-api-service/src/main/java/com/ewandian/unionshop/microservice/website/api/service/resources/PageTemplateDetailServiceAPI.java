package com.ewandian.unionshop.microservice.website.api.service.resources;

import com.ewandian.microservices.platform.common.ServiceHelper;
import com.ewandian.microservices.platform.common.model.entity.Result;
import com.ewandian.unionshop.microservice.utils.HttpHeaderUtils;
import com.ewandian.unionshop.microservice.utils.SequenceUtils;
import com.ewandian.unionshop.microservice.website.api.service.dto.PageTemplateDTO;
import com.ewandian.unionshop.microservice.website.api.service.dto.PageTemplateDetailDTO;
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
@RequestMapping("/v1/api/web/pagetemplatedetail/")
public class PageTemplateDetailServiceAPI {
    private static final Logger LOG = LoggerFactory.getLogger(PageTemplateDetailServiceAPI.class);


    @Autowired
    ServiceHelper serviceHelper;


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    DiscoveryClient client;




    @RequestMapping(value = "add" ,method = RequestMethod.POST)
    public Result add(@RequestBody PageTemplateDetailDTO pageTemplateDetailDTO, HttpServletRequest request) {
        String url = "http://website-websitecore-service/v1/pagetemplatedetail/add";
        String seqNo="MODULE-"+ UUID.randomUUID().toString().toUpperCase().substring(0,8);
        pageTemplateDetailDTO.setId(seqNo);
        ResponseEntity<Result> result = restTemplate.postForEntity(url, pageTemplateDetailDTO, Result.class);
        LOG.debug("select website  body: {}", result.getBody());
        return result.getBody();
    }

    @RequestMapping(value = "update" ,method = RequestMethod.POST)
    public Result update(@RequestBody PageTemplateDetailDTO pageTemplateDetailDTO) {
        String url = "http://website-websitecore-service/v1/pagetemplatedetail/update";
        ResponseEntity<Result> result = restTemplate.postForEntity(url, pageTemplateDetailDTO, Result.class);
        LOG.debug("select website body: {}", result.getBody());
        return result.getBody();
    }

    @RequestMapping(value = "updateList" ,method = RequestMethod.POST)
    public Result update(@RequestBody List<PageTemplateDetailDTO> pageTemplateDetailDTOList) {
        String url = "http://website-websitecore-service/v1/pagetemplatedetail/updateList";
        ResponseEntity<Result> result = restTemplate.postForEntity(url, pageTemplateDetailDTOList, Result.class);
        LOG.debug("select website body: {}", result.getBody());
        return result.getBody();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(@RequestBody String [] id) {
        String url = "http://website-websitecore-service/v1/pagetemplatedetail/delete";
        restTemplate.postForEntity(url, id, Collection.class);
        Result result = new Result();
        result.setStatus(Result.Status.OK);
        return  serviceHelper.createResponse(result, HttpStatus.OK);
    }
}

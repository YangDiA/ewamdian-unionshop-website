package com.ewandian.unionshop.microservice.website.websitecore.domain.service.impl;

import com.ewandian.microservices.platform.common.model.entity.PageEntity;
import com.ewandian.unionshop.microservice.resources.services.common.BaseServiceImpl;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.dto.WebSiteDTO;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity.WebSite;
import com.ewandian.unionshop.microservice.website.websitecore.domain.service.IWebSiteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * Created by pc15 on 2017/6/28.
 */
@Service("webSiteService")
public class WebSiteServiceImpl extends BaseServiceImpl<WebSite> implements IWebSiteService {

    @Override
    public PageEntity<WebSite> getPageEntity(WebSiteDTO webSiteDTO) {
        int size = webSiteDTO.getSize();
        int page = webSiteDTO.getPage();
        PageEntity pageEntity = new PageEntity();
        Query query = new Query();
        Criteria criteria = new Criteria();
        if (StringUtils.isNotBlank(webSiteDTO.getSiteName())){
            query.addCriteria(criteria.where("siteName").regex(webSiteDTO.getSiteName()));
        }
        query.limit(size).skip((page-1)*size);
        pageEntity=this.findPageEntityByFilter( query, WebSite.class);
        pageEntity.setCurrentPageNumber(page);
        return pageEntity;
    }
}

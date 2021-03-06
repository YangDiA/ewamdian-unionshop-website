package com.ewandian.unionshop.microservice.website.websitecore.domain.service;

import com.ewandian.microservices.platform.common.model.entity.PageEntity;
import com.ewandian.unionshop.microservice.resources.services.common.IBaseService;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.dto.WebSiteDTO;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity.WebSite;

/**
 * Created by YangDi on 2017/6/28.
 */
public interface IWebSiteService extends IBaseService<WebSite> {
    public PageEntity<WebSite> getPageEntity(WebSiteDTO webSiteDTO);
}

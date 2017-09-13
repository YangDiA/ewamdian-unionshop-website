package com.microservice.website.websitecore.domain.service;

import com.ewandian.microservices.platform.common.model.entity.PageEntity;
import com.microservice.resources.services.common.IBaseService;
import com.microservice.website.websitecore.domain.model.dto.PageColumnMapDTO;
import com.microservice.website.websitecore.domain.model.entity.PageColumnMap;

/**
 * Created by YangDi on 2017/7/18.
 */
public interface IPageColumnMapService extends IBaseService<PageColumnMap> {
    public PageEntity<PageColumnMap> getPageEntity(PageColumnMapDTO pageColumnMapDTO);
}

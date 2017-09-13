package com.ewandian.unionshop.microservice.website.websitecore.domain.service;

import com.ewandian.microservices.platform.common.model.entity.PageEntity;
import com.ewandian.unionshop.microservice.resources.services.common.IBaseService;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.dto.PageColumnMapDTO;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity.PageColumnMap;

/**
 * Created by YangDi on 2017/7/18.
 */
public interface IPageColumnMapService extends IBaseService<PageColumnMap> {
    public PageEntity<PageColumnMap> getPageEntity(PageColumnMapDTO pageColumnMapDTO);
}

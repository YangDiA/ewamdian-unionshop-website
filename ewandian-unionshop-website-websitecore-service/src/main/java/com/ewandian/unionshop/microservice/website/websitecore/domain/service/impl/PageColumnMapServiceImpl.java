package com.ewandian.unionshop.microservice.website.websitecore.domain.service.impl;

import com.ewandian.microservices.platform.common.model.entity.PageEntity;
import com.ewandian.unionshop.microservice.resources.services.common.BaseServiceImpl;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.dto.PageColumnMapDTO;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity.PageColumnMap;
import com.ewandian.unionshop.microservice.website.websitecore.domain.service.IPageColumnMapService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * Created by YangDi on 2017/7/18.
 */
@Service("pageColumnMapService")
public class PageColumnMapServiceImpl  extends BaseServiceImpl<PageColumnMap> implements IPageColumnMapService{

    @Override
    public PageEntity<PageColumnMap> getPageEntity(PageColumnMapDTO pageColumnMapDTO) {
        int size = pageColumnMapDTO.getSize();
        int page = pageColumnMapDTO.getPage();
        PageEntity pageEntity = new PageEntity();
        Query query = new Query();
        Criteria criteria = new Criteria();
        if (StringUtils.isNotBlank(pageColumnMapDTO.getColumnId())){
            query.addCriteria(criteria.where("columnId").is(pageColumnMapDTO.getColumnId()));
        }
        if (StringUtils.isNotBlank(pageColumnMapDTO.getEntityType())){
            query.addCriteria(criteria.where("entityType").is(pageColumnMapDTO.getEntityType()));
        }
        query.limit(size).skip((page-1)*size);
        pageEntity=this.findPageEntityByFilter( query, PageColumnMap.class);
        pageEntity.setCurrentPageNumber(page);
        return pageEntity;
    }
}

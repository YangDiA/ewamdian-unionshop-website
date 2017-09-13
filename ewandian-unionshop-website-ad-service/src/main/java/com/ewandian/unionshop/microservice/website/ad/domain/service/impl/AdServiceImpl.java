package com.ewandian.unionshop.microservice.website.ad.domain.service.impl;

import com.ewandian.microservices.platform.common.model.entity.PageEntity;
import com.ewandian.unionshop.microservice.resources.services.common.BaseServiceImpl;
import com.ewandian.unionshop.microservice.website.ad.domain.dto.AdDTO;
import com.ewandian.unionshop.microservice.website.ad.domain.entity.Ad;
import com.ewandian.unionshop.microservice.website.ad.domain.service.IAdService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * Created by YangDi on 2017/7/19.
 */
@Service("adService")
public class AdServiceImpl extends BaseServiceImpl<Ad> implements IAdService {

    @Override
    public PageEntity<Ad> getPageEntity(AdDTO adDTO) {
        int size = adDTO.getSize();
        int page = adDTO.getPage();
        PageEntity pageEntity = new PageEntity();
        Query query = new Query();
        Criteria criteria = new Criteria();
        if (StringUtils.isNotBlank(adDTO.getAdName())){
            query.addCriteria(criteria.where("adName").regex(adDTO.getAdName()));
        }
        query.limit(size).skip((page-1)*size);
        pageEntity=this.findPageEntityByFilter( query, Ad.class);
        pageEntity.setCurrentPageNumber(page);
        return pageEntity;
    }
}

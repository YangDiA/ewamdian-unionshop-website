package com.ewandian.unionshop.microservice.website.ad.domain.service;

import com.ewandian.microservices.platform.common.model.entity.PageEntity;
import com.ewandian.unionshop.microservice.resources.services.common.IBaseService;
import com.ewandian.unionshop.microservice.website.ad.domain.dto.AdDTO;
import com.ewandian.unionshop.microservice.website.ad.domain.entity.Ad;

/**
 * Created by YangDi on 2017/7/19.
 */
public interface IAdService extends IBaseService<Ad> {
    PageEntity<Ad> getPageEntity(AdDTO adDTO);
}

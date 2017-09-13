package com.microservice.website.websitecore.domain.service;

import com.microservice.resources.services.common.IBaseService;
import com.microservice.website.websitecore.domain.model.entity.PageTemplateDetail;

import java.util.List;

/**
 * Created by YangDi on 2017/7/10.
 */
public interface IPageTemplateDetailService extends IBaseService<PageTemplateDetail> {

    List<PageTemplateDetail> getListByTemplateIds(List<String> templateIdList);

}

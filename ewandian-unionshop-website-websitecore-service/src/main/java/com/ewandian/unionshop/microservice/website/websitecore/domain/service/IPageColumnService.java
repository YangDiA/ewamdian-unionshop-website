package com.ewandian.unionshop.microservice.website.websitecore.domain.service;

import com.ewandian.unionshop.microservice.resources.services.common.IBaseService;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity.PageColumn;

import java.util.List;

/**
 * Created by YangDi on 2017/7/14.
 */
public interface IPageColumnService  extends IBaseService<PageColumn> {

   List<PageColumn> getListByPageIds(List<String> pageIdList);
}

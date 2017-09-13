package com.microservice.website.websitecore.domain.service;

import com.microservice.resources.services.common.IBaseService;
import com.microservice.website.websitecore.domain.model.entity.PageColumn;

import java.util.List;

/**
 * Created by YangDi on 2017/7/14.
 */
public interface IPageColumnService  extends IBaseService<PageColumn> {

   List<PageColumn> getListByPageIds(List<String> pageIdList);
}

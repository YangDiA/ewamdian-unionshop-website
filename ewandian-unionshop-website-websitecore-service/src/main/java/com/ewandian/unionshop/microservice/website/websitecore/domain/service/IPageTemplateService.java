package com.ewandian.unionshop.microservice.website.websitecore.domain.service;

import com.ewandian.unionshop.microservice.resources.services.common.IBaseService;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity.PageTemplate;

/**
 * Created by YangDi on 2017/7/5.
 */
public interface IPageTemplateService  extends IBaseService<PageTemplate> {

    /**
     * 根据主题版本号 查找页面和页面栏目信息
     * @return
     */
    Object selectAllTemplateAndDetail(String themeId);

    int deletePageTemplateAndDetailByIds(String [] ids);

}

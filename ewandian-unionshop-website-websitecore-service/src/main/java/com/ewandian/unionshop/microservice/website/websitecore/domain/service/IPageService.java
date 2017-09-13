package com.ewandian.unionshop.microservice.website.websitecore.domain.service;

import com.ewandian.unionshop.microservice.resources.services.common.IBaseService;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity.Page;

/**
 * Created by YangDi on 2017/7/14.
 */
public interface IPageService extends IBaseService<Page> {
    /**
     * 根据主题版本号 查找页面和页面栏目信息
     * @return
     */
    Object selectAllPageAndColumn(String versionId);

    int deletePageAndColumnByIds(String [] ids);
}

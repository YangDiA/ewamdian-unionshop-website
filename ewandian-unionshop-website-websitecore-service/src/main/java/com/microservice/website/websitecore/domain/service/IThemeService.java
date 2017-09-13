package com.microservice.website.websitecore.domain.service;

import com.ewandian.microservices.platform.common.model.entity.PageEntity;
import com.microservice.resources.services.common.IBaseService;
import com.microservice.website.websitecore.domain.model.dto.ThemeDTO;
import com.microservice.website.websitecore.domain.model.entity.Theme;

/**
 * Created by YangDi on 2017/7/4.
 */
public interface IThemeService extends IBaseService<Theme> {
    public PageEntity<Theme> getPageEntity(ThemeDTO themeDTO);
}

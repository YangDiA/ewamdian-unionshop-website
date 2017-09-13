package com.microservice.website.websitecore.domain.service;

import com.ewandian.microservices.platform.common.model.entity.PageEntity;
import com.microservice.resources.services.common.IBaseService;
import com.microservice.website.websitecore.domain.model.dto.ThemeVersionDTO;
import com.microservice.website.websitecore.domain.model.entity.ThemeVersion;

/**
 * Created by YangDi on 2017/7/13.
 */
public interface IThemeVersionService  extends IBaseService<ThemeVersion> {
    ThemeVersion saveThemeVersion(ThemeVersionDTO themeVersionDTO);

    PageEntity<ThemeVersion> getPageEntity(ThemeVersionDTO themeVersionDTO);
}

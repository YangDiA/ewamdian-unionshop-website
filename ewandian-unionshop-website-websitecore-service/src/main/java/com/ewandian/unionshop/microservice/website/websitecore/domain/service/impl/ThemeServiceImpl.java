package com.ewandian.unionshop.microservice.website.websitecore.domain.service.impl;

import com.ewandian.microservices.platform.common.model.entity.PageEntity;
import com.ewandian.unionshop.microservice.resources.services.common.BaseServiceImpl;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.dto.ThemeDTO;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity.Theme;
import com.ewandian.unionshop.microservice.website.websitecore.domain.service.IThemeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * Created by pc15 on 2017/7/4.
 */
@Service("themeService")
public class ThemeServiceImpl extends BaseServiceImpl<Theme> implements IThemeService {
    @Override
    public PageEntity<Theme> getPageEntity(ThemeDTO themeDTO) {
        int size = themeDTO.getSize();
        int page = themeDTO.getPage();
        PageEntity pageEntity = new PageEntity();
        Query query = new Query();
        Criteria criteria = new Criteria();
        if (StringUtils.isNotBlank(themeDTO.getThemeName())){
            query.addCriteria(criteria.where("themeName").regex(themeDTO.getThemeName()));
        }
        if (StringUtils.isNotBlank(themeDTO.getThemeType())){
            query.addCriteria(criteria.where("themeType").is(themeDTO.getThemeType()));
        }
        query.limit(size).skip((page-1)*size);
        pageEntity=this.findPageEntityByFilter( query, Theme.class);
        pageEntity.setCurrentPageNumber(page);
        return pageEntity;
    }
}

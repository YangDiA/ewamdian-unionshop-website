package com.ewandian.unionshop.microservice.website.websitecore.domain.service.impl;

import com.ewandian.microservices.platform.common.model.entity.PageEntity;
import com.ewandian.unionshop.microservice.resources.services.common.BaseServiceImpl;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.dto.ThemeVersionDTO;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity.*;
import com.ewandian.unionshop.microservice.website.websitecore.domain.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by YangDi on 2017/7/13.
 */
@Service("themeVersionService")
public class ThemeVersionServiceImpl extends BaseServiceImpl<ThemeVersion>implements IThemeVersionService {

    @Autowired
    private IPageTemplateService pageTemplateService;

    @Autowired
    private IPageTemplateDetailService pageTemplateDetailService;

    @Autowired
    private IPageService pageService;

    @Autowired
    private IPageColumnService pageColumnService;

    @Override
    public ThemeVersion saveThemeVersion(ThemeVersionDTO themeVersionDTO) {
        ThemeVersion themeVersion = new ThemeVersion();
        BeanUtils.copyProperties(themeVersionDTO, themeVersion);
        this.insert(themeVersion);

        PageTemplate pageTemplate=new PageTemplate();
        pageTemplate.setThemeId(themeVersionDTO.getThemeId());
        List<PageTemplate> pageTemplateList =  pageTemplateService.queryList(pageTemplate);

        //根据PageTemplate新增Page
        List<Page> pageList=new ArrayList<Page>();
        List<String> pageTemplateIdList=new ArrayList<String>();
        Map<Object,Object> mapPageIdMap=new HashMap<Object,Object>();
        pageTemplateList.forEach(PT -> {
            Page page=new Page();
            BeanUtils.copyProperties(PT,page);
            page.setVersionId(themeVersionDTO.getId());
            page.setPageName(PT.getTemplateName());
            page.setTemplateId((String) PT.getId());
            page.setId("PAGE-"+ UUID.randomUUID().toString().toUpperCase().substring(0,8));
            pageList.add(page);
            pageTemplateIdList.add((String) PT.getId());
            mapPageIdMap.put(PT.getId(),page.getId());
        });
        pageService.insert(pageList);

        List<PageTemplateDetail> pageTemplateDetailList =  pageTemplateDetailService.getListByTemplateIds(pageTemplateIdList);
        List<PageColumn>  pageColumnList = new ArrayList<>();
        pageTemplateDetailList.forEach(ptd ->{
            PageColumn pageColumn = new PageColumn();
            BeanUtils.copyProperties(ptd,pageColumn);
            pageColumn.setColumnName(ptd.getModuleIdName());
            pageColumn.setPageId((String) mapPageIdMap.get(ptd.getTemplateId()));
            pageColumn.setModuleId((String) ptd.getId());
            pageColumnList.add(pageColumn);
        });

        pageColumnService.insert(pageColumnList);

        return themeVersion;
    }

    @Override
    public PageEntity<ThemeVersion> getPageEntity(ThemeVersionDTO themeVersionDTO) {
        int size = themeVersionDTO.getSize();
        int page = themeVersionDTO.getPage();
        PageEntity pageEntity = new PageEntity();
        Query query = new Query();
        Criteria criteria = new Criteria();
        if (StringUtils.isNotBlank(themeVersionDTO.getVersionName())){
            query.addCriteria(criteria.where("versionName").regex(themeVersionDTO.getVersionName()));
        }
        if (StringUtils.isNotBlank(themeVersionDTO.getWebsiteId())){
            query.addCriteria(criteria.where("websiteId").is(themeVersionDTO.getWebsiteId()));
        }
        query.limit(size).skip((page-1)*size);
        pageEntity=this.findPageEntityByFilter( query, ThemeVersion.class);
        pageEntity.setCurrentPageNumber(page);
        return pageEntity;
    }
}

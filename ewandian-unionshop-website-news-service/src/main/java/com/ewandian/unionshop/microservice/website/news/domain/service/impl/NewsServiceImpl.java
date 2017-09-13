package com.ewandian.unionshop.microservice.website.news.domain.service.impl;

import com.ewandian.microservices.platform.common.model.entity.PageEntity;
import com.ewandian.unionshop.microservice.resources.services.common.BaseServiceImpl;
import com.ewandian.unionshop.microservice.website.news.domain.dto.NewsDTO;
import com.ewandian.unionshop.microservice.website.news.domain.entity.News;
import com.ewandian.unionshop.microservice.website.news.domain.service.INewsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * Created by YangDi on 2017/7/19.
 */
@Service("newsService")
public class NewsServiceImpl extends BaseServiceImpl<News> implements INewsService {
    @Override
    public PageEntity<News> getPageEntity(NewsDTO newsDTO) {
        int size = newsDTO.getSize();
        int page = newsDTO.getPage();
        PageEntity pageEntity = new PageEntity();
        Query query = new Query();
        Criteria criteria = new Criteria();
        if (StringUtils.isNotBlank(newsDTO.getTitle())){
            query.addCriteria(criteria.where("title").regex(newsDTO.getTitle()));
        }
        query.limit(size).skip((page-1)*size);
        pageEntity=this.findPageEntityByFilter( query, News.class);
        pageEntity.setCurrentPageNumber(page);
        return pageEntity;
    }
}

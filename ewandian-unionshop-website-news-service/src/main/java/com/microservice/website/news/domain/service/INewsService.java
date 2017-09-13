package com.microservice.website.news.domain.service;

import com.ewandian.microservices.platform.common.model.entity.PageEntity;
import com.microservice.resources.services.common.IBaseService;
import com.microservice.website.news.domain.dto.NewsDTO;
import com.microservice.website.news.domain.entity.News;

/**
 * Created by YangDi on 2017/7/19.
 */
public interface INewsService  extends IBaseService<News> {

    PageEntity<News> getPageEntity(NewsDTO newsDTO);
}

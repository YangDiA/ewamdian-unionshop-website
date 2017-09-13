package com.ewandian.unionshop.microservice.website.websitecore.domain.service.impl;

import com.ewandian.unionshop.microservice.resources.services.common.BaseServiceImpl;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity.PageTemplateDetail;
import com.ewandian.unionshop.microservice.website.websitecore.domain.service.IPageTemplateDetailService;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YangDi on 2017/7/10.
 */
@Service("pageTemplateDetailService")
public class PageTemplateDetailServiceImpl extends BaseServiceImpl<PageTemplateDetail> implements IPageTemplateDetailService {

    @Override
    public List<PageTemplateDetail> getListByTemplateIds(List<String> templateIdList) {
        Query detailQuery = new Query();
        Criteria detailCriteria = new Criteria();
        detailCriteria.and("templateId").in(templateIdList);
        Sort.Order orderSeqNo = new Sort.Order(Sort.Direction.ASC,"seqNo");
        Sort.Order orderCreate = new Sort.Order(Sort.Direction.ASC,"createDate");
        detailQuery.with(new Sort(orderSeqNo,orderCreate));
        detailQuery.addCriteria(detailCriteria);
        return this.mongoTemplate.find(detailQuery,PageTemplateDetail.class);

    }
}

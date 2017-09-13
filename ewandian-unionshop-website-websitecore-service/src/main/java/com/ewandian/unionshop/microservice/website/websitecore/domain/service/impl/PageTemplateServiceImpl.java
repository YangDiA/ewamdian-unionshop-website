package com.ewandian.unionshop.microservice.website.websitecore.domain.service.impl;

import com.ewandian.unionshop.microservice.resources.services.common.BaseServiceImpl;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity.PageTemplate;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity.PageTemplateDetail;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.vo.PageTemplateTreeVO;
import com.ewandian.unionshop.microservice.website.websitecore.domain.service.IPageTemplateDetailService;
import com.ewandian.unionshop.microservice.website.websitecore.domain.service.IPageTemplateService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YangDi on 2017/7/5.
 */@Service("pageTemplateService")
public class PageTemplateServiceImpl extends BaseServiceImpl<PageTemplate> implements IPageTemplateService {

    @Autowired
    private IPageTemplateDetailService pageTemplateDetailService;

    @Override
    public Object selectAllTemplateAndDetail(String themeId) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("themeId").is(themeId);
        query.addCriteria(criteria);
        List<PageTemplate> pageTemplateList =  this.mongoTemplate.find(query,PageTemplate.class);

        List<PageTemplateTreeVO> returnList = new ArrayList<>();

        List<String> templateIds = new ArrayList<>();//查询所有模块
        pageTemplateList.forEach(pageTemplate -> {
            templateIds.add((String) pageTemplate.getId());
            PageTemplateTreeVO pageTemplateTreeVO = new PageTemplateTreeVO();
            pageTemplateTreeVO.setId((String) pageTemplate.getId());
            pageTemplateTreeVO.setTemplateName(pageTemplate.getTemplateName());
            pageTemplateTreeVO.setType(pageTemplate.getType());
            pageTemplateTreeVO.setImgId(pageTemplate.getImgId());
            pageTemplateTreeVO.setPageType(pageTemplate.getPageType());
            pageTemplateTreeVO.setMaxPageQty(pageTemplate.getMaxPageQty());
            pageTemplateTreeVO.setMinPageQty(pageTemplate.getMinPageQty());
            returnList.add(pageTemplateTreeVO);//页面模板
        });

        //获取页面模块
        List<PageTemplateDetail> pageTemplateDetailList = pageTemplateDetailService.getListByTemplateIds(templateIds);
        List<PageTemplateTreeVO> childrenList = new ArrayList<>();
        pageTemplateDetailList.forEach(pageTemplateDetail->{
            PageTemplateTreeVO pageTemplateTreeVO = new PageTemplateTreeVO();
            pageTemplateTreeVO.setId((String) pageTemplateDetail.getId());
            pageTemplateTreeVO.setTemplateId(pageTemplateDetail.getTemplateId());
            pageTemplateTreeVO.setTemplateName(pageTemplateDetail.getModuleIdName());
            pageTemplateTreeVO.setType(pageTemplateDetail.getType());
            pageTemplateTreeVO.setImgId(pageTemplateDetail.getImgId());
            pageTemplateTreeVO.setEntityType(pageTemplateDetail.getEntityType());
            pageTemplateTreeVO.setFitmentMark(pageTemplateDetail.getFitmentMark());
            pageTemplateTreeVO.setParentId(pageTemplateDetail.getParentId());
            pageTemplateTreeVO.setModuleIdName(pageTemplateDetail.getModuleIdName());
            childrenList.add(pageTemplateTreeVO);
        });


        for(PageTemplateTreeVO pageTemplateTreeVO :returnList){
            children(pageTemplateTreeVO,childrenList);
        }
        return returnList;
    }

    private void children(PageTemplateTreeVO c,List<PageTemplateTreeVO> child){
        if(CollectionUtils.isEmpty(child)){
            return;
        }
        List<PageTemplateTreeVO> childTemp=new ArrayList<PageTemplateTreeVO>();
        for(PageTemplateTreeVO child1:child){
            if(child1.getParentId()==null){
                if(child1.getTemplateId().equals(c.getId())){
                    if(c.getChildren()!=null&&c.getChildren().size()>0){
                        c.getChildren().add(child1);

                    }else{
                        List<PageTemplateTreeVO> cs =new ArrayList<PageTemplateTreeVO>();
                        cs.add(child1);
                        c.setChildren(cs);
                    }
                }else{
                    childTemp.add(child1);
                }
            }else {
                if(c.getId().equals(child1.getParentId())){
                    if(c.getChildren()!=null&&c.getChildren().size()>0){
                        c.getChildren().add(child1);
                    }else{
                        List<PageTemplateTreeVO> cs =new ArrayList<PageTemplateTreeVO>();
                        cs.add(child1);
                        c.setChildren(cs);
                    }
                }else{
                    childTemp.add(child1);
                }
            }

        }
        if(CollectionUtils.isNotEmpty(childTemp)&&CollectionUtils.isNotEmpty(c.getChildren())){
            for(PageTemplateTreeVO c1:c.getChildren()){
                children(c1,childTemp);
            }
        }
        return;
    }

    @Override
    public int deletePageTemplateAndDetailByIds(String [] ids) {
        for (String i:ids){

            Query detailQuery = new Query();
            Criteria detailCriteria = new Criteria();
            detailCriteria.and("templateId").is(i);
            detailQuery.addCriteria(detailCriteria);
            this.mongoTemplate.remove(detailQuery,PageTemplateDetail.class);
            this.deleteById(i);

        }
        return 0;
    }
}

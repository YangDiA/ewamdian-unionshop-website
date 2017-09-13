package com.ewandian.unionshop.microservice.website.websitecore.domain.service.impl;

import com.ewandian.unionshop.microservice.resources.services.common.BaseServiceImpl;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity.*;
import com.ewandian.unionshop.microservice.website.websitecore.domain.model.vo.PageTreeVO;
import com.ewandian.unionshop.microservice.website.websitecore.domain.service.IPageColumnService;
import com.ewandian.unionshop.microservice.website.websitecore.domain.service.IPageService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YangDi on 2017/7/14.
 */
@Service("pageService")
public class PageServiceImpl extends BaseServiceImpl<Page> implements IPageService {

    @Autowired
    private IPageColumnService pageColumnService;

    @Override
    public Object selectAllPageAndColumn(String versionId) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("versionId").is(versionId);
        query.addCriteria(criteria);
        List<Page> pageList =  this.mongoTemplate.find(query,Page.class);

        List<PageTreeVO> returnList = new ArrayList<PageTreeVO>();
        List<String> pageIdList = new ArrayList<>();
        pageList.forEach(page -> {
            PageTreeVO pageTreeVO = new PageTreeVO();
            BeanUtils.copyProperties(page,pageTreeVO);
            pageTreeVO.setId((String) page.getId());
            returnList.add(pageTreeVO);
            pageIdList.add((String) page.getId());
        });

        List<PageColumn> pageColumnList = this.pageColumnService.getListByPageIds(pageIdList);
        List<PageTreeVO> childrenList = new ArrayList<>();
        pageColumnList.forEach(pageColumn -> {
            PageTreeVO pageTreeVO = new PageTreeVO();
            BeanUtils.copyProperties(pageColumn,pageTreeVO);
            pageTreeVO.setId((String)pageColumn.getId());
            pageTreeVO.setPageName(pageColumn.getColumnName());

            childrenList.add(pageTreeVO);
        });
        for (PageTreeVO pageTreeVO:returnList){
            children(pageTreeVO,childrenList);
        }

        return returnList;
    }

    private void children(PageTreeVO c,List<PageTreeVO> child){
        if(CollectionUtils.isEmpty(child)){
            return;
        }
        List<PageTreeVO> childTemp=new ArrayList<PageTreeVO>();
        for(PageTreeVO child1:child){
            if(child1.getParentId()==null){
                if(child1.getPageId().equals(c.getId())){
                    if(c.getChildren()!=null&&c.getChildren().size()>0){
                        c.getChildren().add(child1);

                    }else{
                        List<PageTreeVO> cs =new ArrayList<PageTreeVO>();
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
                        List<PageTreeVO> cs =new ArrayList<PageTreeVO>();
                        cs.add(child1);
                        c.setChildren(cs);
                    }
                }else{
                    childTemp.add(child1);
                }
            }

        }
        if(CollectionUtils.isNotEmpty(childTemp)&&CollectionUtils.isNotEmpty(c.getChildren())){
            for(PageTreeVO c1:c.getChildren()){
                children(c1,childTemp);
            }
        }
        return;
    }

    @Override
    public int deletePageAndColumnByIds(String[] ids) {
        for (String i:ids){
            Query detailQuery = new Query();
            Criteria detailCriteria = new Criteria();
            detailCriteria.and("pageId").is(i);
            detailQuery.addCriteria(detailCriteria);
            this.mongoTemplate.remove(detailQuery,PageColumn.class);
            this.deleteById(i);
        }
        return 0;
    }
}

package com.ewandian.unionshop.microservice.website.websitecore.domain.model.vo;

import java.util.List;

/**
 * Created by YangDi on 2017/7/14.
 */
public class PageTreeVO {

    private String id;

    private String pageName;

    private String type;

    private String imgId;

    private String pageType;


    //column 属性

    /**
     * 栏目名称
     */
    private String columnName;

    /**
     * 页面编号
     */
    private String pageId;

    /**
     * 父级
     */
    private String parentId;

    private List<PageTreeVO>children;

    private String entityType;

    /**
     * 装修标志位
     */
    private String fitmentMark;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<PageTreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<PageTreeVO> children) {
        this.children = children;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getFitmentMark() {
        return fitmentMark;
    }

    public void setFitmentMark(String fitmentMark) {
        this.fitmentMark = fitmentMark;
    }
}

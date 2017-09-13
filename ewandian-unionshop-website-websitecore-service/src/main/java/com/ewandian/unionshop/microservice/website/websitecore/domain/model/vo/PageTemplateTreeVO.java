package com.ewandian.unionshop.microservice.website.websitecore.domain.model.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by YangDi on 2017/7/11.
 */
public class PageTemplateTreeVO implements Serializable {

    private String id;

    private String templateName;

    private String type;

    private String imgId;

    private String pageType;

    /**
     * 至少设置页面数量
     */
    private int minPageQty;

    /**
     * 最多可设页面数量
     */
    private int maxPageQty;



    //detail 属性
    /**
     * 模块名称
     */
    private String moduleIdName;

    /**
     * 模板编号
     */
    private String templateId;

    /**
     * 显示对象类型
     */
    private String entityType;

    /**
     * 装修标志位
     */
    private String fitmentMark;

    /**
     * 父级
     */
    private String parentId;



    private List<PageTemplateTreeVO> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PageTemplateTreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<PageTemplateTreeVO> children) {
        this.children = children;
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

    public int getMinPageQty() {
        return minPageQty;
    }

    public void setMinPageQty(int minPageQty) {
        this.minPageQty = minPageQty;
    }

    public int getMaxPageQty() {
        return maxPageQty;
    }

    public void setMaxPageQty(int maxPageQty) {
        this.maxPageQty = maxPageQty;
    }

    public String getModuleIdName() {
        return moduleIdName;
    }

    public void setModuleIdName(String moduleIdName) {
        this.moduleIdName = moduleIdName;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}

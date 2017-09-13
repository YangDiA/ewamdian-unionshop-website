package com.ewandian.unionshop.microservice.website.api.service.dto;

import java.io.Serializable;

/**
 * Created by YangDi on 2017/7/18.
 */
public class PageColumnDTO implements Serializable{
    private int page;

    private int size;

    private String id;

    /**
     * 栏目名称
     */
    private String columnName;

    /**
     * 页面编号
     */
    private String pageId;

    /**
     * 模块编号
     */
    private String moduleId;

    /**
     * 图标
     */
    private String imgId;

    /**
     * 跳转至页面
     */
    private String toPageId;

    /**
     * 父级
     */
    private String parentId;

    /**
     * level从0开始
     */
    private int level;

    /**
     * 排序
     */
    private int seqNo;

    /**
     * 栏目信息
     */
    private String description;

    /**
     * 显示对象类型
     */
    private String entityType;

    /**
     * 是否显示
     */
    private String isDisplay;

    /**
     * 类型为固定至，PAGECOLUMN
     */
    private String type;

    /**
     * 装修标志位
     */
    private String fitmentMark;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getToPageId() {
        return toPageId;
    }

    public void setToPageId(String toPageId) {
        this.toPageId = toPageId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(String isDisplay) {
        this.isDisplay = isDisplay;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFitmentMark() {
        return fitmentMark;
    }

    public void setFitmentMark(String fitmentMark) {
        this.fitmentMark = fitmentMark;
    }
}

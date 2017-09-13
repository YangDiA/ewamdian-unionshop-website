package com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity;

import com.ewandian.microservices.platform.common.model.entity.BaseEntity;

/**
 * Created by YangDi on 2017/7/10.
 */
public class PageTemplateDetail extends BaseEntity {
    /**
     * 模块名称
     */
    private String moduleIdName;

    /**
     * 模块说明图
     */
    private String imgId;

    /**
     * 模板编号
     */
    private String templateId;

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
     * 显示对象类型
     */
    private String entityType;
    /**
     * 类型为固定至，PAGECOLUMN
     */
    private String type;

    /**
     * 装修标志位
     */
    private String fitmentMark;

    public String getModuleIdName() {
        return moduleIdName;
    }

    public void setModuleIdName(String moduleIdName) {
        this.moduleIdName = moduleIdName;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
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

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
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

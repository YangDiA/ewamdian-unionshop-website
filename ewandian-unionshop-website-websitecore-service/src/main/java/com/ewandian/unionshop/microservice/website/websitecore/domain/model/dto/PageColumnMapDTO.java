package com.ewandian.unionshop.microservice.website.websitecore.domain.model.dto;

import java.io.Serializable;

/**
 * Created by YangDi on 2017/7/18.
 */
public class PageColumnMapDTO implements Serializable {
    private int page;

    private int size;

    private String id;

    /**
     * 栏目编号
     */
    private String columnId;

    /**
     * 显示(对象)编号
     */
    private String entityId;

    /**
     * 显示(对象)名称
     */
    private String entityName;

    /**
     * 显示(对象)类型
     */
    private String entityType;
    /**
     * 显示图片
     */
    private String imgId;

    /**
     * 链接
     */
    private String href;

    private  String desc;

    /**
     * 排序
     */
    private int seqNo;

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

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }
}

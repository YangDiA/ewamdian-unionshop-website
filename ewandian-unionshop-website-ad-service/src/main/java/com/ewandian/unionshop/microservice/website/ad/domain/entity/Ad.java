package com.ewandian.unionshop.microservice.website.ad.domain.entity;

import com.ewandian.microservices.platform.common.model.entity.BaseEntity;

/**
 * Created by YangDi on 2017/7/19.
 */
public class Ad extends BaseEntity {

    private String adName;
    /**
     * 广告状态
     * 待审核、审核不通过、待上线、投放中、提前终止、已下线
     * 给官网数据的时候过滤条件应该是审核通过（即状态为投放中），在投放周期内
     */
    private String status;

    /**
     * 审核意见
     */
    private String auditMessage;

    /**
     * 是否删除，逻辑删除,T 、F
     */
    private String isDeleted;

    /**
     * 投放开始时间
     */
    private String deliveryStartDate;

    /**
     * 投放结束时间
     */
    private String deliveryEndDate;


    /**
     * 广告图片
     */
    private String imgId;

    /**
     * 排序
     */
    private int seqNo;


    /**
     * 广告指向连接
     */
    private String linkHref;

    /**
     * 广告标题
     */
    private String title;

    /**
     * 广告文案
     */
    private String paperWork;

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuditMessage() {
        return auditMessage;
    }

    public void setAuditMessage(String auditMessage) {
        this.auditMessage = auditMessage;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getDeliveryStartDate() {
        return deliveryStartDate;
    }

    public void setDeliveryStartDate(String deliveryStartDate) {
        this.deliveryStartDate = deliveryStartDate;
    }

    public String getDeliveryEndDate() {
        return deliveryEndDate;
    }

    public void setDeliveryEndDate(String deliveryEndDate) {
        this.deliveryEndDate = deliveryEndDate;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    public String getLinkHref() {
        return linkHref;
    }

    public void setLinkHref(String linkHref) {
        this.linkHref = linkHref;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPaperWork() {
        return paperWork;
    }

    public void setPaperWork(String paperWork) {
        this.paperWork = paperWork;
    }
}

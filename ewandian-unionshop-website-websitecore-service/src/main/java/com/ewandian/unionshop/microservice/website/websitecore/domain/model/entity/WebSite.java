package com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity;

import com.ewandian.microservices.platform.common.model.entity.BaseEntity;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by YangDi on 2017/6/28.
 */
@Document(collection ="website")
public class WebSite extends BaseEntity {
    /**
     * 站点名称
     */
    private String siteName;

    /**
     * 站点图标
     */
    private String imgId;

    /**
     * 站点Url
     */
    private String url;


    /**
     * seo标题
     */
    private String seoTitle;

    /**
     * seo关键字
     */
    private String seoKeywords;

    /**
     * seo描述
     */
    private String seoDescription;

    /**
     * 是否启用
     */
    private String isEnabled;

    /**
     * 站点类型
     */
    private String siteType;

    /**
     * 店铺Id
     */
    private String shopId;


    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    public String getSeoKeywords() {
        return seoKeywords;
    }

    public void setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords;
    }

    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getSiteType() {
        return siteType;
    }

    public void setSiteType(String siteType) {
        this.siteType = siteType;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}

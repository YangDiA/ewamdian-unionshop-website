package com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity;

import com.ewandian.microservices.platform.common.model.entity.BaseEntity;

/**
 * Created by YangDi on 2017/7/5.
 * 页面模板实体
 */
public class PageTemplate extends BaseEntity {
    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 模板图片
     */
    private String imgId;

    /**
     * 主题编号
     */
    private String themeId;

    /**
     * 页面类型：首页、活动页、二级页面等
     */
    private String pageType;

    /**
     * 至少设置页面数量
     */
    private int minPageQty;

    /**
     * 最多可设页面数量
     */
    private int maxPageQty;

    /**
     * 模板路径
     */
    private String httpPath;

    /**
     * 类型为固定至，PAGE
     */
    private String type;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getThemeId() {
        return themeId;
    }

    public void setThemeId(String themeId) {
        this.themeId = themeId;
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

    public String getHttpPath() {
        return httpPath;
    }

    public void setHttpPath(String httpPath) {
        this.httpPath = httpPath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

package com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity;

import com.ewandian.microservices.platform.common.model.entity.BaseEntity;

/**
 * Created by YangDi on 2017/7/13.
 */
public class ThemeVersion extends BaseEntity {
    /**
     * 版本名称
     */
    private String versionName;

    /**
     * 主题编号
     */
    private String themeId;

    /**
     * 站点Id
     */
    private String websiteId;

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getThemeId() {
        return themeId;
    }

    public void setThemeId(String themeId) {
        this.themeId = themeId;
    }

    public String getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(String websiteId) {
        this.websiteId = websiteId;
    }
}

package com.ewandian.unionshop.microservice.website.websitecore.domain.model.dto;

import java.io.Serializable;

/**
 * Created by pc15 on 2017/7/13.
 */
public class ThemeVersionDTO implements Serializable {
    private int page;

    private int size;

    private String id;
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

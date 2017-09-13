package com.ewandian.unionshop.microservice.website.api.service.dto;

import java.io.Serializable;

/**
 * Created by Yangi on 2017/6/29.
 */
public class ThemeDTO implements Serializable {
    private int page;

    private int size;

    private String id;

    /**
     * 主题名称
     */
    private String themeName;

    /**
     * 主题类型
     */
    private String themeType;

    /**
     * 主题图片
     */
    private String imgId;

    /**
     * 是否免费
     */
    private String isFree;

    /**
     * 是否启用
     */
    private String isEnabled;

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

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getThemeType() {
        return themeType;
    }

    public void setThemeType(String themeType) {
        this.themeType = themeType;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getIsFree() {
        return isFree;
    }

    public void setIsFree(String isFree) {
        this.isFree = isFree;
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }
}

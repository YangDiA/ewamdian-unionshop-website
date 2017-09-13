package com.ewandian.unionshop.microservice.website.websitecore.domain.model.entity;

import com.ewandian.microservices.platform.common.model.entity.BaseEntity;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by YangDi on 2017/6/28.
 */
@Document(collection = "theme")
public class Theme extends BaseEntity {
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

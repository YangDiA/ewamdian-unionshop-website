package com.ewandian.unionshop.microservice.website.api.service.dto;

import java.io.Serializable;

/**
 * Created by YangDi on 2017/7/19.
 */
public class NewsDTO implements Serializable{
    private int page;

    private int size;

    private String id;

    /**
     * 文章类型
     */
    private String newsType;
    /**
     * 标题
     */
    private String title;

    /**
     * 封面图片Id
     */
    private String imgId;
    /**
     * 内容
     */
    private String content;
    /**
     * 作者
     */
    private String author;
    /**
     * 来源
     */
    private String sourceFrom;

    /**
     * 点赞数
     */
    private Integer likeArticle;

    /**
     * 吐槽数
     */
    private Integer taunt;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 品类
     */
    private String gcId;
    /**
     * 跳转地址
     */
    private String toUrl;


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

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
    }

    public Integer getLikeArticle() {
        return likeArticle;
    }

    public void setLikeArticle(Integer likeArticle) {
        this.likeArticle = likeArticle;
    }

    public Integer getTaunt() {
        return taunt;
    }

    public void setTaunt(Integer taunt) {
        this.taunt = taunt;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getGcId() {
        return gcId;
    }

    public void setGcId(String gcId) {
        this.gcId = gcId;
    }

    public String getToUrl() {
        return toUrl;
    }

    public void setToUrl(String toUrl) {
        this.toUrl = toUrl;
    }
}

package com.thatnight.rxreok.bean;

/**
 * Time:2017.3.11 15:29
 * Created By:ThatNight
 */

public class News {
    /**
     * ctime : 2017-03-11 11:05
     * title : 济南工地土层塌方 消防员徒手挖出两名被困工人
     * description : 搜狐社会
     * picUrl : http://photocdn.sohu.com/20170311/Img483001844_ss.jpeg
     * url : http://news.sohu.com/20170311/n483001843.shtml
     */

    private String ctime;
    private String title;
    private String description;
    private String picUrl;
    private String url;

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

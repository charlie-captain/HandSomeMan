package com.thatnight.rxreok.bean;

/**
 * Time:2017.3.14 0:57
 * Created By:ThatNight
 */

public class Health {
    /**
     * id : 17
     * description : 如今学生族课业负担沉重，长时间看书、做作业，课后又要参加各种培训班，缺乏户外活动，久坐低头势必使颈部固定在一个姿势，周围的肌肉长时间处于非协调受力状态，久而久之，导致颈部肌肉损伤，引发颈肩酸痛、头昏、头痛、注意力不集中等一系列症状，有的甚至有血压高的症状
     * img : http://api.avatardata.cn/Lore/Img?file=c6dbcead10954922b9ecc6779dd0b6b1.jpg
     * keywords : 长时间 颈椎病 中学生 体育活动 运动
     * title : 学生族课业负担沉重 劳逸结合、动静结合
     * loreclass : 2
     * time : 1438305274000
     * fcount : 0
     * count : 465
     */

    private int id;
    private String description;
    private String img;
    private String keywords;
    private String title;
    private int loreclass;
    private long time;
    private int fcount;
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLoreclass() {
        return loreclass;
    }

    public void setLoreclass(int loreclass) {
        this.loreclass = loreclass;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getFcount() {
        return fcount;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

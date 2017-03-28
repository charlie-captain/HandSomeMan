package com.thatnight.rxreok.bean;

import java.util.List;

/**
 * Time:2017.3.14 0:56
 * Created By:ThatNight
 */

public class HealthList {


    /**
     * total : 525
     * result : [{"id":17,"description":"如今学生族课业负担沉重，长时间看书、做作业，课后又要参加各种培训班，缺乏户外活动，久坐低头势必使颈部固定在一个姿势，周围的肌肉长时间处于非协调受力状态，久而久之，导致颈部肌肉损伤，引发颈肩酸痛、头昏、头痛、注意力不集中等一系列症状，有的甚至有血压高的症状","img":"http://api.avatardata.cn/Lore/Img?file=c6dbcead10954922b9ecc6779dd0b6b1.jpg","keywords":"长时间 颈椎病 中学生 体育活动 运动 ","title":"学生族课业负担沉重 劳逸结合、动静结合","loreclass":2,"time":1438305274000,"fcount":0,"count":465},{"id":51,"description":"父母用手机孩子变焦躁拉德斯基等人的研究显示，当父母花更多时间关注手机时，多数孩子变得焦躁不安，并试图通过自己的行为引起父母注意，不过也有孩子\u201c自娱自乐\u201d的个例","img":"http://api.avatardata.cn/Lore/Img?file=9d25b54d140b46079b72513cf7169f43.jpg","keywords":"父母 手机 孩子 研究 研究人员 ","title":"和孩子在一起时请放下手机！手机会影响你与孩子的关系","loreclass":2,"time":1438305348000,"fcount":0,"count":303},{"id":60,"description":"讯有许多因素会影响儿童癌症幸存者的寿命：例如没有得到足够的锻炼、体重不足、并且是担心他们自己未来的健康和自己的健康医疗保险","img":"http://api.avatardata.cn/Lore/Img?file=5f97b4f6b1344912b4105d023ec8dc48.png","keywords":"幸存者 儿童 健康 癌症 恶性肿瘤 ","title":"生活习惯不良所致 儿童肿瘤幸存者寿命短 ","loreclass":2,"time":1438305357000,"fcount":0,"count":201}]
     * error_code : 0
     * reason : Succes
     */

    private int total;
    private int error_code;
    private String reason;
    private List<ResultBean> result;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
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
}

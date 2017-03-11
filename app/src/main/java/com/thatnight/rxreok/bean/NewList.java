package com.thatnight.rxreok.bean;

import java.util.List;

/**
 * Time:2017.2.20 22:14
 * Created By:ThatNight
 */

public class NewList {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2017-03-11 11:05","title":"济南工地土层塌方 消防员徒手挖出两名被困工人","description":"搜狐社会","picUrl":"http://photocdn.sohu.com/20170311/Img483001844_ss.jpeg","url":"http://news.sohu.com/20170311/n483001843.shtml"},{"ctime":"2017-03-11 11:08","title":"资深殡葬人士揭秘：死人生意利润率达1000%","description":"搜狐社会","picUrl":"http://photocdn.sohu.com/20170311/Img483001981_ss.jpeg","url":"http://news.sohu.com/20170311/n483001980.shtml"},{"ctime":"2017-03-11 11:44","title":"网传广州有人悬赏81万元和千万豪宅找狗","description":"搜狐社会","picUrl":"http://photocdn.sohu.com/20170311/Img483007454_ss.jpeg","url":"http://news.sohu.com/20170311/n483007452.shtml"},{"ctime":"2017-03-11 10:00","title":"男子在公路上玩\u201c漂移\u201d 用\u201cV\u201d字手势挑衅民警","description":"搜狐社会","picUrl":"http://photocdn.sohu.com/20170311/Img482998405_ss.jpeg","url":"http://news.sohu.com/20170311/n482998404.shtml"},{"ctime":"2017-03-11 09:14","title":"女子轧死宠物狗遭对方捅杀全家灭门 凶手获死刑","description":"搜狐社会","picUrl":"http://photocdn.sohu.com/20170311/Img482993505_ss.jpeg","url":"http://news.sohu.com/20170311/n482995921.shtml"},{"ctime":"2017-03-11 09:53","title":"村里的小学生 原来吃了这么多劣质食品","description":"搜狐社会","picUrl":"http://photocdn.sohu.com/20170311/Img482995923_ss.png","url":"http://news.sohu.com/20170311/n482998092.shtml"},{"ctime":"2017-03-11 09:58","title":"男孩被卡广场摇椅 哭喊：先帮我请假","description":"搜狐社会","picUrl":"http://photocdn.sohu.com/20170311/Img482998093_ss.jpeg","url":"http://news.sohu.com/20170311/n482998292.shtml"},{"ctime":"2017-03-11 07:44","title":"女子情绪失控砍伤前夫 将幼子推下楼致其坠亡","description":"搜狐社会","picUrl":"http://photocdn.sohu.com/20170311/Img482992002_ss.jpeg","url":"http://news.sohu.com/20170311/n482993503.shtml"},{"ctime":"2017-03-11 07:46","title":"保安救悬空男孩 以自身当\u201c肉垫\u201d不幸身亡(图)","description":"搜狐社会","picUrl":"http://photocdn.sohu.com/20170311/Img482993505_ss.jpeg","url":"http://news.sohu.com/20170311/n482993504.shtml"},{"ctime":"2017-03-11 06:48","title":"女子丧夫小三上门讨债 对方是自己资助的贫困女生","description":"搜狐社会","picUrl":"http://photocdn.sohu.com/20170311/Img482992002_ss.jpeg","url":"http://news.sohu.com/20170311/n482992690.shtml"}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
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
}

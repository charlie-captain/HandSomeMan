package com.thatnight.rxreok.bean;

import java.util.List;

/**
 * Time:2017.3.13 17:47
 * Created By:ThatNight
 */

public class GirlList {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2016-05-08 13:00","title":"丰满好身材易阳ELLY性感妩媚","description":"美女写真","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2015/12/m.xxxiao.com_dacb28920b6750e6878530c61f93f024-760x500.jpg","url":"http://m.xxxiao.com/17467"},{"ctime":"2016-05-08 22:00","title":"校园时光","description":"华声美女","picUrl":"http://image.hnol.net/c/2016-05/08/21/201605082145452161-1876615.jpg","url":"http://bbs.voc.com.cn/mm/meinv-7250457-0-1.html"},{"ctime":"2016-05-08 22:00","title":"微风","description":"华声美女","picUrl":"http://image.hnol.net/c/2016-05/08/21/201605082138291141-1876615.jpg","url":"http://bbs.voc.com.cn/mm/meinv-7250451-0-1.html"},{"ctime":"2016-05-08 22:00","title":"张扬的过！夏！天！","description":"华声美女","picUrl":"http://image.hnol.net/c/2016-05/08/21/20160508213144181-1876615.jpg","url":"http://bbs.voc.com.cn/mm/meinv-7250437-0-1.html"},{"ctime":"2016-05-08 22:00","title":"[推荐]娇艳无比的陈丽","description":"华声美女","picUrl":"http://image.hnol.net/c/2016-05/08/21/201605082123301881-117285.jpg","url":"http://bbs.voc.com.cn/mm/meinv-7250429-0-1.html"},{"ctime":"2016-05-08 22:00","title":"星晴","description":"华声美女","picUrl":"http://image.hnol.net/c/2016-05/08/21/201605082120306931-1876615.jpg","url":"http://bbs.voc.com.cn/mm/meinv-7250426-0-1.html"},{"ctime":"2016-05-09 08:00","title":"想和你在海边","description":"华声美女","picUrl":"http://image.hnol.net/c/2016-05/08/20/201605082053474321-1876615.jpg","url":"http://bbs.voc.com.cn/mm/meinv-7250401-0-1.html"},{"ctime":"2016-05-09 08:00","title":"五月的天空","description":"华声美女","picUrl":"http://image.hnol.net/c/2016-05/08/21/201605082108359471-1876615.jpg","url":"http://bbs.voc.com.cn/mm/meinv-7250415-0-1.html"},{"ctime":"2016-05-09 09:00","title":"[贴图]清纯的妹子招人喜--周末公园外拍","description":"华声美女","picUrl":"http://image.hnol.net/c/2016-05/09/08/201605090850472831-4217076.jpg","url":"http://bbs.voc.com.cn/mm/meinv-7250687-0-1.html"},{"ctime":"2016-05-09 09:00","title":"初夏的味道【转载】","description":"华声美女","picUrl":"http://image.hnol.net/c/2016-05/09/08/20160509085452571-3356886.jpg","url":"http://bbs.voc.com.cn/mm/meinv-7250690-0-1.html"}]
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
         * ctime : 2016-05-08 13:00
         * title : 丰满好身材易阳ELLY性感妩媚
         * description : 美女写真
         * picUrl : http://m.xxxiao.com/wp-content/uploads/sites/3/2015/12/m.xxxiao.com_dacb28920b6750e6878530c61f93f024-760x500.jpg
         * url : http://m.xxxiao.com/17467
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

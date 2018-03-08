package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/24
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class NoticeInfo {

    /**
     * count : 10
     * list : [{"id":12,"content":"您已完善个人头像和姓名，还未完善公司信息，完善公司信息后可享受更多的权限哦！去完善","sysService":4,"isread":"0","title":"完善信息","toUser":51,"states":"0","createTime":1508832039000,"createMan":"iambuyer","updateTime":1508832039000,"updateMan":"iambuyer"}]
     */

    public int count;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * id : 12
         * content : 您已完善个人头像和姓名，还未完善公司信息，完善公司信息后可享受更多的权限哦！去完善
         * sysService : 4
         * isread : 0
         * title : 完善信息
         * toUser : 51
         * states : 0
         * createTime : 1508832039000
         * createMan : iambuyer
         * updateTime : 1508832039000
         * updateMan : iambuyer
         */

        public int id;
        public String content;
        public int sysService;
        public String isread;
        public String title;
        public int toUser;
        public String states;
        public long createTime;
        public String createMan;
        public long updateTime;
        public String updateMan;
    }
}

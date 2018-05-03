package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/21
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class MySuggestionInfo {

    /**
     * count : 2
     * list : [{"context":"ceshi爱好","id":31,"phone":"18611874477","userId":365,"img001":null,"img002":null,"img003":null,"img004":null,"img005":null,"states":"0","createTime":1521537505000,"createMan":"iambuyer","systemType":"1","feedType":"0","contactUser":null,"isContactUser":null},{"context":"ceshi爱好","id":30,"phone":"18611874477","userId":365,"img001":null,"img002":null,"img003":null,"img004":null,"img005":null,"states":"0","createTime":1521511252000,"createMan":"iambuyer","systemType":"1","feedType":"0","contactUser":null,"isContactUser":null}]
     */

    public int count;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * context : ceshi爱好
         * id : 31
         * phone : 18611874477
         * userId : 365
         * img001 : null
         * img002 : null
         * img003 : null
         * img004 : null
         * img005 : null
         * states : 0
         * createTime : 1521537505000
         * createMan : iambuyer
         * systemType : 1
         * feedType : 0
         * contactUser : null
         * isContactUser : null
         */

        public String context;
        public int id;
        public String phone;
        public int userId;
        public String img001;
        public String img002;
        public String img003;
        public String img004;
        public String img005;
        public String states;
        public long createTime;
        public String createMan;
        public String systemType;
        public String feedType;
        public Object contactUser;
        public Object isContactUser;
    }
}

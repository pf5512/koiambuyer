package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/4/8
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class ChatListInfo {

    /**
     * content : [{"userHeadImg":"http://img01.iambuyer.com/imgup/upload/images/2018/04/02/lFwQazC9qBQy0B2rE2Cmhcjjc2Bm0SvmzcogT9Ao56k2ggOkhHfZAIthcjjc2FY4UcBumLVy5D3z.jpeg","toUserId":57,"userName":"吴启万","EndTime":1522725976000,"fromUserId":365},{"userHeadImg":null,"toUserId":202,"userName":"柳","EndTime":1522725949000,"fromUserId":365},{"userHeadImg":null,"toUserId":203,"userName":"王亚蒙","EndTime":1514356766000,"fromUserId":365}]
     * ret : 200
     * msg : success
     */

    public String ret;
    public String msg;
    public List<ContentBean> content;

    public static class ContentBean {
        /**
         * userHeadImg : http://img01.iambuyer.com/imgup/upload/images/2018/04/02/lFwQazC9qBQy0B2rE2Cmhcjjc2Bm0SvmzcogT9Ao56k2ggOkhHfZAIthcjjc2FY4UcBumLVy5D3z.jpeg
         * toUserId : 57
         * userName : 吴启万
         * EndTime : 1522725976000
         * fromUserId : 365
         */

        public String userHeadImg;
        public int toUserId;
        public String userName;
        public long EndTime;
        public int fromUserId;
        public String lastMsg;
        public String prodName;
        public int notReadNum;

        @Override
        public String toString() {
            return "ContentBean{" +
                    "userHeadImg='" + userHeadImg + '\'' +
                    ", toUserId=" + toUserId +
                    ", userName='" + userName + '\'' +
                    ", EndTime=" + EndTime +
                    ", fromUserId=" + fromUserId +
                    ", lastMsg='" + lastMsg + '\'' +
                    ", prodName='" + prodName + '\'' +
                    ", notReadNum=" + notReadNum +
                    '}';
        }
    }
}

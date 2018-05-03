package com.hc360.koiambuyer.api.bean;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/4/8
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class RightNowInfo {

    /**
     * content : {"id":229,"title":"好纠结","img":"http://img05.iambuyer.com/imgup/upload/images/2018/01/11/PpCtpvFqeWlA8TpwycsDPmyTF3f0bsudRISMaASG2nD7Fw6ho9FSefoIhcjjc2Bhcjjc2BOAnGDX.jpg","maxPice":null,"user":{"email":null,"role":"6","userName":"吴启万","userId":"182","pName":null,"cName":null,"headImg":"http://img04.iambuyer.com/imgup/upload/images/2018/04/02/lFwQazC9qBQy0B2rE2Cmhcjjc2Bm0SvmzcogT9Ao56k2ggOkhHfZAIthcjjc2FY4UcBumLVy5D3z.jpeg","phone":"18792629301","ssoUserId":57,"referUserid":365,"createTime":null,"userQrimg":null,"createTimeStart":null,"createTimeEnd":null,"loginTimeEnd":null,"loginTimeStart":null,"referUserName":"我希","userContent":"我是买手","loginTime":null},"minPice":null}
     * ret : 200
     * msg : success
     */

    public ContentBean content;
    public String ret;
    public String msg;

    public static class ContentBean {
        /**
         * id : 229
         * title : 好纠结
         * img : http://img05.iambuyer.com/imgup/upload/images/2018/01/11/PpCtpvFqeWlA8TpwycsDPmyTF3f0bsudRISMaASG2nD7Fw6ho9FSefoIhcjjc2Bhcjjc2BOAnGDX.jpg
         * maxPice : null
         * user : {"email":null,"role":"6","userName":"吴启万","userId":"182","pName":null,"cName":null,"headImg":"http://img04.iambuyer.com/imgup/upload/images/2018/04/02/lFwQazC9qBQy0B2rE2Cmhcjjc2Bm0SvmzcogT9Ao56k2ggOkhHfZAIthcjjc2FY4UcBumLVy5D3z.jpeg","phone":"18792629301","ssoUserId":57,"referUserid":365,"createTime":null,"userQrimg":null,"createTimeStart":null,"createTimeEnd":null,"loginTimeEnd":null,"loginTimeStart":null,"referUserName":"我希","userContent":"我是买手","loginTime":null}
         * minPice : null
         */

        public int id;
        public String title;
        public String img;
        public Object maxPice;
        public UserBean user;
        public Object minPice;

        public static class UserBean {
            /**
             * email : null
             * role : 6
             * userName : 吴启万
             * userId : 182
             * pName : null
             * cName : null
             * headImg : http://img04.iambuyer.com/imgup/upload/images/2018/04/02/lFwQazC9qBQy0B2rE2Cmhcjjc2Bm0SvmzcogT9Ao56k2ggOkhHfZAIthcjjc2FY4UcBumLVy5D3z.jpeg
             * phone : 18792629301
             * ssoUserId : 57
             * referUserid : 365
             * createTime : null
             * userQrimg : null
             * createTimeStart : null
             * createTimeEnd : null
             * loginTimeEnd : null
             * loginTimeStart : null
             * referUserName : 我希
             * userContent : 我是买手
             * loginTime : null
             */

            public Object email;
            public String role;
            public String userName;
            public String userId;
            public Object pName;
            public Object cName;
            public String headImg;
            public String phone;
            public int ssoUserId;
            public int referUserid;
            public Object createTime;
            public Object userQrimg;
            public Object createTimeStart;
            public Object createTimeEnd;
            public Object loginTimeEnd;
            public Object loginTimeStart;
            public String referUserName;
            public String userContent;
            public Object loginTime;
        }
    }
}

package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/4/19
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class ChatMsgInfo {

    /**
     * content : [{"userName":"buyer63268","userId":479,"userHeadImg":null,"endTime":1524102262000,"chatContent":"三天内暂无记录","thisId":202,"unMsgCount":0},{"userName":"buyer13526","userId":475,"userHeadImg":null,"endTime":1524035755000,"chatContent":"三天内暂无记录","thisId":202,"unMsgCount":0},{"userName":"buyer69948","userId":56,"userHeadImg":null,"endTime":1524019590000,"chatContent":"三天内暂无记录","thisId":202,"unMsgCount":0},{"userName":"buyer28370","userId":474,"userHeadImg":null,"endTime":1523959790000,"chatContent":"三天内暂无记录","thisId":202,"unMsgCount":0},{"userName":"我希","userId":365,"userHeadImg":"http://img05.iambuyer.com/imgup/upload/images/2018/03/27/kDiaG2pBI8obOwIBsTS6hrcRbcb8qSqhh3AJJTmjNTOgahcjjc2FfZDCwmhGIc7BrRDfhy.jpg","endTime":1523954412883,"chatContent":"huu","thisId":202,"unMsgCount":16},{"userName":"尚志","userId":378,"userHeadImg":null,"endTime":1523343073000,"chatContent":"三天内暂无记录","thisId":202,"unMsgCount":0},{"userName":"曾国藩","userId":204,"userHeadImg":null,"endTime":1523330967000,"chatContent":"三天内暂无记录","thisId":202,"unMsgCount":0},{"userName":"康熙","userId":311,"userHeadImg":null,"endTime":1523330646000,"chatContent":"三天内暂无记录","thisId":202,"unMsgCount":0},{"userName":"乾隆","userId":37,"userHeadImg":null,"endTime":1523167275000,"chatContent":"三天内暂无记录","thisId":202,"unMsgCount":0},{"userName":"名字","userId":440,"userHeadImg":null,"endTime":1523167268000,"chatContent":"三天内暂无记录","thisId":202,"unMsgCount":0},{"userName":"王阳明","userId":395,"userHeadImg":null,"endTime":1523167222000,"chatContent":"三天内暂无记录","thisId":202,"unMsgCount":0}]
     * ret : 200
     * msg : success
     */

    public String ret;
    public String msg;
    public List<ContentBean> content;

    public static class ContentBean {
        /**
         * userName : buyer63268
         * userId : 479
         * userHeadImg : null
         * endTime : 1524102262000
         * chatContent : 三天内暂无记录
         * thisId : 202
         * unMsgCount : 0
         */

        public String userName;
        public int userId;
        public String userHeadImg;
        public long endTime;
        public String chatContent;
        public int thisId;
        public int unMsgCount;
    }
}

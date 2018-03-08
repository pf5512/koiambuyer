package com.hc360.koiambuyer.api.bean;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/14
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class ChatInfo {

    /**
     * content : {"id":52,"unit":"个","userHeadImg":"http://img04.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","title":"张家林产品","addStr":"北京 北京市 东城区","compName":"企业名称","img":"http://img06.iambuyer.com/imgup/upload/images/2017/10/20/AtmOshYCWZ8B8xhcjjc2BfjfbQ4jVP4rJk3gAAsJ25eJFUAofXsemvgLY1FO3aUQDAhoFl.jpg","userName":"www","userPosition":"销售经理","minPice":2.1}
     * ret : 200
     * msg : success
     */

    public ContentBean content;
    public String ret;
    public String msg;

    public static class ContentBean {
        /**
         * id : 52
         * unit : 个
         * userHeadImg : http://img04.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg
         * title : 张家林产品
         * addStr : 北京 北京市 东城区
         * compName : 企业名称
         * img : http://img06.iambuyer.com/imgup/upload/images/2017/10/20/AtmOshYCWZ8B8xhcjjc2BfjfbQ4jVP4rJk3gAAsJ25eJFUAofXsemvgLY1FO3aUQDAhoFl.jpg
         * userName : www
         * userPosition : 销售经理
         * minPice : 2.1
         */

        public int id;
        public String unit;
        public String userHeadImg;
        public String title;
        public String addStr;
        public String compName;
        public String img;
        public String userName;
        public String userPosition;
        public double minPice;

    }
}

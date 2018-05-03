package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/6
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class PurchaseHomeInfo {


    /**
     * indexComp : [{"inName":"应急救援","id":1,"serialNumber":1,"imgNameOne":"http://img06.iambuyer.com/imgup/upload/images/2017/10/31/DusTuVXq4QxWhcQrhcjjc2FQwiHhv1hcjjc2B5JkQZnc0lJBBIUB1iIDsA3NXIN9ldhcjjc2BHZWaN4CSX.jpg","imgNameTwo":"http://img01.iambuyer.com/imgup/upload/images/2017/10/31/iCfyTleu5cvvSg46DCt8zW0yhDsO9g9HxKJD7n3jPXo8i4MgTLd33g7HKLbjOCVr.jpg","mainIndustry":"266","states":"0","createMan":"11aaa","updateMan":"admin","titleOne":"让污染凤舞","titleTwo":"凤舞","createTime":1508913794000,"updateTime":1509950444000},{"inName":"家装","id":2,"serialNumber":2,"imgNameOne":"http://img07.iambuyer.com/imgup/upload/images/2017/10/31/ahcjjc2FzsDQ015cr9GZ0f3ZRVeb8I0ytsQl2QTMvdTO2s9afy3Thcjjc2B7iy2AkpoMQgACkjDL.jpg","imgNameTwo":"http://img08.iambuyer.com/imgup/upload/images/2017/10/31/GINtLOM3mqbXrQCfa8bBeTps4fIAIYIPAdbbmd6PV859ebs3ryRKUiHv3tJO8URk.jpg","mainIndustry":"233","states":"0","createMan":null,"updateMan":"admin","titleOne":"到凤舞凤舞","titleTwo":"放3太贵3","createTime":null,"updateTime":1509950452000}]
     * supply : [{"jumpContent":"060","id":1,"jumpType":"0","imgName":"http://img01.iambuyer.com/imgup/upload/images/2017/10/31/hcjjc2BmD8E5j8P7uxIBkymJ6nuRxv2CyZUv6JQrXWOMM5a9s9k8jBEQW1kiZ2IqBboHa5.jpg","updateMan":"admin","titleTwo":"若非我","updateTime":1509950505000,"titleOne":"耳温枪","jumpTitle":"丝印/印花","states":"0","serialNumber":1},{"jumpContent":"060","id":2,"jumpType":"0","imgName":"http://img01.iambuyer.com/imgup/upload/images/2017/10/31/SedJ41jDUWgQ7ZSIY5kVPRsAAvbhcjjc2B3Khcjjc2BReFZWHkOdwdCGDWng53hcjjc2BmmhNgXUcjaLfhcjjc2F.jpg","updateMan":"admin","titleTwo":"3214惹我惹我","updateTime":1509950509000,"titleOne":"21额外额外","jumpTitle":"丝印/印花","states":"0","serialNumber":2},{"jumpContent":"062","id":3,"createTime":1509442970000,"jumpType":"0","imgName":"http://img01.iambuyer.com/imgup/upload/images/2017/10/31/fSVcNuoRhcjjc2BaDsAzP1fh6A2o4eA46M5jcoqhcjjc2Bw5o4QT5okVVYusPOMqnykwYQJ3w1bR.jpg","updateMan":"admin","titleTwo":"3213","updateTime":1509950524000,"titleOne":"321321","jumpTitle":"消防","states":"0","serialNumber":3,"cretaeMan":"admin"},{"jumpContent":"052","id":4,"createTime":1509443139000,"jumpType":"0","imgName":"http://img01.iambuyer.com/imgup/upload/images/2017/10/31/4TI9CxVW7KbvHirKgHHW8ut9g3x3mZPKs1VsXuVxU6N9AJVGgQUDZ3NvniKUPakv.jpg","updateMan":"admin","titleTwo":"俄文","updateTime":1509950515000,"titleOne":"俄文","jumpTitle":"供热采暖","states":"0","serialNumber":4,"cretaeMan":"admin"},{"jumpContent":"060","id":5,"createTime":1509962217000,"jumpType":"0","imgName":"http://img04.iambuyer.com/imgup/upload/images/2017/11/06/0gpoCOioolRrHVOVhcjjc2FVwhhcjjc2FoNdsoogCoifvlpgv50ZnwxoW2ya3RIqBNjWPwu5m7oR.png","titleTwo":"5","titleOne":"5","jumpTitle":"丝印/印花","states":"0","serialNumber":5,"cretaeMan":"admin"},{"jumpContent":"062","id":6,"createTime":1509962229000,"jumpType":"0","imgName":"http://img07.iambuyer.com/imgup/upload/images/2017/11/06/H1qZgJPTiUlp92fbjJTnjBf2sbWuyV5IjsMK5q3JeQbsT9ZRAKXMZKCWFOy4Ihcjjc2F7r.jpg","titleTwo":"6","titleOne":"6","jumpTitle":"消防","states":"0","serialNumber":6,"cretaeMan":"admin"},{"jumpContent":"062","id":7,"createTime":1509962239000,"jumpType":"0","imgName":"http://img06.iambuyer.com/imgup/upload/images/2017/11/06/N0u4Mwg9XoCswxdq9BID4rtNXPSMDNJnzLzhcjjc2FEtFhDERI599xhcjjc2BM6O4kuCXi8iefkn.png","titleTwo":"7","titleOne":"7","jumpTitle":"消防","states":"0","serialNumber":7,"cretaeMan":"admin"},{"jumpContent":"061","id":8,"createTime":1509962250000,"jumpType":"0","imgName":"http://img06.iambuyer.com/imgup/upload/images/2017/11/06/lg8uJhnm1u5HnYgJW3tHPLwlhcjjc2FJblhcjjc2FZk4tx8p7hcjjc2FgNnVAnDoZgc85A5vMy2ORhcjjc2FomHz.jpg","titleTwo":"8","titleOne":"8","jumpTitle":"纸业","states":"0","serialNumber":8,"cretaeMan":"admin"}]
     * indexTab : [{"jumpContent":"047","id":25,"jumpType":"0","imgName":"http://img08.iambuyer.com/imgup/upload/images/2017/11/06/PpVNf0AVRdqsF5evLk2RVvQe9L3W2iIJcI3TX65TjBQ9oEXtTNPK3hcjjc2F0PK6vutH6f.png","updateMan":"admin","title":"分","updateTime":1509957867000,"jumpTitle":"清洁/保洁/洗涤","states":"0","serialNumber":1},{"jumpContent":"001","id":26,"jumpType":"1","imgName":"http://img06.iambuyer.com/imgup/upload/images/2017/10/31/5gPADiQ5LsYFlPQa1PiODMFVmMSBm9hy0BBiZHocHoI1JDySgmSkRywr36LuJH9e.jpg","updateMan":"admin","title":"22222222","updateTime":1509437545000,"jumpTitle":"22222222","states":"0","serialNumber":2},{"jumpContent":"045","id":27,"jumpType":"0","imgName":"http://img06.iambuyer.com/imgup/upload/images/2017/10/31/nSGypzfygBrIhcjjc2BU5FMnzOvztzZfDskfSP7q9EaRP5iWTFUJPQyQE5oP0rN55SHWFZ.jpg","updateMan":"admin","title":"2恶213","updateTime":1509950385000,"jumpTitle":"家居用品","states":"0","serialNumber":3},{"jumpContent":"061","id":28,"jumpType":"0","imgName":"http://img03.iambuyer.com/imgup/upload/images/2017/10/31/N50W4X7Z2cWgGPextlFq0QceKxIvcn9xeqIqlt9G9AIHkKdAOgqQmdwv9HxWibvX.jpg","updateMan":"admin","title":"绯闻绯闻","updateTime":1509950394000,"jumpTitle":"纸业","states":"0","serialNumber":4}]
     * carousel : [{"id":3,"content":"e32r32","serialNumber":3,"imgName":"http://img08.iambuyer.com/imgup/upload/images/2017/10/13/MQGjTYyi2ehYaD5bwnTciZcbIouvmtcD0KtbHu5qvfbRQwgEKg6Xxhcjjc2BaJURaXF3m1.jpg","states":"0","createMan":null,"updateMan":null,"loginType":"0","jumpType":"0","createTime":1508915952000,"updateTime":1508915954000,"jumpContent":"047"},{"id":1,"content":"凤舞","serialNumber":4,"imgName":"http://img08.iambuyer.com/imgup/upload/images/2017/10/27/ApP7bhcjjc2FoJD5XcV9MirbPhcjjc2F4hcjjc2Fuzhcjjc2FChcjjc2BDIuJgpF9BlPTwhcjjc2BMoPteAqQMpdrKWqNEMtyAUG.jpg","states":"0","createMan":null,"updateMan":null,"loginType":"0","jumpType":"1","createTime":1508913811000,"updateTime":1508915747000,"jumpContent":"http://www.baidu.com"}]
     */

    public List<IndexCompBean> indexComp;
    public List<SupplyBean> supply;
    public List<IndexTabBean> indexTab;
    public List<CarouselBean> carousel;

    public static class IndexCompBean {
        /**
         * inName : 应急救援
         * id : 1
         * serialNumber : 1
         * imgNameOne : http://img06.iambuyer.com/imgup/upload/images/2017/10/31/DusTuVXq4QxWhcQrhcjjc2FQwiHhv1hcjjc2B5JkQZnc0lJBBIUB1iIDsA3NXIN9ldhcjjc2BHZWaN4CSX.jpg
         * imgNameTwo : http://img01.iambuyer.com/imgup/upload/images/2017/10/31/iCfyTleu5cvvSg46DCt8zW0yhDsO9g9HxKJD7n3jPXo8i4MgTLd33g7HKLbjOCVr.jpg
         * mainIndustry : 266
         * states : 0
         * createMan : aaa
         * updateMan : admin
         * titleOne : 让污染凤舞
         * titleTwo : 凤舞
         * createTime : 1508913794000
         * updateTime : 1509950444000
         */

        public String inName;
        public int id;
        public int serialNumber;
        public String imgNameOne;
        public String imgNameTwo;
        public String mainIndustry;
        public String states;
        public String createMan;
        public String updateMan;
        public String titleOne;
        public String titleTwo;
        public long createTime;
        public long updateTime;
    }

    public static class SupplyBean {
        /**
         * jumpContent : 060
         * id : 1
         * jumpType : 0
         * imgName : http://img01.iambuyer.com/imgup/upload/images/2017/10/31/hcjjc2BmD8E5j8P7uxIBkymJ6nuRxv2CyZUv6JQrXWOMM5a9s9k8jBEQW1kiZ2IqBboHa5.jpg
         * updateMan : admin
         * titleTwo : 若非我
         * updateTime : 1509950505000
         * titleOne : 耳温枪
         * jumpTitle : 丝印/印花
         * states : 0
         * serialNumber : 1
         */

        public String jumpContent;
        public int id;
        public String jumpType;
        public String imgName;
        public String updateMan;
        public String titleTwo;
        public long updateTime;
        public String titleOne;
        public String jumpTitle;
        public String states;
        public int serialNumber;
    }

    public static class IndexTabBean {
        /**
         * jumpContent : 047
         * id : 25
         * jumpType : 0
         * imgName : http://img08.iambuyer.com/imgup/upload/images/2017/11/06/PpVNf0AVRdqsF5evLk2RVvQe9L3W2iIJcI3TX65TjBQ9oEXtTNPK3hcjjc2F0PK6vutH6f.png
         * updateMan : admin
         * title : 分
         * updateTime : 1509957867000
         * jumpTitle : 清洁/保洁/洗涤
         * states : 0
         * serialNumber : 1
         */

        public String jumpContent;
        public int id;
        public String jumpType;
        public String imgName;
        public String updateMan;
        public String title;
        public long updateTime;
        public String jumpTitle;
        public String states;
        public int serialNumber;
    }

    public static class CarouselBean {
        /**
         * id : 3
         * content : e32r32
         * serialNumber : 3
         * imgName : http://img08.iambuyer.com/imgup/upload/images/2017/10/13/MQGjTYyi2ehYaD5bwnTciZcbIouvmtcD0KtbHu5qvfbRQwgEKg6Xxhcjjc2BaJURaXF3m1.jpg
         * states : 0
         * createMan : null
         * updateMan : null
         * loginType : 0
         * jumpType : 0
         * createTime : 1508915952000
         * updateTime : 1508915954000
         * jumpContent : 047
         */

        public int id;
        public String content;
        public int serialNumber;
        public String imgName;
        public String states;
        public Object createMan;
        public Object updateMan;
        public String loginType;
        public String jumpType;
        public long createTime;
        public long updateTime;
        public String jumpContent;
    }
}

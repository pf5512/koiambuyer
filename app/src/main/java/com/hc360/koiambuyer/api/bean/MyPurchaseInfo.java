package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/1
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class MyPurchaseInfo {


    /**
     * list : [{"id":801,"offerNumber":0,"prodNumber":"3","prodName":"多","prodTime":"2018-03-22 17:46:10","prodState":" 게시 됨"},{"id":800,"offerNumber":0,"prodNumber":"3","prodName":"摸","prodTime":"2018-03-22 17:40:48","prodState":" 게시 됨"},{"id":799,"offerNumber":0,"prodNumber":"3","prodName":"啊","prodTime":"2018-03-22 17:03:32","prodState":" 게시 됨"},{"id":798,"offerNumber":0,"prodNumber":"2","prodName":"的","prodTime":"2018-03-22 16:59:39","prodState":" 게시 됨"},{"id":785,"offerNumber":0,"prodNumber":"1","prodName":"呃呃呃","prodTime":"2018-03-22 10:19:06","prodState":" 게시 됨"},{"id":784,"offerNumber":0,"prodNumber":"1","prodName":"呢饿呃呃呃","prodTime":"2018-03-22 10:17:35","prodState":" 게시 됨"},{"id":743,"offerNumber":2,"prodNumber":"100","prodName":"jjc测试1111","prodTime":"2018-03-21 10:17:35","prodState":" 게시 됨"},{"id":742,"offerNumber":0,"prodNumber":"100","prodName":"jjc测试1111","prodTime":"2018-03-21 09:41:16","prodState":" 게시 됨"},{"id":740,"offerNumber":0,"prodNumber":"100","prodName":"jjc测试1111","prodTime":"2018-03-21 09:40:48","prodState":" 게시 됨"},{"id":737,"offerNumber":0,"prodNumber":"100","prodName":"jjc测试","prodTime":"2018-03-21 09:40:11","prodState":" 게시 됨"},{"id":735,"offerNumber":0,"prodNumber":"100","prodName":"jjc测试","prodTime":"2018-03-21 09:33:40","prodState":" 게시 됨"},{"id":732,"offerNumber":0,"prodNumber":"100","prodName":"jjc测试","prodTime":"2018-03-21 09:29:07","prodState":" 게시 됨"},{"id":730,"offerNumber":0,"prodNumber":"100","prodName":"jjc测试","prodTime":"2018-03-20 18:16:48","prodState":" 게시 됨"},{"id":729,"offerNumber":0,"prodNumber":"100","prodName":"jjc测试","prodTime":"2018-03-20 18:15:15","prodState":" 게시 됨"},{"id":728,"offerNumber":0,"prodNumber":"100","prodName":"jjc测试","prodTime":"2018-03-20 18:14:52","prodState":" 게시 됨"},{"id":727,"offerNumber":0,"prodNumber":"100","prodName":"jjc测试","prodTime":"2018-03-20 18:13:54","prodState":" 게시 됨"},{"id":726,"offerNumber":0,"prodNumber":"100","prodName":"jjc测试","prodTime":"2018-03-20 18:10:06","prodState":" 게시 됨"},{"id":725,"offerNumber":0,"prodNumber":"100","prodName":"jjc测试","prodTime":"2018-03-20 18:06:27","prodState":" 게시 됨"},{"id":724,"offerNumber":0,"prodNumber":"100","prodName":"jjc测试","prodTime":"2018-03-20 17:59:57","prodState":" 게시 됨"},{"id":704,"offerNumber":0,"prodNumber":"44444","prodName":"1","prodTime":"2018-03-08 21:34:13","prodState":" 게시 됨"},{"id":703,"offerNumber":0,"prodNumber":"44444","prodName":"1","prodTime":"2018-03-08 21:32:46","prodState":" 게시 됨"},{"id":702,"offerNumber":0,"prodNumber":"1","prodName":"1","prodTime":"2018-03-08 18:28:35","prodState":"취소 구매"},{"id":701,"offerNumber":0,"prodNumber":"44444","prodName":"1","prodTime":"2018-03-08 18:12:11","prodState":"진행중"},{"id":700,"offerNumber":0,"prodNumber":"44444","prodName":"1","prodTime":"2018-03-08 18:11:55","prodState":"거래끝났습니다"},{"id":699,"offerNumber":0,"prodNumber":"44444","prodName":"1","prodTime":"2018-03-08 18:06:46","prodState":" 게시 됨"},{"id":698,"offerNumber":0,"prodNumber":"44444","prodName":"1","prodTime":"2018-03-08 18:06:22","prodState":"거래끝났습니다"},{"id":697,"offerNumber":0,"prodNumber":"44444","prodName":"1","prodTime":"2018-03-08 18:04:56","prodState":"취소 구매"},{"id":696,"offerNumber":0,"prodNumber":"3","prodName":"干货","prodTime":"2018-03-08 17:17:24","prodState":" 게시 됨"}]
     */

    public List<ListBean> list;

    public static class ListBean {
        /**
         * id : 801
         * offerNumber : 0
         * prodNumber : 3
         * prodName : 多
         * prodTime : 2018-03-22 17:46:10
         * prodState :  게시 됨
         */

        public int id;
        public int offerNumber;
        public String prodNumber;
        public String prodStateStr;
        public String prodName;
        public String prodTime;
        public String prodState;
    }
}

package com.hc360.koiambuyer.api.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/31
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class PurchaseDetailInfo implements Serializable{

    /**
     * id : 743
     * prodNumber : 100
     * prodPrice : 100.21
     * prodImage2 : null
     * prodImage3 : null
     * prodName : jjc测试1111
     * prodTime : 2018-03-21 10:17:35
     * prodImage : http://img01.iambuyer.com/imgup/upload/images/2018/03/21/qDzVFQyw61nyS9qFbszJQLczhcjjc2BYXen6Jahcjjc2FQVE3Lihcjjc2FCwuf0DIiXJ7Ohcjjc2FGzIjP4UShZo.jpeg
     * productState : 0
     * proType : 测试---20180321
     * productUnit : 单位
     * productStateStr :  게시 됨
     * stProductParas : [{"paramName":"canshu","paramValue":"124"},{"paramName":"cab","paramValue":"455"}]
     * prodIntro : 挺好的
     * prodOfferCnt : 2
     * offerProdList : [{"id":263,"offerTime":"2018-03-09 16:16:02","offerProdImage":"http://img08.iambuyer.com/imgup/upload/images/2018/03/21/TNEkCJacsQBDNdWXdTI3AGLhbohcjjc2FwTctQ2YzV9mLqJY8Khcjjc2FXeu40q1QCrEVC4Uij6K.jpeg","offerProdName":"45","offerProdPrice":"0","offerProdStore":"10","offerPhone":"12341567489","offerContent":"111111111111111"},{"id":262,"offerTime":"2018-03-09 16:16:01","offerProdImage":"http://img01.iambuyer.com/imgup/upload/images/2018/03/09/ybm7TqxXRdPhcjjc2BxwRFJmkghcjjc2FPXy3dRWLUSIF1jnQF9rNy5LivvU1Jo7U6PwMjVtzjAn.jpeg","offerProdName":"34","offerProdPrice":"0","offerProdStore":"10","offerPhone":"12341567489","offerContent":"11"}]
     * productPriceMax : 200.12
     * productSampleCount : 11222
     */

    public int id;
    public String prodNumber;
    public BigDecimal prodPrice;
    public String prodImage2;
    public String prodImage3;
    public String prodName;
    public String prodTime;
    public String successTime;
    public String prodImage;
    public String productState;
    public String proType;
    public String productUnit;
    public String productStateStr;
    public String prodIntro;
    public int prodOfferCnt;
    public BigDecimal productPriceMax;
    public int productSampleCount;
    public List<StProductParasBean> stProductParas;
    public List<String> imgs;
    public List<OfferProdListBean> offerProdList;

    public static class StProductParasBean {
        /**
         * paramName : canshu
         * paramValue : 124
         */

        public String paramName;
        public String paramValue;

        public StProductParasBean(String paramName, String paramValue) {
            this.paramName = paramName;
            this.paramValue = paramValue;
        }
    }

    public static class OfferProdListBean {
        /**
         * id : 263
         * offerTime : 2018-03-09 16:16:02
         * offerProdImage : http://img08.iambuyer.com/imgup/upload/images/2018/03/21/TNEkCJacsQBDNdWXdTI3AGLhbohcjjc2FwTctQ2YzV9mLqJY8Khcjjc2FXeu40q1QCrEVC4Uij6K.jpeg
         * offerProdName : 45
         * offerProdPrice : 0
         * offerProdStore : 10
         * offerPhone : 12341567489
         * offerContent : 111111111111111
         */

        public int id;
        public String offerTime;
        public String offerProdImage;
        public String offerProdName;
        public String offerProdPrice;
        public String offerProdStore;
        public String offerPhone;
        public String offerContent;
        public String isIntention;
    }
}

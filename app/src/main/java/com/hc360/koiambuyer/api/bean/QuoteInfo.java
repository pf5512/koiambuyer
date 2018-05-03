package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/26
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class QuoteInfo {

    /**
     * id : 263
     * compName : 1111111
     * offerImg : http://img05.iambuyer.com/imgup/upload/images/2018/03/21/TNEkCJacsQBDNdWXdTI3AGLhbohcjjc2FwTctQ2YzV9mLqJY8Khcjjc2FXeu40q1QCrEVC4Uij6K.jpeg
     * offerImg2 : http://img06.iambuyer.com/imgup/upload/images/2018/03/21/TNEkCJacsQBDNdWXdTI3AGLhbohcjjc2FwTctQ2YzV9mLqJY8Khcjjc2FXeu40q1QCrEVC4Uij6K.jpeg
     * offerImg3 : http://img06.iambuyer.com/imgup/upload/images/2018/03/21/TNEkCJacsQBDNdWXdTI3AGLhbohcjjc2FwTctQ2YzV9mLqJY8Khcjjc2FXeu40q1QCrEVC4Uij6K.jpeg
     * aliImg : null
     * korStProductOfferB2bs : [{"id":399,"offerId":263,"b22Name":"1-50","b22NameKorea":"1","b22Value":"20元/台","b22ValueKorea":"1"},{"id":400,"offerId":263,"b22Name":"51-200","b22NameKorea":"1","b22Value":"30元/台","b22ValueKorea":"1"}]
     * createTime : 2018-03-09 16:16:02.0
     * offerType : 家电
     * offerProsize : 1
     * offerPacksize : 1
     * offerProweight : 1
     * offerProcolor : 1
     * sendTime : 1
     * offerPice : 1
     * offerCount : 1
     * offerContent : 111111111111111
     * offerName : 45
     * isSuccess : 1
     * isIntention : 1
     * offerProsizeUnit : mm
     * offerPacksizeUnit : mm
     * offerProweightUnit : g
     * offerCust : 1
     */

    public String id;
    public String compName;
    public String offerImg;
    public String offerImg2;
    public String offerImg3;
    public Object aliImg;
    public int offerStock;
    public String createTime;
    public String offerType;
    public String offerProsize;
    public String offerPacksize;
    public String offerProweight;
    public String offerProcolor;
    public String sendTime;
    public String offerPice;
    public String offerCount;
    public String offerContent;
    public String offerName;
    public String isSuccess;
    public String isIntention;
    public String offerProsizeUnit;
    public String offerPacksizeUnit;
    public String offerProweightUnit;
    public String offerCust;
    public List<KorStProductOfferB2bsBean> korStProductOfferB2bs;

    public static class KorStProductOfferB2bsBean {
        /**
         * id : 399
         * offerId : 263
         * b22Name : 1-50
         * b22NameKorea : 1
         * b22Value : 20元/台
         * b22ValueKorea : 1
         */

        public int id;
        public int offerId;
        public String b22Name;
        public String b22NameKorea;
        public String b22Value;
        public String b22ValueKorea;
    }
}

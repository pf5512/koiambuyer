package com.hc360.koiambuyer.api.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/20
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class GoodsDetailInfo {


    /**
     * content : {"unit":"个","comp":{"aName":"东城区","pName":"北京","compLogo":"http://img06.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","inName":"家电配件","compName":"企业名称","cName":"北京市","followComp":1},"sysCategoryStr":"CD盒","spProduct":{"userId":55,"productId":55,"productName":"张家林产品","loopImg001":"http://img04.iambuyer.com/imgup/upload/images/2017/10/20/nbcTt7DKByUvKhQysTNEL0D8P1S4WvYvaHpJEyTDjWYcZ7muIkLfdkRrBZG9Xhcjjc2BTZ.jpg","loopImg002":"http://img08.iambuyer.com/imgup/upload/images/2017/10/20/CBLp64QZO1OEykNuu0SMQ9wx5h5Jpbhcjjc2Fohcjjc2BHxHjC5lAwhcjjc2FfNIx1xcpgZNRzYlQMiZkX.jpg","loopImg003":"http://img01.iambuyer.com/imgup/upload/images/2017/10/20/YCmhcjjc2FjLOmQs8ia12cqXmmHdbeCDpX20vu6Yb1mQwRAOubij8yFbaL101M4ImWeqbe.jpg","loopImg004":"http://img01.iambuyer.com/imgup/upload/images/2017/10/20/yVqn22du9shcjjc2BLDP58BNVnZ3hcjjc2FlYSOOMh5Zn5hlbVxzR1MhUs5TJNzMbZXCc1gtcY00.jpg","loopImg005":"http://img01.iambuyer.com/imgup/upload/images/2017/10/20/zQjhcjjc2FlvB8ORRnKEbAN4UEU9xJBXJETQel1pOIVhja4n5t3nWlcMUUQMa46JGZ3pKP.jpg","thirdCateId":"001001001","secondCateId":"001001","deliverGoogsId":1,"providerId":1,"states":"0","createMan":"iambuyer","updateMan":"iambuyer","lookCount":0,"brandName":"品牌名称","isOnline":"0","isMade":"1","createTime":1508482453000,"updateTime":1508482453000,"shareCount":0,"recordCount":0,"productStates":null,"firstCateId":"001","fourthCateId":"","productPrice":"1","isHaveGoods":"0"},"SpProductPice":[{"productId":55,"price":5.1,"unit":"个","states":"0","priceId":149,"minNumber":30,"maxNumber":40},{"productId":55,"price":4.1,"unit":"个","states":"0","priceId":148,"minNumber":10,"maxNumber":20},{"productId":55,"price":2.1,"unit":"个","states":"0","priceId":147,"minNumber":1,"maxNumber":10}],"goodsDeliverAddrStr":"北京北京市","goodsDeliverAddrInfoStr":"北京 北京市 东城区","spProductImgS":[{"id":180,"imgName":"http://img04.iambuyer.com/imgup/upload/images/2017/10/20/4RcElGLfCV5u7IyGPezvGTXF0Ey1eUitLP70B5mhcjjc2BJwGEkdCfNCyA0p2Xhcjjc2FtqZ8pXK.jpg","productId":55,"createTime":1508482454000},{"id":179,"imgName":"http://img07.iambuyer.com/imgup/upload/images/2017/10/20/YVnbhcjjc2F0hcjjc2Fohcjjc2FW3Z9j7Ovq2IgWn4GWvSbEm70tE3jOVUV5O941RNgFUhTgH9CqpPKEit.jpg","productId":55,"createTime":1508482454000},{"id":178,"imgName":"http://img08.iambuyer.com/imgup/upload/images/2017/10/20/MUiHygAzCKVwMarJMk9AwdTk08hcjjc2BpoCo3j291ggBaHS4oc4dH7iEFPwTPloaHtjFJ.jpg","productId":55,"createTime":1508482454000},{"id":177,"imgName":"http://img01.iambuyer.com/imgup/upload/images/2017/10/20/ABtRmuyHM95FhAkjXfadAoMSeoghcjjc2BnSjoVrWELXQfee0PCd8JZ8dJ1c1KeMmGaRfs.jpg","productId":55,"createTime":1508482454000}],"user":{"wxCode":"11111","compId":1,"userHeadImg":"http://img02.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","compLogo":"JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","compName":"企业名称","qqCode":"123456","isCust":"0","cName":"北京市","loginType":"1","aName":"东城区","pName":"北京","refuseReason":"老子不背锅,自动@张佳林\n","cUserId":55,"inName":"家电配件","userId":55,"userName":"www","checkState":"2","userPosition":"销售经理"},"minPice":2.1,"spProductIntro":{"img001":null,"img003":null,"img002":null,"img004":null,"img005":null,"productId":55,"states":"0","introId":51,"img006":null,"img007":null,"img008":null,"img009":null,"img010":null,"productIntro":"你好啊测试----"}}
     * ret : 200
     * msg : success
     */

    public ContentBean content;
    public String ret;
    public String msg;

    public static class ContentBean implements Serializable{
        /**
         * unit : 个
         * comp : {"aName":"东城区","pName":"北京","compLogo":"http://img06.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","inName":"家电配件","compName":"企业名称","cName":"北京市","followComp":1}
         * sysCategoryStr : CD盒
         * spProduct : {"userId":55,"productId":55,"productName":"张家林产品","loopImg001":"http://img04.iambuyer.com/imgup/upload/images/2017/10/20/nbcTt7DKByUvKhQysTNEL0D8P1S4WvYvaHpJEyTDjWYcZ7muIkLfdkRrBZG9Xhcjjc2BTZ.jpg","loopImg002":"http://img08.iambuyer.com/imgup/upload/images/2017/10/20/CBLp64QZO1OEykNuu0SMQ9wx5h5Jpbhcjjc2Fohcjjc2BHxHjC5lAwhcjjc2FfNIx1xcpgZNRzYlQMiZkX.jpg","loopImg003":"http://img01.iambuyer.com/imgup/upload/images/2017/10/20/YCmhcjjc2FjLOmQs8ia12cqXmmHdbeCDpX20vu6Yb1mQwRAOubij8yFbaL101M4ImWeqbe.jpg","loopImg004":"http://img01.iambuyer.com/imgup/upload/images/2017/10/20/yVqn22du9shcjjc2BLDP58BNVnZ3hcjjc2FlYSOOMh5Zn5hlbVxzR1MhUs5TJNzMbZXCc1gtcY00.jpg","loopImg005":"http://img01.iambuyer.com/imgup/upload/images/2017/10/20/zQjhcjjc2FlvB8ORRnKEbAN4UEU9xJBXJETQel1pOIVhja4n5t3nWlcMUUQMa46JGZ3pKP.jpg","thirdCateId":"001001001","secondCateId":"001001","deliverGoogsId":1,"providerId":1,"states":"0","createMan":"iambuyer","updateMan":"iambuyer","lookCount":0,"brandName":"品牌名称","isOnline":"0","isMade":"1","createTime":1508482453000,"updateTime":1508482453000,"shareCount":0,"recordCount":0,"productStates":null,"firstCateId":"001","fourthCateId":"","productPrice":"1","isHaveGoods":"0"}
         * SpProductPice : [{"productId":55,"price":5.1,"unit":"个","states":"0","priceId":149,"minNumber":30,"maxNumber":40},{"productId":55,"price":4.1,"unit":"个","states":"0","priceId":148,"minNumber":10,"maxNumber":20},{"productId":55,"price":2.1,"unit":"个","states":"0","priceId":147,"minNumber":1,"maxNumber":10}]
         * goodsDeliverAddrStr : 北京北京市
         * goodsDeliverAddrInfoStr : 北京 北京市 东城区
         * spProductImgS : [{"id":180,"imgName":"http://img04.iambuyer.com/imgup/upload/images/2017/10/20/4RcElGLfCV5u7IyGPezvGTXF0Ey1eUitLP70B5mhcjjc2BJwGEkdCfNCyA0p2Xhcjjc2FtqZ8pXK.jpg","productId":55,"createTime":1508482454000},{"id":179,"imgName":"http://img07.iambuyer.com/imgup/upload/images/2017/10/20/YVnbhcjjc2F0hcjjc2Fohcjjc2FW3Z9j7Ovq2IgWn4GWvSbEm70tE3jOVUV5O941RNgFUhTgH9CqpPKEit.jpg","productId":55,"createTime":1508482454000},{"id":178,"imgName":"http://img08.iambuyer.com/imgup/upload/images/2017/10/20/MUiHygAzCKVwMarJMk9AwdTk08hcjjc2BpoCo3j291ggBaHS4oc4dH7iEFPwTPloaHtjFJ.jpg","productId":55,"createTime":1508482454000},{"id":177,"imgName":"http://img01.iambuyer.com/imgup/upload/images/2017/10/20/ABtRmuyHM95FhAkjXfadAoMSeoghcjjc2BnSjoVrWELXQfee0PCd8JZ8dJ1c1KeMmGaRfs.jpg","productId":55,"createTime":1508482454000}]
         * user : {"wxCode":"11111","compId":1,"userHeadImg":"http://img02.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","compLogo":"JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","compName":"企业名称","qqCode":"123456","isCust":"0","cName":"北京市","loginType":"1","aName":"东城区","pName":"北京","refuseReason":"老子不背锅,自动@张佳林\n","cUserId":55,"inName":"家电配件","userId":55,"userName":"www","checkState":"2","userPosition":"销售经理"}
         * minPice : 2.1
         * spProductIntro : {"img001":null,"img003":null,"img002":null,"img004":null,"img005":null,"productId":55,"states":"0","introId":51,"img006":null,"img007":null,"img008":null,"img009":null,"img010":null,"productIntro":"你好啊测试----"}
         */

        public String unit;
        public CompBean comp;
        public String sysCategoryStr;
        public SpProductBean spProduct;
        public String goodsDeliverAddrStr;
        public String goodsDeliverAddrInfoStr;
        public UserBean user;
        public double minPice;
        public SpProductIntroBean spProductIntro;
        public List<SpProductPiceBean> SpProductPice;
        public List<SpProductImgSBean> spProductImgS;

        public static class CompBean {
            /**
             * aName : 东城区
             * pName : 北京
             * compLogo : http://img06.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg
             * inName : 家电配件
             * compName : 企业名称
             * cName : 北京市
             * followComp : 1
             */

            public String aName;
            public String pName;
            public String compLogo;
            public String inName;
            public String compName;
            public String compShortName;
            public String cName;
            public int followComp;
        }

        public static class SpProductBean {
            /**
             * userId : 55
             * productId : 55
             * productName : 张家林产品
             * loopImg001 : http://img04.iambuyer.com/imgup/upload/images/2017/10/20/nbcTt7DKByUvKhQysTNEL0D8P1S4WvYvaHpJEyTDjWYcZ7muIkLfdkRrBZG9Xhcjjc2BTZ.jpg
             * loopImg002 : http://img08.iambuyer.com/imgup/upload/images/2017/10/20/CBLp64QZO1OEykNuu0SMQ9wx5h5Jpbhcjjc2Fohcjjc2BHxHjC5lAwhcjjc2FfNIx1xcpgZNRzYlQMiZkX.jpg
             * loopImg003 : http://img01.iambuyer.com/imgup/upload/images/2017/10/20/YCmhcjjc2FjLOmQs8ia12cqXmmHdbeCDpX20vu6Yb1mQwRAOubij8yFbaL101M4ImWeqbe.jpg
             * loopImg004 : http://img01.iambuyer.com/imgup/upload/images/2017/10/20/yVqn22du9shcjjc2BLDP58BNVnZ3hcjjc2FlYSOOMh5Zn5hlbVxzR1MhUs5TJNzMbZXCc1gtcY00.jpg
             * loopImg005 : http://img01.iambuyer.com/imgup/upload/images/2017/10/20/zQjhcjjc2FlvB8ORRnKEbAN4UEU9xJBXJETQel1pOIVhja4n5t3nWlcMUUQMa46JGZ3pKP.jpg
             * thirdCateId : 001001001
             * secondCateId : 001001
             * deliverGoogsId : 1
             * providerId : 1
             * states : 0
             * createMan : iambuyer
             * updateMan : iambuyer
             * lookCount : 0
             * brandName : 品牌名称
             * isOnline : 0
             * isMade : 1
             * createTime : 1508482453000
             * updateTime : 1508482453000
             * shareCount : 0
             * recordCount : 0
             * productStates : null
             * firstCateId : 001
             * fourthCateId :
             * productPrice : 1
             * isHaveGoods : 0
             */

            public int userId;
            public int productId;
            public String productName;
            public String loopImg001;
            public String loopImg002;
            public String loopImg003;
            public String loopImg004;
            public String loopImg005;
            public String thirdCateId;
            public String secondCateId;
            public int deliverGoogsId;
            public int providerId;
            public String states;
            public String createMan;
            public String updateMan;
            public int lookCount;
            public String brandName;
            public String isOnline;
            public String isMade;
            public long createTime;
            public long updateTime;
            public int shareCount;
            public int recordCount;
            public Object productStates;
            public String firstCateId;
            public String fourthCateId;
            public String productPrice;
            public String isHaveGoods;
        }

        public static class UserBean {
            /**
             * wxCode : 11111
             * compId : 1
             * userHeadImg : http://img02.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg
             * compLogo : JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg
             * compName : 企业名称
             * qqCode : 123456
             * isCust : 0
             * cName : 北京市
             * loginType : 1
             * aName : 东城区
             * pName : 北京
             * refuseReason : 老子不背锅,自动@张佳林

             * cUserId : 55
             * inName : 家电配件
             * userId : 55
             * userName : www
             * checkState : 2
             * userPosition : 销售经理
             */

            public String wxCode;
            public int compId;
            public String userHeadImg;
            public String compLogo;
            public String compName;
            public String qqCode;
            public String isCust;
            public String cName;
            public String loginType;
            public String aName;
            public String pName;
            public String refuseReason;
            public int cUserId;
            public String inName;
            public int userId;
            public String userName;
            public String checkState;
            public String userPosition;
        }

        public static class SpProductIntroBean {
            /**
             * img001 : null
             * img003 : null
             * img002 : null
             * img004 : null
             * img005 : null
             * productId : 55
             * states : 0
             * introId : 51
             * img006 : null
             * img007 : null
             * img008 : null
             * img009 : null
             * img010 : null
             * productIntro : 你好啊测试----
             */

            public String img001;
            public String img003;
            public String img002;
            public String img004;
            public String img005;
            public int productId;
            public String states;
            public int introId;
            public String img006;
            public String img007;
            public String img008;
            public String img009;
            public String img010;
            public String productIntro;
        }

        public static class SpProductPiceBean implements Serializable{
            /**
             * productId : 55
             * price : 5.1
             * unit : 个
             * states : 0
             * priceId : 149
             * minNumber : 30
             * maxNumber : 40
             */

            public int productId;
            public BigDecimal price = new BigDecimal(0);
            public String unit;
            public String states;
            public int priceId;
            public int minNumber;
            public int maxNumber;

            @Override
            public String toString() {
                return "SpProductPiceBean{" +
                        "price=" + price +
                        ", minNumber=" + minNumber +
                        ", maxNumber=" + maxNumber +
                        ", unit='" + unit + '\'' +
                        '}';
            }
        }

        public static class SpProductImgSBean {
            /**
             * id : 180
             * imgName : http://img04.iambuyer.com/imgup/upload/images/2017/10/20/4RcElGLfCV5u7IyGPezvGTXF0Ey1eUitLP70B5mhcjjc2BJwGEkdCfNCyA0p2Xhcjjc2FtqZ8pXK.jpg
             * productId : 55
             * createTime : 1508482454000
             */

            public int id;
            public String imgName;
            public int productId;
            public long createTime;
        }
    }
}

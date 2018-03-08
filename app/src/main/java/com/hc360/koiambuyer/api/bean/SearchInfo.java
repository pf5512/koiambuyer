package com.hc360.koiambuyer.api.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/2
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class SearchInfo {


    /**
     * count : 11
     * list : [{"typeName":null,"userName":"姜家成1","userPosition":"销售经理","userHeadImg":"http://img04.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","compName":"姜家成股份有限公司","addStr":"北京 北京市 东城区","priceList":null,"intro":null,"imgs":null,"minPrice":2.1,"sysCategoryStr":"CD盒","loopImg001":null,"loopImg002":null,"loopImg003":null,"loopImg004":null,"loopImg005":null,"thirdCateId":"001001001","secondCateId":"001001","deliverGoogsId":1,"providerId":1,"userId":51,"productId":5,"states":"0","createMan":"iambuyer","updateMan":"iambuyer","lookCount":2,"brandName":"品牌名称","isOnline":"1","isMade":"1","createTime":1508288234000,"updateTime":1508288234000,"shareCount":0,"recordCount":0,"productStates":null,"productName":"产品名称","firstCateId":"001","fourthCateId":"","productPrice":"1","isHaveGoods":"0"},{"typeName":null,"userName":"姜家成1","userPosition":"销售经理","userHeadImg":"http://img04.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","compName":"姜家成股份有限公司","addStr":"北京 北京市 东城区","priceList":null,"intro":null,"imgs":null,"minPrice":2.1,"sysCategoryStr":"CD盒","loopImg001":null,"loopImg002":null,"loopImg003":null,"loopImg004":null,"loopImg005":null,"thirdCateId":"001001001","secondCateId":"001001","deliverGoogsId":1,"providerId":1,"userId":51,"productId":6,"states":"0","createMan":"iambuyer","updateMan":"iambuyer","lookCount":0,"brandName":"品牌名称","isOnline":"1","isMade":"1","createTime":1508288245000,"updateTime":1508288245000,"shareCount":0,"recordCount":1,"productStates":null,"productName":"产品名称","firstCateId":"001","fourthCateId":"","productPrice":"1","isHaveGoods":"0"},{"typeName":null,"userName":"姜家成221","userPosition":"销售经理","userHeadImg":"http://img02.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","compName":"姜家成股份有限公司","addStr":"北京 北京市 东城区","priceList":null,"intro":null,"imgs":null,"minPrice":2.1,"sysCategoryStr":"CD盒","loopImg001":"http://img02.iambuyer.com/imgup/upload/images/2017/10/20/21tHAT3i4POBmmgGqAhcjjc2FCAq33GxKhat123qvE5b0jptnhpLXz94rD9IfT7ZofwcQA.jpg","loopImg002":"http://img03.iambuyer.com/imgup/upload/images/2017/10/20/5LQf8PLaCeAMHrfZj9RvvFMvd76gTu3GMjuFaDYICMFMaP9Mp5AncxHuuzpfjqmS.jpg","loopImg003":"http://img01.iambuyer.com/imgup/upload/images/2017/10/20/YSX9nT3rxon5UntczTRwCE1HyIXnokjaIpfoXvanGTs3MwYhcjjc2BJ5z32SixOT9QLlwu.jpg","loopImg004":"http://img01.iambuyer.com/imgup/upload/images/2017/10/20/3ZKFfm4pCg628K8ykHqr3r6raj2doohIob70Jsz1yVIx6weS5oTkahcjjc2B5jdKgtmc7A.jpg","loopImg005":"http://img01.iambuyer.com/imgup/upload/images/2017/10/20/HXHaCDhfcjeyihcjjc2FCHFMCofMvZHq8Wvc0QiHhcjjc2BTNicWUF7o91pTbvnrLlgNEZw6nsVF.jpg","thirdCateId":"001001001","secondCateId":"001001","deliverGoogsId":1,"providerId":1,"userId":55,"productId":45,"states":"0","createMan":"iambuyer","updateMan":"iambuyer","lookCount":0,"brandName":"品牌名称","isOnline":"1","isMade":"1","createTime":1508482437000,"updateTime":1508482437000,"shareCount":0,"recordCount":0,"productStates":null,"productName":"张家林产品","firstCateId":"001","fourthCateId":"","productPrice":"1","isHaveGoods":"0"},{"typeName":null,"userName":"姜家成221","userPosition":"销售经理","userHeadImg":"http://img04.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","compName":"姜家成股份有限公司","addStr":"北京 北京市 东城区","priceList":null,"intro":null,"imgs":null,"minPrice":2.1,"sysCategoryStr":"CD盒","loopImg001":"http://img03.iambuyer.com/imgup/upload/images/2017/10/20/UeO4JYZRMVw4bDXuPhcjjc2FVdlP0qyrldN7hcjjc2Bf68sF3F9JcoD79Q2tLl6Thl2Ik45qGhcjjc2BoK.jpg","loopImg002":"http://img01.iambuyer.com/imgup/upload/images/2017/10/20/NwJZKIGTOgK7cFpmpAcZg8v1QWDlbWyhskhv7U1IIW2VykKQI72mhcjjc2BQXVle9hPqWc.jpg","loopImg003":"http://img06.iambuyer.com/imgup/upload/images/2017/10/20/hcjjc2Btb8m2xLie5OIfooo04ws5Lnthcjjc2BtlwtJ2HXRhuA0RjL9zL1g2ET7NsOlvfANpbEhcjjc2B3.jpg","loopImg004":"http://img08.iambuyer.com/imgup/upload/images/2017/10/20/qn1j3S8JBD7VJ9Dk5vEsy0eaipbH1ah5Q8ljSfIIk0Kks4XRAKfvQ2DibnFP9SrQ.jpg","loopImg005":"http://img06.iambuyer.com/imgup/upload/images/2017/10/20/Ti36hcjjc2FdEmnNIRl31tzfsVAfChcjjc2B22YDaxroeKgnhNOvsrfM8v8JFrDAthcjjc2F1YErtvBbuq.jpg","thirdCateId":"001001001","secondCateId":"001001","deliverGoogsId":1,"providerId":1,"userId":55,"productId":46,"states":"0","createMan":"iambuyer","updateMan":"iambuyer","lookCount":0,"brandName":"品牌名称","isOnline":"1","isMade":"1","createTime":1508482438000,"updateTime":1508482438000,"shareCount":0,"recordCount":0,"productStates":null,"productName":"张家林产品","firstCateId":"001","fourthCateId":"","productPrice":"1","isHaveGoods":"0"},{"typeName":null,"userName":"姜家成221","userPosition":"销售经理","userHeadImg":"http://img03.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","compName":"姜家成股份有限公司","addStr":"北京 北京市 东城区","priceList":null,"intro":null,"imgs":null,"minPrice":2.1,"sysCategoryStr":"CD盒","loopImg001":"http://img04.iambuyer.com/imgup/upload/images/2017/10/20/cCsx6mkPea8ZTpTy5HixOgO4Rn332Jvzvhcjjc2BdooigzfArQ4fWeQgqE8cNwZuqhcjjc2FMA9a.jpg","loopImg002":"http://img08.iambuyer.com/imgup/upload/images/2017/10/20/f5115BxHdjwLhcjjc2FoM8tFDbEtI6XgiqMAjkgQgeMThcjjc2BC6np78fGr4AQThcjjc2FIodKjQLylLa.jpg","loopImg003":"http://img08.iambuyer.com/imgup/upload/images/2017/10/20/Fhcjjc2Bs36qXGrEzyfS6uHxbKhyZvPQA7ZHMqg8jeh2FrybeW3O7lG1zhihcjjc2FlJW31TRcfi.jpg","loopImg004":"http://img07.iambuyer.com/imgup/upload/images/2017/10/20/qshcjjc2Ft4wEtYzFLgJavTiulMVfTCFG3LMKl4QjLD3nUUhWGe01BjZRIPcQJh1xwMO7F.jpg","loopImg005":"http://img06.iambuyer.com/imgup/upload/images/2017/10/20/hcjjc2BtQfu5h7CnH4D9Mpg7qSGCdCqBnNAowX91JSwxJld3vRWhs9V58b0bII3Kk9JLIW.jpg","thirdCateId":"001001001","secondCateId":"001001","deliverGoogsId":1,"providerId":1,"userId":55,"productId":47,"states":"0","createMan":"iambuyer","updateMan":"iambuyer","lookCount":0,"brandName":"品牌名称","isOnline":"1","isMade":"1","createTime":1508482439000,"updateTime":1508482439000,"shareCount":0,"recordCount":0,"productStates":null,"productName":"张家林产品","firstCateId":"001","fourthCateId":"","productPrice":"1","isHaveGoods":"0"},{"typeName":null,"userName":"姜家成221","userPosition":"销售经理","userHeadImg":"http://img05.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","compName":"姜家成股份有限公司","addStr":"北京 北京市 东城区","priceList":null,"intro":null,"imgs":null,"minPrice":2.1,"sysCategoryStr":"CD盒","loopImg001":"http://img08.iambuyer.com/imgup/upload/images/2017/10/20/ZnClphcjjc2FdQfyWuD6LwChcjjc2B1jcsfM9NzZY6WBplRhpz60op9cjXXPzcic4UjGAnj55zj6.jpg","loopImg002":"http://img01.iambuyer.com/imgup/upload/images/2017/10/20/iHsre0odUqCfPwCOMHBz9TQqVf3jzTwyO82taSU8vhllUSZSMXHE6HgfLM1Vn2k4.jpg","loopImg003":"http://img01.iambuyer.com/imgup/upload/images/2017/10/20/FTluDsdaUB9gpVGMbJAhvORea32btPxYu8OYek0hcjjc2BJzJQhcjjc2BlDUK33daZhcjjc2FI2yWR6mUu.jpg","loopImg004":"http://img08.iambuyer.com/imgup/upload/images/2017/10/20/CQJ40nu7LOZo1IOD1o6NH7qqdsXLYWj91kkTjj3wfN6Dz3fhcjjc2FKyOAXSX5EaXA5vCS.jpg","loopImg005":"http://img03.iambuyer.com/imgup/upload/images/2017/10/20/irSfqdfTSVf1Gk7vw6ttbB1sWLZvYwhBKYJhusz46dJTbHohcjjc2BoKeWLGI4E0o4p6H6.jpg","thirdCateId":"001001001","secondCateId":"001001","deliverGoogsId":1,"providerId":1,"userId":55,"productId":48,"states":"0","createMan":"iambuyer","updateMan":"iambuyer","lookCount":0,"brandName":"品牌名称","isOnline":"1","isMade":"1","createTime":1508482440000,"updateTime":1508482440000,"shareCount":0,"recordCount":0,"productStates":null,"productName":"张家林产品","firstCateId":"001","fourthCateId":"","productPrice":"1","isHaveGoods":"0"},{"typeName":null,"userName":"姜家成221","userPosition":"销售经理","userHeadImg":"http://img03.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","compName":"姜家成股份有限公司","addStr":"北京 北京市 东城区","priceList":null,"intro":null,"imgs":null,"minPrice":2.1,"sysCategoryStr":"CD盒","loopImg001":"http://img05.iambuyer.com/imgup/upload/images/2017/10/20/aAXs2keihjrJbt2ce0OUVaKMZUqBj9f81Bsglolg7KkV5hcjjc2FAwNgOQ5lsYn337XAvX.jpg","loopImg002":"http://img02.iambuyer.com/imgup/upload/images/2017/10/20/fpIZbsyWykNJxfHPwMHJAhcjjc2BbNNtihcjjc2F7p9zW0Kbvhcjjc2FSoYNeMxErJCrhcjjc2FZZ8mAlIJrXuZx.jpg","loopImg003":"http://img04.iambuyer.com/imgup/upload/images/2017/10/20/llsCFlmr3NEEGplLSBhe2OVHlGnW9QN5ahcjjc2FycrA8uaDTnnU2iiW4pAQ1RMIJYR1hcjjc2FU.jpg","loopImg004":"http://img02.iambuyer.com/imgup/upload/images/2017/10/20/htF0YaJEJ7hcjjc2BcrEEbK1nxUTViWiOYSg9FkJwNsrOpoZHdgPmeqf4sClGNYbspJzVe.jpg","loopImg005":"http://img04.iambuyer.com/imgup/upload/images/2017/10/20/AIP1mTbuanxTOTAK1CYssfy9tEY0Ihcjjc2Bwql57wA48QKBllRnZdF8VFsta4UIMrQpFi.jpg","thirdCateId":"001001001","secondCateId":"001001","deliverGoogsId":1,"providerId":1,"userId":55,"productId":49,"states":"0","createMan":"iambuyer","updateMan":"iambuyer","lookCount":0,"brandName":"品牌名称","isOnline":"1","isMade":"1","createTime":1508482441000,"updateTime":1508482441000,"shareCount":0,"recordCount":0,"productStates":null,"productName":"张家林产品","firstCateId":"001","fourthCateId":"","productPrice":"1","isHaveGoods":"0"},{"typeName":null,"userName":"姜家成221","userPosition":"销售经理","userHeadImg":"http://img05.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","compName":"姜家成股份有限公司","addStr":"北京 北京市 东城区","priceList":null,"intro":null,"imgs":null,"minPrice":2.1,"sysCategoryStr":"CD盒","loopImg001":"http://img06.iambuyer.com/imgup/upload/images/2017/10/20/Nv7fqklIrlMbTsiD8eqJdxfdCX59IkX6ep8GBrQm5hcjjc2FZcv3XY9EkmI2Sy6yCTepJH.jpg","loopImg002":"http://img06.iambuyer.com/imgup/upload/images/2017/10/20/QxYkdKUfOV37vx5DSnIU2lQ3NWwwVRO09zuJ402Ihcjjc2BRVVnVADFAgtzoP7fGcrka36.jpg","loopImg003":"http://img06.iambuyer.com/imgup/upload/images/2017/10/20/tTzgFOJQqBLylnKWk9qI0ZogyatDrbAKqIfeXTVBOHYsjYKJfzVrHrII4Z9EhPxf.jpg","loopImg004":"http://img02.iambuyer.com/imgup/upload/images/2017/10/20/w2gzQjRcfbteGhBCrpYgehcjjc2BiUU1wneiJZlVCGpjSWcouy0MPH83CgZPv9aoCBsgDj.jpg","loopImg005":"http://img01.iambuyer.com/imgup/upload/images/2017/10/20/lMSFCkCwnfzlfJu0xmC4lZ8S0tVNjnnB0hZgf2RFl7A4wBlOEDKh54W4mhcjjc2BhQqtKq.jpg","thirdCateId":"001001001","secondCateId":"001001","deliverGoogsId":1,"providerId":1,"userId":55,"productId":50,"states":"0","createMan":"iambuyer","updateMan":"iambuyer","lookCount":0,"brandName":"品牌名称","isOnline":"1","isMade":"1","createTime":1508482442000,"updateTime":1508482442000,"shareCount":0,"recordCount":0,"productStates":null,"productName":"张家林产品","firstCateId":"001","fourthCateId":"","productPrice":"1","isHaveGoods":"0"},{"typeName":null,"userName":"姜家成221","userPosition":"销售经理","userHeadImg":"http://img03.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","compName":"姜家成股份有限公司","addStr":"北京 北京市 东城区","priceList":null,"intro":null,"imgs":null,"minPrice":2.1,"sysCategoryStr":"CD盒","loopImg001":"http://img08.iambuyer.com/imgup/upload/images/2017/10/20/aIIKgGhcjjc2FjWWDsdpn2rxh85tqm7UHPzty6hcjjc2Bb4Yy0WMcySTpXaqpoU2XwIWMZ7a1znU.jpg","loopImg002":"http://img08.iambuyer.com/imgup/upload/images/2017/10/20/DBFXrxCcuA3LiPwTWNWG8N7kNXl2rqLEHGjqW7cO2PwWQUQTc9besbrGLnsFAImX.jpg","loopImg003":"http://img04.iambuyer.com/imgup/upload/images/2017/10/20/5Vhcjjc2FRfTOLax5K8XV1psOlLhcjjc2B8oxCx3O689j3hcjjc2Bc7PlQpNwWaoLFenL5FOW189geDCvg.jpg","loopImg004":"http://img04.iambuyer.com/imgup/upload/images/2017/10/20/AVOPnJJftVSBOLASCugls5OpDhVUoVjHCQONH1mT68SLhHQJR9Mc8YJUNXLEPZiP.jpg","loopImg005":"http://img03.iambuyer.com/imgup/upload/images/2017/10/20/0bYPnBzumhcjjc2FNH7zOxhNPEpJhcjjc2FAc7kh1iPH64wQgiZiHC9EVdBefUsZvT3J3G1pTsD0.jpg","thirdCateId":"001001001","secondCateId":"001001","deliverGoogsId":1,"providerId":1,"userId":55,"productId":51,"states":"0","createMan":"iambuyer","updateMan":"iambuyer","lookCount":0,"brandName":"品牌名称","isOnline":"1","isMade":"1","createTime":1508482443000,"updateTime":1508482443000,"shareCount":0,"recordCount":0,"productStates":null,"productName":"张家林产品","firstCateId":"001","fourthCateId":"","productPrice":"1","isHaveGoods":"0"},{"typeName":null,"userName":"姜家成221","userPosition":"销售经理","userHeadImg":"http://img05.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","compName":"姜家成股份有限公司","addStr":"北京 北京市 东城区","priceList":null,"intro":null,"imgs":null,"minPrice":2.1,"sysCategoryStr":"CD盒","loopImg001":"http://img03.iambuyer.com/imgup/upload/images/2017/10/20/AtmOshYCWZ8B8xhcjjc2BfjfbQ4jVP4rJk3gAAsJ25eJFUAofXsemvgLY1FO3aUQDAhoFl.jpg","loopImg002":"http://img02.iambuyer.com/imgup/upload/images/2017/10/20/tOhcjjc2BMpx2ix8idYqB11jTIPCVDoyxzoQdcQhcjjc2Bhcjjc2Ft37rIIiz18hcjjc2BcKVUEgk0Rjx5qLc4jk.jpg","loopImg003":"http://img07.iambuyer.com/imgup/upload/images/2017/10/20/Y0txU8gOyKyyblCopn877SzX3dOgCMRfC6bzeCTa2cja6hcjjc2FZLxaTD2bgw3f15anwd.jpg","loopImg004":"http://img02.iambuyer.com/imgup/upload/images/2017/10/20/HtwiGSYqrl9MWsLsFkuSQStvagXB1WvjEUqke5cZYtJgJfFpQA0KijR3L87WGA3R.jpg","loopImg005":"http://img06.iambuyer.com/imgup/upload/images/2017/10/20/LOWIUhcjjc2BZznjZ0EkGziB2hcjjc2Fxx2TjlFeJwVaE6IeawouNNZmuTo60cUIG55XHWDP7lMhcjjc2F.jpg","thirdCateId":"001001001","secondCateId":"001001","deliverGoogsId":1,"providerId":1,"userId":55,"productId":52,"states":"0","createMan":"iambuyer","updateMan":"iambuyer","lookCount":0,"brandName":"品牌名称","isOnline":"1","isMade":"1","createTime":1508482444000,"updateTime":1508482444000,"shareCount":0,"recordCount":0,"productStates":null,"productName":"张家林产品","firstCateId":"001","fourthCateId":"","productPrice":"1","isHaveGoods":"0"}]
     */

    public int count;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * typeName : null
         * userName : 姜家成1
         * userPosition : 销售经理
         * userHeadImg : http://img04.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg
         * compName : 姜家成股份有限公司
         * addStr : 北京 北京市 东城区
         * priceList : null
         * intro : null
         * imgs : null
         * minPrice : 2.1
         * sysCategoryStr : CD盒
         * loopImg001 : null
         * loopImg002 : null
         * loopImg003 : null
         * loopImg004 : null
         * loopImg005 : null
         * thirdCateId : 001001001
         * secondCateId : 001001
         * deliverGoogsId : 1
         * providerId : 1
         * userId : 51
         * productId : 5
         * states : 0
         * createMan : iambuyer
         * updateMan : iambuyer
         * lookCount : 2
         * brandName : 品牌名称
         * isOnline : 1
         * isMade : 1
         * createTime : 1508288234000
         * updateTime : 1508288234000
         * shareCount : 0
         * recordCount : 0
         * productStates : null
         * productName : 产品名称
         * firstCateId : 001
         * fourthCateId :
         * productPrice : 1
         * isHaveGoods : 0
         */

        public Object typeName;
        public String userName;
        public String userPosition;
        public String userHeadImg;
        public String compName;
        public String addStr;
        public Object priceList;
        public Object intro;
        public Object imgs;
        public BigDecimal minPrice;
        public String sysCategoryStr;
        public Object loopImg001;
        public Object loopImg002;
        public Object loopImg003;
        public Object loopImg004;
        public Object loopImg005;
        public String thirdCateId;
        public String secondCateId;
        public int deliverGoogsId;
        public int providerId;
        public int userId;
        public int productId;
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
        public String productName;
        public String firstCateId;
        public String fourthCateId;
        public String productPrice;
        public String isHaveGoods;
    }
}

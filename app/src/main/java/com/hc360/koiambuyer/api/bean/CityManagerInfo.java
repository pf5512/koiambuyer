package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/7
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class CityManagerInfo {

    /**
     * count : 2
     * list : [{"manageName":"11112333","manageId":"66","managePhone":"18534597725","manageHeadImg":"http://iambuyer.com/img/head00.jpg","manageCity":null,"manageNickName":"11112333 [18534597725]"},{"manageName":"zhaojie","manageId":"396","managePhone":"18401429226","manageHeadImg":"nDx5IbdMHUhcjjc2FSqn6pVftC9hcjjc2F4nfhcjjc2FKMHMOChcjjc2B5dxeRqfXXkkRCFhcjjc2FQ4spfTgxp81ZsTax.jpeg","manageCity":null,"manageNickName":"zhaojie [18401429226]"}]
     */

    public int count;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * manageName : 11112333
         * manageId : 66
         * managePhone : 18534597725
         * manageHeadImg : http://iambuyer.com/img/head00.jpg
         * manageCity : null
         * manageNickName : 11112333 [18534597725]
         */

        public String manageName;
        public String manageId;
        public String managePhone;
        public String manageHeadImg;
        public Object manageCity;
        public String manageNickName;
    }
}

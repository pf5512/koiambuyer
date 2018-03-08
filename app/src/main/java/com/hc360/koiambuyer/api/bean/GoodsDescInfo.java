package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/23
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class GoodsDescInfo {


    public ContentBean content;
    public String ret;
    public String msg;

    public static class ContentBean {

        public SpProductIntroBean spProductIntro;
        public List<SpProductImgSBean> spProductImgS;

        public static class SpProductIntroBean {
            public int productId;
            public Object img001;
            public Object img002;
            public Object img003;
            public Object img004;
            public Object img005;
            public int introId;
            public Object img006;
            public Object img007;
            public Object img008;
            public Object img009;
            public Object img010;
            public String productIntro;
            public String states;
        }

        public static class SpProductImgSBean {
            public int id;
            public int productId;
            public String imgName;
            public long createTime;
        }
    }
}

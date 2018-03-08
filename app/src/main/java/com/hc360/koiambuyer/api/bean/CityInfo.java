package com.hc360.koiambuyer.api.bean;


import com.bigkoo.pickerview.model.IPickerViewData;

import java.util.List;

/**
 * Project: Ruhefu
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/8/30
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class CityInfo implements IPickerViewData {

    public String code;
    public String name;
    public List<SubBeanX> sub;

    @Override
    public String getPickerViewText() {
        return this.name;
    }

    public static class SubBeanX implements IPickerViewData {
        public String code;
        public String name;
        public List<SubBean> sub;

        @Override
        public String getPickerViewText() {
            return this.name;
        }

        public static class SubBean implements IPickerViewData {
            public String code;
            public String name;

            @Override
            public String getPickerViewText() {
                return this.name;
            }
        }
    }
}

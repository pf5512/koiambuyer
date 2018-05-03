package com.hc360.koiambuyer.utils;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;
import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.myinterface.AddressListener;
import com.hc360.koiambuyer.myinterface.KoAddressListener;

import static com.hc360.koiambuyer.view.MyApp.options1Items;
import static com.hc360.koiambuyer.view.MyApp.options2Items;
import static com.hc360.koiambuyer.view.MyApp.options3Items;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/16
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class AddressUtils {
    public static void showPickerView(Activity activity,final AddressListener listener) {// 弹出选择器
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(activity, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String proCode = options1Items.get(options1).code;
                String proName = options1Items.get(options1).name;
                String cityCode = options2Items.get(options1).get(options2).code;
                String cityName = options2Items.get(options1).get(options2).name;
                String areaCode = options3Items.get(options1).get(options2).get(options3).code;
                String areaName = options3Items.get(options1).get(options2).get(options3).name;
                listener.getAddress(proName + " " + cityName + " " + areaName,proCode,cityCode,areaCode);
            }
        })
                .setTitleText(activity.getResources().getString(R.string.choice_city))
                .setCancelText(activity.getResources().getString(R.string.cancel))
                .setSubmitText(activity.getResources().getString(R.string.stv_default))
                .setCancelColor(activity.getResources().getColor(R.color.mainColor))
                .setSubmitColor(activity.getResources().getColor(R.color.mainColor))
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    public static void showKoPickerView(Activity activity,final KoAddressListener listener) {// 弹出选择器
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(activity, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String proCode = options1Items.get(options1).code;
                String proName = options1Items.get(options1).name;
                String cityCode = options2Items.get(options1).get(options2).code;
                String cityName = options2Items.get(options1).get(options2).name;
                listener.getAddress(proName ,cityName ,proCode,cityCode);
            }
        })
                .setTitleText(activity.getResources().getString(R.string.choice_city))
                .setCancelText(activity.getResources().getString(R.string.cancel))
                .setSubmitText(activity.getResources().getString(R.string.stv_default))
                .setCancelColor(activity.getResources().getColor(R.color.mainColor))
                .setSubmitColor(activity.getResources().getColor(R.color.mainColor))
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        pvOptions.setPicker(options1Items, options2Items);//三级选择器
        pvOptions.show();
    }
}

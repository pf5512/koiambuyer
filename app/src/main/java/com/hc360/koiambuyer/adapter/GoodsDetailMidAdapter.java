package com.hc360.koiambuyer.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.GoodsDetailInfo;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/21
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class GoodsDetailMidAdapter extends BaseQuickAdapter<GoodsDetailInfo.ContentBean.SpProductPiceBean> {
    private String mUnit  ;
    public GoodsDetailMidAdapter(int layoutResId, List data,String unit) {
        super(layoutResId, data);
        mUnit = unit;
    }

    @Override
    protected void convert(BaseViewHolder holder, GoodsDetailInfo.ContentBean.SpProductPiceBean bean) {
        holder.setText(R.id.tv_price,bean.price+"                    ");
        String money = "";
        if (holder.getAdapterPosition()==0){
            money = bean.minNumber+mUnit+mContext.getResources().getString(R.string.good_start);
            holder.setText(R.id.tv_num,money);
        }else {
            if (bean.maxNumber >= 99999999){
                money = "≥"+bean.minNumber+mUnit ;
            }else {
                money = bean.minNumber + "~" + bean.maxNumber+mUnit;
            }
            holder.setText(R.id.tv_num,money);
        }
    }
}

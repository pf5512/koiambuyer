package com.hc360.koiambuyer.adapter;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.PurchaseDetailInfo;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseHolder;

import java.util.List;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/23
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class PurchaseDetailParamAdapter extends BaseAdapter<PurchaseDetailInfo.StProductParasBean> {

    public PurchaseDetailParamAdapter(int layoutId, List<PurchaseDetailInfo.StProductParasBean> list) {
        super(layoutId, list);
    }

    @Override
    protected void convert(BaseHolder holder, PurchaseDetailInfo.StProductParasBean bean) {
        holder.setBackgroundColor(R.id.ll_item,holder.getAdapterPosition()%2==1?R.color.StvColor:R.color.BgColor);
        holder.setText(R.id.tv_title,bean.paramName);
        holder.setText(R.id.tv_content,bean.paramValue);
    }
}

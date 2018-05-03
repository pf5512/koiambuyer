package com.hc360.koiambuyer.adapter;

import android.view.View;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.PurchaseDetailInfo;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseHolder;
import com.hc360.koiambuyer.view.home.PublishActivity;

import java.util.List;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/22
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class PublishParamAdapter extends BaseAdapter<PurchaseDetailInfo.StProductParasBean> {

    PublishActivity mActivity;
    public PublishParamAdapter(int layoutId, List<PurchaseDetailInfo.StProductParasBean> list, PublishActivity activity) {
        super(layoutId, list);
        mActivity = activity;
    }

    @Override
    protected void convert(final BaseHolder holder, PurchaseDetailInfo.StProductParasBean bean) {
        holder.setText(R.id.tv_name,bean.paramName);
        holder.setText(R.id.tv_content,bean.paramValue);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.addParam(holder.getAdapterPosition()+1,getItemCount());
            }
        });
        holder.itemView.findViewById(R.id.fl_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData().remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
    }
}

package com.hc360.koiambuyer.adapter;

import android.view.View;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.CityManagerInfo;
import com.hc360.koiambuyer.utils.GlideUtils;
import com.hc360.koiambuyer.view.CityManagerActivity;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseHolder;

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

public class CityManagerAdapter extends BaseAdapter<CityManagerInfo.ListBean> {
    CityManagerActivity mActivity;

    public CityManagerAdapter(int layoutId, List<CityManagerInfo.ListBean> list, CityManagerActivity activity) {
        super(layoutId, list);
        mActivity = activity;
    }

    @Override
    protected void convert(BaseHolder holder, final CityManagerInfo.ListBean bean) {

        if (holder.getAdapterPosition()==1||holder.getAdapterPosition()==2){
            holder.setVisibility(R.id.space,true);
        }else {
            holder.setVisibility(R.id.space,false);
        }
        holder.setText(R.id.tv_name,bean.manageName);
        holder.setText(R.id.tv_phone,bean.managePhone);
        GlideUtils.loadHead(mContext,bean.manageHeadImg,holder,R.id.iv_head);
        holder.setVisibility(R.id.fl_select,mActivity.mManageId.equals(bean.manageId));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mActivity.mManageId.equals(bean.manageId)){
                    mActivity.mManageId= "";
                    mActivity.mPhone = "";
                }else {
                    mActivity.mManageId = bean.manageId;
                    mActivity.mPhone = bean.managePhone;
                }
                notifyDataSetChanged();
            }
        });
    }
}

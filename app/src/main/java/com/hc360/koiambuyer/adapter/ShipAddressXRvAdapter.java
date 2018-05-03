package com.hc360.koiambuyer.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.ShipAddressInfo;
import com.hc360.koiambuyer.model.States;
import com.hc360.koiambuyer.myinterface.iview.IShipAddressView;
import com.hc360.koiambuyer.utils.TVDrawableUtil;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseHolder;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/22
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class ShipAddressXRvAdapter extends BaseAdapter<ShipAddressInfo.ListBean> {

    IShipAddressView mView;
    public ShipAddressXRvAdapter(int layoutResId, List data , IShipAddressView view) {
        super(layoutResId, data);
        this.mView = view;
    }

    @Override
    protected void convert(BaseHolder holder, final ShipAddressInfo.ListBean bean) {
        holder.setText(R.id.tv_detail_address,bean.pName+"  "+bean.cName+"  "+bean.addressDetail);
        holder.setText(R.id.tv_address,bean.receiveUser+"   "+bean.telphone);
        final Button btnDefault = (Button) holder.itemView.findViewById(R.id.btn_default);
        if (bean.useState.equals(States.DEFAULT_ADDRESS)){
            TVDrawableUtil.setLeftNormalByRes(mContext,R.mipmap.select_buyer,btnDefault);
            btnDefault.setTextColor(mContext.getResources().getColor(R.color.mainColor));
        }else{
            TVDrawableUtil.setLeftNormalByRes(mContext,R.mipmap.not_select_s,btnDefault);
            btnDefault.setTextColor(mContext.getResources().getColor(R.color.tvNormalColor));
        }

        btnDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.useState.equals(States.DEFAULT_ADDRESS)){
                    mView.setDefaultAddress(bean.receiveId,States.NOT_DEFAULT_ADDRESS);
                }else{
                    mView.setDefaultAddress(bean.receiveId,States.DEFAULT_ADDRESS);
                }
            }
        });

        TextView tvEdit = (TextView) holder.itemView.findViewById(R.id.tv_edit);

//        holder.setVisibility(R.id.tv_edit,!mView.getSelectState());
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.editAddress(bean.receiveId);
            }
        });
        TextView tvDelete = (TextView) holder.itemView.findViewById(R.id.tv_delete);
//        holder.setVisibility(R.id.tv_delete,!mView.getSelectState());
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.deleteAddress(bean.receiveId);
            }
        });
//        tvDelete.setVisibility(SPUtils.getBoolean(mContext, Constant._IS_BOSS,false)?View.VISIBLE:View.GONE);
//        tvEdit.setVisibility(SPUtils.getBoolean(mContext, Constant._IS_BOSS,false)?View.VISIBLE:View.GONE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发布商品，选择发货地址
                mView.selectAddress(bean.receiveUser+"   "+bean.telphone,bean.pName+"  "+ bean.cName+"  "+bean.addressDetail,bean.receiveId);
            }
        });
    }
}

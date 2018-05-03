package com.hc360.koiambuyer.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.MeOrderInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.view.ContainerFooterActivity;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseHolder;

import java.util.List;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/20
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class MePurchasesAdapter extends BaseAdapter<MeOrderInfo>{
    public MePurchasesAdapter(int layoutId, List<MeOrderInfo> list) {
        super(layoutId, list);
    }

    @Override
    protected void convert(final BaseHolder holder, MeOrderInfo bean) {
        holder.setText(R.id.tv_text,bean.text);
        ImageView ivIcon = (ImageView) holder.itemView.findViewById(R.id.iv_icon);
        ivIcon.setImageResource(bean.picRes);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openMyPurchase = new Intent(mContext, ContainerFooterActivity.class);
                openMyPurchase.putExtra(Constant.TYPE,Constant.MY_PURCHASE);
                int position = -1;
                if (holder.getAdapterPosition()==2){
                    position = 3;
                }else if (holder.getAdapterPosition()==3){
                    position = 2;
                }else {
                    position =  holder.getAdapterPosition();
                }
                openMyPurchase.putExtra(Msg.PURCHASE_STATE,position);
                mContext.startActivity(openMyPurchase);
            }
        });
    }
}

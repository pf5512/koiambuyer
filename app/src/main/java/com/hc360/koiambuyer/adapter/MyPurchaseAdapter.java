package com.hc360.koiambuyer.adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.MyPurchaseInfo;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.model.States;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseHolder;
import com.hc360.koiambuyer.view.purchase.PurchaseDetailActivity;

import java.util.List;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/21
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class MyPurchaseAdapter extends BaseAdapter<MyPurchaseInfo.ListBean> {

    public MyPurchaseAdapter(int layoutId, List<MyPurchaseInfo.ListBean> list) {
        super(layoutId, list);
    }

    @Override
    protected void convert(final BaseHolder holder, final MyPurchaseInfo.ListBean bean) {
        holder.setText(R.id.tv_title,bean.prodName);
        holder.setText(R.id.tv_state,bean.prodStateStr);
        CardView cardView = (CardView) holder.itemView.findViewById(R.id.card);
        if (bean.prodState.equals(States.STATE_PURCHASED)){
//            holder.setBackgroundRes(R.id.tv_state,R.drawable.shape_state_purchased_2);
            holder.setBackgroundColor(R.id.tv_state,R.color.buyerColor);
            cardView.setCardBackgroundColor(mContext.getResources().getColor(R.color.buyerColor));
        }else if (bean.prodState.equals(States.STATE_MATCH)){
            holder.setBackgroundColor(R.id.tv_state,R.color.sellerColor);
            cardView.setCardBackgroundColor(mContext.getResources().getColor(R.color.sellerColor));
//            holder.setBackgroundRes(R.id.tv_state,R.drawable.shape_state_match_2);
        }else if (bean.prodState.equals(States.STATE_SUCCESS)){
            holder.setBackgroundColor(R.id.tv_state,R.color.moneyColor);
            cardView.setCardBackgroundColor(mContext.getResources().getColor(R.color.moneyColor));
//            holder.setBackgroundRes(R.id.tv_state,R.drawable.shape_state_done_2);
        }else {
            holder.setBackgroundColor(R.id.tv_state,R.color.unableClick);
            cardView.setCardBackgroundColor(mContext.getResources().getColor(R.color.unableClick));
//            holder.setBackgroundRes(R.id.tv_state,R.drawable.shape_state_close_2);
        }
        holder.setText(R.id.tv_num,bean.prodNumber);
        holder.setText(R.id.tv_time,bean.prodTime);
        holder.setText(R.id.tv_msg,bean.offerNumber+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openPurchaseDetail = new Intent(mContext,PurchaseDetailActivity.class);
                openPurchaseDetail.putExtra(Msg.PUR_ID,bean.id);
                mContext.startActivity(openPurchaseDetail);
            }
        });
    }
}

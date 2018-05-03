package com.hc360.koiambuyer.adapter;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.SearchInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/2
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class FindAdapter extends BaseQuickAdapter<SearchInfo.ListBean> {

    public FindAdapter(int layoutResId, List<SearchInfo.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder, final SearchInfo.ListBean bean) {
        Glide.with(mContext).load(bean.loopImg001).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                holder.setImageDrawable(R.id.iv_goods,resource);
            }
        });
        holder.setImageResource(R.id.iv_head,R.mipmap.buyer_head);
        Glide.with(mContext).load(bean.userHeadImg).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                holder.setImageDrawable(R.id.iv_head,resource);
            }
        });
        holder.setText(R.id.tv_name,bean.korCoreUserVo.userName);
        String compName = bean.compName;
        if (TextUtils.isEmpty(bean.compName)){
            compName = getStr(R.string.personal_account);
        }
        holder.setText(R.id.tv_company_name,compName);
        holder.setText(R.id.tv_title,bean.productName);
        if (bean.productPrice.equals("0")){
            holder.setText(R.id.tv_money,getStr(R.string.face));
        }else if(bean.productPrice.equals("1")){
            try {
                if (bean.minPrice.compareTo(BigDecimal.ZERO) == 1){
                    holder.setText(R.id.tv_money,"￥"+bean.minPrice);
                }
            }catch (Exception e){

            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent openGoodsDetail = new Intent(mContext, GoodsDetailActivity.class);
//                openGoodsDetail.putExtra(Msg.PRODUCT_ID,bean.productId);
//                mContext.startActivity(openGoodsDetail);
            }
        });

        holder.setText(R.id.tv_chat_num,bean.recordCount+"");
        holder.setText(R.id.tv_keep_num,bean.lookCount+"");
    }

    private String getStr(int resId){
        return mContext.getResources().getString(resId);
    }
}

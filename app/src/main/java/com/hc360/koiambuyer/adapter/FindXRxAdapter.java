package com.hc360.koiambuyer.adapter;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.SearchInfo;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseHolder;

import java.math.BigDecimal;
import java.util.List;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/2
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class FindXRxAdapter extends BaseAdapter<SearchInfo.ListBean> {

    public FindXRxAdapter(int layoutId, List<SearchInfo.ListBean> list) {
        super(layoutId, list);
    }

    @Override
    protected void convert(final BaseHolder holder, SearchInfo.ListBean bean) {
        Glide.with(mContext).load(bean.loopImg001).asBitmap().placeholder(R.mipmap.good_default).error(R.mipmap.good_default).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                holder.setImageBitmap(R.id.iv_goods,resource);
            }
        });
        holder.setImageResource(R.id.iv_head,R.mipmap.buyer_head);
        Glide.with(mContext).load(bean.userHeadImg).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                holder.setImageBitmap(R.id.iv_head,resource);
            }
        });
        holder.setText(R.id.tv_name,bean.userName);
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

            }
        });

        holder.setText(R.id.tv_chat_num,bean.recordCount+"");
        holder.setText(R.id.tv_keep_num,bean.lookCount+"");
    }
}

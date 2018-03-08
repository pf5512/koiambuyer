package com.hc360.koiambuyer.adapter;

import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
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

public class SearchGoodsAdapter extends BaseQuickAdapter<SearchInfo.ListBean> {

    public SearchGoodsAdapter(int layoutResId, List<SearchInfo.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder, final SearchInfo.ListBean bean) {
        holder.setVisible(R.id.view,holder.getAdapterPosition() == 0);
        Glide.with(mContext).load(bean.loopImg001).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                holder.setImageBitmap(R.id.iv_goods,resource);
            }
        });

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

        holder.setText(R.id.tv_chat_num,bean.recordCount+"");
        holder.setText(R.id.tv_keep_num,bean.lookCount+"");
    }

    private String getStr(int resId){
        return mContext.getResources().getString(resId);
    }
}

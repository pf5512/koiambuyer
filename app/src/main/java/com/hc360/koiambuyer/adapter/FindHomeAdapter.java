package com.hc360.koiambuyer.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.FindInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.model.States;
import com.hc360.koiambuyer.utils.GlideUtils;
import com.hc360.koiambuyer.utils.TimeTool;
import com.hc360.koiambuyer.view.ContainerActivity;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseHolder;
import com.hc360.koiambuyer.view.good.GoodsDetailActivity;
import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.Random;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/2
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class FindHomeAdapter extends BaseAdapter<FindInfo.ListBean> {

    public FindHomeAdapter(int layoutId, List<FindInfo.ListBean> list) {
        super(layoutId, list);
    }

    @Override
    protected void convert(final BaseHolder holder, final FindInfo.ListBean bean) {
        final ImageView ivGoods = (ImageView) holder.itemView.findViewById(R.id.iv_goods);
        setIvBg(ivGoods);
//        GlideUtils.loadGood(mContext,bean.loopImg001,(ImageView)holder.itemView.findViewById(R.id.iv_goods));
//        Glide.with(mContext).load(bean.loopImg001).into(ivGoods);
        Glide.with(mContext).load(bean.loopImg001).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                ivGoods.setImageDrawable(resource);
                ivGoods.setBackgroundColor(mContext.getResources().getColor(R.color.StvColor));
            }
        });
        GlideUtils.loadHead(mContext,bean.korCoreUserVo.headImg,(ImageView) holder.itemView.findViewById(R.id.iv_head));
        holder.setText(R.id.tv_user,bean.korCoreUserVo.userName);
        holder.setText(R.id.tv_title,bean.productName);
        holder.setText(R.id.tv_money, TimeTool.getLastTime(bean.createTime));
        holder.itemView.findViewById(R.id.ll_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openBuyer = new Intent(mContext, ContainerActivity.class);
                openBuyer.putExtra(States.WHITE,true);
                openBuyer.putExtra(Constant.TYPE, Constant.BUYER_DETAIL);
                openBuyer.putExtra(Constant.IS_CUSTOM, true);
                Logger.e(bean.korCoreUserVo.ssoUserId+"maishou");
                openBuyer.putExtra(Msg.BUYER_ID,bean.korCoreUserVo.ssoUserId+"");
                mContext.startActivity(openBuyer);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGoodDetail = new Intent(mContext, GoodsDetailActivity.class);
                openGoodDetail.putExtra(Msg.PRODUCT_ID,bean.productId);
                mContext.startActivity(openGoodDetail);
            }
        });
        holder.setText(R.id.tv_chat_num,bean.followCount+"");
        holder.setText(R.id.tv_keep_num,"0");
    }
    private void setIvBg(ImageView iv){
        int i = new Random().nextInt(4);
        switch (i){
            case 0:
                iv.setBackgroundColor(Color.parseColor("#FCE4E5"));
                Logger.e("0");
                break;
            case 1:
                iv.setBackgroundColor(Color.parseColor("#DDDA8B"));
                break;
            case 2:
                iv.setBackgroundColor(Color.parseColor("#D2BA9E"));
                break;
            case 3:
                iv.setBackgroundColor(Color.parseColor("#E9F0EE"));
                break;
            case 4:
                iv.setBackgroundColor(Color.parseColor("#FCE4E5"));
                break;
        }
    }
}

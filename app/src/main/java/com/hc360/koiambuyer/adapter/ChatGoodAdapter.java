package com.hc360.koiambuyer.adapter;

import android.content.Intent;
import android.view.View;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.ChatGoodInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.utils.TimeTool;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.ChatActivity;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseHolder;
import com.hc360.koiambuyer.view.good.GoodsDetailActivity;

import java.util.List;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/4/8
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class ChatGoodAdapter extends BaseAdapter<ChatGoodInfo.ListBean>{
    String preTime = "";
    public ChatGoodAdapter(int layoutId, List<ChatGoodInfo.ListBean> list) {
        super(layoutId, list);
    }

    @Override
    protected void convert(final BaseHolder holder, final ChatGoodInfo.ListBean bean) {
        loadGood(bean.proImg, R.id.iv_good);
        loadHead(bean.userHeadImg,R.id.iv_head);
        if (TimeTool.getTimeByHanYue(bean.createTime).equals(preTime)){
            holder.setVisibility(R.id.tv_time,false);
            holder.setVisibility(R.id.line_time,false);
            holder.setVisibility(R.id.line,true);
        }else {
            preTime = TimeTool.getTimeByHanYue(bean.createTime);
            holder.setText(R.id.tv_time,preTime);
            holder.setVisibility(R.id.tv_time,true);
            holder.setVisibility(R.id.line_time,true);
            holder.setVisibility(R.id.line,false);
        }
        holder.setText(R.id.tv_name,bean.userName);

        holder.itemView.findViewById(R.id.tv_chat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bean.isOnline.equals("1")){
                    ToastUtil.showShort(mContext,getStr(R.string.pro_out_line));
                }else {
                    Intent openChat = new Intent(mContext, ChatActivity.class);
                    openChat.putExtra(Msg.RIGHT_NOW, true);
                    openChat.putExtra(Msg.USER_NAME,bean.userName);
                    openChat.putExtra(Msg.ID, bean.userId);
                    openChat.putExtra(Msg.PRO_ID, bean.proId);
                    openChat.putExtra(Msg.TYPE, Constant.LOOK_GOODS);
                    mContext.startActivity(openChat);
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bean.isOnline.equals("1")){
                    ToastUtil.showShort(mContext,getStr(R.string.pro_out_line));
                }else {
                    Intent openGoodDetail = new Intent(mContext, GoodsDetailActivity.class);
                    openGoodDetail.putExtra(Msg.PRODUCT_ID,bean.proId);
                    mContext.startActivity(openGoodDetail);
                }
            }
        });
        if (!bean.isOnline.equals("1")){
            holder.setVisibility(R.id.iv_out_line, true);
            holder.setVisibility(R.id.gray,true);
            holder.setBackgroundRes(R.id.tv_chat,R.drawable.ic_bg_gray_9);
            holder.setTextColor(R.id.tv_price_about,R.color.minorColor);
            holder.setTextColor(R.id.tv_title,R.color.minorColor);
            holder.setTextColor(R.id.tv_chat,R.color.unableClick);
        }else {
            holder.setVisibility(R.id.iv_out_line, false);
            holder.setVisibility(R.id.gray,false);
            holder.setBackgroundRes(R.id.tv_chat,R.drawable.ic_bg_yellow_9);
            holder.setTextColor(R.id.tv_price_about,R.color.buyerColor);
            holder.setTextColor(R.id.tv_title,R.color.tvNormalColor);
            holder.setTextColor(R.id.tv_chat,R.color.StvColor);
        }
        holder.setText(R.id.tv_title,bean.proName);
        holder.setText(R.id.tv_price_about,getStr(R.string.money_unit)+bean.minPrice+"~"+getStr(R.string.money_unit)+bean.maxPrice);
    }
}

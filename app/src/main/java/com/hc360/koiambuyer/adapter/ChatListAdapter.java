package com.hc360.koiambuyer.adapter;

import android.content.Intent;
import android.text.Spannable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.ChatListInfo;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.model.States;
import com.hc360.koiambuyer.utils.ImageUtil;
import com.hc360.koiambuyer.utils.SPUtils;
import com.hc360.koiambuyer.utils.TimeTool;
import com.hc360.koiambuyer.view.ChatActivity;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseHolder;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.utils.EaseSmileUtils;

import java.util.List;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/17
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class ChatListAdapter extends BaseAdapter<ChatListInfo.ContentBean> {

    public ChatListAdapter(int layoutResId, List<ChatListInfo.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseHolder holder, final ChatListInfo.ContentBean bean) {
        holder.setText(R.id.tv_name, bean.userName);
        if (!TextUtils.isEmpty(bean.prodName)){
            String proName = "";
            if (bean.prodName.length()>16){
                proName = bean.prodName.substring(0,15)+"...";
            }else {
                proName = bean.prodName;
            }
            holder.setText(R.id.tv_last_title, getStr(R.string.chat_on)+" ("+proName+")");
        }
        holder.setVisibility(R.id.line, holder.getAdapterPosition() != (getData().size()));
        holder.setVisibility(R.id.last_line, holder.getAdapterPosition() == (getData().size()));
        long time = bean.EndTime > 0 ? bean.EndTime : bean.EndTime;
        if (TimeTool.getDay(time).equals(TimeTool.getDay(System.currentTimeMillis()))) {
            holder.setText(R.id.tv_time, TimeTool.getHourSecond(time));
        } else {
            holder.setText(R.id.tv_time, TimeTool.getTimeByHanYue(time));
        }

        TextView tvMsg = (TextView) holder.itemView.findViewById(R.id.tv_msg);
        try {
            if (TextUtils.isEmpty(bean.lastMsg)) {
                tvMsg.setText(getStr(R.string.have_no_msg));
            } else {
                Spannable span = EaseSmileUtils.getSmiledText(mContext, bean.lastMsg);
                tvMsg.setText(span, TextView.BufferType.SPANNABLE);
            }
        } catch (Exception e) {}
        Badge badge = null;
        if (bean.notReadNum > 0) {
            badge = new QBadgeView(mContext).bindTarget(holder.itemView.findViewById(R.id.tv_msg_num)).setBadgeNumber(bean.notReadNum);
            badge.setBadgeGravity(Gravity.CENTER);
            badge.setShowShadow(false);
            badge.setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                    @Override
                public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                    if (dragState == STATE_SUCCEED) {
                        try {
                            EMConversation conversation = EMClient.getInstance().chatManager().getConversation("iambuyer_" + bean.toUserId);
                            conversation.markAllMessagesAsRead();
                        } catch (Exception e) {
                        }
                    }
                }
            });
            badge.setBadgeTextSize(12, true);
            holder.setVisibility(R.id.tv_msg_num, true);
        } else {
            //去掉未读消息
            holder.setVisibility(R.id.tv_msg_num, false);
        }
        ImageView ivHead = (ImageView) holder.itemView.findViewById(R.id.iv_head);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(ImageUtil.getToHead());
        requestOptions.error(ImageUtil.getToHead());
        Glide.with(mContext)
                .setDefaultRequestOptions(requestOptions)
                .load(bean.userHeadImg)
                .into(ivHead);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SPUtils.saveString(mContext, States.TO_CHAT_STATE, bean.checkState);
                SPUtils.saveString(mContext, States.TO_CHAT_HEAD, bean.userHeadImg);
                Intent openChat = new Intent(mContext, ChatActivity.class);
                //将这个人的未读消息置为已读
                openChat.putExtra(Msg.ID, bean.toUserId);
                openChat.putExtra(Msg.USER_NAME, bean.userName);
                mContext.startActivity(openChat);
            }
        });
    }
}

package com.hc360.koiambuyer.adapter;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.MeOrderInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.view.ContainerFooterActivity;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseHolder;
import com.orhanobut.logger.Logger;

import java.util.List;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/20
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class MeOrderAdapter extends BaseAdapter<MeOrderInfo>{
    public MeOrderAdapter(int layoutId, List<MeOrderInfo> list) {
        super(layoutId, list);
    }

    @Override
    protected void convert(final BaseHolder holder, MeOrderInfo bean) {
        holder.setText(R.id.tv_text,bean.text);
        ImageView ivIcon = (ImageView) holder.itemView.findViewById(R.id.iv_icon);
        ivIcon.setImageResource(bean.picRes);
        if (holder.getAdapterPosition()<2){
            if (bean.num>0){
                Badge badge = new QBadgeView(mContext).bindTarget(ivIcon);
                badge.setBadgeGravity(Gravity.END | Gravity.TOP);
                badge.setBadgeText(bean.num+"");
                badge.setBadgeTextSize(10,true);
                badge.setBadgeBackgroundColor(mContext.getResources().getColor(R.color.msg_num));
                badge.setBadgePadding(3,true);
                badge.setShowShadow(false);
            }
        }else if (holder.getAdapterPosition()==2){
            Logger.e("取出"+bean.toString());
            String num = bean.num+"";
            if (bean.num>99){
                num = "99+";
            }
            holder.setText(R.id.tv_text,getStr(R.string.state_3)+"("+num+")");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openOrder = new Intent(mContext, ContainerFooterActivity.class);
                int position = holder.getAdapterPosition();
                String positionStr = "";
                if (position < 2){
                    positionStr = position+"";
                }else {
                    positionStr = (position+1)+"";
                }
                openOrder.putExtra(Msg.POSITION, positionStr);
                openOrder.putExtra(Constant.TYPE, Constant.ORDER);
                mContext.startActivity(openOrder);
            }
        });
    }
}

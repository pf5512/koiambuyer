package com.hc360.koiambuyer.adapter;

import android.content.Intent;
import android.view.View;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.NumberInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.ContainerActivity;
import com.hc360.koiambuyer.view.ContainerFooterActivity;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseHolder;

import java.util.List;



/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/4/4
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class MeNumberAdapter extends BaseAdapter<NumberInfo>{

    public MeNumberAdapter(int layoutId, List<NumberInfo> list) {
        super(layoutId, list);
    }

    @Override
    protected void convert(final BaseHolder holder, final NumberInfo bean) {
        holder.setText(R.id.tv_num,bean.number+"");
        holder.setText(R.id.tv_text,bean.text+"");
        holder.setTextColor(R.id.tv_num,holder.getAdapterPosition()==0?R.color.minorColor:R.color.tvNormalColor);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (holder.getAdapterPosition()){
                    case 0:
                        ToastUtil.showShort(mContext,getStr(R.string.can_not_click));
                        break;
                    case 1:
                        Intent openChatGood = new Intent(mContext,ContainerActivity.class);
                        openChatGood.putExtra(Constant.TYPE, Constant.CHAT_GOOD);
                        mContext.startActivity(openChatGood);
                        break;
                    case 2:
                        Intent openLike = new Intent(mContext, ContainerActivity.class);
                        openLike.putExtra(Constant.TYPE, Constant.LIKE);
                        mContext.startActivity(openLike);
                        break;
                    case 3:
                        Intent openAttention = new Intent(mContext, ContainerFooterActivity.class);
                        openAttention.putExtra(Constant.TYPE, Constant.ATTENTION);
                        mContext.startActivity(openAttention);
                        break;
                }
            }
        });
    }
}

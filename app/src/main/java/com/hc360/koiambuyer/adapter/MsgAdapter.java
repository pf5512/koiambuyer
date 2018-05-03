package com.hc360.koiambuyer.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.MsgInfo;
import com.hc360.koiambuyer.model.States;
import com.hc360.koiambuyer.utils.TVDrawableUtil;
import com.hc360.koiambuyer.utils.TimeTool;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseHolder;
import com.hc360.koiambuyer.view.me.MsgFragment;

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

public class MsgAdapter extends BaseAdapter<MsgInfo.ListBean> {

    MsgFragment  mFragment;
    public MsgAdapter(int layoutId, List<MsgInfo.ListBean> list, MsgFragment  fragment) {
        super(layoutId, list);
        mFragment = fragment;
    }

    @Override
    protected void convert(BaseHolder holder, final MsgInfo.ListBean bean) {
        TextView tvTitle = (TextView) holder.itemView.findViewById(R.id.tv_title);
        tvTitle.setText(bean.msgTitle+"   "+ TimeTool.getTimeDetail(bean.createTime));
        holder.setText(R.id.tv_content,bean.msgContent);
        if (bean.isRead.equals(States.READ)){
            tvTitle.setCompoundDrawables(null,null, null, null);
        }else{
            TVDrawableUtil.setLeftNormalByRes(mContext,R.drawable.shape_msg,tvTitle);
        }
        holder.setTextColor(R.id.tv_content,bean.isRead.equals(States.READ)?R.color.HintColor:R.color.minorSecondColor);
        final ImageView ivSelect = (ImageView) holder.itemView.findViewById(R.id.iv_select);
        if (mFragment.mSelectMessages.contains(bean.id+"")){
            ivSelect.setImageResource(R.mipmap.select_buyer);
        }else{
            ivSelect.setImageResource(mFragment.isEdit?R.mipmap.not_select_s:R.mipmap.not_select_b_20);
        }
        ivSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment.changeEditState(true);
                if (mFragment.mSelectMessages.contains(bean.id+"")){
                    mFragment.mSelectMessages.remove(bean.id+"");
                    ivSelect.setImageResource(R.mipmap.not_select_36);
                    if (mFragment.mSelectMessages.size() == 0){
                        mFragment.changeEditState(false);
                    }
                }else{
                    mFragment.mSelectMessages.add(bean.id+"");
                    ivSelect.setImageResource(R.mipmap.select_buyer);
                }
                notifyDataSetChanged();
            }
        });
    }
}

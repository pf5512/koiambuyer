package com.hc360.koiambuyer.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hc360.koiambuyer.R;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/6
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class DatePopAdapter extends BaseQuickAdapter<String> {

    public int prePosition = -1;
    public DatePopAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, String s) {
        holder.setText(R.id.tv_item,s);
        int position = holder.getAdapterPosition();
        if (position == prePosition){
            holder.setImageResource(R.id.iv_item, R.mipmap.yes);
        }else{
            holder.setImageResource(R.id.iv_item,R.drawable.shape_transparents);
        }
    }
}

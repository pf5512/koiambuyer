package com.hc360.koiambuyer.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.PurchaseBasicInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.view.ContainerActivity;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseHolder;

import java.util.List;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/23
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class PurchaseDetailBasicAdapter extends BaseAdapter<PurchaseBasicInfo> {

    int mQuoteId = -1;
    public PurchaseDetailBasicAdapter(int layoutId, List<PurchaseBasicInfo> list) {
        super(layoutId, list);
    }
    public PurchaseDetailBasicAdapter(int layoutId, List<PurchaseBasicInfo> list,int quoteId) {
        super(layoutId, list);
        mQuoteId = quoteId;
    }

    @Override
    protected void convert(BaseHolder holder, PurchaseBasicInfo bean) {
        if (bean.title.equals(getStr(R.string.quote_price))){
            holder.setTextColor(R.id.tv_content,R.color.moneyColor);
        }else {
            holder.setTextColor(R.id.tv_content,R.color.tvNormalColor);
        }
        holder.setVisibility(R.id.bottom_line,holder.getAdapterPosition()!=0);
        holder.setText(R.id.tv_title,bean.title);
        if (bean.title.equals(getStr(R.string.quote_b2b))){
            TextView tvContent = (TextView) holder.itemView.findViewById(R.id.tv_content);
            tvContent.setLineSpacing(1,(float) 1.5);
        }
        holder.setText(R.id.tv_content,bean.content);
        if (mQuoteId != -1){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent openQuote = new Intent(mContext,ContainerActivity.class);
                    openQuote.putExtra(Constant.TYPE,Constant.QUOTE);
                    openQuote.putExtra(Msg.QUOTE_ID,mQuoteId);
                    mContext.startActivity(openQuote);
                }
            });
        }
    }
}

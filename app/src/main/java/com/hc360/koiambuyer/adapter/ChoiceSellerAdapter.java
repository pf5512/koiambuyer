package com.hc360.koiambuyer.adapter;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.ChoiceSellerInfo;
import com.hc360.koiambuyer.utils.TVDrawableUtil;

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

public class ChoiceSellerAdapter extends BaseQuickAdapter<ChoiceSellerInfo.ListBean> {

    public ChoiceSellerAdapter(int layoutResId, List<ChoiceSellerInfo.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder, final ChoiceSellerInfo.ListBean bean) {
        holder.setText(R.id.tv_company_name,bean.compShortName);
        holder.setText(R.id.tv_company_detail_name,bean.compName);
        holder.setText(R.id.tv_msg,bean.pName+bean.aName+"   |   "+bean.inName);
        Glide.with(mContext).load(bean.compLogo).into((ImageView) holder.itemView.findViewById(R.id.iv_head));
        holder.setVisible(R.id.first,holder.getAdapterPosition()==0);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent openCompanyInfoHome = new Intent(mContext, CompanyInfoHomeActivity.class);
//                openCompanyInfoHome.putExtra(Msg.COM_ID, bean.compId + "");
//                mContext.startActivity(openCompanyInfoHome);
            }
        });
        String proNames = bean.proNames;
        if (!TextUtils.isEmpty(proNames)){
            if (proNames.length()>6){
                proNames = proNames.substring(0,5)+"...";
            }
            SpannableStringBuilder style = new SpannableStringBuilder(getStr(R.string.hot_sell)+proNames+getStr(R.string.hold)+bean.count+getStr(R.string.provide));
            style.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.mainColor)), 3, proNames.length()+4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.setText(R.id.tv_hot_sell,style);
        }else{
            holder.setText(R.id.tv_hot_sell,getStr(R.string.have_no_hot_goods));
        }

        TextView tvHotSell = (TextView) holder.itemView.findViewById(R.id.tv_hot_sell);
        TVDrawableUtil.setRightByRes(mContext,R.mipmap.in,tvHotSell);

    }

    private String getStr(int resId){
        return mContext.getResources().getString(resId);
    }
}

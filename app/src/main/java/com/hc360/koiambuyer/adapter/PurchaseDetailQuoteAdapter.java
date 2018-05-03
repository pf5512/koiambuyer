package com.hc360.koiambuyer.adapter;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.PurchaseBasicInfo;
import com.hc360.koiambuyer.api.bean.PurchaseDetailInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.utils.DensityUtil;
import com.hc360.koiambuyer.view.ContainerActivity;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseHolder;
import com.hc360.koiambuyer.view.purchase.PurchaseDetailActivity;

import java.util.ArrayList;
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

public class PurchaseDetailQuoteAdapter extends BaseAdapter<PurchaseDetailInfo.OfferProdListBean> {
    PurchaseDetailActivity mActivity ;
    boolean isCuo;

    public PurchaseDetailQuoteAdapter(int layoutId, List<PurchaseDetailInfo.OfferProdListBean> list, PurchaseDetailActivity activity,boolean cuo) {
        super(layoutId, list);
        mActivity = activity;
        isCuo = cuo;
    }

    @Override
    protected void convert(BaseHolder holder, final PurchaseDetailInfo.OfferProdListBean bean) {
        holder.setText(R.id.tv_title,bean.offerProdName);
        RecyclerView rvQuote = (RecyclerView) holder.itemView.findViewById(R.id.rv_quote);
        rvQuote.setNestedScrollingEnabled(false);
        rvQuote.setLayoutManager(new LinearLayoutManager(mContext));
        List<PurchaseBasicInfo>  infoLis = new ArrayList<>();
        infoLis.add(new PurchaseBasicInfo(getStr(R.string.quote_price),bean.offerProdPrice+"元"));
        infoLis.add(new PurchaseBasicInfo(getStr(R.string.quote_stock),bean.offerProdStore));
        infoLis.add(new PurchaseBasicInfo(getStr(R.string.quote_time),bean.offerTime));
        infoLis.add(new PurchaseBasicInfo(getStr(R.string.quote_remark),bean.offerContent));
        rvQuote.setAdapter(new PurchaseDetailBasicAdapter(R.layout.rv_purchase_detail_basic,infoLis,bean.id));

        final ImageView ivItem = (ImageView) holder.itemView.findViewById(R.id.iv_quote_pic);
        DisplayMetrics dm = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels - DensityUtil.dp2px(mContext,30);
        ViewGroup.LayoutParams lp = ivItem.getLayoutParams();
        lp.width = screenWidth;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        ivItem.setLayoutParams(lp);
        ivItem.setMaxWidth(screenWidth);
        ivItem.setMaxHeight(screenWidth * 10);

        if (TextUtils.isEmpty(bean.offerProdImage)){
            holder.setVisibility(R.id.iv_quote_pic,false);
        }else {
            loadGood(bean.offerProdImage,R.id.iv_quote_pic);
        }
        if (holder.getAdapterPosition()==(getData().size()-1)){
            holder.setVisibility(R.id.tv_no_more,true);
            holder.setVisibility(R.id.space,false);
        }else {
            holder.setVisibility(R.id.tv_no_more,false);
            holder.setVisibility(R.id.space,true);
        }
        if (!TextUtils.isEmpty(bean.isIntention)){
            holder.setVisibility(R.id.iv_attention,bean.isIntention.equals("1"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openQuote = new Intent(mContext,ContainerActivity.class);
                openQuote.putExtra(Constant.TYPE,Constant.QUOTE);
                openQuote.putExtra(Msg.IS_CUO,isCuo);
                openQuote.putExtra(Msg.QUOTE_ID,bean.id);
                mContext.startActivity(openQuote);
            }
        });
    }
}

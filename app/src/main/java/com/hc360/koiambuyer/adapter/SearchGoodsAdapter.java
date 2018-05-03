package com.hc360.koiambuyer.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.SearchInfo;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.utils.GlideUtils;
import com.hc360.koiambuyer.utils.TimeTool;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseHolder;
import com.hc360.koiambuyer.view.find.SearchActivity;
import com.hc360.koiambuyer.view.good.GoodsDetailActivity;
import com.hc360.koiambuyer.view.home.PublishActivity;

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

public class SearchGoodsAdapter extends BaseAdapter<SearchInfo.ListBean> {
    SearchActivity mActivity;
    public SearchGoodsAdapter(int layoutResId, List<SearchInfo.ListBean> data) {
        super(layoutResId, data);
    }

    public SearchGoodsAdapter(int layoutResId, List<SearchInfo.ListBean> data, SearchActivity activity) {
        super(layoutResId, data);
        mActivity = activity;
    }

    @Override
    protected void convert(BaseHolder holder, final SearchInfo.ListBean bean) {

        holder.setVisibility(R.id.tv_foot,mActivity.isNoLong&&holder.getAdapterPosition() == getData().size());
        holder.setVisibility(R.id.ll_foot,mActivity.isNoLong&&holder.getAdapterPosition() == getData().size());
        if (mActivity==null){
            holder.setVisibility(R.id.foot_line,mActivity.isNoLong&&holder.getAdapterPosition() == getData().size());
        }
        holder.itemView.findViewById(R.id.tv_publish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, PublishActivity.class));
            }
        });
        ImageView ivHead = (ImageView) holder.itemView.findViewById(R.id.iv_head);
        ImageView ivGoods = (ImageView) holder.itemView.findViewById(R.id.iv_goods);
        GlideUtils.loadHead(mContext,bean.korCoreUserVo.headImg,ivHead);
        GlideUtils.loadGood(mContext,bean.loopImg001,ivGoods);
        holder.setText(R.id.tv_user,bean.korCoreUserVo.userName);
        holder.setText(R.id.tv_title,bean.productName);
        holder.setText(R.id.tv_chat_num,bean.recordCount+"");
//        holder.setText(R.id.tv_keep_num,bean.lookCount+"");
        holder.setText(R.id.tv_keep_num,"0");
        holder.setText(R.id.tv_money, TimeTool.getLastTime(bean.createTime));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGoodDetail = new Intent(mContext, GoodsDetailActivity.class);
                openGoodDetail.putExtra(Msg.PRODUCT_ID,bean.productId);
                mContext.startActivity(openGoodDetail);
            }
        });
    }

}

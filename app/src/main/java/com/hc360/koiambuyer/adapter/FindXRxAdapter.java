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
import com.hc360.koiambuyer.view.good.GoodsDetailActivity;

import java.util.List;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/2
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class FindXRxAdapter extends BaseAdapter<SearchInfo.ListBean> {

    public FindXRxAdapter(int layoutId, List<SearchInfo.ListBean> list) {
        super(layoutId, list);
    }

    @Override
    protected void convert(final BaseHolder holder, final SearchInfo.ListBean bean) {
        GlideUtils.loadGood(mContext,bean.loopImg001,(ImageView)holder.itemView.findViewById(R.id.iv_goods));
        GlideUtils.loadHead(mContext,bean.korCoreUserVo.headImg,(ImageView) holder.itemView.findViewById(R.id.iv_head));
        holder.setText(R.id.tv_user,bean.korCoreUserVo.userName);
        holder.setText(R.id.tv_title,bean.productName);
        holder.setText(R.id.tv_money, TimeTool.getLastTime(bean.createTime));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGoodDetail = new Intent(mContext, GoodsDetailActivity.class);
                openGoodDetail.putExtra(Msg.PRODUCT_ID,bean.productId);
                mContext.startActivity(openGoodDetail);
            }
        });
        holder.setText(R.id.tv_chat_num,bean.recordCount+"");
        holder.setText(R.id.tv_keep_num,"0");
    }
}

package com.hc360.koiambuyer.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.OrderInfo;
import com.hc360.koiambuyer.engine.OrderStateTypeEnum;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.utils.TimeTool;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseHolder;
import com.hc360.koiambuyer.view.good.StockActivity;
import com.hc360.koiambuyer.view.me.OrderFragment;
import com.hc360.koiambuyer.view.order.OrderDetailActivity;

import java.math.BigDecimal;
import java.util.List;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/30
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class OrderAdapter extends BaseAdapter<OrderInfo.ListBean>{
    OrderFragment mFragment;
    public OrderAdapter(int layoutId, List<OrderInfo.ListBean> list, OrderFragment fragment) {
        super(layoutId, list);
        mFragment = fragment;
    }

    @Override
    protected void convert(BaseHolder holder, final OrderInfo.ListBean bean) {
        holder.setVisibility(R.id.space,holder.getAdapterPosition() != 1);
        holder.setText(R.id.tv_time, TimeTool.getDay(bean.createTime));
        TextView tvCancel = (TextView) holder.itemView.findViewById(R.id.tv_cancel);
        TextView tvAgain = (TextView) holder.itemView.findViewById(R.id.tv_again);
        TextView tvSure = (TextView) holder.itemView.findViewById(R.id.tv_sure);
        if (bean.orderStat.equals(OrderStateTypeEnum.PENDING_DELIVERY.getValue())){
            //待发货
            holder.setVisibility(R.id.ll,true);
            tvCancel.setVisibility(View.VISIBLE);
            tvAgain.setVisibility(View.GONE);
            tvSure.setVisibility(View.GONE);
            holder.setText(R.id.tv_state,OrderStateTypeEnum.PENDING_DELIVERY.getDesc());
        }else if (bean.orderStat.equals(OrderStateTypeEnum.DELIVER_GOODS.getValue())){
            //已发货
            holder.setVisibility(R.id.ll,true);
            tvCancel.setVisibility(View.GONE);
            tvAgain.setVisibility(View.VISIBLE);
            tvSure.setVisibility(View.VISIBLE);
            holder.setText(R.id.tv_state,OrderStateTypeEnum.DELIVER_GOODS.getDesc());
        }else if (bean.orderStat.equals(OrderStateTypeEnum.SUCCESS.getValue())){
            //已完成
            holder.setVisibility(R.id.line,false);
            holder.setVisibility(R.id.ll,false);
            holder.setText(R.id.tv_state,OrderStateTypeEnum.SUCCESS.getDesc());
        }else if (bean.orderStat.equals(OrderStateTypeEnum.cancel.getValue())){
            //已取消
            holder.setVisibility(R.id.ll,true);
            tvCancel.setVisibility(View.GONE);
            tvAgain.setVisibility(View.VISIBLE);
            tvSure.setVisibility(View.GONE);
            holder.setText(R.id.tv_state,OrderStateTypeEnum.cancel.getDesc());
        }
        loadGood(bean.loopImg001,R.id.iv_good);
        holder.setText(R.id.tv_title,bean.productName);
        //金额区间
        boolean isSample = false;
        if (bean.orderSimprice != null){
            if (bean.orderSimprice.compareTo(BigDecimal.ZERO)==1){
                isSample = true;
            }
        }
        holder.setText(R.id.tv_price_about,getStr(R.string.money_unit)+(isSample?bean.orderSimunitpri:bean.orderUnitpri));
        holder.setText(R.id.tv_content,(isSample?getStr(R.string.order_sample):getStr(R.string.order_good))+(isSample?bean.orderSimcount:bean.orderCount)+bean.productUnit+"  "+getStr(R.string.order_price));
        holder.setText(R.id.tv_price,getStr(R.string.money_unit)+bean.money);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment.cancelOrder(bean.orderNo,bean.orderStat);
            }
        });

        final boolean finalIsSample = isSample;
        tvAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openStock = new Intent(mContext, StockActivity.class);
                openStock.putExtra(Constant.STOCK_SAMPLE, finalIsSample);
                openStock.putExtra(Msg.ORDERNO,bean.orderNo);
                openStock.putExtra(Msg.DELIVER_ID,bean.id);
                openStock.putExtra(Msg.GOOD_ID,bean.productId);
                openStock.putExtra(Msg.GOOD_NUM,finalIsSample?bean.orderSimcount:bean.orderCount);
                openStock.putExtra(Msg.GOOD_MSG,bean.money+"");
                openStock.putExtra(Msg.GOOD_PRICE,(finalIsSample?bean.orderSimunitpri:bean.orderUnitpri)+"");
                openStock.putExtra(Msg.GOOD_NAME,bean.productName);
                openStock.putExtra(Msg.GOOD_PIC,bean.loopImg001);
                openStock.putExtra(Msg.GOOD_MAX_PRICE,(finalIsSample?bean.orderSimunitpri:bean.maxPice)+"");
                openStock.putExtra(Msg.GOOD_MIN_PRICE,(finalIsSample?bean.orderSimunitpri:bean.minPice)+"");
                openStock.putExtra(Msg.GOOD_MIN_NUM,finalIsSample?1:bean.minNumber);
                //这里还有判断库存
                openStock.putExtra(Msg.GOOD_MAX_NUM,finalIsSample?bean.productMaxcount:(bean.productTotal>bean.maxNumber?bean.maxNumber:bean.productTotal));
                openStock.putExtra(Msg.GOOD_GOOD_ID,bean.goodsId);
                openStock.putExtra(Msg.GOOD_DESC,bean.orderContent);
                mContext.startActivity(openStock);
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment.sureOrder(bean.orderNo);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openOrderDetail = new Intent(mContext, OrderDetailActivity.class);
                openOrderDetail.putExtra(Msg.ORDERNO,bean.orderNo);
                mContext.startActivity(openOrderDetail);
            }
        });
    }
}

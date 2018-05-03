package com.hc360.koiambuyer.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.GoodsDetailInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.utils.GlideUtils;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.good.StockActivity;
import com.orhanobut.logger.Logger;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Project: MyDemo
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/12
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class StockDialog extends Dialog {

    Context mContext;
    @BindView(R.id.snv)
    StockNumView mSnv;
    @BindView(R.id.iv_good)
    ImageView mIvGood;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.tv_num)
    TextView mTvNum;
    GoodsDetailInfo mInfo;

    boolean isSample;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.stv_submit)
    SingleTextView mStvSubmit;
    private int mMaxNum;
    private int mMinNumber;
    private int mNum;
    private String mMsg;
    private BigDecimal mPrice;

    public StockDialog(Context context) {
        // 在构造方法里, 传入主题
        super(context, R.style.BottomDialogTranStyle);
        this.mContext = context;
    }

    public StockDialog(Context context, GoodsDetailInfo info, boolean sample) {
        this(context);
        mInfo = info;
        isSample = sample;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_stock_layout);
        ButterKnife.bind(this);
        // 拿到Dialog的Window, 修改Window的属性
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        // 获取Window的LayoutParams
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        // 一定要重新设置, 才能生效
        window.setAttributes(attributes);
        GlideUtils.loadGood(mContext, mInfo.content.spProduct.loopImg001, mIvGood);
        mPrice = new BigDecimal("0");
        mTvPrice.setText(isSample ? getStr(R.string.money_unit) + (mPrice = mInfo.content.spProduct.productMoney) : (getStr(R.string.money_unit) + (mPrice = mInfo.content.minPice) + "~" + getStr(R.string.money_unit) + mInfo.content.maxPice));
        mTvTitle.setText(mInfo.content.spProduct.productName);
        mMaxNum = isSample ? mInfo.content.spProduct.productMaxcount : mInfo.content.spProduct.productTotal;
        Logger.e("isSample"+mInfo.content.spProduct.productMaxcount+",isGood"+mInfo.content.spProduct.productTotal+",productUnit"+mInfo.content.spProduct.productUnit);
        Logger.e(isSample + "" + (isSample ? mInfo.content.spProduct.productMaxcount : mInfo.content.spProduct.productTotal));
        mMinNumber = isSample ? 1 : mInfo.content.SpProductPice.get(0).minNumber;
        mNum = mMinNumber;
        mSnv.initSnv(isSample, mMaxNum, mMinNumber);
        String unit = mInfo.content.spProduct.productUnit;
        mTvNum.setText(getStr(R.string.stock_num)+mInfo.content.spProduct.productTotal +(TextUtils.isEmpty(unit)?"":unit) );

        initMoney();
        mStvSubmit.setText(mMsg+"  "+getStr(R.string.good_detail_get_good));

        mSnv.setOnStockNumListener(new StockNumView.OnStockNumListener() {
            @Override
            public void onStockNumChange(String sample) {
                if (!TextUtils.isEmpty(sample)) {
                    mNum = Integer.parseInt(sample);
                    if (!isSample) {
                        List<GoodsDetailInfo.ContentBean.SpProductPiceBean> spProductPice = mInfo.content.SpProductPice;
                        BigDecimal price = new BigDecimal("0");
                        if (mNum >= spProductPice.get(spProductPice.size() - 1).maxNumber) {
                            price = spProductPice.get(spProductPice.size() - 1).price;
                        }
                        if (mNum <= spProductPice.get(0).minNumber) {
                            price = spProductPice.get(0).price;
                        } else {
                            for (GoodsDetailInfo.ContentBean.SpProductPiceBean spProductPiceBean : spProductPice) {
                                if (mNum > spProductPiceBean.minNumber && mNum <= spProductPiceBean.maxNumber) {
                                    price = spProductPiceBean.price;
                                    break;
                                }
                            }
                        }
                        mPrice = price;
                    }
                    initMoney();
                    mStvSubmit.setText(mMsg+"  "+getStr(R.string.good_detail_get_good));
                } else {
                    mStvSubmit.setText(getStr(R.string.money_unit) + (0.00)+"  "+getStr(R.string.good_detail_get_good));
                }
            }
        });
    }

    private void initMoney() {
        BigDecimal n1 = new BigDecimal(mNum);
        BigDecimal pr = mPrice.multiply(n1);
        pr = pr.setScale(2);
        mMsg = getStr(R.string.money_unit) + pr;
    }

    @OnClick(R.id.stv_submit)
    public void onClick() {
        if (mNum < mMinNumber) {
            ToastUtil.showShort(mContext, isSample ? getStr(R.string.out_of_sample_min) : getStr(R.string.out_of_good_min));
        } else if (mNum > mMaxNum) {
            ToastUtil.showShort(mContext, isSample ? getStr(R.string.out_of_sample_max) : getStr(R.string.out_of_good_max));
        } else {
            dismiss();
            Intent openStock = new Intent(mContext, StockActivity.class);
            openStock.putExtra(Constant.STOCK_SAMPLE, isSample);
            openStock.putExtra(Msg.GOOD_INFO, mInfo);
            openStock.putExtra(Msg.GOOD_NUM, mNum);
            openStock.putExtra(Msg.GOOD_MSG, mMsg);
            openStock.putExtra(Msg.GOOD_PRICE, mPrice + "");
            mContext.startActivity(openStock);
        }
    }

    public String getStr(Integer strRes) {
        return mContext.getResources().getString(strRes);
    }
}

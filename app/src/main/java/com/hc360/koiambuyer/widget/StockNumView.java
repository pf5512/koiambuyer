package com.hc360.koiambuyer.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.GoodsDetailInfo;
import com.hc360.koiambuyer.engine.LimitInputNumWatcher;
import com.hc360.koiambuyer.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/12
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class StockNumView extends LinearLayout {

    @BindView(R.id.tv_type)
    TextView mTvType;
    private Integer mMinNum = 0;
    private Integer mMaxNum = 1000000;
    @BindView(R.id.iv_sample_minus)
    ImageView mIvSampleMinus;
    @BindView(R.id.et_sample)
    EditText mEtSample;
    @BindView(R.id.iv_sample_add)
    ImageView mIvSampleAdd;
    @BindView(R.id.ll_sample)
    LinearLayout mLlSample;

    private Context mContext;
    OnStockNumListener listener;
    private boolean isSample;


    List<GoodsDetailInfo.ContentBean.SpProductPiceBean> mPrices;

    public StockNumView(Context context) {
        this(context, null);
    }

    public StockNumView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StockNumView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        View.inflate(mContext, R.layout.layout_stock_num, this);
        ButterKnife.bind(this);
        mEtSample.setSelection(getSampleText().length());
        mEtSample.addTextChangedListener(new LimitInputNumWatcher(mEtSample) {
            @Override
            public void afterTextChanged(Editable editable) {
                mEtSample.setSelection(getSampleText().length());
                if (TextUtils.isEmpty(getSampleText())){
                    mIvSampleMinus.setImageResource(R.mipmap.ic_reduce_no);
                }else {
                   if (Integer.parseInt(getSampleText())<=mMinNum){
                       mIvSampleMinus.setImageResource(R.mipmap.ic_reduce_no);
                   }else {
                       mIvSampleMinus.setImageResource(R.mipmap.ic_reduce);
                   }
                   if (Integer.parseInt(getSampleText())>=mMaxNum){
                       mIvSampleAdd.setImageResource(R.mipmap.ic_add_no);
                   }else {
                       mIvSampleAdd.setImageResource(R.mipmap.ic_add);
                   }
                }
                if (listener != null) {
                    listener.onStockNumChange(getSampleText());
                }
            }
        });
    }

    @OnClick({R.id.fl_sample_minus, R.id.fl_sample_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_sample_minus:
                if (!TextUtils.isEmpty(getSampleText())) {
                    int sample = Integer.parseInt(getSampleText());
                    if (sample > mMinNum) {
                        sample--;
                        mEtSample.setText(sample + "");
                    }
                }
                break;
            case R.id.fl_sample_add:
                if (TextUtils.isEmpty(getSampleText())) {
                    mEtSample.setText(String.valueOf(mMinNum));
                } else {
                    int sample = Integer.parseInt(getSampleText());
                    if (sample < mMaxNum) {
                        sample++;
                        mEtSample.setText(sample + "");
                    } else {
                        ToastUtil.showShort(mEtSample.getContext(), isSample ? getStr(R.string.out_of_sample_max) : getStr(R.string.out_of_good_max));
                    }
                }
                break;

        }
    }

    public void initSnv(boolean sample, int max, int min) {
        isSample = sample;
        mMaxNum = max;
        mMinNum = min;
        mEtSample.setText(min+"");
        mEtSample.setSelection((min+"").length());
        mTvType.setText(isSample?getStr(R.string.sample_num):getStr(R.string.goods_num));
    }

    public String getSampleText() {
        String trim = mEtSample.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            return "";
        } else {
            return trim;
        }
    }
    public void setSampleText(String text) {
        mEtSample.setText(text);
    }


    public interface OnStockNumListener {
        void onStockNumChange(String sample);
    }

    public void setOnStockNumListener(OnStockNumListener onStockNumListener) {
        listener = onStockNumListener;
    }

    public String getStr(Integer strRes) {
        return mEtSample.getContext().getResources().getString(strRes);
    }


}

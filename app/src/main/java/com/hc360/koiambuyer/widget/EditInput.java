package com.hc360.koiambuyer.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.engine.CustomTextWatcher;
import com.hc360.koiambuyer.engine.LimitInputNumWatcher;
import com.hc360.koiambuyer.engine.MaxNumTextWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/12
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class EditInput extends LinearLayout {

    private static final float PADDING_DEFAULT = 20;
    private static final float PADDING_DEFAULT_LEFT = 15;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.et_input)
    EditText mEtInput;
    @BindView(R.id.tv_et_hint)
    TextView mTvEtHint;
    @BindView(R.id.ll)
    LinearLayout mLl;

    public EditInput(Context context) {
        this(context, null);
    }

    public EditInput(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EditInput(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        View.inflate(context, R.layout.layout_et, this);
        ButterKnife.bind(this);
        try {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
            int paddingBottom = a.getDimensionPixelSize(R.styleable.EditInput_ei_paddingBottom, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, PADDING_DEFAULT, getResources().getDisplayMetrics()));
            int paddingLeft = a.getDimensionPixelSize(R.styleable.EditInput_ei_paddingLeft, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, PADDING_DEFAULT_LEFT, getResources().getDisplayMetrics()));
            int paddingRight = a.getDimensionPixelSize(R.styleable.EditInput_ei_paddingRight, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, PADDING_DEFAULT_LEFT, getResources().getDisplayMetrics()));
            int paddingTop = a.getDimensionPixelSize(R.styleable.EditInput_ei_paddingTop, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, PADDING_DEFAULT, getResources().getDisplayMetrics()));
            mLl.setPadding(paddingLeft,0,0,0);
            mEtInput.setPadding(0,paddingTop,paddingRight,paddingBottom);
            mTvEtHint.setPadding(0,paddingTop,10,paddingBottom);
        }catch (Exception e){

        }
        mEtInput.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    mTvEtHint.setVisibility(GONE);
                }else {
                    if (!TextUtils.isEmpty(mEtInput.getText())){
                        mTvEtHint.setVisibility(GONE);
                    }else {
                        mTvEtHint.setVisibility(VISIBLE);
                    }
                }
            }
        });
        mEtInput.addTextChangedListener(new CustomTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                mTvEtHint.setVisibility(TextUtils.isEmpty(s) ? View.VISIBLE : View.GONE);
            }
        });
    }

    public void initText(String title,String contentHint){
        mTvTitle.setText(title);
        mTvEtHint.setText(contentHint);
    }
    public void setEtText(String text){
        mEtInput.setText(text);
        mEtInput.setSelection(text.length());
    }

    public String getEtText(){
        return mEtInput.getText().toString().trim();
    }

    public void setEtMaxNum(int max){
        mEtInput.addTextChangedListener(new MaxNumTextWatcher(max, mEtInput));
    }
    public void setEtMaxNum(){
        mEtInput.addTextChangedListener(new LimitInputNumWatcher(mEtInput));
    }

    public void setEtInputType(int type){
        mEtInput.setInputType(type);
    }

    public void setNext(){
        mEtInput.setSingleLine();
    }
}

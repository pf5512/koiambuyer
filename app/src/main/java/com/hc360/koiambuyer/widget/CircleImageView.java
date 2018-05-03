package com.hc360.koiambuyer.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.hc360.koiambuyer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/9
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class CircleImageView extends CardView {
    private static final float HEIGHT_DEFAULT = 41;
    @BindView(R.id.iv_head)
    ImageView mIvHead;
    @BindView(R.id.card_view)
    CardView mCardView;

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        View.inflate(context, R.layout.layout_circle_imageview, this);
        ButterKnife.bind(this);
        try {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
            int radius = a.getDimensionPixelSize(R.styleable.CircleImageView_civ_radius, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, HEIGHT_DEFAULT, getResources().getDisplayMetrics()));
            mCardView.setRadius(radius);
        }catch (Exception e){

        }
    }

    public ImageView getIvHead() {
        return mIvHead;
    }
}

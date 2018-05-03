package com.hc360.koiambuyer.adapter;

import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.utils.DensityUtil;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseFragment;
import com.hc360.koiambuyer.view.base.BaseHolder;
import com.hc360.koiambuyer.view.purchase.QuoteFragment;

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

public class QuotePicAdapter extends BaseAdapter<String> {
    BaseFragment mFragment;

    public QuotePicAdapter(int layoutId, List<String> list, QuoteFragment fragment) {
        super(layoutId, list);
        mFragment =fragment;
    }

    @Override
    protected void convert(BaseHolder holder, String bean) {
        final ImageView ivItem = (ImageView) holder.itemView.findViewById(R.id.iv_pic);
        DisplayMetrics dm = new DisplayMetrics();
        mFragment.getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels - DensityUtil.dp2px(mContext,30); // 获取屏幕宽度
        ViewGroup.LayoutParams lp = ivItem.getLayoutParams();
        lp.width = screenWidth;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        ivItem.setLayoutParams(lp);
        ivItem.setMaxWidth(screenWidth);
        ivItem.setMaxHeight(screenWidth * 10); //这里其实可以根据需求而定，我这里测试为最大宽度的5倍

        if (!TextUtils.isEmpty(bean)){
            loadGood(bean, R.id.iv_pic);
        }
    }
}

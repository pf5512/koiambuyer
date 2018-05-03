package com.hc360.koiambuyer.adapter;

import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.utils.DensityUtil;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseHolder;
import com.hc360.koiambuyer.view.purchase.PurchaseDetailActivity;

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

public class PurchaseDetailPicAdapter extends BaseAdapter<String> {
    PurchaseDetailActivity mActivity;
    public PurchaseDetailPicAdapter(int layoutId, List<String> list, PurchaseDetailActivity activity) {
        super(layoutId, list);
        mActivity = activity;
    }

    @Override
    protected void convert(BaseHolder holder, String bean) {
        final ImageView ivItem = (ImageView) holder.itemView.findViewById(R.id.iv_pic);
        DisplayMetrics dm = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels - DensityUtil.dp2px(mContext,30);
        ViewGroup.LayoutParams lp = ivItem.getLayoutParams();
        lp.width = screenWidth;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        ivItem.setLayoutParams(lp);
        ivItem.setMaxWidth(screenWidth);
        ivItem.setMaxHeight(screenWidth * 10);

        if (!TextUtils.isEmpty(bean)){
            loadGood(bean, R.id.iv_pic);
        }
    }
}

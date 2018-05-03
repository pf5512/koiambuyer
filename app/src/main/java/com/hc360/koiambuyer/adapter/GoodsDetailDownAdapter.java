package com.hc360.koiambuyer.adapter;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.GoodsDetailInfo;
import com.hc360.koiambuyer.utils.GlideUtils;
import com.hc360.koiambuyer.utils.PopUtils;
import com.hc360.koiambuyer.view.good.GoodsDetailActivity;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/23
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class GoodsDetailDownAdapter extends BaseQuickAdapter<GoodsDetailInfo.ContentBean.SpProductImgSBean> {
    GoodsDetailActivity mActivity;
    public GoodsDetailDownAdapter(int layoutResId, List data, GoodsDetailActivity activity) {
        super(layoutResId, data);
        this.mActivity = activity;
    }

    @Override
    protected void convert(final BaseViewHolder holder, final GoodsDetailInfo.ContentBean.SpProductImgSBean bean) {
        final ImageView ivItem = (ImageView) holder.itemView.findViewById(R.id.iv_item);
        DisplayMetrics dm = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels; // 获取屏幕宽度
        ViewGroup.LayoutParams lp = ivItem.getLayoutParams();
        lp.width = screenWidth;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        ivItem.setLayoutParams(lp);
        ivItem.setMaxWidth(screenWidth);
        ivItem.setMaxHeight(screenWidth * 20); //这里其实可以根据需求而定，我这里测试为最大宽度的20倍

        GlideUtils.loadGood(mContext,bean.imgName,ivItem);
//        Glide.with(mContext).load(bean.imgName).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).into(new SimpleTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                ivItem.setImageBitmap(resource);
//            }
//        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUtils.showOnlyPic(mActivity,mActivity.head,bean.imgName);
            }
        });
    }
}

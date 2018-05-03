package com.hc360.koiambuyer.adapter;

import android.view.View;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.PostPicInfo;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseHolder;
import com.hc360.koiambuyer.view.home.PublishActivity;

import java.util.List;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/22
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class PublishPicAdapter extends BaseAdapter<PostPicInfo>{

    PublishActivity mActivity;

    public PublishPicAdapter(int layoutId, List<PostPicInfo> list, PublishActivity activity) {
        super(layoutId, list);
        mActivity = activity;
    }

    @Override
    protected void convert(final BaseHolder holder, final PostPicInfo bean) {

//        final ImageView ivItem = (ImageView) holder.itemView.findViewById(R.id.iv_pic);
//        DisplayMetrics dm = new DisplayMetrics();
//        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int screenWidth = dm.widthPixels; // 获取屏幕宽度
//        ViewGroup.LayoutParams lp = ivItem.getLayoutParams();
//        lp.width = screenWidth;
//        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//        ivItem.setLayoutParams(lp);
//        ivItem.setMaxWidth(screenWidth);
//        ivItem.setMaxHeight(screenWidth * 20); //这里其实可以根据需求而定，我这里测试为最大宽度的20倍

        loadGood(bean.httpUrl, R.id.iv_pic);
        holder.itemView.findViewById(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.removePic(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
    }
}

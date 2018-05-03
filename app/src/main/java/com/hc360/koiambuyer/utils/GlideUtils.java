package com.hc360.koiambuyer.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.view.base.BaseHolder;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/9
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class GlideUtils {
    public static void loadHead(Context context, String picUrl, ImageView iv){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.buyer_head);
        requestOptions.error(R.mipmap.buyer_head);
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(picUrl).into(iv);
    }

    public static void loadHead(Context context, String picUrl, BaseHolder hold,int ivRes){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.buyer_head);
        requestOptions.error(R.mipmap.buyer_head);
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(picUrl).into((ImageView) hold.itemView.findViewById(ivRes));
    }

    public static void loadGood(Context context, Object picUrl, ImageView iv){

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.good_default);
        requestOptions.error(R.mipmap.good_default);
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(picUrl).into(iv);
    }

    public static void loadGood(Context context, Object picUrl,  BaseHolder hold,int ivRes){

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.good_default);
        requestOptions.error(R.mipmap.good_default);
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(picUrl).into((ImageView) hold.itemView.findViewById(ivRes));
    }
}

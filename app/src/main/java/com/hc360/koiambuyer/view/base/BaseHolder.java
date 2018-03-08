package com.hc360.koiambuyer.view.base;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/12/23.
 */

public class BaseHolder extends RecyclerView.ViewHolder {

    private HashMap<Integer, View> mViews = new HashMap<>();
    public BaseHolder(View itemView) {
        super(itemView);
    }
    //根据保存变量的类型 强转为该类型
    public <T> T getView(Integer viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            //缓存
            mViews.put(viewId, view);
        }
        return (T) view;
    }
    public BaseHolder setText(Integer viewId, String value) {
        TextView textView = getView(viewId);
        if (textView != null) {
            textView.setText(value);
        }
        return this;
    }
    public BaseHolder setImageResource(Integer viewId, Integer resId) {
        ImageView imageView = getView(viewId);
        if (imageView != null) {
            imageView.setImageResource(resId);
        }
        return this;
    }
    public BaseHolder setImageBitmap(Integer viewId, Bitmap bitmap) {
        ImageView imageView = getView(viewId);
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        }
        return this;
    }
    public BaseHolder setVisibility(Integer viewId, boolean visible) {
        View view = getView(viewId);
        if (view != null) {
            view.setVisibility(visible?View.VISIBLE:View.GONE);
        }
        return this;
    }

}

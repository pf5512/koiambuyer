package com.hc360.koiambuyer.view.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hc360.koiambuyer.utils.ImageUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.封装时，可以选择由外部的构造函数或者set方法
 */

public abstract class BaseAdapter <T> extends RecyclerView.Adapter<BaseHolder> {
    private List<T> mList = new ArrayList<>();
    private int layoutId;
    public Context mContext;
    public BaseHolder mHolder;
    //onCreateViewHolder用来给rv创建缓存
    public BaseAdapter(int layoutId, List<T> list) {
        this.layoutId = layoutId;
        this.mList = list;
    }
    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //   View itemView=View.inflate(parent.getContext(),R.layout.item_simple,null);
        //参数3 判断条件 true  1.打气 2.添加到paraent
        //false 1.打气 （参考parent的宽度）
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        BaseHolder holder = new BaseHolder(itemView);
        mContext = parent.getContext();
        return holder;
    }

    //onBindViewHolder给缓存控件设置数据
    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        //获取数据
        T item = mList.get(position);
        mHolder = holder;
        convert(holder,item);
    }
    protected abstract void convert(BaseHolder holder, T bean) ;
    //获取记录数
    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<T> getData(){
        return mList;
    }
    public String getStr(int resId){
        return mContext.getResources().getString(resId);
    }

    public View findView(int idRes){
        return mHolder.itemView.findViewById(idRes);
    }


    public void loadHead(String picUrl,int ivRes){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(ImageUtil.getToHead());
        requestOptions.error(ImageUtil.getToHead());
        Glide.with(mContext)
                .setDefaultRequestOptions(requestOptions)
                .load(picUrl)
                .into((ImageView) mHolder.itemView.findViewById(ivRes));
    }

    public void loadGood(Object picUrl,int ivRes){
        if (picUrl != null){
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(ImageUtil.getGoodDefault());
            requestOptions.error(ImageUtil.getGoodDefault());
            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
                    .load(picUrl)
                    .into((ImageView) mHolder.itemView.findViewById(ivRes));
        }

    }
}
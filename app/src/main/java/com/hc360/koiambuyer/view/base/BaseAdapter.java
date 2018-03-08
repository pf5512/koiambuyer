package com.hc360.koiambuyer.view.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.封装时，可以选择由外部的构造函数或者set方法
 */

public abstract class BaseAdapter <T> extends RecyclerView.Adapter<BaseHolder> {
    private List<T> mList = new ArrayList<>();
    private int layoutId;
    public Context mContext;
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
}
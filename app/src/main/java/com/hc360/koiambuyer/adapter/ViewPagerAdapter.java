package com.hc360.koiambuyer.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/17
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class ViewPagerAdapter extends PagerAdapter {

    List<View> mPageView;
    View mCurrentView;
    OnViewPagerItemListener mListener;

    public ViewPagerAdapter(List<View> pageView) {
        this.mPageView = pageView;
    }

    @Override
    public int getCount() {
        return mPageView.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (View) object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        mPageView.get(position).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener !=null){
                    mListener.onViewPagerItemClick(position);
                }
            }
        });
        container.addView(mPageView.get(position));
        return mPageView.get(position);
    }

    public void setData(List<View> pageView){
        this.mPageView = pageView;
        notifyDataSetChanged();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mCurrentView = (View)object;
    }

    public View getPrimaryItem() {
        return mCurrentView;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public List<View> getData() {
        return mPageView;
    }

    public interface OnViewPagerItemListener {
        void onViewPagerItemClick(int position);
    }

    public void setOnViewPagerItemListener(OnViewPagerItemListener listener){
        mListener = listener;
    }
}

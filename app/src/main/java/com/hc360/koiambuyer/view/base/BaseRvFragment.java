package com.hc360.koiambuyer.view.base;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.MyApp;
import com.hc360.koiambuyer.widget.AutoLoadRecyclerView;
import com.hc360.koiambuyer.widget.EmptyLayout;

import butterknife.BindView;



/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/25
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public abstract class BaseRvFragment<T extends IBasePresenter,M extends BaseQuickAdapter,I> extends BaseFragment<T> implements IBaseListView<I>{

    @Nullable
    @BindView(R.id.rv_list)
    AutoLoadRecyclerView mRv;
    @Nullable
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;
    @Nullable
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    M mAdapter;

    @Override
    protected void initViews() {

    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
        mRv.setLoadMoreListener(new AutoLoadRecyclerView.loadMoreListener() {
            @Override
            public void onLoadMore() {
                mPager++;
                getData();
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        if (isRefresh){
            mPager = 1;
            getData();
        }
    }

    @Override
    public void setAdapter(I i) {
        if (getList(i).size()>0){
            hideLoading();
            if (mPager == 1){
                initAdapter(i);
            }else{
                mAdapter.getData().addAll(getList(i));
                mAdapter.notifyDataSetChanged();
            }
            mSwipeRefresh.setRefreshing(false);
            mRv.setLoading(false);
        }else{
            mSwipeRefresh.setRefreshing(false);
            if (mPager == 1) {
                initAdapter(i);
                showEmpty();
            } else {
                hideLoading();
                ToastUtil.showShort(MyApp.getAppContext(),getResources().getString(R.string.no_more_data));
            }
        }
    }

    public void initAdapter(I i){
        if (mAdapter == null){
            initRvLayoutManager();
            mAdapter = (M) newAdapter(i);
            mRv.setAdapter(mAdapter);
        }else{
            mAdapter.getData().clear();
            mAdapter.getData().addAll(getList(i));
            mAdapter.notifyDataSetChanged();
        }
    }
    public void initRvLayoutManager(){
        mRv.setLayoutManager(new LinearLayoutManager(mContext));
    }
    public void showEmpty(){
        showNoData();
    }

}

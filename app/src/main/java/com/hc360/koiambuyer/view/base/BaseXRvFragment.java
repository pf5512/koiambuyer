package com.hc360.koiambuyer.view.base;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.MyApp;
import com.hc360.koiambuyer.widget.EmptyLayout;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

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

public abstract class BaseXRvFragment<T extends IBasePresenter,M extends BaseAdapter,I> extends BaseFragment<T> implements IBaseXRvView<I>{

    @Nullable
    @BindView(R.id.rv_list)
    XRecyclerView mXRv;
    @Nullable
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;
    M mAdapter;
    boolean isRefresh = false;
    boolean isBack = false;

    @Override
    public void onResume() {
        super.onResume();
        getData();
        isBack = (mPager != 1);
        mXRv.setPullRefreshEnabled(true);
        mXRv.setLoadingMoreEnabled(true);
        mXRv.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mXRv.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        mXRv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPager = 1;
                isRefresh = true;
                getData();
                isBack = false;
            }

            @Override
            public void onLoadMore() {
                mPager++;
                getData();
                isBack = false;
            }
        });
    }

    @Override
    public void showLoading() {
        if (mEmptyLayout != null && mPager == 1) {
            if (!isRefresh){
                mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
            }
        }
    }
    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void setAdapter(I i) {
        if (getList(i).size()>0){
            hideLoading();
            if (mPager == 1){
                isRefresh = false;
                initAdapter(i);
                mXRv.refreshComplete();
            }else{
                mXRv.loadMoreComplete();
                mAdapter.getData().addAll(getList(i));
                mAdapter.notifyDataSetChanged();
            }
        }else{
            mXRv.loadMoreComplete();
            if (mPager == 1) {
                isRefresh = false;
                initAdapter(i);
                showEmpty();
            } else {
                hideLoading();
                if (!isBack){
                    ToastUtil.showShort(MyApp.getAppContext(),getStr(R.string.no_more_data));
                }

            }
        }
    }

    public void initAdapter(I i){
        if (mAdapter == null){
            initRvLayoutManager();
            mAdapter = (M) newAdapter(i);
            mXRv.setAdapter(mAdapter);
        }else{
            mAdapter.getData().clear();
            mAdapter.getData().addAll(getList(i));
            mAdapter.notifyDataSetChanged();
        }
    }

    public void initRvLayoutManager(){
        mXRv.setLayoutManager(new LinearLayoutManager(mContext));
    }

    public void showEmpty(){
        showNoData();
    }
}

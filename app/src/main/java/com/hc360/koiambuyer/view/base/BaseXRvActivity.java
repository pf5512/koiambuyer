package com.hc360.koiambuyer.view.base;

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

public abstract class BaseXRvActivity<T extends IBasePresenter,M extends BaseAdapter,I> extends BaseActivity<T> implements IBaseXRvView<I>{

    @BindView(R.id.rv_list)
    XRecyclerView mXRv;
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;
    boolean isRefresh = false;

    public int mPager = 1;
    M mAdapter;


    @Override
    protected void onResume() {
        super.onResume();
        getData();
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
            }

            @Override
            public void onLoadMore() {
                mPager++;
                getData();
            }
        });
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
                ToastUtil.showShort(MyApp.getAppContext(),getStr(R.string.no_more_data));
            }
        }
    }

    public void showEmpty() {
        showNoData();
    }

    private void initAdapter(I i) {
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
        mXRv.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    protected void updateViews(boolean isRefresh) {

    }

    public XRecyclerView getXRv(){
        return mXRv;
    }
}

package com.hc360.koiambuyer.view.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.utils.HeightTool;
import com.hc360.koiambuyer.utils.SwipeRefreshHelper;
import com.hc360.koiambuyer.view.MyApp;
import com.hc360.koiambuyer.widget.EmptyLayout;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/8/28
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public abstract class BaseFragment<T extends IBasePresenter> extends RxFragment implements IBaseView, EmptyLayout.OnRetryListener {
    /**
     * 注意，资源的ID一定要一样
     */
    @Nullable
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;
    @Nullable
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    /**
     * 底部的控件，为了适配带虚拟键盘的手机，注意，资源的ID一定要一样
     */
    @Nullable
    @BindView(R.id.bottom)
    View mBottom;

    @Nullable
    @BindView(R.id.toolbar_right)
    TextView mTvRight;

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Nullable
    @BindView(R.id.toolbar_title)
    TextView mTvTitle;
    protected T mPresenter;

    protected Context mContext;
    //缓存Fragment view
    private View mRootView;
    private boolean mIsMulti = false;

    protected int mPager = 1;

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    protected abstract int attachLayoutRes();

    /**
     * Dagger 注入
     */
    protected abstract void initInjector();

    /**
     * 初始化视图控件
     */
    protected abstract void initViews();

    /**
     * 更新视图控件
     *
     * @param isRefresh 新增参数，用来判断是否为下拉刷新调用，下拉刷新的时候不应该再显示加载界面和异常界面
     */
    protected abstract void updateViews(boolean isRefresh);


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        if (RetrofitService.isDebug){
            MyApp.sUserId = "365";
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(attachLayoutRes(), null);
            ButterKnife.bind(this, mRootView);
            initInjector();
            initViewConfig();
            initViews();
            initSwipeRefresh();
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    protected void initViewConfig() {
        if (mBottom != null) {
            try {
                int BottomHeight = HeightTool.getBottomKeyboardHeight(getActivity());
                LinearLayout.LayoutParams lp_bottom = (LinearLayout.LayoutParams) mBottom.getLayoutParams();
                lp_bottom.bottomMargin = BottomHeight;
                mBottom.setLayoutParams(lp_bottom);
            } catch (Exception e) {

            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            updateViews(false);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isVisible() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            updateViews(false);
        } else {
            super.setUserVisibleHint(isVisibleToUser);
        }
    }

    public void setEmptyMsg(String msg) {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyMessage(msg);
        }
    }

    @Override
    public void showLoading() {
        if (mEmptyLayout != null && mPager == 1) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
            SwipeRefreshHelper.enableRefresh(mSwipeRefresh, false);
        }
    }

    @Override
    public void showNoData() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_DATA);
        }
    }

    @Override
    public void hideLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.hide();
            SwipeRefreshHelper.enableRefresh(mSwipeRefresh, true);
            SwipeRefreshHelper.controlRefresh(mSwipeRefresh, false);
        }
    }

    @Override
    public void showNetError() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_NET);
            mEmptyLayout.setRetryListener(this);
        }
        if (mSwipeRefresh != null) {
            mSwipeRefresh.setRefreshing(false);
        }
    }

    @Override
    public void onRetry() {
        updateViews(true);
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }

    @Override
    public void finishRefresh() {
        if (mSwipeRefresh != null) {
            mSwipeRefresh.setRefreshing(false);
        }
    }


    /**
     * 初始化 Toolbar
     *
     * @param toolbar
     * @param homeAsUpEnabled
     * @param title
     */
    protected void initToolBar(Toolbar toolbar, @Nullable Integer iconLeft, boolean homeAsUpEnabled, String title) {
        ((BaseActivity) getActivity()).initToolBar(toolbar, iconLeft, homeAsUpEnabled, title);
    }

    /**
     * 初始化下拉刷新
     */
    private void initSwipeRefresh() {
        if (mSwipeRefresh != null) {
            SwipeRefreshHelper.init(mSwipeRefresh, new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    updateViews(true);
                }
            });
        }
    }

    public void setRightByRes(Context context, int resId, TextView tv){
        tv.setCompoundDrawables(null, null, getTvDrawable(context,resId), null);
    }
    public void setLeftByRes(Context context, int resId, TextView tv){
        tv.setCompoundDrawables(getTvDrawable(context,resId), null, null , null);
    }
    public Drawable getTvDrawable(Context context, int resId){
        Drawable nav=context.getResources().getDrawable(resId);
        nav.setBounds(0, 0, nav.getMinimumWidth()/2, nav.getMinimumHeight()/2);
        return nav;
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    public String getStr(int resId){
        return getResources().getString(resId);
    }
    public String getAppStr(int resId){
        return MyApp.getAppContext().getResources().getString(resId);
    }


    public void initToolBar(String title,String content){
        if (mTvTitle != null){
            mTvTitle.setText(title);
        }
        if (mTvRight != null){
            mTvRight.setText(content);
            mTvRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onTvRightClick();
                }
            });
        }
        onNavigationClick();
    }
    public void initToolBar(String title){
        if (mTvTitle != null){
            mTvTitle.setText(title);
        }
        onNavigationClick();
    }
    private void onNavigationClick() {
        if (mToolbar != null){
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().finish();
                }
            });
        }
    }

    public void onTvRightClick(){

    }

    public void setRightText(String text){
        if (mTvRight != null){
            mTvRight.setText(text);
        }
    }

    public void initRv(RecyclerView rv, LinearLayoutManager manager){
        manager.setSmoothScrollbarEnabled(true);
        manager.setAutoMeasureEnabled(true);
        rv.setLayoutManager(manager);
        rv.setHasFixedSize(true);
        rv.setNestedScrollingEnabled(false);
    }
}

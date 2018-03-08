package com.hc360.koiambuyer.view.base;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.utils.DensityUtil;
import com.hc360.koiambuyer.utils.HeightTool;
import com.hc360.koiambuyer.utils.SwipeRefreshHelper;
import com.hc360.koiambuyer.utils.TVDrawableUtil;
import com.hc360.koiambuyer.widget.EmptyLayout;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

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

public abstract class BaseActivity<T extends IBasePresenter> extends RxAppCompatActivity implements IBaseView, EmptyLayout.OnRetryListener{

    private static final float TOOLBAR_RIGHT_SIZE = 30;

    protected T mPresenter;

    /**
     * 把 EmptyLayout 放在基类统一处理，@Nullable 表明 View 可以为 null，详细可看 ButterKnife
     */
    @Nullable
    @BindView(R.id.empty_layout)
    protected EmptyLayout mEmptyLayout;

    /**
     * 刷新控件，注意，资源的ID一定要一样
     */
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
    @BindView(R.id.toolbar_title)
    TextView mTvTitle;
    @Nullable
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    Fragment preFragment;

    protected int mPager = 1;

    /**
     * 初始化视图控件
     */
    protected abstract void initView();

    /**
     * Dagger2 注入
     */
    protected abstract void initInjector();

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    protected abstract int attachLayoutRes();

    /**
     * 更新视图控件
     */
    protected abstract void updateViews(boolean isRefresh);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(attachLayoutRes());
        ButterKnife.bind(this);
        initInjector();
        initViewConfig();
        initView();
        initSwipeRefresh();
        updateViews(false);
    }

    protected  void initViewConfig(){
        if (mBottom != null){
            try {
                int BottomHeight = HeightTool.getBottomKeyboardHeight(this);
                LinearLayout.LayoutParams lp_bottom = (LinearLayout.LayoutParams) mBottom.getLayoutParams();
                lp_bottom.bottomMargin = BottomHeight;
                mBottom.setLayoutParams(lp_bottom);
            }catch (Exception e){

            }
        }
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

    public void setNoDataIcon(int IconRes){
        try {
            TVDrawableUtil.setTopByRes(this,IconRes,mEmptyLayout.getTvNoData());
        }catch (Exception e){}
    }


    @Override
    public void showLoading() {
        if (mEmptyLayout != null&& mPager == 1) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
        }
    }

    public void setEmptyMsg(String msg) {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyMessage(msg);
        }
    }

    @Override
    public void hideLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.hide();
        }
    }

    @Override
    public void showNoData() {
        if (mEmptyLayout != null){
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_DATA);
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
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }

    @Override
    public void finishRefresh() {
        if (mSwipeRefresh != null) {
            mSwipeRefresh.setRefreshing(false);
        }
    }

    @Override
    public void onRetry() {
        updateViews(true);
    }


    /**
     * 初始化 Toolbar,右边不带图标
     *
     * @param toolbar
     * @param homeAsUpEnabled
     * @param title
     */
    protected void initToolBar(Toolbar toolbar, @Nullable  Integer iconLeft, boolean homeAsUpEnabled, String title) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(homeAsUpEnabled);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onToolBarNavigationClick();
            }
        });
        setDefaultToolBarBgColor(toolbar);
        if (iconLeft != null){
            toolbar.setNavigationIcon(iconLeft);
        }
        if (mTvRight != null) {
            mTvRight.setVisibility(View.GONE);
        }
        if (mTvTitle != null) {
            mTvTitle.setText(title);
        }
    }

    private void setDefaultToolBarBgColor(Toolbar toolbar) {
        toolbar.setBackgroundColor(getResources().getColor(R.color.mainColor));
    }

    public void onToolBarNavigationClick(){
        finish();
    }
    /**
     * 初始化 Toolbar,使用自带的
     *
     * @param title
     */
    protected void initToolBar(String title) {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (mTvRight != null) {
            mTvRight.setVisibility(View.GONE);
        }
        if (mTvTitle != null) {
            mTvTitle.setText(title);
        }
    }


    /** 初始化 Toolbar,右边带图标
     *
     * @param toolbar
     * @param homeAsUpEnabled
     * @param title
     * @param right
     * @param icon
     * @param rightClick
     */
    protected void initToolBar(Toolbar toolbar, @Nullable  Integer iconLeft, boolean homeAsUpEnabled, String title, String right, @Nullable  Integer icon, @Nullable  Integer iconSize, View.OnClickListener rightClick) {
        initToolBar(toolbar,iconLeft,homeAsUpEnabled, title);
        setToolbarRight(right,icon,iconSize,rightClick);
    }
    /** 初始化 Toolbar,右边带图标
     *
     */
    protected void initToolBar(String title, @Nullable  Integer icon,@Nullable  Integer iconSize, View.OnClickListener rightClick) {
        initToolBar(mToolbar,null,true, title);
        setToolbarRight(null,icon,iconSize,rightClick);
    }
    /** 初始化 Toolbar,右边带图标,自带模式
     *
     * @param title
     * @param right
     * @param rightClick
     */
    protected void initToolBar( String title,String right, View.OnClickListener rightClick) {
        initToolBar(mToolbar,null,true, title);
        setToolbarRight(right,null,null,rightClick);
    }

    protected void setToolbarRight(String text, @Nullable  Integer icon,@Nullable  Integer iconSize, View.OnClickListener rightClick){
        if (mTvRight != null) {
            if(text!=null) {
                mTvRight.setText(text);
            }
            if (icon != null) {
                mTvRight.setBackgroundResource(icon.intValue());
                Toolbar.LayoutParams linearParams = (Toolbar.LayoutParams) mTvRight.getLayoutParams();
                linearParams.height= DensityUtil.dp2px(this,iconSize != null?iconSize:TOOLBAR_RIGHT_SIZE);
                linearParams.width= DensityUtil.dp2px(this,iconSize != null?iconSize:TOOLBAR_RIGHT_SIZE);
                linearParams.setMargins(0,0,30,0);
                mTvRight.setLayoutParams(linearParams);
            }
            mTvRight.setVisibility(View.VISIBLE);
            mTvRight.setOnClickListener(rightClick);
        }
    }


    /**
     * 替换 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void replaceFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    /**
     * 替换 Fragment
     * @param containerViewId
     * @param fragment
     */
    protected void replaceFragment(int containerViewId, Fragment fragment, String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            // 设置tag
            fragmentTransaction.replace(containerViewId, fragment, tag);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            // 这里要设置tag，上面也要设置tag
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();
        } else {
            // 存在则弹出在它上面的所有fragment，并显示对应fragment
            getSupportFragmentManager().popBackStack(tag, 0);
        }
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

}

package com.hc360.koiambuyer.view.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.utils.ActivitySkipHelper;
import com.hc360.koiambuyer.view.base.BaseActivity;
import com.hc360.koiambuyer.view.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.fl_container)
    FrameLayout mFlContainer;
    @BindView(R.id.tv_home)
    TextView mTvHome;
    @BindView(R.id.iv_publish)
    ImageView mIvPublish;
    @BindView(R.id.tv_me)
    TextView mTvMe;
    private long mExitTime = 0;
    private int mPosition = 1;
    BaseFragment mMeFragment;
    BaseFragment mHomeFragment;
    BaseFragment mPublishFragment;
    @Override
    protected void initView() {
        replaceFragment(R.id.fl_container, getHomeFragment());
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivitySkipHelper.HomeDoSkip(this);
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_home, R.id.iv_publish, R.id.tv_me})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_home:
                if (mPosition != 1) {
                    replaceFragment(R.id.fl_container, getHomeFragment());
                    mPosition = 1;
                }
                break;
            case R.id.iv_publish:
                if (mPosition != 2) {
                    replaceFragment(R.id.fl_container, getPublishFragment());
                    mPosition = 2;
                }
                break;
            case R.id.tv_me:
                if (mPosition != 3) {
                    replaceFragment(R.id.fl_container, getMeFragment());
                    mPosition = 3;
                }
                break;
        }
    }

    private Fragment getMeFragment() {
        if (mMeFragment == null){
            mMeFragment = new MeFragment();
        }
        return mMeFragment;
    }

    private Fragment getPublishFragment() {
        if (mPublishFragment == null){
            mPublishFragment = new PublishFragment();
        }
        return mPublishFragment;
    }

    private BaseFragment getHomeFragment() {
        if (mHomeFragment == null){
            mHomeFragment = new FindFragment();
        }
        return mHomeFragment;
    }

    private void exitApp() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            Toast.makeText(this, getStr(R.string.exit_app), Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitApp();     //调用双击退出函数
        }
        return false;
    }

}

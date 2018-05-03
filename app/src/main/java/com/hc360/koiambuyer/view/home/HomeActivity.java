package com.hc360.koiambuyer.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.myinterface.DialogClickListener;
import com.hc360.koiambuyer.myinterface.ipresenter.IHomePresenter;
import com.hc360.koiambuyer.myinterface.iview.IHomeView;
import com.hc360.koiambuyer.presenter.HomePresenter;
import com.hc360.koiambuyer.utils.ActivitySkipHelper;
import com.hc360.koiambuyer.utils.DialogHelper;
import com.hc360.koiambuyer.utils.SPUtils;
import com.hc360.koiambuyer.view.CityManagerActivity;
import com.hc360.koiambuyer.view.MyApp;
import com.hc360.koiambuyer.view.base.BaseActivity;
import com.hc360.koiambuyer.view.base.BaseFragment;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hc360.koiambuyer.utils.TVDrawableUtil.setTopByRes;

public class HomeActivity extends BaseActivity<IHomePresenter> implements IHomeView {

    @BindView(R.id.fl_container)
    FrameLayout mFlContainer;
    @BindView(R.id.tv_home)
    TextView mTvHome;
    @BindView(R.id.tv_publish)
    TextView mTvPublish;
    @BindView(R.id.tv_me)
    TextView mTvMe;
    @BindView(R.id.tv_chat)
    TextView mTvChat;
    private long mExitTime = 0;
    private int mPosition = 1;
    BaseFragment mMeFragment;
    BaseFragment mHomeFragment;
    BaseFragment mPublishFragment;
    BaseFragment mChatListFragment;

    @Override
    protected void initView() {
        replaceFragment(R.id.fl_container, new FindFragment(),FindFragment.class.getName());
        Logger.e(FindFragment.class.getName());
        selectBottom(true, false, false);
        setTopByRes(this, R.mipmap.home_message_no, mTvPublish);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivitySkipHelper.HomeDoSkip(this);
        if (SPUtils.getBoolean(this,Msg.TO_FIND,false)){
            replaceFragment(R.id.fl_container, new FindFragment(),FindFragment.class.getName());
            mPosition = 1;
            selectBottom(true, false, false);
            SPUtils.saveBoolean(this,Msg.TO_FIND,false);
        }
        if (TextUtils.isEmpty(SPUtils.getString(this,Constant.REFER_PHONE,""))){
            mPresenter.getInitStates(MyApp.sUserId);
        }
    }

    @Override
    protected void initInjector() {
        mPresenter = new HomePresenter(this);
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

    @OnClick({R.id.tv_home, R.id.tv_publish, R.id.tv_me,R.id.tv_chat})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_home:
                if (mPosition != 1) {
                    replaceFragment(R.id.fl_container, new FindFragment(),FindFragment.class.getName());
                    mPosition = 1;
                    selectBottom(true, false, false);
                }
                break;
            case R.id.tv_publish:
                if (TextUtils.isEmpty(SPUtils.getString(this,Constant.REFER_PHONE,""))&&(!SPUtils.getBoolean(this, Constant.SKIP_CITY_MANAGER, false))){
                    DialogHelper.showCustomNoTitleDialog(this, getStr(R.string.have_no_city_manager_yet), getStr(R.string.skip), getStr(R.string.go_to_add), new DialogClickListener() {
                        @Override
                        public void positiveClick() {
                            startActivity(new Intent(HomeActivity.this,CityManagerActivity.class));
                        }

                        @Override
                        public void negativeClick() {
                            getToPublish();
                        }
                    });
                }else {
                    getToPublish();
                }
                break;
            case R.id.tv_chat:
                if (mPosition != 2) {
                    replaceFragment(R.id.fl_container, new ChatListFragment(),ChatListFragment.class.getName());
                    mPosition = 2;
                    selectBottom(false, true, false);
                }
                break;
            case R.id.tv_me:
                if (mPosition != 3) {
                    replaceFragment(R.id.fl_container, new MeFragment(),MeFragment.class.getName());
                    mPosition = 3;
                    selectBottom(false, false, true);
                }
                break;

        }
    }

    private void getToPublish() {
        Intent openPublish = new Intent(this, PublishActivity.class);
        startActivity(openPublish);
        setTopByRes(this, R.mipmap.home_purchase_yes, mTvPublish);
        mTvPublish.setTextColor(getResources().getColor(R.color.buyerColor));
    }

    private Fragment getChatFragment() {
        if (mChatListFragment == null){
            mChatListFragment = new ChatListFragment();
        }
        return mChatListFragment;
    }

    @Override
    protected void onStop() {
        super.onStop();
        setTopByRes(this, R.mipmap.home_purchase_no, mTvPublish);
        mTvPublish.setTextColor(getResources().getColor(R.color.minorColor));
    }

    private void selectBottom(boolean isHome, boolean isChat, boolean isMe) {
        setTopByRes(this, isHome ? R.mipmap.home_goods_yes : R.mipmap.home_goods_no, mTvHome);
        setTopByRes(this, isMe ? R.mipmap.home_me_yes_buyer : R.mipmap.home_me_no, mTvMe);
        setTopByRes(this, isChat ? R.mipmap.home_chat_yes : R.mipmap.home_chat_no, mTvChat);
        mTvHome.setTextColor(getResources().getColor(isHome ? R.color.buyerColor : R.color.minorColor));
        mTvMe.setTextColor(getResources().getColor(isMe ? R.color.buyerColor : R.color.minorColor));
        mTvChat.setTextColor(getResources().getColor(isChat ? R.color.buyerColor : R.color.minorColor));
    }

    private Fragment getMeFragment() {
        if (mMeFragment == null) {
            mMeFragment = new MeFragment();
        }
        return mMeFragment;
    }

    private Fragment getPublishFragment() {
        if (mPublishFragment == null) {
            mPublishFragment = new PublishFragment();
        }
        return mPublishFragment;
    }

    private BaseFragment getHomeFragment() {
        if (mHomeFragment == null) {
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

    @OnClick(R.id.tv_chat)
    public void onClick() {
    }
}

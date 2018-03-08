package com.hc360.koiambuyer.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.duoyi.provider.qrscan.activity.CaptureActivity;
import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.MeInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.myinterface.ipresenter.IMePresenter;
import com.hc360.koiambuyer.myinterface.iview.IMeView;
import com.hc360.koiambuyer.presenter.MePresenter;
import com.hc360.koiambuyer.view.ContainerFooterActivity;
import com.hc360.koiambuyer.view.MyApp;
import com.hc360.koiambuyer.view.base.BaseFragment;
import com.hc360.koiambuyer.view.me.PersonalDataActivity;
import com.hc360.koiambuyer.view.me.SettingActivity;
import com.hc360.koiambuyer.view.me.ShipAddressActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/2/28
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class MeFragment extends BaseFragment<IMePresenter> implements IMeView {

    @BindView(R.id.tv_code)
    TextView mTvCode;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.rl_person)
    RelativeLayout mRlPerson;
    @BindView(R.id.iv_head)
    ImageView mIvHead;
    @BindView(R.id.iv_prove_flag)
    ImageView mIvProveFlag;
    @BindView(R.id.tv_enquiry)
    TextView mTvEnquiry;
    @BindView(R.id.tv_ship_address)
    TextView mTvShipAddress;
    @BindView(R.id.tv_suggestion)
    TextView mTvSuggestion;
    @BindView(R.id.tv_setting)
    TextView mTvSetting;
    @BindView(R.id.tv_messages)
    TextView mTvMessages;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initInjector() {
        mPresenter = new MePresenter(this);
    }

    @Override
    protected void initViews() {
        setRightByRes(mContext,R.mipmap.in,mTvEnquiry);
        setRightByRes(mContext,R.mipmap.in,mTvShipAddress);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(MyApp.sUserId)){
            mPresenter.getMeInfo(new Integer(MyApp.sUserId), Constant.BUYER);
        }
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.tv_code, R.id.rl_person, R.id.tv_ship_address,R.id.iv_head, R.id.tv_suggestion, R.id.tv_setting,R.id.tv_messages})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_code:
                startActivity(new Intent(mContext, CaptureActivity.class));
                break;
            case R.id.rl_person:
                //个人资料
                Intent openPersonalData = new Intent(mContext, PersonalDataActivity.class);
                startActivity(openPersonalData);
                break;
            case R.id.iv_head:
                //头像
                break;
            case R.id.tv_suggestion:
                //意见和反馈
                Intent openSuggestion = new Intent(mContext, ContainerFooterActivity.class);
                openSuggestion.putExtra(Constant.TYPE, Constant.SUGGESTION);
                startActivity(openSuggestion);
                break;
            case R.id.tv_setting:
                //设置
                Intent openSetting = new Intent(mContext, SettingActivity.class);
                startActivity(openSetting);
                break;
            case R.id.tv_messages:
                //消息列表
                break;
            case R.id.tv_ship_address:
                Intent openShipAddress = new Intent(mContext, ShipAddressActivity.class);
//                openShipAddress.putExtra(Msg.FROM_PUBLISH_GOODS, true);
                startActivityForResult(openShipAddress, Constant.OPEN_SHIP_ADDRESS);
                break;
        }
    }

    @Override
    public void getMeInfo(MeInfo info) {
        Glide.with(mContext).load(info.content.userHeadImg).into(mIvHead);
    }
}

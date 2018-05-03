package com.hc360.koiambuyer.view.me;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.adapter.MyPurchaseAdapter;
import com.hc360.koiambuyer.api.bean.MyPurchaseInfo;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.myinterface.DatePopChoosePosition;
import com.hc360.koiambuyer.myinterface.ipresenter.IMyPurchasePresenter;
import com.hc360.koiambuyer.myinterface.iview.IMyPurchaseView;
import com.hc360.koiambuyer.presenter.MyPurchasePresenter;
import com.hc360.koiambuyer.utils.PopUtils;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseXRvFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/16
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class MyPurchaseFragment extends BaseXRvFragment<IMyPurchasePresenter, MyPurchaseAdapter, MyPurchaseInfo> implements IMyPurchaseView {

    @BindView(R.id.tv_date)
    TextView mTvDate;
    @BindView(R.id.fl_date)
    FrameLayout mFlDate;
    @BindView(R.id.tv_state)
    TextView mTvState;
    @BindView(R.id.fl_state)
    FrameLayout mFlState;
    @BindView(R.id.bg)
    View mBg;
    private List<String> mDates;
    private List<String> mStates;
    private int mDataType = -1;
    private int mStateType = -1;

    @Override
    public void getMyPurchases(MyPurchaseInfo info) {
        setAdapter(info);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_my_purchase;
    }

    @Override
    protected void initInjector() {
        mPresenter = new MyPurchasePresenter(this);
    }

    @Override
    protected void initViews() {
        setRightByRes(mContext, R.mipmap.down, mTvDate);
        setRightByRes(mContext, R.mipmap.down, mTvState);
        setItemSpace(10);
        mDates = new ArrayList<>();
        mDates.add(getString(R.string.date_0));
        mDates.add(getString(R.string.date_1));
        mDates.add(getString(R.string.date_2));
        mDates.add(getString(R.string.date_3));
        mStates = new ArrayList<>();
        mStates.add(getString(R.string.state_0));
        mStates.add(getString(R.string.state_1));
        mStates.add(getString(R.string.state_2));
        mStates.add(getString(R.string.state_3));
        Bundle args = getArguments();
        if (args != null) {
            String stateStr = args.getString(Msg.STATE);
            if (!TextUtils.isEmpty(stateStr)){
                mStateType = Integer.parseInt(stateStr);
                if (mStateType > -1){
                    mTvState.setText(mStates.get(mStateType));
                }
            }
        }
    }

    @Override
    public void getData() {
        mPresenter.getMyPurchases(String.valueOf(mDataType), String.valueOf(mStateType), mPager);
    }

    @Override
    public BaseAdapter newAdapter(MyPurchaseInfo myPurchaseInfo) {
        return new MyPurchaseAdapter(R.layout.rv_my_purchase, myPurchaseInfo.list);
    }

    @Override
    public List getList(MyPurchaseInfo myPurchaseInfo) {
        return myPurchaseInfo.list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.fl_date, R.id.fl_state})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_date:
                PopUtils.getPopType(mContext, mTvDate, mDates,mDataType, new DatePopChoosePosition() {
                    @Override
                    public void getType(int type, String msg) {
                        mDataType = type;
                        mPager = 1;
                        mTvDate.setText(msg);
                        getData();
                    }
                    @Override
                    public void onPopDismiss() {
                        mBg.setVisibility(View.GONE);
                    }
                });
                mBg.setVisibility(View.VISIBLE);
                break;
            case R.id.fl_state:
                PopUtils.getPopType(mContext, mTvDate, mStates,mStateType, new DatePopChoosePosition() {
                    @Override
                    public void getType(int type, String msg) {
                        mStateType = type;
                        mPager = 1;
                        mTvState.setText(msg);
                        getData();
                    }
                    @Override
                    public void onPopDismiss() {
                        mBg.setVisibility(View.GONE);
                    }
                });
                mBg.setVisibility(View.VISIBLE);
                break;
        }
    }
}

package com.hc360.koiambuyer.view.me;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.adapter.OrderAdapter;
import com.hc360.koiambuyer.api.bean.OrderInfo;
import com.hc360.koiambuyer.engine.OrderStateTypeEnum;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.myinterface.DialogClickListener;
import com.hc360.koiambuyer.myinterface.DialogPositiveClickListener;
import com.hc360.koiambuyer.myinterface.ipresenter.IOrderPresenter;
import com.hc360.koiambuyer.myinterface.iview.IOrderView;
import com.hc360.koiambuyer.presenter.OrderPresenter;
import com.hc360.koiambuyer.utils.DialogHelper;
import com.hc360.koiambuyer.utils.TagFlowLayoutUtils;
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
 * Date:    2018/3/30
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class OrderFragment extends BaseXRvFragment<IOrderPresenter, OrderAdapter, OrderInfo> implements IOrderView {

    @BindView(R.id.tv_all)
    TextView mTvAll;
    @BindView(R.id.tv_to_ready)
    TextView mTvToReady;
    @BindView(R.id.tv_send_order)
    TextView mTvSendOrder;
    @BindView(R.id.tv_success_order)
    TextView mTvSuccessOrder;
    @BindView(R.id.tv_cancel_order)
    TextView mTvCancelOrder;
    @BindView(R.id.tab)
    TabLayout mTab;
    private String mPosition;
    private int position;
    private List<String> mTabList;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initInjector() {
        mPresenter = new OrderPresenter(this);
    }

    @Override
    protected void initViews() {
        Bundle args = getArguments();
        if (args != null) {
            mPosition = args.getString(Msg.POSITION);
        }
//        initTab(mPosition);

        mTabList = new ArrayList<>();
        mTabList.add(getString(R.string.all));
        mTabList.add(getString(R.string.to_ready));
        mTabList.add(getString(R.string.to_receive));
        mTabList.add(getString(R.string.success_order));
        mTabList.add(getString(R.string.cancel_order));
        if (TextUtils.isEmpty(mPosition)){
            position = 0;
        }else if (mPosition.equals("0")){
            //待发货
            position = 1;
        }else if (mPosition.equals("1")){
            position = 2;
        }else if (mPosition.equals("3")){
            position = 3;
        }else if (mPosition.equals("4")){
            position = 4;
        }
//        case R.id.tv_all:
//        initTab("");
//        break;
//        case R.id.tv_to_ready:
//        initTab("0");
//        break;
//        case R.id.tv_send_order:
//        initTab("1");
//        break;
//        case R.id.tv_success_order:
//        initTab("3");
//        break;
//        case R.id.tv_cancel_order:
//        initTab("4");
//        break;

        for (int i = 0; i < mTabList.size(); i++) {
            mTab.addTab(mTab.newTab().setText(mTabList.get(i)), i == position);
        }

        //修正指示器的宽度
        TagFlowLayoutUtils.reflex(mTab);

        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String mSelectText = tab.getText().toString();
                setStateCode(mSelectText);
                mPager = 1;
                getData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setStateCode(String selectText) {
        if (selectText.equals(mTabList.get(0))){
            mPosition = "";
        }else if (selectText.equals(mTabList.get(1))){
            mPosition = "0";
        }else if (selectText.equals(mTabList.get(2))){
            mPosition = "1";
        }else if (selectText.equals(mTabList.get(3))){
            mPosition = "3";
        }else if (selectText.equals(mTabList.get(4))){
            mPosition = "4";
        }
    }

    private void initTab(String position) {
        mPosition = position;
        switch (mPosition) {
            case "":
                mTvAll.setTextColor(getResources().getColor(R.color.tvNormalColor));
                setMinorColor(mTvCancelOrder, mTvSendOrder, mTvSuccessOrder, mTvToReady);
                break;
            case "0":
                mTvToReady.setTextColor(getResources().getColor(R.color.tvNormalColor));
                setMinorColor(mTvCancelOrder, mTvSendOrder, mTvSuccessOrder, mTvAll);
                break;
            case "1":
                mTvSendOrder.setTextColor(getResources().getColor(R.color.tvNormalColor));
                setMinorColor(mTvCancelOrder, mTvAll, mTvSuccessOrder, mTvToReady);
                break;
            case "3":
                mTvSuccessOrder.setTextColor(getResources().getColor(R.color.tvNormalColor));
                setMinorColor(mTvCancelOrder, mTvSendOrder, mTvAll, mTvToReady);
                break;
            case "4":
                mTvCancelOrder.setTextColor(getResources().getColor(R.color.tvNormalColor));
                setMinorColor(mTvAll, mTvSendOrder, mTvSuccessOrder, mTvToReady);
                break;
        }
    }

    private void setMinorColor(TextView... tvs) {
        for (TextView tv : tvs) {
            tv.setTextColor(getResources().getColor(R.color.minorColor));
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

    @OnClick({R.id.tv_all, R.id.tv_to_ready, R.id.tv_send_order, R.id.tv_success_order, R.id.tv_cancel_order})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_all:
                initTab("");
                break;
            case R.id.tv_to_ready:
                initTab("0");
                break;
            case R.id.tv_send_order:
                initTab("1");
                break;
            case R.id.tv_success_order:
                initTab("3");
                break;
            case R.id.tv_cancel_order:
                initTab("4");
                break;
        }
        mPager = 1;
        getData();
    }

    @Override
    public void getOrders(OrderInfo info) {
        setAdapter(info);
    }

    @Override
    public void cancelSuccess() {
        mPager = 1;
        getData();
    }

    @Override
    public void sureSuccess() {
        mPager = 1;
        getData();
    }

    @Override
    public void getData() {
        mPresenter.getOrders(mPosition, mPager);
    }

    @Override
    public BaseAdapter newAdapter(OrderInfo orderInfo) {
        return new OrderAdapter(R.layout.rv_order, orderInfo.list, this);
    }

    @Override
    public List getList(OrderInfo orderInfo) {
        return orderInfo.list;
    }

    public void cancelOrder(final String orderNo, String orderStat) {
        String content = "";
        if (orderStat.equals(OrderStateTypeEnum.PENDING_DELIVERY.getValue())) {
            //待发货
            content = getStr(R.string.cancel_order_content_msg);
        } else if (orderStat.equals(OrderStateTypeEnum.DELIVER_GOODS.getValue())) {
            //已发货
        } else if (orderStat.equals(OrderStateTypeEnum.SUCCESS.getValue())) {
            //已完成
        } else if (orderStat.equals(OrderStateTypeEnum.cancel.getValue())) {
            //已取消
        }
        DialogHelper.showCustomNormalColorDialog(getActivity(), getStr(R.string.sure_cancle_order), content, null, R.color.buyerColor, null, R.color.tvNormalColor, new DialogClickListener() {
            @Override
            public void positiveClick() {
                mPresenter.cancelOrder(orderNo);
            }

            @Override
            public void negativeClick() {

            }
        });


    }

    public void sureOrder(final String orderNo) {
        DialogHelper.showCustomNoCancelDialog(mContext, getStr(R.string.sure_order_tv), getStr(R.string.sure_order_content_msg), null, new DialogPositiveClickListener() {
            @Override
            public void positiveClick() {
                mPresenter.sureOrder(orderNo);
            }
        });

    }
}

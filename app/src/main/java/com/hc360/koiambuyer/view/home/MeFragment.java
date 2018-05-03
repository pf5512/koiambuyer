package com.hc360.koiambuyer.view.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.duoyi.provider.qrscan.activity.CaptureActivity;
import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.adapter.MeNumberAdapter;
import com.hc360.koiambuyer.adapter.MeOrderAdapter;
import com.hc360.koiambuyer.adapter.MePurchasesAdapter;
import com.hc360.koiambuyer.api.bean.MeInfo;
import com.hc360.koiambuyer.api.bean.MeOrderInfo;
import com.hc360.koiambuyer.api.bean.NumberInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.myinterface.ipresenter.IMePresenter;
import com.hc360.koiambuyer.myinterface.iview.IMeView;
import com.hc360.koiambuyer.presenter.MePresenter;
import com.hc360.koiambuyer.utils.SPUtils;
import com.hc360.koiambuyer.view.ContainerActivity;
import com.hc360.koiambuyer.view.ContainerFooterActivity;
import com.hc360.koiambuyer.view.MyApp;
import com.hc360.koiambuyer.view.base.BaseFragment;
import com.hc360.koiambuyer.view.me.PersonalDataActivity;
import com.hc360.koiambuyer.view.me.SettingActivity;
import com.hc360.koiambuyer.view.me.ShipAddressActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hc360.koiambuyer.R.id.tv_like;

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

    @BindView(R.id.iv_code)
    ImageView mTvCode;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.rl_person)
    RelativeLayout mRlPerson;
    @BindView(R.id.iv_head)
    ImageView mIvHead;

    @BindView(R.id.tv_enquiry)
    TextView mTvEnquiry;
    @BindView(R.id.tv_ship_address)
    TextView mTvShipAddress;
    @BindView(R.id.tv_suggestion)
    TextView mTvSuggestion;

    @BindView(R.id.tv_messages)
    TextView mTvMessages;
    @BindView(tv_like)
    TextView mTvLike;
    @BindView(R.id.tv_attention)
    TextView mTvAttention;
    @BindView(R.id.rv_purchase)
    RecyclerView mRvPurchase;
    @BindView(R.id.rv_order)
    RecyclerView mRvOrder;
    @BindView(R.id.tv_see_all_orders)
    TextView mTvSeeAllOrders;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.tv_see_all_purchases)
    TextView mTvSeeAllPurchases;
    @BindView(R.id.card_head)
    CardView mCardHead;
    private String mHeadStr = "";
    private Bitmap mHeadBit;

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
//        setLeftByRes(mContext, R.mipmap.in, mTvEnquiry);
        setLeftByRes(mContext, R.mipmap.ic_ship_address, mTvShipAddress);
        setLeftByRes(mContext, R.mipmap.ic_message, mTvMessages);
        setLeftByRes(mContext, R.mipmap.ic_suggestion, mTvSuggestion);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(MyApp.sUserId)) {
            mPresenter.getMeInfo();
        }
        mCardHead.setVisibility(View.GONE);
        if (mHeadBit!=null){
            mIvHead.setImageBitmap(mHeadBit);
            mCardHead.setVisibility(View.VISIBLE);
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

    @OnClick({R.id.iv_code, R.id.ll_user, R.id.tv_ship_address, R.id.tv_suggestion, R.id.iv_setting, R.id.tv_messages, R.id.tv_attention, R.id.tv_enquiry, R.id.tv_see_all_orders, R.id.tv_see_all_purchases})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_code:
                startActivity(new Intent(mContext, CaptureActivity.class));
                break;
            case R.id.ll_user:
                //个人资料
                Intent openPersonalData = new Intent(mContext, PersonalDataActivity.class);
                startActivity(openPersonalData);
                break;

            case R.id.tv_suggestion:
                //意见和反馈
                Intent openSuggestion = new Intent(mContext, ContainerFooterActivity.class);
                openSuggestion.putExtra(Constant.TYPE, Constant.SUGGESTION);
                startActivity(openSuggestion);
                break;
            case R.id.iv_setting:
                //设置
                Intent openSetting = new Intent(mContext, SettingActivity.class);
                startActivity(openSetting);
                break;
            case R.id.tv_messages:
                //消息列表
                Intent openMsg = new Intent(mContext, ContainerActivity.class);
                openMsg.putExtra(Constant.TYPE, Constant.MSG);
                startActivity(openMsg);
                break;
            case R.id.tv_ship_address:
                Intent openShipAddress = new Intent(mContext, ShipAddressActivity.class);
                startActivityForResult(openShipAddress, Constant.OPEN_SHIP_ADDRESS);
                break;
            case R.id.tv_attention:
//                Intent openAttention = new Intent(mContext, ContainerFooterActivity.class);
//                openAttention.putExtra(Constant.TYPE, Constant.ATTENTION);
//                startActivity(openAttention);
//                Intent openLike = new Intent(mContext, ContainerActivity.class);
//                openLike.putExtra(Constant.TYPE, Constant.LIKE);
//                startActivity(openLike);
                break;
            case R.id.tv_enquiry:
                //我的询盘
                Intent openChatList = new Intent(mContext, ContainerActivity.class);
                openChatList.putExtra(Msg.BACK, Msg.BACK);
                openChatList.putExtra(Constant.TYPE, Constant.CHAT_LIST);
                startActivity(openChatList);
                break;
            case R.id.tv_see_all_orders:
                Intent openOrder = new Intent(mContext, ContainerFooterActivity.class);
                openOrder.putExtra(Msg.POSITION, "");
                openOrder.putExtra(Constant.TYPE, Constant.ORDER);
                startActivity(openOrder);
                break;
            case R.id.tv_see_all_purchases:
                //查看全部我的采购需求
                Intent openMyPurchases = new Intent(mContext, ContainerFooterActivity.class);
                openMyPurchases.putExtra(Constant.TYPE, Constant.MY_PURCHASE);
                int position = -1;
                openMyPurchases.putExtra(Msg.PURCHASE_STATE, position);
                mContext.startActivity(openMyPurchases);
                break;
        }
    }

    @Override
    public void getMeInfo(MeInfo info) {
        Glide.with(mContext).load(info.content.headImg).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                BitmapDrawable bd = (BitmapDrawable) resource;
                Bitmap bm = bd.getBitmap();
                mIvHead.setImageBitmap(bm);
                mHeadBit = bm;
                mCardHead.setVisibility(View.VISIBLE);
            }
        });
        SPUtils.saveString(MyApp.getAppContext(), Constant.MY_HEAD, info.content.headImg);
        SPUtils.saveString(MyApp.getAppContext(), Constant.USER_IMG, info.content.headImg);
        String userName = info.content.userName;
        if (!TextUtils.isEmpty(userName)) {
            if (userName.length()>9){
                userName = userName.substring(0, 8) + "...";
            }
        }
        mTvName.setText(userName);
//        mTvLike.setText("喜欢  " + info.content.followProCount);
//        mTvAttention.setText("关注  " + info.content.followUserCount);
        List<NumberInfo> numbers = new ArrayList<>();
        numbers.add(new NumberInfo(getStr(R.string.deal_number), info.content.productSuccessCount + info.content.successCount));
        numbers.add(new NumberInfo(getStr(R.string.chat_number), info.content.recordProCount));
        numbers.add(new NumberInfo(getStr(R.string.keep_good_number), info.content.followProCount));
        numbers.add(new NumberInfo(getStr(R.string.my_attention_number), info.content.followUserCount));
        mRv.setLayoutManager(new GridLayoutManager(mContext, 4));
        mRv.setAdapter(new MeNumberAdapter(R.layout.rv_me_purchase, numbers));

        List<MeOrderInfo> purchases = new ArrayList<>();
        purchases.add(new MeOrderInfo(getStr(R.string.state_0), R.mipmap.ic_me_purchase_state_0, info.content.publishedCount));
        purchases.add(new MeOrderInfo(getStr(R.string.state_1), R.mipmap.ic_me_purchase_state_1, info.content.matchmakingCount));
        MeOrderInfo meOrderInfo = new MeOrderInfo(getStr(R.string.state_3), R.mipmap.ic_me_purchase_state_2, info.content.productSuccessCount);
        purchases.add(meOrderInfo);
        Logger.e("加入"+meOrderInfo.toString());
        purchases.add(new MeOrderInfo(getStr(R.string.state_2), R.mipmap.ic_me_purchase_state_3, info.content.shopCount));
        mRvPurchase.setLayoutManager(new GridLayoutManager(mContext, 4));
        mRvPurchase.setAdapter(new MePurchasesAdapter(R.layout.rv_me_order, purchases));

        List<MeOrderInfo> orders = new ArrayList<>();
        orders.add(new MeOrderInfo(getStr(R.string.to_ready), R.mipmap.ic_me_order_state_0, info.content.pendingDeliveryCount));
        orders.add(new MeOrderInfo(getStr(R.string.to_receive), R.mipmap.ic_me_order_state_1, info.content.deliverGoodsCount));
        orders.add(new MeOrderInfo(getStr(R.string.state_3), R.mipmap.ic_me_order_state_2, info.content.successCount));
        orders.add(new MeOrderInfo(getStr(R.string.cancel_order), R.mipmap.ic_me_order_state_3, info.content.cancelCount));
        mRvOrder.setLayoutManager(new GridLayoutManager(mContext, 4));
        mRvOrder.setAdapter(new MeOrderAdapter(R.layout.rv_me_order, orders));
    }

}

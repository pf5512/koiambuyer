package com.hc360.koiambuyer.view.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.OrderDetailInfo;
import com.hc360.koiambuyer.engine.OrderStateTypeEnum;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.model.States;
import com.hc360.koiambuyer.myinterface.DialogClickListener;
import com.hc360.koiambuyer.myinterface.ipresenter.IOrderDetailPresenter;
import com.hc360.koiambuyer.myinterface.iview.IOrderDetailView;
import com.hc360.koiambuyer.presenter.OrderDetailPresenter;
import com.hc360.koiambuyer.utils.DialogHelper;
import com.hc360.koiambuyer.utils.GlideUtils;
import com.hc360.koiambuyer.utils.PopUtils;
import com.hc360.koiambuyer.utils.SPUtils;
import com.hc360.koiambuyer.utils.TimeTool;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.ChatActivity;
import com.hc360.koiambuyer.view.base.BaseActivity;
import com.hc360.koiambuyer.view.good.GoodsDetailActivity;
import com.hyphenate.easeui.utils.TVDrawableUtil;
import com.orhanobut.logger.Logger;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderDetailActivity extends BaseActivity<IOrderDetailPresenter> implements IOrderDetailView {

    @BindView(R.id.tv_state)
    TextView mTvState;
    @BindView(R.id.tv_msg)
    TextView mTvMsg;
    @BindView(R.id.tv_user_info)
    TextView mTvUserInfo;
    @BindView(R.id.tv_address_info)
    TextView mTvAddressInfo;
    @BindView(R.id.ll_address)
    LinearLayout mLlAddress;
    @BindView(R.id.iv_good)
    ImageView mIvGood;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_price_about)
    TextView mTvPriceAbout;
    @BindView(R.id.tv_order_sample)
    TextView mTvOrderSample;
    @BindView(R.id.tv_into)
    TextView mTvInto;
    @BindView(R.id.tv_price_top)
    TextView mTvPriceTop;
    @BindView(R.id.tv_see_detail)
    TextView mTvSeeDetail;
    @BindView(R.id.tv_sample_price)
    TextView mTvSamplePrice;
    @BindView(R.id.fl_sample_price)
    FrameLayout mFlSamplePrice;
    @BindView(R.id.line_sample_price)
    View mLineSamplePrice;
    @BindView(R.id.tv_total_price)
    TextView mTvTotalPrice;
    @BindView(R.id.fl_total_price)
    FrameLayout mFlTotalPrice;
    @BindView(R.id.tv_purchase_info)
    TextView mTvPurchaseInfo;
    @BindView(R.id.tv_iambuyer)
    TextView mTvIambuyer;
    @BindView(R.id.tv_contact_buyer)
    TextView mTvContactBuyer;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.rl_logistics)
    RelativeLayout mRlLogistics;
    @BindView(R.id.card)
    CardView card;
    @BindView(R.id.head)
    LinearLayout mHead;
    @BindView(R.id.tv_number_text)
    TextView mTvNumberText;
    @BindView(R.id.tv_sample_price_text)
    TextView mTvSamplePriceText;
    @BindView(R.id.card_bottom)
    CardView mCardBottom;
    @BindView(R.id.rl_good)
    RelativeLayout mRlGood;
    private String mOrderNo;
    private boolean isDetail = false;
    private boolean isSample = false;
    private boolean isCancel = false;
    private OrderDetailInfo.ContentBean.StProductOrderExpressBean mStProductOrderExpress;
    private String mHeadImg;
    private int mSsoUserId;
    private String mUserName;
    private int mGoodsId;

    @Override
    protected void initView() {
        mOrderNo = getIntent().getStringExtra(Msg.ORDERNO);
        mPresenter.getOrderDetail(mOrderNo);
        Logger.e(mOrderNo);
        initToolBar(getStr(R.string.order_detail_title));
        TVDrawableUtil.setRightByRes(this, R.mipmap.ic_down, mTvSeeDetail);
        TVDrawableUtil.setLeftByRes(this, R.mipmap.ic_contact_buyer, mTvContactBuyer);
        seeDetail(isDetail);
    }

    @Override
    protected void initInjector() {
        mPresenter = new OrderDetailPresenter(this);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_order_detail;
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

    @OnClick({R.id.tv_see_detail, R.id.tv_iambuyer, R.id.tv_contact_buyer, R.id.tv, R.id.rl_logistics,R.id.rl_good})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_see_detail:
                isDetail = !isDetail;
                if (isDetail) {
                    TVDrawableUtil.setRightByRes(this, R.mipmap.ic_up, mTvSeeDetail);
                    mTvSeeDetail.setText("");
                } else {
                    mTvSeeDetail.setText(getStr(R.string.order_detail_see));
                    TVDrawableUtil.setRightByRes(this, R.mipmap.ic_down, mTvSeeDetail);
                }
                seeDetail(isDetail);
                break;
            case R.id.tv_iambuyer:

                break;
            case R.id.tv_contact_buyer:
                //聊天
                SPUtils.saveString(this, States.TO_CHAT_HEAD, mHeadImg);
                Intent openChat = new Intent(this, ChatActivity.class);
                openChat.putExtra(Msg.ID, mSsoUserId);
                openChat.putExtra(Msg.USER_NAME, mUserName);
                startActivity(openChat);
                break;
            case R.id.tv:
                if (isCancel) {
                    DialogHelper.showCustomNoTitleDialog(this, getStr(R.string.cancel_order_content), null, null, new DialogClickListener() {
                        @Override
                        public void positiveClick() {
                            mPresenter.cancelOrder(mOrderNo);
                        }

                        @Override
                        public void negativeClick() {

                        }
                    });

                } else {
                    DialogHelper.showCustomNoTitleDialog(this, getStr(R.string.cancel_sure_content), null, null, new DialogClickListener() {
                        @Override
                        public void positiveClick() {
                            mPresenter.sureOrder(mOrderNo);
                        }

                        @Override
                        public void negativeClick() {

                        }
                    });
                }
                break;
            case R.id.rl_logistics:
                if (mStProductOrderExpress != null) {
                    if (!TextUtils.isEmpty(mStProductOrderExpress.expressNo)) {
                        PopUtils.showLogistics(this, mHead, mStProductOrderExpress);
                    }
                }
                break;
            case R.id.rl_good:
                Intent openGoodDetail = new Intent(this, GoodsDetailActivity.class);
                openGoodDetail.putExtra(Msg.PRODUCT_ID,mGoodsId);
                startActivity(openGoodDetail);
                break;
        }
    }

    private void seeDetail(boolean detail) {

        mFlSamplePrice.setVisibility(detail ? View.VISIBLE : View.GONE);
        mLineSamplePrice.setVisibility(detail ? View.VISIBLE : View.GONE);
        mFlTotalPrice.setVisibility(detail ? View.VISIBLE : View.GONE);
        mTvPriceTop.setVisibility(detail ? View.INVISIBLE : View.VISIBLE);
    }


    @Override
    public void getOrderDetail(OrderDetailInfo info) {
        mGoodsId = info.content.proId;
        mHeadImg = info.content.korCoreUserVo.headImg;
        mSsoUserId = info.content.korCoreUserVo.ssoUserId;
        mUserName = info.content.korCoreUserVo.userName;
        mStProductOrderExpress = info.content.stProductOrderExpress;
        if (info.content.orderState.equals(OrderStateTypeEnum.PENDING_DELIVERY.getValue())) {
            //待发货
            mTvState.setText(OrderStateTypeEnum.PENDING_DELIVERY.getDesc());
            mTvMsg.setText(getStr(R.string.pending_delivery));
            mCardBottom.setVisibility(View.VISIBLE);
            mTv.setText(getStr(R.string.cancel_order_tv));
            isCancel = true;
        } else if (info.content.orderState.equals(OrderStateTypeEnum.DELIVER_GOODS.getValue())) {
            //已发货
            mTvState.setText(OrderStateTypeEnum.DELIVER_GOODS.getDesc());
            mTvMsg.setText(getStr(R.string.deliver_goods));
            mCardBottom.setVisibility(View.VISIBLE);
            mTv.setText(getStr(R.string.sure_order_tv));
            isCancel = false;
        } else if (info.content.orderState.equals(OrderStateTypeEnum.SUCCESS.getValue())) {
            //已完成
            mTvState.setText(OrderStateTypeEnum.SUCCESS.getDesc());
            mTvMsg.setText(getStr(R.string.success_order_msg));
            mCardBottom.setVisibility(View.GONE);
        } else if (info.content.orderState.equals(OrderStateTypeEnum.cancel.getValue())) {
            //已取消
            mTvState.setText(OrderStateTypeEnum.cancel.getDesc());
            mTvMsg.setText(getStr(R.string.cancel_order_msg));
            mTvMsg.setVisibility(View.GONE);
            mCardBottom.setVisibility(View.GONE);
        }
        if (info.content.orderSimprice != null) {
            if (info.content.orderSimprice.compareTo(BigDecimal.ZERO) == 1) {
                isSample = true;
            }
        }
        mTvUserInfo.setText(info.content.goodsReceive.receiveUser + "  " + info.content.goodsReceive.telphone);
        mTvAddressInfo.setText(info.content.goodsReceive.pName + "  " + info.content.goodsReceive.cName + "  " + info.content.goodsReceive.addressDetail);

        GlideUtils.loadGood(this, info.content.spProduct.loopImg001, mIvGood);
        mTvTitle.setText(info.content.spProduct.productName);

        mTvPriceAbout.setText(getStr(R.string.money_unit) + (isSample ? info.content.orderSimunitpri : info.content.orderUnitpri));
        mTvNumberText.setText(isSample ? getStr(R.string.order_detail_sample) : getStr(R.string.order_detail_order));
        mTvOrderSample.setText("X" + (isSample ? info.content.orderSimcount : info.content.orderCount));
        if (!TextUtils.isEmpty(info.content.orderContent)) {
            mTvInto.setText(info.content.orderContent);
        } else {
            mTvInto.setText(getStr(R.string.into_empty));
        }
        mTvPriceTop.setText(getStr(R.string.money_unit) + info.content.stProductOrderAccount.money + "");
        mTvTotalPrice.setText(getStr(R.string.money_unit) + info.content.stProductOrderAccount.money + "");

        mTvSamplePriceText.setText(isSample ? getStr(R.string.order_detail_sample_price) : getStr(R.string.order_detail_good_price));
        mTvSamplePrice.setText(getStr(R.string.money_unit) + (isSample ? info.content.orderSimunitpri : info.content.orderUnitpri) + "*" + (isSample ? info.content.orderSimcount : info.content.orderCount));

        StringBuilder sb = new StringBuilder(getStr(R.string.order_detail_purchase_id) + info.content.orderCode + "\n"
                + getStr(R.string.order_detail_purchase_create) + TimeTool.getTimeDetail(info.content.stProductOrderAccount.createTime));
        long sendDate = info.content.stProductOrderExpress.sendDate;
        if (sendDate > 0) {
            sb.append("\n").append(getStr(R.string.order_detail_purchase_send)).append(TimeTool.getTimeDetail(sendDate));
            if (info.content.successTime > 0) {
                sb.append("\n").append(getStr(R.string.order_detail_purchase_receive)).append(TimeTool.getTimeDetail(info.content.successTime));
            }
        }
        if (info.content.cancelTime > 0) {
            sb.append("\n").append(getStr(R.string.order_detail_purchase_cancel)).append(TimeTool.getTimeDetail(info.content.cancelTime));
        }
        mTvPurchaseInfo.setText(sb.toString());
    }

    @Override
    public void cancelSuccess() {
        ToastUtil.showShort(this, getStr(R.string.cancel_order_success));
        finish();
    }

    @Override
    public void sureSuccess() {
        ToastUtil.showShort(this, getStr(R.string.cancel_sure_success));
        finish();
    }

}

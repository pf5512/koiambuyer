package com.hc360.koiambuyer.view.good;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.AddressInfo;
import com.hc360.koiambuyer.api.bean.GoodsDetailInfo;
import com.hc360.koiambuyer.api.bean.OrderDetailInfo;
import com.hc360.koiambuyer.api.bean.ShipAddressInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.myinterface.ipresenter.IStockPresenter;
import com.hc360.koiambuyer.myinterface.iview.IStockView;
import com.hc360.koiambuyer.presenter.StockPresenter;
import com.hc360.koiambuyer.utils.GlideUtils;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.ContainerFooterActivity;
import com.hc360.koiambuyer.view.base.BaseActivity;
import com.hc360.koiambuyer.view.me.ShipAddressActivity;
import com.hc360.koiambuyer.widget.SingleTextView;
import com.hc360.koiambuyer.widget.StockNumView;
import com.hyphenate.easeui.utils.TVDrawableUtil;
import com.orhanobut.logger.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建进货单
 */
public class StockActivity extends BaseActivity<IStockPresenter> implements IStockView {

    @BindView(R.id.tv_address_user)
    TextView mTvAddressUser;
    @BindView(R.id.tv_address_detail)
    TextView mTvAddressDetail;
    @BindView(R.id.tv_add_address)
    TextView mTvAddAddress;
    @BindView(R.id.ll_address)
    LinearLayout mLlAddress;
    @BindView(R.id.iv_good)
    ImageView mIvGood;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.tv_num)
    TextView mTvNum;
    @BindView(R.id.snv)
    StockNumView mSnv;
    @BindView(R.id.et_remark)
    EditText mEtRemark;
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
    @BindView(R.id.tv_iambuyer)
    TextView mTvIambuyer;
    @BindView(R.id.stv)
    SingleTextView mStv;
    @BindView(R.id.ll_address_info)
    LinearLayout mLlAddressInfo;
    @BindView(R.id.card)
    CardView card;
    @BindView(R.id.line_top)
    View mLineTop;
    @BindView(R.id.rl_good)
    RelativeLayout mRlGood;
    private boolean isDetail = false;
    private int mNum;
    private GoodsDetailInfo mInfo;
    private boolean isSample;
    private int mMaxNum;
    private int mMinNumber;
    private String mPreTotalPrice;
    private BigDecimal mPrice;
    private int mGoodId = -1;
    private List<GoodsDetailInfo.ContentBean.SpProductPiceBean> mSpProductPice;
    //收货地址
    private int mDeliverId;
    private boolean isOpenShipAddress = false;

    @Override
    protected void initView() {
        initToolBar(getStr(R.string.stock_title));
        TVDrawableUtil.setRightByRes(this, R.mipmap.ic_down, mTvSeeDetail);
        isSample = getIntent().getBooleanExtra(Constant.STOCK_SAMPLE, false);
        mNum = getIntent().getIntExtra(Msg.GOOD_NUM, 0);
        //产品id
        mGoodId = getIntent().getIntExtra(Msg.GOOD_ID, -1);
        //发货地id
        mDeliverId = getIntent().getIntExtra(Msg.DELIVER_ID, -1);
        mSpProductPice = new ArrayList<>();
        if (mGoodId == -1) {
            mPresenter.getAddresses();
            mInfo = (GoodsDetailInfo) getIntent().getSerializableExtra(Msg.GOOD_INFO);
            mGoodId = mInfo.content.spProduct.productId;

            mTvTitle.setText(mInfo.content.spProduct.productName);
            GlideUtils.loadGood(this, mInfo.content.spProduct.loopImg001, mIvGood);

            mTvPrice.setText(isSample ? (getStr(R.string.money_unit) + mInfo.content.spProduct.productMoney) : (getStr(R.string.money_unit) + mInfo.content.minPice + "~" + getStr(R.string.money_unit) + mInfo.content.maxPice));
            mMaxNum = isSample ? mInfo.content.spProduct.productMaxcount : mInfo.content.spProduct.productTotal;
            mMinNumber = isSample ? 1 : mInfo.content.SpProductPice.get(0).minNumber;
        } else {
            //再次购买
            mDeliverId = getIntent().getIntExtra(Msg.GOOD_GOOD_ID, -1);
            String orderNo = getIntent().getStringExtra(Msg.ORDERNO);
            mPresenter.getOrderDetail(orderNo);
            mTvTitle.setText(getIntent().getStringExtra(Msg.GOOD_NAME));
            GlideUtils.loadGood(this, getIntent().getStringExtra(Msg.GOOD_PIC), mIvGood);
            String maxPrice = getIntent().getStringExtra(Msg.GOOD_MAX_PRICE);
            String minPrice = getIntent().getStringExtra(Msg.GOOD_MIN_PRICE);
            mTvPrice.setText(isSample ? (getStr(R.string.money_unit) + maxPrice) : (getStr(R.string.money_unit) + minPrice + "~" + getStr(R.string.money_unit) + maxPrice));

            mMinNumber = getIntent().getIntExtra(Msg.GOOD_MIN_NUM, -1);
            mMaxNum = getIntent().getIntExtra(Msg.GOOD_MAX_NUM, -1);
            String reMark = getIntent().getStringExtra(Msg.GOOD_DESC);
            mEtRemark.setText(reMark);
            mEtRemark.setSelection(reMark.length());
        }
        mPreTotalPrice = getIntent().getStringExtra(Msg.GOOD_MSG);
        Logger.e(Msg.GOOD_MSG);
        mPrice = new BigDecimal(getIntent().getStringExtra(Msg.GOOD_PRICE));
        mTvSamplePrice.setText(getStr(R.string.money_unit) + mPrice + "*" + mNum);
        mSnv.initSnv(isSample, mMaxNum, mMinNumber);
        mTvNum.setText("X" + mNum);
        mSnv.setSampleText(mNum + "");

        mTvTotalPrice.setText(mPreTotalPrice);
        mTvPriceTop.setText(mPreTotalPrice);
        seeDetail(isDetail);
        mSnv.setOnStockNumListener(new StockNumView.OnStockNumListener() {
            @Override
            public void onStockNumChange(String sample) {
                if (!TextUtils.isEmpty(sample)) {
                    mNum = Integer.parseInt(sample);
                    mTvNum.setText("X" + mNum);
                    if (mInfo != null) {
                        mSpProductPice = mInfo.content.SpProductPice;
                    }
                    try {
                        if (!isSample) {
                            BigDecimal price = new BigDecimal("0");
                            if (mNum >= mSpProductPice.get(mSpProductPice.size() - 1).maxNumber) {
                                price = mSpProductPice.get(mSpProductPice.size() - 1).price;
                            }
                            if (mNum <= mSpProductPice.get(0).minNumber) {
                                price = mSpProductPice.get(0).price;
                            } else {
                                for (GoodsDetailInfo.ContentBean.SpProductPiceBean spProductPiceBean : mSpProductPice) {
                                    if (mNum > spProductPiceBean.minNumber && mNum <= spProductPiceBean.maxNumber) {
                                        price = spProductPiceBean.price;
                                        break;
                                    }
                                }
                            }
                            mPrice = price;
                        }
                        mTvSamplePrice.setText(getStr(R.string.money_unit) + mPrice + "*" + mNum);
                        mTvTotalPrice.setText(getPriceText());
                        mTvPriceTop.setText(getPriceText());
                    } catch (Exception e) {

                    }
                } else {
                    mTvNum.setText("X0");
                    mTvSamplePrice.setText(getStr(R.string.money_unit) + mPrice + "*" + 0);
                    mTvTotalPrice.setText(getStr(R.string.money_unit) + (0.00));
                    mTvPriceTop.setText(getStr(R.string.money_unit) + (0.00));
                }
            }
        });
    }

    private String getPriceText() {
        return getStr(R.string.money_unit) + getPriceBD();
    }

    private BigDecimal getPriceBD() {
        BigDecimal n1 = new BigDecimal(mNum);
        BigDecimal pr = mPrice.multiply(n1);
        pr = pr.setScale(2);
        return pr;
    }

    @Override
    protected void initInjector() {
        mPresenter = new StockPresenter(this);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_stock;
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isOpenShipAddress) {
            if (mDeliverId > 0) {
                mPresenter.getAddresses(mDeliverId);
                isOpenShipAddress = false;
            }
        }
    }

    @OnClick({R.id.ll_address, R.id.tv_iambuyer, R.id.stv, R.id.ll_see_detail,R.id.rl_good})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_address:
                if (mDeliverId > 0) {
                    isOpenShipAddress = true;
                    Intent openShip = new Intent(StockActivity.this, ShipAddressActivity.class);
                    openShip.putExtra(Msg.SELECT, true);
                    startActivityForResult(openShip, Constant.OPEN_SHIP);
                } else {
                    //没有地址，跳转到添加地址界面
                    Intent openEditAddress = new Intent(StockActivity.this, ContainerFooterActivity.class);
                    //新建地址
                    openEditAddress.putExtra(Constant.TYPE, Constant.NEW_SHIP_ADDRESS);
                    startActivityForResult(openEditAddress, Constant.OPEN_EDIT_ADDRESS);
                }
                break;
            case R.id.tv_iambuyer:

                break;
            case R.id.stv:
                if (mDeliverId > 0) {
                    if (mNum < mMinNumber) {
                        ToastUtil.showShort(this, isSample ? getStr(R.string.out_of_sample_min) : getStr(R.string.out_of_good_min));
                    } else if (mNum > mMaxNum) {
                        ToastUtil.showShort(this, isSample ? getStr(R.string.out_of_sample_max) : getStr(R.string.out_of_good_max));
                    } else {
                        if (isSample) {
                            mPresenter.submit(mGoodId, mDeliverId, 0, new BigDecimal("0"), new BigDecimal("0"), mNum, mPrice, getPriceBD(), mEtRemark.getText().toString().trim(), getPriceBD());
                        } else {
                            mPresenter.submit(mGoodId, mDeliverId, mNum, mPrice, getPriceBD(), 0, new BigDecimal("0"), new BigDecimal("0"), mEtRemark.getText().toString().trim(), getPriceBD());
                        }
                    }
                } else {
                    ToastUtil.showShort(this, getStr(R.string.have_no_address));
                }

                break;
            case R.id.ll_see_detail:
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
            case R.id.rl_good:
                Intent openGoodDetail = new Intent(this,GoodsDetailActivity.class);
                openGoodDetail.putExtra(Msg.PRODUCT_ID,mGoodId);
                startActivity(openGoodDetail);
                break;
        }
    }

    private void seeDetail(boolean detail) {
        mFlSamplePrice.setVisibility(detail ? View.VISIBLE : View.GONE);
        mLineSamplePrice.setVisibility(detail ? View.VISIBLE : View.GONE);
        mFlTotalPrice.setVisibility(detail ? View.VISIBLE : View.GONE);
        mTvPriceTop.setVisibility(detail ? View.INVISIBLE : View.VISIBLE);
        mLineTop.setVisibility(detail ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void getAddress(ShipAddressInfo info) {
        if (info.list.size() > 0) {
            mTvAddAddress.setVisibility(View.GONE);
            mLlAddressInfo.setVisibility(View.VISIBLE);
            ShipAddressInfo.ListBean addressInfo = info.list.get(0);
            mDeliverId = addressInfo.receiveId;
            mTvAddressUser.setText(addressInfo.receiveUser + "   " + addressInfo.telphone);
            mTvAddressDetail.setText(addressInfo.pName + "  " + addressInfo.cName + "  " + addressInfo.addressDetail);
        }
    }

    /**
     * 修改了地址
     *
     * @param info
     */
    @Override
    public void getAddress(AddressInfo info) {
        if (info.content.states.equals("0")) {
            mTvAddAddress.setVisibility(View.GONE);
            mLlAddressInfo.setVisibility(View.VISIBLE);
            mDeliverId = info.content.receiveId;
            mTvAddressUser.setText(info.content.receiveUser + "   " + info.content.telphone);
            mTvAddressDetail.setText(info.content.pName + "  " + info.content.cName + "  " + info.content.addressDetail);
        } else {
            //地址被删了
            getErrorAddress();
        }
    }

    @Override
    public void submitSuccess() {
        ToastUtil.showShort(this, getStr(R.string.stock_success));
        Intent openOrder = new Intent(this, ContainerFooterActivity.class);
        openOrder.putExtra(Msg.POSITION, "0");
        openOrder.putExtra(Constant.TYPE, Constant.ORDER);
        startActivity(openOrder);
        finish();
    }

    /**
     * 地址被删了
     */
    @Override
    public void getErrorAddress() {
        mTvAddAddress.setVisibility(View.VISIBLE);
        mLlAddressInfo.setVisibility(View.GONE);
        mDeliverId = -1;
    }

    @Override
    public void getOrderDetail(OrderDetailInfo info) {

        if (info.content.goodsReceive.states.equals("1")) {
            //地址被删掉了
            getErrorAddress();
        } else {
            mTvAddAddress.setVisibility(View.GONE);
            mLlAddressInfo.setVisibility(View.VISIBLE);
            mTvAddressUser.setText(info.content.goodsReceive.receiveUser + "   " + info.content.goodsReceive.telphone);
            mTvAddressDetail.setText(info.content.goodsReceive.pName + "  " + info.content.goodsReceive.cName + "  " + info.content.goodsReceive.addressDetail);
        }
        for (OrderDetailInfo.ContentBean.PriceListBean bean : info.content.priceList) {
            mSpProductPice.add(new GoodsDetailInfo.ContentBean.SpProductPiceBean(bean.states, bean.productId, bean.price, bean.unit, bean.priceId, bean.minNumber, bean.maxNumber));
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Constant.OPEN_SHIP) {
                //选择地址
                mTvAddressUser.setText(data.getStringExtra(Msg.USER_TELE));
                mTvAddressDetail.setText(data.getStringExtra(Msg.ADDSTR));
                mDeliverId = data.getIntExtra(Msg.DELIVERID, -1);
                if (mDeliverId != -1) {
                    mTvAddAddress.setVisibility(View.GONE);
                    mLlAddressInfo.setVisibility(View.VISIBLE);
                }
            } else if (requestCode == Constant.OPEN_EDIT_ADDRESS) {
                mTvAddressUser.setText(data.getStringExtra(Msg.USER_TELE));
                mTvAddressDetail.setText(data.getStringExtra(Msg.ADDSTR));
                mDeliverId = data.getIntExtra(Msg.DELIVERID, -1);
                if (mDeliverId != -1) {
                    mTvAddAddress.setVisibility(View.GONE);
                    mLlAddressInfo.setVisibility(View.VISIBLE);
                }
            }
        }
    }

}

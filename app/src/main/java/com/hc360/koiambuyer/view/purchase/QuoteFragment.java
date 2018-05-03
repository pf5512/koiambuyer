package com.hc360.koiambuyer.view.purchase;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.adapter.PurchaseDetailBasicAdapter;
import com.hc360.koiambuyer.adapter.PurchaseDetailParamAdapter;
import com.hc360.koiambuyer.adapter.QuotePicAdapter;
import com.hc360.koiambuyer.api.bean.PurchaseBasicInfo;
import com.hc360.koiambuyer.api.bean.PurchaseDetailInfo;
import com.hc360.koiambuyer.api.bean.QuoteInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.model.States;
import com.hc360.koiambuyer.myinterface.DialogClickListener;
import com.hc360.koiambuyer.myinterface.ipresenter.IQuotePresenter;
import com.hc360.koiambuyer.myinterface.iview.IQuoteView;
import com.hc360.koiambuyer.presenter.QuotePresenter;
import com.hc360.koiambuyer.utils.DialogHelper;
import com.hc360.koiambuyer.utils.SPUtils;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.base.BaseFragment;
import com.hc360.koiambuyer.widget.SingleTextView;
import com.hc360.koiambuyer.widget.SmartSecondScrollNestedView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/23
 * Modify:  //TODO
 * Description: //报价详情
 * Copyright notice:
 */

public class QuoteFragment extends BaseFragment<IQuotePresenter> implements IQuoteView, EasyPermissions.PermissionCallbacks {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_basic_title)
    TextView mTvBasicTitle;
    @BindView(R.id.tv_other_title)
    TextView mTvOtherTitle;
    @BindView(R.id.tv_into_title)
    TextView mTvIntoTitle;
    @BindView(R.id.ll_top)
    LinearLayout mLlTop;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_pur_name)
    TextView mTvPurName;
    @BindView(R.id.rv_basic)
    RecyclerView mRvBasic;
    @BindView(R.id.tv_param)
    TextView mTvParam;
    @BindView(R.id.rv_param)
    RecyclerView mRvParam;
    @BindView(R.id.tv_into)
    TextView mTvInto;
    @BindView(R.id.rv_pic)
    RecyclerView mRvPic;
    @BindView(R.id.tv_purchase_time)
    TextView mTvPurchaseTime;
    @BindView(R.id.scroll_view)
    SmartSecondScrollNestedView mScrollView;
    @BindView(R.id.tv_basic_text)
    TextView mTvBasicText;
    @BindView(R.id.tv_into_text)
    TextView mTvIntoText;
    @BindView(R.id.stv)
    SingleTextView mStv;
    @BindView(R.id.iv_attention)
    ImageView mIvAttention;
    @BindView(R.id.iv_call)
    ImageView mIvCall;
    @BindView(R.id.iv_cuo)
    ImageView mIvCuo;
    private String mQuoteId;
    private boolean isIntention;
    String[] perms = {Manifest.permission.CALL_PHONE};
    private String mPhone;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_quote;
    }

    @Override
    protected void initInjector() {
        mPresenter = new QuotePresenter(this);
    }

    @Override
    protected void initViews() {
        Bundle args = getArguments();
        if (args != null) {
            mQuoteId = args.getString(Msg.QUOTE_ID);
            if (args.getString(Msg.IS_CUO).equals("true")) {
                mIvCuo.setVisibility(View.VISIBLE);
            }
        }
        mPresenter.getQuote(mQuoteId);
        mPhone = SPUtils.getString(mContext, Constant.REFER_PHONE, "");
        mIvCall.setVisibility(TextUtils.isEmpty(mPhone) ? View.GONE : View.VISIBLE);
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

    @OnClick({R.id.iv_back, R.id.tv_basic_title, R.id.tv_other_title, R.id.tv_into_title, R.id.stv, R.id.iv_call})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                getActivity().finish();
                break;
            case R.id.tv_basic_title:
                mScrollView.smoothScrollTo(0,mTvBasicText.getTop()+5);
                break;
            case R.id.tv_other_title:
                mScrollView.smoothScrollTo(0,mTvParam.getTop()+5);
                break;
            case R.id.tv_into_title:
                mScrollView.smoothScrollTo(0,mTvIntoText.getTop()+5);
                break;
            case R.id.stv:
                if (isIntention) {
                    ToastUtil.showShort(mContext, getStr(R.string.quote_iniented));
                } else {
                    mPresenter.setIntent(mQuoteId);
                }
                break;
            case R.id.iv_call:
                DialogHelper.showCustomNormalDialog(mContext, getStr(R.string.call_phone), getStr(R.string.call_phone_content) + com.hyphenate.easeui.utils.SPUtils.getString(mContext, Constant.REFER_PHONE, ""), null, null, new DialogClickListener() {
                    @Override
                    public void positiveClick() {
                        if (EasyPermissions.hasPermissions(getActivity(), perms)) {
                            makeCall();
                        } else {
                            EasyPermissions.requestPermissions(QuoteFragment.this, getStr(R.string.call_perm_msg),
                                    Constant.CALL_PHONE, perms);
                        }
                    }

                    @Override
                    public void negativeClick() {

                    }
                });

                break;
        }
    }

    private void makeCall() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + mPhone);
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    public void getGoods(QuoteInfo info) {
        mTvPurName.setText(info.offerName);
        mIvAttention.setVisibility("1".equals(info.isIntention) ? View.VISIBLE : View.GONE);
        List<PurchaseBasicInfo> basicInfoLis = new ArrayList<>();
        if (!TextUtils.isEmpty(info.offerName)) {
            basicInfoLis.add(new PurchaseBasicInfo(getStr(R.string.quote_name), info.offerName));
        }
        if (!TextUtils.isEmpty(info.offerType)) {
            basicInfoLis.add(new PurchaseBasicInfo(getStr(R.string.quote_class), info.offerType));
        }
        if (!TextUtils.isEmpty(info.offerPice)) {
            basicInfoLis.add(new PurchaseBasicInfo(getStr(R.string.quote_sample_price), info.offerPice));
        }
        if (!TextUtils.isEmpty(info.offerCount)) {
            basicInfoLis.add(new PurchaseBasicInfo(getStr(R.string.quote_sample_num), info.offerCount));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < info.korStProductOfferB2bs.size(); i++) {
            sb.append(info.korStProductOfferB2bs.get(i).b22NameKorea + "   " + info.korStProductOfferB2bs.get(i).b22ValueKorea);
            if (i < info.korStProductOfferB2bs.size() - 1) {
                sb.append("\n");
            }
        }
        if (!TextUtils.isEmpty(sb.toString())) {
            basicInfoLis.add(new PurchaseBasicInfo(getStr(R.string.quote_b2b), sb.toString()));
        }
        initRv(mRvBasic,new LinearLayoutManager(mContext));
        mRvBasic.setAdapter(new PurchaseDetailBasicAdapter(R.layout.rv_purchase_detail_basic, basicInfoLis));

        List<PurchaseDetailInfo.StProductParasBean> otherInfoLis = new ArrayList<>();
        if (!TextUtils.isEmpty(info.offerProsize)) {
            String size = "";
            if (!TextUtils.isEmpty(info.offerProsizeUnit)) {
                size = info.offerProsizeUnit;
            }
            otherInfoLis.add(new PurchaseDetailInfo.StProductParasBean(getStr(R.string.quote_size), info.offerProsize + size));
        }
        if (!TextUtils.isEmpty(info.offerPacksize)) {
            String pack = "";
            if (!TextUtils.isEmpty(info.offerPacksizeUnit)) {
                pack = info.offerPacksizeUnit;
            }
            otherInfoLis.add(new PurchaseDetailInfo.StProductParasBean(getStr(R.string.quote_pack_size), info.offerPacksize + pack));
        }
        if (!TextUtils.isEmpty(info.offerProweight)) {
            String unit = "";
            if (!TextUtils.isEmpty(info.offerProweightUnit)) {
                unit = info.offerProweightUnit;
            }
            otherInfoLis.add(new PurchaseDetailInfo.StProductParasBean(getStr(R.string.quote_weight), info.offerProweight + unit));
        }
        if (!TextUtils.isEmpty(info.offerProcolor)) {
            otherInfoLis.add(new PurchaseDetailInfo.StProductParasBean(getStr(R.string.quote_color), info.offerProcolor));
        }
        otherInfoLis.add(new PurchaseDetailInfo.StProductParasBean(getStr(R.string.quote_stock_num), info.offerStock + ""));
        otherInfoLis.add(new PurchaseDetailInfo.StProductParasBean(getStr(R.string.quote_make), info.offerCust.equals(States.MAKE_ABLE) ? getStr(R.string.yes) : getStr(R.string.no)));
        if (!TextUtils.isEmpty(info.sendTime)) {
            otherInfoLis.add(new PurchaseDetailInfo.StProductParasBean(getStr(R.string.quote_send), info.sendTime));
        }
        initRv(mRvParam,new LinearLayoutManager(mContext));
        mRvParam.setAdapter(new PurchaseDetailParamAdapter(R.layout.rv_purchase_detail_param, otherInfoLis));

        if (TextUtils.isEmpty(info.offerContent)) {
            mTvInto.setText(getStr(R.string.purchase_detail_no_into));
        } else {
            mTvInto.setText(info.offerContent);
        }

        List<String> pics = initPics(info.offerImg, info.offerImg2, info.offerImg3);
        if (pics.size() > 0) {
            initRv(mRvPic,new LinearLayoutManager(mContext));
            mRvPic.setAdapter(new QuotePicAdapter(R.layout.rv_quote_pic, pics, this));
        } else {
            mRvPic.setVisibility(View.GONE);
        }

        mTvPurchaseTime.setText(getStr(R.string.purchase_detail_purchase_time) + info.createTime);

        if (!TextUtils.isEmpty(info.isIntention)) {
            //1是感兴趣 0是
            isIntention = info.isIntention.equals("1");
            if (isIntention) {
                mStv.setBgNormalColorRes(R.color.LineColor);
                mStv.setBgPressColorRes(R.color.LineColor);
                mStv.setTextColor(getResources().getColor(R.color.HintColor));
            }
        }

        mScrollView.setOnScrollViewAtEndListener(new SmartSecondScrollNestedView.OnScrollViewAtEndListener() {
            @Override
            public void moreAction() {

            }

            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
                if (t < mTvBasicText.getTop()) {
                    setTopType(true, false, false, false);
                } else if (t < mTvParam.getTop()) {
                    setTopType(false, true, false, false);
                } else if (t < mTvIntoText.getTop()) {
                    setTopType(false, false, true, false);
                } else if (t >= mTvIntoText.getTop()) {
                    setTopType(false, false, false, true);
                } else {
                    setTopType(true, false, false, false);
                }
            }
        });
    }

    @Override
    public void setIntent() {
        ToastUtil.showShort(mContext, getStr(R.string.quote_intent_success));
        isIntention = true;
        mIvAttention.setVisibility(View.VISIBLE);
        mStv.setBgNormalColorRes(R.color.LineColor);
        mStv.setBgPressColorRes(R.color.LineColor);
    }

    private void setTopType(boolean title, boolean basic, boolean other, boolean into) {
        if (title) {
            mTvTitle.setVisibility(View.VISIBLE);
            mLlTop.setVisibility(View.GONE);
        } else {
            mTvTitle.setVisibility(View.GONE);
            mLlTop.setVisibility(View.VISIBLE);
            mTvBasicTitle.setTextColor(getResources().getColor(basic ? R.color.StvColor : R.color.StvSColor));
            mTvOtherTitle.setTextColor(getResources().getColor(other ? R.color.StvColor : R.color.StvSColor));
            mTvIntoTitle.setTextColor(getResources().getColor(into ? R.color.StvColor : R.color.StvSColor));
        }
    }


    public List<String> initPics(String... pic) {
        List<String> pics = new ArrayList<>();
        for (String s : pic) {
            if (!TextUtils.isEmpty(s)) {
                pics.add(s);
            }
        }
        return pics;
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (requestCode == Constant.CALL_PHONE) {
            makeCall();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == Constant.CALL_PHONE) {
            ToastUtil.showLong(getActivity(), getStr(R.string.call_perm));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}

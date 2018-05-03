package com.hc360.koiambuyer.view.purchase;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.adapter.PurchaseDetailBasicAdapter;
import com.hc360.koiambuyer.adapter.PurchaseDetailParamAdapter;
import com.hc360.koiambuyer.adapter.PurchaseDetailPicAdapter;
import com.hc360.koiambuyer.adapter.PurchaseDetailQuoteAdapter;
import com.hc360.koiambuyer.api.bean.PurchaseBasicInfo;
import com.hc360.koiambuyer.api.bean.PurchaseDetailInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.model.States;
import com.hc360.koiambuyer.myinterface.DialogClickListener;
import com.hc360.koiambuyer.myinterface.ipresenter.IPurchaseDetailPresenter;
import com.hc360.koiambuyer.myinterface.iview.IPurchaseDetailView;
import com.hc360.koiambuyer.presenter.PurchaseDetailPresenter;
import com.hc360.koiambuyer.utils.DialogHelper;
import com.hc360.koiambuyer.utils.SPUtils;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.ContainerFooterActivity;
import com.hc360.koiambuyer.view.base.BaseActivity;
import com.hc360.koiambuyer.view.home.PublishActivity;
import com.hc360.koiambuyer.widget.SingleTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

public class PurchaseDetailActivity extends BaseActivity<IPurchaseDetailPresenter> implements IPurchaseDetailView, EasyPermissions.PermissionCallbacks {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_state)
    TextView mTvState;
    @BindView(R.id.rv_basic)
    RecyclerView mRvBasic;
    @BindView(R.id.rv_param)
    RecyclerView mRvParam;
    @BindView(R.id.tv_into)
    TextView mTvInto;
    @BindView(R.id.rv_pic)
    RecyclerView mRvPic;
    @BindView(R.id.tv_purchase_time)
    TextView mTvPurchaseTime;
    @BindView(R.id.tv_end_time)
    TextView mTvEndTime;
    @BindView(R.id.tv_quote)
    TextView mTvQuote;
    @BindView(R.id.rv_quote)
    RecyclerView mRvQuote;
    @BindView(R.id.stv_edit)
    SingleTextView mStvEdit;
    @BindView(R.id.stv_delete)
    SingleTextView mStvDelete;
    @BindView(R.id.tv_param)
    TextView mTvParam;
    @BindView(R.id.line)
    View mLine;
    @BindView(R.id.card)
    CardView mCard;
    @BindView(R.id.iv_call)
    ImageView mIvCall;
    @BindView(R.id.ll_btn)
    LinearLayout mLlBtn;
    private int mPurId;
    String[] perms = {Manifest.permission.CALL_PHONE};
    private String mPhone;
    private boolean isCuo;

    @Override
    protected void initView() {
        mPurId = getIntent().getIntExtra(Msg.PUR_ID, -1);
        initToolBar(getStr(R.string.purchase_detail_title));
        mPhone = SPUtils.getString(this, Constant.REFER_PHONE, "");
        mIvCall.setVisibility(TextUtils.isEmpty(mPhone) ? View.GONE : View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getPurchaseDetail(mPurId);
    }

    @Override
    protected void initInjector() {
        mPresenter = new PurchaseDetailPresenter(this);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_purchase_detail;
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void getPurchaseDetail(PurchaseDetailInfo info) {
        mTvTitle.setText(info.prodName);
        mTvState.setText(info.productStateStr);
        if (info.productState.equals(States.STATE_PURCHASED)) {
            mTvState.setBackgroundResource(R.color.buyerColor);
            mCard.setCardBackgroundColor(this.getResources().getColor(R.color.buyerColor));
            //编辑只在已经发布那有，删除只在已经发布，已经完成那有
            mLlBtn.setVisibility(View.VISIBLE);
            mStvDelete.setVisibility(View.VISIBLE);
            mStvEdit.setVisibility(View.VISIBLE);
        } else if (info.productState.equals(States.STATE_MATCH)) {
            mLlBtn.setVisibility(View.GONE);
            mTvState.setBackgroundResource(R.color.sellerColor);
            mCard.setCardBackgroundColor(this.getResources().getColor(R.color.sellerColor));
        } else if (info.productState.equals(States.STATE_SUCCESS)) {
            mLlBtn.setVisibility(View.GONE);
            isCuo = true;
            mTvState.setBackgroundResource(R.color.moneyColor);
            mCard.setCardBackgroundColor(this.getResources().getColor(R.color.moneyColor));
        } else {
            mLlBtn.setVisibility(View.GONE);
            mTvState.setBackgroundResource(R.color.unableClick);
            mCard.setCardBackgroundColor(this.getResources().getColor(R.color.unableClick));
        }
        List<PurchaseBasicInfo> basicInfoLis = new ArrayList<>();
        basicInfoLis.add(new PurchaseBasicInfo(getStr(R.string.publish_pro_class), info.proType));
        basicInfoLis.add(new PurchaseBasicInfo(getStr(R.string.purchase_detail_price), getStr(R.string.money_unit) + info.prodPrice + "~" + info.productPriceMax));
        basicInfoLis.add(new PurchaseBasicInfo(getStr(R.string.publish_sample_pro_num), info.productSampleCount + ""));
        basicInfoLis.add(new PurchaseBasicInfo(getStr(R.string.purchase_detail_max_num), info.prodNumber + ""));
        mRvBasic.setLayoutManager(new LinearLayoutManager(this));
        mRvBasic.setAdapter(new PurchaseDetailBasicAdapter(R.layout.rv_purchase_detail_basic, basicInfoLis));

        if (info.stProductParas.size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            initRv(mRvParam,manager);
            mRvParam.setAdapter(new PurchaseDetailParamAdapter(R.layout.rv_purchase_detail_param, info.stProductParas));
        } else {
            mRvParam.setVisibility(View.GONE);
            mTvParam.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(info.prodIntro)) {
            mTvInto.setText(getStr(R.string.purchase_detail_no_into));
        } else {
            mTvInto.setText(info.prodIntro);
        }

        List<String> pics = initPics(info.prodImage, info.prodImage2, info.prodImage3);
        if (pics.size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            initRv(mRvPic,manager);
            mRvPic.setAdapter(new PurchaseDetailPicAdapter(R.layout.rv_quote_pic, pics, this));
        } else {
            mRvPic.setVisibility(View.GONE);
        }

        mTvPurchaseTime.setText(getStr(R.string.purchase_detail_purchase_time) + info.prodTime);
        if (TextUtils.isEmpty(info.successTime)) {
            mTvEndTime.setVisibility(View.GONE);
        } else {
            mTvEndTime.setText(getStr(R.string.purchase_detail_end_time) + info.successTime);
        }

        mTvQuote.setText(getStr(R.string.purchase_detail_quote_info) + "(" + info.offerProdList.size() + ")");

        if (info.offerProdList.size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            initRv(mRvQuote,manager);
            mRvQuote.setAdapter(new PurchaseDetailQuoteAdapter(R.layout.rv_purchase_detail_quote, info.offerProdList, this,isCuo));
        } else {
            mLine.setVisibility(View.GONE);
        }
    }

    @Override
    public void deletePurchase() {
        ToastUtil.showShort(this, getStr(R.string.delete_success));
        Intent openMyPurchase = new Intent(this, ContainerFooterActivity.class);
        openMyPurchase.putExtra(Constant.TYPE, Constant.MY_PURCHASE);
        startActivity(openMyPurchase);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.stv_edit, R.id.stv_delete, R.id.iv_call})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.stv_edit:
                Intent openPublishPurchase = new Intent(this, PublishActivity.class);
                openPublishPurchase.putExtra(Msg.PUR_ID, mPurId);
                startActivity(openPublishPurchase);
                break;
            case R.id.stv_delete:
                DialogHelper.showCustomNoTitleDialog(this, getStr(R.string.purchase_detail_sure_delete), null, null, new DialogClickListener() {
                    @Override
                    public void positiveClick() {
                        mPresenter.deletePurchase(mPurId);
                    }

                    @Override
                    public void negativeClick() {

                    }
                });
                break;
            case R.id.iv_call:
                DialogHelper.showCustomNormalDialog(this, getStr(R.string.call_phone), getStr(R.string.call_phone_content) + com.hyphenate.easeui.utils.SPUtils.getString(this, Constant.REFER_PHONE, ""), null, null, new DialogClickListener() {
                    @Override
                    public void positiveClick() {
                        if (EasyPermissions.hasPermissions(PurchaseDetailActivity.this, perms)) {
                            makeCall();
                        } else {
                            EasyPermissions.requestPermissions(PurchaseDetailActivity.this, getStr(R.string.call_perm_msg),
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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
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
            ToastUtil.showLong(this, getStr(R.string.call_perm));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}

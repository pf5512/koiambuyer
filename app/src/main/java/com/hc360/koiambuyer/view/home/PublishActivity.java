package com.hc360.koiambuyer.view.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.adapter.PublishParamAdapter;
import com.hc360.koiambuyer.adapter.PublishPicAdapter;
import com.hc360.koiambuyer.api.bean.PostPicInfo;
import com.hc360.koiambuyer.api.bean.PublishPurchaseInfo;
import com.hc360.koiambuyer.api.bean.PurchaseDetailInfo;
import com.hc360.koiambuyer.engine.LimitInputNumWatcher;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.myinterface.DialogClickListener;
import com.hc360.koiambuyer.myinterface.ipresenter.IPublishPresenter;
import com.hc360.koiambuyer.myinterface.iview.IPublishView;
import com.hc360.koiambuyer.presenter.PublishPresenter;
import com.hc360.koiambuyer.utils.DialogHelper;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.ContainerActivity;
import com.hc360.koiambuyer.view.ContainerFooterActivity;
import com.hc360.koiambuyer.view.base.BasePhotoActivity;
import com.hc360.koiambuyer.widget.CustomToast;
import com.hc360.koiambuyer.widget.EditInput;
import com.hc360.koiambuyer.widget.ParamDialog;
import com.hc360.koiambuyer.widget.SingleTextView;
import com.hyphenate.easeui.utils.SPUtils;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

public class PublishActivity extends BasePhotoActivity<IPublishPresenter> implements IPublishView {

    @BindView(R.id.tv_add_param)
    TextView mTvAddParam;
    @BindView(R.id.ei_pro_name)
    EditInput mEiProName;
    @BindView(R.id.ei_pro_class)
    EditInput mEiProClass;
    @BindView(R.id.et_min_price)
    EditText mEtMinPrice;
    @BindView(R.id.et_max_price)
    EditText mEtMaxPrice;
    @BindView(R.id.ei_sample_pro_num)
    EditInput mEiSampleProNum;
    @BindView(R.id.ei_pro_num)
    EditInput mEiProNum;
    @BindView(R.id.ei_unit)
    EditInput mEiUnit;
    @BindView(R.id.rv_param)
    RecyclerView mRvParam;
    @BindView(R.id.et_into)
    EditText mEtInto;
    @BindView(R.id.rv_pic)
    RecyclerView mRvPic;
    @BindView(R.id.tv_add_pic)
    TextView mTvAddPic;
    @BindView(R.id.stv_submit)
    SingleTextView mStvSubmit;
    public List<PostPicInfo> mPics;
    @BindView(R.id.iv_call)
    ImageView mIvCall;
    @BindView(R.id.scrollView)
    NestedScrollView mScrollView;
    @BindView(R.id.card_submit)
    CardView mCardSubmit;
    private List<PurchaseDetailInfo.StProductParasBean> mParams;
    String[] pubPerms = {Manifest.permission.CALL_PHONE};
    private boolean isEdit = false;
    private int mPurId = -1;
    private PublishParamAdapter mParamAdapter;
    public static final int MAX_PIC_NUM = 10;
    public static final int MAX_PARAM_NUM = 10;
    private PublishPicAdapter mPicAdapter;
    private int mPicNum = 0;
    private CustomToast mToast;

    @Override
    protected void initView() {
        super.initView();
        initToolBar(getStr(R.string.publish_title), getStr(R.string.my_purchase_pub_title), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openMyPurchases = new Intent(PublishActivity.this, ContainerFooterActivity.class);
                openMyPurchases.putExtra(Constant.TYPE, Constant.MY_PURCHASE);
                int position = -1;
                openMyPurchases.putExtra(Msg.PURCHASE_STATE, position);
                startActivity(openMyPurchases);
            }
        });
        mPurId = getIntent().getIntExtra(Msg.PUR_ID, -1);

        mEiProName.initText(getStr(R.string.publish_pro_name), getStr(R.string.publish_pro_name_hint));
        mEiProClass.initText(getStr(R.string.publish_pro_class), getStr(R.string.publish_pro_class_hint));

        mEiSampleProNum.initText(getStr(R.string.publish_sample_pro_num), getStr(R.string.publish_sample_pro_num_hint));
        mEiSampleProNum.setEtInputType(InputType.TYPE_CLASS_NUMBER);
        mEiProNum.initText(getStr(R.string.publish_pro_num), getStr(R.string.publish_sample_pro_num_hint));
        mEiProNum.setEtInputType(InputType.TYPE_CLASS_NUMBER);
        mEiSampleProNum.setEtMaxNum();
        mEiProNum.setEtMaxNum();

        mEtMinPrice.addTextChangedListener(new LimitInputNumWatcher(mEtMinPrice));
        mEtMaxPrice.addTextChangedListener(new LimitInputNumWatcher(mEtMaxPrice));

        mEiUnit.initText(getStr(R.string.publish_pro_unit), getStr(R.string.publish_pro_unit_hint));
        mPics = new ArrayList<>();
        mParams = new ArrayList<>();
        mToast = new CustomToast(this);
        if (mPurId != -1) {
            mPresenter.getPurchase(mPurId);
        }
        if (TextUtils.isEmpty(SPUtils.getString(this, Constant.REFER_PHONE, ""))) {
            mIvCall.setVisibility(View.GONE);
        }
    }

    private void initRvParam() {
        if (mParamAdapter != null) {
            mParamAdapter.notifyDataSetChanged();
        } else {
            mRvParam.setLayoutManager(new LinearLayoutManager(this));
            mParamAdapter = new PublishParamAdapter(R.layout.rv_publish_param, mParams, this);
            mRvParam.setAdapter(mParamAdapter);
        }
    }

    @Override
    protected void initInjector() {
        mPresenter = new PublishPresenter(this);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_publish;
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

    @OnClick({R.id.tv_add_pic, R.id.stv_submit, R.id.tv_add_param, R.id.iv_call})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add_pic:
                if (mPicNum >= MAX_PIC_NUM) {
                    ToastUtil.showShort(this, getStr(R.string.publish_out_of_pic_num));
                } else {
                    if (mDialog != null) {
                        mDialog.show();
                    }
                }
                break;
            case R.id.stv_submit:
                String proName = mEiProName.getEtText();
                String proClass = mEiProClass.getEtText();
                String minPrice = mEtMinPrice.getText().toString().trim();
                String maxPrice = mEtMaxPrice.getText().toString().trim();
                String sampleProNum = mEiSampleProNum.getEtText();
                String proNum = mEiProNum.getEtText();
                String unit = mEiUnit.getEtText();
                if (TextUtils.isEmpty(proName)) {
                    ToastUtil.showShort(this, getStr(R.string.publish_pro_name_empty));
                } else if (TextUtils.isEmpty(proClass)) {
                    ToastUtil.showShort(this, getStr(R.string.publish_pro_class_empty));
                } else if (TextUtils.isEmpty(minPrice)) {
                    ToastUtil.showShort(this, getStr(R.string.publish_min_price_empty));
                } else if (TextUtils.isEmpty(maxPrice)) {
                    ToastUtil.showShort(this, getStr(R.string.publish_max_price_empty));
                } else if (TextUtils.isEmpty(sampleProNum)) {
                    ToastUtil.showShort(this, getStr(R.string.publish_sample_pro_empty));
                } else if (TextUtils.isEmpty(proNum)) {
                    ToastUtil.showShort(this, getStr(R.string.publish_pro_num_empty));
                } else if (TextUtils.isEmpty(unit)) {
                    ToastUtil.showShort(this, getStr(R.string.publish_unit_empty));
                } else {
                    if (Double.parseDouble(maxPrice) > Double.parseDouble(minPrice)) {
                        String into = mEtInto.getText().toString().toString();
                        List<String> pics = new ArrayList<>();
                        for (PostPicInfo pic : mPics) {
                            pics.add(pic.name);
                        }
                        if (mToast != null) {
                            mToast.alwaysShow(getStr(R.string.publish_post_submit));
                        }
                        Logger.e(pics.toString().replace(",", "&&").replace("[", "").replace("]", ""));
                        mPresenter.publishPurchase(proName, proClass, Integer.parseInt(proNum), Double.parseDouble(minPrice), Double.parseDouble(maxPrice), Integer.parseInt(sampleProNum), unit, into, pics, mParams, mPurId);
                    } else {
                        ToastUtil.showShort(this, getStr(R.string.publish_form_error));
                    }
                }
                break;
            case R.id.tv_add_param:
                addPublish();
                break;
            case R.id.iv_call:
                DialogHelper.showCustomNormalDialog(this, getStr(R.string.call_phone), getStr(R.string.call_phone_content) + SPUtils.getString(this, Constant.REFER_PHONE, ""), null, null, new DialogClickListener() {
                    @Override
                    public void positiveClick() {
                        if (EasyPermissions.hasPermissions(PublishActivity.this, pubPerms)) {
                            makeCall();
                        } else {
                            EasyPermissions.requestPermissions(PublishActivity.this, getStr(R.string.call_perm_msg),
                                    Constant.CALL_PHONE, pubPerms);
                        }
                    }

                    @Override
                    public void negativeClick() {

                    }
                });

                break;
        }
    }

    private void addPublish() {
        if (mParams.size() >= MAX_PARAM_NUM) {
            ToastUtil.showShort(this, getStr(R.string.publish_out_of_param_num));
        } else {
            addParam(mParams.size() + 1, mParams.size());
        }
    }

    private void makeCall() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + SPUtils.getString(this, Constant.REFER_PHONE, ""));
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

    public void addParam(int position, int num) {
        PurchaseDetailInfo.StProductParasBean info = null;
        if (position < mParams.size() + 1) {
            info = mParams.get(position - 1);
        }
        new ParamDialog(this, position, num, MAX_PARAM_NUM, info, new ParamDialog.onSaveListener() {
            @Override
            public void onSave(String name, String content, int position, boolean goOn) {
                if (mParams.size() < position) {
                    //添加
                    mParams.add(new PurchaseDetailInfo.StProductParasBean(name, content));
                } else {
                    //编辑
                    mParams.set(position - 1, new PurchaseDetailInfo.StProductParasBean(name, content));
                }
                initRvParam();
                if (goOn) {
                    ToastUtil.showShort(PublishActivity.this, getStr(R.string.save_success));
                    if (mParams.size() < MAX_PARAM_NUM) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        addPublish();
                                    }
                                });
                            }
                        }, 500);
                    }
                }
            }
        }).show();
    }

    @Override
    public void publishPurchase(PublishPurchaseInfo info) {
//        if (mToast != null) {
//            mToast.hide();
//        }
        Intent openPublishSuccess = new Intent(this, ContainerActivity.class);
        openPublishSuccess.putExtra(Constant.TYPE, Constant.PUBLISH_SUCCESS);
        startActivity(openPublishSuccess);
        finish();
    }

    @Override
    public void postPic(PostPicInfo info) {
        mPics.add(info);
//        if (mToast != null) {
//            mToast.hide();
//        }
        initRvPic();
    }

    @Override
    public void getPurchase(PurchaseDetailInfo info) {
        mEiProName.setEtText(info.prodName);
        mEiProClass.setEtText(info.proType);
        mEtMinPrice.setText(info.prodPrice + "");
        mEtMaxPrice.setText(info.productPriceMax + "");
        mEiSampleProNum.setEtText(info.productSampleCount + "");
        mEiProNum.setEtText(info.prodNumber);
        mEiUnit.setEtText(info.productUnit);
        if (!TextUtils.isEmpty(info.prodIntro)) {
            mEtInto.setText(info.prodIntro);
            mEtInto.setSelection(info.prodIntro.length());
        }
        mParams = info.stProductParas;
        initRvParam();
        initPics(info.imgs);
        mPicNum = mPics.size();
        initRvPic();
    }

    public void initPics(List<String> picUrls) {
        for (String s : picUrls) {
            if (!TextUtils.isEmpty(s)) {
                mPics.add(new PostPicInfo(s, s));
            }
        }
    }

    @Override
    public void onPhotoPick(File file, Bitmap bitmap) {
        mPicNum++;
//        if (mToast != null) {
//            mToast.alwaysShow(getStr(R.string.post_pic));
//        }
        mPresenter.postPic(file);
    }

    private void initRvPic() {
        if (mPicAdapter == null) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            initRv(mRvPic, manager);
            mPicAdapter = new PublishPicAdapter(R.layout.rv_publish_pic, mPics, this);
            mRvPic.setAdapter(mPicAdapter);
        } else {
            mPicAdapter.notifyDataSetChanged();
        }
        //这里对srcollview进行滑动
        Logger.e("cardSubmit_Top"+mCardSubmit.getTop());
//        mScrollView.smoothScrollTo(0, 10);

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                //设置ScrollView滚动到底部
                mScrollView.fullScroll(NestedScrollView.FOCUS_DOWN);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mToast != null) {
            mToast.hide();
        }
    }

    public void removePic(int position) {
        mPics.remove(position);
        mPicNum--;
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        super.onPermissionsGranted(requestCode, perms);
        if (requestCode == Constant.CALL_PHONE) {
            makeCall();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        super.onPermissionsDenied(requestCode, perms);
        if (requestCode == Constant.CALL_PHONE) {
            ToastUtil.showLong(this, getStr(R.string.call_perm));
        }
    }
}

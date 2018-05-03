package com.hc360.koiambuyer.view.me;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.UserBaseInfo;
import com.hc360.koiambuyer.engine.CustomTextWatcher;
import com.hc360.koiambuyer.engine.MaxNumTextWatcher;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.myinterface.DialogClickListener;
import com.hc360.koiambuyer.myinterface.DialogPositiveClickListener;
import com.hc360.koiambuyer.myinterface.PhotoDialogClickListener;
import com.hc360.koiambuyer.myinterface.ipresenter.IPersonalDataPresenter;
import com.hc360.koiambuyer.myinterface.iview.IPersonalDataView;
import com.hc360.koiambuyer.presenter.PersonalDataPresenter;
import com.hc360.koiambuyer.utils.DialogHelper;
import com.hc360.koiambuyer.utils.TVDrawableUtil;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.ContainerActivity;
import com.hc360.koiambuyer.view.ContainerFooterActivity;
import com.hc360.koiambuyer.view.MyApp;
import com.hc360.koiambuyer.view.base.BasePhotoActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 个人资料
 */
public class PersonalDataActivity extends BasePhotoActivity<IPersonalDataPresenter> implements PhotoDialogClickListener, EasyPermissions.PermissionCallbacks, IPersonalDataView {

    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_name)
    EditText mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_email)
    TextView mTvEmail;
    @BindView(R.id.ll_name)
    LinearLayout mLlName;
    @BindView(R.id.ll_phone)
    LinearLayout mLlPhone;
    @BindView(R.id.ll_email)
    LinearLayout mLlEmail;
    @BindView(R.id.ll_code)
    LinearLayout mLlCode;
    @BindView(R.id.last_line)
    View mLastLine;
    @BindView(R.id.tv_et_hint)
    TextView mTvEtHint;

    @BindView(R.id.iv_head)
    ImageView mIvHead;
    @BindView(R.id.tv_code)
    TextView mTvCode;
    @BindView(R.id.card)
    CardView mCard;
    private String mUserName = "";
    private String mUserHead = "";
    private String mQr = "";
    private boolean isGetData = false;
    private boolean isFinish = false;
    private String mEmail;
    private Bitmap mHeadBit;

    @Override
    protected void initView() {
        super.initView();
        initToolBar(getStr(R.string.personal_data_title), getStr(R.string.save), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMsg();
            }
        });
        setCropRatio("1:1");
        mTvName.addTextChangedListener(new CustomTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTvEtHint.setVisibility(TextUtils.isEmpty(s) ? View.VISIBLE : View.GONE);
            }
        });
//        mTvName.addTextChangedListener(new LimitInputTextWatcher(mTvName));
        mTvName.addTextChangedListener(new MaxNumTextWatcher(10, mTvName));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getUserBaseInfo(MyApp.sUserId);
        if (mHeadBit!=null){
            mCard.setVisibility(View.VISIBLE);
        }else {
            mCard.setVisibility(View.GONE);
        }
    }

    private void saveMsg() {
        String name = mTvName.getText().toString().trim();
        String email = mTvEmail.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            ToastUtil.showShort(PersonalDataActivity.this, getStr(R.string.must_have_name));
        } else {
            mUserName = name;
            mPresenter.updateInfo(mUserHead, name, email);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            onToolBarNavigationClick();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


    @Override
    public void onToolBarNavigationClick() {
        if (isGetData) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
            }
            if (mUserName.equals(mTvName.getText().toString().trim())) {
                finish();
            } else {
                DialogHelper.showCustomNoTitleDialog(PersonalDataActivity.this, getStr(R.string.have_not_save), null, getStr(R.string.save), new DialogClickListener() {
                    @Override
                    public void positiveClick() {
                        isFinish = true;
                        saveMsg();
                    }

                    @Override
                    public void negativeClick() {
                        finish();
                    }
                });

            }
        } else {
            finish();
        }
    }

    @Override
    protected void initInjector() {
        mPresenter = new PersonalDataPresenter(this);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_personal_data;
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @OnClick({R.id.iv_head, R.id.ll_name, R.id.ll_phone, R.id.ll_email, R.id.ll_code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                //头像
                mDialog.show();
                break;
            case R.id.ll_name:

                break;
            case R.id.ll_phone:
                //修改手机号
//                Intent changePhone = new Intent(PersonalDataActivity.this, ContainerMainFooterActivity.class);
//                changePhone.putExtra(Constant.TYPE, Constant.CHANGE_PHONE);
//                startActivity(changePhone);
                break;
            case R.id.ll_email:
                Intent openFragment = new Intent(PersonalDataActivity.this, ContainerFooterActivity.class);
                openFragment.putExtra(Constant.TYPE, Constant.BIND_EMAIL);
                if (TextUtils.isEmpty(mEmail)){
                    openFragment.putExtra(Msg.EMAIL, "");
                }else {
                    openFragment.putExtra(Msg.EMAIL, mEmail);
                }
                startActivityForResult(openFragment, Constant.OPEN_BIND_EMAIL);
                break;
            case R.id.ll_code:
                if (TextUtils.isEmpty(mQr)) {
                    ToastUtil.showShort(this, getStr(R.string.no_qr));
                } else {
                    Intent openQr = new Intent(PersonalDataActivity.this, ContainerActivity.class);
                    openQr.putExtra(Constant.TYPE, Constant.QR);
                    openQr.putExtra(Msg.QR, mQr);
                    startActivity(openQr);
                }
                break;
        }
    }

    @Override
    public void onPhotoPick(File file, Bitmap bitmap) {
        mIvHead.setImageBitmap(bitmap);
        mPresenter.postPic(file);
    }

    @Override
    public void postPic(String name, String httpName) {
        mUserHead = name;
        Glide.with(this).load(httpName).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                BitmapDrawable bd = (BitmapDrawable) resource;
                Bitmap bm = bd.getBitmap();
                mIvHead.setImageBitmap(bm);
                mCard.setVisibility(View.VISIBLE);
                mHeadBit = bm;
            }
        });
//        GlideUtils.loadHead(this, httpName, mIvHead);
    }

    @Override
    public void updateInfo() {
        if (isFinish) {
            finish();
            isFinish = false;
        } else {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
            }
//            DialogHelper.showCustomNoTitleDialog(this, getStr(R.string.save_success), getStr(R.string.go_on), getStr(R.string.back_me), new DialogClickListener() {
//                @Override
//                public void positiveClick() {
//                    finish();
//                }
//
//                @Override
//                public void negativeClick() {
//
//                }
//            });

            DialogHelper.showCustomNoTitleSureDialog(PersonalDataActivity.this, getStr(R.string.save_success), getStr(R.string.back_me), new DialogPositiveClickListener() {
                @Override
                public void positiveClick() {
                    finish();
                }
            });
        }
    }

    @Override
    public void getUserBaseInfo(UserBaseInfo info) {
        isGetData = true;
        if (!TextUtils.isEmpty(info.content.user.userName)) {
            mUserName = info.content.user.userName + "";
            mTvName.setText(mUserName);
            mTvName.setSelection(mUserName.length());
        }
        mTvPhone.setText(info.content.user.phone);
        mEmail = info.content.user.email;
        mTvEmail.setText(info.content.user.email);

        Glide.with(this).load(info.content.user.headImg).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                BitmapDrawable bd = (BitmapDrawable) resource;
                Bitmap bm         = bd.getBitmap();
                mIvHead.setImageBitmap(bm);
                mHeadBit = bm;
                mCard.setVisibility(View.VISIBLE);
            }
        });

//        GlideUtils.loadHead(this, info.content.user.headImg, mIvHead);
        mQr = info.content.user.userQrimg;
        if (!TextUtils.isEmpty(mQr)) {
            TVDrawableUtil.setRightByRes(this, R.mipmap.ic_qr, mTvCode);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

package com.hc360.koiambuyer.view.me;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.UserBaseInfo;
import com.hc360.koiambuyer.engine.CustomTextWatcher;
import com.hc360.koiambuyer.engine.LimitInputTextWatcher;
import com.hc360.koiambuyer.engine.MaxNumTextWatcher;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.myinterface.DialogClickListener;
import com.hc360.koiambuyer.myinterface.PhotoDialogClickListener;
import com.hc360.koiambuyer.myinterface.ipresenter.IPersonalDataPresenter;
import com.hc360.koiambuyer.myinterface.iview.IPersonalDataView;
import com.hc360.koiambuyer.presenter.PersonalDataPresenter;
import com.hc360.koiambuyer.utils.DialogHelper;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.ContainerMainFooterActivity;
import com.hc360.koiambuyer.view.MyApp;
import com.hc360.koiambuyer.view.base.BasePhotoActivity;

import java.io.File;

import butterknife.BindView;
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
    private String mCheckState = "-1";
    private String mRefuseReason = "";
    private String mUserName = "";
    private String mUserPosition = "";
    private boolean isGetData = false;
    private boolean isFinish = false;

    @Override
    protected void initView() {
        super.initView();
        initToolBar(getStr(R.string.personal_data_title), getStr(R.string.save), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMsg();
            }
        });
        mPresenter.getUserBaseInfo(MyApp.sUserId);
        mTvName.addTextChangedListener(new CustomTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTvEtHint.setVisibility(TextUtils.isEmpty(s) ? View.VISIBLE : View.GONE);
            }
        });
        mTvName.addTextChangedListener(new LimitInputTextWatcher(mTvName));
        mTvName.addTextChangedListener(new MaxNumTextWatcher(10, mTvName));
    }

    private void saveMsg() {
        String name = mTvName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            ToastUtil.showShort(PersonalDataActivity.this, getStr(R.string.must_have_name));
        } else {
            mUserName = name;
            mPresenter.updateInfo(new Integer(MyApp.sUserId), name, Constant.USER_NAME);
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
                Intent changePhone = new Intent(PersonalDataActivity.this, ContainerMainFooterActivity.class);
                changePhone.putExtra(Constant.TYPE, Constant.CHANGE_PHONE);
                startActivity(changePhone);
                break;
            case R.id.ll_email:

                break;
            case R.id.ll_code:

                break;
        }
    }


    @Override
    public void onPhotoPick(File file, Bitmap bitmap) {
        mIvHead.setImageBitmap(bitmap);
        mPresenter.postPic(file);
    }

    @Override
    public void postPic(String name) {
        mPresenter.updateInfo(new Integer(MyApp.sUserId), name, Constant.HEAD);
    }

    @Override
    public void updateInfo(String type) {
        switch (type) {
            case Constant.USER_NAME:
                ToastUtil.showShort(this, getStr(R.string.save_success));
                finish();
                break;
            case Constant.USER_POSITION:
                break;
            case Constant.HEAD:
                ToastUtil.showShort(this, getStr(R.string.head_have_change));
                break;
        }
        if (isFinish) {
            finish();
            isFinish = false;
        }
    }

    @Override
    public void getUserBaseInfo(UserBaseInfo info) {
        isGetData = true;
        mUserName = info.content.userName;
        mTvName.setText(mUserName);
        mTvName.setSelection(mUserName.length());

        mCheckState = info.content.checkState;
        String refuseReason = info.content.refuseReason;
        if (TextUtils.isEmpty(refuseReason)) {
            mRefuseReason = refuseReason;
        }

        mUserPosition = info.content.userPosition;
        if (!TextUtils.isEmpty(mUserPosition)) {

        } else {
            mUserPosition = "";
        }
        Glide.with(this).load(info.content.userHeadImg).into(mIvHead);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.OPEN_SET_NAME_FROM_PERSONAL:
                    mUserName = data.getStringExtra(Msg.MSG);
                    if (!TextUtils.isEmpty(mUserName)) {
                        mTvName.setText(mUserName);
                        mPresenter.updateInfo(new Integer(MyApp.sUserId), mUserName, Constant.USER_NAME);
                    }
                    break;
                case Constant.OPEN_SET_POSITION:
                    mUserPosition = data.getStringExtra(Msg.MSG);
                    if (!TextUtils.isEmpty(mUserPosition)) {

                        mPresenter.updateInfo(new Integer(MyApp.sUserId), mUserPosition, Constant.USER_POSITION);
                    }
                    break;
            }
        }
    }
}

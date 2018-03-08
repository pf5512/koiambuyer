package com.hc360.koiambuyer.view.me;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.SettingInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.myinterface.DialogPositiveClickListener;
import com.hc360.koiambuyer.myinterface.ipresenter.ISettingPresenter;
import com.hc360.koiambuyer.myinterface.iview.ISettingView;
import com.hc360.koiambuyer.presenter.SettingPresenter;
import com.hc360.koiambuyer.utils.DialogHelper;
import com.hc360.koiambuyer.utils.SPUtils;
import com.hc360.koiambuyer.utils.TVDrawableUtil;
import com.hc360.koiambuyer.utils.VersionUtils;
import com.hc360.koiambuyer.view.ContainerFooterActivity;
import com.hc360.koiambuyer.view.MyApp;
import com.hc360.koiambuyer.view.base.BaseActivity;
import com.hc360.koiambuyer.view.home.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity<ISettingPresenter> implements ISettingView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_change_phone)
    TextView mTvChangePhone;
    @BindView(R.id.tv_change_pwd)
    TextView mTvChangePwd;
    @BindView(R.id.ll_update_version)
    LinearLayout mLlUpdateVersion;
    @BindView(R.id.tv_login_out)
    TextView mTvLoginOut;
    @BindView(R.id.tv_version)
    TextView mTvVersion;
    @BindView(R.id.tv_about_us)
    TextView mTvAboutUs;
    private String mVersion = "";
    private String mAppVersionName;
    private boolean isExit = false;

    private ProgressDialog mDialog;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initView() {
        initToolBar(mToolbar, null, true, getStr(R.string.me_setting));
        setRight(mTvAboutUs,mTvVersion,mTvChangePhone,mTvChangePwd);
        mAppVersionName = VersionUtils.getAppVersionName(this);
        mTvVersion.setText("V" + mAppVersionName);
        mDialog = new ProgressDialog(this);
    }

    private void setRight(TextView ... tvs){
        for (TextView tv : tvs) {
            TVDrawableUtil.setRightByRes(this, R.mipmap.in,tv);
        }
    }

    @Override
    public void dialogShow(String msg) {
        if (mDialog!= null){
            mDialog.setMessage(msg);
            mDialog.show();
        }
    }

    @Override
    public void dialogDismiss() {
        if (mDialog!= null){
            mDialog.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getSettingInfo(MyApp.sUserId);
    }

    @Override
    protected void initInjector() {
        mPresenter = new SettingPresenter(this);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_setting;
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

    @OnClick({R.id.tv_change_phone, R.id.tv_change_pwd,  R.id.ll_update_version, R.id.tv_login_out, R.id.tv_about_us})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_change_phone:
                //更换手机号
                Intent changePhone = new Intent(SettingActivity.this, ContainerFooterActivity.class);
                changePhone.putExtra(Constant.TYPE, Constant.CHANGE_PHONE);
                startActivity(changePhone);
                break;
            case R.id.tv_change_pwd:
                //更换密码
                Intent changePwd = new Intent(SettingActivity.this, ContainerFooterActivity.class);
                changePwd.putExtra(Constant.TYPE, Constant.CHANGE_PWD);
                startActivity(changePwd);
                break;

            case R.id.ll_update_version:
                //版本更新
                if (mVersion.equals(mAppVersionName)) {
                    DialogHelper.showCustomNoTitleSureDialog(this, getStr(R.string.new_version), getStr(R.string.know), null);
                } else {
                    DialogHelper.showCustomNoTitleSureDialog(this, getStr(R.string.new_version_is) + mVersion + getStr(R.string.please_update), getStr(R.string.know), null);
                }
                break;
            case R.id.tv_login_out:
                //退出
                DialogHelper.showCustomNoTitleSureDialog(this, getStr(R.string.exit_this), null, new DialogPositiveClickListener() {
                    @Override
                    public void positiveClick() {
                        mPresenter.loginOut();
                    }
                });
                break;
            case R.id.tv_about_us:
                //关于我们
//                Intent openAboutUs = new Intent(this,ContainerFooterActivity.class);
//                openAboutUs.putExtra(Constant.TYPE,Constant.ABOUT_US);
//                openAboutUs.putExtra(Msg.VERSION,mAppVersionName);
//                startActivity(openAboutUs);
                break;
        }
    }

    @Override
    public void getSettingInfo(SettingInfo info) {
        mVersion = info.content.version;
    }

    @Override
    public void loginOutSuccess() {
        isExit = true;
        MyApp.sUserId = "";
        SPUtils.saveString(SettingActivity.this, Constant._ID, "");
        SPUtils.saveString(SettingActivity.this, Constant._COM_ID, "");
        SPUtils.saveString(SettingActivity.this, Constant._PHONE, "");
        SPUtils.saveString(SettingActivity.this, Constant._USER_NAME, "");
        SPUtils.saveString(SettingActivity.this, Constant.USER_IMG, "");
        SPUtils.saveString(SettingActivity.this, Constant.CHECK_STATE, "");
        startActivity(new Intent(SettingActivity.this, HomeActivity.class));
        dialogDismiss();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isExit) {
            SPUtils.saveString(SettingActivity.this, Constant._ID, "");
            isExit = false;
        }
    }
}

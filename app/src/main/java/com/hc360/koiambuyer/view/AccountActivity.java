package com.hc360.koiambuyer.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.engine.CustomTextWatcher;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.model.SmsStautsEnum;
import com.hc360.koiambuyer.model.States;
import com.hc360.koiambuyer.myinterface.IdentifyListener;
import com.hc360.koiambuyer.myinterface.ipresenter.IAccountPresenter;
import com.hc360.koiambuyer.myinterface.iview.IAccountView;
import com.hc360.koiambuyer.presenter.AccountPresenter;
import com.hc360.koiambuyer.utils.DialogHelper;
import com.hc360.koiambuyer.utils.FormTool;
import com.hc360.koiambuyer.utils.PopUtils;
import com.hc360.koiambuyer.utils.SPUtils;
import com.hc360.koiambuyer.utils.TVDrawableUtil;
import com.hc360.koiambuyer.utils.TabHelper;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.base.BaseActivity;
import com.hc360.koiambuyer.view.home.HomeActivity;
import com.hc360.koiambuyer.widget.SingleTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountActivity extends BaseActivity<IAccountPresenter> implements IAccountView {

    private static final int SEND_LOGIN_MSG = 111;
    private static final int SEND_REGISTER_MSG = 222;
    @BindView(R.id.tv_login)
    TextView mTvLogin;
    @BindView(R.id.tv_register)
    TextView mTvRegister;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.tv_identify)
    TextView mTvLoginIdentify;
    @BindView(R.id.tv_login_way)
    TextView mTvLoginWay;
    @BindView(R.id.tv_forget)
    TextView mTvForget;
    @BindView(R.id.tv_agree)
    TextView mTvAgree;
    @BindView(R.id.stv_sure)
    SingleTextView mStvSure;
    @BindView(R.id.iv_qq)
    ImageView mIvQq;
    @BindView(R.id.iv_wei_xin)
    ImageView mIvWeiXin;
    @BindView(R.id.iv_hc360)
    ImageView mIvHc360;
    @BindView(R.id.fl_foot)
    FrameLayout mFlFoot;
    @BindView(R.id.tv_or)
    TextView mTvOr;
    @BindView(R.id.ll_three_login)
    LinearLayout mLlThreeLogin;
    @BindView(R.id.tv_re_identify)
    TextView mTvRegisterIdentify;
    @BindView(R.id.bottom)
    View bottom;
    @BindView(R.id.head)
    LinearLayout head;
    @BindView(R.id.tv_account_xy)
    TextView mTvAccountXy;
    @BindView(R.id.tv_secret_xy)
    TextView mTvSecretXy;
    @BindView(R.id.ll_agree)
    LinearLayout mLlAgree;
    @BindView(R.id.iv_delete)
    ImageView mIvDelete;

    private boolean isLoginByIdentify = false;
    private boolean isRegister = false;
    private boolean isGetIdentify;
    private boolean isLoginIdentify;
    private boolean isRegisterIdentify;
    private boolean isAgree = true;
    private ProgressDialog mDialog;
    private String phone;
    private String password;

    private int l = 59;
    private int ml = 900;
    private int TIME = 1000;
    private int r = 59;
    private int mr = 900;
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SEND_LOGIN_MSG:
                    if (isLoginIdentify) {
                        if (l > 0) {
                            l--;
                            if (l >= 0) {
                                mTvLoginIdentify.setText(l + "s");
                                if (mTvLoginIdentify.getText().toString().equals("0s")) {
                                    mTvLoginIdentify.setText(getResources().getString(R.string.get_identify));
                                    mTvLoginIdentify.setClickable(true);
                                }
                            }
                        } else {
                            mTvLoginIdentify.setText(getResources().getString(R.string.get_identify));
                            mTvLoginIdentify.setClickable(true);
                        }
                        if (ml > 0) {
                            ml--;
                        } else {
                            isLoginIdentify = false;
                            SPUtils.saveBoolean(AccountActivity.this, Msg.IS_LOGIN_IDENTIFY, false);
                        }
                    }
                    break;
                case SEND_REGISTER_MSG:
                    if (isRegisterIdentify) {
                        if (r > 0) {
                            r--;
                            mTvRegisterIdentify.setText(r + "s");
                            if (mTvRegisterIdentify.getText().toString().equals("0s")) {
                                mTvRegisterIdentify.setText(getResources().getString(R.string.get_identify));
                                mTvRegisterIdentify.setClickable(true);
                            }
                        } else {
                            mTvRegisterIdentify.setText(getResources().getString(R.string.get_identify));
                            mTvRegisterIdentify.setClickable(true);
                        }
                        if (mr > 0) {
                            mr--;
                        } else {
                            isRegisterIdentify = false;
                            SPUtils.saveBoolean(AccountActivity.this, Msg.IS_REGISTER_IDENTIFY, false);
                        }
                    }
                    break;
                default:
                    break;
            }
        }

        ;
    };

    @Override
    protected void initView() {
        mDialog = new ProgressDialog(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        String etText = SPUtils.getString(this, Msg.LAST_ACCOUNT, "");
        mEtPhone.setText(etText);
        mEtPhone.setSelection(etText.length());
        mIvDelete.setVisibility(TextUtils.isEmpty(etText)?View.GONE:View.VISIBLE);
        mTvAccountXy.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
        mTvSecretXy.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
        boolean register = SPUtils.getBoolean(this, Msg.LAST_STATE, false);
        if (register) {
            tabChange(true, getResources().getString(R.string.input_phone), getResources().getString(R.string.input_identify), getResources().getString(R.string.next));
        } else {
            tabChange(false, getResources().getString(R.string.phone_email), getResources().getString(R.string.pwd), getResources().getString(R.string.login));
            loginWayChange(isLoginByIdentify);
        }
        isLoginIdentify = SPUtils.getBoolean(AccountActivity.this, Msg.IS_LOGIN_IDENTIFY, false);
        isRegisterIdentify = SPUtils.getBoolean(AccountActivity.this, Msg.IS_REGISTER_IDENTIFY, false);

        mEtPhone.addTextChangedListener(new CustomTextWatcher(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                mIvDelete.setVisibility(TextUtils.isEmpty(s)?View.GONE:View.VISIBLE);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        String trim = mEtPhone.getText().toString().trim();
        SPUtils.saveString(this, Msg.LAST_ACCOUNT, trim);
        SPUtils.saveBoolean(this, Msg.LAST_STATE, isRegister);
    }

    @Override
    protected void initInjector() {
        mPresenter = new AccountPresenter(this);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_account;
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @OnClick({R.id.tv_login, R.id.tv_register, R.id.tv_identify, R.id.tv_re_identify, R.id.tv_login_way, R.id.tv_forget, R.id.tv_agree, R.id.stv_sure, R.id.iv_qq, R.id.iv_wei_xin, R.id.iv_hc360, R.id.fl_foot, R.id.tv_account_xy, R.id.tv_secret_xy, R.id.iv_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                //tab标签的登录
                tabChange(false, getResources().getString(R.string.phone_email), getResources().getString(R.string.pwd), getResources().getString(R.string.login));
                break;
            case R.id.tv_register:
                //tab标签的注册
                tabChange(true, getResources().getString(R.string.input_phone), getResources().getString(R.string.input_identify), getResources().getString(R.string.next));
                break;
            case R.id.tv_identify:
            case R.id.tv_re_identify:
                //逻辑需要改，根据服务器返回进行数据展示，没有网，就不倒计时
                phone = mEtPhone.getText().toString().trim();
                mPresenter.sendIdentify(phone, isRegister ? SmsStautsEnum.REG.getValue() : SmsStautsEnum.NOTELOGIN.getValue());
                SPUtils.saveString(this, Msg.LAST_ACCOUNT, phone);
                //获取验证码的按钮
                break;

            case R.id.tv_login_way:
                //登录方式的按钮，是验证码还是密码
                loginWayChange(isLoginByIdentify);
                break;
            case R.id.tv_forget:
                //忘记密码的按钮
                Intent openForgetPwd = new Intent(AccountActivity.this, ContainerMainFooterActivity.class);
                openForgetPwd.putExtra(Constant.TYPE, Constant.FORGET_PWD);
                startActivity(openForgetPwd);
                break;
            case R.id.tv_agree:
                //输入框下协议
                isAgree = !isAgree;
                TVDrawableUtil.setLeftNormalByRes(this, isAgree ? R.mipmap.login_select : R.mipmap.login_not_select, mTvAgree);
                break;
            case R.id.stv_sure:
                //登录和注册的按钮
                password = mEtPassword.getText().toString().trim();
                phone = mEtPhone.getText().toString().trim();
                if (TextUtils.isEmpty(password) || TextUtils.isEmpty(phone)) {
                    ToastUtil.showShort(this, getResources().getString(R.string.input_cannot_empty));
                    return;
                }
                if (isRegister) {
                    //注册
                    if (isAgree) {
                        if (FormTool.isPhone(phone)) {
                            if (isRegisterIdentify) {
                                //其实是检验验证码
                                mPresenter.register(phone, password);
                            } else {
                                ToastUtil.showShort(this, getResources().getString(R.string.please_get_identify));
                            }
                        } else {
                            ToastUtil.showShort(this, getResources().getString(R.string.phone_incorrect));
                        }
                    } else {
                        ToastUtil.showShort(this, getResources().getString(R.string.agree_agreement));
                    }
                } else {
                    //登录
                    if (isLoginByIdentify) {
                        //验证码登录
                        if (FormTool.isPhone(phone)) {
                            //如果已经发送了验证码
                            if (isLoginIdentify) {
                                mPresenter.loginByIdentify(phone, password);
                            } else {
                                ToastUtil.showShort(this, getResources().getString(R.string.please_get_identify));
                            }
                        } else {
                            ToastUtil.showShort(this, getResources().getString(R.string.account_incorrect));
                        }
                    } else {
                        if (FormTool.isPhone(phone) || FormTool.isEmail(phone)) {
                            //密码登录
                            //密码需要6~18位数字
                            if (password.length() > 18 || password.length() < 6) {
                                ToastUtil.showShort(this, getResources().getString(R.string.input_format_pwd));
                                return;
                            }
                            int count = SPUtils.getInt(this, phone, 0);
                            if (count > 2) {
                                DialogHelper.showIdentifyDialog(this, new IdentifyListener() {
                                    @Override
                                    public void onCodeCorrect() {
                                        mPresenter.loginByPwd(phone, password);
                                    }
                                });
                            } else {
                                mPresenter.loginByPwd(phone, password);
                            }
                        } else {
                            ToastUtil.showShort(this, getResources().getString(R.string.account_incorrect));
                        }
                    }
                }
                break;
            case R.id.iv_qq:
                break;
            case R.id.iv_wei_xin:
                break;
            case R.id.iv_hc360:
                break;
            case R.id.fl_foot:
                break;
            case R.id.tv_account_xy:
                PopUtils.showSecret(this, head, false);
                break;
            case R.id.tv_secret_xy:
                PopUtils.showSecret(this, head, true);
                break;
            case R.id.iv_delete:
                mEtPhone.setText("");
                break;
        }
    }

    @Override
    public void loginCount(String phone) {
        int count = SPUtils.getInt(this, phone, 0);
        count += 1;
        SPUtils.saveInt(this, phone, count);
    }

    @Override
    public void loginSuccess(String msg, String id) {
        SPUtils.saveBoolean(this, Msg.IS_LOGIN_IDENTIFY, false);
        SPUtils.saveBoolean(this, Msg.IS_REGISTER_IDENTIFY, false);
        dialogDismiss();
        switch (msg) {
            case States.OK:
                //调用查看是否选择身份
                mPresenter.getInitStates(id);
                break;
            case States.NEED_PWD:
                //跳转到设置密码界面,需要更新密码
                SPUtils.saveString(MyApp.getAppContext(), Constant.TEMP_ID, id);
                Intent openSettingPwd = new Intent(AccountActivity.this, ContainerFooterActivity.class);
                openSettingPwd.putExtra(Constant.TYPE, Constant.SETTING_PWD);
                openSettingPwd.putExtra(Msg.PHONE, Msg.UPDATE_PWD);
                startActivity(openSettingPwd);
                break;
            case States.NEED_PHONE_PWD:
                //第三方登录第一次，才会返回这个
                break;
        }
    }


    /**
     * 验证码正确，跳转到设置密码界面
     */
    @Override
    public void registerSuccessByIdentify() {
        Intent openSettingPwd = new Intent(AccountActivity.this, ContainerFooterActivity.class);
        openSettingPwd.putExtra(Constant.TYPE, Constant.SETTING_PWD);
        openSettingPwd.putExtra(Msg.PHONE, phone);
        startActivity(openSettingPwd);
    }

    /**
     * @param state
     */
    @Override
    public void getUserStates(String state) {
        //登录成功，暂时跳到主页
        startActivity(new Intent(this, HomeActivity.class));
        //这里需要判断是否已经选择了城市经理
//        startActivity(new Intent(this,CityManagerActivity.class));

    }

    @Override
    public void dialogShow(String msg) {
        mDialog.setMessage(msg);
        mDialog.show();
    }

    @Override
    public void dialogDismiss() {
        mDialog.dismiss();
    }

    /**
     * 短信发送成功，开始倒计时
     */
    @Override
    public void sendSuccess(String businessName) {
        if (businessName.equals(SmsStautsEnum.REG.getValue())) {
            isRegisterIdentify = true;
            r = 19;
            mTvRegisterIdentify.setClickable(false);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (r > 0) {
                        try {
                            Thread.sleep(TIME);
                            Message msg = new Message();
                            msg.what = SEND_REGISTER_MSG;
                            mHandler.sendMessage(msg);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            mTvRegisterIdentify.setText(r + "s");
            SPUtils.saveBoolean(this, Msg.IS_REGISTER_IDENTIFY, true);
        } else if (businessName.equals(SmsStautsEnum.NOTELOGIN.getValue())) {
            isLoginIdentify = true;
            l = 19;
            mTvLoginIdentify.setClickable(false);
            mTvLoginIdentify.setText(l + "s");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (l > 0) {
                        try {
                            Thread.sleep(TIME);
                            Message msg = new Message();
                            msg.what = SEND_LOGIN_MSG;
                            mHandler.sendMessage(msg);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            SPUtils.saveBoolean(this, Msg.IS_LOGIN_IDENTIFY, true);
        }
        ToastUtil.showShort(AccountActivity.this, getResources().getString(R.string.send_identify_already));
    }

    private void loginWayChange(boolean loginByIdentify) {
        mEtPhone.setHint(new SpannableString(loginByIdentify ? getResources().getString(R.string.phone_email) : getResources().getString(R.string.input_phone)));
        mEtPassword.setHint(new SpannableString(loginByIdentify ? getResources().getString(R.string.pwd) : getResources().getString(R.string.input_identify)));
        mTvLoginWay.setText(loginByIdentify ? getResources().getString(R.string.login_by_identify) : getResources().getString(R.string.login_by_pwd));
        mTvLoginIdentify.setVisibility(loginByIdentify ? View.GONE : View.VISIBLE);
        //修改输入格式
        mEtPhone.setInputType(loginByIdentify ? InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS : InputType.TYPE_CLASS_NUMBER);
        mEtPassword.setTransformationMethod(loginByIdentify ? PasswordTransformationMethod.getInstance() : HideReturnsTransformationMethod.getInstance());
        isLoginByIdentify = !loginByIdentify;
        mEtPassword.setText("");
    }

    private void tabChange(boolean register, String etPhone, String etPassword, String tvSure) {
        mEtPhone.setHint(new SpannableString(etPhone));
        mEtPassword.setHint(new SpannableString(etPassword));
        mStvSure.setText(tvSure);
        mTvLoginWay.setVisibility(register ? View.GONE : View.VISIBLE);
        if (!register) {
            isLoginByIdentify = false;
        }
        mTvForget.setVisibility(register ? View.GONE : View.VISIBLE);
        mTvOr.setVisibility(View.GONE);
        mLlThreeLogin.setVisibility(View.GONE);
        if (register) {
            mTvRegisterIdentify.setVisibility(View.VISIBLE);
            mTvLoginIdentify.setVisibility(View.GONE);
        } else {
            mTvRegisterIdentify.setVisibility(View.GONE);
            mTvLoginIdentify.setVisibility(View.GONE);
        }
        mLlAgree.setVisibility(register ? View.VISIBLE : View.GONE);
        TabHelper.changeMainTab(AccountActivity.this, register ? mTvRegister : mTvLogin, register ? mTvLogin : mTvRegister);
        //修改输入格式
        mEtPhone.setInputType(register ? InputType.TYPE_CLASS_NUMBER : InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        mEtPassword.setTransformationMethod(register ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
        isRegister = register;
        mEtPassword.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

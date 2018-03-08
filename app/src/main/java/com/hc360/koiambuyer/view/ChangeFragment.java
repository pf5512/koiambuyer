package com.hc360.koiambuyer.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.model.SmsStautsEnum;
import com.hc360.koiambuyer.myinterface.DialogPositiveClickListener;
import com.hc360.koiambuyer.myinterface.ipresenter.IChangePresenter;
import com.hc360.koiambuyer.myinterface.iview.IChangeView;
import com.hc360.koiambuyer.presenter.ChangePresenter;
import com.hc360.koiambuyer.utils.DialogHelper;
import com.hc360.koiambuyer.utils.FormTool;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.base.BaseFragment;
import com.hc360.koiambuyer.widget.SingleTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hc360.koiambuyer.view.MyApp.sPhone;


public class ChangeFragment extends BaseFragment<IChangePresenter> implements IChangeView {
    String mMode = "";
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.ll_phone)
    LinearLayout mLlPhone;
    @BindView(R.id.line)
    View mLine;
    @BindView(R.id.et_identify)
    EditText mEtIdentify;
    @BindView(R.id.btn_get_identify)
    Button mBtnGetIdentify;
    @BindView(R.id.stv)
    SingleTextView mStv;
    @BindView(R.id.tv_prompt)
    TextView mTvPrompt;
    private int i = 59;
    private int TIME = 1000;
    Handler mHandler = new Handler();
    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                mHandler.postDelayed(this, TIME);
                if (i>0){
                    mBtnGetIdentify.setText((i--) + "s");
                }else{
                    mBtnGetIdentify.setText(getStr(R.string.re_send));
                    mBtnGetIdentify.setClickable(true);
                    mHandler.removeMessages(0);
                    i=59;
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };
    private String mPhone;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_change;
    }

    @Override
    protected void initInjector() {
        mPresenter = new ChangePresenter(this);
    }

    @Override
    protected void initViews() {
        mStv.setText(getStr(R.string.change_sure));
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle args = getArguments();
        if (args != null) {
            mMode = args.getString(Constant.MODE);
        }
        if (mMode.equals(Constant.CHANGE_PHONE)) {
            //更换手机
            mTvPhone.setText(getStr(R.string.phone_is)+ sPhone);
            mLlPhone.setVisibility(View.VISIBLE);
        } else if (mMode.equals(Constant.CHANGE_PWD)) {
            //更换密码
            mPhone = sPhone;
            mTvPhone.setText(getStr(R.string.phone_is)+ sPhone);
            mTvPrompt.setText(getStr(R.string.need_phone));
            mLine.setVisibility(View.GONE);
            mLlPhone.setVisibility(View.GONE);
        } else if (mMode.equals(Constant.FORGET_PWD)) {
            //忘记密码,发短信的枚举注意
            mTvPhone.setVisibility(View.GONE);
            mTvPrompt.setVisibility(View.GONE);
            mStv.setText(getStr(R.string.next));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.btn_get_identify, R.id.stv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_identify:
                if (mMode.equals(Constant.CHANGE_PHONE)) {
                    //更换手机
                    mPhone = mEtPhone.getText().toString().trim();
                    if (FormTool.isPhone(mPhone)){
                        mPresenter.sendIdentify(mPhone, SmsStautsEnum.UPDATE.getValue());
                    }else{
                        ToastUtil.showShort(mContext,getStr(R.string.phone_not_correct));
                    }
                } else if (mMode.equals(Constant.CHANGE_PWD)) {
                    //更换密码
                    mPresenter.sendIdentify(sPhone, SmsStautsEnum.FINDPASSWORD.getValue());
                } else if (mMode.equals(Constant.FORGET_PWD)) {
                    //忘记密码
                    mPhone = mEtPhone.getText().toString().trim();
                    if (FormTool.isPhone(mPhone)){
                        mPresenter.sendIdentify(mPhone, SmsStautsEnum.FINDPASSWORD.getValue());
                    }else{
                        ToastUtil.showShort(mContext,getStr(R.string.phone_not_correct));
                    }
                }

                break;
            case R.id.stv:
                String code = mEtIdentify.getText().toString().trim();
                if (TextUtils.isEmpty(code)){
                    ToastUtil.showShort(mContext,getStr(R.string.input_identify));
                    return;
                }
                if (TextUtils.isEmpty(mPhone)){
                    ToastUtil.showShort(mContext,getStr(R.string.input_phone));
                }
                if (mMode.equals(Constant.CHANGE_PHONE)) {
                    //更换手机
                    mPresenter.changePhone(new Integer(MyApp.sUserId),mPhone,code,SmsStautsEnum.UPDATE.getValue());
                } else if (mMode.equals(Constant.CHANGE_PWD)) {
                    //更换密码,暂时用忘记密码的
                    mPresenter.changePwd(sPhone,code, SmsStautsEnum.FINDPASSWORD.getValue());
                } else if (mMode.equals(Constant.FORGET_PWD)) {
                    //忘记密码
                    mPresenter.changePwd(mPhone,code, SmsStautsEnum.FINDPASSWORD.getValue());
                }
                break;
        }
    }

    @Override
    public void sendIdentify() {
        mBtnGetIdentify.setClickable(false);
        mHandler.postDelayed(mRunnable, TIME);
    }

    @Override
    public void submit() {
        if (mMode.equals(Constant.CHANGE_PHONE)) {
            //更换手机
            showDialog(getStr(R.string.change_success));
        } else if (mMode.equals(Constant.CHANGE_PWD)) {
            //更换密码
            Intent openSetPassword = new Intent(mContext, ContainerFooterActivity.class);
            openSetPassword.putExtra(Constant.TYPE,Constant.SET_PASSWORD_UPDATE);
            startActivity(openSetPassword);
            getActivity().finish();
        } else if (mMode.equals(Constant.FORGET_PWD)) {
            //忘记密码
            Intent openSetPassword = new Intent(mContext, ContainerFooterActivity.class);
            openSetPassword.putExtra(Constant.TYPE,Constant.SET_PASSWORD_FORGET);
            openSetPassword.putExtra(Msg.PHONE,mPhone);
            startActivity(openSetPassword);
            getActivity().finish();
        }
    }

    public void showDialog(String msg){
        DialogHelper.showCustomNoTitleSureDialog(getActivity(), msg, null, new DialogPositiveClickListener() {
            @Override
            public void positiveClick() {
                getActivity().finish();
            }
        });
    }

    @Override
    public void toRegister() {
        DialogHelper.showCustomNoCancelDialog(getActivity(), null, getStr(R.string.have_not_register), null, new DialogPositiveClickListener() {
            @Override
            public void positiveClick() {
                getActivity().finish();
                //跳转到注册界面
            }
        });
    }
}

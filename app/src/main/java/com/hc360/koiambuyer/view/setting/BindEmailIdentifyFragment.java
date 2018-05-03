package com.hc360.koiambuyer.view.setting;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.model.SmsStautsEnum;
import com.hc360.koiambuyer.myinterface.DialogPositiveClickListener;
import com.hc360.koiambuyer.myinterface.ipresenter.IBindEmailIdentifyPresenter;
import com.hc360.koiambuyer.myinterface.iview.IBindEmailIdentifyView;
import com.hc360.koiambuyer.presenter.BindEmailIdentifyPresenter;
import com.hc360.koiambuyer.utils.DialogHelper;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.ContainerFooterActivity;
import com.hc360.koiambuyer.view.base.BaseFragment;
import com.hc360.koiambuyer.widget.SingleTextView;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;



public class BindEmailIdentifyFragment extends BaseFragment<IBindEmailIdentifyPresenter> implements IBindEmailIdentifyView {

    @BindView(R.id.tv_remind)
    TextView mTvRemind;
    @BindView(R.id.et_identify)
    EditText mEtIdentify;
    @BindView(R.id.btn_get_identify)
    TextView mBtnGetIdentify;
    @BindView(R.id.stv)
    SingleTextView mStv;
    private String msg;
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
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_bind_email_identify;
    }

    @Override
    protected void initInjector() {
        mPresenter = new BindEmailIdentifyPresenter(this);
    }

    @Override
    protected void initViews() {
        mStv.setText(getStr(R.string.bind));
        msg = ((ContainerFooterActivity) getActivity()).getMsg();
        mTvRemind.setText(getStr(R.string.remind)+ msg +getStr(R.string.remind_identify));

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @OnClick({R.id.btn_get_identify, R.id.stv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_identify:
                //获取验证码
                mPresenter.sendEmail(msg, SmsStautsEnum.ACCOUNTEMAIL.getValue());
                break;
            case R.id.stv:
                //绑定邮箱
                String code = mEtIdentify.getText().toString().trim();
                if (TextUtils.isEmpty(code)){
                    ToastUtil.showShort(mContext,getStr(R.string.input_identify));
                }else{
                    mPresenter.checkEmail(msg,SmsStautsEnum.ACCOUNTEMAIL.getValue(),code);
                }
                break;
        }
    }

    @Override
    public void sendEmail() {
        mHandler.postDelayed(mRunnable, TIME);
    }

    @Override
    public void checkEmail() {
        DialogHelper.showCustomNoTitleSureDialog(getActivity(), getStr(R.string.bind_success), null, new DialogPositiveClickListener() {
            @Override
            public void positiveClick() {
                Intent intent = new Intent();
                intent.putExtra(Msg.BIND_EMAIL,true);
                getActivity().setResult(RESULT_OK,intent);
                getActivity().finish();
            }
        });
    }
}

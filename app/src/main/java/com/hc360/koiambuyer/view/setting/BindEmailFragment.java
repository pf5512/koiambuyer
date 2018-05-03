package com.hc360.koiambuyer.view.setting;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.utils.CodeUtils;
import com.hc360.koiambuyer.utils.FormTool;
import com.hc360.koiambuyer.utils.FragmentFactory;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.ContainerFooterActivity;
import com.hc360.koiambuyer.view.base.BaseFragment;
import com.hc360.koiambuyer.widget.SingleTextView;

import butterknife.BindView;
import butterknife.OnClick;




public class BindEmailFragment extends BaseFragment {

    @BindView(R.id.et_email_account)
    EditText mEtEmailAccount;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.iv_code)
    ImageView mIvCode;
    @BindView(R.id.stv)
    SingleTextView mStv;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_bind_email;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        Bundle args = getArguments();
        if (args != null) {
            String email = args.getString(Constant.EMAIL);
            mEtEmailAccount.setText(email);
            mEtEmailAccount.setSelection(email.length());
        }
        mIvCode.setImageBitmap(CodeUtils.getInstance().createBitmap());
        mStv.setText(getStr(R.string.get_identify));
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @OnClick({R.id.iv_code, R.id.stv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_code:
                mIvCode.setImageBitmap(CodeUtils.getInstance().createBitmap());
                break;
            case R.id.stv:
                if (TextUtils.isEmpty(mEtCode.getText().toString().trim())){
                    ToastUtil.showShort(mContext, getStr(R.string.input_identify));
                    return;
                }
                String email = mEtEmailAccount.getText().toString().trim();
                if (!FormTool.isEmail(email)) {
                    ToastUtil.showShort(mContext, getStr(R.string.email_form_error));
                    return;
                }
                if (CodeUtils.getInstance().getCode().equalsIgnoreCase(mEtCode.getText().toString().trim())){
                    ContainerFooterActivity activity = (ContainerFooterActivity) getActivity();
                    activity.setMsg(email);
                    FragmentFactory.replaceFragment(activity,R.id.fl_container, Constant.BIND_EMAIL_IDENTIFY);
                }else{
                    ToastUtil.showShort(mContext, getStr(R.string.pic_identify_error));
                }
                break;
        }
    }
}

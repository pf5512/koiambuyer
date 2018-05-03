package com.hc360.koiambuyer.view.me;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.AddressInfo;
import com.hc360.koiambuyer.engine.CustomTextWatcher;
import com.hc360.koiambuyer.engine.MaxNumTextWatcher;
import com.hc360.koiambuyer.engine.PhoneTextWatcher;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.model.States;
import com.hc360.koiambuyer.myinterface.DialogPositiveClickListener;
import com.hc360.koiambuyer.myinterface.KoAddressListener;
import com.hc360.koiambuyer.myinterface.ipresenter.IEditShipAddressPresenter;
import com.hc360.koiambuyer.myinterface.iview.IEditShipAddressView;
import com.hc360.koiambuyer.presenter.EditShipAddressPresenter;
import com.hc360.koiambuyer.utils.AddressUtils;
import com.hc360.koiambuyer.utils.DialogHelper;
import com.hc360.koiambuyer.utils.EmptyHelper;
import com.hc360.koiambuyer.utils.FormTool;
import com.hc360.koiambuyer.utils.TVDrawableUtil;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.MyApp;
import com.hc360.koiambuyer.view.base.BaseFragment;
import com.hc360.koiambuyer.widget.SingleTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;


/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/22
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class EditShipAddressFragment extends BaseFragment<IEditShipAddressPresenter> implements IEditShipAddressView {

    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.ll_address)
    LinearLayout mLlAddress;
    @BindView(R.id.et_address_detail)
    EditText mEtAddressDetail;
    @BindView(R.id.btn_default)
    TextView mBtnDefault;
    @BindView(R.id.stv)
    SingleTextView mStv;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.iv_name_delete)
    ImageView mIvNameDelete;
    @BindView(R.id.iv_phone_delete)
    ImageView mIvPhoneDelete;
    @BindView(R.id.fl_default)
    FrameLayout flDefault;
    @BindView(R.id.fl)
    FrameLayout fl;
    private String mMode;
    private String mProCode;
    private String mPName;
    private String mCName;
    private String mCityCode;
    private String mAreaCode;
    private int mDeliverId;
    private int mCompId = 0;
    private String mAddressDetail;
    private String mName;
    private String mPhone;
    private String mUseState = States.NOT_DEFAULT_ADDRESS;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_edit_ship_address;
    }

    @Override
    protected void initInjector() {
        mPresenter = new EditShipAddressPresenter(this);
    }

    @Override
    protected void initViews() {
        Bundle args = getArguments();
        if (args != null) {
            mMode = args.getString(Constant.MODE);
        }
        if (mMode.equals(Constant.EDIT_SHIP_ADDRESS)) {
            //编辑地址，先获取该地址
            int id = args.getInt(Msg.DELIVER_ID);
            mPresenter.getAddress(id);
        }
        mStv.setText(getStr(R.string.save));
        mEtPhone.addTextChangedListener(new PhoneTextWatcher(mEtPhone));
        mEtPhone.addTextChangedListener(new MaxNumTextWatcher(13, mEtPhone,false));
        mEtName.addTextChangedListener(new MaxNumTextWatcher(18, mEtName));
        mEtAddressDetail.addTextChangedListener(new MaxNumTextWatcher(80, mEtAddressDetail));
        mEtPhone.addTextChangedListener(new CustomTextWatcher(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                mIvPhoneDelete.setVisibility(TextUtils.isEmpty(s)?View.GONE:View.VISIBLE);
            }
        });
        mEtName.addTextChangedListener(new CustomTextWatcher(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                mIvNameDelete.setVisibility(TextUtils.isEmpty(s)?View.GONE:View.VISIBLE);
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void getAddress(AddressInfo addressInfo) {
        if (!TextUtils.isEmpty(addressInfo.content.telphone)) {
            mPhone = addressInfo.content.telphone;
            mEtPhone.setText(mPhone);
            mEtPhone.setSelection(mPhone.length());
        }
        if (!TextUtils.isEmpty(addressInfo.content.receiveUser)) {
            mName = addressInfo.content.receiveUser;
            mEtName.setText(mName);
            mEtName.setSelection(mName.length());
        }
        mProCode = addressInfo.content.provinceCode;
        mCityCode = addressInfo.content.cityCode;
        mPName = addressInfo.content.pName;
        mCName = addressInfo.content.cName;
        mTvAddress.setText(mPName + " " + mCName);
        mDeliverId = addressInfo.content.receiveId;
        mAddressDetail = addressInfo.content.addressDetail;
        if (!TextUtils.isEmpty(mAddressDetail)) {
            mEtAddressDetail.setText(mAddressDetail);
            mEtAddressDetail.setSelection(mAddressDetail.length());
        }
        mUseState = addressInfo.content.useState;
        if (mUseState.equals(States.DEFAULT_ADDRESS)) {
            TVDrawableUtil.setLeftNormalByRes(mContext, R.mipmap.select_s, mBtnDefault);
            mBtnDefault.setTextColor(mContext.getResources().getColor(R.color.mainColor));
        } else {
            TVDrawableUtil.setLeftNormalByRes(mContext, R.mipmap.not_select_s, mBtnDefault);
            mBtnDefault.setTextColor(mContext.getResources().getColor(R.color.tvNormalColor));
        }
    }

    @Override
    public void saveAddress(final int receiveId, final String addStr, final String userStr) {
        DialogHelper.showCustomNoTitleSureDialog(getActivity(), getStr(R.string.save_success), null, new DialogPositiveClickListener() {
            @Override
            public void positiveClick() {
                //写在这里带回去数据
                Intent intent = new Intent();
                //把返回数据存入Intent
                intent.putExtra(Msg.DELIVERID, receiveId);
                intent.putExtra(Msg.ADDSTR, addStr);
                intent.putExtra(Msg.USER_TELE, userStr);
                //设置返回数据
                getActivity().setResult(RESULT_OK, intent);
                getActivity().finish();
            }
        });
    }

    @OnClick({R.id.ll_address, R.id.fl_default, R.id.stv, R.id.btn_default,R.id.iv_name_delete, R.id.iv_phone_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_address:

                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                }
                AddressUtils.showKoPickerView(getActivity(), new KoAddressListener() {
                    @Override
                    public void getAddress(String pName, String cName, String proCode, String cityCode) {
                        mPName = pName;
                        mCName = cName;
                        mTvAddress.setText(pName + " " + cName);
                        mProCode = proCode;
                        mCityCode = cityCode;
                    }
                });
                break;
            case R.id.fl_default:
            case R.id.btn_default:
                if (mUseState.equals(States.DEFAULT_ADDRESS)) {
                    TVDrawableUtil.setLeftNormalByRes(mContext, R.mipmap.not_select_s, mBtnDefault);
                    mBtnDefault.setTextColor(mContext.getResources().getColor(R.color.tvNormalColor));
                    mUseState = States.NOT_DEFAULT_ADDRESS;
                } else {
                    TVDrawableUtil.setLeftNormalByRes(mContext, R.mipmap.select_s, mBtnDefault);
                    mBtnDefault.setTextColor(mContext.getResources().getColor(R.color.mainColor));
                    mUseState = States.DEFAULT_ADDRESS;
                }
                break;
            case R.id.stv:
                mAddressDetail = EmptyHelper.etEmptyToast(mEtAddressDetail, getStr(R.string.edit_ship_address_detail_et));
                mPhone = EmptyHelper.etEmptyToast(mEtPhone, getStr(R.string.edit_ship_address_phone));
                mName = EmptyHelper.etEmptyToast(mEtName, getStr(R.string.edit_ship_address_name));
                if (TextUtils.isEmpty(mAddressDetail)) return;
                if (TextUtils.isEmpty(mPhone)) return;
                if (TextUtils.isEmpty(mName)) return;

                if (mName.length() < 2) {
                    ToastUtil.showShort(mContext, getStr(R.string.name_msg_toast));
                    return;
                }
                String phone = "";
                phone = mPhone.replace(" ", "");
                if (FormTool.isPhone(phone)) {
                    if (mMode.equals(Constant.EDIT_SHIP_ADDRESS)) {
                        //编辑地址
                        mPresenter.saveAddress(mDeliverId, new Integer(MyApp.sUserId), mProCode, mCityCode, mAddressDetail, mName, mPhone, mUseState, mPName, mCName);
                    }
                    if (mMode.equals(Constant.NEW_SHIP_ADDRESS)) {
                        //新建地址
                        mPresenter.saveAddress(new Integer(MyApp.sUserId), mProCode, mCityCode, mAddressDetail, mName, mPhone, mUseState, mPName, mCName);
                    }
                } else {
                    ToastUtil.showShort(mContext, getStr(R.string.phone_error));
                }
                break;
            case R.id.iv_name_delete:
                mEtName.setText("");
                break;
            case R.id.iv_phone_delete:
                mEtPhone.setText("");
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

}

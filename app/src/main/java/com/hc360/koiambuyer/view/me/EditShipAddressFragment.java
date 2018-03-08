package com.hc360.koiambuyer.view.me;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.AddressInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.model.States;
import com.hc360.koiambuyer.myinterface.AddressListener;
import com.hc360.koiambuyer.myinterface.DialogPositiveClickListener;
import com.hc360.koiambuyer.myinterface.ipresenter.IEditShipAddressPresenter;
import com.hc360.koiambuyer.myinterface.iview.IEditShipAddressView;
import com.hc360.koiambuyer.presenter.EditShipAddressPresenter;
import com.hc360.koiambuyer.utils.AddressUtils;
import com.hc360.koiambuyer.utils.DialogHelper;
import com.hc360.koiambuyer.utils.EmptyHelper;
import com.hc360.koiambuyer.utils.TVDrawableUtil;
import com.hc360.koiambuyer.view.MyApp;
import com.hc360.koiambuyer.view.base.BaseFragment;
import com.hc360.koiambuyer.widget.SingleTextView;

import butterknife.BindView;
import butterknife.OnClick;



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
    Button mBtnDefault;
    @BindView(R.id.stv)
    SingleTextView mStv;
    private String mMode;
    private String mProCode;
    private String mCityCode;
    private String mAreaCode;
    private int mDeliverId;
    private int mCompId = new Integer(MyApp.sComId);
    private String mAddressDetail;
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
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void getAddress(AddressInfo addressInfo) {
        mProCode = addressInfo.content.provinceCode;
        mCityCode = addressInfo.content.cityCode;
        mAreaCode = addressInfo.content.areaCode;
        mTvAddress.setText(addressInfo.content.addStr);
        mDeliverId = addressInfo.content.deliverId;
        mCompId = addressInfo.content.compId;
        mAddressDetail = addressInfo.content.addressDetail;
        if (!TextUtils.isEmpty(mAddressDetail)){
            mEtAddressDetail.setText(mAddressDetail);
            mEtAddressDetail.setSelection(mAddressDetail.length());
        }
        mUseState = addressInfo.content.useState;
        if (mUseState.equals(States.DEFAULT_ADDRESS)){
            TVDrawableUtil.setLeftNormalByRes(mContext,R.mipmap.select_s,mBtnDefault);
            mBtnDefault.setTextColor(mContext.getResources().getColor(R.color.mainColor));
        }else{
            TVDrawableUtil.setLeftNormalByRes(mContext,R.mipmap.not_select_s,mBtnDefault);
            mBtnDefault.setTextColor(mContext.getResources().getColor(R.color.tvNormalColor));
        }
    }

    @Override
    public void saveAddress() {
        DialogHelper.showCustomNoTitleSureDialog(getActivity(), getStr(R.string.save_success), null, new DialogPositiveClickListener() {
            @Override
            public void positiveClick() {
                getActivity().finish();
            }
        });
    }


    @OnClick({R.id.ll_address, R.id.fl_default, R.id.stv,R.id.btn_default})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_address:
                AddressUtils.showPickerView(getActivity(),new AddressListener() {
                    @Override
                    public void getAddress(String msg, String proCode, String cityCode, String areaCode) {
                        mTvAddress.setText(msg);
                        mProCode = proCode;
                        mCityCode = cityCode;
                        mAreaCode = areaCode;
                    }
                });
                break;
            case R.id.fl_default:
            case R.id.btn_default:
                if (mUseState.equals(States.DEFAULT_ADDRESS)){
                    TVDrawableUtil.setLeftNormalByRes(mContext,R.mipmap.not_select_s,mBtnDefault);
                    mBtnDefault.setTextColor(mContext.getResources().getColor(R.color.tvNormalColor));
                    mUseState = States.NOT_DEFAULT_ADDRESS;
                }else{
                    TVDrawableUtil.setLeftNormalByRes(mContext,R.mipmap.select_s,mBtnDefault);
                    mBtnDefault.setTextColor(mContext.getResources().getColor(R.color.mainColor));
                    mUseState = States.DEFAULT_ADDRESS;
                }
                break;
            case R.id.stv:
                mAddressDetail = EmptyHelper.etEmptyToast(mEtAddressDetail, "请填写详细地址");
                if (TextUtils.isEmpty(mAddressDetail))return;
                if (mMode.equals(Constant.EDIT_SHIP_ADDRESS)) {
                    //编辑地址
                    mPresenter.saveAddress(mDeliverId,mCompId,mProCode,mCityCode,mAreaCode,mAddressDetail,mUseState);
                }
                if (mMode.equals(Constant.NEW_SHIP_ADDRESS)){
                    //新建地址
                    mPresenter.saveAddress(mCompId,mProCode,mCityCode,mAreaCode,mAddressDetail,mUseState);
                }
                break;
        }
    }
}

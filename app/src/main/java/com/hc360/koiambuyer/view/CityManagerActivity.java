package com.hc360.koiambuyer.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.adapter.CityManagerAdapter;
import com.hc360.koiambuyer.api.bean.CityManagerInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.myinterface.ipresenter.ICityManagerPresenter;
import com.hc360.koiambuyer.myinterface.iview.ICityManagerView;
import com.hc360.koiambuyer.presenter.CityManagerPresenter;
import com.hc360.koiambuyer.utils.SPUtils;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseXRvActivity;
import com.hc360.koiambuyer.view.home.PublishActivity;
import com.hc360.koiambuyer.widget.SingleTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CityManagerActivity extends BaseXRvActivity<ICityManagerPresenter, CityManagerAdapter, CityManagerInfo> implements ICityManagerView {

    @BindView(R.id.et_city_manager)
    EditText mEtCityManager;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    public String mManageId = "";
    public String mPhone = "";
    @BindView(R.id.stv)
    SingleTextView mStv;

    @Override
    protected void initView() {
        setEmptyMsg(getStr(R.string.city_manager_empty));
    }

    @Override
    protected void initInjector() {
        mPresenter = new CityManagerPresenter(this);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_city_manager;
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void getData() {
        mManageId = "";
        mPresenter.getCityManagers(mEtCityManager.getText().toString().trim(), mPager);
    }

    @Override
    public BaseAdapter newAdapter(CityManagerInfo cityManagerInfo) {
        return new CityManagerAdapter(R.layout.rv_city_manager, cityManagerInfo.list, this);
    }

    @Override
    public List getList(CityManagerInfo cityManagerInfo) {
        return cityManagerInfo.list;
    }

    @Override
    public void initRvLayoutManager() {
        getXRv().setLayoutManager(new GridLayoutManager(this, 2));
    }

    @OnClick({R.id.toolbar_right, R.id.iv_search, R.id.stv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_right:
                SPUtils.saveBoolean(this, Constant.SKIP_CITY_MANAGER, true);
                startActivity(new Intent(this, PublishActivity.class));
                finish();
                break;
            case R.id.iv_search:
                getData();
                break;
            case R.id.stv:
                if (!TextUtils.isEmpty(mManageId)){
                    try {
                        mPresenter.selectCityManager(Integer.parseInt(mManageId));
                    }catch (Exception e){

                    }
                }else {
                    ToastUtil.showShort(this,getStr(R.string.have_no_city_manager));
                }
                break;
        }
    }

    @Override
    public void getCityManger(CityManagerInfo info) {
        setAdapter(info);
    }

    @Override
    public void selectCityManger() {
        SPUtils.saveString(MyApp.getAppContext(),Constant.REFER_PHONE,mPhone);
        startActivity(new Intent(this, PublishActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}

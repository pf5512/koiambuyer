package com.hc360.koiambuyer.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.adapter.CityManagerAdapter;
import com.hc360.koiambuyer.api.bean.CityManagerInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.ICityManagerPresenter;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseXRvActivity;
import com.hc360.koiambuyer.view.home.HomeActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CityManagerActivity extends BaseXRvActivity<ICityManagerPresenter, CityManagerAdapter, CityManagerInfo> {

    @BindView(R.id.et_city_manager)
    EditText mEtCityManager;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;

    @Override
    protected void initView() {

    }

    @Override
    protected void initInjector() {

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

    }

    public void getData(CityManagerInfo cityManagerInfo) {
        setAdapter(cityManagerInfo);
    }

    @Override
    public BaseAdapter newAdapter(CityManagerInfo cityManagerInfo) {
        return new CityManagerAdapter(R.layout.rv_city_manager, cityManagerInfo.list);
    }

    @Override
    public List getList(CityManagerInfo cityManagerInfo) {
        return cityManagerInfo.list;
    }

    @Override
    public void initRvLayoutManager() {
        getXRv().setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.toolbar_right, R.id.iv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_right:
                startActivity(new Intent(this, HomeActivity.class));
                break;
            case R.id.iv_search:
                getData();
                break;
        }
    }
}

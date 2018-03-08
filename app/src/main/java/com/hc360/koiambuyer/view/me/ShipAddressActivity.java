package com.hc360.koiambuyer.view.me;

import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.adapter.ShipAddressXRvAdapter;
import com.hc360.koiambuyer.api.bean.ShipAddressInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.myinterface.ipresenter.IShipAddressPresenter;
import com.hc360.koiambuyer.myinterface.iview.IShipAddressView;
import com.hc360.koiambuyer.presenter.ShipAddressPresenter;
import com.hc360.koiambuyer.view.ContainerFooterActivity;
import com.hc360.koiambuyer.view.ContainerMainFooterActivity;
import com.hc360.koiambuyer.view.MyApp;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseXRvActivity;
import com.hc360.koiambuyer.widget.SingleTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 发货地址
 */
public class ShipAddressActivity extends BaseXRvActivity<IShipAddressPresenter,ShipAddressXRvAdapter,ShipAddressInfo> implements IShipAddressView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.stv_add_address)
    SingleTextView mStvAddAddress;

    @Override
    protected void initView() {
        initToolBar(mToolbar, null, true, getStr(R.string.ship_address));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void initInjector() {
        mPresenter = new ShipAddressPresenter(this);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_ship_address;
    }

    @OnClick(R.id.stv_add_address)
    public void onClick() {
        Intent openEditAddress = new Intent(ShipAddressActivity.this, ContainerFooterActivity.class);
        //新建地址，非编辑
        openEditAddress.putExtra(Constant.TYPE, Constant.NEW_SHIP_ADDRESS);
        startActivityForResult(openEditAddress, Constant.OPEN_EDIT_ADDRESS);
    }

    @Override
    public void getAddress(ShipAddressInfo info) {
        setAdapter(info);
    }

    @Override
    public void deleteAddress(int id) {
        mPresenter.deleteAddress(id);
    }

    @Override
    public void deleteAddress() {
        mPresenter.getAddresses(MyApp.sComId,true);
    }

    @Override
    public void setDefaultAddress(int id, int comId, String useState) {
        mPresenter.setDefaultAddress(id, comId, useState);
    }

    /**
     * 设置默认成功,请求服务器，刷新
     */
    @Override
    public void setDefaultAddress() {
        mPresenter.getAddresses(MyApp.sComId,true);
    }

    @Override
    public void editAddress(int id) {
        Intent openEditAddress = new Intent(ShipAddressActivity.this, ContainerMainFooterActivity.class);
        openEditAddress.putExtra(Constant.TYPE, Constant.EDIT_SHIP_ADDRESS);
        openEditAddress.putExtra(Msg.DELIVER_ID, id + "");
        startActivity(openEditAddress);
    }

    @Override
    public void selectAddress(String addStr, int deliverId) {
        //发布商品，选择发货地址
        boolean fromPublishGoods = getIntent().getBooleanExtra(Msg.FROM_PUBLISH_GOODS, false);
        if (fromPublishGoods) {
            Intent intent = new Intent();
            intent.putExtra(Msg.ADDSTR, addStr);
            intent.putExtra(Msg.DELIVERID, deliverId);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void getData() {
        mPresenter.getAddresses(MyApp.sComId,false);
    }

    @Override
    public BaseAdapter newAdapter(ShipAddressInfo shipAddressInfo) {
        return new ShipAddressXRvAdapter(R.layout.rv_add_address_item, shipAddressInfo.list, this);
    }

    @Override
    public List getList(ShipAddressInfo shipAddressInfo) {
        return shipAddressInfo.list;
    }
}

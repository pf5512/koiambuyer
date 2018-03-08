package com.hc360.koiambuyer.presenter;


import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.AddressInfo;
import com.hc360.koiambuyer.api.bean.ResponseInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.IEditShipAddressPresenter;
import com.hc360.koiambuyer.myinterface.iview.IEditShipAddressView;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/16
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class EditShipAddressPresenter implements IEditShipAddressPresenter {
    IEditShipAddressView mView;

    public EditShipAddressPresenter(IEditShipAddressView view) {
        this.mView = view;
    }

    @Override
    public void getAddress(int id) {
        RetrofitService.getAddress(id)
                .subscribe(new MyObserver<AddressInfo>() {
                    @Override
                    public void onNext(AddressInfo addressInfo) {
                        mView.getAddress(addressInfo);
                    }
                });
    }

    @Override
    public void saveAddress(int deliverId, int compId, String provinceCode, String cityCode, String areaCode, String addressDetail, String useState) {
        RetrofitService.editAddress(deliverId,compId,provinceCode,cityCode,areaCode,addressDetail,useState)
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        mView.saveAddress();
                    }
                });
    }

    @Override
    public void saveAddress(int compId, String provinceCode, String cityCode, String areaCode, String addressDetail, String useState) {
        RetrofitService.addAddress(compId,provinceCode,cityCode,areaCode,addressDetail,useState)
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        mView.saveAddress();
                    }
                });
    }


}

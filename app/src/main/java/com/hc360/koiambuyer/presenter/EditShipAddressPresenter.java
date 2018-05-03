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
    public void saveAddress(final int receiveId, int userId, String provinceCode, String cityCode, final String addressDetail, final String receiveUser, final String telphone, String useState, final String pName, final String cName) {
        RetrofitService.editAddress(receiveId,userId,provinceCode,cityCode,addressDetail,receiveUser,telphone,useState)
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        mView.saveAddress(receiveId,pName+"  "+ cName+"  "+addressDetail,receiveUser+"   "+telphone);
                    }
                });
    }

    @Override
    public void saveAddress(int userId, String provinceCode, String cityCode, final String addressDetail, final String receiveUser, final String telphone, String useState, final String pName, final String cName) {
        RetrofitService.addAddress(userId,provinceCode,cityCode,addressDetail,receiveUser,telphone,useState)
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        mView.saveAddress(getReceiveId(responseInfo.content),pName+"  "+ cName+"  "+addressDetail,receiveUser+"   "+telphone);
                    }
                });
    }

    private int getReceiveId(String content) {
        try {
            int i = Integer.parseInt(content);
            return i;
        }catch (Exception e){
            return -1;
        }
    }


}

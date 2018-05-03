package com.hc360.koiambuyer.presenter;

import android.graphics.Bitmap;

import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.PostPicInfo;
import com.hc360.koiambuyer.api.bean.ResponseInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.ISuggestionPresenter;
import com.hc360.koiambuyer.myinterface.iview.ISuggestionView;

import java.io.File;
import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/13
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class SuggestionPresenter implements ISuggestionPresenter {

    ISuggestionView mView;

    public SuggestionPresenter(ISuggestionView mView) {
        this.mView = mView;
    }


    @Override
    public void postPic(File file, final int position, final Bitmap bitmap) {
        RetrofitService.postPic(file)
                .subscribe(new MyObserver<PostPicInfo>() {
                    @Override
                    public void onNext(PostPicInfo postPicInfo) {
                        mView.postPic(postPicInfo,position,bitmap);
                    }
                });
    }

    @Override
    public void submit(String userId, String context, String phone, String feedType, List<String> imgs) {
        RetrofitService.submitSuggestion(userId,context,phone,feedType,imgs)
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        mView.submitSuccess();
                    }
                });
    }
}

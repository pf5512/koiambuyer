package com.hc360.koiambuyer.presenter;


import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.QuoteInfo;
import com.hc360.koiambuyer.api.bean.ResponseInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.IQuotePresenter;
import com.hc360.koiambuyer.myinterface.iview.IQuoteView;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/3
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class QuotePresenter implements IQuotePresenter {

    IQuoteView mView;

    public QuotePresenter(IQuoteView mView) {
        this.mView = mView;
    }

    @Override
    public void getQuote(String offerId) {
        RetrofitService.getQuote(offerId)
                .subscribe(new MyObserver<QuoteInfo>() {
                    @Override
                    public void onNext(QuoteInfo quoteInfo) {
                        mView.getGoods(quoteInfo);
                    }
                });

    }

    @Override
    public void setIntent(String offerId) {
        RetrofitService.setIntent(offerId)
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        mView.setIntent();
                    }
                });
    }
}

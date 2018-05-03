package com.hc360.koiambuyer.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.base.BaseFragment;
import com.hc360.koiambuyer.widget.CodeInput.CodeInput;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/2/28
 * Modify:  //TODO
 * Description: //测试
 * Copyright notice:
 */

public class PublishFragment extends BaseFragment {
    @BindView(R.id.code)
    CodeInput mCode;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_publish;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        mCode.setOnReadyListener(new CodeInput.OnReadyListener() {
            @Override
            public void onReady(String code) {
                ToastUtil.showShort(mContext,code);
                mCode.setErrorColor(R.color.buyerColor);
            }

            @Override
            public void onBeforeReady(String code) {

            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

}

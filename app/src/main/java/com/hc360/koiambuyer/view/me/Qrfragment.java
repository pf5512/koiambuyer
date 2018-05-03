package com.hc360.koiambuyer.view.me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.utils.GlideUtils;
import com.hc360.koiambuyer.view.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/27
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class QrFragment extends BaseFragment {
    @BindView(R.id.iv_qr)
    ImageView mIvQr;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_qr;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initToolBar(getStr(R.string.qr_title));
        Bundle args = getArguments();
        if (args != null) {
            String qr = args.getString(Msg.QR);
            GlideUtils.loadGood(mContext, qr, mIvQr);
        }
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

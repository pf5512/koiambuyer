package com.hc360.koiambuyer.view.good;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.view.base.BaseFragment;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/15
 * Modify:  //TODO
 * Description: //询盘页面
 * Copyright notice:
 */

public class EnquiryFragment extends BaseFragment {
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_enquiry;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initToolBar(getStr(R.string.enquiry_title),getStr(R.string.enquiry_right));
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void onTvRightClick() {

    }
}

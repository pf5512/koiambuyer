package com.hc360.koiambuyer.view.me;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.adapter.AttentionXRxAdapter;
import com.hc360.koiambuyer.api.bean.AttentionKoInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.IAttentionPresenter;
import com.hc360.koiambuyer.myinterface.iview.IAttentionView;
import com.hc360.koiambuyer.presenter.AttentionPresenter;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseXRvFragment;

import java.util.List;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/15
 * Modify:  //TODO
 * Description: //我的关注
 * Copyright notice:
 */

public class AttentionFragment extends BaseXRvFragment<IAttentionPresenter,AttentionXRxAdapter,AttentionKoInfo> implements IAttentionView {

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_attention;
    }

    @Override
    protected void initInjector() {
        mPresenter = new AttentionPresenter(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void getAttentions(AttentionKoInfo info) {
        setAdapter(info);
    }

    @Override
    public void notFollow() {
        mPager = 1;
        getData();
    }

    @Override
    public void getData() {
        mPresenter.getAttentions(mPager);
    }

    @Override
    public BaseAdapter newAdapter(AttentionKoInfo attentionKoInfo) {
        return new AttentionXRxAdapter(R.layout.rv_attention,attentionKoInfo.list,mPresenter);
    }

    @Override
    public List getList(AttentionKoInfo attentionKoInfo) {
        return attentionKoInfo.list;
    }
}

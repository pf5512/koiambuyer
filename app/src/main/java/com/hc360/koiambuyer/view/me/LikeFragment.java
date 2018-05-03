package com.hc360.koiambuyer.view.me;

import android.content.Intent;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.adapter.LikeXRxAdapter;
import com.hc360.koiambuyer.api.bean.LikeInfo;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.myinterface.ipresenter.ILikePresenter;
import com.hc360.koiambuyer.myinterface.iview.ILikeView;
import com.hc360.koiambuyer.presenter.LikePresenter;
import com.hc360.koiambuyer.utils.SPUtils;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseXRvFragment;
import com.hc360.koiambuyer.view.home.HomeActivity;

import java.util.List;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/15
 * Modify:  //TODO
 * Description: //我的喜欢
 * Copyright notice:
 */

public class LikeFragment extends BaseXRvFragment<ILikePresenter,LikeXRxAdapter,LikeInfo> implements ILikeView {

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_like;
    }

    @Override
    protected void initInjector() {
        mPresenter = new LikePresenter(this);
    }

    @Override
    protected void initViews() {
        initToolBar(getStr(R.string.keep_good_number),getStr(R.string.find_more));
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void getData() {
        mPresenter.getGoods(mPager);
    }

    @Override
    public BaseAdapter newAdapter(LikeInfo likeInfo) {
        return new LikeXRxAdapter(R.layout.rv_like,likeInfo.list);
    }

    @Override
    public List getList(LikeInfo likeInfo) {
        return likeInfo.list;
    }

    @Override
    public void getGoods(LikeInfo info) {
        setAdapter(info);
    }

    @Override
    public void onTvRightClick() {
        Intent openHome = new Intent(mContext, HomeActivity.class);
        SPUtils.saveBoolean(mContext,Msg.TO_FIND,true);
        startActivity(openHome);
    }
}

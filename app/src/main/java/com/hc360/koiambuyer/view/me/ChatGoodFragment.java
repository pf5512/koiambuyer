package com.hc360.koiambuyer.view.me;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.adapter.ChatGoodAdapter;
import com.hc360.koiambuyer.api.bean.ChatGoodInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.IChatGoodPresenter;
import com.hc360.koiambuyer.myinterface.iview.IChatGoodView;
import com.hc360.koiambuyer.presenter.ChatGoodPresenter;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseXRvFragment;

import java.util.List;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/4/8
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class ChatGoodFragment extends BaseXRvFragment<IChatGoodPresenter,ChatGoodAdapter,ChatGoodInfo> implements IChatGoodView{

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_chat_good;
    }

    @Override
    protected void initInjector() {
        mPresenter = new ChatGoodPresenter(this);
    }

    @Override
    protected void initViews() {
        initToolBar(getStr(R.string.chat_good));
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void getData() {
        mPresenter.getChatGood(mPager);
    }

    @Override
    public BaseAdapter newAdapter(ChatGoodInfo info) {
        return new ChatGoodAdapter(R.layout.rv_chat_good,info.list);
    }

    @Override
    public List getList(ChatGoodInfo info) {
        return info.list;
    }

    @Override
    public void getChatGood(ChatGoodInfo info) {
        setAdapter(info);
    }
}

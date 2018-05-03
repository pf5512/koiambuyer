package com.hc360.koiambuyer.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.adapter.ChatListAdapter;
import com.hc360.koiambuyer.api.bean.ChatListInfo;
import com.hc360.koiambuyer.api.bean.ChatMsgInfo;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.myinterface.ipresenter.IChatListPresenter;
import com.hc360.koiambuyer.myinterface.iview.IChatListView;
import com.hc360.koiambuyer.presenter.ChatListPresenter;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseXRvFragment;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/30
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class ChatListFragment extends BaseXRvFragment<IChatListPresenter, ChatListAdapter, ChatListInfo> implements IChatListView {

    @BindView(R.id.tv_contacts)
    TextView mTvContacts;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    private Subscription mSubscribeLoop;
    private Map<String,String> mMap;
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_chat_list;
    }

    @Override
    protected void initInjector() {
        mPresenter = new ChatListPresenter(this);
    }

    @Override
    protected void initViews() {
        Bundle args = getArguments();
        if (args != null) {
            args.getString(Msg.BACK);
            if (args.getString(Msg.BACK).equals(Msg.BACK)) {
                mIvBack.setVisibility(View.VISIBLE);
            }
        }
        setEmptyMsg(getStr(R.string.chat_list_empty));
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void initXRv() {
        getXRv().setPullRefreshEnabled(false);
        getXRv().setLoadingMoreEnabled(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        //延时3000 ，每间隔3000，时间单位
        if (mSubscribeLoop == null || mSubscribeLoop.isUnsubscribed()){
            mSubscribeLoop = Observable.interval(3000, 3000, TimeUnit.MILLISECONDS)
                    //延时3000 ，每间隔3000，时间单位
                    .compose(this.<Long>bindToLifecycle())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Long>() {
                        @Override
                        public void call(Long aLong) {
                            mPresenter.getChatMsg();
                        }
                    });
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mSubscribeLoop != null && !mSubscribeLoop.isUnsubscribed()) {
            mSubscribeLoop.unsubscribe();
        }
    }

    @Override
    public void getData() {
        mPresenter.getChatList();
    }

    @Override
    public BaseAdapter newAdapter(ChatListInfo chatListInfo) {
        return new ChatListAdapter(R.layout.rv_chat_list_item_new, chatListInfo.content);
    }

    @Override
    public List getList(ChatListInfo chatListInfo) {
        return chatListInfo.content;
    }

    @Override
    public void initNewAdapter() {
        isNewAdapter = true;
    }

    @Override
    public void getChatList(ChatListInfo info) {
        int noReadCount = 0;
        for (ChatListInfo.ContentBean bean : info.content) {
            if (bean.notReadNum > 0) {
                noReadCount++;
            }
        }
        mTvContacts.setText(getAppStr(R.string.contacts_left) + noReadCount + getAppStr(R.string.contacts_right));
        setAdapter(info);
    }

    @Override
    public void getChatMsg(ChatMsgInfo info) {
        Map<String,String> map = new HashMap<>();
        for (ChatMsgInfo.ContentBean bean : info.content) {
            map.put(String.valueOf(bean.userId),bean.chatContent);
        }
        if (mMap == null){
            mMap = map;
        }else {
            try {
                Logger.e(map.toString()+"==="+mMap.toString());
                for (String key : map.keySet()) {
                    if (!map.get(key).equals(mMap.get(key))){
                        mPresenter.getChatList();
                        mMap = map;
                        return;
                    }
                }
            }catch (Exception e){
                mPresenter.getChatList();
                mMap = map;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        getActivity().finish();
    }
}

package com.hc360.koiambuyer.presenter;

import android.text.Spannable;

import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.ChatListInfo;
import com.hc360.koiambuyer.api.bean.ChatMsgInfo;
import com.hc360.koiambuyer.engine.ChatListTimeComparator;
import com.hc360.koiambuyer.myinterface.ipresenter.IChatListPresenter;
import com.hc360.koiambuyer.myinterface.iview.IChatListView;
import com.hc360.koiambuyer.utils.ThreadUtil;
import com.hc360.koiambuyer.view.MyApp;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.easeui.utils.EaseSmileUtils;
import com.orhanobut.logger.Logger;

import java.util.Collections;
import java.util.List;

import rx.Observer;
import rx.functions.Action0;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/17
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class ChatListPresenter implements IChatListPresenter {

    IChatListView mView;

    public ChatListPresenter(IChatListView mView) {
        this.mView = mView;
    }

    @Override
    public void getChatList() {
        RetrofitService.getChatList()
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showLoading();
                    }
                })
                .subscribe(new Observer<ChatListInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showNetError();
                    }

                    @Override
                    public void onNext(final ChatListInfo info) {
                        final List<ChatListInfo.ContentBean> content = info.content;
                        if (content.size()>0){
                            ThreadUtil.toToOnSubThread(new Runnable() {
                                @Override
                                public void run() {
                                    for (ChatListInfo.ContentBean bean : content) {
                                        try {
                                            EMConversation conversation = EMClient.getInstance().chatManager().getConversation("iambuyer_" + bean.toUserId);
                                            if (conversation != null){
                                                bean.notReadNum = conversation.getUnreadMsgCount();
                                                List<EMMessage> messages = conversation.getAllMessages();
                                                if (messages.size()>1){
                                                    if (messages.get(messages.size() - 1).getMsgTime()>0){
                                                        bean.EndTime = messages.get(messages.size() - 1).getMsgTime();
                                                    }
                                                    EMTextMessageBody body = (EMTextMessageBody) messages.get(messages.size() - 1).getBody();
                                                    Spannable span = EaseSmileUtils.getSmiledText(MyApp.getAppContext(), body.getMessage());
                                                    bean.lastMsg = span.toString();
                                                }else {

                                                }
                                            }
                                            Logger.e("sssa"+bean.toString());
                                        } catch (Exception e) {
                                            Logger.e(e.toString());
                                            Thread.currentThread().interrupt();
                                        }
                                    }
                                    ThreadUtil.toToOnMainThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (content.size()>0){
                                                Collections.sort(content, new ChatListTimeComparator());
                                                info.content = content;
                                                mView.getChatList(info);
                                                mView.hideLoading();
                                            }else{
                                                mView.showNoData();
                                            }
                                        }
                                    });
                                }
                            });
                        }else {
                            mView.showNoData();
                        }
                    }
                });
    }

    @Override
    public void getChatMsg() {
        RetrofitService.getChatMsg(MyApp.sUserId)
                .subscribe(new MyObserver<ChatMsgInfo>() {
                    @Override
                    public void onNext(ChatMsgInfo info) {
                        mView.getChatMsg(info);
                    }
                });
    }


}

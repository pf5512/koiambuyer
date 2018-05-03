package com.hc360.koiambuyer.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.GoodsDetailInfo;
import com.hc360.koiambuyer.api.bean.RightNowInfo;
import com.hc360.koiambuyer.engine.AndroidBug5497Workaround;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.model.States;
import com.hc360.koiambuyer.myinterface.ipresenter.IChatPresenter;
import com.hc360.koiambuyer.myinterface.iview.IChatView;
import com.hc360.koiambuyer.presenter.ChatPresenter;
import com.hc360.koiambuyer.utils.SPUtils;
import com.hc360.koiambuyer.view.base.BaseActivity;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.exceptions.HyphenateException;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;


public class ChatActivity extends BaseActivity<IChatPresenter> implements IChatView {

    @BindView(R.id.fl_container)
    FrameLayout mContainer;
    @BindView(R.id.head)
    LinearLayout mSearchlayout;

    private ChatMsgFragment mChatFragment;
    public String mToChatId;
    public String mMeId;
    private int mProId;
    private int mType;
    private GoodsDetailInfo mGoodsDetailInfo;
    private int mToId;

    @Override
    protected void initView() {
        AndroidBug5497Workaround.assistActivity(this);
        boolean isRightNow = getIntent().getBooleanExtra(Msg.RIGHT_NOW, false);
        mToId = getIntent().getIntExtra(Msg.ID, -1);
        mToChatId = "iambuyer_" + mToId;
        mMeId = "iambuyer_" + MyApp.sUserId;
        if (isRightNow) {
            mProId = getIntent().getIntExtra(Msg.PRO_ID, -1);
            //type:1 代表采购   0代表供应
            mType = getIntent().getIntExtra(Msg.TYPE, -1);
            //获取供应的详细信息
            if (mType == Constant.LOOK_GOODS) {
                //用户上次发送这个商品的时间
                String lastTime = SPUtils.getString(this, mToChatId + "," + mProId, "");
                //如果一天之内给对方发过商品，就不再发送链接
                if (TextUtils.isEmpty(lastTime)){
                    mPresenter.getGoodsDetail(mProId, MyApp.sUserId, true);
                }else if (System.currentTimeMillis()-Long.parseLong(lastTime)>86400000){
                    mPresenter.getGoodsDetail(mProId, MyApp.sUserId, true);
                }
            }
            //调用这个接口，是让服务器保存一下
            mPresenter.rightNow(mProId, new Integer(MyApp.sUserId), mType);
        }
        //在这里登录环信，确保把别人挤下去
        mPresenter.loginIm(mMeId);
        String userName = getIntent().getStringExtra(Msg.USER_NAME);
        initToolBar(userName);
    }

    private void initChatView() {
        SPUtils.saveString(this,mToChatId+mProId,"0");
        //new出EaseChatFragment或其子类的实例
        mChatFragment = new ChatMsgFragment();
        mChatFragment.setActivity(this);
        //传入参数
        Bundle args = new Bundle();
        args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
        args.putString(EaseConstant.EXTRA_USER_ID, mToChatId);
        mChatFragment.setArguments(args);
        replaceFragment(R.id.fl_container, mChatFragment);
    }

    @Override
    protected void initInjector() {
        mPresenter = new ChatPresenter(this);
    }

    @Override
    protected int attachLayoutRes() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return R.layout.activity_chat;
    }

    @Override
    protected void updateViews(boolean isRefresh) {}


    @Override
    public void getGoodsDetail(GoodsDetailInfo info, boolean sendProUrl) {
        if (info.ret.equals(States.STATES_RESULT_OK)) {
            if (sendProUrl) {
                this.mGoodsDetailInfo = info;
            }
            sendPro(info, sendProUrl,false);
        }
    }

    @Override
    public void getRightNow(RightNowInfo info) {

    }

    @Override
    public void loginSuccess() {
        initChatView();
        try {
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(mToChatId);
        //指定会话消息未读数清零
        conversation.markAllMessagesAsRead();
        //所有未读消息数清零
        EMClient.getInstance().chatManager().markAllConversationsAsRead();
        }catch (Exception e){

        }
    }

    /**
     * 发产品
     */
    public void sendPro(boolean purUrl) {
        if (mGoodsDetailInfo != null) {
            sendPro(mGoodsDetailInfo, purUrl,false);
        }
    }

    /**
     * 发产品
     */
    public void sendPro(boolean purUrl, int proId) {
        if (mGoodsDetailInfo == null) {
            mPresenter.getGoodsDetail(proId, MyApp.sUserId, false);
        } else {
            sendPro(mGoodsDetailInfo, purUrl,false);
        }
    }

    /**
     * 发产品
     */
    public void sendPro(GoodsDetailInfo info, boolean purUrl,boolean dismiss) {
        EMMessage message = EMMessage.createTxtSendMessage(purUrl ? getString(R.string.pur_id) : getStr(R.string.pur), mToChatId);
        message.setChatType(EMMessage.ChatType.Chat);
        if (purUrl) {
            message.setAttribute("isProId", true);
            message.setAttribute("userHeadImg", "");
            message.setAttribute("userMsg", "");

            message.setAttribute("isDismiss", dismiss+","+mProId);
            message.setAttribute("companyMsg", "");

            String lastTime = System.currentTimeMillis() + "";
            SPUtils.saveString(this,mToChatId+mProId,lastTime);
            message.setAttribute("lastProIdTime", mProId+","+lastTime);
            Log.e("qqq",mProId+","+lastTime+"发送");
        } else {
            message.setAttribute("isPro", true);
            EMConversation conversation = EMClient.getInstance().chatManager().getConversation(mToChatId);
            List<EMMessage> messages = conversation.getAllMessages();
            for (EMMessage emMessage : messages) {
                try {
                    boolean isProId = emMessage.getBooleanAttribute("isProId");
                    if (isProId){
                        emMessage.setAttribute("isDismiss","true,"+mProId);
                        EMClient.getInstance().chatManager().updateMessage(emMessage);
                        mChatFragment.getMessageList().refresh();//刷新数据，否则发送时，无法接收到
                        mChatFragment.getMessageList().refreshSelectLast();
                    }
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        }
        message.setAttribute("proImg", info.content.spProduct.loopImg001);
        message.setAttribute("productName", info.content.spProduct.productName);
        message.setAttribute("proId", info.content.spProduct.productId);
        String price = "";
        BigDecimal minPice = info.content.minPice;
        if (minPice.compareTo(BigDecimal.ZERO)==1) {
            price = "￥" + minPice;
        } else {
            price = getStr(R.string.face);
        }
        message.setAttribute("price", price);

        EMClient.getInstance().chatManager().sendMessage(message);
        mChatFragment.getMessageList().refresh();//刷新数据，否则发送时，无法接收到
        mChatFragment.getMessageList().refreshSelectLast();
    }

    /**
     * 发产品
     */
    public void sendProFromId(String proImg,String productName,int proId,String price) {
        EMMessage message = EMMessage.createTxtSendMessage( getStr(R.string.pur), mToChatId);
        message.setChatType(EMMessage.ChatType.Chat);
        message.setAttribute("isPro", true);
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(mToChatId);
        List<EMMessage> messages = conversation.getAllMessages();
        for (EMMessage emMessage : messages) {
            try {
                boolean isProId = emMessage.getBooleanAttribute("isProId");
                if (isProId){
                    emMessage.setAttribute("isDismiss","true,"+proId);
                    EMClient.getInstance().chatManager().updateMessage(emMessage);
                    mChatFragment.getMessageList().refresh();//刷新数据，否则发送时，无法接收到
                    mChatFragment.getMessageList().refreshSelectLast();
                }
            } catch (HyphenateException e) {
                e.printStackTrace();
            }
        }
        message.setAttribute("proImg", proImg);
        message.setAttribute("productName", productName);
        message.setAttribute("proId", proId);
        message.setAttribute("price", price);
        EMClient.getInstance().chatManager().sendMessage(message);
        mChatFragment.getMessageList().refresh();//刷新数据，否则发送时，无法接收到
        mChatFragment.getMessageList().refreshSelectLast();
    }

    public void sendProveMsg() {
        EMMessage message_weiXin = EMMessage.createTxtSendMessage(getStr(R.string.prove_msg), mToChatId);
        message_weiXin.setChatType(EMMessage.ChatType.Chat);
        message_weiXin.setAttribute("isProveMsg", true);
        EMClient.getInstance().chatManager().sendMessage(message_weiXin);
        mChatFragment.getMessageList().refresh();//刷新数据，否则发送时，无法接收到
        mChatFragment.getMessageList().refreshSelectLast();
    }

}

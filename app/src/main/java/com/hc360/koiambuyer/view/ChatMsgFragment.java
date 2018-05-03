package com.hc360.koiambuyer.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.utils.IMUtils;
import com.hc360.koiambuyer.utils.ImageUtil;
import com.hc360.koiambuyer.utils.SPUtils;
import com.hc360.koiambuyer.utils.ThreadUtil;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.good.GoodsDetailActivity;
import com.hc360.koiambuyer.view.purchase.PurchaseDetailActivity;
import com.hyphenate.chat.EMImageMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.model.EaseImageCache;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.utils.EaseImageUtils;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.hyphenate.exceptions.HyphenateException;

import java.io.File;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;



/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/14
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class ChatMsgFragment extends EaseChatFragment implements EaseChatFragment.EaseChatFragmentHelper, EasyPermissions.PermissionCallbacks {

    ChatActivity mActivity;

    String[] perms = {Manifest.permission.CALL_PHONE};
    private String mUserPhone;

    @Override
    public void onCmdMessageReceived(List<EMMessage> messages) {
        for (EMMessage message : messages) {
            try {
                final String transparentMsg = message.getStringAttribute(Msg.TRANSPARENT_MSG);
                switch (transparentMsg){
                    case Msg.CHANGE_CARD:
                        //交换名片
                        ThreadUtil.toToOnMainThread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                        break;
                    case Msg.REFUSE_CHANGE_CARD:
                        ToastUtil.showShort(getActivity(),getContext().getResources().getString(R.string.refuse_card));
                        break;
                    case Msg.RECEIVE_CHANGE_CARD:

                        break;
                    case Msg.CHANGE_WX:
                        ThreadUtil.toToOnMainThread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                        break;
                    case Msg.RECEIVE_CHANGE_WX:

                        break;
                    case Msg.UPDATE_CHANGE_WX:
                        //更新对方微信，并发送

                        break;
                    case Msg.REFUSE_CHANGE_WX:
                        ToastUtil.showShort(getActivity(),getContext().getResources().getString(R.string.refuse_wx));
                        break;
                    case Msg.SEND_PUR:

                        break;
                    case Msg.SEND_PRO:
                        mActivity.sendPro(false);
                        break;
                }
            } catch (HyphenateException e) {
                e.printStackTrace();
            }
        }
    }

    public void setActivity(ChatActivity activity) {
        mActivity = activity;
        setChatFragmentListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSetMessageAttributes(EMMessage message) {
        message.setAttribute("timestamp",System.currentTimeMillis());
    }

    @Override
    public void onEnterToChatDetails() {

    }

    @Override
    public void onAvatarClick(String username) {

    }

    @Override
    public void onAvatarLongClick(String username) {

    }

    @Override
    public boolean onMessageBubbleClick(EMMessage message) {
        try {
            if (message.getBooleanAttribute("isWX",false)){
                String wxCode = message.getStringAttribute("wxCode");
                ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(wxCode);
                ToastUtil.showShort(getActivity(),getContext().getResources().getString(R.string.copy_success));
            }
            if (message.getBooleanAttribute("isPurId",false)){
                //点击采购项的发送链接按钮
                int purId = message.getIntAttribute("purId");
//                mActivity.sendPur(false,purId);
                IMUtils.sendTag(Msg.SEND_PUR,mActivity.mToChatId);
            }
            if (message.getBooleanAttribute("isProId",false)){
                int proId = message.getIntAttribute("proId");
                String to = message.getTo();
                //这里保存发送产品的时间
                SPUtils.saveString(getContext(),to+","+proId,System.currentTimeMillis()+"");
                mActivity.sendProFromId(message.getStringAttribute("proImg"),message.getStringAttribute("productName"),proId,message.getStringAttribute("price"));
                IMUtils.sendTag(Msg.SEND_PRO,mActivity.mToChatId);
            }
            if (message.getBooleanAttribute("isPro",false)){
                int proId = message.getIntAttribute("proId");
                Intent openGoodsDetail = new Intent(getActivity(), GoodsDetailActivity.class);
                openGoodsDetail.putExtra(Msg.PRODUCT_ID,proId);
                getActivity().startActivity(openGoodsDetail);
            }
            if (message.getBooleanAttribute("isPur",false)){
                int purListId = message.getIntAttribute("purListId");
                Intent openPurchaseDetail = new Intent(getActivity(), PurchaseDetailActivity.class);
                openPurchaseDetail.putExtra(Msg.ID,MyApp.sUserId);
                openPurchaseDetail.putExtra(Msg.PRODUCT_LIST_ID,purListId);
                getActivity().startActivity(openPurchaseDetail);
            }
            if (message.getBooleanAttribute("isCard",false)){
                mUserPhone = message.getStringAttribute("userPhone");
                if (EasyPermissions.hasPermissions(getActivity(), perms)) {
                    makeCall();
                } else {
                    EasyPermissions.requestPermissions(this, getContext().getResources().getString(R.string.call_perm_msg),
                            Constant.CALL_PHONE, perms);
                }
            }
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void makeCall() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + mUserPhone);
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    public void onMessageBubbleLongClick(EMMessage message) {
        try {
            if (message.getType().equals(EMMessage.Type.IMAGE)){
                EMImageMessageBody imgBody = (EMImageMessageBody) message.getBody();
                String thumbPath = imgBody.thumbnailLocalPath();
                if (!new File(thumbPath).exists()) {
                    // to make it compatible with thumbnail received in previous version
                    thumbPath = EaseImageUtils.getThumbnailImagePath(imgBody.getLocalUrl());
                }
                Bitmap bitmap = EaseImageCache.getInstance().get(thumbPath);
                ImageUtil.saveImage(getActivity(),bitmap,"111");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onExtendMenuItemClick(int itemId, View view) {
        return false;
    }

    @Override
    public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
        return null;
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (requestCode == Constant.CALL_PHONE) {
            makeCall();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == Constant.CALL_PHONE) {
            ToastUtil.showLong(getActivity(), getContext().getResources().getString(R.string.call_perm));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}

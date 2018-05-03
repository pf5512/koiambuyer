package com.hc360.koiambuyer.utils;


import com.hc360.koiambuyer.model.Msg;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.EMMessage;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/14
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class IMUtils {
    public static void sendChangeAsk(String msgType, String toChatId){
        EMMessage cmdMsg = EMMessage.createSendMessage(EMMessage.Type.CMD);
        EMCmdMessageBody cmdBody = new EMCmdMessageBody(Msg.ACTION);
        cmdMsg.setReceipt(toChatId);
        cmdMsg.setAttribute(Msg.TRANSPARENT_MSG, msgType);//将动态信息发送给对方
        cmdMsg.addBody(cmdBody);
        EMClient.getInstance().chatManager().sendMessage(cmdMsg);
    }

    public static void sendTag(String msgType, String toChatId){
        EMMessage cmdMsg = EMMessage.createSendMessage(EMMessage.Type.CMD);
        EMCmdMessageBody cmdBody = new EMCmdMessageBody(Msg.ACTION);
        cmdMsg.setReceipt(toChatId);
        cmdMsg.setAttribute(Msg.TRANSPARENT_MSG, msgType);//将动态信息发送给对方
        cmdMsg.addBody(cmdBody);
        EMClient.getInstance().chatManager().sendMessage(cmdMsg);
    }

    public static void sendChangeResponse(String msgType, String toChatId){
        EMMessage cmdMsg = EMMessage.createSendMessage(EMMessage.Type.CMD);
        EMCmdMessageBody cmdBody = new EMCmdMessageBody(Msg.ACTION);
        cmdMsg.setReceipt(toChatId);
        cmdMsg.setAttribute(Msg.TRANSPARENT_MSG, msgType);//将动态信息发送给对方
        cmdMsg.addBody(cmdBody);
        EMClient.getInstance().chatManager().sendMessage(cmdMsg);
    }
}

package com.hc360.koiambuyer.model;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/29
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class States {

    public static final String loginRetType = "loginRetType";
    public static final String STATES_RESULT_OK = "200";
    public static final String OK = "100";
    public static final String NEED_PWD = "200";
    public static final String NEED_PHONE_PWD = "300";
    public static final String ID = "_id";
    public static final String STATES_VERIFYING = "400";
    public static final String STATES_SUCCESS = "500";
    public static final String STATES_FAIL = "600";
    public static final String NO_CHECK_STATE = "-1";
    public static final String CHECK_WAIT = "0";// 0 待审
    public static final String CHECK_PASS = "1";// 1审核通过
    public static final String CHECK_REFUSE = "2";// 2 拒绝审核
    public static final String PRICE_MIN_MAX = "最小值比最大值大了";
    public static final String ON_LINE = "1";
    public static final String OUT_LINE = "0";
    //可定制
    public static final String MAKE_ABLE = "1";
    //不可定制
    public static final String UN_MAKE_ABLE = "0";
    //求购列表，	"listState":"0", // 0进行中
    public static final String LIST_STATE_ON = "0";
    //"isOver":"0" // 0 没过期  1过期的
    public static final String IS_OVER_NO = "0";
    public static final String IS_OVER_Yes = "1";
    public static final String KEEP_YES = "0";
    public static final String KEEP_NO = "1";

    public static final String PROVE_OK = "1";

    public static final String CHAT_CHANGE_SEND = "0";//是发过去了。对方没接收到
    public static final String CHAT_CHANGE_NO_RESPONSE = "1";//是发过去了， 对方接收到了。但不处理
    public static final String CHAT_CHANGE_REFUSE = "2";//我发过去了。对方接收到了 ， 拒绝了
    public static final String CHAT_CHANGE_AGREE = "3";//我发过去了，对方同意了

    public static final String TO_CHAT_STATE = "to_chat_state";
    public static final String TO_CHAT_HEAD = "to_chat_head";
    public static final String DEFAULT_ADDRESS = "0";
    public static final String NOT_DEFAULT_ADDRESS = "1";
    public static final String NO_COMPANY = "-1";

    public static final String READ = "1";
    public static final String STATE_PURCHASED = "0";
    public static final String STATE_MATCH = "1";
    public static final String STATE_CANCEL = "2";
    public static final String STATE_SUCCESS = "3";
    public static final String WHITE = "white";
}

package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/20
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class MsgInfo {

    /**
     * count : 15
     * list : [{"id":880,"states":"0","userId":365,"appVisual":"0","msgTitle":"采购翻译完成","msgContent":"编号 685 的采购信息已翻译完成，翻译员18610115025 ，翻译时间2018-03-07 14:24:00 ，快去查看吧~~~","msgType":"0","isPrompt":"0","isRead":"0","createName":"WQW","createTime":1520410495000},{"id":879,"states":"0","userId":365,"appVisual":"0","msgTitle":"发布采购","msgContent":"采购商 13681008117 于 2018-03-07 14:24:00 发布了一条新采购，翻译员正在翻译哦，请耐心等待~~~","msgType":"0","isPrompt":"0","isRead":"0","createName":"WQW","createTime":1520404076000},{"id":878,"states":"0","userId":365,"appVisual":"0","msgTitle":"시장조사 신천","msgContent":"구매자 13681008117 는 2018-03-07 14:24:00 시장조사를 신청했습니니다，번역 빨리 해주세요~~~","msgType":"0","isPrompt":"1","isRead":"1","createName":"WQW","createTime":1520404076000},{"id":877,"states":"0","userId":365,"appVisual":"0","msgTitle":"일괄 처리","msgContent":"대량 구매 처리 성공~~~","msgType":"0","isPrompt":"0","isRead":"0","createName":"WQW","createTime":1520404076000},{"id":876,"states":"0","userId":365,"appVisual":"0","msgTitle":"发布采购","msgContent":"采购商 13681008117 于 2018-03-07 14:24:00 发布了一条新采购，翻译员正在翻译哦，请耐心等待~~~","msgType":"0","isPrompt":"0","isRead":"0","createName":"WQW","createTime":1520403840000},{"id":875,"states":"0","userId":365,"appVisual":"0","msgTitle":"시장조사 신천","msgContent":"구매자 13681008117 는 2018-03-07 14:24:00 시장조사를 신청했습니니다，번역 빨리 해주세요~~~","msgType":"0","isPrompt":"1","isRead":"1","createName":"WQW","createTime":1520403840000},{"id":874,"states":"0","userId":365,"appVisual":"0","msgTitle":"发布采购","msgContent":"采购商 13681008117 于 2018-03-07 14:24:00 发布了一条新采购，翻译员正在翻译哦，请耐心等待~~~","msgType":"0","isPrompt":"0","isRead":"0","createName":"WQW","createTime":1520403840000},{"id":873,"states":"0","userId":365,"appVisual":"0","msgTitle":"일괄 처리","msgContent":"대량 구매 처리 성공~~~","msgType":"0","isPrompt":"0","isRead":"0","createName":"WQW","createTime":1520403840000},{"id":872,"states":"0","userId":365,"appVisual":"0","msgTitle":"시장조사 신천","msgContent":"구매자 13681008117 는 2018-03-07 14:24:00 시장조사를 신청했습니니다，번역 빨리 해주세요~~~","msgType":"0","isPrompt":"1","isRead":"1","createName":"WQW","createTime":1520403840000},{"id":871,"states":"0","userId":365,"appVisual":"0","msgTitle":"일괄 처리","msgContent":"대량 구매 처리 성공~~~","msgType":"0","isPrompt":"0","isRead":"0","createName":"WQW","createTime":1520403840000}]
     */

    public int count;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * id : 880
         * states : 0
         * userId : 365
         * appVisual : 0
         * msgTitle : 采购翻译完成
         * msgContent : 编号 685 的采购信息已翻译完成，翻译员18610115025 ，翻译时间2018-03-07 14:24:00 ，快去查看吧~~~
         * msgType : 0
         * isPrompt : 0
         * isRead : 0
         * createName : WQW
         * createTime : 1520410495000
         */

        public int id;
        public String states;
        public int userId;
        public String appVisual;
        public String msgTitle;
        public String msgContent;
        public String msgType;
        public String isPrompt;
        public String isRead;
        public String createName;
        public long createTime;
    }
}

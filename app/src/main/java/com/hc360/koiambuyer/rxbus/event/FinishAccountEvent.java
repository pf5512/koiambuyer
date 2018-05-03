package com.hc360.koiambuyer.rxbus.event;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/8
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class FinishAccountEvent {
    public boolean isAccountFinish;

    public FinishAccountEvent(boolean accountFinish) {
        this.isAccountFinish = accountFinish;
    }
}

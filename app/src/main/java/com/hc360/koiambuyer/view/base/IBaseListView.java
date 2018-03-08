package com.hc360.koiambuyer.view.base;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/25
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:I
 */

public interface IBaseListView<I> extends IBaseView{
    void getData();
    void setAdapter(I i);
    BaseQuickAdapter newAdapter(I i);
    List getList(I i);
}

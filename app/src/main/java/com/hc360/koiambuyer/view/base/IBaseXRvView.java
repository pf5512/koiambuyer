package com.hc360.koiambuyer.view.base;

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

public interface IBaseXRvView<I> extends IBaseView{
    void getData();
    void setAdapter(I i);
    BaseAdapter newAdapter(I i);
    List getList(I i);
}

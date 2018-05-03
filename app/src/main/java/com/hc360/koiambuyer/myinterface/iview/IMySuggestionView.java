package com.hc360.koiambuyer.myinterface.iview;

import com.hc360.koiambuyer.api.bean.MySuggestionInfo;
import com.hc360.koiambuyer.view.base.IBaseView;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/21
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface IMySuggestionView extends IBaseView{
    void getSuggestions(MySuggestionInfo info);
    void deleteMsg();
}

package com.hc360.koiambuyer.adapter;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.utils.GlideUtils;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseHolder;

import java.util.List;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/21
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class MySuggestionSecondAdapter extends BaseAdapter<String>{

    public MySuggestionSecondAdapter(int layoutId, List<String> list) {
        super(layoutId, list);
    }

    @Override
    protected void convert(BaseHolder holder, String bean) {
        GlideUtils.loadGood(mContext,bean,holder, R.id.iv_pic);
    }
}

package com.hc360.koiambuyer.adapter;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseHolder;

import java.util.List;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/2
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class AttentionPicAdapter extends BaseAdapter<String> {

    public AttentionPicAdapter(int layoutId, List<String> list) {
        super(layoutId, list);
    }

    @Override
    protected void convert(final BaseHolder holder, String s) {
        loadGood(s, R.id.iv_pic);
    }
}

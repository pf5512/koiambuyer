package com.hc360.koiambuyer.adapter;

import android.view.View;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.view.me.SuggestionFragment;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;


/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/19
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class SuggestionTagAdapter extends TagAdapter<String> {

    List<String> list;
    SuggestionFragment mFragment;
    public SuggestionTagAdapter(List datas, SuggestionFragment fragment) {
        super(datas);
        this.list = datas;
        mFragment = fragment;
    }

    public void setData(List<String> data) {
        this.list = data;
        notifyDataChanged();
    }

    @Override
    public View getView(FlowLayout parent, final int position,  String s) {
        TextView tv = (TextView) View.inflate(parent.getContext(), R.layout.tfl_suggestion, null);
        tv.setText(s);
        if (mFragment.mSelectPosition == position){
            tv.setTextColor(tv.getResources().getColor(R.color.mainColor));
            tv.setBackgroundResource(R.drawable.shape_tfl_rect_select);
        }else {
            tv.setTextColor(tv.getResources().getColor(R.color.tvNormalColor));
            tv.setBackgroundResource(R.drawable.shape_et_rect_bg);
        }
        return tv;
    }
}

package com.hc360.koiambuyer.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.MySuggestionInfo;
import com.hc360.koiambuyer.engine.FeedBackTypeEnum;
import com.hc360.koiambuyer.utils.TimeTool;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseHolder;
import com.hc360.koiambuyer.view.me.MySuggestionFragment;

import java.util.ArrayList;
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

public class MySuggestionAdapter extends BaseAdapter<MySuggestionInfo.ListBean> {
    MySuggestionFragment mFragment;
    public MySuggestionAdapter(int layoutId, List<MySuggestionInfo.ListBean> list, MySuggestionFragment fragment) {
        super(layoutId, list);
        mFragment = fragment;
    }

    @Override
    protected void convert(final BaseHolder holder, final MySuggestionInfo.ListBean bean) {
        final TextView tvContent = (TextView) holder.itemView.findViewById(R.id.tv_content);
        final TextView tvClose = (TextView) holder.itemView.findViewById(R.id.tv_close);
        if (!TextUtils.isEmpty(bean.context)){
            tvContent.setText(bean.context);
            if (bean.context.length()>90||bean.img001!=null){
                tvContent.setMaxLines(3);
                tvContent.setEllipsize(TextUtils.TruncateAt.END);
                tvClose.setVisibility(View.VISIBLE);
            }else {
                tvClose.setVisibility(View.GONE);
            }
        }

        holder.setText(R.id.tv_title, FeedBackTypeEnum.getEnum(bean.feedType)+"    "+TimeTool.getTimeDetail(bean.createTime));
        final boolean[] close = {true};
        final RecyclerView rvPic = (RecyclerView) holder.itemView.findViewById(R.id.rv_pic);

        final ImageView ivSelect = (ImageView) holder.itemView.findViewById(R.id.iv_select);
        if (mFragment.mSelectSuggestions.contains(bean.id+"")){
            ivSelect.setImageResource(R.mipmap.select_buyer);
        }else{
            ivSelect.setImageResource(mFragment.isEdit?R.mipmap.not_select_s:R.mipmap.not_select_b_20);
        }

        ivSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment.changeEditState(true);
                if (mFragment.mSelectSuggestions.contains(bean.id+"")){
                    mFragment.mSelectSuggestions.remove(bean.id+"");
                    ivSelect.setImageResource(R.mipmap.not_select_36);
                    if (mFragment.mSelectSuggestions.size() == 0){
                        mFragment.changeEditState(false);
                    }
                }else{
                    mFragment.mSelectSuggestions.add(bean.id+"");
                    ivSelect.setImageResource(R.mipmap.select_buyer);
                }
                notifyDataSetChanged();
            }
        });

        if (bean.img001!=null){
            holder.setVisibility(R.id.line,true);
            List<String> pics = new ArrayList<>();
            initList(pics,bean.img001,bean.img002,bean.img003,bean.img004,bean.img005);
            rvPic.setLayoutManager(new LinearLayoutManager(mContext));
            rvPic.setAdapter(new MySuggestionSecondAdapter(R.layout.rv_my_suggestion_second,pics));
        }else {
            holder.setVisibility(R.id.line,false);
        }
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (close[0]){
                    close[0] = false;
                    if (bean.img001!=null){
                        rvPic.setVisibility(View.VISIBLE);
                    }else {
                        if (bean.context.length()>90){
                            //展开,显示全部的字
                            tvContent.setMaxLines(10);
                            tvContent.setEllipsize(TextUtils.TruncateAt.END);
                        }
                    }
                    tvClose.setText(getStr(R.string.open));
                }else {
                    //收起，最多三行
                    if (bean.img001!=null){
                        rvPic.setVisibility(View.GONE);
                    }else {
                        if (bean.context.length()>90){
                            tvContent.setMaxLines(3);
                            tvContent.setEllipsize(TextUtils.TruncateAt.END);
                        }
                    }
                    tvClose.setText(getStr(R.string.close));
                    close[0] = true;
                }
            }
        });


    }
    public void initList(List<String> pics,String ... pic){
        for (String s : pic) {
            if (s!=null){
                pics.add(s);
            }
        }
    }
}

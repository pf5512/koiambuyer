package com.hc360.koiambuyer.adapter;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.AttentionKoInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.model.States;
import com.hc360.koiambuyer.myinterface.ipresenter.IAttentionPresenter;
import com.hc360.koiambuyer.view.ContainerActivity;
import com.hc360.koiambuyer.view.MyApp;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseHolder;

import java.util.ArrayList;
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

public class AttentionXRxAdapter extends BaseAdapter<AttentionKoInfo.ListBean> {
    IAttentionPresenter mPresenter;
    public AttentionXRxAdapter(int layoutId, List<AttentionKoInfo.ListBean> list, IAttentionPresenter presenter) {
        super(layoutId, list);
        mPresenter = presenter;
    }

    @Override
    protected void convert(final BaseHolder holder, final AttentionKoInfo.ListBean bean) {
        loadHead(bean.user.headImg, R.id.iv_head);
        holder.setText(R.id.tv_name,bean.user.userName);
        ImageView ivOnlyOne = (ImageView) findView(R.id.iv_only_one);

        RecyclerView rvImg = (RecyclerView) findView(R.id.rv_img);

        LinearLayout llThree = (LinearLayout) findView(R.id.ll_three);

        if (bean.imgList.size()>3){
            ivOnlyOne.setVisibility(View.VISIBLE);
            rvImg.setVisibility(View.VISIBLE);
            llThree.setVisibility(View.GONE);
            List<String> otherList = new ArrayList<>();
            for (int i = 0; i < bean.imgList.size(); i++) {
                if (i>0){
                    otherList.add(bean.imgList.get(i));
                }else {
                    loadGood(bean.imgList.get(i),R.id.iv_only_one);
                }
            }
            LinearLayoutManager manager = new LinearLayoutManager(mContext);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rvImg.setLayoutManager(manager);
            rvImg.setAdapter(new AttentionPicAdapter(R.layout.rv_rv_pic,otherList));

        }else if (bean.imgList.size()>1){
            ivOnlyOne.setVisibility(View.GONE);
            rvImg.setVisibility(View.GONE);
            llThree.setVisibility(View.VISIBLE);
            loadGood(bean.imgList.get(0),R.id.iv_first);
            loadGood(bean.imgList.get(1),R.id.iv_second);
            if (bean.imgList.size()>2){
                loadGood(bean.imgList.get(2),R.id.iv_third);
            }else {
                //给第三个增加默认图
            }
        }else {
            ivOnlyOne.setVisibility(View.VISIBLE);
            rvImg.setVisibility(View.GONE);
            llThree.setVisibility(View.GONE);
            loadGood(bean.imgList.get(0),R.id.iv_only_one);
        }

        findView(R.id.tv_attention).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.notFollow(new Integer(MyApp.sUserId),bean.user.ssoUserId);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openBuyer = new Intent(mContext, ContainerActivity.class);
                openBuyer.putExtra(States.WHITE,true);
                openBuyer.putExtra(Constant.TYPE, Constant.BUYER_DETAIL);
                openBuyer.putExtra(Constant.IS_CUSTOM, true);
                openBuyer.putExtra(Msg.BUYER_ID,bean.user.ssoUserId+"");
                mContext.startActivity(openBuyer);
            }
        });
    }
}

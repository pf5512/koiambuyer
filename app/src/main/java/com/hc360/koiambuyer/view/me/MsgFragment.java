package com.hc360.koiambuyer.view.me;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.adapter.MsgAdapter;
import com.hc360.koiambuyer.api.bean.MsgInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.IMsgPresenter;
import com.hc360.koiambuyer.myinterface.iview.IMsgView;
import com.hc360.koiambuyer.presenter.MsgPresenter;
import com.hc360.koiambuyer.utils.TVDrawableUtil;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseXRvFragment;
import com.hc360.koiambuyer.widget.SingleTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/20
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class MsgFragment extends BaseXRvFragment<IMsgPresenter, MsgAdapter, MsgInfo> implements IMsgView {

    @BindView(R.id.tv_all)
    TextView mTvAll;
    @BindView(R.id.stv_read)
    SingleTextView mStvRead;
    @BindView(R.id.stv_delete)
    SingleTextView mStvDelete;
    @BindView(R.id.card_edit)
    CardView mCardEdit;

    public boolean isEdit = false;
    public boolean isAll = false;
    public List<String> mSelectMessages;
    public List<String> mMessages;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_msg;
    }

    @Override
    protected void initInjector() {
        mPresenter = new MsgPresenter(this);
    }

    @Override
    protected void initViews() {
        mSelectMessages = new ArrayList<>();
        mMessages = new ArrayList<>();
        initToolBar(getStr(R.string.msg_title), getStr(R.string.edit));
        setItemSpace(5);
        setEmptyMsg(getStr(R.string.msg_empty));
    }

    @Override
    public void onTvRightClick() {
        changeEditState(!isEdit);
    }

    public void changeEditState(boolean edit) {
        isEdit = edit;
        setRightText(isEdit?getStr(R.string.done):getStr(R.string.edit));
        mCardEdit.setVisibility(isEdit? View.VISIBLE:View.GONE);
        if (!isEdit){
            mSelectMessages.clear();
        }
        notifyXRv();
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void getData() {
        mPresenter.getMsgList(mPager);
    }

    @Override
    public BaseAdapter newAdapter(MsgInfo msgInfo) {
        return new MsgAdapter(R.layout.rv_msg, msgInfo.list,this);
    }

    @Override
    public List getList(MsgInfo msgInfo) {
        return msgInfo.list;
    }

    @Override
    public void getMsgList(MsgInfo info) {
        if (mPager == 1){
            //刷新的操作，先清空之前的数据
            mMessages.clear();
        }
        for (MsgInfo.ListBean bean : info.list) {
            if (!mMessages.contains(bean.id+"")){
                mMessages.add(bean.id+"");
            }
        }
        setAdapter(info);
    }

    @Override
    public void setRead() {
        mPager = 1;
        getData();
        changeEditState(false);
        setAll(false);
    }

    @Override
    public void deleteMsg() {
        mPager = 1;
        getData();
        changeEditState(false);
        setAll(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.tv_all, R.id.stv_read ,R.id.stv_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_all:
                setAll(!isAll);
                break;
            case R.id.stv_read:
                if (mSelectMessages.size()>0){
                    mPresenter.setRead(formSelectMessages());
                }else {
                    ToastUtil.showShort(mContext,getStr(R.string.choose_mark_msg));
                }
                break;
            case R.id.stv_delete:
                if (mSelectMessages.size()>0){
                    mPresenter.deleteMsg(formSelectMessages());
                }else {
                    ToastUtil.showShort(mContext,getStr(R.string.choose_delete_msg));
                }
                break;
        }
    }

    private void setAll(boolean all) {
        isAll = all;
        if (isAll){
            for (String msg : mMessages) {
                if (!mSelectMessages.contains(msg)){
                    mSelectMessages.add(msg);
                }
            }
        }else {
            mSelectMessages.clear();
        }
        mTvAll.setText(isAll?getStr(R.string.all_no):getStr(R.string.all_choose));
        TVDrawableUtil.setLeftNormalByRes(mContext,isAll? R.mipmap.select_buyer:R.mipmap.not_select_s,mTvAll);
        notifyXRv();
    }

    private String formSelectMessages() {
        return mSelectMessages.toString().replace("[","").replace("]","");
    }
}

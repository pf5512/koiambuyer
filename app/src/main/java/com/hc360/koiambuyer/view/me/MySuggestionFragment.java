package com.hc360.koiambuyer.view.me;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.adapter.MySuggestionAdapter;
import com.hc360.koiambuyer.api.bean.MySuggestionInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.IMySuggestionPresenter;
import com.hc360.koiambuyer.myinterface.iview.IMySuggestionView;
import com.hc360.koiambuyer.presenter.MySuggestionPresenter;
import com.hc360.koiambuyer.utils.TVDrawableUtil;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.MyApp;
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
 * Date:    2018/3/14
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class MySuggestionFragment extends BaseXRvFragment<IMySuggestionPresenter, MySuggestionAdapter, MySuggestionInfo> implements IMySuggestionView {
    @BindView(R.id.tv_all)
    TextView mTvAll;
    @BindView(R.id.stv_delete)
    SingleTextView mStvDelete;
    public boolean isEdit = false;
    public boolean isAll = false;
    public List<String> mSelectSuggestions;
    public List<String> mSuggestions;
    @BindView(R.id.card_edit)
    CardView mCardEdit;
    private boolean isCanEdit = false;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_my_suggestion;
    }

    @Override
    protected void initInjector() {
        mPresenter = new MySuggestionPresenter(this);
    }

    @Override
    protected void initViews() {
        mSelectSuggestions = new ArrayList<>();
        mSuggestions = new ArrayList<>();
        initToolBar(getStr(R.string.my_suggestion), getStr(R.string.edit));
        setItemSpace(5);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void onTvRightClick() {
        if (isCanEdit){
            changeEditState(!isEdit);
        }else {
            ToastUtil.showShort(mContext,getStr(R.string.suggestion_msg));
        }
    }

    public void changeEditState(boolean edit) {
        isEdit = edit;
        setRightText(isEdit ? getStr(R.string.done) : getStr(R.string.edit));
        mCardEdit.setVisibility(isEdit? View.VISIBLE:View.GONE);
        if (!isEdit) {
            mSelectSuggestions.clear();
        }
        notifyXRv();
    }

    @Override
    public void getSuggestions(MySuggestionInfo info) {
        if (mPager == 1){
            //刷新的操作，先清空之前的数据
            mSuggestions.clear();
        }
        for (MySuggestionInfo.ListBean bean : info.list) {
            if (!mSuggestions.contains(bean.id+"")){
                mSuggestions.add(bean.id+"");
            }
        }
        setAdapter(info);
    }

    @Override
    public void deleteMsg() {
        mPager = 1;
        getData();
        changeEditState(false);
        setAll(false);
    }

    @Override
    public void getData() {
        mPresenter.getSuggestions(new Integer(MyApp.sUserId), mPager);
    }

    @Override
    public BaseAdapter newAdapter(MySuggestionInfo info) {
        return new MySuggestionAdapter(R.layout.rv_my_suggestion, info.list,this);
    }

    @Override
    public List getList(MySuggestionInfo info) {
        isCanEdit = info.count>0;
        return info.list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.tv_all, R.id.stv_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_all:
                setAll(!isAll);
                break;
            case R.id.stv_delete:
                if (mSelectSuggestions.size()>0){
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
            for (String msg : mSuggestions) {
                if (!mSelectSuggestions.contains(msg)){
                    mSelectSuggestions.add(msg);
                }
            }
        }else {
            mSelectSuggestions.clear();
        }
        mTvAll.setText(isAll?getStr(R.string.all_no):getStr(R.string.all_choose));
        TVDrawableUtil.setLeftNormalByRes(mContext,isAll? R.mipmap.select_buyer:R.mipmap.not_select_s,mTvAll);
        notifyXRv();
    }

    private String formSelectMessages() {
        return mSelectSuggestions.toString().replace("[","").replace("]","");
    }

}

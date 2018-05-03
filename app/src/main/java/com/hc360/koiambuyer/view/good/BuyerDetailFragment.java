package com.hc360.koiambuyer.view.good;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.adapter.BuyerDetailAdapter;
import com.hc360.koiambuyer.api.bean.InitInfo;
import com.hc360.koiambuyer.api.bean.SearchInfo;
import com.hc360.koiambuyer.engine.SpaceItemDecoration;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.myinterface.ipresenter.IBuyerDetailPresenter;
import com.hc360.koiambuyer.myinterface.iview.IBuyerDetailView;
import com.hc360.koiambuyer.presenter.BuyerDetailPresenter;
import com.hc360.koiambuyer.utils.DensityUtil;
import com.hc360.koiambuyer.utils.GlideUtils;
import com.hc360.koiambuyer.view.base.BaseFragment;
import com.hc360.koiambuyer.widget.SmartSecondScrollNestedView;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/4/4
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class BuyerDetailFragment extends BaseFragment<IBuyerDetailPresenter> implements IBuyerDetailView {

    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.tv_attention)
    TextView mTvAttention;
    @BindView(R.id.tv_title_top)
    TextView mTvTitleTop;
    @BindView(R.id.iv_head)
    ImageView mIvHead;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.tv_user_content)
    TextView mTvUserContent;
    @BindView(R.id.tv_rv_title)
    TextView mTvRvTitle;
    @BindView(R.id.scrollView)
    SmartSecondScrollNestedView mScrollView;
    private String mBuyerId;
    private int mPager = 1;
    private boolean isAttention =false;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_buyer_detail;
    }

    @Override
    protected void initInjector() {
        mPresenter = new BuyerDetailPresenter(this);
    }

    @Override
    protected void initViews() {
        Bundle args = getArguments();
        if (args != null) {
            mBuyerId = args.getString(Msg.BUYER_ID);
            mPresenter.getBuyerDetail(mBuyerId);
        }
        mTvTitleTop.setAlpha(0);
        mRv.setNestedScrollingEnabled(false);
        mPresenter.getGoods("",new Integer(mBuyerId), mPager);
        mScrollView.setOnScrollViewAtEndListener(new SmartSecondScrollNestedView.OnScrollViewAtEndListener() {
            @Override
            public void moreAction() {

            }

            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
                Logger.e("t="+t+";oldt"+oldt+";height="+mTvUserName.getTop());
                int top = mTvUserName.getTop();
                int height = t - mTvUserName.getTop();
                float i = 0;
                if (height>0){
                    i = (float) height/ mTvUserName.getHeight();
                    if (i > 1) {
                        i = 1;
                    }
                }
                mTvTitleTop.setAlpha(i);
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void getGoods(SearchInfo info) {
        mTvRvTitle.setText(getStr(R.string.hot_sell_buyer)+"("+info.list.size()+")");
        mRv.setLayoutManager(new LinearLayoutManager(mContext));
        mRv.addItemDecoration(new SpaceItemDecoration(DensityUtil.dp2px(mContext,30)));
        mRv.setAdapter(new BuyerDetailAdapter(R.layout.rv_buyer_detail, info.list));
    }

    @Override
    public void getBuyerDetail(InitInfo info) {
        if (info.content.isFollowUser.equals("0")){
            attentionBuyer();
        }else {
            noAttentionBuyer();
        }
        GlideUtils.loadHead(mContext,info.content.user.headImg,mIvHead);
        mTvTitleTop.setText(info.content.user.userName);
        mTvUserName.setText(info.content.user.userName);
        String userContent = info.content.user.userContent;
        if (!TextUtils.isEmpty(userContent)){
            mTvUserContent.setText(userContent);
        }
    }

    @Override
    public void attentionBuyer() {
        isAttention = true;
        mTvAttention.setText(getStr(R.string.cancel));
        mTvAttention.setBackgroundResource(R.drawable.ic_bg_gray_9);
        mTvAttention.setTextColor(getResources().getColor(R.color.minorColor));
    }

    @Override
    public void noAttentionBuyer() {
        isAttention = false;
        mTvAttention.setText(getStr(R.string.attention));
        mTvAttention.setBackgroundResource(R.drawable.ic_bg_yellow_9);
        mTvAttention.setTextColor(getResources().getColor(R.color.StvColor));
    }

    @OnClick({R.id.fl_back, R.id.fl_attention})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_back:
                getActivity().finish();
                break;
            case R.id.fl_attention:
                if (isAttention){
                    mPresenter.noAttentionBuyer(Integer.parseInt(mBuyerId));
                }else {
                    mPresenter.attentionBuyer(Integer.parseInt(mBuyerId));
                }
                break;
        }
    }
}

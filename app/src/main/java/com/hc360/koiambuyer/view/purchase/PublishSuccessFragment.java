package com.hc360.koiambuyer.view.purchase;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.adapter.FindXRxAdapter;
import com.hc360.koiambuyer.api.bean.SearchInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.myinterface.ipresenter.IPublishSuccessPresenter;
import com.hc360.koiambuyer.myinterface.iview.IPublishSuccessView;
import com.hc360.koiambuyer.presenter.PublishSuccessPresenter;
import com.hc360.koiambuyer.utils.DensityUtil;
import com.hc360.koiambuyer.view.ContainerFooterActivity;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseXRvFragment;
import com.hc360.koiambuyer.view.home.PublishActivity;
import com.hc360.koiambuyer.widget.SmartSecondScrollNestedView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/16
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class PublishSuccessFragment extends BaseXRvFragment<IPublishSuccessPresenter, FindXRxAdapter, SearchInfo> implements IPublishSuccessView {

    @BindView(R.id.tv_publish)
    TextView mTvPublish;
    @BindView(R.id.tv_watch_mine)
    TextView mTvWatchMine;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.scroll_view)
    SmartSecondScrollNestedView mScrollView;
    @BindView(R.id.fl_top)
    FrameLayout mFlTop;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_publish_success;
    }

    @Override
    protected void initInjector() {
        mPresenter = new PublishSuccessPresenter(this);
    }

    @Override
    protected void initViews() {
        setRvInScroll();
        mFlTop.setAlpha(0);
        mScrollView.setOnScrollViewAtEndListener(new SmartSecondScrollNestedView.OnScrollViewAtEndListener() {
            @Override
            public void moreAction() {
                mPager++;
                getData();
            }

            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
                float i = (float) t / DensityUtil.dp2px(mContext, 180);
                if (i > 1) {
                    i = 1;
                }
                mFlTop.setAlpha(i);
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void getData() {
        mPresenter.getGoods(mPager);
    }

    @Override
    public BaseAdapter newAdapter(SearchInfo searchInfo) {
        return new FindXRxAdapter(R.layout.rv_search_goods, searchInfo.list);
    }

    @Override
    public List getList(SearchInfo searchInfo) {
        return searchInfo.list;
    }

    @Override
    public void getGoods(SearchInfo info) {
        setAdapter(info);
    }

    @OnClick({R.id.tv_publish, R.id.tv_watch_mine, R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_publish:
                startActivity(new Intent(mContext, PublishActivity.class));
                getActivity().finish();
                break;
            case R.id.tv_watch_mine:
                Intent openMyPurchase = new Intent(mContext, ContainerFooterActivity.class);
                openMyPurchase.putExtra(Constant.TYPE,Constant.MY_PURCHASE);
                startActivity(openMyPurchase);
                getActivity().finish();
                break;
            case R.id.iv_back:
                getActivity().finish();
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

}

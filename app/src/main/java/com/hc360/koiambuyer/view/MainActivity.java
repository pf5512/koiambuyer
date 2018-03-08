package com.hc360.koiambuyer.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.adapter.SearchGoodsAdapter;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.SearchInfo;
import com.hc360.koiambuyer.view.base.BaseActivity;
import com.hc360.koiambuyer.widget.SmartScrollView;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

public class MainActivity extends BaseActivity {
    int mPager = 1;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.scrollview)
    SmartScrollView mScrollview;
    @BindView(R.id.iv_send)
    ImageView mIvSend;
    private SearchGoodsAdapter mAdapter;

    @Override
    protected void initView() {
        mScrollview.setOnScrollViewAtEndListener(new SmartScrollView.OnScrollViewAtEndListener() {
            @Override
            public void moreAction() {
                mPager++;
                RetrofitService.getSearch("", mPager)
                        .subscribe(new Observer<SearchInfo>() {
                            @Override
                            public void onCompleted() {
                                Logger.e("onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Logger.e(e.toString());
                            }

                            @Override
                            public void onNext(SearchInfo searchInfo) {
                                initData(searchInfo);
                            }

                        });
            }
        });
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mPager = 1;
        RetrofitService.getSearch("", mPager)
                .subscribe(new Observer<SearchInfo>() {
                    @Override
                    public void onCompleted() {
                        Logger.e("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.toString());
                    }

                    @Override
                    public void onNext(SearchInfo searchInfo) {
                        initData(searchInfo);
                    }

                });
    }

    private void initData(SearchInfo searchInfo) {
        Logger.e(searchInfo.list.size() + "");
        if (mPager == 1) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            mRv.setNestedScrollingEnabled(false);
            mRv.setLayoutManager(linearLayoutManager);
            mAdapter = new SearchGoodsAdapter(R.layout.rv_search_goods, searchInfo.list);
            mRv.setAdapter(mAdapter);
        } else {
//            mAdapter.notifyDataSetChanged();
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallBack(mAdapter.getData(), mAdapter.getData()), true);
            diffResult.dispatchUpdatesTo(mAdapter);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_send)
    public void onClick() {
        Intent data=new Intent(Intent.ACTION_SENDTO);
        data.setData(Uri.parse("mailto:455245521@qq.com"));
        startActivity(data);
    }

    class DiffCallBack extends DiffUtil.Callback {
        List<SearchInfo.ListBean> oldList;
        List<SearchInfo.ListBean> newList;

        public DiffCallBack(List<SearchInfo.ListBean> data, List<SearchInfo.ListBean> data1) {
            oldList = data;
            newList = data1;
        }

        @Override
        public int getOldListSize() {
            return oldList.size();
        }

        @Override
        public int getNewListSize() {
            return newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldList.get(oldItemPosition).getClass().equals(newList.get(newItemPosition).getClass());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
        }
    }
}

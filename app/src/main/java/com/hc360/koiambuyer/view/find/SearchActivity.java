package com.hc360.koiambuyer.view.find;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.adapter.ChoiceSellerAdapter;
import com.hc360.koiambuyer.adapter.SearchGoodsAdapter;
import com.hc360.koiambuyer.api.bean.ChoiceSellerInfo;
import com.hc360.koiambuyer.api.bean.HotInfo;
import com.hc360.koiambuyer.api.bean.SearchInfo;
import com.hc360.koiambuyer.engine.CustomTextWatcher;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.myinterface.ipresenter.ISearchPresenter;
import com.hc360.koiambuyer.myinterface.iview.ISearchView;
import com.hc360.koiambuyer.presenter.SearchPresenter;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.MyApp;
import com.hc360.koiambuyer.view.base.BaseActivity;
import com.hc360.koiambuyer.widget.AutoLoadRecyclerView;
import com.hc360.koiambuyer.widget.EmptyLayout;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class SearchActivity extends BaseActivity<ISearchPresenter> implements ISearchView {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.iv_delete)
    ImageView mIvDelete;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.tv_goods)
    TextView mTvGoods;
    @BindView(R.id.tv_company)
    TextView mTvCompany;
    @BindView(R.id.rv_list)
    AutoLoadRecyclerView mRv;
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.ll_result)
    LinearLayout mLlResult;
    @BindView(R.id.tfl_hot_goods)
    TagFlowLayout mTflHotGoods;
    @BindView(R.id.tfl_hot_company)
    TagFlowLayout mTflHotCompany;
    @BindView(R.id.ll_search)
    LinearLayout mLlSearch;
    @BindView(R.id.ll_bg)
    LinearLayout mLlBg;
    @BindView(R.id.head)
    LinearLayout head;
    @BindView(R.id.ll_search_head)
    LinearLayout mLlSearchHead;
    private PopupWindow mPopWindow;
    //默认是搜索商品
    private boolean isGoods = true;
    private boolean haveClickHot = false;
    private String mText;

    private ChoiceSellerAdapter mComAdapter;
    private SearchGoodsAdapter mGoodsAdapter;
    private List<String> mProKeyList;
    private List<String> mCompKeyList;

    @Override
    protected void initView() {
        mEmptyLayout.setVisibility(View.GONE);
        mIvDelete.setVisibility(View.GONE);
        initTab();
        mEtSearch.setFocusable(true);
        mEtSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mLlResult.setVisibility(hasFocus ? View.GONE : View.VISIBLE);
                mLlSearch.setVisibility(hasFocus ? View.VISIBLE : View.GONE);
            }
        });
        mLlSearchHead.setBackgroundResource(R.drawable.shape_search);

        mEtSearch.addTextChangedListener(new CustomTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mIvDelete.setVisibility(TextUtils.isEmpty(s) ? View.GONE : View.VISIBLE);
                if (TextUtils.isEmpty(s) && haveClickHot) {
                    //出现热门搜索
                    mLlSearch.setVisibility(View.VISIBLE);
                    mLlResult.setVisibility(View.GONE);
                    initTflData(mProKeyList, mCompKeyList);
                }
            }
        });
        mRv.setLoadMoreListener(new AutoLoadRecyclerView.loadMoreListener() {
            @Override
            public void onLoadMore() {
                mPager++;
                getData();
            }
        });
        mPresenter.getHot();
    }

    @Override
    protected void initInjector() {
        mPresenter = new SearchPresenter(this);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_search;
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        if (isRefresh) {
            getData();
        }
    }

    @Override
    public void getHot(HotInfo info) {
        mProKeyList = info.proKeyList;
        mCompKeyList = info.compKeyList;
        initTflData(mProKeyList, mCompKeyList);
    }


    @Override
    public void getCompany(ChoiceSellerInfo info) {
        setComAdapter(info);
    }

    @Override
    public void getSearchGoods(SearchInfo info) {
        setGoodsAdapter(info);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.iv_delete, R.id.tv_goods, R.id.tv_company, R.id.tv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_delete:
                mEtSearch.setText("");
                break;
            case R.id.tv_goods:
                isGoods = true;
                initTab();
                getData();
                break;
            case R.id.tv_company:
                isGoods = false;
                initTab();
                getData();
                break;
            case R.id.tv_search:
                mText = mEtSearch.getText().toString().trim();
                if (TextUtils.isEmpty(mText)) {
                    ToastUtil.showShort(this, getStr(R.string.search_toast));
                } else {
                    getData();
                }
                break;
        }
    }

    private void getData() {
        mLlSearch.setVisibility(View.GONE);
        mLlResult.setVisibility(View.VISIBLE);
        initTab();
        if (isGoods) {
            //搜索商品
            mPresenter.getSearchGoods(mText, mPager);
        } else {
            //搜索公司
            mPresenter.getCompany(mText, mPager);
        }
    }

    private void initTab() {
        mPager = 1;
        mTvGoods.setTextColor(getResources().getColor(isGoods ? (MyApp.sLoginType.equals(Constant.BUYER)?R.color.buyerColor:R.color.sellerColor) : R.color.minorSecondColor));
        mTvCompany.setTextColor(getResources().getColor(isGoods ? R.color.minorSecondColor : (MyApp.sLoginType.equals(Constant.BUYER)?R.color.buyerColor:R.color.sellerColor)));
    }

    private void initTflData(final List<String> proKeyList, final List<String> compKeyList) {
        final TagAdapter proAdapter = getTagAdapter(proKeyList, mTflHotGoods);
        final TagAdapter comAdapter = getTagAdapter(compKeyList, mTflHotCompany);
        mTflHotCompany.setAdapter(comAdapter);
        mTflHotGoods.setAdapter(proAdapter);
        mTflHotGoods.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {

            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                isGoods = true;
                mEtSearch.setText(proKeyList.get(position));
                mEtSearch.setSelection(proKeyList.get(position).length());
                comAdapter.setSelected(-1, null);
                comAdapter.notifyDataChanged();
                mText = proKeyList.get(position);
                haveClickHot = true;
                getData();
                return true;
            }
        });

        mTflHotCompany.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {

            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                isGoods = false;
                mEtSearch.setText(compKeyList.get(position));
                mEtSearch.setSelection(compKeyList.get(position).length());
                proAdapter.setSelected(-1, null);
                proAdapter.notifyDataChanged();
                mText = compKeyList.get(position);
                haveClickHot = true;
                getData();
                return true;
            }
        });
    }


    @NonNull
    private TagAdapter getTagAdapter(final List<String> list, final TagFlowLayout tfl) {
        return new TagAdapter(list) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv = (TextView) LayoutInflater.from(SearchActivity.this).inflate(R.layout.flowlayout_item, tfl, false);
                tv.setText(list.get(position));
                return tv;
            }
        };
    }


    public void setComAdapter(ChoiceSellerInfo info) {
        if (info.list.size() > 0) {
            hideLoading();
            if (mPager == 1) {
                initComAdapter(info);
            } else {
                mComAdapter.getData().addAll(info.list);
                mComAdapter.notifyDataSetChanged();
            }
            mSwipeRefresh.setRefreshing(false);
            mRv.setLoading(false);
        } else {
            mSwipeRefresh.setRefreshing(false);
            if (mPager == 1) {
                initComAdapter(info);
                showNoData();
            } else {
                hideLoading();
                ToastUtil.showShort(MyApp.getAppContext(), getStr(R.string.no_more_data));
            }
        }
    }

    public void setGoodsAdapter(SearchInfo info) {
        if (info.list.size() > 0) {
            hideLoading();
            if (mPager == 1) {
                initGoodsAdapter(info);
            } else {
                mGoodsAdapter.getData().addAll(info.list);
                mGoodsAdapter.notifyDataSetChanged();
            }
            mSwipeRefresh.setRefreshing(false);
            mRv.setLoading(false);
        } else {
            mSwipeRefresh.setRefreshing(false);
            if (mPager == 1) {
                initGoodsAdapter(info);
                showNoData();
            } else {
                hideLoading();
                ToastUtil.showShort(MyApp.getAppContext(),  getStr(R.string.no_more_data));
            }
        }
    }

    public void initGoodsAdapter(SearchInfo info) {
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mGoodsAdapter = new SearchGoodsAdapter(R.layout.rv_search_goods, info.list);
        mRv.setAdapter(mGoodsAdapter);
    }

    public void initComAdapter(ChoiceSellerInfo info) {
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mComAdapter = new ChoiceSellerAdapter(R.layout.rv_company, info.list);
        mRv.setAdapter(mComAdapter);
    }
}

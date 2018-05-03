package com.hc360.koiambuyer.view.good;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.adapter.GoodsDetailDownAdapter;
import com.hc360.koiambuyer.adapter.GoodsDetailMidAdapter;
import com.hc360.koiambuyer.adapter.PurchaseDetailParamAdapter;
import com.hc360.koiambuyer.adapter.ViewPagerAdapter;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.GoodsDetailInfo;
import com.hc360.koiambuyer.api.bean.PurchaseDetailInfo;
import com.hc360.koiambuyer.api.bean.ResponseInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.model.States;
import com.hc360.koiambuyer.myinterface.ipresenter.IGoodsDetailPresenter;
import com.hc360.koiambuyer.myinterface.iview.IGoodsDetailView;
import com.hc360.koiambuyer.presenter.GoodsDetailPresenter;
import com.hc360.koiambuyer.utils.DensityUtil;
import com.hc360.koiambuyer.utils.GlideUtils;
import com.hc360.koiambuyer.utils.PopUtils;
import com.hc360.koiambuyer.utils.TVDrawableUtil;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.ChatActivity;
import com.hc360.koiambuyer.view.ContainerActivity;
import com.hc360.koiambuyer.view.MyApp;
import com.hc360.koiambuyer.view.base.BaseActivity;
import com.hc360.koiambuyer.widget.SmartSecondScrollNestedView;
import com.hc360.koiambuyer.widget.StockDialog;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class GoodsDetailActivity extends BaseActivity<IGoodsDetailPresenter> implements IGoodsDetailView {

    @BindView(R.id.toolbar_keep)
    ImageView mIvKeep;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.tv_pager)
    TextView mTvPager;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_mid)
    RecyclerView mRvMid;
    LinearLayout mLlCount;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.head)
    public LinearLayout head;
    @BindView(R.id.ll_top)
    LinearLayout mRlTop;
    @BindView(R.id.scrollView)
    SmartSecondScrollNestedView mScrollView;
    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.tv_face)
    TextView mTvFace;
    @BindView(R.id.iv_top_back)
    ImageView mIvTopBack;
    @BindView(R.id.tv_top_title)
    TextView mTvTopTitle;
    @BindView(R.id.iv_top_keep)
    ImageView mIvTopKeep;
    @BindView(R.id.rv_down)
    RecyclerView mRvDown;
    @BindView(R.id.rv_format)
    RecyclerView mRvFormat;
    @BindView(R.id.tv_love)
    TextView mTvLove;

    @BindView(R.id.tv_like)
    TextView mTvLike;
    @BindView(R.id.tv_ask)
    TextView mTvAsk;
    @BindView(R.id.tv_stock)
    TextView mTvStock;
    @BindView(R.id.tv_sample_price)
    TextView mTvSamplePrice;
    @BindView(R.id.tv_sample_num)
    TextView mTvSampleNum;
    @BindView(R.id.tv_publish_id)
    TextView mTvPublishId;
    @BindView(R.id.tv_publish_num)
    TextView mTvPublishNum;
    @BindView(R.id.iv_user)
    ImageView mIvUser;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.tv_user_info)
    TextView mTvUserInfo;
    @BindView(R.id.tv_user_say)
    TextView mTvUserSay;
    @BindView(R.id.card_user_say)
    CardView mCardUserSay;
    @BindView(R.id.fl_stock)
    FrameLayout mFlStock;
    @BindView(R.id.iv_like)
    ImageView mIvLike;
    @BindView(R.id.tv_param_title)
    TextView mTvParamTitle;
    @BindView(R.id.tv_desc_title)
    TextView mTvDescTitle;
    @BindView(R.id.tv_good_desc)
    TextView mTvGoodDesc;

    private int mProductId;
    private boolean isAttention;
    private int mProUserId;
    private List<String> pics;
    private StockDialog mStockDialog;
    private boolean isLastStockSample;

    private GoodsDetailInfo mInfo;
    private int mFollowCount;
    private String mBuyerId;

    @Override
    protected void initView() {
        mRvMid.setVisibility(View.GONE);
        mRlTop.setBackgroundColor(getResources().getColor(R.color.buyerColor));
        mRlTop.setAlpha(0);
        mProductId = getIntent().getIntExtra(Msg.PRODUCT_ID, -1);
        if (RetrofitService.isDebug) {
            mProductId = 221;
        }
        Logger.e("商品：" + mProductId);

        mFlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pics = new ArrayList<>();

        mScrollView.setOnScrollViewAtEndListener(new SmartSecondScrollNestedView.OnScrollViewAtEndListener() {
            @Override
            public void moreAction() {

            }

            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
                float i = (float) t / DensityUtil.dp2px(GoodsDetailActivity.this, 180);
                if (i > 1) {
                    i = 1;
                }
                mRlTop.setAlpha(i);
                mFlBack.setAlpha(1 - i);
            }
        });
        TVDrawableUtil.setLeftByRes(this, R.mipmap.ic_goods_detail_sample_price, mTvSamplePrice);
        TVDrawableUtil.setLeftByRes(this, R.mipmap.ic_goods_detail_sample_num, mTvSampleNum);
        TVDrawableUtil.setLeftByRes(this, R.mipmap.ic_i_want_to_buy, mTvStock);
        TVDrawableUtil.setLeftByRes(this, R.mipmap.ic_like_bottom, mTvLike);

        TVDrawableUtil.setLeftRightByRes(this, R.mipmap.ic_good_title, mTvDescTitle);
        TVDrawableUtil.setLeftRightByRes(this, R.mipmap.ic_good_title, mTvParamTitle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getGoodsDetail(mProductId, MyApp.sUserId);
    }

    @Override
    protected void initInjector() {
        mPresenter = new GoodsDetailPresenter(this);
    }

    @Override
    public void setCusTheme() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_goods_detail;
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void getData(GoodsDetailInfo info) {
        mInfo = info;
        mProUserId = info.content.user.ssoUserId;
        final ArrayList<View> pagers = new ArrayList<>();
        pics.clear();
        initPics(info.content.spProduct.loopImg001, info.content.spProduct.loopImg002, info.content.spProduct.loopImg003, info.content.spProduct.loopImg004, info.content.spProduct.loopImg005);
        initViewPager(pics, pagers);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(pagers);
        if (pagers.size() > 0) {
            mTvPager.setText("1/" + pagers.size());
        }
        viewPagerAdapter.setOnViewPagerItemListener(new ViewPagerAdapter.OnViewPagerItemListener() {
            @Override
            public void onViewPagerItemClick(int position) {
                PopUtils.showOnlyPics(GoodsDetailActivity.this, head, pics, position);
            }
        });
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTvPager.setText((position + 1) + "/" + pagers.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        if (info.content.followPro == 0) {
            //关注过
            isAttention = true;
            TVDrawableUtil.setLeftByRes(this, R.mipmap.ic_attention, mTvLike);
            mIvLike.setImageResource(R.mipmap.ic_attention);
        } else {
            isAttention = false;
            TVDrawableUtil.setLeftByRes(this, R.mipmap.ic_like_bottom, mTvLike);
            mIvLike.setImageResource(R.mipmap.ic_like);
        }
        mFollowCount = info.content.followCount;
        mTvLove.setText(getStr(R.string.good_detail_like) + mFollowCount + ")");
        mTvLike.setText(mFollowCount + "");
        String productName = info.content.spProduct.productName;
        if (!TextUtils.isEmpty(productName)) {
            if (productName.length() > 40) {
                productName = productName.substring(0, 40) + "....";
            }
            mTvTitle.setText(productName);
        } else {
            mTvTitle.setText(getStr(R.string.have_no_title));
        }

        mTvFace.setVisibility(View.GONE);
        mRvMid.setVisibility(View.VISIBLE);
        List<GoodsDetailInfo.ContentBean.SpProductPiceBean> spProductPice = info.content.SpProductPice;
        mRvMid.setLayoutManager(new GridLayoutManager(this, 3));
        mRvMid.setAdapter(new GoodsDetailMidAdapter(R.layout.rv_goods_detail_mid_item, spProductPice, initStr(info.content.spProduct.productUnit)));

        mTvSamplePrice.setText(getStr(R.string.goods_detail_sample_price) + getStr(R.string.money_unit) + info.content.spProduct.productMoney);
        if (info.content.spProduct.productMaxcount > 1) {
            mTvSampleNum.setText(getStr(R.string.goods_detail_sample_num) + "1~" + info.content.spProduct.productMaxcount + initStr(info.content.spProduct.productUnit));
        } else {
            mTvSampleNum.setText(getStr(R.string.goods_detail_sample_num) + "1" + initStr(info.content.spProduct.productUnit));
        }
        LinearLayoutManager formatManager = new LinearLayoutManager(this);
        initRv(mRvFormat, formatManager);
//        mRvFormat.setLayoutManager(formatManager);
        List<PurchaseDetailInfo.StProductParasBean> formats = new ArrayList<>();
        formats.add(new PurchaseDetailInfo.StProductParasBean(getStr(R.string.quote_size), info.content.spProduct.productSize));
        formats.add(new PurchaseDetailInfo.StProductParasBean(getStr(R.string.quote_pack_size), info.content.spProduct.productPkgsize));
        formats.add(new PurchaseDetailInfo.StProductParasBean(getStr(R.string.quote_weight), info.content.spProduct.productWeight));
        formats.add(new PurchaseDetailInfo.StProductParasBean(getStr(R.string.quote_color), info.content.spProduct.productColor));
        formats.add(new PurchaseDetailInfo.StProductParasBean(getStr(R.string.quote_stock_num), info.content.spProduct.productTotal + initStr(info.content.spProduct.productUnit)));
        formats.add(new PurchaseDetailInfo.StProductParasBean(getStr(R.string.quote_make), info.content.spProduct.isMade.equals(States.MAKE_ABLE) ? getStr(R.string.yes) : getStr(R.string.no_second)));
        formats.add(new PurchaseDetailInfo.StProductParasBean(getStr(R.string.quote_send), info.content.spProduct.sendTime));
        mRvFormat.setAdapter(new PurchaseDetailParamAdapter(R.layout.rv_purchase_detail_param, formats));

        mTvPublishId.setText(getStr(R.string.good_detail_publish_id) + initStr(info.content.user.userName));
        if (!TextUtils.isEmpty(info.content.spProduct.productNum)) {
            mTvPublishNum.setText(getStr(R.string.good_detail_publish_num) + initStr(info.content.spProduct.productNum));
        }

        GlideUtils.loadHead(this, info.content.user.headImg, mIvUser);
        mBuyerId = info.content.user.ssoUserId + "";
        mTvUserName.setText(initStr(info.content.user.userName));
        mTvUserInfo.setText(initStr(info.content.user.userContent));
        if (TextUtils.isEmpty(info.content.spProduct.buyerContent)) {
            mCardUserSay.setVisibility(View.GONE);
        } else {
            mTvUserSay.setText("           " + initStr(info.content.spProduct.buyerContent));
        }

        //没有拿过样品
        if (info.content.isCreateOrder == 0) {
            //库存不足
            if (info.content.spProduct.productTotal < info.content.SpProductPice.get(0).minNumber) {
                if (info.content.spProduct.productTotal<1){
                    //显示我要购买，库存不足
                    mTvStock.setText(getStr(R.string.i_want_to_buy));
                }else {
                    //显示我要拿样,库存不足以直接购买
                    mTvStock.setText(getStr(R.string.good_detail_get_sample));
                }
            }
        }
        List<GoodsDetailInfo.ContentBean.SpProductImgSBean> spProductImgS = info.content.spProductImgS;
        LinearLayoutManager downManager = new LinearLayoutManager(this);
        initRv(mRvDown, downManager);
//        mRvDown.setLayoutManager(downManager);
        mRvDown.setAdapter(new GoodsDetailDownAdapter(R.layout.rv_goods_detail_down_item, spProductImgS, this));
        if (TextUtils.isEmpty(info.content.spProductIntro.productIntro)){
            mTvGoodDesc.setVisibility(View.GONE);
        }else {
            mTvGoodDesc.setVisibility(View.VISIBLE);
            mTvGoodDesc.setText(info.content.spProductIntro.productIntro);
        }

    }

    @Override
    public void attentionSuccess(ResponseInfo responseInfo) {
        if (responseInfo.ret.equals(States.STATES_RESULT_OK)) {
            //改变心的颜色，数量+1
            isAttention = true;
            TVDrawableUtil.setLeftByRes(this, R.mipmap.ic_attention, mTvLike);
            mIvLike.setImageResource(R.mipmap.ic_attention);
            mFollowCount = mFollowCount + 1;
            mTvLove.setText(getStr(R.string.good_detail_like) + mFollowCount + ")");
            mTvLike.setText(mFollowCount + "");
            ToastUtil.showShort(this, getStr(R.string.attention_success));
        } else if (responseInfo.msg.equals(getStr(R.string.attentioned))) {
            isAttention = true;
        }
    }

    @Override
    public void noAttentionSuccess() {
        isAttention = false;
        //改变心的颜色，数量-1
        TVDrawableUtil.setLeftByRes(this, R.mipmap.ic_like_bottom, mTvLike);
        mIvLike.setImageResource(R.mipmap.ic_like);
        ToastUtil.showShort(this, getStr(R.string.no_attention_success));
        mFollowCount = mFollowCount - 1;
        mTvLove.setText(getStr(R.string.good_detail_like) + mFollowCount + ")");
        mTvLike.setText(mFollowCount + "");
    }

    private void initViewPager(List<String> pics, ArrayList<View> pagers) {
        for (String pic : pics) {
            View picView = getNewView(pic);
            pagers.add(picView);
        }
    }

    private void initPics(String... picUrls) {
        for (String picUrl : picUrls) {
            if (!TextUtils.isEmpty(picUrl)) {
                pics.add(picUrl);
            }
        }
    }

    @NonNull
    private View getNewView(String imgUrl) {
        View item = LayoutInflater.from(GoodsDetailActivity.this).inflate(
                R.layout.viewpager_item, null);
        ImageView iv = (ImageView) item.findViewById(R.id.iv);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.good_default);
        requestOptions.error(R.mipmap.good_default);
        Glide.with(this) .setDefaultRequestOptions(requestOptions).load(imgUrl).into(iv);
        return item;
    }

    @OnClick({R.id.toolbar_keep, R.id.fl_back, R.id.fl_love, R.id.iv_top_keep, R.id.iv_top_back, R.id.tv_love, R.id.fl_like, R.id.tv_ask, R.id.fl_stock, R.id.iv_user, R.id.tv_user_name, R.id.rl_buyer})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_top_keep:
            case R.id.toolbar_keep:
                share();
                break;
            case R.id.iv_top_back:
            case R.id.fl_back:
                finish();
                break;
            case R.id.fl_like:
            case R.id.tv_love:
            case R.id.fl_love:
                if (isAttention) {
                    mPresenter.noAttentionGood(mProductId);
                } else {
                    mPresenter.attentionGood(mProUserId, mProductId);
                }
                break;
            case R.id.tv_ask:
//                Intent openEnquiry = new Intent(this, ContainerActivity.class);
//                openEnquiry.putExtra(Constant.TYPE,Constant.ENQUIRY);
//                startActivity(openEnquiry);

                Intent openChat = new Intent(this, ChatActivity.class);
                openChat.putExtra(Msg.RIGHT_NOW, true);
                openChat.putExtra(Msg.USER_NAME, mInfo.content.user.userName);
                openChat.putExtra(Msg.ID, mProUserId);
                openChat.putExtra(Msg.PRO_ID, mProductId);
                //从产品详情跳进去
                openChat.putExtra(Msg.TYPE, Constant.LOOK_GOODS);
                startActivity(openChat);
                break;
            case R.id.fl_stock:
                //如果已经拿过样品了，就不能再拿样品了，如果库存少于起批量，就不能再购买了
                if (mInfo != null) {
                    //库存充足
                    if (mInfo.content.spProduct.productTotal >= mInfo.content.SpProductPice.get(0).minNumber) {
                        //没有拿过样品
                        if (mInfo.content.isCreateOrder == 0) {
                            showPopUp(mFlStock);
                        } else {
                            //之前拿过样品，直接购买
                            getGood();
                        }
                    } else {
                        if (mInfo.content.isCreateOrder == 0) {
                            if (mInfo.content.spProduct.productTotal<1){
                                ToastUtil.showShort(this, getStr(R.string.under_stock));
                            }else {
                                getSample();
                            }
                        } else {
                            ToastUtil.showShort(this, getStr(R.string.under_stock));
                        }
                    }
                }

                break;
            case R.id.iv_user:
            case R.id.tv_user_name:
            case R.id.rl_buyer:
                //买手详情
                Intent openBuyer = new Intent(this, ContainerActivity.class);
                openBuyer.putExtra(States.WHITE, true);
                openBuyer.putExtra(Constant.TYPE, Constant.BUYER_DETAIL);
                openBuyer.putExtra(Constant.IS_CUSTOM, true);
                openBuyer.putExtra(Msg.BUYER_ID, mBuyerId);
                startActivity(openBuyer);
                Logger.e(mBuyerId + "");
                break;
        }
    }

    private void share() {

    }

    private void showPopUp(View v) {
        View inflate = View.inflate(this, R.layout.pop_show_stock, null);
        TextView tvGetSample = (TextView) inflate.findViewById(R.id.tv_get_sample);
        TextView tvGetGood = (TextView) inflate.findViewById(R.id.tv_get_good);
        int textLen = tvGetSample.getText().length();
        if (tvGetSample.getText().length() < tvGetGood.getText().length()) {
            textLen = tvGetGood.getText().length();
        }
        int text = textLen * DensityUtil.dp2px(this, 14) + 2 * DensityUtil.dp2px(this, 10);
        if (textLen < 5) {
            WindowManager wm = this.getWindowManager();
            int width = wm.getDefaultDisplay().getWidth();
            text = width / 3;
        }
        final PopupWindow popupWindow = new PopupWindow(inflate, text, DensityUtil.dp2px(this, 110));
        tvGetGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGood();
                popupWindow.dismiss();
            }
        });
        tvGetSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSample();
                popupWindow.dismiss();
            }
        });
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0], location[1] - popupWindow.getHeight());
    }

    private void getSample() {
        if (!isLastStockSample) {
            mStockDialog = new StockDialog(GoodsDetailActivity.this, mInfo, true);
        } else {
            if (mStockDialog == null) {
                mStockDialog = new StockDialog(GoodsDetailActivity.this, mInfo, true);
            }
        }
        isLastStockSample = true;
        mStockDialog.show();
    }

    private void getGood() {
        if (isLastStockSample) {
            mStockDialog = new StockDialog(GoodsDetailActivity.this, mInfo, false);
        } else {
            if (mStockDialog == null) {
                mStockDialog = new StockDialog(GoodsDetailActivity.this, mInfo, false);
            }
        }
        isLastStockSample = false;
        mStockDialog.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}

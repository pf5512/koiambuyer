package com.hc360.koiambuyer.view.home;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.adapter.FindHomeAdapter;
import com.hc360.koiambuyer.api.bean.ClassifyBean;
import com.hc360.koiambuyer.api.bean.FindInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.IFindPresenter;
import com.hc360.koiambuyer.myinterface.iview.IFindView;
import com.hc360.koiambuyer.presenter.FindPresenter;
import com.hc360.koiambuyer.utils.DBHelper;
import com.hc360.koiambuyer.utils.TVDrawableUtil;
import com.hc360.koiambuyer.utils.TagFlowLayoutUtils;
import com.hc360.koiambuyer.view.base.BaseAdapter;
import com.hc360.koiambuyer.view.base.BaseXRvFragment;
import com.hc360.koiambuyer.view.find.SearchActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/2/28
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class FindFragment extends BaseXRvFragment<IFindPresenter, FindHomeAdapter, FindInfo> implements IFindView {

    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.iv_select)
    ImageView mIvSelect;
    @BindView(R.id.fl_select)
    FrameLayout mFlSelect;
    @BindView(R.id.line)
    View mLine;
    @BindView(R.id.toolbar_right)
    TextView mTvRight;

    @BindView(R.id.gray_layout)
    View mGrayLayout;
    private PopupWindow mPopWindow;
    public final static int POP_HEIGHT = 960;
    private List<ClassifyBean> mTabList;
    private String mSelectText = "";

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initInjector() {
        mPresenter = new FindPresenter(this);
    }

    @Override
    protected void initViews() {
        if (mToolbar != null) {
            mToolbar.setBackgroundResource(R.color.mainColor);
        }
        mToolbar.setNavigationIcon(null);
        mToolbarTitle.setText(getStr(R.string.find_title));
        TVDrawableUtil.setRightByRes(mContext, R.mipmap.search, mTvRight);

        mTabList = new ArrayList<>();
        mTabList.add(new ClassifyBean(getStr(R.string.all), "", ""));
        List<ClassifyBean> classifyBeen = DBHelper.searchFirstClassify(mContext);
        mTabList.addAll(classifyBeen);
        for (int i = 0; i < mTabList.size(); i++) {
            mTab.addTab(mTab.newTab().setText(mTabList.get(i).cateName), i == 0);
        }

        //修正指示器的宽度
        TagFlowLayoutUtils.reflex(mTab);
        updateTabTextView(mTab.getTabAt(mTab.getSelectedTabPosition()), true);

        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mSelectText = tab.getText().toString();
                if (mSelectText.equals(getStr(R.string.all))) {
                    mSelectText = "";
                } else {
                    int position = tab.getPosition();
                    mSelectText = mTabList.get(position).cateId;
                }
                mPager = 1;
                getData();
                updateTabTextView(tab, true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                updateTabTextView(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
//                updateTabTextView(tab, false);
            }
        });
        setEmptyMsg(getStr(R.string.have_no_good));
    }

    private void updateTabTextView(TabLayout.Tab tab, boolean isSelect) {
        if (isSelect) {
            try {
                java.lang.reflect.Field fieldView= tab.getClass().getDeclaredField("mView");
                fieldView.setAccessible(true);
                View view= (View) fieldView.get(tab);
                java.lang.reflect.Field fieldTxt= view.getClass().getDeclaredField("mTextView");
                fieldTxt.setAccessible(true);
                TextView tabSelect= (TextView) fieldTxt.get(view);
//                tabSelect.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                TextPaint tp = tabSelect.getPaint();
                tp.setFakeBoldText(true);
                tabSelect.setText(tab.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                java.lang.reflect.Field fieldView= tab.getClass().getDeclaredField("mView");
                fieldView.setAccessible(true);
                View view= (View) fieldView.get(tab);
                java.lang.reflect.Field fieldTxt= view.getClass().getDeclaredField("mTextView");
                fieldTxt.setAccessible(true);
                TextView tabSelect= (TextView) fieldTxt.get(view);
//                tabSelect.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                TextPaint tp = tabSelect.getPaint();
                tp.setFakeBoldText(false);
                tabSelect.setText(tab.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.toolbar_right, R.id.fl_select})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_right:
                startActivity(new Intent(mContext, SearchActivity.class));
                break;
            case R.id.fl_select:
                showPopAsDown();
                mGrayLayout.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void showPopAsDown() {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.pop_supply_goods, null);
        mPopWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setHeight(POP_HEIGHT);
        TagFlowLayout tfl = (TagFlowLayout) contentView.findViewById(R.id.tfl_select);
        tfl.setAdapter(new TagAdapter<ClassifyBean>(mTabList) {
            @Override
            public View getView(FlowLayout parent, int position, ClassifyBean bean) {
                TextView tv = (TextView) View.inflate(mContext, R.layout.tfl_tv, null);
                tv.setText(bean.cateName);
                return tv;
            }
        });
        tfl.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                mTab.getTabAt(position).select();
                mPopWindow.dismiss();
                return true;
            }
        });
        mPopWindow.setContentView(contentView);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.showAsDropDown(mLine);
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mGrayLayout.setVisibility(View.GONE);
                mIvSelect.setImageResource(R.mipmap.down);
            }
        });
        mIvSelect.setImageResource(R.mipmap.up);
    }

    @Override
    public void getData() {
        mPresenter.getGoods(mSelectText, mPager);
    }

    @Override
    public BaseAdapter newAdapter(FindInfo searchInfo) {
        return new FindHomeAdapter(R.layout.rv_search_goods, searchInfo.list);
    }

    @Override
    public List getList(FindInfo searchInfo) {
        return searchInfo.list;
    }

    @Override
    public void getGoods(FindInfo info) {
        setAdapter(info);
    }


}

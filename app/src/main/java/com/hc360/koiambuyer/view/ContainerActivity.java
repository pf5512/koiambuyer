package com.hc360.koiambuyer.view;

import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.utils.FragmentFactory;
import com.hc360.koiambuyer.view.base.BaseActivity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ContainerActivity extends BaseActivity {
    private String mType;
    @Override
    protected void initView() {
        mType = getIntent().getStringExtra(Constant.TYPE);
        switch (mType) {
            case Constant.ENQUIRY:
            case Constant.LIKE:
            case Constant.MY_SUGGESTION:
            case Constant.MSG:
            case Constant.PUBLISH_SUCCESS:
            case Constant.CHAT_GOOD:
                FragmentFactory.replaceFragment(this, R.id.fl_container, mType);
                break;
            case Constant.QUOTE:
                String offerId = getIntent().getIntExtra(Msg.QUOTE_ID,-1)+"";
                boolean isCuo = getIntent().getBooleanExtra(Msg.IS_CUO, false);
                replaceFragment(R.id.fl_container, FragmentFactory.getNewFragmentByTag(mType, offerId,isCuo+""));
                break;
            case Constant.QR:
                String qr = getIntent().getStringExtra(Msg.QR);
                replaceFragment(R.id.fl_container, FragmentFactory.getNewFragmentByTag(mType, qr));
                break;
            case Constant.BUYER_DETAIL:
                String buyerId = getIntent().getStringExtra(Msg.BUYER_ID);
                replaceFragment(R.id.fl_container, FragmentFactory.getNewFragmentByTag(mType, buyerId));
                break;
            case Constant.CHAT_LIST:
                String back = getIntent().getStringExtra(Msg.BACK);
                replaceFragment(R.id.fl_container, FragmentFactory.getNewFragmentByTag(mType, back));
                break;
        }
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_container;
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            FragmentFactory.removeAllFragment(ContainerActivity.this);
            finish();
        }
        return false;
    }

    public void replaceFragment(String... msg) {
        replaceFragment(R.id.fl_container, FragmentFactory.getNewFragmentByTag(mType, msg));
    }

    public void replaceFragment(String title, String... msg) {
        replaceFragment(R.id.fl_container, FragmentFactory.getNewFragmentByTag(mType, msg));
        initToolBar(title);
    }

    public void replaceNewFragmentWithTitleAndMsg(String title, String... msg) {
        replaceFragment(R.id.fl_container, FragmentFactory.getNewFragmentByTag(mType, msg));
        initToolBar(title);
    }

    @Override
    public void setCusTheme() {
        if (getIntent().getBooleanExtra(Constant.IS_CUSTOM,false)){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(android.R.color.white));
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                Class<? extends Window> clazz = getWindow().getClass();
                try {
                    int darkModeFlag = 0;
                    Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                    Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                    darkModeFlag = field.getInt(layoutParams);
                    Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                    extraFlagField.invoke(getWindow(), darkModeFlag , darkModeFlag);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

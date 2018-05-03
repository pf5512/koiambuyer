package com.hc360.koiambuyer.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.view.ChangeFragment;
import com.hc360.koiambuyer.view.MyApp;
import com.hc360.koiambuyer.view.SettingPwdFragment;
import com.hc360.koiambuyer.view.base.BaseFragment;
import com.hc360.koiambuyer.view.good.BuyerDetailFragment;
import com.hc360.koiambuyer.view.good.EnquiryFragment;
import com.hc360.koiambuyer.view.home.ChatListFragment;
import com.hc360.koiambuyer.view.me.AttentionFragment;
import com.hc360.koiambuyer.view.me.ChatGoodFragment;
import com.hc360.koiambuyer.view.me.EditShipAddressFragment;
import com.hc360.koiambuyer.view.me.FirstStepFragment;
import com.hc360.koiambuyer.view.me.LikeFragment;
import com.hc360.koiambuyer.view.me.MsgFragment;
import com.hc360.koiambuyer.view.me.MyPurchaseFragment;
import com.hc360.koiambuyer.view.me.MySuggestionFragment;
import com.hc360.koiambuyer.view.me.OrderFragment;
import com.hc360.koiambuyer.view.me.QrFragment;
import com.hc360.koiambuyer.view.me.SecondStepFragment;
import com.hc360.koiambuyer.view.me.SuggestionFragment;
import com.hc360.koiambuyer.view.me.ThirdStepFragment;
import com.hc360.koiambuyer.view.purchase.PublishSuccessFragment;
import com.hc360.koiambuyer.view.purchase.QuoteFragment;
import com.hc360.koiambuyer.view.setting.BindEmailFragment;
import com.hc360.koiambuyer.view.setting.BindEmailIdentifyFragment;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

/**
 * Created by huang on 2017/2/24.
 */

public class FragmentFactory {
    private static BaseFragment getChangeFragment(String mode) {
        ChangeFragment changeFragment = new ChangeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.MODE, mode);
        changeFragment.setArguments(bundle);
        return changeFragment;
    }

    private static BaseFragment getFistStepFragment(String mode) {
        FirstStepFragment changeFragment = new FirstStepFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.MODE, mode);
        changeFragment.setArguments(bundle);
        return changeFragment;
    }

    /**
     * 根据tag获取Fragment
     *
     * @param tag
     * @return
     */
    public static BaseFragment getFragmentByTag(String tag) {
        switch (tag) {
            case Constant.CHANGE_PHONE:
                return getFistStepFragment(tag);
            case Constant.CHANGE_PWD_FIRST:
                return getFistStepFragment(tag);
            case Constant.FORGET_PWD:
                return getChangeFragment(tag);
            case Constant.BIND_EMAIL:
                return new BindEmailFragment();
            case Constant.BIND_EMAIL_IDENTIFY:
                return new BindEmailIdentifyFragment();
            case Constant.SUGGESTION:
                //可以删除
                return new SuggestionFragment();
            default:
                return null;
        }
    }



    /**
     * 根据tag每次创建新的Fragment
     *
     * @param tag
     * @return
     */
    public static <T> BaseFragment getNewFragmentByTag(String tag, T... t) {
        switch (tag) {
            case Constant.SUGGESTION:
                return new SuggestionFragment();
            case Constant.EDIT_SHIP_ADDRESS:
                return getEditShipAddressFragment(tag, t);
            case Constant.NEW_SHIP_ADDRESS:
                //这里要区分是新建还是编辑
                return getEditShipAddressFragment(tag);
//            case Constant.ABOUT_US:
//                return getAboutUsFragment(tag, t);
            case Constant.SETTING_PWD:
                //设置密码传手机号
                return getSettingPwdFragment(t);
            case Constant.CHANGE_PWD:
                return getSecondStepFragment(tag,t);
            case Constant.CHANGE_PWD_FORGET:
                return getSecondStepFragment(tag,t);
            case Constant.MY_SUGGESTION:
                return new MySuggestionFragment();
            case Constant.ENQUIRY:
                return new EnquiryFragment();
            case Constant.LIKE:
                return new LikeFragment();
            case Constant.MSG:
                return new MsgFragment();
            case Constant.PUBLISH_SUCCESS:
                return new PublishSuccessFragment();
            case Constant.QUOTE:
                return getQuoteFragment(t);
            case Constant.ORDER:
                return getOrderFragment(t);
            case Constant.BUYER_DETAIL:
                return getBuyerDetailFragment(t);
            case Constant.CHAT_GOOD:
                return new ChatGoodFragment();
            case Constant.CHAT_LIST:
                return getChatListFragment(t);
            case Constant.THIRD_STEP:
            case Constant.THIRD_STEP_CHANGE:
                return getThirdStepFragment(tag,t);
            case Constant.BIND_EMAIL:
                return getBindEmailFragment(tag,t);
//            case Constant.MY_ATTENTION:
//                return new AttentionFragment();
//            case Constant.UPDATE_PWD:
//                return new SettingPwdFragment();
//            case Constant.CHOOSE_STATES:
//                return new ChangeStatesFragment();
//            case Constant.CHOOSE_STATES_FIRST:
//                return getChooseStatesFragment(tag);
//            case Constant.BRAND:
//                return getSetNameFragment(tag);
//            case Constant.SET_NAME:
//                return getSetNameFragment(tag);
//            case Constant.FIND_COMPANY:
//                return new FindCompanyFragment();
//            case Constant.SUBMIT_APPLY:
//                return getSubmitApplyFragment(tag, t);
//            case Constant.MAIN_TRADE:
//                //主营行业
//                return new MainTradeFragment();
//            case Constant.MAIN_TRADE_NEW:
//                //主营行业
//                return new MainTradeNewFragment();
//            case Constant.SET_COMPANY_SHORT_NAME:
//                return getSetNameFragment(tag);
//            case Constant.COMPANY_ADDRESS:
//                return new CompanyAddressFragment();
//            case Constant.COMPANY_SELECT_ADDRESS:
//                return new CompanySelectAddressFragment();
//            case Constant.FILL_MSG:
//            case Constant.RE_FILL_MSG:
//                return getFillMsgFragment(tag);
//            case Constant.PROVE_STATES:
//                return getProveStatesFragment(t);
//            case Constant.PROVE_SECOND:
//                return new FillMsgSecondFragment();
//            case Constant.GOODS_CLASSIFY:
//                return new GoodsClassifyFragment();
//            case Constant.NEW_GOODS_CLASSIFY:
//                return new NewGoodsClassifyFragment();
//            case Constant.PRICE:
//                return new PriceFragment();
//            case Constant.BUY_INTENT:
//            case Constant.EDIT_BUYER_INTENT:
//                return getBuyIntentFragment(tag);
//            case Constant.GOODS_DESC:
//                return getGoodsDescFragment(tag);
//            case Constant.EDIT_GOODS_DESC:
//                return getGoodsDescFragment(tag, t);
//            case Constant.KEEP:
//                return getTalkOverFragment(tag);
//            case Constant.TALK_OVER:
//                return getTalkOverFragment(tag);
//            case Constant.SET_NAME_FROM_PERSONAL:
//                return getSetNameFragment(tag);
//            case Constant.SET_POSITION:
//                return getSetNameFragment(tag);
            case Constant.ATTENTION:
                return new AttentionFragment();
//            case Constant.COMPANY_BASIC_INFO:
//                return getCompanyBasicInfoFragment(tag, t);
//            case Constant.COMPANY_SHORT_NAME:
//                return getSetNameFragment(tag, t);
//            case Constant.SET_PASSWORD_FORGET:
//                return getSetPasswordFragment(tag, t);
//            case Constant.SET_PASSWORD_UPDATE:
//                return getSetPasswordFragment(tag);
//            case Constant.EDIT_PURCHASE:
//            case Constant.EDIT_PURCHASE_BEFORE:
//                return getEditPurchaseFragment(tag, (PurchaseDetailInfo.ContentBean.StProductsBean) t[0]);
//            case Constant.NEW_PURCHASE:
//                return getEditPurchaseFragment(tag);
            case Constant.MY_PURCHASE:
                return getMyPurchaseFragment(t);
            case Constant.QR:
                return getQrFragment(t);
//            case Constant.MY_SUB_PURCHASE:
//                return new MySubPurchaseFragment();
//            case Constant.CHOICE_SELLER:
//                return new ChoiceSellerFragment();
//            case Constant.WEB:
//                return getWebFragment(tag, t);
//            case Constant.GOODS_LIST:
//                return getGoodsListFragment(tag, t);
//            case Constant.SELLER_FRAGMENT:
//                return new SellerFragment();
//            case Constant.PURCHASE_LIST:
//                return getPurchaseListFragment(tag, t);
//            case Constant.NOTICE:
//                return new NoticeFragment();
            default:
                return null;
        }
    }

    private static <T> BaseFragment getThirdStepFragment(String tag,T ... t) {
        ThirdStepFragment fragment = new ThirdStepFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.MODE, tag);
        if (t.length>0) {
            bundle.putString(Msg.MSG, (String) t[0]);
        }
        fragment.setArguments(bundle);
        return fragment;
    }
    private static <T> BaseFragment getBindEmailFragment(String tag,T ... t) {
        BindEmailFragment fragment = new BindEmailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.MODE, tag);
        if (t.length>0) {
            bundle.putString(Msg.EMAIL, (String) t[0]);
        }
        fragment.setArguments(bundle);
        return fragment;
    }


//    private static <T> BaseFragment getMainTradeFragment(String tag, T... t) {
//        WebFragment fragment = new WebFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(Constant.MODE, tag);
//        bundle.putString(Msg.MAIN_TRADE_MSG, (String) t[0]);
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    private static BaseFragment getChooseStatesFragment(String tag) {
//        ChooseStatesFragment fragment = new ChooseStatesFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(Constant.MODE, tag);
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    private static BaseFragment getBuyIntentFragment(String tag) {
//        BuyIntentFragment fragment = new BuyIntentFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(Constant.MODE, tag);
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    private static <T> BaseFragment getPurchaseListFragment(String tag, T... t) {
//        PurchaseListFragment fragment = new PurchaseListFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(Constant.MODE, tag);
//        if (!TextUtils.isEmpty((String) t[0])) {
//            bundle.putString(Msg.CRUX_KEY, (String) t[0]);
//        }
//        if (!TextUtils.isEmpty((String) t[1])) {
//            bundle.putString(Msg.PURCHASE_TYPE, (String) t[1]);
//        }
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    private static <T> BaseFragment getWebFragment(String tag, T... t) {
//        WebFragment fragment = new WebFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(Constant.MODE, tag);
//        bundle.putString(Msg.WEB_URL, (String) t[0]);
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    private static <T> BaseFragment getGoodsListFragment(String tag, T... t) {
//        GoodsListFragment fragment = new GoodsListFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(Constant.MODE, tag);
//        bundle.putString(Msg.FIRST_CATE_ID, (String) t[0]);
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    private static BaseFragment getEditPurchaseFragment(String tag, PurchaseDetailInfo.ContentBean.StProductsBean... t) {
//        EditPurchaseFragment fragment = new EditPurchaseFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(Constant.MODE, tag);
//        if (tag.equals(Constant.EDIT_PURCHASE)|| tag.equals(Constant.EDIT_PURCHASE_BEFORE)) {
//            fragment.setData(t[0]);
//        }
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    private static <T> CompanyBasicInfoFragment getCompanyBasicInfoFragment(String tag, T... t) {
//        CompanyBasicInfoFragment fragment = new CompanyBasicInfoFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(Constant.MODE, tag);
//        bundle.putString(Msg.MSG, (String) t[0]);
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    private static <T> BaseFragment getSetPasswordFragment(String tag, T... t) {
//        SetPasswordFragment fragment = new SetPasswordFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(Constant.MODE, tag);
//        if (tag.equals(Constant.SET_PASSWORD_FORGET)) {
//            bundle.putString(Msg.PHONE, (String) t[0]);
//        }
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    private static BaseFragment getTalkOverFragment(String tag) {
//        TalkOverFragment fragment = new TalkOverFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(Constant.MODE, tag);
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    private static <T> GoodsDescFragment getGoodsDescFragment(String tag, T... t) {
//        GoodsDescFragment fragment = new GoodsDescFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(Constant.MODE, tag);
//        if (tag.equals(Constant.EDIT_GOODS_DESC)) {
//            if (t.length == 1) {
//                bundle.putString(Msg.PRODUCT_ID, (String) t[0]);
//            }
//        }
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    private static FillMsgFragment getFillMsgFragment(String tag) {
//        FillMsgFragment fragment = new FillMsgFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(Constant.MODE, tag);
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    private static SetNameFragment getSetNameFragment(String tag) {
//        SetNameFragment setNameFragment = new SetNameFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(Constant.MODE, tag);
//        setNameFragment.setArguments(bundle);
//        return setNameFragment;
//    }
//
//    private static <T> SetNameFragment getSetNameFragment(String tag, T... t) {
//        SetNameFragment setNameFragment = new SetNameFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(Constant.MODE, tag);
//        bundle.putString(Msg.MSG, (String) t[0]);
//        setNameFragment.setArguments(bundle);
//        return setNameFragment;
//    }


    public static String getFragmentTitle(String type) {
        switch (type) {
            case Constant.CHANGE_PHONE:
            return MyApp.getAppContext().getResources().getString(R.string.toolbar_edit_phone);
            case Constant.CHANGE_PWD:
            return MyApp.getAppContext().getResources().getString(R.string.toolbar_edit_pwd);
            case Constant.BIND_EMAIL:
            return MyApp.getAppContext().getResources().getString(R.string.toolbar_bind_email);
            case Constant.FORGET_PWD:
            return MyApp.getAppContext().getResources().getString(R.string.toolbar_forget_pwd);
            case Constant.SUGGESTION:
            return MyApp.getAppContext().getResources().getString(R.string.toolbar_suggestion);
            case Constant.EDIT_SHIP_ADDRESS:
            return MyApp.getAppContext().getResources().getString(R.string.toolbar_edit_address);
            case Constant.NEW_SHIP_ADDRESS:
            return MyApp.getAppContext().getResources().getString(R.string.toolbar_new_address);
            case Constant.ABOUT_US:
            return MyApp.getAppContext().getResources().getString(R.string.toolbar_about_us);
            case Constant.SETTING_PWD:
            return MyApp.getAppContext().getResources().getString(R.string.toolbar_set_pwd);
            case Constant.SET_NAME:
            return MyApp.getAppContext().getResources().getString(R.string.toolbar_set_name);
            case Constant.FIND_COMPANY:
            return MyApp.getAppContext().getResources().getString(R.string.toolbar_com_info);
            case Constant.MAIN_TRADE:
            return MyApp.getAppContext().getResources().getString(R.string.toolbar_main_trade);
        }
        return "";
    }

    /**
     * 移除fragment
     *
     * @param activity
     */
    public static void removeAllFragment(RxAppCompatActivity activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null && fragment.isVisible()) {
                    fragmentTransaction.remove(fragment);
                }
            }
        }
    }

    /**
     * 点击toolbar左边的图标，移除fragment，结束页面
     *
     * @param toolbar
     * @param activity
     */
    public static void finishWithFragments(Toolbar toolbar, final RxAppCompatActivity activity) {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentFactory.removeAllFragment(activity);
                activity.finish();
            }
        });
    }

    /**
     * 根据tag添加fragment
     *
     * @param activity
     * @param containerViewId
     * @param tag
     */
    public static void replaceFragment(RxAppCompatActivity activity, int containerViewId, String tag) {
        Fragment fragment = FragmentFactory.getFragmentByTag(tag);
        if (fragment == null) {
            fragment = FragmentFactory.getNewFragmentByTag(tag);
        }
        if (activity.getSupportFragmentManager().findFragmentByTag(tag) == null) {
            FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
            // 设置tag
            fragmentTransaction.replace(containerViewId, fragment, tag);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            // 这里要设置tag，上面也要设置tag
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();
        } else {
            // 存在则弹出在它上面的所有fragment，并显示对应fragment
            activity.getSupportFragmentManager().popBackStack(tag, 0);
        }
    }

    public static <T> BaseFragment getEditShipAddressFragment(String tag, T... msg) {
        EditShipAddressFragment editShipAddressFragment = new EditShipAddressFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.MODE, tag);
        if (tag.equals(Constant.EDIT_SHIP_ADDRESS)) {
            if (msg.length == 1) {
                String integer = (String) (msg[0]);
                bundle.putInt(Msg.DELIVER_ID, new Integer(integer));
            }
        }
        editShipAddressFragment.setArguments(bundle);
        return editShipAddressFragment;
    }
//
//    public static BaseFragment getAboutUsFragment(String tag, Object... msg) {
//        AboutUsFragment aboutUsFragment = new AboutUsFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(Constant.MODE, tag);
//        for (Object m : msg) {
//            bundle.putString(Msg.VERSION, (String) m);
//        }
//        aboutUsFragment.setArguments(bundle);
//        return aboutUsFragment;
//    }

    public static <T> BaseFragment getSettingPwdFragment(T... msg) {
        SettingPwdFragment settingPwdFragment = new SettingPwdFragment();
        Bundle bundle = new Bundle();
        for (Object m : msg) {
            bundle.putString(Msg.PHONE, (String) m);
        }
        settingPwdFragment.setArguments(bundle);
        return settingPwdFragment;
    }

    private static  <T> BaseFragment getSecondStepFragment(String mode, T... msg) {
        SecondStepFragment fragment = new SecondStepFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.MODE, mode);
        if (msg.length>0){
            for (Object m : msg) {
                bundle.putString(Msg.MSG, (String) m);
            }
        }
        fragment.setArguments(bundle);
        return fragment;
    }
    public static <T> BaseFragment getMyPurchaseFragment(T... msg) {
        MyPurchaseFragment settingPwdFragment = new MyPurchaseFragment();
        Bundle bundle = new Bundle();
        for (Object m : msg) {
            bundle.putString(Msg.STATE, (String) m);
        }
        settingPwdFragment.setArguments(bundle);
        return settingPwdFragment;
    }
    public static <T> BaseFragment getQrFragment(T... msg) {
        QrFragment settingPwdFragment = new QrFragment();
        Bundle bundle = new Bundle();
        for (Object m : msg) {
            bundle.putString(Msg.QR, (String) m);
        }
        settingPwdFragment.setArguments(bundle);
        return settingPwdFragment;
    }
    public static <T> BaseFragment getQuoteFragment(T... msg) {
        QuoteFragment settingPwdFragment = new QuoteFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Msg.QUOTE_ID, (String) msg[0]);
        if (msg.length>1){
            bundle.putString(Msg.IS_CUO, (String) msg[1]);
        }
        settingPwdFragment.setArguments(bundle);
        return settingPwdFragment;
    }

    public static <T> BaseFragment getOrderFragment(T... msg) {
        OrderFragment settingPwdFragment = new OrderFragment();
        Bundle bundle = new Bundle();
        for (Object m : msg) {
            bundle.putString(Msg.POSITION, (String) m);
        }
        settingPwdFragment.setArguments(bundle);
        return settingPwdFragment;
    }
    public static <T> BaseFragment getBuyerDetailFragment(T... msg) {
        BuyerDetailFragment fragment = new BuyerDetailFragment();
        Bundle bundle = new Bundle();
        for (Object m : msg) {
            bundle.putString(Msg.BUYER_ID, (String) m);
        }
        fragment.setArguments(bundle);
        return fragment;
    }
    public static <T> BaseFragment getChatListFragment(T... msg) {
        ChatListFragment fragment = new ChatListFragment();
        Bundle bundle = new Bundle();
        for (Object m : msg) {
            bundle.putString(Msg.BACK, (String) m);
        }
        fragment.setArguments(bundle);
        return fragment;
    }
//
//
//    private static <T> BaseFragment getSubmitApplyFragment(String tag, T... msg) {
//
//        SubmitApplyFragment submitApplyFragment = new SubmitApplyFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(Constant.MODE, tag);
//        if (msg.length == 2) {
//            bundle.putString(Msg.COMPANY, (String) msg[0]);
//            bundle.putString(Msg.MANAGER_PHONE, (String) msg[1]);
//        }
//        submitApplyFragment.setArguments(bundle);
//        return submitApplyFragment;
//    }
//
//    private static <T> BaseFragment getProveStatesFragment(T... msg) {
//        ProveStatesFragment fragment = new ProveStatesFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(Msg.COM_PROVE_STATES, (String) msg[0]);
//        if (msg.length == 2){
//            bundle.putString(Msg.MSG, (String) msg[1]);
//        }
//        fragment.setArguments(bundle);
//        return fragment;
//    }

}

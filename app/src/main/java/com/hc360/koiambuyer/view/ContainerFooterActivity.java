package com.hc360.koiambuyer.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.PurchaseDetailInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.utils.CameraUtil;
import com.hc360.koiambuyer.utils.FragmentFactory;
import com.hc360.koiambuyer.utils.ImageUtil;
import com.hc360.koiambuyer.utils.PhotoUtils;
import com.hc360.koiambuyer.utils.SPUtils;
import com.hc360.koiambuyer.view.base.BaseActivity;
import com.hc360.koiambuyer.view.base.BaseFragment;
import com.hc360.koiambuyer.view.home.HomeActivity;
import com.hc360.koiambuyer.view.me.SuggestionFragment;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContainerFooterActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.line)
    View mLine;
    @BindView(R.id.toolbar_right)
    TextView mTvRight;
    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.head)
    LinearLayout head;
    @BindView(R.id.view)
    public View mHead;
    private String mType;

    private String msg;
    public ArrayList<String> mCateIds;

    public File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    public File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/"+ System.currentTimeMillis()+"crop_photo.jpg");
    public Uri imageUri;
    public Uri cropImageUri;

    @Override
    protected void initView() {
        mToolbar.measure(0,0);
        int height = mToolbar.getMeasuredHeight();
        SPUtils.saveInt(this,Constant.TOOLBAR_HEIGHT,height);
        mType = getIntent().getStringExtra(Constant.TYPE);
        switch (mType) {
            case Constant.CHANGE_PHONE:
                //修改手机号
                replaceFragmentWithTitle(getStr(R.string.toolbar_edit_phone));
                break;
            case Constant.CHANGE_PWD:
                //更换密码
                replaceFragmentWithTitle(getStr(R.string.toolbar_edit_pwd));
                break;
            case Constant.FORGET_PWD:
                //忘记密码
                replaceFragmentWithTitle(getStr(R.string.toolbar_forget_pwd));
                break;
            case Constant.UPDATE_PWD:
                //更新密码，使用验证码登录，服务器返回需要不全密码,与注册设置密码一个界面，只是请求不同接口
                replaceFragmentWithTitle(getStr(R.string.toolbar_update_pwd));
                break;
            case Constant.BIND_EMAIL:
                //绑定邮箱
                replaceFragmentWithTitle(getStr(R.string.toolbar_bind_email));
                break;
            case Constant.SUGGESTION:
                //意见和反馈
                replaceFragmentWithTitle(getStr(R.string.toolbar_suggestion));
                break;
            case Constant.EDIT_SHIP_ADDRESS:
                //编辑地址
                String deliverId = getIntent().getStringExtra(Msg.DELIVER_ID);
                replaceNewFragmentWithTitleAndMsg(getStr(R.string.toolbar_edit_address), deliverId);
                break;
            case Constant.SET_NAME:
                //完善信息 -- 修改姓名
                replaceFragmentWithTitle(getStr(R.string.toolbar_set_name), getStr(R.string.save));
                break;
            case Constant.SET_COMPANY_SHORT_NAME:
                //创建新公司 -- 公司/店铺简称
                replaceFragmentWithTitle("公司/店铺简称", getStr(R.string.save));
                break;
            case Constant.COMPANY_SHORT_NAME:
                //公司信息 -- 公司/店铺简称
                String companyShortName = getIntent().getStringExtra(Msg.COMPANY_SHORT_NAME);
                replaceFragment("公司/店铺简称", getStr(R.string.save), companyShortName);
                break;
            case Constant.FIND_COMPANY:
                //完善信息 -- 公司信息 -- toolbar右边带图标
                replaceFragmentWithTitle("公司信息", getStr(R.string.search));
                mLine.setVisibility(View.GONE);
                break;
            case Constant.NEW_SHIP_ADDRESS:
                //新建地址
                replaceFragmentWithTitle(FragmentFactory.getFragmentTitle(mType));
                break;
            case Constant.SETTING_PWD:
                //注册设置密码
                String phone = getIntent().getStringExtra(Msg.PHONE);
                replaceNewFragmentWithTitleAndMsg(FragmentFactory.getFragmentTitle(mType), phone);
                mToolbar.setBackgroundColor(getResources().getColor(R.color.sellerColor));
                break;
            case Constant.ABOUT_US:
                String version = getIntent().getStringExtra(Msg.VERSION);
                replaceNewFragmentWithTitleAndMsg(getStr(R.string.toolbar_about_us), version);
                break;
            case Constant.SUBMIT_APPLY:
                //申请加入公司成功
                String company = getIntent().getStringExtra(Msg.COMPANY);
                String manager_phone = getIntent().getStringExtra(Msg.MANAGER_PHONE);
                replaceNewFragmentWithTitleAndMsg("提交申请", company, manager_phone);
                break;
            case Constant.MAIN_TRADE:
//                replaceFragmentWithTitle("主营行业", "保存");
                replaceFragmentWithTitle(getStr(R.string.toolbar_main_trade));
                break;
            case Constant.MAIN_TRADE_NEW:
                replaceFragmentWithTitle(getStr(R.string.toolbar_main_trade), getStr(R.string.save));
                break;
            case Constant.CHOOSE_STATES_FIRST:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(getResources().getColor(R.color.cusStatusBarColor));
                }
                mToolbar.setBackgroundColor(getResources().getColor(R.color.StvColor));
            case Constant.CHOOSE_STATES:
                replaceFragmentWithTitle("");
                if (mType.equals(Constant.CHOOSE_STATES_FIRST)) {
                    mToolbar.setNavigationIcon(null);
                }
                mLine.setVisibility(View.GONE);
                break;
            case Constant.COMPANY_ADDRESS:
                replaceFragmentWithTitle("公司所在地");
                break;
            case Constant.COMPANY_SELECT_ADDRESS:
                replaceFragmentWithTitle("公司所在地");
                break;
            case Constant.GOODS_CLASSIFY:
                replaceFragmentWithTitle("分类");
                break;
            case Constant.NEW_GOODS_CLASSIFY:
                replaceFragmentWithTitle("选择分类");
                ArrayList<String> cateIds = getIntent().getStringArrayListExtra(Msg.CATE_IDS);
                mCateIds = cateIds;
                break;
            case Constant.BRAND:
                //完善信息 -- 修改姓名
                replaceFragmentWithTitle("所属品牌", getStr(R.string.save));
                break;
            case Constant.PRICE:
                replaceFragmentWithTitle("起批量/价格", getStr(R.string.save));
                break;
            case Constant.BUY_INTENT:
                replaceFragmentWithTitle("选择采购意向", "跳过");
                break;
            case Constant.EDIT_BUYER_INTENT:
                replaceFragmentWithTitle("选择采购意向");
                break;
            case Constant.GOODS_DESC:
                replaceFragmentWithTitle("商品描述");
                break;
            case Constant.EDIT_GOODS_DESC:
                //编辑商品描述
                int productId = getIntent().getIntExtra(Msg.PRODUCT_ID, -1);
//                replaceNewFragmentWithTitleAndMsg("商品描述", productId + "");
                replaceGoodsDescFragment("商品描述", productId + "");
                break;
            case Constant.KEEP:
                //收藏求购
                replaceFragmentWithTitle(MyApp.sLoginType.equals(Constant.BUYER) ? "收藏产品" : "收藏求购");
                break;
            case Constant.TALK_OVER:
                //洽谈过
                replaceFragmentWithTitle("洽谈过");
                break;
            case Constant.SET_NAME_FROM_PERSONAL:
                replaceFragmentWithTitle("姓名", getStr(R.string.save));
                break;
            case Constant.SET_POSITION:
                replaceFragmentWithTitle("我的职位", getStr(R.string.save));
                break;
            case Constant.ATTENTION:
                replaceFragmentWithTitle("关注公司");
                break;
            case Constant.COMPANY_BASIC_INFO:
                //公司信息 -- 公司/店铺简称
                String companyIntro = getIntent().getStringExtra(Msg.COMPANY_INTRO);
                replaceFragment("公司概况", getStr(R.string.save), companyIntro);
                break;
            case Constant.SET_PASSWORD_UPDATE:
                replaceFragmentWithTitle("设置密码");
                break;
            case Constant.SET_PASSWORD_FORGET:
                replaceNewFragmentWithTitleAndMsg("设置密码", getIntent().getStringExtra(Msg.PHONE));
                break;
            case Constant.EDIT_PURCHASE:
            case Constant.EDIT_PURCHASE_BEFORE:
                PurchaseDetailInfo.ContentBean.StProductsBean extra = (PurchaseDetailInfo.ContentBean.StProductsBean) getIntent().getSerializableExtra(Msg.PURCHASE);
                replaceEditPurchaseWithTitleAndMsg("采购商品", extra);
                break;
            case Constant.NEW_PURCHASE:
                replaceFragmentWithTitle("采购产品");
                break;
            case Constant.MY_PURCHASE:
                replaceFragmentWithTitle("我的求购");
                break;
            case Constant.MY_SUB_PURCHASE:
                replaceFragment("我的求购", "", "");
                mTvRight.setVisibility(View.GONE);
                break;
            case Constant.CHOICE_SELLER:
                replaceFragmentWithTitle("精选好商", R.mipmap.search, 18);
                break;
            case Constant.GOODS_LIST:
                String firstCateId = getIntent().getStringExtra(Msg.FIRST_CATE_ID);
                String title = getIntent().getStringExtra(Msg.TITLE);
                replaceNewFragmentWithTitleAndMsg(title, firstCateId);
                break;
            case Constant.WEB:
                String webUrl = getIntent().getStringExtra(Msg.WEB_URL);
                String titleWeb = getIntent().getStringExtra(Msg.TITLE);
                replaceNewFragmentWithTitleAndMsg(titleWeb, webUrl);
                break;
            case Constant.SELLER_FRAGMENT:
                String mainIndustry = getIntent().getStringExtra(Msg.MAIN_INDUSTRY);
                String titleMain = getIntent().getStringExtra(Msg.TITLE);
                replaceNewFragmentWithTitleAndMsg(titleMain, mainIndustry);
                break;
            case Constant.PURCHASE_LIST:
                String cruxKey = getIntent().getStringExtra(Msg.CRUX_KEY);
                String titlePurchase = getIntent().getStringExtra(Msg.TITLE);
                String purchaseType = getIntent().getStringExtra(Msg.PURCHASE_TYPE);
                replaceNewFragmentWithTitleAndMsg(titlePurchase, cruxKey, purchaseType);
                break;
            case Constant.NOTICE:
                replaceFragmentWithTitle("消息通知");
                break;

        }
        //ToolBar左侧返回按钮的监听
        FragmentFactory.finishWithFragments(mToolbar, this);
    }

    private void replaceGoodsDescFragment(String title, String msg) {
//        GoodsDescFragment fragment = (GoodsDescFragment) FragmentFactory.getNewFragmentByTag(mType, msg);
//        if (getSupportFragmentManager().findFragmentByTag(mType) == null) {
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            // 设置tag
//            fragmentTransaction.replace(R.id.fl_container, fragment, mType);
//            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//            // 这里要设置tag，上面也要设置tag
//            fragmentTransaction.addToBackStack(mType);
//            fragmentTransaction.commit();
//        } else {
//            // 存在则弹出在它上面的所有fragment，并显示对应fragment
//            getSupportFragmentManager().popBackStack(mType, 0);
//        }
        initToolBar(title);
    }

    private void replaceFragment(String title, String msg) {
        BaseFragment fragment = FragmentFactory.getNewFragmentByTag(mType, msg);
        if (getSupportFragmentManager().findFragmentByTag(mType) == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            // 设置tag
            fragmentTransaction.replace(R.id.fl_container, fragment, mType);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            // 这里要设置tag，上面也要设置tag
            fragmentTransaction.addToBackStack(mType);
            fragmentTransaction.commit();
        } else {
            // 存在则弹出在它上面的所有fragment，并显示对应fragment
            getSupportFragmentManager().popBackStack(mType, 0);
        }
        initToolBar(title);
    }


    public void replaceFragmentWithTitle(String title) {
        FragmentFactory.replaceFragment(this, R.id.fl_container, mType);
        initToolBar(title);
    }

    public void replaceFragmentWithTitle(String title, Integer icon) {
        FragmentFactory.replaceFragment(this, R.id.fl_container, mType);
        initToolBar(title, icon, null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(ContainerFooterActivity.this, SearchActivity.class));
            }
        });
    }

    public void replaceFragmentWithTitle(String title, Integer icon, Integer iconSize) {
        FragmentFactory.replaceFragment(this, R.id.fl_container, mType);
        initToolBar(title, icon, iconSize, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(ContainerFooterActivity.this, SearchActivity.class));
            }
        });
    }

    public <T> void replaceFragment(String title, String right, T... msg) {
        replaceFragment(R.id.fl_container, FragmentFactory.getNewFragmentByTag(mType, msg), mType);
        initToolBar(title, right, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mType) {
                    case Constant.COMPANY_SHORT_NAME:
                        saveMsgFromSetFragment();
                        break;
                    case Constant.COMPANY_BASIC_INFO:
//                        CompanyBasicInfoFragment companyBasicInfoFragment = (CompanyBasicInfoFragment) getSupportFragmentManager().findFragmentByTag(mType);
//                        String etCompanyBasicInfo = companyBasicInfoFragment.getEtContent();
//                        if (TextUtils.isEmpty(etCompanyBasicInfo)) {
//                            ToastUtil.showShort(ContainerFooterActivity.this, "输入为空，请重新输入");
//                            return;
//                        }
//                        Intent intentCompanyBasicInfo = new Intent();
//                        intentCompanyBasicInfo.putExtra(Msg.MSG, etCompanyBasicInfo);
//                        setResult(RESULT_OK, intentCompanyBasicInfo);
//                        finish();
                        break;
                    case Constant.MY_SUB_PURCHASE:
//                        MySubPurchaseFragment fragment = (MySubPurchaseFragment) getSupportFragmentManager().findFragmentByTag(mType);
//                        fragment.editState();
                        break;
                }
            }
        });
    }

    public void replaceFragmentWithTitle(String title, String right) {
        FragmentFactory.replaceFragment(this, R.id.fl_container, mType);
        initToolBar(title, right, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mType) {
                    case Constant.FIND_COMPANY:
                        //公司信息--搜索
//                        FindCompanyFragment fragment = (FindCompanyFragment) getSupportFragmentManager().findFragmentByTag(mType);
//                        fragment.searchCompany("");
                        break;
                    case Constant.SET_NAME:
                        //完善资料--姓名
//                        SetNameFragment setNameFragment = (SetNameFragment) getSupportFragmentManager().findFragmentByTag(mType);
//                        String etName = setNameFragment.getEtName();
//                        if (TextUtils.isEmpty(etName)) {
//                            ToastUtil.showShort(ContainerFooterActivity.this, "姓名为空，请填写");
//                            return;
//                        }
//                        if (etName.length() < 2) {
//                            ToastUtil.showShort(ContainerFooterActivity.this, "请输入2~10个汉字");
//                            return;
//                        }
//                        Intent intent_name = new Intent();
//                        intent_name.putExtra(Msg.NAME, etName);
//                        setResult(RESULT_OK, intent_name);
//                        finish();
                        break;
                    case Constant.SET_COMPANY_SHORT_NAME:
//                        SetNameFragment setComNameFragment = (SetNameFragment) getSupportFragmentManager().findFragmentByTag(mType);
//                        String etComName = setComNameFragment.getEtName();
//                        if (TextUtils.isEmpty(etComName)) {
//                            ToastUtil.showShort(ContainerFooterActivity.this, "公司简称为空，请填写");
//                            return;
//                        }
//                        Intent intent_com_name = new Intent();
//                        intent_com_name.putExtra(Msg.COM_NAME, etComName);
//                        setResult(RESULT_OK, intent_com_name);
//                        finish();
                        break;
                    case Constant.MAIN_TRADE:
//                        MainTradeFragment mainTradeFragment = (MainTradeFragment) getSupportFragmentManager().findFragmentByTag(mType);
//                        String selectMsg = mainTradeFragment.getSelectMsg();
//                        if (TextUtils.isEmpty(selectMsg)) {
//                            ToastUtil.showShort(ContainerFooterActivity.this, "请选择行业");
//                            return;
//                        }
//                        Intent intent_main_trade = new Intent();
//                        intent_main_trade.putExtra(Msg.MAIN_TRADE_CODE, selectMsg);
//                        intent_main_trade.putExtra(Msg.MAIN_TRADE_MSG, mainTradeFragment.getMsg());
//                        setResult(RESULT_OK, intent_main_trade);
//                        finish();
                        break;
                    case Constant.MAIN_TRADE_NEW:
//                        MainTradeNewFragment mainTradeNewFragment = (MainTradeNewFragment) getSupportFragmentManager().findFragmentByTag(mType);
//                        String selectMsgNew = mainTradeNewFragment.getSelectMsg();
//                        if (TextUtils.isEmpty(selectMsgNew)) {
//                            ToastUtil.showShort(ContainerFooterActivity.this, "请选择行业");
//                            return;
//                        }
//                        Intent intent_main_trade_new = new Intent();
//                        intent_main_trade_new.putExtra(Msg.MAIN_TRADE_CODE, selectMsgNew);
//                        intent_main_trade_new.putExtra(Msg.MAIN_TRADE_MSG, mainTradeNewFragment.getMsg());
//                        setResult(RESULT_OK, intent_main_trade_new);
//                        finish();
                        break;

                    case Constant.BRAND:
                        //发布商品 -- 所属品牌
//                        SetNameFragment brandFragment = (SetNameFragment) getSupportFragmentManager().findFragmentByTag(mType);
//                        String etBrandName = brandFragment.getEtName();
//                        if (TextUtils.isEmpty(etBrandName)) {
//                            ToastUtil.showShort(ContainerFooterActivity.this, "所属品牌为空，请填写");
//                            return;
//                        }
//                        Intent intentBrand = new Intent();
//                        intentBrand.putExtra(Msg.BRAND, etBrandName);
//                        setResult(RESULT_OK, intentBrand);
//                        finish();
                        break;
                    case Constant.PRICE:
//                        PriceFragment priceFragment = (PriceFragment) getSupportFragmentManager().findFragmentByTag(mType);
//                        Intent intentPrice = new Intent();
//                        boolean isSetPrice = priceFragment.getIsSetPrice();
//                        if (!isSetPrice) {
//                            intentPrice.putExtra(Msg.FACE, Msg.PRICE_FACE);
//                            priceFragment.clearPrice();
//                            setResult(RESULT_OK, intentPrice);
//                            finish();
//                        } else {
//                            intentPrice.putExtra(Msg.FACE, Msg.PRICE_NO_FACE);
//                            List<GoodsDetailInfo.ContentBean.SpProductPiceBean> priceList = priceFragment.getPriceList();
//                            if (priceList != null) {
//                                if (priceList.size() > 0) {
//                                    boolean legal = priceFragment.isLegal();
//                                    if (legal) {
//                                        setResult(RESULT_OK, intentPrice);
//                                        MyApp.sPriceList = priceList;
//                                        finish();
//                                    } else {
//
//                                    }
//                                } else {
//                                    ToastUtil.showShort(ContainerFooterActivity.this, "请输入起批量");
//                                }
//                            } else {
//                                ToastUtil.showShort(ContainerFooterActivity.this, "请输入起批量");
//                            }
//                        }
                        break;
                    case Constant.BUY_INTENT:
                        //跳过选择采购意向
//                        String userName = SPUtils.getString(ContainerFooterActivity.this, Constant._USER_NAME, "");
//                        if (TextUtils.isEmpty(userName)) {
//                            Intent openFulfillInfo = new Intent(ContainerFooterActivity.this, FulfillInfoActivity.class);
//                            openFulfillInfo.putExtra(Msg.LOGIN_TYPE, Constant.BUYER);
//                            SPUtils.saveString(ContainerFooterActivity.this, Constant._LOGIN_TYPE, Constant.BUYER);
//                            MyApp.sLoginType = Constant.BUYER;
//                            startActivity(openFulfillInfo);
//                            finish();
//                        } else {
//                            finish();
//                        }
                        startActivity(new Intent(ContainerFooterActivity.this, HomeActivity.class));
                        finish();
                        break;
                    case Constant.SET_NAME_FROM_PERSONAL:
                        saveMsgFromSetFragment();
                        break;
                    case Constant.SET_POSITION:
                        saveMsgFromSetFragment();
                        break;
                }
            }

        });
    }

    private void saveMsgFromSetFragment() {
//        SetNameFragment brandFragment = (SetNameFragment) getSupportFragmentManager().findFragmentByTag(mType);
//        String etBrandName = brandFragment.getEtName();
//        if (TextUtils.isEmpty(etBrandName)) {
//            ToastUtil.showShort(ContainerFooterActivity.this, "输入为空，请重新输入");
//            return;
//        }
//        if (mType.equals(Constant.SET_NAME_FROM_PERSONAL)) {
//            if (etBrandName.length() < 2 || etBrandName.length() > 20) {
//                ToastUtil.showShort(ContainerFooterActivity.this, "限输入2-20位文字");
//                return;
//            }
//        }
//        Intent intentBrand = new Intent();
//        intentBrand.putExtra(Msg.MSG, etBrandName);
//        setResult(RESULT_OK, intentBrand);
//        finish();
    }

    public void replaceNewFragmentWithTitleAndMsg(String title, String... msg) {
        replaceFragment(R.id.fl_container, FragmentFactory.getNewFragmentByTag(mType, msg));
        initToolBar(title);
    }

    public void replaceEditPurchaseWithTitleAndMsg(String title, Serializable msg) {
//        EditPurchaseFragment fragment = (EditPurchaseFragment) FragmentFactory.getNewFragmentByTag(mType, msg);
//        replaceFragment(R.id.fl_container, fragment);
//        initToolBar(title);
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected int attachLayoutRes() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//        if (!TextUtils.isEmpty(MyApp.sLoginType)){
//            setTheme(MyApp.sLoginType.equals(Constant.BUYER)?R.style.BuyTheme:R.style.SellerTheme);
//        }
        return R.layout.activity_container_footer;
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            FragmentFactory.removeAllFragment(ContainerFooterActivity.this);
            finish();
        }
        return false;
    }

    public void setData(String value, String msg) {
        Intent data = new Intent();
        data.putExtra(value, msg);
        setResult(RESULT_OK, data);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.OPEN_CAMERA:
                    //拍照
                    try {
                        cropImageUri = Uri.fromFile(fileCropUri);
                        PhotoUtils.cropImageUri(this, imageUri, cropImageUri, Constant.CROP_PICTURE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case Constant.OPEN_PHOTO:
                    if (hasSdcard()) {
                        cropImageUri = Uri.fromFile(fileCropUri);
                        Uri newUri = Uri.parse(PhotoUtils.getPath(this, data.getData()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                            newUri = FileProvider.getUriForFile(this, "com.hc360.iambuyer.fileprovider", new File(newUri.getPath()));
                        }
                        PhotoUtils.cropImageUri(this, newUri, cropImageUri, Constant.CROP_PICTURE);
                    } else {
                        Toast.makeText(this, getStr(R.string.have_no_sd), Toast.LENGTH_SHORT).show();
                    }
                    break;
                case Constant.CROP_PICTURE:
                    //裁剪后
                    try {
                        Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, this);
                        if (bitmap != null) {
                            Bitmap crop_bitmap = ImageUtil.compressBitmapByQuality( bitmap, 300);
                            File file = CameraUtil.saveImageToGallery(ContainerFooterActivity.this, crop_bitmap, Constant.HEAD_PIC);
                            //上传图片
                            switch (mType) {
                                case Constant.SUGGESTION:
                                    SuggestionFragment fragment = (SuggestionFragment) getSupportFragmentManager().findFragmentByTag(Constant.SUGGESTION);
                                    fragment.getPic(bitmap, file);
                                    break;
                                case Constant.GOODS_DESC:
                                case Constant.EDIT_GOODS_DESC:
//                                    GoodsDescFragment goodsDescFragment = (GoodsDescFragment) getSupportFragmentManager().findFragmentByTag(mType);
//                                    goodsDescFragment.getPic(bitmap, file);
                                    break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setRightText(String text) {
        mTvRight.setText(text);
    }

    public void setRightVis(boolean vis) {
        mTvRight.setVisibility(vis ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

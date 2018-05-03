package com.hc360.koiambuyer.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.multidex.MultiDexApplication;

import com.hc360.koiambuyer.BuildConfig;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.CityInfo;
import com.hc360.koiambuyer.api.bean.GoodsDetailInfo;
import com.hc360.koiambuyer.api.bean.PostPicInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.utils.SPUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/8/28
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class MyApp extends MultiDexApplication {

    private static MyApp sContext;
    public static List<GoodsDetailInfo.ContentBean.SpProductPiceBean> sPriceList = new ArrayList<>();
    public static ArrayList<PostPicInfo> sImgBeans = new ArrayList<>();
    public static int sPosition;
    public static String sUserId ;
    public static String sComId;
    public static String sLoginType = "";
    public static String sPhone;
    private static final String DB_NAME = "user.db";

    public static List<CityInfo> options1Items = new ArrayList<>();
    public static List<ArrayList<CityInfo.SubBeanX>> options2Items = new ArrayList<>();
    public static List<ArrayList<ArrayList<CityInfo.SubBeanX.SubBean>>> options3Items = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
//        initDatabase();
        initConfig();
    }

    private void initConfig() {
        sContext = this;
        sComId = SPUtils.getString(this, Constant._COM_ID,"");
        sLoginType = SPUtils.getString(this,Constant._LOGIN_TYPE,"");
        sUserId = SPUtils.getString(this,Constant._ID,"");
        sPhone = SPUtils.getString(this,Constant._PHONE,"");
        RetrofitService.init();
        if (RetrofitService.isDebug){
            SPUtils.saveString(this,Constant._ID,"365");
            sUserId = "365";
        }

        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，false需要验证
        options.setAcceptInvitationAlways(false);
        EaseUI.getInstance().init(getApplicationContext(), options);
        try {
            EMClient.getInstance().setDebugMode(true);  //设置debug模式
        }catch (Exception e){

        }
        if (BuildConfig.DEBUG) {
            Logger.init("LogTAG");
        }
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

//    private void initDatabase() {
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DB_NAME);
//        Database database = helper.getWritableDb();
//        sDaoSession = new DaoMaster(database).newSession();
//    }

//    public static DaoSession getDaoSession() {
//        return sDaoSession;
//    }



    public synchronized static MyApp getApp() {
        return sContext;
    }
    public synchronized static Context getAppContext() {
        return sContext.getApplicationContext();
    }
    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public static void runUITask(Runnable run) {
        mHandler.post(run);
    }

    public synchronized static MyApp getInstance(){
        return sContext;
    }
}

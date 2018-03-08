package com.hc360.koiambuyer.utils;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.view.AccountActivity;
import com.hc360.koiambuyer.view.MyApp;
import com.hc360.koiambuyer.view.home.HomeActivity;


/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/27
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class ActivitySkipHelper {
    public static void  _doSkip(Activity activity){
        String id = SPUtils.getString(activity, Constant._ID, "");
        if (TextUtils.isEmpty(id)) {
            activity.startActivity(new Intent(activity, AccountActivity.class));
        }else{
            activity.startActivity(new Intent(activity, HomeActivity.class));
        }
        activity.finish();
    }

    public static boolean  HomeDoSkip(Activity activity){
        String id = SPUtils.getString(activity, Constant._ID, "");
        String userName = SPUtils.getString(activity, Constant._USER_NAME, "");
        if (TextUtils.isEmpty(id)) {
            activity.startActivity(new Intent(activity, AccountActivity.class));
            activity.finish();
            return false;
        }else if (TextUtils.isEmpty(userName)){
            return false;
        }else{
            MyApp.sUserId = id;
            return true;
        }
    }
}

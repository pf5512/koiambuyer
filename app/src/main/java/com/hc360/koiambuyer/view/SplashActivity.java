package com.hc360.koiambuyer.view;

import android.Manifest;

import com.alibaba.fastjson.JSON;
import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.CityInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.utils.ActivitySkipHelper;
import com.hc360.koiambuyer.utils.DBHelper;
import com.hc360.koiambuyer.utils.GetJsonDataUtil;
import com.hc360.koiambuyer.utils.RxHelper;
import com.hc360.koiambuyer.utils.SPUtils;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;
import rx.Subscriber;

public class SplashActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks{

    String[] perms = {Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO,Manifest.permission.CHANGE_WIFI_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE,Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    protected void initView() {
        if (EasyPermissions.hasPermissions(this, perms)) {

        } else {
            EasyPermissions.requestPermissions(this, getStr(R.string.location_permission),
                    Constant.SPLASH_PERM, perms);
        }
        DBHelper.writeMainTradeDB(this);
        DBHelper.writeClassifyDB(this);
        DBHelper.writeCityDB(this);
        toJson();
        RxHelper.countdown(3)
                .compose(this.<Integer>bindToLife())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        if (SPUtils.getBoolean(SplashActivity.this,Constant.FIRST_LOGIN,true)){
//                            startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                            ActivitySkipHelper._doSkip(SplashActivity.this);
//                            startActivity(new Intent(SplashActivity.this,HomeActivity.class));
                        }else{
                            ActivitySkipHelper._doSkip(SplashActivity.this);
//                            startActivity(new Intent(SplashActivity.this,HomeActivity.class));
                        }
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (SPUtils.getBoolean(SplashActivity.this,Constant.FIRST_LOGIN,true)){
//                            startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                            ActivitySkipHelper._doSkip(SplashActivity.this);
//                            startActivity(new Intent(SplashActivity.this,HomeActivity.class));
                        }else{
                            ActivitySkipHelper._doSkip(SplashActivity.this);
//                            startActivity(new Intent(SplashActivity.this,HomeActivity.class));
                        }
                        finish();
                    }

                    @Override
                    public void onNext(Integer integer) {

                    }
                });
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    public void toJson() {
        initJsonData();
    }
    public void initJsonData() {//解析数据
        try {
            /**
             * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
             * 关键逻辑在于循环体GetJsonDataUtil
             * */
            String JsonData = new GetJsonDataUtil().getJson(this, "ko_province.json");//获取assets目录下的json文件数据
            List<CityInfo> jsonBean = new ArrayList<>();
            jsonBean = parseData(JsonData);//用Gson 转成实体
            /**
             * 添加省份数据
             * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
             * PickerView会通过getPickerViewText方法获取字符串显示出来。
             */
            MyApp.options1Items = jsonBean;
            for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
                ArrayList<CityInfo.SubBeanX> CityList = new ArrayList<>();//该省的城市列表（第二级）
                for (int c = 0; c < jsonBean.get(i).sub.size(); c++) {//遍历该省份的所有城市
                    CityList.add(jsonBean.get(i).sub.get(c));//添加城市
                }
                /**
                 * 添加城市数据
                 */
                MyApp.options2Items.add(CityList);
            }
        }catch (Exception e){
            ToastUtil.showShort(this,e.toString());
        }

    }

    public List<CityInfo> parseData(String result) {//Gson 解析
        List<CityInfo> cityInfos = new ArrayList<>();
        try {
            cityInfos = JSON.parseArray(result, CityInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtil.showShort(this, getStr(R.string.parse_failure));
        }
        return cityInfos;
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (requestCode == Constant.SPLASH_PERM) {

        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == Constant.SPLASH_PERM) {
            ToastUtil.showLong(this, getStr(R.string.open_camera));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}

package com.hc360.koiambuyer.api;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.AddressInfo;
import com.hc360.koiambuyer.api.bean.AttentionInfo;
import com.hc360.koiambuyer.api.bean.AttentionKoInfo;
import com.hc360.koiambuyer.api.bean.ChatAttentionInfo;
import com.hc360.koiambuyer.api.bean.ChatGoodInfo;
import com.hc360.koiambuyer.api.bean.ChatInfo;
import com.hc360.koiambuyer.api.bean.ChatListInfo;
import com.hc360.koiambuyer.api.bean.ChatLookedInfo;
import com.hc360.koiambuyer.api.bean.ChatMsgInfo;
import com.hc360.koiambuyer.api.bean.ChatStatesInfo;
import com.hc360.koiambuyer.api.bean.ChildrenAccountInfo;
import com.hc360.koiambuyer.api.bean.ChoiceSellerInfo;
import com.hc360.koiambuyer.api.bean.CityManagerInfo;
import com.hc360.koiambuyer.api.bean.ComAuthInfo;
import com.hc360.koiambuyer.api.bean.CompanyInfo;
import com.hc360.koiambuyer.api.bean.CompanyInfoHomeHotInfo;
import com.hc360.koiambuyer.api.bean.FindCompanyInfo;
import com.hc360.koiambuyer.api.bean.FindInfo;
import com.hc360.koiambuyer.api.bean.GoodsDescInfo;
import com.hc360.koiambuyer.api.bean.GoodsDetailInfo;
import com.hc360.koiambuyer.api.bean.GoodsInfo;
import com.hc360.koiambuyer.api.bean.GoodsListInfo;
import com.hc360.koiambuyer.api.bean.HotInfo;
import com.hc360.koiambuyer.api.bean.InitInfo;
import com.hc360.koiambuyer.api.bean.IntentInfo;
import com.hc360.koiambuyer.api.bean.KeepGoodsInfo;
import com.hc360.koiambuyer.api.bean.LikeInfo;
import com.hc360.koiambuyer.api.bean.LoginInfo;
import com.hc360.koiambuyer.api.bean.MeInfo;
import com.hc360.koiambuyer.api.bean.MsgInfo;
import com.hc360.koiambuyer.api.bean.MyIntentInfo;
import com.hc360.koiambuyer.api.bean.MyPurchaseInfo;
import com.hc360.koiambuyer.api.bean.MySuggestionInfo;
import com.hc360.koiambuyer.api.bean.NewPurchaseInfo;
import com.hc360.koiambuyer.api.bean.NoticeInfo;
import com.hc360.koiambuyer.api.bean.OrderDetailInfo;
import com.hc360.koiambuyer.api.bean.OrderInfo;
import com.hc360.koiambuyer.api.bean.PostPicInfo;
import com.hc360.koiambuyer.api.bean.PublishPurchaseInfo;
import com.hc360.koiambuyer.api.bean.PurchaseDetailInfo;
import com.hc360.koiambuyer.api.bean.PurchaseHomeInfo;
import com.hc360.koiambuyer.api.bean.PurchaseInfo;
import com.hc360.koiambuyer.api.bean.PurchaseItemInfo;
import com.hc360.koiambuyer.api.bean.QuoteInfo;
import com.hc360.koiambuyer.api.bean.ResponseInfo;
import com.hc360.koiambuyer.api.bean.RightNowInfo;
import com.hc360.koiambuyer.api.bean.SearchInfo;
import com.hc360.koiambuyer.api.bean.SettingInfo;
import com.hc360.koiambuyer.api.bean.ShipAddressInfo;
import com.hc360.koiambuyer.api.bean.SupplyHomeInfo;
import com.hc360.koiambuyer.api.bean.TabChatInfo;
import com.hc360.koiambuyer.api.bean.TalkOverInfo;
import com.hc360.koiambuyer.api.bean.TalkedInfo;
import com.hc360.koiambuyer.api.bean.UserBaseInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.LoginTypeEnum;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.model.SmsStautsEnum;
import com.hc360.koiambuyer.model.States;
import com.hc360.koiambuyer.utils.HttpUtils;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.MyApp;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Project: TaxDuck
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/8/28
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class RetrofitService {
    private static IApi sServiceSecond;
    private static IApi sServiceThird;
    private static IApi sService;
    private static IApi sServiceIMG;
    public static boolean isDebug = false;

    //测试环境
    public static final String API_HOST = "http://10.8.84.28:8080/";
    public static final String API_HOST_SECOND = "http://10.8.84.28:8080/";
    public static final String API_HOST_THIRD = "http://10.8.84.28:8080/";
//    private static final String API_HOST_IMG = "http://10.158.41.39:8080/imgup/upLoad/";
    //正式环境
//        public static final String API_HOST_SECOND = "http://api.iambuyer.com/";
//        public static final String API_HOST = "http://api.iambuyer.com/";
        private static final String API_HOST_IMG = "http://img01.iambuyer.com/imgup/upLoad/";

    //设缓存有效期为1天
    static final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置
    //(假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)
    static final String CACHE_CONTROL_NETWORK = "Cache-Control: public, max-age=3600";
    // 避免出现 HTTP 403 Forbidden，参考：http://stackoverflow.com/questions/13670692/403-forbidden-with-java-but-not-web-browser
    static final String AVOID_HTTP403_FORBIDDEN = "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11";

    public static void init() {
        Cache cache = new Cache(new File(MyApp.getAppContext().getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().cache(cache)
                .retryOnConnectionFailure(true)
                .addInterceptor(sLoggingInterceptor)
                .addNetworkInterceptor(sRewriteCacheControlInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(API_HOST)
                .build();
        sService = retrofit.create(IApi.class);

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(API_HOST_SECOND)
                .build();
        sServiceSecond = retrofit.create(IApi.class);

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(API_HOST_THIRD)
                .build();
        sServiceThird = retrofit.create(IApi.class);

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(API_HOST_IMG)
                .build();
        sServiceIMG= retrofit.create(IApi.class);

        if (RetrofitService.API_HOST_SECOND.equals("http://10.158.41.39:8091/")){
            isDebug = true;
        }
    }
    /**
     * 打印返回的json数据拦截器
     */
    private static final Interceptor sLoggingInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request request = chain.request();
            Buffer requestBuffer = new Buffer();
            if (request.body() != null) {
                request.body().writeTo(requestBuffer);
            } else {
                Logger.d("LogTAG", "request.body() == null");
            }
            //打印url信息
            Logger.w(request.url() + (request.body() != null ? "?" + _parseParams(request.body(), requestBuffer) : ""));
            final Response response = chain.proceed(request);

            return response;
        }
    };
    @NonNull
    private static String _parseParams(RequestBody body, Buffer requestBuffer) throws UnsupportedEncodingException {
        if (body.contentType() != null && !body.contentType().toString().contains("multipart")) {
            return URLDecoder.decode(requestBuffer.readUtf8(), "UTF-8");
        }
        return "null";
    }
    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private static final Interceptor sRewriteCacheControlInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!HttpUtils.isNetworkAvailable(MyApp.getAppContext())) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                Logger.e("no network");
            }
            Response originalResponse = chain.proceed(request);

            if (HttpUtils.isNetworkAvailable(MyApp.getAppContext())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, " + CACHE_CONTROL_CACHE)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };
    //以下是具体的方法----------------------------------------------
    /**
     * 过滤器
     * @return
     */
    private static Func1<ResponseInfo, Boolean> _flatMap() {
        return new Func1<ResponseInfo, Boolean>() {
            @Override
            public Boolean call(ResponseInfo responseInfo) {
                if (responseInfo.ret.equals(States.STATES_RESULT_OK)){
                    return true;
                }else{
                    ToastUtil.showShort(MyApp.getAppContext(),responseInfo.msg);
                    return false;
                }
            }
        };
    }
    /**
     * 注册
     * @return
     */
    public static Observable<ResponseInfo> register(String phone, String password) {
        HashMap<String,String> map  =new HashMap();
        map.put("phone",phone);
        map.put("password",password);
        map.put("portal","IambuyerKorea");
        return sService.register(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<ResponseInfo, Boolean>() {
                    @Override
                    public Boolean call(ResponseInfo responseInfo) {
                        if (responseInfo.ret.equals(States.STATES_RESULT_OK)){
                            return true;
                        }else{
                            ToastUtil.showShort(MyApp.getAppContext(),responseInfo.msg);
                            return false;
                        }
                    }
                });
    }
    /**
     * 发送验证码
     * @return
     */
    public static Observable<ResponseInfo> sendIdentify(String phone, String businessName) {
        HashMap<String,String> map  =new HashMap();
        map.put("phone",phone);
        map.put("businessName",businessName);
        map.put("portal","IambuyerKorea");
        return sService.sendIdentify(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }
    /**
     * 发送验证码,不带拦截器
     * @return
     */
    public static Observable<ResponseInfo> identify(String phone, String businessName) {
        HashMap<String,String> map  =new HashMap();
        map.put("phone",phone);
        map.put("businessName",businessName);
        map.put("portal","IambuyerKorea");
        Logger.e(map.toString());
        return sService.sendIdentify(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
    /**
     * 注册--检验验证码
     * @return
     */
    public static Observable<ResponseInfo> confirmIdentify(final String phone, final String phoneCode) {
        HashMap<String,String> map  =new HashMap();
        map.put("phone",phone);
        map.put("businessName", SmsStautsEnum.REG.getValue());
        map.put("phoneCode",phoneCode);
        map.put("portal","IambuyerKorea");
        return sService.confirmIdentify(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
    /**
     * 注册--检验验证码
     * @return
     */
    public static Observable<ResponseInfo> checkIdentify(final String phone, final String phoneCode ,String businessNumber) {
        HashMap<String,String> map  =new HashMap();
        map.put("phone",phone);
        map.put("businessName", businessNumber);
        map.put("phoneCode",phoneCode);
        map.put("portal","IambuyerKorea");
        return sService.confirmIdentify(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<ResponseInfo, Boolean>() {
                    @Override
                    public Boolean call(ResponseInfo responseInfo) {
                        if (responseInfo.ret.equals(States.STATES_RESULT_OK)){
                            return true;
                        }else{
                            ToastUtil.showShort(MyApp.getAppContext(),responseInfo.msg);
                            return false;
                        }

                    }
                });
    }

    /**
     * 注册--检验验证码
     * @return
     */
    public static Observable<ResponseInfo> checkIdentifySecond(final String phone, final String phoneCode ,String businessNumber) {
        HashMap<String,String> map  =new HashMap();
        map.put("phone",phone);
        map.put("businessName", businessNumber);
        map.put("phoneCode",phoneCode);
        map.put("portal","IambuyerKorea");
        return sService.confirmIdentify(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
    /**
     * 密码登录
     * @return
     */
    public static Observable<LoginInfo> loginByPwd(String phone, String password) {
        HashMap<String,String> map  =new HashMap();
        map.put("loginType", LoginTypeEnum.PASSWORD.getValue());
        map.put("phone",phone);
        map.put("password",password);
        map.put("portal","IambuyerKorea");
        return sService.loginByPwd(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
    /**
     * 验证码登录
     * @return
     */
    public static Observable<LoginInfo> loginByIdentify(String phone, String phonecode) {
        HashMap<String,String> map  =new HashMap();
        map.put("loginType",LoginTypeEnum.PHONECODE.getValue());
        map.put("phone",phone);
        map.put("phonecode",phonecode);
        map.put("portal","IambuyerKorea");
        return sService.loginByPwd(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<LoginInfo, Boolean>() {
                    @Override
                    public Boolean call(LoginInfo loginInfo) {
                        if (loginInfo.ret.equals("200")){
                            return true;
                        }else{
                            ToastUtil.showShort(MyApp.getAppContext(),loginInfo.msg);
                            return false;
                        }
                    }
                });
    }

    /**
     * 验证码登录
     * @return
     */
    public static Observable<LoginInfo> loginByIdentifyAccount(String phone, String phonecode) {
        HashMap<String,String> map  =new HashMap();
        map.put("loginType",LoginTypeEnum.PHONECODE.getValue());
        map.put("phone",phone);
        map.put("phonecode",phonecode);
        map.put("portal","IambuyerKorea");
        return sService.loginByPwd(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 判断是否已选择身份
     * @param id
     * @return
     */
    public static Observable<InitInfo> getInitStates(String id) {
        return sServiceSecond.getInitStates(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<InitInfo, Boolean>() {
                    @Override
                    public Boolean call(InitInfo initInfo) {
                        if (initInfo.ret.equals(States.STATES_RESULT_OK)){
                            return true;
                        }else{
                            ToastUtil.showShort(MyApp.getAppContext(),initInfo.msg);
                            return false;
                        }
                    }
                });
    }

    /**
     * 更新密码
     * @return
     */
    public static Observable<ResponseInfo> updatePwd(final String id, final String password) {
        HashMap<String,String> map  =new HashMap();
        map.put("id",id);
        map.put("password", password);
        return sService.confirmIdentify(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<ResponseInfo, Boolean>() {
                    @Override
                    public Boolean call(ResponseInfo responseInfo) {
                        if (responseInfo.ret.equals(200)){
                            return true;
                        }else{
                            ToastUtil.showShort(MyApp.getAppContext(),responseInfo.msg);
                            return false;
                        }
                    }
                });
    }

    /**
     * 更新密码
     * @return
     */
    public static Observable<ResponseInfo> updateLoginPwd(int id, final String password) {
        HashMap<String,Object> map  =new HashMap();
        map.put("id",id);
        map.put("password", password);
        return sService.updatePwd(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }

    /**
     * 上传图片
     * @return
     */
    public static Observable<PostPicInfo> postPic(File file) {
        Map<String, RequestBody> map = new HashMap<>();
        map.put("file\";filename=\""+1+".jpg",RequestBody.create(
                MediaType.parse("multipart/form-data"),file));
        Logger.e(map.toString());
        return sServiceIMG.postPic(map,"iambuyer")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<PostPicInfo, Boolean>() {
                    @Override
                    public Boolean call(PostPicInfo postPicInfo) {
                        if (postPicInfo.ret.equals(States.STATES_RESULT_OK)){
                            return true;
                        }else {
                            ToastUtil.showShort(MyApp.getAppContext(),MyApp.getAppContext().getResources().getString(R.string.upload_fail));
                            return false;
                        }
                    }
                });
    }


    /**
     * 查询公司
     * @return
     */
    public static Observable<FindCompanyInfo> findCompany(String compName, int pager) {
        return  sServiceSecond.findCompany(compName,pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 请求加入公司
     * @return
     */
    public static Observable<ResponseInfo> userApply(int userId,int compId) {
        HashMap<String,Integer> map  =new HashMap();
        map.put("userId",userId);
        map.put("compId", compId);
        return  sServiceSecond.userApply(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 创建新公司
     * @return
     */
    public static Observable<ResponseInfo> makeCompany(String comName,String comShortName,String mainTradeCode,String latitude,String longitude,String detailAddress,String id) {
        HashMap<String,String> map  =new HashMap();
        map.put("compName",comName);
        map.put("compShortName", comShortName);
        map.put("mainIndustry", mainTradeCode);
        map.put("longitude", longitude);
        map.put("latitude", latitude);
        map.put("addressDetail", detailAddress);
        map.put("userId", id);
        return  sServiceSecond.makeCompany(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<ResponseInfo, Boolean>() {
                    @Override
                    public Boolean call(ResponseInfo responseInfo) {
                        if (responseInfo.ret.equals("200")){
                            return true;
                        }else{
                            return false;
                        }
                    }
                });
    }

    /**
     * 创建新公司
     * @return
     */
    public static Observable<ResponseInfo> makeCompany(String comName,String comShortName,String mainTradeCode,String provinceCode,String cityCode,String areaCode,String detailAddress,String id) {
        HashMap<String,String> map  =new HashMap();
        map.put("compName",comName);
        map.put("compShortName", comShortName);
        map.put("mainIndustry", mainTradeCode);
        map.put("provinceCode", provinceCode);
        map.put("cityCode", cityCode);
        map.put("areaCode", areaCode);
        map.put("addressDetail", detailAddress);
        map.put("userId", id);
        return  sServiceSecond.makeCompany(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<ResponseInfo, Boolean>() {
                    @Override
                    public Boolean call(ResponseInfo responseInfo) {
                        if (responseInfo.ret.equals("200")){
                            return true;
                        }else{
                            return false;
                        }
                    }
                });
    }

    /**
     * 完善信息
     * @return
     */
    public static Observable<ResponseInfo> submit(Integer id, String loginType, String userName, String compId, String userHeadImg) {
        HashMap<String,Object> map  =new HashMap();
        map.put("userId",id);
        map.put("loginType",loginType);
        map.put("userName",userName);
        if (!compId.equals(States.NO_COMPANY)){
            map.put("compId",new Integer(compId));
        }
        if (!TextUtils.isEmpty(userHeadImg)){
            map.put("userHeadImg",userHeadImg);
        }
        return  sServiceSecond.submit(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<ResponseInfo, Boolean>() {
                    @Override
                    public Boolean call(ResponseInfo responseInfo) {
                        if (responseInfo.ret.equals("200")){
                            return true;
                        }else{
                            return false;
                        }
                    }
                });
    }

    /**
     * 认证公司
     * @return
     */
    public static Observable<ResponseInfo> compAuth(String comId,String comName,String compType,String proCode,String cityCode,String areaCode,String time,String name,String number,String img1,String img2,String img3,String img4,String img5,String img6,String applyUserType,String phone,String agentName) {
        HashMap<String,String> map  =new HashMap();
        map.put("compId",comId);
        map.put("compName",comName);
        map.put("compType",compType);
        map.put("provinceCode",proCode);
        map.put("cityCode",cityCode);
        map.put("areaCode",areaCode);
        map.put("regDate",time);
        map.put("legalPersonName",name);
        map.put("businLicenseCode",number);
        map.put("businLicenseImg",img1);
        map.put("accountLicenseImg",img2);
        map.put("legalIdcardFrontImg",img3);
        map.put("legalIdcardBackImg",img4);
        map.put("applyUserType",applyUserType);
        if (applyUserType.equals("1")){
            //代理人
            map.put("agentIdcardFrontImg",img5);
            map.put("agentIdcardBackImg",img6);
            map.put("applyUserName",agentName);
        }
        map.put("applyUserTelphone",phone);
        return  sServiceSecond.compAuth(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<ResponseInfo, Boolean>() {
                    @Override
                    public Boolean call(ResponseInfo responseInfo) {
                        if (responseInfo.ret.equals("200")){
                            return true;
                        }else{
                            ToastUtil.showShort(MyApp.getAppContext(),responseInfo.msg);
                            return false;
                        }
                    }
                });
    }

    /**
     * 认证公司信息查询
     * @param id
     * @return
     */
    public static Observable<ComAuthInfo> getComAuthInfo(String id) {
        return sServiceSecond.getComAuthInfo(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<ComAuthInfo, Boolean>() {
                    @Override
                    public Boolean call(ComAuthInfo comAuthInfo) {
                        if (comAuthInfo.ret.equals("200")){
                            return true;
                        }else{
                            ToastUtil.showShort(MyApp.getAppContext(),comAuthInfo.msg);
                            return false;
                        }
                    }
                });
    }

    /**
     * 完善信息
     * @return
     */
    public static Observable<ResponseInfo> submitSuggestion(String userId, String context,String phone, String feedType, List<String> imgs) {
        HashMap<String,String> map  =new HashMap();
        map.put("userId",userId);
        map.put("context",context);
        if (!TextUtils.isEmpty(phone)){
            map.put("phone",phone);
        }
        map.put("feedType",feedType);
        for (int i = 0; i < imgs.size(); i++) {
            map.put("img00"+(i+1),imgs.get(i));
        }
        return  sServiceSecond.submitSuggestion(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }

    /**
     * 获取发货地址列表
     * @return
     */
    public static Observable<ShipAddressInfo> getAddresses(Integer id) {
        return  sServiceSecond.getAddresses(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 设置为默认发货地址
     * @return
     */
    public static Observable<ResponseInfo> setDefaultAddress(int deliverId,String useState) {
        HashMap<String,Object> map  =new HashMap();
        map.put("receiveId",deliverId);
        map.put("useState",useState);
        return  sServiceSecond.setDefaultAddress(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }

    /**
     * 删除发货地址
     * @returni
     */
    public static Observable<ResponseInfo> deleteAddress(int id) {
        return  sServiceSecond.deleteAddress(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }

    /**
     * 新增发货地址
     * @return
     */
    public static Observable<ResponseInfo> addAddress(int userId, String provinceCode, String cityCode, String addressDetail, String receiveUser,String telphone,String useState) {
        HashMap<String,Object> map  =new HashMap();
        map.put("userId",userId);
        map.put("provinceCode",provinceCode);
        map.put("cityCode",cityCode);
        map.put("addressDetail",addressDetail);
        map.put("receiveUser",receiveUser);
        map.put("telphone",telphone);
        map.put("useState",useState);
        return  sServiceSecond.addAddress(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }

    /**
     * 认证公司信息查询
     * @param id
     * @return
     */
    public static Observable<AddressInfo> getAddress(int id) {
        return sServiceSecond.getAddress(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<AddressInfo, Boolean>() {
                    @Override
                    public Boolean call(AddressInfo addressInfo) {
                        if (addressInfo.ret.equals(States.STATES_RESULT_OK)){
                            return true;
                        }else{
                            ToastUtil.showShort(MyApp.getAppContext(),addressInfo.msg);
                            return false;
                        }

                    }
                });
    }

    /**
     * 编辑发货地址
     * @return
     */
    public static Observable<ResponseInfo> editAddress(int receiveId, int userId, String provinceCode, String cityCode, String addressDetail, String receiveUser, String telphone, String useState) {
        HashMap<String,Object> map  =new HashMap();
        map.put("receiveId",receiveId);
        map.put("userId",userId);
        map.put("provinceCode",provinceCode);
        map.put("cityCode",cityCode);
        map.put("addressDetail",addressDetail);
        map.put("receiveUser",receiveUser);
        map.put("telphone",telphone);
        map.put("useState",useState);
        return  sServiceSecond.setDefaultAddress(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }

    /**
     * 子账户列表
     * @return
     */
    public static Observable<ChildrenAccountInfo> getChildren(int compId, String state , int pager) {
        HashMap<String,Object> map  =new HashMap();
        map.put("compId",compId);
        map.put("state",state);
        return  sServiceSecond.getChildren(map,pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 子账户审核通过、审核拒绝
     * @return
     */
    public static Observable<ResponseInfo> askStates(String ids, int compId, String applyState) {
        HashMap<String,Object> map  =new HashMap();
        map.put("compId",compId);
        map.put("applyState",applyState);
        return  sServiceSecond.askStates(map,ids)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }

    /**
     * 采购意向列表
     * @return
     */
    public static Observable<IntentInfo> getIntents(int pager) {
        return  sServiceSecond.getIntents(pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<IntentInfo, Boolean>() {
                    @Override
                    public Boolean call(IntentInfo intentInfo) {
                        if (intentInfo.list.size()>0){
                            return true;
                        }else{
                            ToastUtil.showShort(MyApp.getAppContext(),MyApp.getAppContext().getResources().getString(R.string.no_data_toast));
                            return false;
                        }
                    }
                });
    }

    /**
     * 提交采购意向
     * @return
     */
    public static Observable<ResponseInfo> submitIntents(int userId, List<String> intentIds) {
        HashMap<String,Object> map  =new HashMap();
        map.put("userId",userId);
        map.put("stPurcIntentionCodeList",intentIds);
        return  sServiceSecond.submitIntents(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }

    /**
     * 发布商品
     * @return
     */
    public static Observable<ResponseInfo> submitPublishGoods( String providerId, String userId, String productName, String brandName, String isOnline, ArrayList<String> cateIds, int deliverGoogsId, String productPrice, String isHaveGoods, String isMade, List<String> loopImgs, String imgs, Map<String,String> intro, ArrayList<Map<String, Object>> priceList) {
        HashMap<String,Object> map  =new HashMap();
        map.put("providerId",providerId);
        map.put("userId",userId);
        map.put("productName",productName);
        map.put("brandName",brandName);
        map.put("isOnline",isOnline);
        for (int i = 0; i < cateIds.size(); i++) {
            switch (i){
                case 0:
                    map.put("firstCateId",cateIds.get(i));
                    break;
                case 1:
                    map.put("secondCateId",cateIds.get(i));
                    break;
                case 2:
                    map.put("thirdCateId",cateIds.get(i));
                    break;
                case 3:
                    map.put("fourthCateId",cateIds.get(i));
                    break;
            }
        }
        if (deliverGoogsId != 0){
            map.put("deliverGoogsId",deliverGoogsId);
        }
        map.put("productPrice",productPrice);
        map.put("isHaveGoods",isHaveGoods);
        map.put("isMade",isMade);
        for (int i = 0; i < loopImgs.size(); i++) {
            switch (i){
                case 0:
                    map.put("loopImg001",loopImgs.get(i));
                    break;
                case 1:
                    map.put("loopImg002",loopImgs.get(i));
                    break;
                case 2:
                    map.put("loopImg003",loopImgs.get(i));
                    break;
                case 3:
                    map.put("loopImg004",loopImgs.get(i));
                    break;
                case 4:
                    map.put("loopImg005",loopImgs.get(i));
                    break;
            }
        }
        map.put("intro",intro);
        map.put("imgs",imgs);
        if (productPrice.equals(Msg.PRICE_NO_FACE)){
            map.put("priceList",priceList);
        }
        Logger.e(map.toString());
        return  sServiceSecond.submitPublishGoods(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }

    /**
     * 发布商品
     * @return
     */
    public static Observable<ResponseInfo> editGoods(int productId,String providerId, String userId, String productName, String brandName, String isOnline, ArrayList<String> cateIds, int deliverGoogsId, String productPrice, String isHaveGoods, String isMade, List<String> loopImgs, String imgs, Map<String,String> intro, ArrayList<Map<String, Object>> priceList) {
        HashMap<String,Object> map  =new HashMap();
        map.put("productId",productId);
        if (!TextUtils.isEmpty(providerId)){
            if (!providerId.equals(States.NO_COMPANY)){
                map.put("providerId",providerId);
            }
        }
        map.put("userId",userId);
        map.put("productName",productName);
        map.put("brandName",brandName);
        map.put("isOnline",isOnline);
        for (int i = 0; i < cateIds.size(); i++) {
            switch (i){
                case 0:
                    map.put("firstCateId",cateIds.get(i));
                    break;
                case 1:
                    map.put("secondCateId",cateIds.get(i));
                    break;
                case 2:
                    map.put("thirdCateId",cateIds.get(i));
                    break;
                case 3:
                    map.put("fourthCateId",cateIds.get(i));
                    break;
            }
        }
        map.put("deliverGoogsId",deliverGoogsId);
        map.put("productPrice",productPrice);
        map.put("isHaveGoods",isHaveGoods);
        map.put("isMade",isMade);

//        for (int i = 0; i < loopImgs.size(); i++) {
//            switch (i){
//                case 0:
//                    map.put("loopImg001",loopImgs.get(i));
//                    break;
//                case 1:
//                    map.put("loopImg002",loopImgs.get(i));
//                    break;
//                case 2:
//                    map.put("loopImg003",loopImgs.get(i));
//                    break;
//                case 3:
//                    map.put("loopImg004",loopImgs.get(i));
//                    break;
//                case 4:
//                    map.put("loopImg005",loopImgs.get(i));
//                    break;
//            }
//        }

        for (int i = 0; i < 5; i++) {
            if (i<loopImgs.size()){
                switch (i){
                    case 0:
                        map.put("loopImg001",loopImgs.get(i));
                        break;
                    case 1:
                        map.put("loopImg002",loopImgs.get(i));
                        break;
                    case 2:
                        map.put("loopImg003",loopImgs.get(i));
                        break;
                    case 3:
                        map.put("loopImg004",loopImgs.get(i));
                        break;
                    case 4:
                        map.put("loopImg005",loopImgs.get(i));
                        break;
                }
            }else{
                switch (i){
                    case 0:
                        map.put("loopImg001","");
                        break;
                    case 1:
                        map.put("loopImg002","");
                        break;
                    case 2:
                        map.put("loopImg003","");
                        break;
                    case 3:
                        map.put("loopImg004","");
                        break;
                    case 4:
                        map.put("loopImg005","");
                        break;
                }
            }
        }
        map.put("intro",intro);
        map.put("imgs",imgs);
        if (productPrice.equals(Msg.PRICE_NO_FACE)){
            map.put("priceList",priceList);
        }
        Logger.e(map.toString());
        return  sServiceSecond.editGoods(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }

    /**
     * 提交采购意向
     * @return
     */
    public static Observable<GoodsInfo> getGoods(int userId, int compId, String isOnline, int pager) {
        HashMap<String,Object> map  =new HashMap();
        map.put("userId",userId);
        if (!(compId == 0 || compId == -1)){
            map.put("compId",compId);
        }
        map.put("isOnline",isOnline);
        return  sServiceSecond.getGoods(map,pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 删除商品
     * @return
     */
    public static Observable<ResponseInfo> deleteGoods(String ids) {
        return  sServiceSecond.deleteGoods(ids)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }

    /**
     * 上架/下架商品
     * @return
     */
    public static Observable<ResponseInfo> onLineGoods(String ids,String isOnline) {
        return  sServiceSecond.onLineGoods(ids,isOnline)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }

    /**
     * 获取商品详情
     * @return
     */
    public static Observable<GoodsDetailInfo> getGoodsDetail(int productId, String userId) {
        return  sServiceSecond.getGoodsDetail(productId,userId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<GoodsDetailInfo, Boolean>() {
                    @Override
                    public Boolean call(GoodsDetailInfo goodsDetailInfo) {
                        if (goodsDetailInfo.ret.equals(States.STATES_RESULT_OK)){
                            return true;
                        }else{
                            return false;
                        }
                    }
                });
    }


    /**
     * 商品详情-详情内容+图片
     * @return
     */
    public static Observable<GoodsDescInfo> getGoodsDesc(int productId) {
        return  sServiceSecond.getGoodsDesc(productId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<GoodsDescInfo, Boolean>() {
                    @Override
                    public Boolean call(GoodsDescInfo goodsDescInfo) {
                        if (goodsDescInfo.ret.equals("200")){
                            return true;
                        }else{
                            return false;
                        }

                    }
                });
    }

    /**
     * 获取收藏求购列表
     * @return
     */
    public static Observable<TalkOverInfo> getKeepList(int pager, String userId) {
        return  sServiceSecond.getKeepList(pager,userId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 获取收藏产品列表
     * @return
     */
    public static Observable<KeepGoodsInfo> getKeepGoodsList(int pager, String userId) {
        return  sServiceSecond.getKeepGoodsList(pager,userId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
    /**
     * 提交采购意向
     * @return
     */
    public static Observable<ResponseInfo> updateInfo(String headImg, String userName,String email) {
        HashMap<String,Object> map  =new HashMap();
        if (!TextUtils.isEmpty(headImg)){
            map.put("headImg",headImg);
        }
        if (!TextUtils.isEmpty(email)){
            map.put("email",email);
        }
        map.put("userName",userName);
        return  sServiceSecond.updateInfo(map,MyApp.sUserId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }


    /**
     * 获取用户基本信息
     * @return
     */
    public static Observable<UserBaseInfo> getUserBaseInfo(String userId) {
        return  sServiceSecond.getUserBaseInfo(userId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<UserBaseInfo, Boolean>() {
                    @Override
                    public Boolean call(UserBaseInfo userBaseInfo) {
                        if (userBaseInfo.ret.equals("200")){
                           return true;
                        }else{
                           return false;
                        }
                    }
                });
    }

    /**
     * 已洽谈
     * @return
     */
    public static Observable<TalkedInfo> getTalkOver(int pager, int userId, String loginType) {
        HashMap<String,Object> map  =new HashMap();
        map.put("userId",userId);
        map.put("loginType",loginType);
        return  sServiceSecond.getTalkOver(pager,map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取用户基本信息
     * @return
     */
    public static Observable<AttentionInfo> getAttentions(int pager, String userId) {
        return  sServiceSecond.getAttentions(pager,userId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 公司信息
     * @return
     */
    public static Observable<CompanyInfo> getCompanyInfo(String compId, String userId) {
        return  sServiceSecond.getCompanyInfo(compId,userId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<CompanyInfo, Boolean>() {
                    @Override
                    public Boolean call(CompanyInfo companyInfo) {
                        if (companyInfo.ret.equals("200")){
                            return true;
                        }else{
                            return false;
                        }
                    }
                });
    }

    /**
     * 公司信息
     * @return
     */
    public static Observable<CompanyInfo> getCompanyInfo(String compId) {
        return  sServiceSecond.getCompanyInfo(compId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<CompanyInfo, Boolean>() {
                    @Override
                    public Boolean call(CompanyInfo companyInfo) {
                        if (companyInfo.ret.equals("200")){
                            return true;
                        }else{
                            return false;
                        }
                    }
                });
    }

    /**
     * 编辑公司
     * @return
     */
    public static Observable<ResponseInfo> editCompany(int compId, int userId, String compLogo, String compShortName, String mainIndustry, String provinceCode, String cityCode, String areaCode, List<String> mPics, String compNature, String iscust, String compIntro) {
        HashMap<String,Object> map  =new HashMap();
        map.put("compId",compId);
        map.put("userId",userId);
        if (!TextUtils.isEmpty(compLogo)){
            map.put("compLogo",compLogo);
        }
        map.put("compShortName",compShortName);
        map.put("mainIndustry",mainIndustry);
        map.put("provinceCode",provinceCode);
        map.put("cityCode",cityCode);
        map.put("areaCode",areaCode);
        map.put("compNature",compNature);
        map.put("iscust",iscust);

        for (int i = 0; i < mPics.size(); i++) {
            switch (i){
                case 0:
                    map.put("banner1",mPics.get(i));
                    break;
                case 1:
                    map.put("banner2",mPics.get(i));
                    break;
                case 2:
                    map.put("banner3",mPics.get(i));
                    break;
                case 3:
                    map.put("banner4",mPics.get(i));
                    break;
                case 4:
                    map.put("banner5",mPics.get(i));
                    break;
            }
        }
        if (!TextUtils.isEmpty(compIntro)){
            map.put("compIntro",compIntro);
        }
        Logger.e(map.toString());
        return  sServiceSecond.editCompany(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }
    /**
     * 设置界面获取版本，绑定
     * @return
     */
    public static Observable<SettingInfo> getSettingInfo() {
        return  sServiceSecond.getSettingInfo()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<SettingInfo, Boolean>() {
                    @Override
                    public Boolean call(SettingInfo settingInfo) {
                        if (settingInfo.ret.equals(States.STATES_RESULT_OK)){
                            return true;
                        }else{
                            return false;
                        }
                    }
                });
    }
    /**
     * 忘记密码
     * @return
     */
    public static Observable<ResponseInfo> forgetPwd( String phone, String password) {
        HashMap<String,Object> map  =new HashMap();
        map.put("phone",phone);
        map.put("password",password);
        return  sService.forgetPwd(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }
    /**
     * 修改手机号，密码
     * @return
     */
    public static Observable<ResponseInfo> update(int userId,String msg,String type) {
        HashMap<String,Object> map  =new HashMap();
        map.put("id",userId);
        switch (type){
            case Constant.PHONE:
                map.put("phone",msg);
                break;
            case Constant.PASSWORD:
                map.put("password",msg);
                map.put("type","0");
                break;
            case Constant.EMAIL:
                map.put("email",msg);
                break;
        }
        return  sService.update(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }

    /**
     * 邮箱验证码发送
     * @return
     */
    public static Observable<ResponseInfo> sendEmail(String email, String businessName) {
        HashMap<String,Object> map  =new HashMap();
        map.put("email",email);
        map.put("businessName",businessName);
        map.put("portal","IambuyerKorea");
        return  sService.sendEmail(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }
    /**
     * 邮箱验证码发送
     * @return
     */
    public static Observable<ResponseInfo> checkEmail(String email, String businessName, String emailCode) {
        HashMap<String,Object> map  =new HashMap();
        map.put("email",email);
        map.put("businessName",businessName);
        map.put("portal","IambuyerKorea");
        map.put("emailCode",emailCode);
        return  sService.checkEmail(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }

    /**
     * 公司详情 -- 热门供应
     * @return
     */
    public static Observable<CompanyInfoHomeHotInfo> getHotList(int compId, int pager) {
        HashMap<String,Object> map  =new HashMap();
        map.put("compId",compId);
        map.put("isOnline","1");
        return  sServiceSecond.getHotList(map,pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 采购列表
     * @return
     */
    public static Observable<PurchaseInfo> getPurchaseList(int userId, int compId, String listState, String isOver, int pager) {
        HashMap<String,Object> map  =new HashMap();
        map.put("userId",userId);
        if (compId != -1){
            map.put("compId",compId);
        }
        map.put("listState",listState);
        map.put("isOver",isOver);
        return  sServiceSecond.getPurchaseList(map,pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 我的
     * @return
     */
    public static Observable<MeInfo> getMeInfo() {
        return  sServiceSecond.getMeInfo(MyApp.sUserId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<MeInfo, Boolean>() {
                    @Override
                    public Boolean call(MeInfo meInfo) {
                        if (meInfo.ret.equals(States.STATES_RESULT_OK)){
                            return true;
                        }else {
                            ToastUtil.showShort(MyApp.getAppContext(),meInfo.msg);
                            return false;
                        }
                    }
                });
    }



    /**
     * 发布采购
     * @return
     */
    public static Observable<ResponseInfo> submitPurchase(int userId, int compId, String productListTitle, String endTime, String productListDetail,List stProduct) {
        HashMap<String,Object> map  =new HashMap();
        map.put("userId",userId);
        if (compId  != -1){
            map.put("compId",compId);
        }
        map.put("productListTitle",productListTitle);
        map.put("endTime",endTime);
        map.put("productListDetail",productListDetail);
        map.put("stProduct",stProduct);
        return  sServiceSecond.submitPurchase(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }

    /**
     * 删除采购
     * @return
     */
    public static Observable<ResponseInfo> deletePurchase(String productId) {
        return  sServiceSecond.deletePurchase(productId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }
    /**
     * 更新采购
     * @return
     */
    public static Observable<ResponseInfo> editPurchase(int userId, int compId,int productListId, String productListTitle, String endTime, String productListDetail,List stProduct) {
        HashMap<String,Object> map  =new HashMap();
        map.put("userId",userId);
        map.put("productListId",productListId);
        if (compId  != -1){
            map.put("compId",compId);
        }
        map.put("productListTitle",productListTitle);
        map.put("endTime",endTime);
        map.put("productListDetail",productListDetail);
        map.put("stProduct",stProduct);
        return  sServiceSecond.editPurchase(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }

    /**
     * 采购列表
     * @return
     */
    public static Observable<MyPurchaseInfo> getMyPurchase(int userId, int compId, String listState, String isOver, int pager) {
        HashMap<String,Object> map  =new HashMap();
        map.put("userId",userId);
        if (compId != -1){
            map.put("compId",compId);
        }
        map.put("listState",listState);
        map.put("isOver",isOver);
        return  sServiceSecond.getMyPurchase(map,pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 更新采购
     * @return
     */
    public static Observable<ResponseInfo> stopPurchase(int productListId,  String endTime) {
        HashMap<String,Object> map  =new HashMap();
        map.put("productListId",productListId);
        map.put("endTime",endTime);
        map.put("stopType","1");
        return  sServiceSecond.stopPurchase(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }

    /**
     * 更新采购
     * @return
     */
    public static Observable<ResponseInfo> stopPurchases(String ids,  String endTime) {
        HashMap<String,Object> map  =new HashMap();
        map.put("ids",ids);
        map.put("endTime",endTime);
        return  sServiceSecond.stopPurchases(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }



    /**
     * 精选好商页
     * @return
     */
    public static Observable<ChoiceSellerInfo> getChoiceSeller(@NonNull String compName, @NonNull String cityCode, int pager) {
        HashMap<String,Object> map  =new HashMap();
        if (compName !=null){
            map.put("compName",compName);
        }
        if (cityCode != null){
            map.put("cityCode",cityCode);
        }
        if (!TextUtils.isEmpty(MyApp.sUserId)){
            map.put("userId",new Integer(MyApp.sUserId));
        }
        return  sServiceSecond.getChoiceSeller(map,pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取热门
     * @return
     */
    public static Observable<HotInfo> getHot() {
        return  sServiceSecond.getHot()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 采购列表
     * @return
     */
    public static Observable<SearchInfo> getSearch(String productName, int pager) {
        HashMap<String,Object> map  =new HashMap();
        map.put("proName",productName);
        return  sServiceSecond.getSearch(map,pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 采购列表
     * @return
     */
    public static Observable<SearchInfo> getSearchByCode(String productName, int pager) {
        HashMap<String,Object> map  =new HashMap();
        if (!TextUtils.isEmpty(productName)){
            map.put("firstCateId",productName);
        }
        return  sServiceSecond.getSearch(map,pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 买手采购列表
     * @return
     */
    public static Observable<SearchInfo> getBuyerSearchByCode(String productName,int userId, int pager) {
        HashMap<String,Object> map  =new HashMap();
        if (!TextUtils.isEmpty(productName)){
            map.put("firstCateId",productName);
        }
        map.put("userId",userId);
        return  sServiceSecond.getSearch(map,pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 供应商  -- 采购
     * @return
     */
    public static Observable<PurchaseInfo> getPurchases(String dateType,String name, int pager) {
        HashMap<String,Object> map  =new HashMap();
        map.put("dateType",dateType);
        map.put("Name",name);
        return  sServiceSecond.getPurchases(map,pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
    /**
     * 供应商  -- 采购
     * @return
     */
    public static Observable<PurchaseInfo> getPurchases(String purchaseType,String dateType,String name, int pager) {
        HashMap<String,Object> map  =new HashMap();
        map.put("purchaseType",purchaseType);
        map.put("dateType",dateType);
        map.put("Name",name);
        Logger.e(map.toString());
        return  sServiceSecond.getPurchases(map,pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 采购商首页
     * @return
     */
    public static Observable<PurchaseHomeInfo> getPurchaseHomeData() {
        return  sServiceSecond.getPurchaseHomeData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
    /**
     * 采购商首页跳转的列表页
     * @return
     */
    public static Observable<SearchInfo> getGoodsList(String firstCateId, int pager) {
        HashMap<String,Object> map  =new HashMap();
        map.put("firstCateId",firstCateId);
        return  sServiceSecond.getGoodsList(map,pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
    /**
     * 首页精选好商条目
     * @return
     */
    public static Observable<ChoiceSellerInfo> getSellers(String inCode,int pager) {
        HashMap<String,Object> map  =new HashMap();
        map.put("inCode",inCode);
        if (!TextUtils.isEmpty(MyApp.sUserId)){
            map.put("userId",new Integer(MyApp.sUserId));
        }
        return  sServiceSecond.getChoiceSeller(map,pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 供应商首页
     * @return
     */
    public static Observable<SupplyHomeInfo> getSupplyHomeData() {
        return  sServiceSecond.getSupplyHomeData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 关注采购
     * @return
     */
    public static Observable<ResponseInfo> keepPurchase(int stProductListId,  int stCompId,int stUserId,int userId) {
        HashMap<String,Object> map  =new HashMap();
        map.put("stProductListId",stProductListId);
        if (stCompId != 0){
            map.put("stCompId",stCompId);
        }
        map.put("stUserId",stUserId);
        map.put("userId",userId);
        return  sServiceSecond.keepPurchase(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 取消关注采购
     * @return
     */
    public static Observable<ResponseInfo> notKeepPurchase(int stProductListId,int userId) {
        HashMap<String,Object> map  =new HashMap();
        map.put("stProductListId",stProductListId);
        map.put("userId",userId);
        return  sServiceSecond.notKeepPurchase(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
    /**
     * 关注产品
     * @return
     */
    public static Observable<ResponseInfo> keepGoods(int userId,  int compId,int productUserId,int productId) {
        HashMap<String,Object> map  =new HashMap();
        map.put("userId",userId);
        if (compId != 0){
            map.put("compId",compId);
        }
        map.put("productUserId",productUserId);
        map.put("productId",productId);
        return  sServiceSecond.keepGoods(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 取消关注产品
     * @return
     */
    public static Observable<ResponseInfo> notKeepGoods(int userId,int productId) {
        HashMap<String,Object> map  =new HashMap();
        map.put("userId",userId);
        map.put("productId",productId);
        return  sServiceSecond.notKeepGoods(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
    /**
     * 关注公司
     * @return
     */
    public static Observable<ResponseInfo> keepCompany(int userId,  int compId) {
        HashMap<String,Object> map  =new HashMap();
        map.put("userId",userId);
        map.put("compId",compId);
        return  sServiceSecond.keepCompany(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 取消关注公司
     * @return
     */
    public static Observable<ResponseInfo> notKeepCompany(int userId,int compId) {
        HashMap<String,Object> map  =new HashMap();
        map.put("userId",userId);
        map.put("compId",compId);
        return  sServiceSecond.notKeepCompany(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 聊天  -- 新求购
     * @return
     */
    public static Observable<NewPurchaseInfo> getChatPurchase(String loginType, int pager) {
        HashMap<String,Object> map  =new HashMap();
        map.put("loginType",loginType);
        return  sServiceSecond.getChatPurchase(map,pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 聊天  -- 对我感兴趣
     * @return
     */
    public static Observable<ChatAttentionInfo> getChatAttention(int userId, String loginType, int pager) {
        HashMap<String,Object> map  =new HashMap();
        map.put("loginType",loginType);
        map.put("userId",userId);
        return  sServiceSecond.getChatAttention(map,pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
    /**
     * 聊天  -- 看过我
     * @return
     */
    public static Observable<ChatLookedInfo> getChatLooked(int userId, String loginType, int pager) {
        HashMap<String,Object> map  =new HashMap();
        map.put("loginType",loginType);
        map.put("userId",userId);
        return  sServiceSecond.getChatLooked(map,pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 立即沟通
     * @return
     */
    public static Observable<ChatInfo> getChatInfo(int proid, int userid, int type) {
        HashMap<String,Object> map  =new HashMap();
        map.put("proid",proid);
        map.put("userid",userid);
        map.put("type",type);
        return  sServiceSecond.getChatInfo(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<ChatInfo, Boolean>() {
                    @Override
                    public Boolean call(ChatInfo chatInfo) {
                        if (chatInfo.ret.equals(States.STATES_RESULT_OK)){
                            return true;
                        }else{
                            return false;
                        }
                    }
                });
    }

    /**
     * 立即沟通
     * @return
     */
    public static Observable<RightNowInfo> getRightNow(int proid, int userid, int type) {
        HashMap<String,Object> map  =new HashMap();
        map.put("proid",proid);
        map.put("userid",userid);
        map.put("type",type);
        return  sServiceSecond.getRightNow(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<RightNowInfo, Boolean>() {
                    @Override
                    public Boolean call(RightNowInfo chatInfo) {
                        if (chatInfo.ret.equals(States.STATES_RESULT_OK)){
                            return true;
                        }else{
                            return false;
                        }
                    }
                });
    }

    /**
     *交换名片，微信
     */
    public static Observable<ResponseInfo> changed(int formUserId,int toUserId,String type,String state) {
        HashMap<String,Object> map  =new HashMap();
        map.put("formUserId",formUserId);
        map.put("toUserId",toUserId);
        map.put("type",type);
        map.put("state",state);
        return  sServiceSecond.changed(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }


    /**
     *是否交换名片，微信
     */
    public static Observable<ChatStatesInfo> haveChanged(int formUserId, int toUserId, String type) {
        HashMap<String,Object> map  =new HashMap();
        map.put("formUserId",formUserId);
        map.put("toUserId",toUserId);
        map.put("type",type);
        return  sServiceSecond.haveChanged(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<ChatStatesInfo, Boolean>() {
                    @Override
                    public Boolean call(ChatStatesInfo chatStatesInfo) {
                        if (chatStatesInfo.ret.equals(States.STATES_RESULT_OK)){
                            return true;
                        }else{
                            return false;
                        }
                    }
                });
    }

    /**
     * 采购项信息
     * @return
     */
    public static Observable<PurchaseItemInfo> getPurchaseItem(int productId) {
        return  sServiceSecond.getPurchaseItem(productId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<PurchaseItemInfo, Boolean>() {
                    @Override
                    public Boolean call(PurchaseItemInfo purchaseItemInfo) {
                        if (purchaseItemInfo.ret.equals(States.STATES_RESULT_OK)){
                            return true;
                        }else{
                            return false;
                        }
                    }
                });
    }

    /**
     * 聊天 发商品
     * @return
     */
    public static Observable<GoodsListInfo> getGoodsList(int userId, String productName, int pager) {
        HashMap<String,Object> map  =new HashMap();
        map.put("userId",userId);
        map.put("productName",productName);
        return  sServiceSecond.getChatGoodsList(map,pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 采购列表
     * @return
     */
    public static Observable<PurchaseInfo> getChatPurchases(int userId, int pager) {
        HashMap<String,Object> map  =new HashMap();
        map.put("userId",userId);
        return  sServiceSecond.getPurchaseList(map,pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 聊天列表
     * @return
     */
    public static Observable<TabChatInfo> getTabChatList(String loginType, int userId) {
        HashMap<String,Object> map  =new HashMap();
        map.put("loginType",loginType);
        map.put("userId",userId);
        return  sServiceSecond.getTabChatList(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 聊天列表
     * @return
     */
    public static Observable<ChatListInfo> getChatList() {
        return  sServiceSecond.getChatList(MyApp.sUserId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 消息通知
     * @return
     */
    public static Observable<NoticeInfo> getNotice(String userId, int pager) {
        return  sServiceSecond.getNotice(pager,userId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 我的采购意向
     * @return
     */
    public static Observable<MyIntentInfo> getMyIntent(String userId) {
        return  sServiceSecond.getMyIntent(userId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 我的喜欢
     * @return
     */
    public static Observable<LikeInfo> getLikeGoods(String userId, int pager) {
        return  sServiceSecond.getLikeGoods(userId,pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 我的关注
     * @return
     */
    public static Observable<AttentionKoInfo> getAttentions(String userId, int pager) {
        return  sServiceSecond.getAttentions(userId,pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 取消关注
     * @return
     */
    public static Observable<ResponseInfo> notFollow(int userId, int followUserId) {
        HashMap<String,Object> map  =new HashMap();
        map.put("userId",userId);
        map.put("followUserId",followUserId);
        return  sServiceSecond.notFollow(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }


    /**
     * 消息列表
     * @return
     */
    public static Observable<MsgInfo> getMsgs(String userId,int pager) {
        return  sServiceSecond.getMsgs(userId,pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 设为已读
     * @return
     */
    public static Observable<ResponseInfo> setRead(String ids) {
        return  sServiceSecond.setRead(ids)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }

    /**
     * 设为已读
     * @return
     */
    public static Observable<ResponseInfo> deleteMsg(String ids) {
        return  sServiceSecond.deleteMsg(ids)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }

    /**
     * 我的反馈列表
     * @return
     */
    public static Observable<MySuggestionInfo> getSuggestions(int userId,int pager) {
        HashMap<String,Object> map  =new HashMap();
        map.put("userId",userId);
        return  sServiceSecond.getSuggestions(map,pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }



    /**
     * 城市经理列表
     * @return
     */
    public static Observable<CityManagerInfo> getCityManagers(String userName, int pager) {
        HashMap<String,Object> map  =new HashMap();
        map.put("userName",userName);
        return  sServiceSecond.getCityManagers(map,pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 选择城市经理
     * @return
     */
    public static Observable<ResponseInfo> selectCityManager(String userId,int referUserid) {
        HashMap<String,Object> map  =new HashMap();
        map.put("referUserid",referUserid);
        return  sServiceSecond.selectCityManager(map,userId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }

    /**
     * 发布采购
     * @return
     */
    public static Observable<PublishPurchaseInfo> publishPurchase(String prodName, String prodClass, int prodNumber, double prodPrice, double prodHighPrice, int sampleProdNumer, String unit, String intro, List<String> pics, List<PurchaseDetailInfo.StProductParasBean> paramString, int prodid) {
        //List<String> pics,List paramString,int prodid
        HashMap<String,Object> map  =new HashMap();

        if (RetrofitService.isDebug){
            map.put("mobile","18611874490");
        }else {
            map.put("mobile",MyApp.sPhone);
        }
        //采购产品名称
        map.put("prodName",prodName);
        //所属品类
        map.put("prodClass",prodClass);
        //采购产品数量
        map.put("prodNumber",prodNumber);
        //价格上限
        map.put("prodHighPrice",prodHighPrice);
        //价格下限
        map.put("prodPrice",prodPrice);
        //样品数量
        map.put("sampleProdNumer",sampleProdNumer);
        //单位
        map.put("unit",unit);
        //采购补充说明
        map.put("intro",intro);

        if (prodid!=-1){
            //编辑时,删除多余的图片
            while (pics.size()<3){
                pics.add("delImg");
            }
        }
        //采购补充说明
//        if (pics.size()>0){
//            for (int i = 0; i < pics.size(); i++) {
//                map.put("prodImage"+(i+1),pics.get(i));
//            }
//        }
        if (pics.size()>0){
            map.put("imgs",pics.toString().replace(",","&&").replace("[","").replace("]","").replace(" ",""));
        }
        //采购补充说明
        if (paramString.size()>0){
            map.put("paramString",new Gson().toJson(paramString));
        }

        if (prodid!=-1){
            //有这个就是更新
            map.put("prodid",prodid);
        }
        Logger.e(map.toString());
        return  sServiceSecond.publishPurchase(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 我的采购列表
     * @return
     */
    public static Observable<MyPurchaseInfo> getMyPurchases(String dateType,String stateType,int pager) {
        HashMap<String,Object> map  =new HashMap();
        if (isDebug){
            MyApp.sPhone = "18611874490";
        }
        if (!TextUtils.isEmpty(dateType)){
            if (!dateType.equals("-1")){
                map.put("dateType",dateType);
            }
        }
        if (!TextUtils.isEmpty(stateType)){
            if (!stateType.equals("-1")){
                map.put("stateType",stateType);
            }
        }
        map.put("mobile",MyApp.sPhone);
        return  sServiceSecond.getMyPurchases(map,pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }



    /**
     * 采购详情
     * @return
     */
    public static Observable<PurchaseDetailInfo> getPurchaseDetail(int prodId) {
        HashMap<String,Object> map  =new HashMap();
        if (isDebug){
            MyApp.sPhone = "18611874490";
        }
        map.put("mobile",MyApp.sPhone);
        if (isDebug){
            prodId = 743;
        }
        map.put("prodId",prodId);
        return  sServiceSecond.getPurchaseDetail(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 删除采购
     * @return
     */
    public static Observable<ResponseInfo> deletePurchase(int productId) {
        return  sServiceSecond.deletePurchase(productId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }


    /**
     * 报价详情
     * @return
     */
    public static Observable<QuoteInfo> getQuote(String offerId) {
        return  sServiceSecond.getQuote(offerId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 对报价感兴趣
     * @return
     */
    public static Observable<ResponseInfo> setIntent(String offerId) {
        return  sServiceSecond.setIntent(offerId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }

    /**
     * 报价详情
     * @return
     */
    public static Observable<ResponseInfo> loginPC(String userId,String token) {
        HashMap<String,Object> map  =new HashMap();
        map.put("userId",userId);
        map.put("sysType","iambuyerKorea");
        map.put("token",token);
        return  sService.loginPC(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }

    /**
     * 订单列表
     * @return
     */
    public static Observable<OrderInfo> getOrders(String orderState,int pager) {
        if (TextUtils.isEmpty(orderState)){
            return  sServiceSecond.getOrders(MyApp.sUserId,pager)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread());
        }else {
            return  sServiceSecond.getOrders(MyApp.sUserId,pager,orderState)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }


    /**
     * 订单详情
     * @return
     */
    public static Observable<OrderDetailInfo> getOrderDetail(String orderNo) {
        HashMap<String,Object> map  =new HashMap();
        map.put("orderNo",orderNo);
        return  sServiceSecond.getOrderDetail(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 确认进货
     * @return
     */
    public static Observable<ResponseInfo> submitStock(int proId, int goodsId, int orderCount, BigDecimal orderUnitpri, BigDecimal orderPrice, int orderSimcount, BigDecimal orderSimunitpri, BigDecimal orderSimprice, String orderContent, BigDecimal moneyMap) {
        HashMap<String,Object> map  =new HashMap();
        map.put("proId",proId);
        map.put("userId",MyApp.sUserId);
        map.put("goodsId",goodsId);
        map.put("orderCount",orderCount);
        map.put("orderUnitpri",orderUnitpri);
        map.put("orderPrice",orderPrice);
        map.put("orderSimcount",orderSimcount);
        map.put("orderSimunitpri",orderSimunitpri);
        map.put("orderSimprice",orderSimprice);
        map.put("orderContent",orderContent);
        HashMap<String ,BigDecimal> money = new HashMap<>();
        money.put("money",moneyMap);
        map.put("stProductOrderAccount",money);
        return  sServiceSecond.submitStock(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }

    /**
     * 关注产品
     * @return
     */
    public static Observable<ResponseInfo> attentionGood(int productUserId, int productId) {
        HashMap<String,Object> map  =new HashMap();
        map.put("userId",MyApp.sUserId);
        map.put("productUserId",productUserId);
        map.put("productId",productId);
        return  sServiceSecond.attentionGood(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 取消关注产品
     * @return
     */
    public static Observable<ResponseInfo> noAttentionGood(int productId) {
        HashMap<String,Object> map  =new HashMap();
        map.put("userId",MyApp.sUserId);
        map.put("productId",productId);
        return  sServiceSecond.noAttentionGood(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }


    /**
     * 确认收货
     * @return
     */
    public static Observable<ResponseInfo> sureOrder(String offerId) {
        return  sServiceSecond.sureOrder(offerId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }

    /**
     * 取消订单
     * @return
     */
    public static Observable<ResponseInfo> cancelOrder(String offerId) {
        return  sServiceSecond.cancelOrder(offerId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }

    /**
     * 沟通过的商品
     * @return
     */
    public static Observable<ChatGoodInfo> getChatGood(int pager) {
        return  sServiceSecond.getChatGood(MyApp.sUserId,pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 关注买手
     * @return
     */
    public static Observable<ResponseInfo> attentionBuyer(int followUserId) {
        HashMap<String,Object> map  =new HashMap();
        map.put("userId",new Integer(MyApp.sUserId));
        map.put("followUserId",followUserId);
        return  sServiceSecond.attentionBuyer(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }


    /**
     * 取消关注买手
     * @return
     */
    public static Observable<ResponseInfo> noAttentionBuyer(int followUserId) {
        HashMap<String,Object> map  =new HashMap();
        map.put("userId",new Integer(MyApp.sUserId));
        map.put("followUserId",followUserId);
        return  sServiceSecond.noAttentionBuyer(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }


    /**
     * 买手信息
     * @param id
     * @return
     */
    public static Observable<InitInfo> getBuyerInfo(String id,String thisUserId) {
        return sServiceSecond.getInitStates(id,thisUserId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<InitInfo, Boolean>() {
                    @Override
                    public Boolean call(InitInfo initInfo) {
                        if (initInfo.ret.equals(States.STATES_RESULT_OK)){
                            return true;
                        }else{
                            ToastUtil.showShort(MyApp.getAppContext(),initInfo.msg);
                            return false;
                        }
                    }
                });
    }

    /**
     * 发现
     * @return
     */
    public static Observable<FindInfo> findGood(String productName,int pager) {
        HashMap<String,Object> map  =new HashMap();
        if (!TextUtils.isEmpty(productName)){
            map.put("firstCateId",productName);
        }
        return  sServiceSecond.findGood(map,pager)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 买手信息
     * @return
     */
    public static Observable<ChatMsgInfo> getChatMsg(String userId) {
        return sServiceThird.getChatMsg(userId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 买手信息
     * @return
     */
    public static Observable<ResponseInfo> deleteSuggestions(String ids) {
        String id = ids.replace(" ","");
        return sServiceSecond.deleteSuggestions(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(_flatMap());
    }


}

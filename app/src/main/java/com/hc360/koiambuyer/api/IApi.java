package com.hc360.koiambuyer.api;


import com.hc360.koiambuyer.api.bean.AddressInfo;
import com.hc360.koiambuyer.api.bean.AttentionInfo;
import com.hc360.koiambuyer.api.bean.ChatAttentionInfo;
import com.hc360.koiambuyer.api.bean.ChatInfo;
import com.hc360.koiambuyer.api.bean.ChatLookedInfo;
import com.hc360.koiambuyer.api.bean.ChatStatesInfo;
import com.hc360.koiambuyer.api.bean.ChildrenAccountInfo;
import com.hc360.koiambuyer.api.bean.ChoiceSellerInfo;
import com.hc360.koiambuyer.api.bean.ComAuthInfo;
import com.hc360.koiambuyer.api.bean.CompanyInfo;
import com.hc360.koiambuyer.api.bean.CompanyInfoHomeHotInfo;
import com.hc360.koiambuyer.api.bean.FindCompanyInfo;
import com.hc360.koiambuyer.api.bean.GoodsDescInfo;
import com.hc360.koiambuyer.api.bean.GoodsDetailInfo;
import com.hc360.koiambuyer.api.bean.GoodsInfo;
import com.hc360.koiambuyer.api.bean.GoodsListInfo;
import com.hc360.koiambuyer.api.bean.HotInfo;
import com.hc360.koiambuyer.api.bean.InitInfo;
import com.hc360.koiambuyer.api.bean.IntentInfo;
import com.hc360.koiambuyer.api.bean.KeepGoodsInfo;
import com.hc360.koiambuyer.api.bean.LoginInfo;
import com.hc360.koiambuyer.api.bean.MeInfo;
import com.hc360.koiambuyer.api.bean.MyIntentInfo;
import com.hc360.koiambuyer.api.bean.MyPurchaseInfo;
import com.hc360.koiambuyer.api.bean.NewPurchaseInfo;
import com.hc360.koiambuyer.api.bean.NoticeInfo;
import com.hc360.koiambuyer.api.bean.PostPicInfo;
import com.hc360.koiambuyer.api.bean.PurchaseDetailInfo;
import com.hc360.koiambuyer.api.bean.PurchaseHomeInfo;
import com.hc360.koiambuyer.api.bean.PurchaseInfo;
import com.hc360.koiambuyer.api.bean.PurchaseItemInfo;
import com.hc360.koiambuyer.api.bean.ResponseInfo;
import com.hc360.koiambuyer.api.bean.SearchInfo;
import com.hc360.koiambuyer.api.bean.SettingInfo;
import com.hc360.koiambuyer.api.bean.ShipAddressInfo;
import com.hc360.koiambuyer.api.bean.SupplyHomeInfo;
import com.hc360.koiambuyer.api.bean.TabChatInfo;
import com.hc360.koiambuyer.api.bean.TalkOverInfo;
import com.hc360.koiambuyer.api.bean.TalkedInfo;
import com.hc360.koiambuyer.api.bean.UserBaseInfo;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/8/28
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface IApi {

    //注册
    @POST("rest-sso/register")
    Observable<ResponseInfo> register(@Body HashMap<String, String> map);

    //发送验证码
    @POST("rest-sso/phoneSms")
    Observable<ResponseInfo> sendIdentify(@Body HashMap<String, String> map);

    //校验验证码
    @POST("rest-sso/phoneSmsVali")
    Observable<ResponseInfo> confirmIdentify(@Body HashMap<String, String> map);

    //密码登录
    @POST("rest-sso/login")
    Observable<LoginInfo> loginByPwd(@Body HashMap<String, String> map);

    //更新密码
    @POST("rest-sso/updateInfo")
    Observable<ResponseInfo> updatePwd(@Body HashMap<String, Object> map);

    //判断是否选择了身份
    @GET("rest-iambuyer/auth/init/{id}")
    Observable<InitInfo> getInitStates(@Path("id") String id);

    //上传图片
    @Multipart
    @POST("fileUpload")
    Observable<PostPicInfo> postPic(@PartMap() Map<String, RequestBody> file, @Query("systemName") String systemName);

    //查询公司
    @FormUrlEncoded
    @POST("rest-iambuyer/comp/{pager}/selectCompListPage")
    Observable<FindCompanyInfo> findCompany(@Field("compName") String compName, @Path("pager") int pager);

    //请求加入公司
    @POST("rest-iambuyer/comp/userApply")
    Observable<ResponseInfo> userApply(@Body HashMap<String, Integer> map);

    //创建新公司
    @POST("rest-iambuyer/comp/insert")
    Observable<ResponseInfo> makeCompany(@Body HashMap<String, String> map);

    //
    @POST("rest-iambuyer/auth/updateUser")
    Observable<ResponseInfo> submit(@Body HashMap<String, Object> map);

    //认证公司
    @POST("rest-iambuyer/comp/compAuth")
    Observable<ResponseInfo> compAuth(@Body HashMap<String, String> map);

    //公司审核 -- 详请
    @GET("rest-iambuyer/comp/compAuth/{id}")
    Observable<ComAuthInfo> getComAuthInfo(@Path("id") String id);

    //意见及反馈
    @POST("rest-iambuyer/user/userFeedback/insert")
    Observable<ResponseInfo> submitSuggestion(@Body HashMap<String, String> map);

    //获取发货地址列表
    @POST("rest-iambuyer/user/selectGoodsDeliverList")
    Observable<ShipAddressInfo> getAddresses(@Body HashMap<String, Integer> map);

    //设置为默认发货地址
    @POST("rest-iambuyer/user/goodsDeliver/update")
    Observable<ResponseInfo> setDefaultAddress(@Body HashMap<String, Object> map);

    //删除发货地址
    @GET("rest-iambuyer/user/goodsDeliver/delete/{id}")
    Observable<ResponseInfo> deleteAddress(@Path("id") int id);


    //新增发货地址
    @POST("rest-iambuyer/user/goodsDeliver/insert")
    Observable<ResponseInfo> addAddress(@Body HashMap<String, Object> map);

    //查询发货地址详情
    @GET("rest-iambuyer/user/selectGoodsDeliverOne/{id}")
    Observable<AddressInfo> getAddress(@Path("id") int id);

    //获取子账户列表
    @POST("rest-iambuyer/userApply/{pager}/selectListPage")
    Observable<ChildrenAccountInfo> getChildren(@Body HashMap<String, Object> map, @Path("pager") int pager);

    //子账户审核通过、审核拒绝
    @POST("rest-iambuyer/comp/userAuditedOrRefuse/{id}")
    Observable<ResponseInfo> askStates(@Body HashMap<String, Object> map, @Path("id") String ids);

    //采购意向列表
    @GET("rest-iambuyer/buy/{pager}/selectstProductIntentionListPage")
    Observable<IntentInfo> getIntents(@Path("pager") int pager);

    //提交采购意向
    @POST("rest-iambuyer/buy/insertProductIntention")
    Observable<ResponseInfo> submitIntents(@Body HashMap<String, Object> map);

    //发布商品
    @POST("rest-iambuyer/supply/spProduct/insert")
    Observable<ResponseInfo> submitPublishGoods(@Body HashMap<String, Object> map);

    //我的商品
    @POST("rest-iambuyer/supply/{pager}/selectspProductListPage")
    Observable<GoodsInfo> getGoods(@Body HashMap<String, Object> map, @Path("pager") int pager);

    //删除商品
    @GET("rest-iambuyer/supply/spProduct/delete/{ids}")
    Observable<ResponseInfo> deleteGoods(@Path("ids") String ids);

    //上架/下架商品
    @GET("rest-iambuyer/supply/spProduct/updateOrIsOnline/{ids}")
    Observable<ResponseInfo> onLineGoods(@Path("ids") String ids, @Query("isOnline") String isOnline);

    //商品主表详情
    @GET("rest-iambuyer/supply/spProduct/{productId}")
    Observable<GoodsDetailInfo> getGoodsDetail(@Path("productId") int productId, @Query("userId") String userId);

    //商品详情-详情内容+图片
    @GET("rest-iambuyer/supply/spProductIntro/{productId}")
    Observable<GoodsDescInfo> getGoodsDesc(@Path("productId") int productId);

    //获取收藏求购列表
    @GET("rest-iambuyer/user/{pager}/selectFollowPurcListPage/{userId}")
    Observable<TalkOverInfo> getKeepList(@Path("pager") int pager, @Path("userId") String userId);

    //获取收藏产品列表
    @GET("rest-iambuyer/user/{pager}/selectFollowProdListPage/{userId}")
    Observable<KeepGoodsInfo> getKeepGoodsList(@Path("pager") int pager, @Path("userId") String userId);

    //完善信息 -- 绑定QQ -- 微信 -- 切换身份  -- 换头像
    @POST("rest-iambuyer/auth/updateUser")
    Observable<ResponseInfo> updateInfo(@Body HashMap<String, Object> map);

    //获取用户基本信息
    @GET("rest-iambuyer/user/selectIndexUserInfo/{userId}")
    Observable<UserBaseInfo> getUserBaseInfo(@Path("userId") String userId);

    //已洽谈列表
    @POST("rest-iambuyer/user/{pager}/selectNegotiationPageList")
    Observable<TalkedInfo> getTalkOver(@Path("pager") int pager, @Body HashMap<String, Object> map);

    //关注公司
    @GET("rest-iambuyer/comp/{pager}/selectFollowCompListPage/{userId}")
    Observable<AttentionInfo> getAttentions(@Path("pager") int pager, @Path("userId") String userId);

    //公司信息
    @GET("rest-iambuyer/comp/compInfo/{compId}")
    Observable<CompanyInfo> getCompanyInfo(@Path("compId") String compId, @Query("userId") String userId);

    //公司信息
    @GET("rest-iambuyer/comp/compInfo/{compId}")
    Observable<CompanyInfo> getCompanyInfo(@Path("compId") String compId);
    //编辑公司
    @POST("rest-iambuyer/comp/update")
    Observable<ResponseInfo> editCompany(@Body HashMap<String, Object> map);

    //设置界面获取版本，绑定
    @GET("rest-iambuyer/user/iambuyerVersion")
    Observable<SettingInfo> getSettingInfo(@Query("userId") String userId);

    //忘记密码
    @POST("rest-sso/updatePassWordByPhone")
    Observable<ResponseInfo> forgetPwd(@Body HashMap<String, Object> map);

    //修改手机号，修改密码
    @POST("rest-sso/updateInfo")
    Observable<ResponseInfo> update(@Body HashMap<String, Object> map);

    //邮箱验证码发送
    @POST("rest-sso/sendEmailCode")
    Observable<ResponseInfo> sendEmail(@Body HashMap<String, Object> map);

    //邮箱验证码校验
    @POST("rest-sso/emailVali")
    Observable<ResponseInfo> checkEmail(@Body HashMap<String, Object> map);

    //我的商品
    @POST("rest-iambuyer/supply/{pager}/selectspProductListPage")
    Observable<CompanyInfoHomeHotInfo> getHotList(@Body HashMap<String, Object> map, @Path("pager") int pager);

    //采购列表
    @POST("rest-iambuyer/supply/{pager}/selectIndexListPage")
    Observable<PurchaseInfo> getPurchaseList(@Body HashMap<String, Object> map, @Path("pager") int pager);

    //我的
    @POST("rest-iambuyer/user/myUserDate")
    Observable<MeInfo> getMeInfo(@Body HashMap<String, Object> map);

    //采购详情
    @GET("rest-iambuyer/buy/stProduct/{productListId}")
    Observable<PurchaseDetailInfo> getPurchaseDetail(@Path("productListId") int productListId, @Query("userId") String userId);

    //发布采购
    @POST("rest-iambuyer/buy/stProduct/insert")
    Observable<ResponseInfo> submitPurchase(@Body HashMap<String, Object> map);

    //编辑采购
    @POST("rest-iambuyer/buy/stProduct/update")
    Observable<ResponseInfo> editPurchase(@Body HashMap<String, Object> map);

    //删除采购
    @GET("rest-iambuyer/buy/stProduct/delete/{productId}")
    Observable<ResponseInfo> deletePurchase(@Path("productId") String productId);

    //批量停止采购
    @POST("rest-iambuyer/buy/stProduct/updateStop")
    Observable<ResponseInfo> stopPurchases(@Body HashMap<String, Object> map);

    //我的采购
    @POST("rest-iambuyer/buy/{pager}/selectstProductListPage")
    Observable<MyPurchaseInfo> getMyPurchase(@Body HashMap<String, Object> map, @Path("pager") int pager);

    //编辑采购
    @POST("rest-iambuyer/buy/stProduct/update")
    Observable<ResponseInfo> stopPurchase(@Body HashMap<String, Object> map);

    //精选好商
    @POST("rest-iambuyer/buy/{pager}/compList")
    Observable<ChoiceSellerInfo> getChoiceSeller(@Body HashMap<String, Object> map, @Path("pager") int pager);

    //获取热门商品
    @GET("rest-iambuyer/buy/getKeys")
    Observable<HotInfo> getHot();

    //搜索
    @POST("rest-iambuyer/buy/{pager}/selectIndexspProductListPage")
    Observable<SearchInfo> getSearch(@Body HashMap<String, Object> map, @Path("pager") int pager);

    //供应商 -- 采购页
    @POST("rest-iambuyer/supply/{pager}/selectIndexListPage")
    Observable<PurchaseInfo> getPurchases(@Body HashMap<String, Object> map, @Path("pager") int pager);

    //获取热门商品
    @GET("rest-iambuyer/index/supply")
    Observable<PurchaseHomeInfo> getPurchaseHomeData();

    //搜索
    @POST("rest-iambuyer/buy/{pager}/selectIndexspProductListPage")
    Observable<SearchInfo> getGoodsList(@Body HashMap<String, Object> map, @Path("pager") int pager);


    //获取热门商品
    @GET("rest-iambuyer/index/buy")
    Observable<SupplyHomeInfo> getSupplyHomeData();

    //关注采购
    @POST("rest-iambuyer/user/followPurc")
    Observable<ResponseInfo> keepPurchase(@Body HashMap<String, Object> map);

    //取消关注采购
    @POST("rest-iambuyer/user/notFollowPurc")
    Observable<ResponseInfo> notKeepPurchase(@Body HashMap<String, Object> map);

    //关注产品
    @POST("rest-iambuyer/user/followProd")
    Observable<ResponseInfo> keepGoods(@Body HashMap<String, Object> map);

    //取消产品采购
    @POST("rest-iambuyer/user/notFollowProd")
    Observable<ResponseInfo> notKeepGoods(@Body HashMap<String, Object> map);

    //关注产品
    @POST("rest-iambuyer/comp/followComp")
    Observable<ResponseInfo> keepCompany(@Body HashMap<String, Object> map);

    //取消产品采购
    @POST("rest-iambuyer/comp/notFollowComp")
    Observable<ResponseInfo> notKeepCompany(@Body HashMap<String, Object> map);

    //聊天  --- 新采购
    @POST("rest-iambuyer/user/{pager}/selectNewPageList")
    Observable<NewPurchaseInfo> getChatPurchase(@Body HashMap<String, Object> map, @Path("pager") int pager);

    //聊天  --- 对我感兴趣
    @POST("rest-iambuyer/user/{pager}/selectInterestedListPage")
    Observable<ChatAttentionInfo> getChatAttention(@Body HashMap<String, Object> map, @Path("pager") int pager);

    //聊天  --- 看过我
    @POST("rest-iambuyer/user/{pager}/selectSeeMePageList")
    Observable<ChatLookedInfo> getChatLooked(@Body HashMap<String, Object> map, @Path("pager") int pager);

    //立即沟通
    @POST("rest-iambuyer/user/recordCount")
    Observable<ChatInfo> getChatInfo(@Body HashMap<String, Object> map);


    //获取采购项的详细信息
    @GET("rest-iambuyer/buy/stProductDetails/{productId}")
    Observable<PurchaseItemInfo> getPurchaseItem(@Path("productId") int productId);

    //聊天  发商品
    @POST("rest-iambuyer/buy/{pager}/selectIndexspProductListPage")
    Observable<GoodsListInfo> getChatGoodsList(@Body HashMap<String, Object> map, @Path("pager") int pager);

    //聊天列表
    @POST("rest-iambuyer/user/chatList")
    Observable<TabChatInfo> getTabChatList(@Body HashMap<String, Object> map);

    //提交交换名片，微信的状态
    @POST("rest-iambuyer/user/exchange")
    Observable<ResponseInfo> changed(@Body HashMap<String, Object> map);

    //查看交换名片，微信的状态
    @POST("rest-iambuyer/user/isExchange")
    Observable<ChatStatesInfo> haveChanged(@Body HashMap<String, Object> map);

    //消息通知
    @GET("rest-iambuyer/msg/{pager}/selectMsgListPage")
    Observable<NoticeInfo> getNotice(@Path("pager") int pager, @Query("userId") String userId);


    //我的采购意向
    @GET("rest-iambuyer/buy/selectMyStProductIntentionList/{userId}")
    Observable<MyIntentInfo> getMyIntent(@Path("userId") String userId);

    //查看交换名片，微信的状态
    @POST("rest-iambuyer/supply/spProduct/update")
    Observable<ResponseInfo> editGoods(@Body HashMap<String, Object> map);


}

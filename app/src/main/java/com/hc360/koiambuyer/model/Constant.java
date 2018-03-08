/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hc360.koiambuyer.model;



/**
 * 常用的参数
 */
public class Constant {

	/**
	 * 用户的信息
	 */
	public static final String _ID = "_id";
	public static final String _COM_ID = "_com_id";
	public static final String _PHONE = "_phone";
	public static final String _USER_NAME = "_user_name";
	//是卖家还是买家
	public static final String _LOGIN_TYPE = "_login_type";

	public static final String _LOCATION_CITY ="_location_city";
	//卖家
	public static final String SELLER = "0";
	//买家
	public static final String BUYER = "1";

	//没有公司，赋值默认
	public static final String DEFAULT_COM_ID = "-1";

	public static final String DEFAULT_LIST_STATE = "0";

	public static final String TYPE_COMPANY = "1";
	public static final String TYPE_PERSON = "0";

	//一天的毫秒值
	public static final long TIME_ONE_DAY = 86400000;

	//轮播图的时间
	public static final long BANNER_TIME = 2000;


	//首页跳转列表页
	public static final String JUMP_LIST = "0";
	//首页跳转网页
	public static final String JUMP_WEB = "1";

	public static final int REQUESTCODE_FROM_ACTIVITY = 1;
	public static final String IM_PWD = "JJCiambuyer";
	//FormTool中的标示
	public static final String IS_PHONE = "is_phone";
	public static final String IS_EMAIL = "is_email";

	public static final String IS_PHONE_AND_EMAIL = "is_phone_and_email";
	//发货地址，打开新建地址，编辑地址
	public static final int OPEN_EDIT_ADDRESS = 1001;


	//摄像头权限
	public static final int RC_CAMERA_AND_WIFI = 10001;
	public static final int RC_PHOTO = 100088;
	//打开摄像头的请求码
	public static final int OPEN_CAMERA = 10002;
	//打开相册的请求码
	public static final int OPEN_PHOTO = 1003;
	//裁剪照片的请求码
	public static final int CROP_PICTURE = 1004;

	//创建新公司 -- 设置公司简称的请求码
	public static final int OPEN_SET_COMPANY_SHORT_NAME = 1006;
	//创建新公司 -- 主营行业的请求码
	public static final int OPEN_MAIN_TRADE = 1007;
	//完善资料 -- 设置名字的请求码
	public static final int OPEN_SET_NAME = 1008;
	//完善资料 -- 公司信息的请求码
	public static final int OPEN_FIND_COMPANY = 1009;
	//创建新公司 -- 公司所在地的请求码
	public static final int OPEN_ADDRESS = 1010;
	//创建新公司 -- 定位地图的请求码
	public static final int OPEN_LOCATION = 1011;
	//解析城市区编码
	public static final int MSG_LOAD_SUCCESS = 1012;
	public static final int MSG_LOAD_FAILED = 1013;
	//发布商品 -- 分类
	public static final int OPEN_GOODS_CLASSIFY = 1014;
	//发布商品 -- 所属品牌
	public static final int OPEN_BRAND = 1015;
	//发布商品 -- 起批价格
	public static final int OPEN_PRICE = 1016;
	//发布商品 -- 发货地址
	public static final int OPEN_SHIP_ADDRESS = 1017;
	//发布商品 -- 商品描述
	public static final int OPEN_GOODS_DESC = 1018;
	//我的商品 -- 商品详情
	public static final int OPEN_GOODS_DETAIL = 1036;
	//个人资料  -- 修改姓名
	public static final int OPEN_SET_NAME_FROM_PERSONAL = 1019;
	//个人资料  -- 修改职位
	public static final int OPEN_SET_POSITION = 1020;
	//个人资料  -- 编辑公司
	public static final int OPEN_EDIT_COMPANY_HOME = 1021;
	//个人资料  -- 公司概况
	public static final int OPEN_COMPANY_BASIC_INFO = 1022;
	//公司信息  -- 简称
	public static final int OPEN_COMPANY_SHORT_NAME = 1023;
	//绑定邮箱
	public static final int OPEN_BIND_EMAIL = 1024;
	//编辑采购
	public static final int OPEN_EDIT_PURCHASE = 1025;
	//发采购单和采购项
	public static final int OPEN_PUR_PRO = 1026;
	/**
	 * 图片名称
	 */
	//头像名称
	public static final String HEAD_PIC = "100011.jpg";

	/**
	 * 更新信息 -- 类型判断  --绑定QQ ，微信，邮箱,换头像。。。
	 */
	//绑定QQ
	public static final String QQ = "qq";
	//绑定微信
	public static final String WX = "wx";
	//绑定邮箱
	public static final String EMAIL = "email";
	//换头像
	public static final String HEAD = "head";
	//换职位
	public static final String USER_POSITION = "user_position";
	//换名字
	public static final String USER_NAME = "user_name";
	//切换身份
	public static final String LOGIN_TYPE = "login_type";
	//修改手机号
	public static final String PHONE = "phone";
	//修改密码
	public static final String PASSWORD = "password";

	//Fragment的tag
	public static final String TYPE = "type";
	public static final String MODE = "mode";
	//修改手机
	public static final String CHANGE_PHONE = "change_phone";
	//修改密码
	public static final String CHANGE_PWD = "change_pwd";
	//忘记密码
	public static final String FORGET_PWD = "forget_pwd";

	public static final String BIND_EMAIL = "bind_email";

	public static final String BIND_EMAIL_IDENTIFY = "bind_email_identify";
	//意见和反馈
	public static final String SUGGESTION = "suggestion";
	//编辑发货地址
	public static final String EDIT_SHIP_ADDRESS = "edit_ship_address";
	//新建发货地址
	public static final String NEW_SHIP_ADDRESS = "new_ship_address";
	//关于我们
	public static final String ABOUT_US = "about_us";
	//注册设置密码
	public static final String SETTING_PWD = "setting_pwd";
	//第一次进入，没有返回按钮
	public static final String CHOOSE_STATES_FIRST = "choose_states_first";
	//切换身份，有返回按钮
	public static final String CHOOSE_STATES = "choose_states";
	//完善信息--修改姓名
	public static final String SET_NAME = "set_name";
	//完善信息--公司信息
	public static final String FIND_COMPANY = "find_company";
	//完善信息--申请加入成功
	public static final String SUBMIT_APPLY = "submit_apply";
	//主营行业
	public static final String MAIN_TRADE = "main_trade";
	//更新密码
	public static final String UPDATE_PWD = "update_pwd";
	//创建新公司 -- 公司/店铺简称
	public static final String SET_COMPANY_SHORT_NAME = "set_company_short_name";
	//创建新公司 -- 公司所在地
	public static final String COMPANY_ADDRESS = "company_address";
	//企业认证
	public static final String FILL_MSG = "fill_msg";
	//认证状态
	public static final String PROVE_STATES = "prove_states";
	//重新认证
	public static final String RE_FILL_MSG = "RE_FILL_MSG";
	//发布商品 -- 分类
	public static final String GOODS_CLASSIFY = "goods_classify";
	//发布商品 -- 所属品牌
	public static final String BRAND = "brand";
	//发布商品 -- 起批价格
	public static final String PRICE = "price";
	//采购意向
	public static final String BUY_INTENT = "buy_intent";
	public static final String GOODS_DESC = "goods_desc";
	//编辑产品详情
	public static final String EDIT_GOODS_DESC = "edit_goods_desc";
	//洽谈过
	public static final String TALK_OVER = "talk_over";
	//收藏求购
	public static final String KEEP = "keep";
	//个人资料  -  修改姓名
	public static final String SET_NAME_FROM_PERSONAL = "set_name_from_personal";
	//个人资料  -  修改职位
	public static final String SET_POSITION = "set_position";
	//个人资料  -  我的关注
	public static final String ATTENTION = "attention";
	//个人资料  -  公司概况
	public static final String COMPANY_BASIC_INFO = "company_basic_info";
	//公司信息 -  公司简称
	public static final String COMPANY_SHORT_NAME = "company_short_name";
	//设置 -  修改密码
	public static final String SET_PASSWORD_UPDATE = "set_password_update";
	// 登录 -- 忘记密码
	public static final String SET_PASSWORD_FORGET = "set_password_forget";
	// 发布采购  -- 编辑
	public static final String EDIT_PURCHASE = "edit_purchase";
	// 发布采购  -- 发布前编辑
	public static final String EDIT_PURCHASE_BEFORE = "edit_purchase_before";
	// 发布采购  -- 新增
	public static final String NEW_PURCHASE = "new_purchase";
	//采购商 -- 我的采购
	public static final String MY_PURCHASE = "my_purchase";
	//精选好商
	public static final String CHOICE_SELLER = "choice_seller";
	//首页跳转到列表页
	public static final String GOODS_LIST = "goods_list";
	//首页跳转到网页
	public static final String WEB = "web";
	//首页精选好商条目
	public static final String SELLER_FRAGMENT = "seller_fragment";
	//供应商首页tab条目
	public static final String PURCHASE_LIST = "purchase_list";

	public static final int LOOK_PURCHASE = 1;

	public static final int LOOK_GOODS = 0;
	public static final String CHANGE_WX = "1";
	public static final String CHANGE_CARD = "0";
	//发采购单和采购项
	public static final String PUBLISH = "publish";

	public static final String USER_IMG = "user_img";
	public static final String CHECK_STATE = "check_state";

	public static final int PAGER_HOME = 1;
	public static final int PAGER_GOODS = 2;
	public static final int PAGER_MESSAGE = 3;
	public static final int PAGER_ME = 4;
	public static final String  NOTICE = "notice";
	public static final String EDIT_BUYER_INTENT = "edit_buyer_intent";
	public static final String TEMP_ID = "temp_id";
	public static final String _IS_BOSS = "_is_boss";
	public static final String HAVE_BUY_INTENT = "have_buy_intent";
	public static final String COMPANY_SELECT_ADDRESS = "company_select_address";
	public static final String TO_GO_HOME = "to_go_home";
	public static final String FROM_FILL_INFO = "from_fill_info";
	public static final String MY_SUB_PURCHASE = "my_sub_purchase";
	public static final String NEW_GOODS_CLASSIFY = "new_goods_classify";
	public static final String _LOCATION_CITY_NAME = "_location_city_name";
	public static final String MAIN_TRADE_NEW = "main_trade_new";
	public static final String PROVE_SECOND = "prove_second";
	public static final String FIRST_LOGIN = "first_login";
	public static final String TOOLBAR_HEIGHT = "toolbar_height";
	public static final String COMPANY_HEIGHT = "company_height";
	public static final int CALL_PHONE = 10568;
	public static final int SPLASH_PERM = 10570;
	public static int LOCATION = 10561;
}

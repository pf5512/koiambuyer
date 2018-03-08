package com.hc360.koiambuyer.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 短信enum
 * 
 * @author qawine
 */
public enum SmsStautsEnum implements Serializable {
	//'0'-用户注册,'1'-短信登录,'2'-'忘记密码','3'-换绑手机,'4'-邮箱取回密码
	REG("注册验证码","0"),
	NOTELOGIN("短信登陆", "1"),
	FINDPASSWORD("忘记密码", "2"),
	UPDATE("更换手机号","3"),
    MAILE("邮箱取回密码","4"),
    FAILED("错误信息","5"),
	CSQA("认证公司","6"),
	ACCOUNTEMAIL("邮箱账号绑定","7"),//发邮件
	RESETEMAIL("重置邮箱","8");//发邮件
	/** 描述 */
	private String desc;
	/** 枚举值 */
	private String value;

	private SmsStautsEnum(String desc, String value) {
		this.desc = desc;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static SmsStautsEnum getEnum(String value) {
		SmsStautsEnum resultEnum = null;
		SmsStautsEnum[] enumAry = SmsStautsEnum.values();
		for (int i = 0; i < enumAry.length; i++) {
			if (enumAry[i].getValue().equals(value)) {
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}

	public static Map<String, Map<String, Object>> toMap() {
		SmsStautsEnum[] ary = SmsStautsEnum.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
		for (int num = 0; num < ary.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = String.valueOf(getEnum(ary[num].getValue()));
			map.put("value", String.valueOf(ary[num].getValue()));
			map.put("desc", ary[num].getDesc());
			enumMap.put(key, map);
		}
		return enumMap;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList() {
		SmsStautsEnum[] ary = SmsStautsEnum.values();
		List list = new ArrayList();
		for (int i = 0; i < ary.length; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("value", String.valueOf(ary[i].getValue()));
			map.put("desc", ary[i].getDesc());
			list.add(map);
		}
		return list;
	}

}

package com.hc360.koiambuyer.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
询盘类型枚举
 *
 * @author jjc
 */
public enum OrderStateTypeEnum {
	PENDING_DELIVERY("待发货","0"),
	DELIVER_GOODS("已发货","1"),
	EVALUATE("待评价","2"),
	SUCCESS("已完成","3"),
	cancel("已取消","4");

	;
	/** 描述 */
	private String desc;
	/** 枚举值 */
	private String value;

	private OrderStateTypeEnum(String desc, String value) {
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

	public static OrderStateTypeEnum getEnum(String value) {
		OrderStateTypeEnum resultEnum = null;
		OrderStateTypeEnum[] enumAry = OrderStateTypeEnum.values();
		for (int i = 0; i < enumAry.length; i++) {
			if (enumAry[i].getValue().equals(value)) {
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}

	public static Map<String, Map<String, Object>> toMap() {
		OrderStateTypeEnum[] ary = OrderStateTypeEnum.values();
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
		OrderStateTypeEnum[] ary = OrderStateTypeEnum.values();
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

package com.hc360.koiambuyer.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences的工具类
 */
public class SPUtils {

	public static final String KEY_USERNAME = "login_username";
	public static final String KEY_PASSWORD = "login_password";
	private static SharedPreferences sp;

	/**
	 * 保存boolean值信息
	 *
	 */
	public static void saveBoolean(Context context,String key,boolean value){
		if (sp==null) {
			sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		}
		sp.edit().putBoolean(key, value).commit();
		
	}
	
	/**
	 * 获取boolean值信息
	 *
	 */
	public static boolean getBoolean(Context context,String key,boolean defvalue){
		if (sp==null) {
			sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		}
		return sp.getBoolean(key, defvalue);
	}
	
	
	/**
	 * 保存String值信息
	 *
	 */
	public static void saveString(Context context,String key,String value){
		if (sp==null) {
			sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		}
		sp.edit().putString(key, value).commit();
	}
	/**
	 * 保存Long值信息
	 *
	 */
	public static void saveLong(Context context,String key,long value){
		if (sp==null) {
			sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		}
		sp.edit().putLong(key, value).commit();
	}
	/**
	 * 保存int值信息
	 *
	 */
	public static void saveInt(Context context,String key,int value){
		if (sp==null) {
			sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		}
		sp.edit().putInt(key, value).commit();
	}
	/**
	 * 获取int值信息
	 *
	 */
	public static int getInt(Context context,String key,int defvalue){
		if (sp==null) {
			sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		}
		return sp.getInt(key, defvalue);
	}
	/**
	 * 获取String值信息
	 *
	 */
	public static long getLong(Context context,String key,long defvalue){
		if (sp==null) {
			sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		}
		return sp.getLong(key, defvalue);
	}


	/**
	 * 获取String值信息
	 *
	 */
	public static String getString(Context context,String key,String defvalue){
		if (sp==null) {
			sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		}
		return sp.getString(key, defvalue);
	}

}

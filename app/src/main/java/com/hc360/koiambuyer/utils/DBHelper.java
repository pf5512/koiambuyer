package com.hc360.koiambuyer.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hc360.koiambuyer.api.bean.CityBean;
import com.hc360.koiambuyer.api.bean.ClassifyBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.hc360.koiambuyer.R.raw.city;
import static com.hc360.koiambuyer.R.raw.classify;
import static com.hc360.koiambuyer.R.raw.main_trade;


/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/10
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class DBHelper {

    /**
     * 复制主营行业数据库表，可以考虑放到子线程中进行操作
     * @param context
     */
    public static void writeMainTradeDB(Context context) {
        File file = new File(context.getFilesDir(),"main_trade.db");
        FileOutputStream fout = null;
        InputStream inputStream = null;
        if (!file.exists()){
            try {
                inputStream = context.getResources().openRawResource(main_trade);
                fout = new FileOutputStream(file);
                byte[] buffer = new byte[128];
                int len = 0;
                while ((len = inputStream.read(buffer)) != -1) {
                    fout.write(buffer, 0, len);
                }
                buffer = null;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fout != null) {
                    try {
                        fout.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 查询所有主营行业名称
     * @param context
     * @return
     */
    public static List<String> searchAllMainTrade(Context context){
        List<String> list = new ArrayList<>();
        File file = new File(context.getFilesDir(),"main_trade.db");
        SQLiteDatabase database = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY);
        String sql = "select NAME from sys_industry";
        //查询结果封装成游标
        //rawQuery 第一个参数 要执行查询的sql语句 如果有条件 条件的值要用? 替代
        //第二个参数 string数组 这个数组中保存了用来查询的条件的值 用来替代 sql中?的
        Cursor cursor = database.rawQuery(sql, null);
        //通过游标遍历结果集  moveToNext 只要不是最后一行 调用这个方法就会返回true
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("NAME"));
            list.add(name);
        }
        cursor.close();
        database.close();
        return list;
    }

    /**
     * 根据主营行业名称查询行业码
     * @param context
     * @param msg
     * @return
     */
    public static String searchMainTrade(Context context,String msg){
        File file = new File(context.getFilesDir(),"main_trade.db");
        String name = "";
        SQLiteDatabase database = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY);
        String sql = "select CODE from sys_industry where NAME = ?";
        String[] selectionArgs = {msg};
        //查询结果封装成游标
        //rawQuery 第一个参数 要执行查询的sql语句 如果有条件 条件的值要用? 替代
        //第二个参数 string数组 这个数组中保存了用来查询的条件的值 用来替代 sql中?的
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        //通过游标遍历结果集  moveToNext 只要不是最后一行 调用这个方法就会返回true
        while(cursor.moveToNext()){
            name = cursor.getString(cursor.getColumnIndex("CODE"));
        }
        cursor.close();
        database.close();
        return name;
    }


    /**
     * 复制主营行业数据库表，可以考虑放到子线程中进行操作
     * @param context
     */
    public static void writeClassifyDB(Context context) {
        File file = new File(context.getFilesDir(),"classify.db");
        FileOutputStream fout = null;
        InputStream inputStream = null;
        if (!file.exists()){
            try {
                inputStream = context.getResources().openRawResource(classify);
                fout = new FileOutputStream(file);
                byte[] buffer = new byte[128];
                int len = 0;
                while ((len = inputStream.read(buffer)) != -1) {
                    fout.write(buffer, 0, len);
                }
                buffer = null;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fout != null) {
                    try {
                        fout.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 查询分类的第一级类目
     * @param context
     * @return
     */
    public static List<ClassifyBean> searchFirstClassify(Context context){
        List<ClassifyBean> list = new ArrayList<>();
        File file = new File(context.getFilesDir(),"classify.db");
        SQLiteDatabase database = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY);
        String sql = "SELECT * FROM kor_sys_category WHERE LENGTH(CATE_ID) = 3";
        //查询结果封装成游标
        //rawQuery 第一个参数 要执行查询的sql语句 如果有条件 条件的值要用? 替代
        //第二个参数 string数组 这个数组中保存了用来查询的条件的值 用来替代 sql中?的
        Cursor cursor = database.rawQuery(sql, null);
        //通过游标遍历结果集  moveToNext 只要不是最后一行 调用这个方法就会返回true
        while(cursor.moveToNext()){
            String cateName = cursor.getString(cursor.getColumnIndex("CATE_NAME"));
            String cateId = cursor.getString(cursor.getColumnIndex("CATE_ID"));
            list.add(new ClassifyBean(cateName,cateId,""));
        }
        cursor.close();
        database.close();
        return list;
    }


    /**
     * 查询分类的下一级类目
     * @param context
     * @return
     */
    public static List<ClassifyBean> searchNextClassify(Context context,String msg){
        List<ClassifyBean> list = new ArrayList<>();
        File file = new File(context.getFilesDir(),"classify.db");
        SQLiteDatabase database = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY);
        String sql = "SELECT * FROM kor_sys_category WHERE  PARENT_CATE_ID = ?";
        String[] selectionArgs = {msg};
        //查询结果封装成游标
        //rawQuery 第一个参数 要执行查询的sql语句 如果有条件 条件的值要用? 替代
        //第二个参数 string数组 这个数组中保存了用来查询的条件的值 用来替代 sql中?的
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        //通过游标遍历结果集  moveToNext 只要不是最后一行 调用这个方法就会返回true
        while(cursor.moveToNext()){
            String cateName = cursor.getString(cursor.getColumnIndex("CATE_NAME"));
            String cateId = cursor.getString(cursor.getColumnIndex("CATE_ID"));
            String parentCateId = cursor.getString(cursor.getColumnIndex("PARENT_CATE_ID"));
            list.add(new ClassifyBean(cateName,cateId,parentCateId));
        }
        cursor.close();
        database.close();
        return list;
    }

    /**
     * 复制主营行业数据库表，可以考虑放到子线程中进行操作
     * @param context
     */
    public static void writeCityDB(Context context) {
        File file = new File(context.getFilesDir(),"city.db");
        FileOutputStream fout = null;
        InputStream inputStream = null;
        if (!file.exists()){
            try {
                inputStream = context.getResources().openRawResource(city);
                fout = new FileOutputStream(file);
                byte[] buffer = new byte[128];
                int len = 0;
                while ((len = inputStream.read(buffer)) != -1) {
                    fout.write(buffer, 0, len);
                }
                buffer = null;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fout != null) {
                    try {
                        fout.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    /**
     * 查询城市
     * @param context
     * @return
     */
    public static List<CityBean> searchCity(Context context){
        List<CityBean> list = new ArrayList<>();
        File file = new File(context.getFilesDir(),"city.db");
        SQLiteDatabase database = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY);
        String sql = "SELECT * FROM sys_city";
        //查询结果封装成游标
        //rawQuery 第一个参数 要执行查询的sql语句 如果有条件 条件的值要用? 替代
        //第二个参数 string数组 这个数组中保存了用来查询的条件的值 用来替代 sql中?的
        Cursor cursor = database.rawQuery(sql, null);
        //通过游标遍历结果集  moveToNext 只要不是最后一行 调用这个方法就会返回true
        while(cursor.moveToNext()){
            String code = cursor.getString(cursor.getColumnIndex("CODE"));
            String name = cursor.getString(cursor.getColumnIndex("NAME"));
            list.add(new CityBean(code,name));
        }
        cursor.close();
        database.close();
        return list;
    }
    /**
     * 查询城市
     * @param context
     * @return
     */
    public static String searchCityCode(Context context,String city){
        File file = new File(context.getFilesDir(),"city.db");
        SQLiteDatabase database = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY);
//        String sql = "SELECT CODE FROM sys_city WHERE NAME like '"+city+"_'";
        String sql = "select CODE from sys_city where NAME like ?";
        String[] selectionArgs = {city};
        //查询结果封装成游标
        //查询结果封装成游标
        //rawQuery 第一个参数 要执行查询的sql语句 如果有条件 条件的值要用? 替代
        //第二个参数 string数组 这个数组中保存了用来查询的条件的值 用来替代 sql中?的
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        //通过游标遍历结果集  moveToNext 只要不是最后一行 调用这个方法就会返回true
        String code = "";
        while(cursor.moveToNext()){
            code = cursor.getString(cursor.getColumnIndex("CODE"));
        }
        cursor.close();
        database.close();
        return code;
    }
}

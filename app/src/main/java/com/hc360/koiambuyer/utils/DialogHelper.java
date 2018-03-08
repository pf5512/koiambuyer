package com.hc360.koiambuyer.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.engine.LimitInputTextWatcher;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.myinterface.CalendarDialogListener;
import com.hc360.koiambuyer.myinterface.DialogChoosePositionListener;
import com.hc360.koiambuyer.myinterface.DialogClickListener;
import com.hc360.koiambuyer.myinterface.DialogInputClickListener;
import com.hc360.koiambuyer.myinterface.DialogPositiveClickListener;
import com.hc360.koiambuyer.myinterface.DialogPositiveClickWithMsgListener;
import com.hc360.koiambuyer.myinterface.EtLimitHelper;
import com.hc360.koiambuyer.myinterface.IdentifyListener;
import com.hc360.koiambuyer.view.MyApp;

import java.util.Calendar;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/27
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class DialogHelper {

    /**
     * 自定义dialog，带标题/内容/确定/取消按钮
     * @param activity
     * @param title
     * @param content
     * @param cancel
     * @param sure
     * @param listener
     * @return
     */
    public static AlertDialog showCustomNormalDialog(final Context activity, @Nullable String title, String content, @Nullable String cancel, @Nullable String sure, final DialogClickListener listener) {
        final AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(activity);
        final View dialogView = LayoutInflater.from(activity)
                .inflate(R.layout.dialog_normal,null);
        TextView tvTitle = (TextView) dialogView.findViewById(R.id.tv_title);
        TextView tvContent = (TextView) dialogView.findViewById(R.id.tv_content);
        TextView tvCancel = (TextView) dialogView.findViewById(R.id.tv_cancel);
        TextView tvSure = (TextView) dialogView.findViewById(R.id.tv_sure);
        if (!TextUtils.isEmpty(MyApp.sLoginType)){
            tvSure.setTextColor(activity.getResources().getColor(MyApp.sLoginType.equals(Constant.BUYER)?R.color.buyerColor:R.color.sellerColor));
        }
        if (TextUtils.isEmpty(title)){
            tvTitle.setText(activity.getResources().getString(R.string.dialog_default_title));
        }else{
            tvTitle.setText(title);
        }
        tvContent.setText(content);
        if (TextUtils.isEmpty(cancel)){
            tvCancel.setText(activity.getResources().getString(R.string.dialog_default_cancel));
        }else{
            tvCancel.setText(cancel);
        }
        if (TextUtils.isEmpty(sure)){
            tvSure.setText(activity.getResources().getString(R.string.dialog_default_sure));
        }else{
            tvSure.setText(sure);
        }
        customizeDialog.setView(dialogView);
        final AlertDialog show = customizeDialog.show();
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.negativeClick();
                show.dismiss();
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.positiveClick();
                show.dismiss();
            }
        });
        return show;
    }

    /**
     * 自定义dialog，带标题/内容/确定按钮
     * @param activity
     * @param title
     * @param content
     * @param sure
     * @param listener
     * @return
     */
    public static AlertDialog showCustomNoCancelDialog(final Context activity, @Nullable String title, String content, @Nullable String sure, @Nullable final DialogPositiveClickListener listener) {
        final AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(activity);
        final View dialogView = LayoutInflater.from(activity)
                .inflate(R.layout.dialog_no_cancel,null);
        TextView tvTitle = (TextView) dialogView.findViewById(R.id.tv_title);
        TextView tvContent = (TextView) dialogView.findViewById(R.id.tv_content);
        TextView tvSure = (TextView) dialogView.findViewById(R.id.tv_sure);
        if (!TextUtils.isEmpty(MyApp.sLoginType)){
            tvSure.setTextColor(activity.getResources().getColor(MyApp.sLoginType.equals(Constant.BUYER)?R.color.buyerColor:R.color.sellerColor));
        }
        if (TextUtils.isEmpty(title)){
            tvTitle.setText(activity.getResources().getString(R.string.dialog_default_title));
        }else{
            tvTitle.setText(title);
        }
        tvContent.setText(content);
        if (TextUtils.isEmpty(sure)){
            tvSure.setText(activity.getResources().getString(R.string.dialog_default_sure));
        }else{
            tvSure.setText(sure);
        }
        customizeDialog.setView(dialogView);
        final AlertDialog show = customizeDialog.show();
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.positiveClick();
                }
                show.dismiss();
            }
        });
        return show;
    }

    /**
     * 自定义dialog，带内容/确定/取消按钮
     * @param activity
     * @param content
     * @param cancel
     * @param sure
     * @param listener
     * @return
     */
    public static AlertDialog showCustomNoTitleDialog(final Context activity, String content, @Nullable String cancel, @Nullable String sure, final DialogClickListener listener) {
        final AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(activity);
        final View dialogView = LayoutInflater.from(activity)
                .inflate(R.layout.dialog_no_title,null);
        TextView tvContent = (TextView) dialogView.findViewById(R.id.tv_content);
        TextView tvCancel = (TextView) dialogView.findViewById(R.id.tv_cancel);
        TextView tvSure = (TextView) dialogView.findViewById(R.id.tv_sure);
        if (!TextUtils.isEmpty(MyApp.sLoginType)){
            tvSure.setTextColor(activity.getResources().getColor(MyApp.sLoginType.equals(Constant.BUYER)?R.color.buyerColor:R.color.sellerColor));
        }else{
            tvSure.setTextColor(activity.getResources().getColor(R.color.sellerColor));
        }
        tvContent.setText(content);
        if (TextUtils.isEmpty(cancel)){
            tvCancel.setText(activity.getResources().getString(R.string.dialog_default_cancel));
        }else{
            tvCancel.setText(cancel);
        }
        if (TextUtils.isEmpty(sure)){
            tvSure.setText(activity.getResources().getString(R.string.dialog_default_sure));
        }else{
            tvSure.setText(sure);
        }
        customizeDialog.setView(dialogView);
        final AlertDialog show = customizeDialog.show();
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.negativeClick();
                show.dismiss();
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.positiveClick();
                show.dismiss();
            }
        });
        return show;
    }
    /**
     * 自定义dialog，带内容/确定按钮
     * @param activity
     * @param content
     * @param sure
     * @param listener
     * @return
     */
    public static AlertDialog showCustomNoTitleSureDialog(final Context activity, String content, @Nullable String sure, @Nullable final DialogPositiveClickListener listener) {
        final AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(activity);
        final View dialogView = LayoutInflater.from(activity)
                .inflate(R.layout.dialog_no_title_sure,null);
        TextView tvContent = (TextView) dialogView.findViewById(R.id.tv_content);
        TextView tvSure = (TextView) dialogView.findViewById(R.id.tv_sure);
        if (!TextUtils.isEmpty(MyApp.sLoginType)){
            tvSure.setTextColor(activity.getResources().getColor(MyApp.sLoginType.equals(Constant.BUYER)?R.color.buyerColor:R.color.sellerColor));
        }
        tvContent.setText(content);

        if (TextUtils.isEmpty(sure)){
            tvSure.setText(activity.getResources().getString(R.string.dialog_default_sure));
        }else{
            tvSure.setText(sure);
        }
        customizeDialog.setView(dialogView);
        final AlertDialog show = customizeDialog.show();
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.positiveClick();
                }
                show.dismiss();
            }
        });
        return show;
    }

    /**
     * 自定义输入对话框
     * @param activity
     * @param title
     * @param content
     * @param etText
     * @param hint
     * @param msg
     * @param sure
     * @param cancel
     * @param emptyMsg
     * @param listener
     * @return
     */
    public static AlertDialog showCustomInputDialog(final Context activity, String title, @Nullable String content, @Nullable String etText, @Nullable  String hint, @Nullable String msg, @Nullable String sure, @Nullable String cancel, @Nullable final String emptyMsg, boolean canDismissOutside, @Nullable EtLimitHelper helper, @Nullable final DialogInputClickListener listener) {
        final AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(activity);
        final View dialogView = LayoutInflater.from(activity)
                .inflate(R.layout.dialog_input,null);
        TextView tvTitle = (TextView) dialogView.findViewById(R.id.tv_title);
        tvTitle.setText(title);
        TextView tvContent = (TextView) dialogView.findViewById(R.id.tv_content);
        if (TextUtils.isEmpty(content)){
            tvContent.setVisibility(View.GONE);
        }else{
            tvContent.setVisibility(View.VISIBLE);
            tvContent.setText(content);
        }
        final EditText etInput = (EditText) dialogView.findViewById(R.id.et_input);
        etInput.setBackgroundResource(R.drawable.shape_dialog_et_bg);
        if (!TextUtils.isEmpty(hint)){
            etInput.setHint(hint);
        }
        if (!TextUtils.isEmpty(etText)){
            etInput.setText(etText);
            etInput.setSelection(etText.length());
        }
        if (helper != null){
            helper.limitEt(etInput);
        }
        TextView tvMsg = (TextView) dialogView.findViewById(R.id.tv_msg);
        if (TextUtils.isEmpty(msg)){
            tvMsg.setVisibility(View.GONE);
        }else{
            tvMsg.setVisibility(View.VISIBLE);
            tvMsg.setText(msg);
        }
        TextView tvCancel = (TextView) dialogView.findViewById(R.id.tv_cancel);
        if (TextUtils.isEmpty(cancel)){
            tvCancel.setText(activity.getResources().getString(R.string.dialog_default_cancel));
        }else{
            tvCancel.setText(cancel);
        }
        TextView tvSure = (TextView) dialogView.findViewById(R.id.tv_sure);
        if (TextUtils.isEmpty(sure)){
            tvSure.setText(activity.getResources().getString(R.string.dialog_default_sure));
        }else{
            tvSure.setText(sure);
        }
        tvSure.setTextColor(activity.getResources().getColor((MyApp.sLoginType.equals(Constant.BUYER)?R.color.buyerColor:R.color.sellerColor)));
        customizeDialog.setView(dialogView);
        final AlertDialog show = customizeDialog.show();
        show.setCanceledOnTouchOutside(canDismissOutside);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.negativeClick();
                show.dismiss();
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = etInput.getText().toString().trim();
                if (TextUtils.isEmpty(trim)){
                    if (TextUtils.isEmpty(emptyMsg)){
                        ToastUtil.showShort(etInput.getContext(),activity.getResources().getString(R.string.input_text));
                    }else{
                        ToastUtil.showShort(etInput.getContext(),emptyMsg);
                    }
                }else{
                    listener.positiveClick(trim);
                    show.dismiss();
                }
            }
        });
        return show;
    }


    /**
     * 简单的对话框,只带确定按钮
     * @param context
     * @param msg
     */
    public static  void showDialog(Context context,String title,String positiveMsg, String msg, final DialogPositiveClickListener listener) {
        //将参数传递给服务器，并弹出对话框提醒用户
        //将参数传递给服务器的操作，字段：用户id，提现金额，提现银行卡
        android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(context);
        if (!TextUtils.isEmpty(title)){
            dialog.setTitle(title);
        }
        if (!TextUtils.isEmpty(msg)){
            dialog.setMessage(msg);
        }
        dialog.setPositiveButton(positiveMsg,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.positiveClick();
                        dialog.dismiss();
                    }
                });
        dialog.show();
    }

    /**
     * 简单的对话框,只带确定按钮
     * @param context
     * @param msg
     */
    public static AlertDialog showWithDialog(Context context, String title, String positiveMsg, String msg, final DialogPositiveClickListener listener) {
        //将参数传递给服务器，并弹出对话框提醒用户
        //将参数传递给服务器的操作，字段：用户id，提现金额，提现银行卡
        android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(context);
        if (!TextUtils.isEmpty(title)){
            dialog.setTitle(title);
        }
        if (!TextUtils.isEmpty(msg)){
            dialog.setMessage(msg);
        }
        dialog.setPositiveButton(positiveMsg,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.positiveClick();
                        dialog.dismiss();
                    }
                });
        AlertDialog show = dialog.show();
        return show;
    }
    /**
     * 简单的对话框,带确定/取消按钮
     * @param context
     * @param msg
     */
    public static  android.support.v7.app.AlertDialog.Builder showDialog(Context context,String title,String positiveMsg,String negativeMsg, String msg, final DialogClickListener listener) {
        //将参数传递给服务器，并弹出对话框提醒用户
        //将参数传递给服务器的操作，字段：用户id，提现金额，提现银行卡
        android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.setPositiveButton(positiveMsg,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.positiveClick();
                        dialog.dismiss();
                    }
                });
        dialog.setNegativeButton(negativeMsg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.negativeClick();
                dialog.dismiss();
            }
        });
        dialog.show();
        return dialog;
    }

    /**
     * 简单的对话框,带确定/取消按钮
     * @param context
     * @param msg
     */
    public static  android.support.v7.app.AlertDialog.Builder getDialog(Context context,String title,String positiveMsg,String negativeMsg, String msg, final DialogClickListener listener) {
        //将参数传递给服务器，并弹出对话框提醒用户
        //将参数传递给服务器的操作，字段：用户id，提现金额，提现银行卡
        android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.setPositiveButton(positiveMsg,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.positiveClick();
                        dialog.dismiss();
                    }
                });
        dialog.setNegativeButton(negativeMsg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.negativeClick();
                dialog.dismiss();
            }
        });
        return dialog;
    }

    /**
     * 输入的对话框，完善信息页面中使用
     */
    public static  void showInputDialog(Activity activity, String title,String hint, int num, final DialogPositiveClickWithMsgListener listener) {

        final EditText editText = new EditText(activity);
        editText.setBackgroundResource(R.drawable.shape_transparents);
        if (num != 0){
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(num)});
        }
        editText.setPadding(40,40,0,0);
        editText.setHint(hint);
        AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(activity);
        inputDialog.setTitle(title);
        inputDialog.setView(editText);
        inputDialog.setPositiveButton(activity.getResources().getString(R.string.submit),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.positiveClickWithMeg(editText.getText().toString());
                        dialog.dismiss();
                    }
                });
        inputDialog.setNegativeButton(activity.getResources().getString(R.string.dialog_default_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        inputDialog.show();
    }

    /**
     * 输入的对话框，完善信息页面中使用
     */
    public static  void showInputLimitDialog(Activity activity, String title,String hint, int num, final DialogPositiveClickWithMsgListener listener) {

        final EditText editText = new EditText(activity);
        editText.setBackgroundResource(R.drawable.shape_transparents);
        if (num != 0){
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(num)});
        }
        editText.setPadding(40,40,0,0);
        editText.addTextChangedListener(new LimitInputTextWatcher(editText));
        editText.setHint(hint);
        AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(activity);
        inputDialog.setTitle(title);
        inputDialog.setView(editText);
        inputDialog.setPositiveButton(activity.getResources().getString(R.string.submit),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.positiveClickWithMeg(editText.getText().toString());
                        dialog.dismiss();
                    }
                });
        inputDialog.setNegativeButton(activity.getResources().getString(R.string.dialog_default_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        inputDialog.show();
    }

    public static AlertDialog showIdentifyDialog(final Activity activity, final IdentifyListener listener) {
        final AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(activity);
        final View dialogView = LayoutInflater.from(activity)
                .inflate(R.layout.dialog_identify,null);
        final EditText et = (EditText) dialogView.findViewById(R.id.et_input);
        final ImageView iv = (ImageView) dialogView.findViewById(R.id.iv);
        TextView tv = (TextView) dialogView.findViewById(R.id.tv);
        Bitmap bitmap = CodeUtils.getInstance().createBitmap();

        iv.setImageBitmap(bitmap);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = CodeUtils.getInstance().createBitmap();
                iv.setImageBitmap(bitmap);
            }
        });
        customizeDialog.setView(dialogView);
        final AlertDialog show = customizeDialog.show();
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = et.getText().toString().trim();
                if (text.equalsIgnoreCase(CodeUtils.getInstance().getCode())){
                    listener.onCodeCorrect();
                    show.dismiss();
                }else{
                    et.startAnimation(AnimTool.shakeAnimation(5));
                }
            }
        });
        return show;
    }

    public static  void showCustomizeDialog(final Activity activity, String title) {
        AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(activity);
        final View dialogView = LayoutInflater.from(activity)
                .inflate(R.layout.dialog_custom,null);
        customizeDialog.setTitle(title);
        customizeDialog.setView(dialogView);
        customizeDialog.setPositiveButton(activity.getResources().getString(R.string.dialog_default_sure),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 获取EditView中的输入内容
                    }
                });
        customizeDialog.show();
    }

    /**
     * 日期选择器
     * @param activity
     * @param listener
     */
    public static void showCalendarDialog(Activity activity, final CalendarDialogListener listener) {
        //弹出日历
        Calendar c = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                StringBuilder append = new StringBuilder()
                        .append(year)
                        .append("-")
                        .append((month + 1) < 10 ? "0" + (month + 1) : (month + 1))
                        .append("-")
                        .append((dayOfMonth < 10) ? "0" + dayOfMonth : dayOfMonth);
                listener.selectDay(append.toString());
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    public static void showCalendarWithoutMaxDateDialog(Activity activity, final CalendarDialogListener listener) {
        //弹出日历
        Calendar c = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                StringBuilder append = new StringBuilder()
                        .append(year)
                        .append("-")
                        .append((month + 1) < 10 ? "0" + (month + 1) : (month + 1))
                        .append("-")
                        .append((dayOfMonth < 10) ? "0" + dayOfMonth : dayOfMonth);
                listener.selectDay(append.toString());
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker();
        datePickerDialog.show();
    }
    public static void showCalendarEndTimeDialog(Activity activity, final CalendarDialogListener listener) {
        //弹出日历
        Calendar c = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                StringBuilder append = new StringBuilder()
                        .append(year)
                        .append("-")
                        .append((month + 1) < 10 ? "0" + (month + 1) : (month + 1))
                        .append("-")
                        .append((dayOfMonth < 10) ? "0" + dayOfMonth : dayOfMonth);
                listener.selectDay(append.toString());
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }
    public static void showSingleDialog(Activity activity, final String[] singleList, final DialogPositiveClickWithMsgListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(activity.getResources().getString(R.string.choose_account_type));
        builder.setSingleChoiceItems(singleList, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.positiveClickWithMeg(singleList[which]);
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public static void showSingleDialog(Activity activity, final String[] singleList, final DialogChoosePositionListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(activity.getResources().getString(R.string.choose_account_type));
        builder.setSingleChoiceItems(singleList, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.clickPosition(which);
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

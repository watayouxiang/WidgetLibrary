package com.dovar.dtoast;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;

/**
 * @Date: 2018/11/13
 * @Author: heweizong
 * @Description: 简单封装
 * <p>
 * come from github: https://github.com/Dovar66/DToast
 */
public class NBToast {
    public static void white_showShort(Context mContext, String msg) {
        if (TextUtils.isEmpty(msg)) return;
        DToast.make(mContext)
                .setText(R.id.tv_content_default, msg)
                .setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 200)
                .show();
    }

    public static void white_showLong(Context mContext, String msg) {
        if (TextUtils.isEmpty(msg)) return;
        DToast.make(mContext)
                .setText(R.id.tv_content_default, msg)
                .setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 200)
                .showLong();
    }

    public static void black_showShort(Context mContext, String msg) {
        if (TextUtils.isEmpty(msg)) return;
        DToast.make(mContext)
                .setView(View.inflate(mContext, R.layout.layout_toast_black, null))
                .setText(R.id.tv_content_custom, msg)
                .setGravity(Gravity.CENTER, 0, 0)
                .show();
    }

    public static void black_showLong(Context mContext, String msg) {
        if (TextUtils.isEmpty(msg)) return;
        DToast.make(mContext)
                .setView(View.inflate(mContext, R.layout.layout_toast_black, null))
                .setText(R.id.tv_content_custom, msg)
                .setGravity(Gravity.CENTER, 0, 0)
                .showLong();
    }

    /**
     * 退出APP时调用
     */
    public static void cancelAll() {
        DToast.cancel();
    }

    /**
     * 在 {@link Activity#onDestroy()} 中调用
     */
    public static void cancelActivityToast(Activity activity) {
        DToast.cancelActivityToast(activity);
    }
}

package com.dovar.dtoast;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;

/**
 * @Date: 2018/11/13
 * @Author: heweizong
 * @Description: 简单封装
 * <p>
 * github: https://github.com/Dovar66/DToast
 */
public class ToastUtil {
    /**
     * 较长显示
     */
    public static void showShort(Context mContext, String msg) {
        if (TextUtils.isEmpty(msg)) return;
        DToast.make(mContext)
                .setView(View.inflate(mContext, R.layout.layout_toast_black, null))
                .setText(R.id.tv_content_custom, msg)
                .setGravity(Gravity.CENTER, 0, 0)
                .show();
    }

    /**
     * 短暂显示
     */
    public static void showLong(Context mContext, String msg) {
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
}

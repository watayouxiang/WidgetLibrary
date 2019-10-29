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
public class NBToast {
    public static void white_showShort(Context mContext, String msg) {
        if (TextUtils.isEmpty(msg)) return;
        try {
            DToast.make(mContext)
                    .setText(R.id.tv_content_default, msg)
                    .setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 200)
                    .show();
        } catch (Exception ignored) {
        }
    }

    public static void white_showLong(Context mContext, String msg) {
        if (TextUtils.isEmpty(msg)) return;
        try {
            DToast.make(mContext)
                    .setText(R.id.tv_content_default, msg)
                    .setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 200)
                    .showLong();
        } catch (Exception ignored) {
        }
    }

    public static void black_showShort(Context mContext, String msg) {
        if (TextUtils.isEmpty(msg)) return;
        try {
            DToast.make(mContext)
                    .setView(View.inflate(mContext, R.layout.layout_toast_black, null))
                    .setText(R.id.tv_content_custom, msg)
                    .setGravity(Gravity.CENTER, 0, 0)
                    .show();
        } catch (Exception ignored) {
        }
    }

    public static void black_showLong(Context mContext, String msg) {
        if (TextUtils.isEmpty(msg)) return;
        try {
            DToast.make(mContext)
                    .setView(View.inflate(mContext, R.layout.layout_toast_black, null))
                    .setText(R.id.tv_content_custom, msg)
                    .setGravity(Gravity.CENTER, 0, 0)
                    .showLong();
        } catch (Exception ignored) {
        }
    }

    /**
     * 退出APP时调用
     */
    public static void cancelAll() {
        try {
            DToast.cancel();
        } catch (Exception ignored) {
        }
    }
}

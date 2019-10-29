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
 * 来源、说明文档: https://github.com/Dovar66/DToast
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
    public static void cancelAllToast() {
        DToast.cancel();
    }

    /**
     * 如果满足以下条件：
     * 1.你的应用设置的targetSdkVersion>=26.
     * 2.通知栏权限被关闭(通知栏权限默认都是打开的).
     * 3.非MIUI设备(MIUI弹吐司不需要通知栏权限).
     * 4.运行设备的系统版本在Android9.0及以上。
     * 会无法成功展示出弹窗(该场景下原生Toast也一样无法弹出)。
     * <p>
     * 解决办法：
     * 请在DToast.make(context)时传入Activity作为上下文，
     * 这样在该场景下DToast会启用ActivityToast展示出弹窗。
     * 当Context为Activity时，关闭Activity时，需要清除关联的ActivityToast，避免窗口泄漏。
     * 在 {@link Activity#onDestroy()} 中调用
     */
    public static void cancelActivityToast(Activity activity) {
        DToast.cancelActivityToast(activity);
    }
}

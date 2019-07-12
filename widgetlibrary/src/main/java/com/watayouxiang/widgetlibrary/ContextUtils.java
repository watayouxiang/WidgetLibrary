package com.watayouxiang.widgetlibrary;

import android.app.Application;
import android.content.Context;

public class ContextUtils {
    private static Context mApplicationContext;

    /**
     * 初始化context，如果由于不同机型导致反射获取context失败可以在Application调用此方法
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        mApplicationContext = context;
    }

    /**
     * 通过反射获取 applicationContext
     *
     * @return applicationContext
     */
    public static Context getContext() {
        if (mApplicationContext == null) {
            try {
                Application application = (Application) Class.forName("android.app.ActivityThread")
                        .getMethod("currentApplication").invoke(null, (Object[]) null);
                if (application != null) {
                    mApplicationContext = application;
                    return application;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Application application = (Application) Class.forName("android.app.AppGlobals")
                        .getMethod("getInitialApplication").invoke(null, (Object[]) null);
                if (application != null) {
                    mApplicationContext = application;
                    return application;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            throw new IllegalStateException("ContextUtils is not initialed, it is recommend to init with application context.");
        }
        return mApplicationContext;
    }
}

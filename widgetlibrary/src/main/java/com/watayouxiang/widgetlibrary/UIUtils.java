package com.watayouxiang.widgetlibrary;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

public class UIUtils {
    /**
     * 设置外边距
     *
     * @param view   view
     * @param margin 外边距
     */
    public static void setMargin(View view, Rect margin) {
        if (view == null || margin == null) return;
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) params;
            marginLayoutParams.setMargins(margin.left, margin.top, margin.right, margin.bottom);
        }
    }

    /**
     * dp-->px
     *
     * @param dp
     * @return
     */
    public static int dp2px(@NonNull Context context, int dp) {
        float d = context.getResources().getDisplayMetrics().density;
        return (int) (dp * d + 0.5);
    }
}

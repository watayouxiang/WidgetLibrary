package com.watayouxiang.widgetlibdemo;

import android.content.Context;
import android.view.View;

import com.dovar.dtoast.NBToast;
import com.watayouxiang.demoshell.ListActivity;
import com.watayouxiang.demoshell.ListData;

public class ToastActivity extends ListActivity {
    @Override
    protected ListData getListData() {
        final Context context = ToastActivity.this;
        return new ListData()
                .addClick("black_showShort", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NBToast.black_showShort(context, "这是一个不受限制的吐司");
                    }
                })
                .addClick("black_showLong", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NBToast.black_showLong(context, "这是一个不受限制的吐司");
                    }
                })
                .addClick("white_showShort", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NBToast.white_showShort(context, "这是一个不受限制的吐司");
                    }
                })
                .addClick("white_showLong", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NBToast.white_showLong(context, "这是一个不受限制的吐司");
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NBToast.cancelAllToast();
    }
}

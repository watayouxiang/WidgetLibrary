package com.watayouxiang.widgetlibdemo;

import android.view.View;

import com.dovar.dtoast.ToastUtil;
import com.watayouxiang.demoshell.ListActivity;
import com.watayouxiang.demoshell.ListData;

public class ToastActivity extends ListActivity {
    @Override
    protected ListData getListData() {
        return new ListData()
                .addClick("弹出ShortToast", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showShort(v.getContext().getApplicationContext(), "这是一个不受限制的吐司");
                    }
                })
                .addClick("弹出LongToast", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showLong(v.getContext().getApplicationContext(), "这是一个不受限制的吐司");
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ToastUtil.cancelAll();
    }
}

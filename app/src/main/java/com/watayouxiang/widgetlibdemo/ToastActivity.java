package com.watayouxiang.widgetlibdemo;

import android.view.View;

import com.dovar.dtoast.ToastUtil;
import com.watayouxiang.demoshell.ListActivity;
import com.watayouxiang.demoshell.ListData;

public class ToastActivity extends ListActivity {
    @Override
    protected ListData getListData() {
        return new ListData()
                .addClick("弹出默认Toast", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.show(v.getContext().getApplicationContext(), "这是一个不受限制的吐司");
                    }
                })
                .addClick("弹出自定义Toast", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showAtCenter(v.getContext().getApplicationContext(), "这是一个不受限制的吐司");
                    }
                });
    }
}

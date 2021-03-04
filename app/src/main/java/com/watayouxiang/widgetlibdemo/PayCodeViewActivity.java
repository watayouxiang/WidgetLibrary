package com.watayouxiang.widgetlibdemo;

import android.os.Bundle;
import android.view.View;

import com.dovar.dtoast.NBToast;
import com.watayouxiang.demoshell.DemoActivity;
import com.watayouxiang.demoshell.ListData;
import com.watayouxiang.widgetlibrary.PayCodeView;

public class PayCodeViewActivity extends DemoActivity {
    private PayCodeView smsCodeView;

    @Override
    protected int getHolderViewId() {
        return R.layout.view_paycode;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        smsCodeView = findViewById(R.id.smsCodeView);

        //设置监听
        smsCodeView.setCallback(new PayCodeView.Callback() {
            @Override
            public void onInputFinished(String code) {
                NBToast.black_showShort(PayCodeViewActivity.this, "验证码输入完成");
            }
        });
    }

    @Override
    protected ListData getListData() {
        return new ListData()
                .addClick("弹出键盘", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        smsCodeView.popKeyboard();
                    }
                })
                .addClick("获取验证码", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String code = smsCodeView.getCode();
                        NBToast.black_showShort(PayCodeViewActivity.this, "code = " + code);
                    }
                })
                ;
    }
}

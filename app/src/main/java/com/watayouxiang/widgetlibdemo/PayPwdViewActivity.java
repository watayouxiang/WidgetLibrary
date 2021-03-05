package com.watayouxiang.widgetlibdemo;

import android.os.Bundle;
import android.view.View;

import com.dovar.dtoast.NBToast;
import com.watayouxiang.demoshell.DemoActivity;
import com.watayouxiang.demoshell.ListData;
import com.watayouxiang.widgetlibrary.PayPwdView;

public class PayPwdViewActivity extends DemoActivity {
    private PayPwdView smsCodeView;

    @Override
    protected int getHolderViewId() {
        return R.layout.view_paypwd;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        smsCodeView = findViewById(R.id.smsCodeView);

        //设置监听
        smsCodeView.setCallback(new PayPwdView.Callback() {
            @Override
            public void onInputFinished(String code) {
                NBToast.black_showShort(PayPwdViewActivity.this, "code = " + code);
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
                        NBToast.black_showShort(PayPwdViewActivity.this, "code = " + code);
                    }
                })
                ;
    }
}

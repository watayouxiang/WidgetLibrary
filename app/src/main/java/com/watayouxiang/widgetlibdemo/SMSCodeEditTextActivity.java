package com.watayouxiang.widgetlibdemo;

import android.os.Bundle;
import android.view.View;

import com.dovar.dtoast.NBToast;
import com.watayouxiang.demoshell.DemoActivity;
import com.watayouxiang.demoshell.ListData;
import com.watayouxiang.widgetlibrary.SMSCodeEditText;

public class SMSCodeEditTextActivity extends DemoActivity {
    private SMSCodeEditText smsCodeView;

    @Override
    protected int getHolderViewId() {
        return R.layout.view_smscode_edittext;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        smsCodeView = findViewById(R.id.smsCodeView);

        //设置监听
        smsCodeView.setCallback(new SMSCodeEditText.Callback() {
            @Override
            public void onInputFinished(String code) {
                NBToast.black_showShort(SMSCodeEditTextActivity.this, "code = " + code);
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
                        NBToast.black_showShort(SMSCodeEditTextActivity.this, "code = " + code);
                    }
                })
                ;
    }
}

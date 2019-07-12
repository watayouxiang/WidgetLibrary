package com.watayouxiang.widgetlibdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.watayouxiang.demoshell.DemoActivity;
import com.watayouxiang.demoshell.ListData;
import com.watayouxiang.widgetlibrary.SMSCodeView;

public class SMSCodeViewActivity extends DemoActivity {
    private SMSCodeView smsCodeView;

    @Override
    protected int getHolderViewId() {
        return R.layout.view_smscode;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        smsCodeView = findViewById(R.id.smsCodeView);
        //设置监听
        smsCodeView.setCallback(new SMSCodeView.Callback() {
            @Override
            public void onInputFinished(String code) {
                Toast.makeText(SMSCodeViewActivity.this, "验证码输入完成", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickBtn() {
                Toast.makeText(SMSCodeViewActivity.this, "按钮被点击", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected ListData getListData() {
        return new ListData()
                .addClick("修改倒计时为10秒", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        smsCodeView.setCountDownTime(10);
                    }
                })
                .addClick("修改倒计时为60秒", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        smsCodeView.setCountDownTime(60);
                    }
                })
                .addClick("开始倒计时", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        smsCodeView.startCountDown();
                    }
                }).addClick("弹出键盘", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        smsCodeView.popKeyboard();
                    }
                })
                .addSection("逻辑：点击发送验证码btn --> 网络请求发送验证码 --> 验证码发送成功，开始倒计时 --> 用户输入验证码 --> 验证码输入完成，获得回调")
                .addSection("说明：样式固定不可修改，仅支持6位数字验证码输入。");
    }
}

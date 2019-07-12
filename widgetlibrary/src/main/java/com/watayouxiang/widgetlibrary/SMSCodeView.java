package com.watayouxiang.widgetlibrary;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;

public class SMSCodeView extends RelativeLayout {
    private LinkedList<String> mCodeList = new LinkedList<>();
    private ArrayList<EditText> mEtList = new ArrayList<>();
    private InputMethodManager mInputMethodManager;
    private Callback mCallback;
    private TextView tv_sendCode;
    private CountDownTimer mCountDownTimer;
    private boolean mCountDown = false;//是否正在倒计时
    private int mCountDownTime = 60;//倒计时时间（单位秒）

    public SMSCodeView(Context context) {
        this(context, null);
    }

    public SMSCodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SMSCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.view_edittext_smscode, this, true);
        //init EditTexts
        mEtList.add((EditText) findViewById(R.id.et_code1));
        mEtList.add((EditText) findViewById(R.id.et_code2));
        mEtList.add((EditText) findViewById(R.id.et_code3));
        mEtList.add((EditText) findViewById(R.id.et_code4));
        mEtList.add((EditText) findViewById(R.id.et_code5));
        mEtList.add((EditText) findViewById(R.id.et_code6));
        for (EditText editText : mEtList) {
            editText.addTextChangedListener(new InputTextWatcher(editText));
        }
        for (EditText editText : mEtList) {
            editText.setOnKeyListener(new InputTextKeyListener());
        }
        //init Btn
        tv_sendCode = findViewById(R.id.tv_sendCode);
        tv_sendCode.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startCountDown();
                if (mCallback != null) {
                    mCallback.onClickBtn();
                }
            }
        });
        //初始化配置
        EditText nextEditText = getNextEditText();
        for (EditText et : mEtList) {
            et.setEnabled(et == nextEditText);
        }
        tv_sendCode.setEnabled(true);
    }

    public interface Callback {
        /**
         * 验证码输入完成
         *
         * @param code 验证码
         */
        void onInputFinished(String code);

        /**
         * 按钮被点击
         */
        void onClickBtn();
    }

    private class InputTextWatcher implements TextWatcher {
        private final EditText mEditText;

        InputTextWatcher(EditText editText) {
            mEditText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String txt = s.toString();
            if (!TextUtils.isEmpty(txt)) {
                mCodeList.addLast(txt);
                //定位到下一个输入框
                EditText nextEditText = getNextEditText();
                if (nextEditText != null) {
                    positionEditText(nextEditText);
                } else {
                    if (mCallback != null) {
                        mCallback.onInputFinished(getCode());
                    }
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (TextUtils.isEmpty(s.toString())) {
                mEditText.setBackgroundResource(R.drawable.bg_reset_pwd_edittext_unselected);
            } else {
                mEditText.setBackgroundResource(R.drawable.bg_reset_pwd_edittext_selected);
            }
        }
    }

    private class InputTextKeyListener implements OnKeyListener {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                if (mCodeList.size() > 0) {
                    mCodeList.removeLast();
                    //清空下一输入框
                    //定位到下一个输入框
                    EditText nextEditText = getNextEditText();
                    if (nextEditText != null) {
                        nextEditText.setText("");
                        positionEditText(nextEditText);
                    }
                }
                return true;
            }
            return false;
        }
    }

    private void showKeyboard(final EditText editText) {
        editText.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mInputMethodManager == null) {
                    mInputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                }
                mInputMethodManager.showSoftInput(editText, 0);
            }
        }, 200);
    }

    private void positionEditText(EditText editText) {
        //设置可用
        editText.setEnabled(true);
        //设置获取焦点
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        editText.setSelection(editText.getText().length());
        //设置其他不可用
        for (EditText et : mEtList) {
            if (et != editText) {
                et.setEnabled(false);
            }
        }
    }

    private EditText getNextEditText() {
        EditText editText = null;
        if (mCodeList.size() == 0) {
            editText = mEtList.get(0);
        } else if (mCodeList.size() == 1) {
            editText = mEtList.get(1);
        } else if (mCodeList.size() == 2) {
            editText = mEtList.get(2);
        } else if (mCodeList.size() == 3) {
            editText = mEtList.get(3);
        } else if (mCodeList.size() == 4) {
            editText = mEtList.get(4);
        } else if (mCodeList.size() == 5) {
            editText = mEtList.get(5);
        }
        return editText;
    }

    private String getCode() {
        StringBuilder sb = new StringBuilder();
        for (String code : mCodeList) {
            sb.append(code);
        }
        return sb.toString();
    }

    @Override
    protected void onDetachedFromWindow() {
        if (mCodeList != null) {
            mCodeList.clear();
            mCodeList = null;
        }
        if (mEtList != null) {
            mEtList.clear();
            mEtList = null;
        }
        if (mInputMethodManager != null) {
            mInputMethodManager = null;
        }
        if (mCallback != null) {
            mCallback = null;
        }
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
        super.onDetachedFromWindow();
    }

    /**
     * 设置回调监听
     *
     * @param callback 监听器
     */
    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    /**
     * 设置倒计时时间，默认60秒
     *
     * @param second 秒
     */
    public void setCountDownTime(int second) {
        if (second > 0) {
            mCountDownTime = second;
        }
    }

    /**
     * 开始倒计时
     */
    public void startCountDown() {
        if (!mCountDown) {
            mCountDownTimer = new CountDownTimer(mCountDownTime * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    tv_sendCode.setText(String.format(Locale.getDefault(),
                            "重新发送（%ds）", millisUntilFinished / 1000));
                }

                @Override
                public void onFinish() {
                    tv_sendCode.setEnabled(true);
                    tv_sendCode.setTextColor(Color.parseColor("#F44236"));
                    tv_sendCode.setText("重新发送");
                    mCountDown = false;
                }
            }.start();
            mCountDown = true;
            tv_sendCode.setEnabled(false);
            tv_sendCode.setTextColor(Color.parseColor("#999999"));
        }
    }

    /**
     * 弹出键盘
     */
    public void popKeyboard() {
        EditText nextEditText = getNextEditText();
        if (nextEditText != null) {
            showKeyboard(nextEditText);
        }
    }
}

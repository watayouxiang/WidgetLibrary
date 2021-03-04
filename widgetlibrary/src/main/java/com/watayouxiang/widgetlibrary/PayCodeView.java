package com.watayouxiang.widgetlibrary;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class PayCodeView extends RelativeLayout {

    private final ArrayList<EditText> mEtList = new ArrayList<>();
    private Callback mCallback;

    public PayCodeView(Context context) {
        this(context, null);
    }

    public PayCodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PayCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.pay_code_view, this, true);

        // init EditTexts
        mEtList.add((EditText) findViewById(R.id.et_code1));
        mEtList.add((EditText) findViewById(R.id.et_code2));
        mEtList.add((EditText) findViewById(R.id.et_code3));
        mEtList.add((EditText) findViewById(R.id.et_code4));
        mEtList.add((EditText) findViewById(R.id.et_code5));
        mEtList.add((EditText) findViewById(R.id.et_code6));

        // 设置监听
        for (EditText editText : mEtList) {
            editText.addTextChangedListener(new InputTextWatcher(editText));
        }
        for (EditText editText : mEtList) {
            editText.setOnKeyListener(new InputTextKeyListener());
        }

        // 设置可用性
        for (int i = 0; i < mEtList.size(); i++) {
            mEtList.get(i).setEnabled(i == 0);
        }

        // 弹出键盘
        popKeyboard();
    }

    private void showKeyboard(final EditText editText) {
        editText.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText, 0);
            }
        }, 200);
    }

    public void popKeyboard() {
        for (int i = 0; i < mEtList.size(); i++) {
            EditText editText = mEtList.get(i);
            if (editText.isEnabled()) {
                positionEditText(editText);
                showKeyboard(editText);
                break;
            }
        }
    }

    private void positionEditText(EditText editText) {
        // 设置可用
        editText.setEnabled(true);
        // 设置获取焦点
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        editText.setSelection(editText.getText().length());
        // 设置其他不可用
        for (final EditText et : mEtList) {
            if (et != editText) {
                et.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                et.setEnabled(false);
            }
        }
    }

    public String getCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mEtList.size(); i++) {
            Editable text = mEtList.get(i).getText();
            if (!TextUtils.isEmpty(text)) {
                sb.append(text);
            } else {
                break;
            }
        }
        return sb.toString();
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    // ====================================================================================
    // interface
    // ====================================================================================

    public interface Callback {
        /**
         * 验证码输入完成
         *
         * @param code 验证码
         */
        void onInputFinished(String code);
    }

    private class InputTextWatcher implements TextWatcher {
        private final EditText editText;

        public InputTextWatcher(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String txt = s.toString();
            if (!TextUtils.isEmpty(txt)) {
                // 获取下一个空白输入框
                EditText nextEmptyEditText = null;
                for (int i = 0; i < mEtList.size(); i++) {
                    EditText editText = mEtList.get(i);
                    if (TextUtils.isEmpty(editText.getText())) {
                        nextEmptyEditText = editText;
                        break;
                    }
                }
                // 定位到下一个输入框
                if (nextEmptyEditText != null) {
                    positionEditText(nextEmptyEditText);
                } else {
                    if (mCallback != null) {
                        mCallback.onInputFinished(getCode());
                    }
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }

    private class InputTextKeyListener implements OnKeyListener {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            // 按下删除键
            if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {

                // 获取当前位置
                int currentPos = -1;
                int size = mEtList.size();
                for (int i = 0; i < size; i++) {
                    if (TextUtils.isEmpty(mEtList.get(i).getText()) || i == size - 1) {
                        currentPos = i;
                        break;
                    }
                }
                if (currentPos == -1) return false;

                // 如果当前位置有内容，就删除当前
                // 如果当前位置没有内容，删除前一个，并定位到前一个
                EditText currentEditText = mEtList.get(currentPos);
                if (!TextUtils.isEmpty(currentEditText.getText())) {
                    currentEditText.setText("");
                } else {
                    int prePos = currentPos - 1;
                    if (prePos >= 0) {
                        EditText preEditText = mEtList.get(prePos);
                        preEditText.setText("");
                        positionEditText(preEditText);
                    }
                }

                return true;
            }
            return false;
        }
    }
}

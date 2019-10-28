package com.watayouxiang.widgetlibrary;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;

public class IOSPhotoDialog {
    private Context mContext;
    private OnClickListener mTakePhotoListener;
    private OnClickListener mSelectPhotoListener;
    private boolean mCanceledOnTouchOutside;

    private IOSPhotoDialog(Context context,
                           OnClickListener takePhotoListener,
                           OnClickListener selectPhotoListener,
                           boolean canceledOnTouchOutside) {
        mContext = context;
        mTakePhotoListener = takePhotoListener;
        mSelectPhotoListener = selectPhotoListener;
        mCanceledOnTouchOutside = canceledOnTouchOutside;
    }

    public void show() {
        final AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
        //设置外部点击是否隐藏
        alertDialog.setCanceledOnTouchOutside(mCanceledOnTouchOutside);
        //设置自定义布局
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.ios_photo_dialog, null);
        rootView.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        rootView.findViewById(R.id.tv_takePhoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTakePhotoListener != null) {
                    mTakePhotoListener.onClick(alertDialog, OnClickListener.BUTTON_TAKE_PHOTO);
                }
            }
        });
        rootView.findViewById(R.id.tv_selectPhoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectPhotoListener != null) {
                    mSelectPhotoListener.onClick(alertDialog, OnClickListener.BUTTON_SELECT_PHOTO);
                }
            }
        });
        alertDialog.setView(rootView);
        alertDialog.show();
        //设置弹窗参数（注意：弹窗样式修改必须在 dialog.show() 方法后调用，否则不生效）
        Window window = alertDialog.getWindow();
        if (window != null) {
            window.setWindowAnimations(R.style.IOSPhotoDialog);
            window.setGravity(Gravity.BOTTOM);
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            //dialog即使没有设置四周的margin也有margin，可以设置给dialog设置个background来解决
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    // ============================================================================
    // Builder
    // ============================================================================

    public static class Builder {
        private OnClickListener mTakePhotoListener;
        private OnClickListener mSelectPhotoListener;
        private boolean mCanceledOnTouchOutside;
        private final Context mContext;

        public Builder setTakePhotoListener(OnClickListener l) {
            mTakePhotoListener = l;
            return this;
        }

        public Builder setSelectPhotoListener(OnClickListener l) {
            mSelectPhotoListener = l;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            mCanceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        public Builder(Context context) {
            this.mContext = context;
        }

        public IOSPhotoDialog build() {
            return new IOSPhotoDialog(mContext,
                    mTakePhotoListener,
                    mSelectPhotoListener,
                    mCanceledOnTouchOutside);
        }
    }

    // ============================================================================
    // Interface
    // ============================================================================

    public interface OnClickListener {
        int BUTTON_TAKE_PHOTO = -1;
        int BUTTON_SELECT_PHOTO = -2;

        void onClick(DialogInterface dialog, int which);
    }
}

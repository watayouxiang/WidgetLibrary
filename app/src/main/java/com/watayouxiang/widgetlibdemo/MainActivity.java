package com.watayouxiang.widgetlibdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

import com.watayouxiang.demoshell.ListActivity;
import com.watayouxiang.demoshell.ListData;
import com.watayouxiang.widgetlibdemo.tablayout.TabActivity;
import com.watayouxiang.widgetlibrary.IOSPhotoDialog;

public class MainActivity extends ListActivity {
    @Override
    protected boolean showBackBtn() {
        return false;
    }

    @Override
    protected CharSequence getPageTitle() {
        return getResources().getString(R.string.app_name);
    }

    @Override
    protected ListData getListData() {
        return new ListData()
                .addClick("仿iOS风格的相片弹窗", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showPhotoDialog(v.getContext());
                    }
                })
                .addActivity(this, "翻滚式公告", NoticeFlipperActivity.class)
                .addActivity(this, "短信验证码控件", SMSCodeViewActivity.class)
                .addActivity(this, "支付密码控件", PayCodeViewActivity.class)
                .addActivity(this, "Tab控件", TabActivity.class)
                .addActivity(this, "FadingEdge使用示例", FadingEdgeActivity.class)
                .addActivity(this, "测试StaticItem", StaticItemActivity.class)
                .addActivity(this, "测试Toast", ToastActivity.class);
    }

    private void showPhotoDialog(final Context context) {
        new IOSPhotoDialog.Builder(context)
                .setSelectPhotoListener(new IOSPhotoDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "选取照片", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                })
                .setTakePhotoListener(new IOSPhotoDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "拍照", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                })
                .setCanceledOnTouchOutside(true)
                .build()
                .show();
    }
}

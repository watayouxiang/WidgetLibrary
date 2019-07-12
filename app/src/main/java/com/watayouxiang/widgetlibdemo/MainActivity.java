package com.watayouxiang.widgetlibdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

import com.watayouxiang.demoshell.ListActivity;
import com.watayouxiang.demoshell.ListData;
import com.watayouxiang.widgetlibrary.IOSPhotoDialog;

public class MainActivity extends ListActivity {

    @Override
    protected ListData getListData() {
        return new ListData()
                .addClick("仿iOS风格的相片弹窗", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showPhotoDialog(v.getContext());
                    }
                })
                .addActivity(this, "翻滚式公告", NoticeFlipperActivity.class);
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
                .build()
                .show();
    }
}

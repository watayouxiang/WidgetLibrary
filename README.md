# WidgetLibrary

> Android 视图组件库

## 1、gradle引入

implementation 'com.watayouxiang:widgetlibrary:[版本号](https://dl.bintray.com/watayouxiang/maven/com/watayouxiang/widgetlibrary/)'

## 2、目前拥有的widget

### 仿iOS风格的相片弹窗：

```
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
```
# WidgetLibrary

> Android 视图组件库

## 1、gradle引入

implementation 'com.watayouxiang:widgetlibrary:[版本号](https://dl.bintray.com/watayouxiang/maven/com/watayouxiang/widgetlibrary/)'

## 2、包含的Widget

### 1）仿iOS风格的相片弹窗

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

### 2）翻滚式公告

> 优势：
> 
> 1. 内部实现复用机制，不论数据量多大，均可保持流畅。
> 2. 当界面不可见停止轮播，页面可见时恢复轮播。

```
<com.watayouxiang.widgetlibrary.NoticeFlipper
	android:id="@+id/noticeFlipper"
	android:layout_width="match_parent"
	android:layout_height="44dp" />

//翻滚式公告
NoticeFlipper flipper = findViewById(R.id.noticeFlipper);
//设置条目点击监听
flipper.setOnItemClickListener(new NoticeFlipper.OnItemClickListener() {
    @Override
    public void onNoticeClick(int position, TextView textView) {
        Toast.makeText(textView.getContext(), textView.getText(), Toast.LENGTH_SHORT).show();
    }
});
//设置数据并开始轮播
flipper.startWithList(Arrays.asList(
            "1. 重影问题可参考以下解决方案",
            "2. 心中有阳光，脚底有力量！",
            "3. 有兴趣的话点点关注，明早十点不见不散。"
    ));
```
# 一款可拖拽的LinearLayout/RelativeLayout

## 效果如下图
![image](https://github.com/wangpingtaohn/dragLayout/blob/master/img-storage/dragview.gif)

## 功能
1.可手动拖拽  
2.可调用hihe()方法隐藏  
3.可调用show()方法滑出  
4.支持LinearLayout、RelativeLayout两种布局  

## 使用方法
1.在project的 build.gradle文件里添加

```
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
  
```
2.在app的build.gralde文件里添加

```
dependencies {
	 implementation 'com.github.wangpingtaohn:dragLayout:1.0.1'
}
```

3.在相应的布局加入，如下

```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

    <Button
            android:layout_centerHorizontal="true"
            android:text="隐藏"
            android:layout_marginTop="10dp"
            android:id="@+id/btn_hide"
            android:layout_width="120dp"
            android:layout_height="50dp"/>

    <Button
            android:layout_centerHorizontal="true"
            android:text="展示"
            android:layout_marginTop="70dp"
            android:id="@+id/btn_show"
            android:layout_width="120dp"
            android:layout_height="50dp"/>


    <com.wpt.drag.DragLinearLayout
            android:id="@+id/drag_view"
            android:background="@drawable/bg_corner_12"
            android:layout_alignParentBottom="true"
            android:layout_width="match_parent"
            android:layout_height="wrap_content">
        <TextView
                android:layout_marginLeft="16dp"
                android:drawableLeft="@drawable/ic_publish_pic"
                android:text="图片"
                android:drawablePadding="10dp"
                android:gravity="center_vertical"
                android:layout_width="match_parent"
                android:layout_height="50dp"/>

        <View
                android:background="#ddd"
                android:layout_width="match_parent"
                android:layout_height="1dp"/>

        <TextView
                android:layout_marginLeft="16dp"
                android:drawableLeft="@drawable/ic_publish_pic"
                android:text="视频"
                android:drawablePadding="10dp"
                android:gravity="center_vertical"
                android:layout_width="match_parent"
                android:layout_height="50dp"/>
        <View android:layout_width="match_parent"
              android:background="#ddd"
              android:layout_height="1dp"/>
        <TextView
                android:layout_marginLeft="16dp"
                android:drawableLeft="@drawable/ic_publish_pic"
                android:text="文字"
                android:drawablePadding="10dp"
                android:gravity="center_vertical"
                android:layout_width="match_parent"
                android:layout_height="50dp"/>
        <View android:layout_width="match_parent"
              android:background="#ddd"
              android:layout_height="1dp"/>
        <TextView
                android:layout_marginLeft="16dp"
                android:drawableLeft="@drawable/ic_publish_pic"
                android:text="更多"
                android:drawablePadding="10dp"
                android:gravity="center_vertical"
                android:layout_width="match_parent"
                android:layout_height="50dp"/>

    </com.wpt.drag.DragLinearLayout>

</RelativeLayout>
```
代码效果如图  
![image](https://github.com/wangpingtaohn/dragLayout/blob/master/img-storage/dragview.png)  
4. Activity中
1.可调用show()、hide()方法

5.Demo地址：https://github.com/wangpingtaohn/dragViewDemo.git

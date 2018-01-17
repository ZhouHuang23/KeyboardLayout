# KeyboardLayout
##### 介绍
KeyboardLayout 用于监听软键盘的弹起和隐藏。
##### 使用
KeyboardLayout 使用简单，只需将其加入到布局文件即可，无其他使用限制，KeyboardLayout只是充当一个布局监听器的作用，即可实现对整个布局树进行监听。

- 布局文件

```java
<android.support.constraint.ConstraintLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    >
......
    <com.hz.android.keyboardlayout.KeyboardLayout
        android:id="@+id/keyboard_layout"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"

        />
......

</android.support.constraint.ConstraintLayout   

```
- 设置监听

```java
 KeyboardLayout keyboardLayout = (KeyboardLayout) findViewById(R.id.keyboard_layout);
keyboardLayout.setKeyboardLayoutListener(new KeyboardLayout.KeyboardLayoutListener() {
    @Override
    public void onKeyboardStateChanged(boolean isActive, int keyboardHeight) {
        if (isActive) { //软键盘弹起
            //do something
        } else { //软键盘隐藏
             //do something
        }
    }
});
```

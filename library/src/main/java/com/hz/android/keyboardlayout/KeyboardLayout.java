package com.hz.android.keyboardlayout;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * 自定义软键盘弹起/隐藏监听布局
 * Created by Administrator on 2018/1/15.
 */

public class KeyboardLayout extends FrameLayout {

    private boolean isKeyboardActive = false;//是否激活输入法
    private int keyboardHeight = 0;//输入法高度
    private KeyboardLayoutListener keyboardLayoutListener;

    public KeyboardLayout(Context context) {
        this(context, null, 0);
    }

    public KeyboardLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KeyboardLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //监听整个视图树布局变化
        getViewTreeObserver().addOnGlobalLayoutListener(new KeyboardOnGlobalLayoutListener()); //监听布局改变
    }

    private class KeyboardOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

        int screenHeight = 0;

        @Override
        public void onGlobalLayout() {
            //获取当前窗口的显示范围
            Rect rect = new Rect();
            ((Activity) getContext()).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);

            //计算得到弹出的高度
            int screenHeight = getScreenHeight();
            int popUpHeight = screenHeight - rect.bottom;

            boolean isActive = false;
            if (Math.abs(popUpHeight) > (screenHeight / 5)) { //弹起高度大于屏幕的1/5，即认为软件盘弹起
                isActive = true;
                keyboardHeight = popUpHeight;
            }
            isKeyboardActive = isActive;

            if (keyboardLayoutListener != null) { //通知外面
                keyboardLayoutListener.onKeyboardStateChanged(isKeyboardActive, keyboardHeight);
            }

        }

        private int getScreenHeight() {
            if (screenHeight > 0) {
                return screenHeight;
            }

            WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            screenHeight = windowManager.getDefaultDisplay().getHeight();
            return screenHeight;
        }
    }

    /**
     * @return 获取的输入法高度
     */
    public int getKeyboardHeight() {
        return keyboardHeight;
    }

    public void setKeyboardLayoutListener(KeyboardLayoutListener keyboardLayoutListener) {
        this.keyboardLayoutListener = keyboardLayoutListener;
    }

    public KeyboardLayoutListener getKeyboardLayoutListener() {
        return keyboardLayoutListener;
    }

    public boolean isKeyboardActive() {
        return isKeyboardActive;
    }

    public interface KeyboardLayoutListener {
        void onKeyboardStateChanged(boolean isActive, int keyboardHeight);
    }
}

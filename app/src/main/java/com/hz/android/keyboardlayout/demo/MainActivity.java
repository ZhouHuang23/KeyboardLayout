package com.hz.android.keyboardlayout.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hz.android.keyboardlayout.KeyboardLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        KeyboardLayout keyboardLayout = (KeyboardLayout) findViewById(R.id.keyboard_layout);
        keyboardLayout.setKeyboardLayoutListener(new KeyboardLayout.KeyboardLayoutListener() {
            @Override
            public void onKeyboardStateChanged(boolean isActive, int keyboardHeight) {
                if (isActive) { //软键盘弹起
                    Toast.makeText(MainActivity.this, "弹起，keyboardHeight =" + keyboardHeight, Toast.LENGTH_SHORT).show();
                } else { //软键盘隐藏
                    Toast.makeText(MainActivity.this, "隐藏" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

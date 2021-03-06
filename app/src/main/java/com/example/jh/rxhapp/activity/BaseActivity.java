package com.example.jh.rxhapp.activity;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.jh.rxhapp.R;

import java.lang.ref.SoftReference;
import java.lang.reflect.Field;

public abstract class BaseActivity extends AppCompatActivity {

    private FrameLayout mMainView;
    private Toolbar mToolbar;
    private TextView mTitle;

    public abstract int setMainView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initBaseView();
        initToobar();
        addMainView(setMainView());
        Object o = new Object();
        SoftReference<Object> softReference = new SoftReference<Object>(o);
        //如果设置 o=null，如果软引用没有被回收依然可以通过获取object对象
        Object o1 = softReference.get();
    }

    //浸入式状态栏
    private void initToobar() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        int statusBarHeight = getStatusBarHeight();
        mToolbar = (Toolbar) findViewById(R.id.base_toobar);
        mToolbar.setPadding(0, statusBarHeight, 0, 0);
        setSupportActionBar(mToolbar);
        mTitle = (TextView) findViewById(R.id.toobar_title);
        mTitle.setText("RXApp");
    }

    public void setToobarTitle(String title) {
        mTitle.setText(title);
    }

    private void initBaseView() {
        mMainView = (FrameLayout) findViewById(R.id.base_body);


    }


    protected void addMainView(View view) {
        mMainView.addView(view);
    }

    protected void addMainView(int id) {
        View main = View.inflate(this, id, null);
        mMainView.addView(main);
    }

    //获取状态栏的高度
    private int getStatusBarHeight() {
        Class<?> c = null;

        Object obj = null;

        Field field = null;

        int x = 0, sbar = 0;

        try {

            c = Class.forName("com.android.internal.R$dimen");

            obj = c.newInstance();

            field = c.getField("status_bar_height");

            x = Integer.parseInt(field.get(obj).toString());

            sbar = this.getResources().getDimensionPixelSize(x);

        } catch (Exception e1) {

            e1.printStackTrace();

        }

        return sbar;
    }




}

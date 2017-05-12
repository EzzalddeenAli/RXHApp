package com.example.jh.rxhapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.jh.rxhapp.activity.BaseActivity;
import com.example.jh.rxhapp.adapter.MainRecycleAdapter;
import com.example.jh.rxhapp.utils.PermissionUtils;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        addMainView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.main_recycle_view);
        mRecyclerView.setAdapter(new MainRecycleAdapter(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        PermissionUtils.checkPermission(this, PermissionUtils.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }

    }
}

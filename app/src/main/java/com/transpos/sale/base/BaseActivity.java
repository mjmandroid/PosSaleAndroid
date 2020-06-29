package com.transpos.sale.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.transpos.sale.utils.ToolScanner;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
        initData();
    }

    protected void initData() {

    }

    protected void initView() {

    }

    public void startActivity(Class<?> clazz){
        startActivity(new Intent(this,clazz));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        super.dispatchKeyEvent(event);
        ToolScanner scanner = getScanner();
        if(scanner != null)
            scanner.analysisKeyEvent(event);
        return true;
    }

    protected ToolScanner getScanner(){
        return null;
    }

    protected abstract int getLayoutId();
}

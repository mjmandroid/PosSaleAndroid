package com.transpos.sale.utils;

import android.annotation.SuppressLint;
import android.support.annotation.IdRes;
import android.widget.Toast;

import com.transpos.sale.base.BaseApp;

public class UiUtils {

    public static void showToastLong(String content){
        Toast.makeText(BaseApp.getApplication(),content,Toast.LENGTH_LONG).show();
    }

    @SuppressLint("ResourceType")
    public static void showToastLong(@IdRes int content){
        Toast.makeText(BaseApp.getApplication(),content,Toast.LENGTH_LONG).show();
    }

    public static void showToastShort(String content){
        Toast.makeText(BaseApp.getApplication(),content,Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("ResourceType")
    public static void showToastShort(@IdRes int content){
        Toast.makeText(BaseApp.getApplication(),content,Toast.LENGTH_SHORT).show();
    }
}

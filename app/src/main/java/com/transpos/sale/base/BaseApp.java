package com.transpos.sale.base;

import android.app.Application;
import android.content.Context;

public class BaseApp extends Application {

    private static Application mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Application getApplication(){
        return mContext;
    }
}

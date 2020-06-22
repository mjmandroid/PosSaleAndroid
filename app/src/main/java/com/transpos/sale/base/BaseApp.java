package com.transpos.sale.base;

import android.app.Application;
import android.content.Context;

import com.trans.network.HttpManger;
import com.transpos.sale.utils.KeyConstrant;
import com.transpos.tools.tputils.TPUtils;

public class BaseApp extends Application {

    private static Application mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        HttpManger.getSingleton().init(this);
    }

    public static Application getApplication(){
        return mContext;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
//        TPUtils.remove(this, KeyConstrant.KEY_WORKER);
    }
}

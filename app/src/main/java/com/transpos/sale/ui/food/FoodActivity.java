package com.transpos.sale.ui.food;

import android.content.Intent;
import android.view.View;

import com.transpos.sale.R;
import com.transpos.sale.base.BaseActivity;
import com.transpos.sale.base.mvp.BaseMvpActivity;
import com.transpos.sale.ui.food.mvp.FoodContract;
import com.transpos.sale.ui.food.mvp.FoodPrestener;
import com.transpos.sale.ui.navi.VipSelectActivity;
import com.transpos.sale.ui.set.SettingActivity;
import com.transpos.sale.view.dialog.LoadingDialog;

import butterknife.OnClick;

public class FoodActivity extends BaseMvpActivity<FoodPrestener> implements FoodContract.View {

    private LoadingDialog mLoadingDialog;
    private boolean isInit;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_food_home;
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!isInit){
            isInit = true;
            presenter.startDownload();
        }

    }

    @Override
    protected FoodPrestener createPresenter() {
        return new FoodPrestener();
    }

    @Override
    public void showLoading() {
        if(mLoadingDialog == null){
            mLoadingDialog = new LoadingDialog(this,"下载中...");
        }
        mLoadingDialog.show();
    }

    @Override
    public void dismissLoading() {
        if(mLoadingDialog != null){
            mLoadingDialog.dismiss();
        }
    }

    @OnClick({R.id.btn_start,R.id.btn_tips})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.btn_start:
                startActivity(new Intent(this, VipSelectActivity.class));
                break;
            case R.id.btn_tips:
                startActivity(new Intent(this, SettingActivity.class));
                break;
        }
    }

    @Override
    public void onError(Object tag, String errorMsg) {

    }
}

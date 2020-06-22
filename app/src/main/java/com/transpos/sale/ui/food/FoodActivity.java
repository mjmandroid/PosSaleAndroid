package com.transpos.sale.ui.food;

import com.transpos.sale.R;
import com.transpos.sale.base.BaseActivity;
import com.transpos.sale.base.mvp.BaseMvpActivity;
import com.transpos.sale.ui.food.mvp.FoodContract;
import com.transpos.sale.ui.food.mvp.FoodPrestener;

public class FoodActivity extends BaseMvpActivity<FoodPrestener> implements FoodContract.View {
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
        presenter.startDownload();
    }

    @Override
    protected FoodPrestener createPresenter() {
        return new FoodPrestener();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void onError(Object tag, String errorMsg) {

    }
}

package com.transpos.sale.ui.navi;

import android.view.View;

import com.transpos.sale.R;
import com.transpos.sale.base.BaseActivity;

import butterknife.OnClick;

public class VipSelectActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_navi_vip_select;
    }

    @OnClick({R.id.btn_vip,R.id.btn_not_vip})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.btn_vip:
                startActivity(VipLoginWaysActivity.class);
                break;
            case R.id.btn_not_vip:

                break;
        }
    }
}

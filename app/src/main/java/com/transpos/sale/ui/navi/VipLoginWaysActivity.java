package com.transpos.sale.ui.navi;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.LinearLayout;

import com.transpos.sale.R;
import com.transpos.sale.base.BaseActivity;
import com.transpos.sale.utils.UiUtils;
import com.transpos.sale.view.CustomGradient;

import butterknife.Bind;
import butterknife.OnClick;

public class VipLoginWaysActivity extends BaseActivity {

    @Bind(R.id.ll_back)
    LinearLayout ll_back;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_vip_login_ways;
    }


    @Override
    protected void initView() {
        ll_back.setBackground(new CustomGradient(GradientDrawable.Orientation.LEFT_RIGHT,
                new int[]{Color.parseColor("#FFB200"),Color.parseColor("#FF7B00")},
                UiUtils.dp2px(4,this)));
    }

    @OnClick({R.id.iv_phone,R.id.iv_vip,R.id.iv_face,R.id.ll_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.iv_phone:
                startActivity(PhoneLoginActivity.class);
                break;
            case R.id.iv_vip:

                break;
            case R.id.iv_face:

                break;
            case R.id.ll_back:

                break;
        }
    }
}

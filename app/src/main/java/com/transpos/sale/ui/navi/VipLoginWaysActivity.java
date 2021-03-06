package com.transpos.sale.ui.navi;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.transpos.sale.R;
import com.transpos.sale.base.BaseActivity;
import com.transpos.sale.ui.food.FoodActivity;
import com.transpos.sale.utils.CountTimer;
import com.transpos.sale.utils.UiUtils;
import com.transpos.sale.view.CustomGradient;

import butterknife.Bind;
import butterknife.OnClick;

public class VipLoginWaysActivity extends BaseActivity {

    @Bind(R.id.ll_back)
    LinearLayout ll_back;
    @Bind(R.id.tv_deal)
    TextView tv_deal;
    private CountTimer mCountTimer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_vip_login_ways;
    }


    @Override
    protected void initView() {
        ll_back.setBackground(new CustomGradient(GradientDrawable.Orientation.LEFT_RIGHT,
                new int[]{Color.parseColor("#FFB200"),Color.parseColor("#FF7B00")},
                UiUtils.dp2px(4,this)));
        tv_deal.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        super.initData();
        mCountTimer = new CountTimer(300*1000L,1000L,this,tv_deal);
        tv_deal.setText(getString(R.string.txt_actionbar_content,"300"));
        mCountTimer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mCountTimer != null){
            mCountTimer.cancel();
            mCountTimer = null;
        }

    }

    @OnClick({R.id.iv_phone,R.id.iv_vip,R.id.iv_face,R.id.ll_back,R.id.tv_deal})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.iv_phone:
                startActivity(PhoneLoginActivity.class);
                break;
            case R.id.iv_vip:
                startActivity(VipScanCodeLoginActivity.class);
                break;
            case R.id.iv_face:
                startActivity(FaceLoginActivity.class);
                break;
            case R.id.ll_back:
            case R.id.tv_deal:
                if(mCountTimer != null){
                    mCountTimer.cancel();
                    mCountTimer = null;
                }
                startActivity(FoodActivity.class);
                break;
        }
    }
}

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

public class VipScanCodeLoginActivity extends BaseActivity {

    private CountTimer mTimeCount;
    @Bind(R.id.tv_deal)
    TextView tv_deal;
    @Bind(R.id.ll_back)
    LinearLayout ll_back;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_navi_vip_scan_login;
    }

    @Override
    protected void initView() {
        super.initView();
        tv_deal.setVisibility(View.VISIBLE);
        ll_back.setBackground(new CustomGradient(GradientDrawable.Orientation.LEFT_RIGHT,
                new int[]{Color.parseColor("#FFB200"),Color.parseColor("#FF7B00")},
                UiUtils.dp2px(4,this)));
    }

    @Override
    protected void initData() {
        super.initData();
        String reslut = getString(R.string.txt_actionbar_content);
        tv_deal.setText(String.format(reslut,"300"));
        mTimeCount = new CountTimer(300*1000L,1000L,this,tv_deal);
        mTimeCount.start();
    }

    @OnClick({R.id.ll_back,R.id.tv_deal})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.ll_back:
            case R.id.tv_deal:
                if(mTimeCount != null){
                    mTimeCount.cancel();
                    mTimeCount = null;
                }
                startActivity(FoodActivity.class);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mTimeCount != null){
            mTimeCount.cancel();
            mTimeCount = null;
        }
    }
}

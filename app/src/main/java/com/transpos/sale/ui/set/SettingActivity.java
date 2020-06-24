package com.transpos.sale.ui.set;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.transpos.sale.R;
import com.transpos.sale.base.BaseActivity;
import com.transpos.sale.utils.UiUtils;
import com.transpos.sale.view.CustomGradient;
import com.transpos.sale.view.dialog.AuthSetDialog;

import butterknife.Bind;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @Bind(R.id.ll_set)
    LinearLayout ll_set;
    @Bind(R.id.ll_quit)
    LinearLayout ll_quit;
    @Bind(R.id.ll_back)
    LinearLayout ll_back;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        ll_set.setBackground(new CustomGradient(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{Color.parseColor("#0082FF"),Color.parseColor("#00BBFF")},
                UiUtils.dp2px(4,this)));
        ll_quit.setBackground(new CustomGradient(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{Color.parseColor("#0082FF"),Color.parseColor("#00BBFF")},
                UiUtils.dp2px(4,this)));
        ll_back.setBackground(new CustomGradient(GradientDrawable.Orientation.LEFT_RIGHT,
                new int[]{Color.parseColor("#FFB200"),Color.parseColor("#FF7B00")},
                UiUtils.dp2px(4,this)));
    }

    @OnClick({R.id.ll_set,R.id.ll_quit,R.id.ll_back})
    public void viewOnClick(View view){
        switch (view.getId()){
            case R.id.ll_set:

                break;
            case R.id.ll_quit:
                AuthSetDialog dialog = AuthSetDialog.newInstance();
                dialog.show(getSupportFragmentManager(),"auth");
                break;
            case R.id.ll_back:

                break;
        }
    }
}

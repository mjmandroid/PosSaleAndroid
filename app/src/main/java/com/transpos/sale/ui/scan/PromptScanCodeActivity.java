package com.transpos.sale.ui.scan;

import android.support.constraint.Group;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.transpos.sale.R;
import com.transpos.sale.base.BaseActivity;
import com.transpos.sale.base.mvp.BaseMvpActivity;
import com.transpos.sale.ui.navi.FaceLoginActivity;
import com.transpos.sale.ui.scan.mvp.PromptScanContract;
import com.transpos.sale.ui.scan.mvp.PromptScanPresenter;
import com.transpos.sale.utils.CountTimer;
import com.transpos.sale.utils.ToolScanner;

import org.apache.commons.lang3.StringUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class PromptScanCodeActivity extends BaseMvpActivity<PromptScanPresenter> implements ToolScanner.OnScanSuccessListener , PromptScanContract.View {
    private CountTimer mTimeCount;
    @Bind(R.id.tv_deal)
    TextView tv_deal;
    @Bind(R.id.group)
    Group mGroup;
    @Bind(R.id.layout_product)
    View layout_product;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_prompt_scan_code;
    }

    @Override
    protected void initView() {
        super.initView();
        mGroup.setVisibility(View.GONE);

    }


    @Override
    protected void initData() {
        super.initData();
        String reslut = getString(R.string.txt_actionbar_content);
        tv_deal.setVisibility(View.VISIBLE);
        tv_deal.setText(String.format(reslut,"300"));
        mTimeCount = new CountTimer(300*1000L,1000L,this,tv_deal);
        mTimeCount.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mTimeCount != null){
            mTimeCount.cancel();
            mTimeCount = null;
        }
    }

    private void start(){
        AnimationSet animationSet = new AnimationSet(false);
        ScaleAnimation animation = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        layout_product.startAnimation(animation);

    }

    @Override
    protected ToolScanner getScanner() {
        return new ToolScanner(this);
    }

    @OnClick({R.id.tv_deal})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.tv_deal:
                if(mTimeCount != null){
                    mTimeCount.cancel();
                    mTimeCount = null;
                }
                start();
//                startActivity(FaceLoginActivity.class);
                break;
        }
    }

    @Override
    public void onScanSuccess(String barcode) {
        if(StringUtils.isNoneBlank(barcode)){

        }
    }

    @Override
    protected PromptScanPresenter createPresenter() {
        return new PromptScanPresenter();
    }
}

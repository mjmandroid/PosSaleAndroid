package com.transpos.sale.ui.navi;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.transpos.sale.R;
import com.transpos.sale.base.BaseActivity;
import com.transpos.sale.ui.food.FoodActivity;
import com.transpos.sale.utils.CountTimer;
import com.transpos.sale.utils.KeyboardUtils;
import com.transpos.sale.utils.UiUtils;
import com.transpos.sale.view.ClearEditText;
import com.transpos.sale.view.CustomGradient;
import com.transpos.sale.view.PhoneVipKeyboardView;

import butterknife.Bind;
import butterknife.OnClick;

public class PhoneLoginActivity extends BaseActivity {

    @Bind(R.id.ll_back)
    LinearLayout ll_back;
    @Bind(R.id.et_phone)
    ClearEditText et_phone;
    @Bind(R.id.phoneKeyboardView)
    PhoneVipKeyboardView mKeyboardView;
    @Bind(R.id.tv_phone_number_error)
    TextView tv_phone_number_error;
    @Bind(R.id.tv_deal)
    TextView tv_deal;

    private String mPhoneNum;
    private CountTimer mTimeCount;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_phone_login;
    }


    @Override
    protected void initView() {
        KeyboardUtils.disableShowInput(et_phone);
        tv_deal.setVisibility(View.VISIBLE);
        ll_back.setBackground(new CustomGradient(GradientDrawable.Orientation.LEFT_RIGHT,
                new int[]{Color.parseColor("#FFB200"),Color.parseColor("#FF7B00")},
                UiUtils.dp2px(4,this)));
        mKeyboardView.setmKeyboardInputListener(new PhoneVipKeyboardView.OnKeyboardInputListener() {
            @Override
            public void onChanged(String content) {
                if(content.length() > 11){
                    return;
                }
                mPhoneNum = content;
                if(content.length() > 7){
                    String prefix = content.substring(0,3);
                    String suffix = content.substring(7,content.length());
                    et_phone.setText(prefix+"****" +suffix);
                } else if(content.length() > 3){
                    String prefix = content.substring(0,3);
                    int len = content.substring(3, content.length()).length();
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < len; i++) {
                        sb.append("*");
                    }
                    et_phone.setText(prefix+sb.toString());
                } else {
                    et_phone.setText(content);
                }
                et_phone.setSelection(mKeyboardView.getSelectPosition());
            }

            @Override
            public void onConfirm() {
                if(TextUtils.isEmpty(mPhoneNum) || mPhoneNum.length() != 11){
                    tv_phone_number_error.setVisibility(View.VISIBLE);
                } else {
                    tv_phone_number_error.setVisibility(View.INVISIBLE);
                }
            }
        });
        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(et_phone.getText().toString())){
                    mKeyboardView.setContent("",0);
                } else {
                    et_phone.setClearIconVisible(true);
                }
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mTimeCount = new CountTimer(300 * 1000L,1000L,this,tv_deal);
        String reslut = getString(R.string.txt_actionbar_content);
        tv_deal.setText(String.format(reslut,"300"));
        mTimeCount.start();
    }

    @OnClick({R.id.et_phone,R.id.ll_back,R.id.tv_deal})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.et_phone:
                mKeyboardView.setContent(mPhoneNum,et_phone.getSelectionStart());
                break;
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
    protected void onDestroy() {
        super.onDestroy();
        if(mTimeCount != null){
            mTimeCount.cancel();
            mTimeCount = null;
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

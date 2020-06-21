package com.transpos.sale.ui.register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.transpos.sale.R;
import com.transpos.sale.base.BaseActivity;
import com.transpos.sale.base.mvp.BaseMvpActivity;
import com.transpos.sale.entity.OpenResponse;
import com.transpos.sale.entity.RegistrationCode;
import com.transpos.sale.ui.register.mvp.ReginsterPresenter;
import com.transpos.sale.ui.register.mvp.RegisterContract;
import com.transpos.sale.utils.KeyConstrant;
import com.transpos.sale.utils.UiUtils;
import com.transpos.sale.view.RegisterKeyboardView;
import com.transpos.tools.tputils.TPUtils;

import butterknife.Bind;

public class RegisterActivity extends BaseMvpActivity<ReginsterPresenter> implements RegisterKeyboardView.OnInputContentListener, RegisterContract.View {

    @Bind(R.id.input_keyboard)
    RegisterKeyboardView mRegisterKeyboardView;
    @Bind(R.id.et_input)
    EditText mInput;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRegisterKeyboardView.setmOnInputContentListener(this);
    }

    @Override
    protected ReginsterPresenter createPresenter() {
        return new ReginsterPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void onChanged(String content) {
        mInput.setText(content.trim());
    }

    @Override
    public void onConfirm(String authCode) {
        doRegister(authCode);
    }

    private void doRegister(String authCode) {
        presenter.register(authCode);
    }

    @Override
    public void registerSuccess(OpenResponse<RegistrationCode> result) {
        TPUtils.put(this, KeyConstrant.KEY_AUTH_REGISTER,result.getData());
        UiUtils.showToastShort("恭喜，注册成功，可以开始正常使用了");
    }
}

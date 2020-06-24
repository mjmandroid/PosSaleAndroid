package com.transpos.sale.view.dialog;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FixDialogFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.transpos.sale.R;
import com.transpos.sale.utils.KeyboardUtils;
import com.transpos.sale.utils.UiUtils;
import com.transpos.sale.view.AuthKeyboardView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthSetDialog extends FixDialogFragment implements AuthKeyboardView.OnKeyboardInputListener {

    @Bind(R.id.et_account)
    EditText et_account;
    @Bind(R.id.et_password)
    EditText et_password;
    @Bind(R.id.input_keyboard)
    AuthKeyboardView mAuthKeyboardView;


    public static AuthSetDialog newInstance(){
        AuthSetDialog dialog =  new AuthSetDialog();
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
        window.setBackgroundDrawable(new ColorDrawable());
        window.setWindowAnimations(R.style.dialog_animation_fade);
        //设置边距
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.getDecorView().setPadding(UiUtils.dp2px(36,getContext()),0,UiUtils.dp2px(36,getContext()),0);
        setCancelable(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_auth_layout, container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    private void initView() {
        KeyboardUtils.disableShowInput(et_account);
        KeyboardUtils.disableShowInput(et_password);
        mAuthKeyboardView.setmKeyboardInputListener(this);
        et_account.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mAuthKeyboardView.setContent(et_account.getText().toString().trim(),et_account.getSelectionStart());
                }
            }
        });

        et_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mAuthKeyboardView.setContent(et_password.getText().toString().trim(),et_password.getSelectionStart());
            }
        });
    }

    @OnClick({R.id.btn_login,R.id.btn_cancel,R.id.iv_cancel,R.id.et_account,R.id.et_password})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.btn_cancel:
            case R.id.iv_cancel:
                dismiss();
                break;
            case R.id.et_account:
                mAuthKeyboardView.setContent(et_account.getText().toString().trim(),et_account.getSelectionStart());
                break;
            case R.id.et_password:
                mAuthKeyboardView.setContent(et_password.getText().toString().trim(),et_password.getSelectionStart());
                break;
        }
    }

    @Override
    public void onChanged(String content) {
        if(et_password.hasFocus()){
            et_password.setText(content);
            et_password.setSelection(mAuthKeyboardView.getSelectPosition());
        } else {
            et_account.setText(content);
            et_account.setSelection(mAuthKeyboardView.getSelectPosition());
        }
    }
}

package com.transpos.sale.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.transpos.sale.R;
import com.transpos.sale.utils.UiUtils;
import com.transpos.tools.tputils.TPUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterKeyboardView extends FrameLayout {

    private StringBuilder content= new StringBuilder();
    private OnInputContentListener mOnInputContentListener;

    public RegisterKeyboardView(@NonNull Context context) {
        this(context, null);
    }

    public RegisterKeyboardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RegisterKeyboardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.register_keyboard_input_layout, this, true);
        ButterKnife.bind(this,view);
    }

    public void setmOnInputContentListener(OnInputContentListener mOnInputContentListener) {
        this.mOnInputContentListener = mOnInputContentListener;
    }

    @OnClick({R.id.tv_num_,R.id.tv_num_0,R.id.tv_num_00,R.id.tv_num_1,R.id.tv_num_2,R.id.tv_num_3,
            R.id.tv_num_4,R.id.tv_num_5,R.id.tv_num_6,R.id.tv_num_7,R.id.tv_num_8,R.id.tv_num_9,
            R.id.tv_clear,R.id.tv_delete,R.id.tv_confirm})
    public void viewOnClick(View view){
        switch (view.getId()){
            case R.id.tv_num_00:
            case R.id.tv_num_:
            case R.id.tv_num_0:
            case R.id.tv_num_1:
            case R.id.tv_num_2:
            case R.id.tv_num_3:
            case R.id.tv_num_4:
            case R.id.tv_num_5:
            case R.id.tv_num_6:
            case R.id.tv_num_7:
            case R.id.tv_num_8:
            case R.id.tv_num_9:
                String result = (String) view.getTag();
                content.append(result);
                break;
            case R.id.tv_clear:
                content.delete(0,content.length());
                break;
            case R.id.tv_delete:
                if(content.length() > 0){
                    content.delete(content.length()-1,content.length());
                }
                break;
        }
        if(view.getId() == R.id.tv_confirm){
            if(mOnInputContentListener != null){
                if(TextUtils.isEmpty(content.toString())){
                    UiUtils.showToastLong("请输入注册码");
                } else {
                    mOnInputContentListener.onConfirm(content.toString());
                }
            }
        } else {
            if(mOnInputContentListener != null){
                mOnInputContentListener.onChanged(content.toString());
            }
        }

    }

    public interface OnInputContentListener{
        void onChanged(String content);
        void onConfirm(String content);
    }
}

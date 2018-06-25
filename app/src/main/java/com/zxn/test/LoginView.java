package com.zxn.test;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginView extends LinearLayout {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;

    public LoginView(Context context) {
        super(context);
        initView(context);
    }

    public LoginView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LoginView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        setOrientation(VERTICAL);
        setPadding(20,20,20,20);
        LayoutInflater
                .from(context)
                .inflate(R.layout.layout_login, this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        String name = etName.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        //将输入的内容,回调给外部使用
        if (mOnLoginListener != null) {
            mOnLoginListener.OnLogin(name, pwd);
        }
    }

    public interface OnLoginListener {
        void OnLogin(String name, String pwd);
    }

    private OnLoginListener mOnLoginListener;

    public void setOnLoginListener(OnLoginListener mOnLoginListener) {
        this.mOnLoginListener = mOnLoginListener;
    }


    /*@Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }*/
}

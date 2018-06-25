package com.zxn.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomView1Activity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    @BindView(R.id.lv)
    LoginView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view1);
        ButterKnife.bind(this);

        //1,首先去写一个布局
        //2,写一个类继承自现有的控件,ViewGroup或者他的子类
        //3,分析:继承自垂直的线性布局,比较合适
        //4,重写前三个构造方法
        //5,构造方法,初始化,将写好的布局注入到当前类
        //6,将按钮注册点击事件,点击的时候获取到输入框的内容
        //7,回调给外部的activity,
        //8,定义接口,对外提供一个set方法,给外界可以回调
        //9,外部设置回调,重写回调方法,弹出吐司内容,或者具体的逻辑


        lv.setOnLoginListener(new LoginView.OnLoginListener() {
            @Override
            public void OnLogin(String name, String pwd) {
                String msg = "name: " + name + "pwd:" + pwd;
                Log.i(TAG, msg);
                Toast.makeText(CustomView1Activity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
        

    }
}

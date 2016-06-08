package com.adamin.android.qdbus.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.adamin.android.qdbus.R;
import com.adamin.android.qdbus.domain.user.BusUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

public class Activity_login extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.input_email)
    EditText _emailText;
    @BindView(R.id.input_password)
    EditText _passwordText;
    @BindView(R.id.btn_login)
    Button _loginButton;
    @BindView(R.id.link_signup)
    TextView _signupLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_login);
        ButterKnife.bind(this);
        initlistener();
    }

    private void initlistener() {
        _signupLink.setOnClickListener(this);
        _loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.link_signup)
        {
            startActivity(new Intent(Activity_login.this,SignUpActivity.class));

        }else if(v.getId()==R.id.btn_login){
            login();
        }
    }

    private void login() {
        if(TextUtils.isEmpty(_emailText.getText().toString())){
            Toast.makeText(this,"登录邮箱不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(_passwordText.getText().toString())){
            Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        BmobUser.loginByAccount(Activity_login.this, _emailText.getText().toString(), _passwordText.getText().toString(),
                new LogInListener<BusUser>() {
                    @Override
                    public void done(BusUser o, BmobException e) {
                        if(null!=o){
                            Toast.makeText(Activity_login.this,"登录成功",Toast.LENGTH_SHORT).show();
                           sendBroadcast(new Intent(MainActivity.ACTON_LOGIN));
                            finish();
                            return;
                        }
                        Toast.makeText(Activity_login.this,"登录失败"+e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });
    }
}

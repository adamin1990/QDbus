package com.adamin.android.qdbus.view;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

public class SignUpActivity extends AppCompatActivity  implements View.OnClickListener {
  private BmobUser bmobUser;
    @BindView(R.id.input_name)
    EditText ed_name;
    @BindView(R.id.input_email)
    EditText ed_email;
    @BindView(R.id.input_password)
    EditText ed_pwd;
    @BindView(R.id.btn_signup)
    Button btn_sign_up;
    @BindView(R.id.link_login)
    TextView tv_go_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        bmobUser=new BmobUser();
        btn_sign_up.setOnClickListener(this);
        tv_go_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_signup:
                if(verify()){
                    signUp();
                }
                break;
            case R.id.link_login:
                break;
        }

    }

    private void signUp() {
        bmobUser.setUsername(ed_name.getText().toString());
        bmobUser.setEmail(ed_email.getText().toString());
        bmobUser.setPassword(ed_pwd.getText().toString());
        bmobUser.signUp(SignUpActivity.this, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(SignUpActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(SignUpActivity.this,"注册失败，"+s,Toast.LENGTH_SHORT).show();

            }
        });

    }

    private boolean verify() {
        if(TextUtils.isEmpty(ed_name.getText().toString())){
            Toast.makeText(SignUpActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(ed_name.getText().toString().length()<3){
            Toast.makeText(SignUpActivity.this,"用户名至少三位",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(ed_email.getText().toString())){
            Toast.makeText(SignUpActivity.this,"邮箱不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(ed_pwd.getText().toString())){
            Toast.makeText(SignUpActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}

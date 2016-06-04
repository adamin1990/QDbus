package com.adamin.android.qdbus.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.adamin.android.qdbus.R;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.link_signup)
        {
            startActivity(new Intent(Activity_login.this,SignUpActivity.class));

        }
    }
}

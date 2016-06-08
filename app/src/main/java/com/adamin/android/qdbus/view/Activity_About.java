package com.adamin.android.qdbus.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.adamin.android.qdbus.R;

public class Activity_About extends AppCompatActivity {
    private AppCompatButton btn_qq,btn_weibo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__about);
        btn_qq= (AppCompatButton) findViewById(R.id.btn_qq);
        btn_weibo= (AppCompatButton) findViewById(R.id.btn_weibo);

        btn_weibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://m.weibo.cn/u/3132916047?from=1063195010&wm=4260_0001&sourceType=qq&uid=3132916047"));
                startActivity(intent);
            }
        });
        btn_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="mqqwpa://im/chat?chat_type=wpa&uin=14846869";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });

    }

}

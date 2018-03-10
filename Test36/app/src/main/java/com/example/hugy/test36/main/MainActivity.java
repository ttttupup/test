package com.example.hugy.test36.main;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.InstrumentationTestCase;
import android.view.View;
import android.widget.Toast;

import com.example.hugy.test36.R;
import com.example.hugy.test36.account.view.LoginActivity;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View viewById = findViewById(R.id.text_id_first);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"点击切换登录",Toast.LENGTH_LONG);
                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setClass(MainActivity.this,LoginActivity.class);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });
    }
}

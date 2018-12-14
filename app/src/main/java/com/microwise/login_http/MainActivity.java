package com.microwise.login_http;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;


public class MainActivity extends AppCompatActivity {

    private EditText et_username;
    private EditText et_password;
    private CheckBox cb_ischecked;
    private Button btnLogin;
    private TextView tvText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //准备需要的ID
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        cb_ischecked = findViewById(R.id.cb_ischecked);
        btnLogin = findViewById(R.id.btn_login);
        tvText = findViewById(R.id.tv_text);

        tvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ShowActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString().trim();
                String password = et_password.getText().toString().trim();

                OkHttpUtils.post().url("http://192.168.8.1:8080/user/login")
                        .addParams("username",username)
                        .addParams("password",password)
                        .build().execute(new StringCallback() { //请求返回的  回调
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        //请求失败
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //请求成功
                        Log.d("返回",response);
                        Gson gson=new Gson();

                    }
                });
            }
        });
    }

}

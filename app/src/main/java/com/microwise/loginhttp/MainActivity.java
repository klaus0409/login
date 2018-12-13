package com.microwise.loginhttp;


import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.microwise.loginhttp.utils.ToastUtils;
import com.microwise.loginhttp.utils.UserBean;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private EditText et_username;
    private EditText et_password;
    private CheckBox cb_ischecked;

    final OkHttpClient client = new OkHttpClient();
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
      public void handlerMessage(Message msg){
          if(msg.what==1){
          String ReturnMessage = (String) msg.obj;
             final UserBean userBean = new Gson().fromJson(ReturnMessage, UserBean.class);
             final String msg1 = userBean.getMsg();
          }
      }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //准备需要的ID
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        cb_ischecked = findViewById(R.id.cb_ischecked);
    }

    public void check(View v){
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
            ToastUtils.showLong(MainActivity.this,"账号密码不能为空");
        }else {
            postRequset(username,password);
        }
    }

    private void postRequset(String username,String password){
        //建立请求表单，添加上传服务器的参数
        RequestBody formBody = new FormBody.Builder()
                .add("username",username)
                .add("password",password)
                .build();
        //发起请求
        final Request request = new Request.Builder()
                .url("http://**************/login?")
                .post(formBody)
                .build();
        //新建一个线程，用于得到服务器响应的参数
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    //回调
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        //将服务器响应的参数response.body().string())发送到hanlder中，并更新ui
                        handler.obtainMessage(1, response.body().string()).sendToTarget();

                    } else {
                        throw new IOException("Unexpected code:" + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}

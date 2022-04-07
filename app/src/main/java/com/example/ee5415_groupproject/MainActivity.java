package com.example.ee5415_groupproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;
import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;


import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Request;


public class MainActivity extends AppCompatActivity {

    EditText account, password;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取用户输入数据
        account = (EditText) findViewById(R.id.editText1);
        password = (EditText) findViewById(R.id.editText2);
        loginButton = (Button) findViewById(R.id.button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_account = account.getText().toString();
                String user_password = password.getText().toString();
                //验证输入是否为空
                if (user_account.equals("") || (user_password.equals(""))) {
                    Toast.makeText(MainActivity.this,
                            R.string.warning1, Toast.LENGTH_LONG).show();
                } else {
                    //请求服务器
                    SendData(user_account, user_password);
                    savePreferences(user_account);
                }
            }

        });
    }

    public void SendData(final String account, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                JSONObject json = new JSONObject();
                try {
                    json.put("account", account);
                    json.put("password", password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RequestBody body = RequestBody.create(JSON, String.valueOf(json));
                Request request = new Request.Builder()
                        .url(getString(R.string.server) + "/login")// 服务器端登录接口
                        .post(body)
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String result = response.body().string();
                        json_activity(result);
                    } else {
                        throw new IOException("Unexpected code" + response);
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    @Override
    protected void onStart() {
        super.onStart();
        loadPreferences(); }

    public void json_activity(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            String r = jsonObject.getString("success");
            if (r.equals("true")) {
                Intent intent = new Intent(getApplicationContext(),
                        HomepageActivity.class);
                startActivity(intent);

            } else {
                Toast.makeText(MainActivity.this,
                        R.string.warning2, Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void savePreferences(String a) {
        SharedPreferences pref = getSharedPreferences("DailyMessage", MODE_PRIVATE);
        pref.edit().putString("account", a).commit();
    }
    public void loadPreferences() {
        SharedPreferences pref = getSharedPreferences("DailyMessage", MODE_PRIVATE);
        account.setText(pref.getString("account", "0"));
    }


}



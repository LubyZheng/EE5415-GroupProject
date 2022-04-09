package com.example.ee5415_groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MessageActivity extends AppCompatActivity {
    EditText Content;

    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Content = (EditText) findViewById(R.id.content1);
        send = (Button) findViewById(R.id.btn_send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = Content.getText().toString();
                Bundle bundle = getIntent().getExtras();
                String name = bundle.getString("receiverName");
                String address = bundle.getString("emailAddress");
                System.out.println(content);
                System.out.println(name);
                System.out.println(address);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient client = new OkHttpClient();
                        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                        JSONObject json = new JSONObject();
                        try {
                            json.put("receiverName", name);
                            json.put("emailAddress", address);
                            json.put("content", content);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        RequestBody body = RequestBody.create(JSON, String.valueOf(json));

                        Request request = new Request.Builder()
                                .url(getString(R.string.server) + "/getContent")// 服务器端登录接口
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
        });


    }

    public void json_activity(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            String r = jsonObject.getString("success");
            if (r.equals("true")) {
                Intent intent = new Intent(getApplicationContext(),
                        HomepageActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(MessageActivity.this,
                        R.string.warning3, Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
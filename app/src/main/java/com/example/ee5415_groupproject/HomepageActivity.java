package com.example.ee5415_groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONObject;

public class HomepageActivity extends AppCompatActivity {
    Button ver;// will be changed
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        VersionActivity.class);
                startActivity(intent);
            }
        }

    }

}
    JSONObject ac=new JSONObject();
                    ac.put("emailAccount",acc);
                    ac.put("emailImapPassword",pw);
                    ac.put("userName",string);

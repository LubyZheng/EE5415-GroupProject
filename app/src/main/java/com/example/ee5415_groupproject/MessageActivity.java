package com.example.ee5415_groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Content =(EditText) findViewById(R.id.content1);
        Music=(EditText) findViewById(R.id.Music);
        Other=(EditText) findViewById(R.id.other);
        Button send;
        send=(Button)super.findViewById(R.id.text_del);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = Content.getText().toString();
                String music = Music.getText().toString();
                String other = Other.getText().toString();
            }
        });

    }
}
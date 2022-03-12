package com.example.ee5415_groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    EditText account, password;
    Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//-- get views
        account = (EditText) findViewById(R.id.editText1);
        password = (EditText) findViewById(R.id.editText2);
        submitButton = (Button) findViewById(R.id.button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String acc = account.getText().toString();//need to be changed
                String pw = password.getText().toString();
                //json array
                if(acc.equals("")||(pw.equals(""))){
                    Toast.makeText(MainActivity.this,R.string.warning,Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent(getApplicationContext(),
                            HomepageActivity.class);
                    JSONObject ac=new JSONObject();
                    ac.put("emailAccount",acc);
                    ac.put("emailImapPassword",pw);
                    startActivity(intent);
                }
            }

        });
    } }
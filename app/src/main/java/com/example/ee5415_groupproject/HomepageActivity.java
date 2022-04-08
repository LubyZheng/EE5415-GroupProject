package com.example.ee5415_groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONObject;

public class HomepageActivity extends AppCompatActivity {
    Button ContactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        ContactButton = (Button) findViewById(R.id.btn_con);
        ContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        contact.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                Intent intent1 = new Intent(getApplicationContext(),
                        InfoActivity.class);
                startActivity(intent1);
                return true;
            case R.id.menu_feedback:
                Intent intent2 = new Intent(getApplicationContext(),
                        VersionActivity.class);
                startActivity(intent2);
                return true;
        }
        return false;
    }

}


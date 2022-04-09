package com.example.ee5415_groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class HomepageActivity extends AppCompatActivity {
    Button ContactButton;
    Database db;
    ListView mListView;
    ListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        db = new Database(this);
        Cursor res = db.queryAll();
        ArrayList<String> NameList = new ArrayList<String>();
        if (res.getCount() != 0) {
            while (res.moveToNext()) {
                NameList.add(res.getString(1));
            }
        }
        String[] nameArray = NameList.toArray(new String[0]);
        mListView = (ListView) findViewById(R.id.listview);
        mListAdapter = new
                ListAdapter(this, nameArray);
        mListView.setAdapter(mListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int
                    i, long l) {
            }
        });
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


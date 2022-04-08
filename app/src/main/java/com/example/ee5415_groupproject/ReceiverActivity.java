package com.example.ee5415_groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;

public class ReceiverActivity extends AppCompatActivity {
    ListView mListView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_homepage_receiver);
        button = (Button) findViewById(R.id.btn_con1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        contact.class);
                startActivity(intent);
            }

        });
        Bundle bundle =getIntent().getExtras();
        String name=bundle.getString("receiverName");
        String data[]={name};
        mListView=(ListView)findViewById(R.id.listview);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
        mListView.setAdapter(adapter);

    }

}

package com.example.ee5415_groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class contact extends AppCompatActivity {
    EditText address,name;
    String[] countryArray,provinceArray,cityArray,hourArray,minArray;
    Spinner spinnerCountry,spinnerProvince,spinnerCity,spinnerHour,spinnerMin;
    int country,province,city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        //用户输入数据
        address = (EditText) findViewById(R.id.edit_email);
        name=(EditText) findViewById(R.id.edit_name);
        //location
        countryArray = getResources().getStringArray(R.array.Location_countries);
        provinceArray = getResources().getStringArray(R.array.Location_province);
        cityArray = getResources().getStringArray(R.array.Location_cities);
        spinnerCountry = (Spinner) findViewById(R.id.spinner_country);
        ArrayAdapter<String> adapterCountry = new ArrayAdapter<String>(this,
                R.layout.spinner_item, countryArray);
        spinnerCountry.setAdapter(adapterCountry);
        spinnerProvince = (Spinner) findViewById(R.id.spinner_province);
        ArrayAdapter<String> adapterProvince = new ArrayAdapter<String>(this,
                R.layout.spinner_item, provinceArray);
        spinnerProvince.setAdapter(adapterProvince);
        spinnerCity = (Spinner) findViewById(R.id.spinner_city);
        ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(this,
                R.layout.spinner_item, provinceArray);
        spinnerCity.setAdapter(adapterCity);
        spinnerCountry.setOnItemSelectedListener(new
                Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
            int arg2, long arg3) {
                country = arg0.getSelectedItemPosition() ; }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        spinnerProvince.setOnItemSelectedListener(new
                                                         Spinner.OnItemSelectedListener() {
                                                             @Override
                                                             public void onItemSelected(AdapterView<?> arg0, View arg1,
                                                                                        int arg2, long arg3) {
                                                                 province = arg0.getSelectedItemPosition() ; }
                                                             @Override
                                                             public void onNothingSelected(AdapterView<?> arg0) {
                                                             }
                                                         });
        spinnerCity.setOnItemSelectedListener(new
                                                         Spinner.OnItemSelectedListener() {
                                                             @Override
                                                             public void onItemSelected(AdapterView<?> arg0, View arg1,
                                                                                        int arg2, long arg3) {
                                                                 city = arg0.getSelectedItemPosition() ; }
                                                             @Override
                                                             public void onNothingSelected(AdapterView<?> arg0) {
                                                             }
                                                         });
        //Schedule Time




        //  switch
        Switch Weather;
        Switch news,cov,more;
        Weather=(Switch)findViewById(R.id.btn_Wea);
        news=(Switch)findViewById(R.id.btn_news);
        cov=(Switch)findViewById(R.id.btn_cov);
        more=(Switch)findViewById(R.id.btn_more);
        Weather.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    Toast.makeText(contact.this,R.string.announcement1,Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(contact.this,R.string.announcement2,Toast.LENGTH_LONG).show();
                }
            }
        });
        news.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    Toast.makeText(contact.this,R.string.announcement1,Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(contact.this,R.string.announcement2,Toast.LENGTH_LONG).show();
                }
            }
        });
        cov.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    Toast.makeText(contact.this,R.string.announcement1,Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(contact.this,R.string.announcement2,Toast.LENGTH_LONG).show();
                }
            }
        });
        more.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    Toast.makeText(contact.this,R.string.announcement1,Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(contact.this,R.string.announcement2,Toast.LENGTH_LONG).show();
                }
            }
        });
        Button Finish,Delete;
        Finish=(Button)super.findViewById(R.id.text_finish);
        Delete=(Button)super.findViewById(R.id.text_del);
        Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        VersionActivity.class);
                startActivity(intent);
            }
        });
        //need to be changed
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        VersionActivity.class);
                startActivity(intent);
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                MediaType JSON =MediaType.parse("application/json; charset=utf-8");
                JSONObject json =new JSONObject();
                try{
                    json.put("receiverName",name);
                    json.put("emailAddress",address);

                }catch ( JSONException e){
                    e.printStackTrace();
                }

                RequestBody body = RequestBody.create(JSON,String.valueOf(json));

                Request request = new Request.Builder()
                        .url("http://10.0.2.2:8080/startTask")// 服务器端登录接口
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
    public void json_activity(String data){
        try{
            JSONObject jsonObject=new JSONObject(data);
            String r =jsonObject.getString("success");
            if (r.equals("true")){
                Intent intent= new Intent(getApplicationContext(),
                        MessageActivity.class);
                startActivity(intent);

            }else{
                Toast.makeText(contact.this,R.string.warning2,Toast.LENGTH_LONG).show();
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}


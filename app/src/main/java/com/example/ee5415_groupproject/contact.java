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

public class contact extends AppCompatActivity {
    EditText address,name;
    String[] countryArray,provinceArray,cityArray,hourArray,minArray;
    Spinner spinnerCountry,spinnerProvince,spinnerCity,spinnerHour,spinnerMin;
    int country,province,city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
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
        hourArray = getResources().getStringArray(R.array.Time_hours);
        minArray = getResources().getStringArray(R.array.Time_min);
        spinnerHour = (Spinner) findViewById(R.id.spinner_hours);
        ArrayAdapter<String> adapterHour = new ArrayAdapter<String>(this,
                R.layout.spinner_item, hourArray);
        spinnerHour.setAdapter(adapterHour);
        spinnerMin = (Spinner) findViewById(R.id.spinner_min);
        ArrayAdapter<String> adapterMin = new ArrayAdapter<String>(this,
                R.layout.spinner_item, minArray);
        spinnerMin.setAdapter(adapterMin);
        spinnerHour.setOnItemSelectedListener(new
                                                         Spinner.OnItemSelectedListener() {
                                                             @Override
                                                             public void onItemSelected(AdapterView<?> arg0, View arg1,
                                                                                        int arg2, long arg3) {
                                                                 country = arg0.getSelectedItemPosition() ; }
                                                             @Override
                                                             public void onNothingSelected(AdapterView<?> arg0) {
                                                             }
                                                         });
        spinnerMin.setOnItemSelectedListener(new
                                                      Spinner.OnItemSelectedListener() {
                                                          @Override
                                                          public void onItemSelected(AdapterView<?> arg0, View arg1,
                                                                                     int arg2, long arg3) {
                                                              country = arg0.getSelectedItemPosition() ; }
                                                          @Override
                                                          public void onNothingSelected(AdapterView<?> arg0) {
                                                          }
                                                      });
        //  switch
        Switch Weather,news,cov,more;
        Weather=(Switch)super.findViewById(R.id.btn_Wea);
        news=(Switch)super.findViewById(R.id.btn_news);
        cov=(Switch)super.findViewById(R.id.btn_cov);
        more=(Switch)super.findViewById(R.id.btn_more);
        Weather.setOnClickListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked){
                if (isChecked){
                    Boolean weather=true;
                }else{
                   false;
                }
            }
        });
       news.setOnClickListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked){
                if (isChecked){

                }else{

                }

            }
        });
        cov.setOnClickListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked){
                if (isChecked){
                    json
                }else{
                    json
                }

            }
        });
        more.setOnClickListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked){
                if (isChecked){
                    json
                }else{
                    json
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
                        MessageActivity.class);
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
    }
}

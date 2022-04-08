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
    EditText address, name,hours,min;
    String[] countryArray, provinceArray, cityArray, hourArray, minArray;
    Spinner spinnerCountry, spinnerProvince, spinnerCity, spinnerHour, spinnerMin;
    String country, province, city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        //用户输入数据
        address = (EditText) findViewById(R.id.edit_email);
        String Address = address.getText().toString();
        name = (EditText) findViewById(R.id.edit_name);
        String Name = name.getText().toString();
        hours = (EditText) findViewById(R.id.spinner_hours);
        String hour =hours.getText().toString();
        min = (EditText) findViewById(R.id.spinner_min);
        String Min = min.getText().toString();
        //location
        countryArray = getResources().getStringArray(R.array.Location_countries);
        provinceArray = getResources().getStringArray(R.array.Location_provinces);
        //cityArray = getResources().getStringArray(R.array.Location_cities);
        spinnerCountry = (Spinner) findViewById(R.id.spinner_country);
        ArrayAdapter<String> adapterCountry = new ArrayAdapter<String>(this,
                R.layout.spinner_item, countryArray);
        spinnerCountry.setAdapter(adapterCountry);
        spinnerProvince = (Spinner) findViewById(R.id.spinner_province);
        ArrayAdapter<String> adapterProvince = new ArrayAdapter<String>(this,
                R.layout.spinner_item, provinceArray);
        spinnerProvince.setAdapter(adapterProvince);
//        spinnerCity = (Spinner) findViewById(R.id.spinner_city);
//        ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(this,
//                R.layout.spinner_item, cityArray);
//        spinnerCity.setAdapter(adapterCity);
        spinnerCountry.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        country = countryArray[arg2];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                    }
                });
        spinnerProvince.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        province = provinceArray[arg2];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                    }
                });
        //spinnerCity.setOnItemSelectedListener(
                //new Spinner.OnItemSelectedListener() {
                    //@Override
                    //public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               //int arg2, long arg3) {
                        //city = cityArray[arg2];
                    //}

                   // @Override
                   // public void onNothingSelected(AdapterView<?> arg0) {
                    //}
               // });
        //Schedule Time


        //  switch
        Switch Weather;
        Switch news, cov, more;
        Weather = (Switch) findViewById(R.id.btn_Wea);
        news = (Switch) findViewById(R.id.btn_news);
        cov = (Switch) findViewById(R.id.btn_cov);
//        more = (Switch) findViewById(R.id.btn_more);
        Weather.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(contact.this,
                            R.string.announcement1, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(contact.this,
                            R.string.announcement2, Toast.LENGTH_LONG).show();
                }
            }
        });
        news.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(contact.this,
                            R.string.announcement1, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(contact.this,
                            R.string.announcement2, Toast.LENGTH_LONG).show();
                }
            }
        });
        cov.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(contact.this,
                            R.string.announcement1, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(contact.this,
                            R.string.announcement2, Toast.LENGTH_LONG).show();
                }
            }
        });
//        more.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    Toast.makeText(contact.this,
//                            R.string.announcement1, Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(contact.this,
//                            R.string.announcement2, Toast.LENGTH_LONG).show();
//                }
//            }
//        });
        Button Finish, Delete;
        Finish = (Button) super.findViewById(R.id.text_finish);
        Delete = (Button) super.findViewById(R.id.text_del);
        Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient client = new OkHttpClient();
                        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                        JSONObject json1 = new JSONObject();
                        try {
                            json1.put("country", country);
                            json1.put("province", province);
                            json1.put("city", city);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONObject json2 = new JSONObject();
                        try {
                            json2.put("hour", hour);
                            json2.put("minute", Min);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONObject json = new JSONObject();
                        try {
                            json.put("receiverName", Name);
                            json.put("emailAddress", Address);
                            json.put("location", json1);
                            json.put("time", json2);
                            json.put("weather", Weather.isChecked());
                            json.put("news", news.isChecked());
                            json.put("covid", cov.isChecked());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        RequestBody body = RequestBody.create(JSON, String.valueOf(json));

                        Request request = new Request.Builder()
                                .url(getString(R.string.server) + "/startTask")// 服务器端登录接口
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

    public void json_activity(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            String r = jsonObject.getString("success");
            address = (EditText) findViewById(R.id.edit_email);
            String Address = address.getText().toString();
            name = (EditText) findViewById(R.id.edit_name);
            String Name = name.getText().toString();
            if (r.equals("true")) {
                Intent intent = new Intent(getApplicationContext(),
                        ReceiverActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("receiverName", Name);
                bundle.putString("emailAddress", Address);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}


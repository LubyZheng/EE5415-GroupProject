package com.example.ee5415_groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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
    EditText email, name;
    String[] provinceArray, cityArray, hourArray, minArray;
    Spinner spinnerHour, spinnerMin, spinnerProvince, spinnerCity;
    String hour, min, province, city;
    Switch Weather, news, cov;
    int pos_p, pos_c, pos_h, pos_m;
    int JustForCitySpinner = -1;  // City spinner related to Province Spinner, this for flag
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        db = new Database(this);

        //用户输入数据
        email = (EditText) findViewById(R.id.edit_email);
        name = (EditText) findViewById(R.id.edit_name);
        //location
        spinnerProvince = (Spinner) findViewById(R.id.spinner_province);
        spinnerCity = (Spinner) findViewById(R.id.spinner_city);
        // Timing
        spinnerHour = (Spinner) findViewById(R.id.spinner_hours);
        spinnerMin = (Spinner) findViewById(R.id.spinner_min);
        //  switch
        Weather = (Switch) findViewById(R.id.btn_Wea);
        news = (Switch) findViewById(R.id.btn_news);
        cov = (Switch) findViewById(R.id.btn_cov);

        provinceArray = getResources().getStringArray(R.array.Location_provinces);
        ArrayAdapter<String> adapterProvince = new ArrayAdapter<String>(this,
                R.layout.spinner_item, provinceArray);
        spinnerProvince.setAdapter(adapterProvince);

        hourArray = getResources().getStringArray(R.array.Hours);
        minArray = getResources().getStringArray(R.array.Minutes);
        ArrayAdapter<String> adapterHour = new ArrayAdapter<String>(this,
                R.layout.spinner_item, hourArray);
        spinnerHour.setAdapter(adapterHour);
        ArrayAdapter<String> adapterMin = new ArrayAdapter<String>(this,
                R.layout.spinner_item, minArray);
        spinnerMin.setAdapter(adapterMin);

        spinnerProvince.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        province = provinceArray[arg2];
                        if (province.equals("Guangdong")) {
                            cityArray = getResources().getStringArray(R.array.Location_cities_GD);
                        } else if (province.equals("Hunan")) {
                            cityArray = getResources().getStringArray(R.array.Location_cities_HN);
                        }
                        pos_p = arg2;
                        ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(
                                contact.this, R.layout.spinner_item, cityArray);
                        spinnerCity.setAdapter(adapterCity);
                        if (JustForCitySpinner >= 0) {
                            spinnerCity.setSelection(JustForCitySpinner);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {

                    }
                });

        spinnerCity.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        city = cityArray[arg2];
                        pos_c = arg2;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {

                    }
                });

        spinnerHour.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        hour = hourArray[arg2];
                        pos_h = arg2;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {

                    }
                });

        spinnerMin.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        min = minArray[arg2];
                        pos_m = arg2;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {

                    }
                });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            // 赶工，没使用WHERE语句查库
            Cursor res = db.queryAll();
            if (res.getCount() != 0) {
                while (res.moveToNext()) {
                    if (res.getString(1).equals(bundle.getString("name"))) {
                        Recover(res.getString(1), res.getString(2), res.getString(3),
                                res.getString(4), res.getString(5), res.getString(6),
                                res.getString(7), res.getString(8), res.getString(9));
                        break;
                    }
                }
            }
        }

        if (cityArray != null) {
            System.out.println("here");
            ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(this,
                    R.layout.spinner_item, cityArray);
            spinnerCity.setAdapter(adapterCity);
        }

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
                            json1.put("country", "China");
                            json1.put("province", province);
                            json1.put("city", city);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONObject json2 = new JSONObject();
                        try {
                            json2.put("hour", hour);
                            json2.put("minute", min);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONObject json = new JSONObject();
                        try {
                            json.put("receiverName", name.getText().toString());
                            json.put("emailAddress", email.getText().toString());
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
                                Cursor res = db.queryAll();
                                boolean flag = false;
                                if (res.getCount() != 0) {
                                    while (res.moveToNext()) {
                                        if (res.getString(1).equals(name.getText().toString())) {
                                            flag = true;
                                            break;
                                        }
                                    }
                                }
                                if (!flag) {
                                    db.insertData(name.getText().toString(),
                                            email.getText().toString(),
                                            String.valueOf(pos_p),
                                            String.valueOf(pos_c),
                                            String.valueOf(pos_h),
                                            String.valueOf(pos_m),
                                            Weather.isChecked(), news.isChecked(), cov.isChecked());
                                } else {
                                    db.updateData(name.getText().toString(),
                                            email.getText().toString(),
                                            String.valueOf(pos_p),
                                            String.valueOf(pos_c),
                                            String.valueOf(pos_h),
                                            String.valueOf(pos_m),
                                            Weather.isChecked(), news.isChecked(), cov.isChecked());
                                }
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
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient client = new OkHttpClient();
                        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                        JSONObject json = new JSONObject();
                        try {
                            json.put("receiverName", name.getText().toString());
                            json.put("emailAddress", email.getText().toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        RequestBody body = RequestBody.create(JSON, String.valueOf(json));
                        Request request = new Request.Builder()
                                .url(getString(R.string.server) + "/deleteTask")// 服务器端登录接口
                                .delete(body)
                                .build();
                        try {
                            Response response = client.newCall(request).execute();
                            if (response.isSuccessful()) {
                                db.deleteData(name.getText().toString());
                                String result = response.body().string();
                                json_activity(result);
                            } else {
                                throw new IOException("Unexpected code" + response);
                            }


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(getApplicationContext(),
                                HomepageActivity.class);
                        startActivity(intent);
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
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void Recover(String name_arg, String email_arg, String province_arg,
                           String city_arg, String hour_arg, String minute_arg,
                           String weather_arg, String news_arg, String covid_arg) {
        name.setText(name_arg);
        email.setText(email_arg);
        spinnerProvince.setSelection(Integer.parseInt(province_arg));
        if (province_arg.equals("0")) {
            cityArray = getResources().getStringArray(R.array.Location_cities_GD);
        } else if (province_arg.equals("1")) {
            cityArray = getResources().getStringArray(R.array.Location_cities_HN);
        }
        JustForCitySpinner = Integer.parseInt(city_arg);
        spinnerHour.setSelection(Integer.parseInt(hour_arg));
        spinnerMin.setSelection(Integer.parseInt(minute_arg));
        Weather.setChecked(weather_arg.equals("1"));
        news.setChecked(news_arg.equals("1"));
        cov.setChecked(covid_arg.equals("1"));
    }
}


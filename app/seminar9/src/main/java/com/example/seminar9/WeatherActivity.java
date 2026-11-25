package com.example.seminar9;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_weather_actvitity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void callApi(View view){
        EditText et = findViewById(R.id.ET_oras);
        String oras = et.getText().toString().trim();
        TextView tvResult = findViewById(R.id.TV_result);

        String codOras = "287430";
        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                String link="https://dataservice.accuweather.com/locations/v1/cities/search?apikey="
                + MainActivity.KEY + "&q="+ oras;

                try {
                    URL url = new URL(link);
                    HttpURLConnection con = null;
                    con = (HttpURLConnection) url.openConnection();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String string = reader.readLine();
//                    StringBuilder builder = new StringBuilder("");
                    JSONArray jsonArray = new JSONArray(string);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String cheieOras = jsonObject.getString("Key");
//                    System.out.println(string);
                    System.out.println(cheieOras);




                } catch (Exception exception) {
                    System.out.println("exceptie: " + exception.getMessage());
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                String link2 = "https://dataservice.accuweather.com/forecasts/v1/daily/1day/"+ codOras + "?apikey=" + MainActivity.KEY;
                                try {
                                    URL url = new URL(link2);
                                    HttpURLConnection con = null;
                                    con = (HttpURLConnection) url.openConnection();

                                    BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                                    String string = reader.readLine();
                                    System.out.println(string);
                                    JSONArray jsonArray = new JSONArray(string);
                                    JSONArray jsonArrayWeather = jsonArray.getJSONArray(1);
                                    JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
                                    JSONObject jsonObjectTemperature = jsonObjectWeather.getJSONObject("Temperature");
                                    int minimum = jsonObjectTemperature.getJSONObject("Minimum").getInt("Value");
                                    int maximum = jsonObjectTemperature.getJSONObject("Maximum").getInt("Value");
                                    System.out.println(minimum);
                                    System.out.println(maximum);

                                } catch (Exception exception) {
                                    System.out.println("exceptie: " + exception.getMessage());
                                }
                            }
                        });
                    }
                });

            }
        });
    }
}
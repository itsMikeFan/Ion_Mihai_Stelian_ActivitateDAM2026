package com.example.seminar8;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AfisareImagini extends AppCompatActivity {
    private List<ImagineSmoothie> listaImagini = new ArrayList<>();
    private List<Bitmap> imagini = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_afisare_imagini);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ListView lv = findViewById(R.id.LV_imagini);
        List<String> linkuriImagini = new ArrayList<>();


        linkuriImagini.add("https://suntnatural.ro/wp-content/uploads/2024/10/DSC2547-scaled.jpg");
        linkuriImagini.add("https://cdn.pixabay.com/photo/2020/09/18/13/07/smoothie-5581794_640.jpg");
        linkuriImagini.add("https://zilesinopti.ro/wp-content/uploads/2025/04/smoothie-de-primavara-01.jpg");
        linkuriImagini.add("https://www.nutrifitup.com/wp-content/uploads/2021/09/retete-smoothie-pentru-slabit.jpg");
        linkuriImagini.add("https://images.pexels.com/photos/6990114/pexels-photo-6990114.jpeg");

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                for(String link:linkuriImagini){
                    HttpURLConnection con =null;
                    try{
                        URL url = new URL(link);
                        con = (HttpURLConnection) url.openConnection();
                        InputStream is = con.getInputStream();
                        imagini.add(BitmapFactory.decodeStream(is));
                    }
                    catch (Exception exception) {
                        System.out.println("exceptie: " + exception.getMessage());
                    }
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
//                listaImagini = new ArrayList<>();
//                for(int i =0;i<linkuriImagini.size();i++){
//                    listaImagini.add(new ImagineSmoothie("Portocale", imagini.get(i), linkuriImagini.get(i)));
//                }
                        listaImagini.add(new ImagineSmoothie("Portocale", imagini.get(0),linkuriImagini.get(0)));
                        listaImagini.add(new ImagineSmoothie("Portocale2", imagini.get(1),linkuriImagini.get(1)));
                        listaImagini.add(new ImagineSmoothie("Portocale3", imagini.get(2),linkuriImagini.get(2)));
                        listaImagini.add(new ImagineSmoothie("Portocale4", imagini.get(3),linkuriImagini.get(3)));
                        listaImagini.add(new ImagineSmoothie("Portocale5", imagini.get(4),linkuriImagini.get(4)));
                        AdapterImagine adapter = new AdapterImagine(listaImagini,getApplicationContext(),R.layout.custom_layout_imagini);
                        lv.setAdapter(adapter);
                    }
                });
            }
        });





        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(getApplicationContext(), WebviewActivity.class);
                it.putExtra("link", listaImagini.get(position).getLink());
                startActivity(it);
            }
        });

    }
}
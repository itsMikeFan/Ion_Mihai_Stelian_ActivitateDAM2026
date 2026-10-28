package com.example.seminar1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivitateRezultat extends AppCompatActivity {

    private int suma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_rezultat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent it = getIntent();
        Bundle bundle = it.getExtras();

        String mesaj = bundle.getString("Mesaj", "");
        int nr1 = bundle.getInt("numar1", 0);
        int nr2 = bundle.getInt("numar2", 0);
        suma = nr1 + nr2;

        StringBuilder builder = new StringBuilder(mesaj);
        builder.append(" ");
        builder.append(nr1);
        builder.append(" ");
        builder.append(nr2);
        Toast.makeText(this, builder.toString(), Toast.LENGTH_SHORT).show();
    }

    public void intoarcereInActivitate(View view) {
        String altMesaj = "Mesaj de intoarcere!";
        Intent it = new Intent();
        it.putExtra("mesaj", altMesaj);
        it.putExtra("suma", suma);
        setResult(RESULT_OK, it);
        finish();
    }

}
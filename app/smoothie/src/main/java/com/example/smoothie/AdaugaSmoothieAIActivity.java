package com.example.smoothie;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// Same logic as AdaugaSmoothieActivity; only the layout (activity_adauga_smoothie_ai) was designed with AI.
public class AdaugaSmoothieAIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_smoothie_ai);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etNume = findViewById(R.id.etNume);
        Spinner spFruct = findViewById(R.id.spFruct);
        RadioGroup rgMarime = findViewById(R.id.rgMarime);
        CheckBox cbGheata = findViewById(R.id.cbGheata);
        Switch swVegan = findViewById(R.id.swVegan);
        RatingBar rbRating = findViewById(R.id.rbRating);
        Button btnSalveaza = findViewById(R.id.btnSalveaza);

        spFruct.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.fructe)));

        btnSalveaza.setOnClickListener(v -> {
            String nume = etNume.getText().toString().trim();
            if (nume.isEmpty()) {
                etNume.setError(getString(R.string.eroare_nume));
                return;
            }
            String marime = rgMarime.getCheckedRadioButtonId() == R.id.rbS ? "S"
                    : rgMarime.getCheckedRadioButtonId() == R.id.rbL ? "L" : "M";
            Smoothie s = new Smoothie(nume, spFruct.getSelectedItem().toString(), marime,
                    cbGheata.isChecked(), swVegan.isChecked(), rbRating.getRating());
            setResult(RESULT_OK, new Intent().putExtra(MainActivity.EXTRA_SMOOTHIE, s));
            finish();
        });
    }
}

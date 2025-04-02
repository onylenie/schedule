package com.example.testapp;

import static com.example.testapp.GlobalVariables.medicines;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class PillsSet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pills_set);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton imageButton5 = findViewById(R.id.imageButton5);

        // Устанавливаем обработчик события нажатия
        imageButton5.setOnClickListener(v -> {
            // Закрываем текущую активность
            finish();
        });


        ListView listView = findViewById(R.id.listViewPills);


        // Создаем адаптер и подключаем к ListView
        MedicineAdapter adapterNull = new MedicineAdapter(this, medicines, MedicineAdapter.FilterType.NULL_TYPE);
        listView.setAdapter(adapterNull);


        ImageButton imageButton6 = findViewById(R.id.imageButton6);

        // Устанавливаем обработчик события нажатия
        imageButton6.setOnClickListener(v -> {
            // Открываем PillsSetActivity
            Intent intent = new Intent(this, PillsNew.class);
            startActivity(intent);
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        ListView listView = findViewById(R.id.listViewPills);

        // Создаем адаптер и подключаем к ListView
        MedicineAdapter adapterNull = new MedicineAdapter(this, medicines, MedicineAdapter.FilterType.NULL_TYPE);
        listView.setAdapter(adapterNull);
    }
}
package com.example.testapp;

import static com.example.testapp.GlobalVariables.medicines;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ScheduleSet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_schedule_set);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton imageButton5 = findViewById(R.id.imageButtonSchSet);

        // Устанавливаем обработчик события нажатия
        imageButton5.setOnClickListener(v -> {
            // Закрываем текущую активность
            finish();
        });


        ListView listView = findViewById(R.id.listViewSchedule);


        // Создаем адаптер и подключаем к ListView
        MedicineAdapter adapterNotNull = new MedicineAdapter(this, medicines, MedicineAdapter.FilterType.NON_NULL_TYPE);
        listView.setAdapter(adapterNotNull);


        ImageButton imageButton6 = findViewById(R.id.imageButtonSchSetNew);

        // Устанавливаем обработчик события нажатия
        imageButton6.setOnClickListener(v -> {
            // Открываем PillsSetActivity
            Intent intent = new Intent(this, ScheduleNew.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ListView listView = findViewById(R.id.listViewSchedule);

        // Создаем адаптер и подключаем к ListView
        MedicineAdapter adapterNull = new MedicineAdapter(this, medicines, MedicineAdapter.FilterType.NON_NULL_TYPE);
        listView.setAdapter(adapterNull);
    }
}

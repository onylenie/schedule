package com.example.testapp;

import static com.example.testapp.GlobalVariables.medicines;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.ui.Day;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        GlobalVariables.loadMedicinesFromCache(this);
        GlobalVariables.sortMedicinesByTimeInterval();
        for (Medicine  a: medicines) {
            Log.d("TAG", "Variable value: " + a.getName()+a.getTabletCount()+a.getTimeInterval()+a.getType());
        }

        // Создаем список данных
        /*
        try {
            SharedPreferences preferences = getSharedPreferences("MedicineCache", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
         */

        /*
        GlobalVariables.addMedicineToCache(new Medicine("08:00","Парацетамол", "1 таблетка(и)"), this);
        GlobalVariables.addMedicineToCache(new Medicine( "12:00","Живопись","активность","Понедельник"), this);
        GlobalVariables.addMedicineToCache(new Medicine("20:00", "Ибупрофен","1 таблетка(и)"), this);
         */



        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        TextView tvCurrentDate = findViewById(R.id.tvCurrentDate);

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM");
        String formattedDate = dateFormat.format(currentDate);

        // Установка текущей даты в TextView
        tvCurrentDate.setText("Сегодня, " + formattedDate);

        Calendar calendar = Calendar.getInstance();

        // Подготовка списка дней с динамическими датами
        List<Day> days = new ArrayList<>();

        // Установка календаря на начало текущей недели (понедельник)
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        // Добавление дней недели с соответствующими датами
        days.add(new Day("ПН", calendar.get(Calendar.DAY_OF_MONTH)));
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        days.add(new Day("ВТ", calendar.get(Calendar.DAY_OF_MONTH)));
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        days.add(new Day("СР", calendar.get(Calendar.DAY_OF_MONTH)));
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        days.add(new Day("ЧТ", calendar.get(Calendar.DAY_OF_MONTH)));
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        days.add(new Day("ПТ", calendar.get(Calendar.DAY_OF_MONTH)));

        // Настройка RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        CalendarAdapter adapter = new CalendarAdapter(days);
        recyclerView.setAdapter(adapter);

        ImageButton imageButtonEmCall= findViewById(R.id.imageButton3);

        imageButtonEmCall.setOnClickListener(v -> {
            Intent intent = new Intent(this, EmergencyCall.class);
            startActivity(intent);
        });

        ImageButton imageButtonSchedule= findViewById(R.id.imageButton2);

        imageButtonSchedule.setOnClickListener(v -> {
            Intent intent = new Intent(this, ScheduleSet.class);
            startActivity(intent);
        });

        ListView listView = findViewById(R.id.listView);

        // Создаем адаптер и подключаем к ListView
        MedicineAdapter adapterAll = new MedicineAdapter(this, medicines, MedicineAdapter.FilterType.ALL);
        listView.setAdapter(adapterAll);


        /*
        ListView listView2 = findViewById(R.id.listView2);

        // Создаем список данных
        List<Medicine> medicines2 = new ArrayList<>();
        medicines2.add(new Medicine("08:00 - 09:00", "Парацетамол", "1 таблетка(и)"));
        medicines2.add(new Medicine("12:00 - 13:00", "Аспирин", "2 таблетка(и)"));
        medicines2.add(new Medicine("20:00 - 21:00", "Ибупрофен", "1 таблетка(и)"));

        // Создаем адаптер и подключаем к ListView
        MedicineAdapter2 adapter3 = new MedicineAdapter2(this, medicines2);
        listView2.setAdapter(adapter3);
        */

        ImageButton imageButton4 = findViewById(R.id.imageButton4);

        // Устанавливаем обработчик события нажатия
        imageButton4.setOnClickListener(v -> {
            // Открываем PillsSetActivity
            Intent intent = new Intent(MainActivity.this, PillsSet.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ListView listView = findViewById(R.id.listView);
        GlobalVariables.sortMedicinesByTimeInterval();

        // Создаем адаптер и подключаем к ListView
        MedicineAdapter adapterNull = new MedicineAdapter(this, medicines, MedicineAdapter.FilterType.ALL);
        listView.setAdapter(adapterNull);
    }
}


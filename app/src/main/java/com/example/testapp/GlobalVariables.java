package com.example.testapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GlobalVariables {

    protected static List<Medicine> medicines = new ArrayList<>();

    // Метод для загрузки списка лекарств из кэша при запуске приложения
    public static void loadMedicinesFromCache(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MedicineCache", Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("medicines_list", null);

        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Medicine>>() {}.getType();
            medicines = gson.fromJson(json, type);
        }
    }

    // Метод для добавления нового элемента в кэш
    public static void addMedicineToCache(Medicine medicine, Context context) {
        medicines.add(medicine);
        saveMedicinesToCache(context);
    }

    // Метод для удаления элемента из списка и кэша
    public static void removeMedicineFromCache(Medicine medicine, Context context) {
        medicines.remove(medicine);
        saveMedicinesToCache(context); // Обновляем кэш после удаления
    }

    // Метод для сохранения текущего списка в кэш
    private static void saveMedicinesToCache(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MedicineCache", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(medicines);
        editor.putString("medicines_list", json);
        editor.apply();
    }

    // Метод для сортировки списка по полю timeInterval
    public static void sortMedicinesByTimeInterval() {
        medicines.sort(new Comparator<Medicine>() {
            @Override
            public int compare(Medicine m1, Medicine m2) {
                return m1.getTimeInterval().compareTo(m2.getTimeInterval());
            }
        });
    }


}

class Medicine {
    private String type;
    private String timeInterval;
    private String name;
    private String tabletCount;

    public Medicine(String timeInterval, String name, String tabletCount, String type) {
        this.type = type;
        this.timeInterval = timeInterval;
        this.name = name;
        this.tabletCount = tabletCount;
    }

    public Medicine(String timeInterval, String name, String tabletCount) {
        this.type=null;
        this.timeInterval = timeInterval;
        this.name = name;
        this.tabletCount = tabletCount;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public String getName() {
        return name;
    }

    public String getTabletCount() {
        return tabletCount;
    }

    public String getType(){return type; }
}

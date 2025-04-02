package com.example.testapp;

import static com.example.testapp.GlobalVariables.medicines;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MedicineAdapter extends BaseAdapter {
    private Context context;
    private List<Medicine> filteredMedicines;
    private FilterType filter;

    public enum FilterType {
        ALL,
        NULL_TYPE,
        NON_NULL_TYPE
    }

    /**
     * @param context контекст активности
     * @param medicines список всех лекарств
     * @param filterType тип фильтрации
     */
    public MedicineAdapter(Context context, List<Medicine> medicines, FilterType filterType) {
        this.context = context;
        filteredMedicines = new ArrayList<>();
        filter = filterType;
        // Получаем текущий день недели
        String today = getTodayDayOfWeek();

        switch (filterType) {
            case NULL_TYPE:
                // Фильтрация по getType() == null
                for (Medicine medicine : medicines) {
                    if (medicine.getType() == null) {
                        filteredMedicines.add(medicine);
                    }
                }
                break;
            case NON_NULL_TYPE:
                // Фильтрация по getType() != null
                for (Medicine medicine : medicines) {
                    if (medicine.getType() != null) {
                        filteredMedicines.add(medicine);
                    }
                }
                break;
            case ALL:
                // Вывод только тех строк, где type равен null или сегодняшнему дню недели
                for (Medicine medicine : medicines) {
                    if (medicine.getType() == null || today.equalsIgnoreCase(medicine.getType())) {
                        filteredMedicines.add(medicine);
                    }
                }
                break;
        }
    }

    // Метод для получения названия текущего дня недели
    private String getTodayDayOfWeek() {
        String[] days = {"Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"};
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return days[day - 1];
    }

    @Override
    public int getCount() {
        return filteredMedicines.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredMedicines.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            // Подключаем файл разметки list_rows
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_rows, parent, false);
        }

        // Получаем текущий элемент из отфильтрованного списка
        Medicine currentMedicine = filteredMedicines.get(position);
        TextView timeTextView = convertView.findViewById(R.id.textView); // Поле "Время"
        TextView nameTextView = convertView.findViewById(R.id.textView3); // Поле "TextView"
        TextView tabletCountTextView = convertView.findViewById(R.id.textView2); // Поле "1 таблетка"
        ImageView imageView = convertView.findViewById(R.id.imageView2); // Добавляем ImageView
        ImageView imageView2 = convertView.findViewById(R.id.imageView3);

        // Устанавливаем значения
        timeTextView.setText(currentMedicine.getTimeInterval());
        nameTextView.setText(currentMedicine.getName());
        if (currentMedicine.getType()!=null){
            tabletCountTextView.setText(currentMedicine.getTabletCount()+", "+currentMedicine.getType());
        }
        else
            tabletCountTextView.setText(currentMedicine.getTabletCount());

        if (filter != FilterType.ALL){
            convertView.setOnClickListener(v -> {

                new AlertDialog.Builder(context)
                        .setTitle("Удалить запись?")
                        .setPositiveButton("Ок", (dialog, which) -> {
                            // Удаляем запись из списка
                            filteredMedicines.remove(position);
                            medicines.remove(currentMedicine);

                            // Уведомляем адаптер об изменениях
                            notifyDataSetChanged();

                            // Удаляем запись из SharedPreferences
                            GlobalVariables.removeMedicineFromCache(currentMedicine, context);
                        })
                        .setNegativeButton("Отмена", null)
                        .show();
            });
        }
        else {
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");;

            try {
                // Преобразование строки времени в Date
                // Преобразуйте строку времени из currentMedicine в Date
                Date time1 = timeFormat.parse(currentMedicine.getTimeInterval());

                // Получите текущее время
                Calendar currentCal = Calendar.getInstance();

                // Убедитесь, что ваша сеть 'Calendar' для time1 только из времени
                Calendar time1Cal = Calendar.getInstance();
                time1Cal.setTime(time1);

                // Убедитесь, что вы исправно сравниваете часы и минуты
                if (currentCal.get(Calendar.HOUR_OF_DAY) < time1Cal.get(Calendar.HOUR_OF_DAY) ||
                        (currentCal.get(Calendar.HOUR_OF_DAY) == time1Cal.get(Calendar.HOUR_OF_DAY) &&
                                currentCal.get(Calendar.MINUTE) < time1Cal.get(Calendar.MINUTE))) {
                    imageView2.setImageResource(R.drawable.circle);
                    Log.d("TAG", "Текущее время раньше, чем " + currentMedicine.getTimeInterval());
                }
                else {
                    imageView2.setImageResource(R.drawable.reload);
                }

            } catch (Exception e) {
                Log.d("TAG", "ОШИБКАААААААААААААААААААААААААААААААААААААААА");

                e.printStackTrace();
            }
        }


        // Устанавливаем иконку
        if (currentMedicine.getType() != null) {
            imageView.setImageResource(R.drawable.painting); // Иконка таблеток
        } else {
            imageView.setImageResource(R.drawable.pill); // Иконка по умолчанию
        }


        return convertView;
    }
}

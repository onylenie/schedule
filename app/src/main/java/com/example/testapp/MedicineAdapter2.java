package com.example.testapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MedicineAdapter2 extends BaseAdapter {
    private Context context;
    private List<Medicine> medicines;

    public MedicineAdapter2(Context context, List<Medicine> medicines) {
        this.context = context;
        this.medicines = medicines;
    }

    @Override
    public int getCount() {
        return medicines.size();
    }

    @Override
    public Object getItem(int position) {
        return medicines.get(position);
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
            convertView = inflater.inflate(R.layout.list_rows2, parent, false);
        }

        // Получаем текущий элемент из списка
        Medicine currentMedicine = medicines.get(position);

        // Привязываем элементы разметки
        TextView timeTextView = convertView.findViewById(R.id.textView3); // Поле "Время"
        TextView nameTextView = convertView.findViewById(R.id.textView2); // Поле "TextView"
        TextView tabletCountTextView = convertView.findViewById(R.id.textView); // Поле "1 таблетка"

        // Устанавливаем значения
        timeTextView.setText(currentMedicine.getTimeInterval());
        nameTextView.setText(currentMedicine.getName());
        tabletCountTextView.setText(currentMedicine.getTabletCount());

        return convertView;
    }
}

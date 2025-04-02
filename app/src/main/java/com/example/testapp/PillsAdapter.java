package com.example.testapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PillsAdapter  extends BaseAdapter {

    private Context context;
    private static List<ListItem> items;
    private LayoutInflater inflater;

    public PillsAdapter(Context context, List<ListItem> items) {
        this.context = context;
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }

    public static String getSelectedItemLabel() {
        for (ListItem item : items) {
            if (item.isChecked()) {
                return item.getLabel();  // Возвращаем строковое значение элемента
            }
        }
        return null; // Если ничего не выбрано, возвращаем null
    }




    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.spinner_row_list, parent, false);
            holder = new ViewHolder();
            holder.icon = convertView.findViewById(R.id.imageView5);
            holder.label = convertView.findViewById(R.id.textView7);
            holder.checkBox = convertView.findViewById(R.id.checkBox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ListItem item = items.get(position);
        holder.icon.setImageResource(item.getIconResId());
        holder.label.setText(item.getLabel());
        holder.checkBox.setChecked(item.isChecked());

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                uncheckAllExcept(position);
                item.setChecked(true);
            } else {
                item.setChecked(false);
            }
            notifyDataSetChanged();  // Обновляем вид, чтобы применить изменения
        });

        return convertView;
    }

    private void uncheckAllExcept(int position) {
        for (int i = 0; i < items.size(); i++) {
            if (i != position) {
                items.get(i).setChecked(false);
            }
        }
    }


    private static class ViewHolder {
        ImageView icon;
        TextView label;
        CheckBox checkBox;
    }

}

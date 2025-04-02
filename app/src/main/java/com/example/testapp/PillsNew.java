package com.example.testapp;

import static com.example.testapp.GlobalVariables.medicines;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PillsNew extends AppCompatActivity {

    Integer page = 0;
    private static final String LOG_TAG = "myLogs";
    Integer c;
    private List<TextView> timeTextViewList = new ArrayList<>();





    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pills_new);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listView = findViewById(R.id.listViewPills);

        List<ListItem> items = new ArrayList<>();
        items.add(new ListItem(R.drawable.pills, "Таблетка(и)", false));
        items.add(new ListItem(R.drawable.pill, "Капсула(ы)", false));
        items.add(new ListItem(R.drawable.schedule, "Инъекция(и)", false));

        PillsAdapter adapter = new PillsAdapter(this, items);
        listView.setAdapter(adapter);

        EditText editTextNum = findViewById(R.id.editTextNumber);
        EditText editTextNum2 = findViewById(R.id.editTextNumber2);
        CheckBox checkbox2 = findViewById(R.id.checkBox2);
        CheckBox checkbox3 = findViewById(R.id.checkBox3);
        TextView tv = findViewById(R.id.textView6);
        Button next = findViewById(R.id.button2);
        ImageButton aButton = findViewById(R.id.imageButton12);

        aButton.setOnClickListener(v -> {
            if (page == 0)
                finish();

            page -= 1;

            pageChange();

        });
        /** переключение между страницами **/
        next.setOnClickListener(v -> {
            if (page >=3)
                page = 3;
            else {
                page += 1;
                pageChange();
            }
        });


        findViewById(R.id.imageButton8).setOnClickListener(v -> {
            try {
                int num = Integer.parseInt(editTextNum.getText().toString());
                editTextNum.setText(String.valueOf(num + 1));
            } catch (NumberFormatException e) {
                // Обработка возможной ошибки в случае нечислового ввода
                editTextNum.setText("0"); // или любое другое значение по умолчанию
            }
        });

        findViewById(R.id.imageButton9).setOnClickListener(v -> {
            try {
                int num = Integer.parseInt(editTextNum.getText().toString());
                if (num <= 1) {
                    editTextNum.setText("1");
                } else
                    editTextNum.setText(String.valueOf(num - 1));
            } catch (NumberFormatException e) {
                // Обработка возможной ошибки в случае нечислового ввода
                editTextNum.setText("1"); // или любое другое значение по умолчанию
            }
        });

        findViewById(R.id.imageButton10).setOnClickListener(v -> {
            try {
                int num = Integer.parseInt(editTextNum2.getText().toString());
                editTextNum2.setText(String.valueOf(num + 1));
            } catch (NumberFormatException e) {
                // Обработка возможной ошибки в случае нечислового ввода
                editTextNum2.setText("0"); // или любое другое значение по умолчанию
            }
        });

        findViewById(R.id.imageButton11).setOnClickListener(v -> {
            try {
                int num = Integer.parseInt(editTextNum2.getText().toString());
                if (num <= 1) {
                    editTextNum2.setText("1");
                } else
                    editTextNum2.setText(String.valueOf(num - 1));
            } catch (NumberFormatException e) {
                // Обработка возможной ошибки в случае нечислового ввода
                editTextNum2.setText("1"); // или любое другое значение по умолчанию
            }
        });


        checkbox2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (checkbox3.isChecked())
                findViewById(R.id.nums).setVisibility(View.VISIBLE);
            else
                findViewById(R.id.nums).setVisibility(View.GONE);
            checkbox3.setChecked(!checkbox2.isChecked()); // Отключаем checkbox3

        });

        checkbox3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (checkbox3.isChecked())
                findViewById(R.id.nums).setVisibility(View.VISIBLE);
            else
                findViewById(R.id.nums).setVisibility(View.GONE);
            checkbox2.setChecked(!checkbox3.isChecked()); // Отключаем checkbox2

        });


    }

    @SuppressLint("SetTextI18n")
    private void pageChange() {
        EditText editTextNum = findViewById(R.id.editTextNumber);
        EditText editTextNum2 = findViewById(R.id.editTextNumber2);
        CheckBox checkbox2 = findViewById(R.id.checkBox2);
        CheckBox checkbox3 = findViewById(R.id.checkBox3);
        TextView tv = findViewById(R.id.textView6);
        Button next = findViewById(R.id.button2);
        ImageButton aButton = findViewById(R.id.imageButton12);
        LinearLayout llButtonsContainer = findViewById(R.id.llButtonsContainer);
        ScrollView scrollView = findViewById(R.id.scrollPills);

        if (page == 0) {
            findViewById(R.id.editTextText).setVisibility(View.VISIBLE);
            findViewById(R.id.listViewPills).setVisibility(View.VISIBLE);
            findViewById(R.id.checkBox2).setVisibility(View.GONE);
            findViewById(R.id.checkBox3).setVisibility(View.GONE);
            aButton.setImageResource(R.drawable.close_mini);

        }
        /** вторая страница **/
        else if (page == 1) {
            next.setText("Далее");
            findViewById(R.id.editTextText).setVisibility(View.INVISIBLE);
            findViewById(R.id.listViewPills).setVisibility(View.GONE);
            tv.setText("Как часто вы \nпринимаете это \nлекарство?");

            findViewById(R.id.checkBox2).setVisibility(View.VISIBLE);
            findViewById(R.id.checkBox3).setVisibility(View.VISIBLE);

            aButton.setImageResource(R.drawable.arrow_left_mini);
            scrollView.setVisibility(View.GONE);
            findViewById(R.id.nums2).setVisibility(View.GONE);


        }
        /** третья страница **/
        else if (page == 2) {
            tv.setText("Когда вы хотите \nполучить напоминание?");
            next.setText("Закончить");
            findViewById(R.id.checkBox2).setVisibility(View.GONE);
            findViewById(R.id.checkBox3).setVisibility(View.GONE);
            findViewById(R.id.nums).setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
            findViewById(R.id.nums2).setVisibility(View.VISIBLE);



            if (checkbox2.isChecked()) {
                c = 1;
            } else {
                c = Integer.parseInt(editTextNum.getText().toString());
            }
            // Очищаем контейнер перед добавлением новых элементов
            llButtonsContainer.removeAllViews();

            // Создаем нужное количество кнопок
            for (int i = 0; i < c; i++) {
                int buttonIndex = i + 1;

                // Создаем кнопку
                Button timeButton = new Button(this);
                timeButton.setText("Выбрать время " + buttonIndex);
                timeButton.setTag("button_" + buttonIndex);
                timeButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                // Создаем TextView для отображения времени
                TextView timeTextView = new TextView(this);
                timeTextView.setText("Время " + buttonIndex + ": не выбрано");
                timeTextView.setId(View.generateViewId());
                timeTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                timeTextView.setTextColor(Color.BLACK);
                timeTextView.setPadding(16, 8, 8, 16);

                // Лэйаут параметры для TextView с отступом
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                layoutParams.setMargins(0, 0, 0, 10);
                timeTextView.setLayoutParams(layoutParams);

                // Добавляем обработчик нажатий для кнопки
                timeButton.setOnClickListener(view -> {
                    showTimePickerDialog(timeTextView);
                });

                // Добавляем кнопку и текстовое поле в контейнер
                llButtonsContainer.addView(timeButton);
                llButtonsContainer.addView(timeTextView);

                // Сохраняем TextView в список
                timeTextViewList.add(timeTextView);
            }


        }
        else if(page==3)
        {
            for (int i = 0; i < c; i++) {

                String timeData = timeTextViewList.get(i).getText().toString(); // Получаем данные времени
                EditText edText = findViewById(R.id.editTextText);
                String type;

                String selectedItem = PillsAdapter.getSelectedItemLabel();
                if (selectedItem==null){
                    type = "Таблетка(и)";
                }
                else {
                    type = selectedItem;
                }

                // Получите текст из EditText
                String text = edText.getText().toString().trim();
                if(text.isEmpty()){
                    text = "Лекарство";

                }

                GlobalVariables.addMedicineToCache(new Medicine(timeData+"", text+"",editTextNum2.getText().toString()+" "+type), this);
                //GlobalVariables.medicines.add(new Medicine(timeData+"", text+"",editTextNum.getText().toString()+" таблетка(и)"));

                Log.d(LOG_TAG, edText.getText().toString()+ editTextNum2.getText().toString()+" "+type+ timeData);

                Toast.makeText(this, "Успешно сохранено!", Toast.LENGTH_SHORT).show();
                finish();
                }
            }
        }

        //setContentView(llButtonsContainer);
        }




    private void showTimePickerDialog(TextView targetView) {
        // Получаем текущее время
        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);

        // Открываем TimePickerDialog
        @SuppressLint("SetTextI18n") TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (TimePickerDialog.OnTimeSetListener) (view, hourOfDay, minute) -> {
                    // Устанавливаем выбранное время в текстовое поле
                    @SuppressLint("DefaultLocale") String selectedTime = String.format("%02d:%02d", hourOfDay, minute);
                    targetView.setText(selectedTime);
                },
                currentHour,
                currentMinute,
                true // 24-часовой формат
        );

        timePickerDialog.show();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        page = 0;
    }
}


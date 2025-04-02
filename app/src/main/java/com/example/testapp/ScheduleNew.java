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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ScheduleNew extends AppCompatActivity {

    Integer page = 0;
    private static final String LOG_TAG = "myLogs";
    Integer c;
    private List<TextView> timeTextViewList = new ArrayList<>();
    private static List<Switch> swCont = new ArrayList<>();
    private static List<String> days = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_schedule_new);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        EditText editTextText = findViewById(R.id.editTextTextName);
        TextView tv = findViewById(R.id.textViewSch);
        Button next = findViewById(R.id.buttonSch);
        ImageButton aButton = findViewById(R.id.imageButtonSchBack);

        Switch switch1 = findViewById(R.id.switch1);
        Switch switch2 = findViewById(R.id.switch2);
        Switch switch3 = findViewById(R.id.switch3);
        Switch switch4 = findViewById(R.id.switch4);
        Switch switch5 = findViewById(R.id.switch5);
        Switch switch6 = findViewById(R.id.switch6);
        Switch switch7 = findViewById(R.id.switch7);

        swCont.clear();
        days.clear();

        swCont.add(switch1);
        swCont.add(switch2);
        swCont.add(switch3);
        swCont.add(switch4);
        swCont.add(switch5);
        swCont.add(switch6);
        swCont.add(switch7);

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
    }

    @SuppressLint("SetTextI18n")
    private void pageChange() {
        TextView tv = findViewById(R.id.textViewSch);
        Button next = findViewById(R.id.buttonSch);
        ImageButton aButton = findViewById(R.id.imageButtonSchBack);
        LinearLayout llButtonsContainer = findViewById(R.id.llButtonsContainer2);
        ConstraintLayout cl = findViewById(R.id.constraintLayout4);


        if (page == 0) {
            findViewById(R.id.editTextTextName).setVisibility(View.VISIBLE);
            aButton.setImageResource(R.drawable.close_mini);
            cl.setVisibility(View.GONE);


        }
        /** вторая страница **/
        else if (page == 1) {
            next.setText("Далее");
            findViewById(R.id.editTextTextName).setVisibility(View.INVISIBLE);
            tv.setText("Как часто проходит эта \nактивность");

            aButton.setImageResource(R.drawable.arrow_left_mini);
            llButtonsContainer.setVisibility(View.GONE);
            cl.setVisibility(View.VISIBLE);


        }

        /** третья страница **/
        else if (page == 2) {
            tv.setText("В какое время и день \nвы планируете?");
            next.setText("Закончить");
            llButtonsContainer.setVisibility(View.VISIBLE);
            cl.setVisibility(View.GONE);


            // Очищаем контейнер перед добавлением новых элементов
            llButtonsContainer.removeAllViews();

            int i=0;

            // Создаем нужное количество кнопок
            for (Switch sw:swCont ){

                if(sw.isChecked()){
                    Log.d(LOG_TAG, "СВИИИИИИИЧ" + i );
                    days.add(sw.getText().toString());
                    i++;
                    int buttonIndex = i;

                    // Создаем кнопку
                    Button timeButton = new Button(this);
                    timeButton.setText("Выбрать время для " + sw.getText().toString());
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

            if (i==0){

                Log.d(LOG_TAG, "НЕ СВИИИИИИИЧ" + i);
                days.add("Каждый день");

                int buttonIndex = 1;

                // Создаем кнопку
                Button timeButton = new Button(this);
                timeButton.setText("Выбрать время для активности");
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
            Log.d(LOG_TAG, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            int i=-1;
            for (String day:days) {
                i++;
                Log.d(LOG_TAG, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                String timeData = timeTextViewList.get(i).getText().toString(); // Получаем данные времени
                EditText edText = findViewById(R.id.editTextTextName);

                // Получите текст из EditText
                String text = edText.getText().toString().trim();
                if(text.isEmpty()){
                    text = "Занятие";
                }

                GlobalVariables.addMedicineToCache(new Medicine(timeData+"", text+"","активность",day+""), this);
                //GlobalVariables.medicines.add(new Medicine(timeData+"", text+"","активность",day+""));

                Log.d(LOG_TAG, edText.getText().toString()+ " " + timeData);

                for (Medicine a:medicines
                ) {
                    Log.d(LOG_TAG,a.getName().toString()+a.getTabletCount().toString()+a.getTimeInterval());
                    Toast.makeText(this, "Успешно сохранено!", Toast.LENGTH_SHORT).show();
                    finish();

                }

                Log.d(LOG_TAG, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
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


}
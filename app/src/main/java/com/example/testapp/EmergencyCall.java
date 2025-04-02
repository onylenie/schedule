package com.example.testapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EmergencyCall extends AppCompatActivity {

    private static final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_emergency_call);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ImageButton imageButton = findViewById(R.id.imageButtonEmCall);

        // Устанавливаем обработчик события нажатия

        imageButton.setOnClickListener(v -> {
            // Закрываем текущую активность
            finish();
        });


        ImageButton imageButton2 = findViewById(R.id.imageButton7);

        // Устанавливаем обработчик события нажатия
        imageButton2.setOnClickListener(v -> {
            Toast.makeText(this, "Экстренный вызов начат!", Toast.LENGTH_LONG).show();
            Log.d(LOG_TAG, "Вызов");

        });


    }


}
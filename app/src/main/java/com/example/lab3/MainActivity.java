package com.example.lab3;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DayCaseFragment dayCaseFragment = new DayCaseFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_view,dayCaseFragment)
                .commit();

        // Находим элементы ввода и кнопку
        TimePicker timeEditText = findViewById(R.id.timePicer);
        EditText titleEditText = findViewById(R.id.name);
        EditText descriptionEditText = findViewById(R.id.desct);
        Button addButton = findViewById(R.id.addBtn);

        // Добавляем обработчик события для кнопки "Добавить"
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем данные из элементов ввода
                String time = timeEditText.getHour() + ":"+ timeEditText.getMinute();
                String title = titleEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                // Создаем новый объект DayCase и добавляем его в список
                DayCase newDayCase = new DayCase(time, title, description, R.drawable.food);
                // Добавление в список
                dayCaseFragment.addDayCase(newDayCase);
                // Очищаем поля ввода
                titleEditText.setText("");
                descriptionEditText.setText("");
            }
        });
    }
}
package com.example.lab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DayCaseAdapter extends BaseAdapter {

    private Context context; // Контекст приложения
    private List<DayCase> dayCaseList; // Список объектов DayCase

    public DayCaseAdapter(Context context, List<DayCase> dayCaseList) {
        this.context = context; // Инициализация контекста
        this.dayCaseList = dayCaseList; // Инициализация списка DayCase
    }

    @Override
    public int getCount() {
        return dayCaseList.size(); // Возвращает количество элементов в списке
    }

    @Override
    public Object getItem(int position) {
        return dayCaseList.get(position); // Возвращает объект DayCase по указанной позиции
    }

    @Override
    public long getItemId(int position) {
        return position; // Возвращает идентификатор элемента (позицию) в списке
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.day_case_item, parent, false);
            // Если convertView пустой, создаем новый макет из day_case_item.xml
        }

        DayCase dayCase = dayCaseList.get(position); // Получаем объект DayCase из списка

        ImageView imageView = convertView.findViewById(R.id.imageView); // Получаем ImageView
        TextView timeTextView = convertView.findViewById(R.id.timeTextView); // Получаем TextView для времени
        TextView titleTextView = convertView.findViewById(R.id.titleTextView); // Получаем TextView для заголовка
        TextView descriptionTextView = convertView.findViewById(R.id.descriptionTextView); // Получаем TextView для описания

        imageView.setImageResource(dayCase.getImageResId()); // Устанавливаем изображение
        timeTextView.setText(dayCase.getTime()); // Устанавливаем время
        titleTextView.setText(dayCase.getTitle()); // Устанавливаем заголовок
        descriptionTextView.setText(dayCase.getDescription()); // Устанавливаем описание

        return convertView; // Возвращаем заполненный макет для отображения элемента списка
    }
}

package com.example.lab3;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.AdapterView;
import android.widget.ProgressBar;

public class DayCaseFragment extends Fragment {

    private List<DayCase> dayCases; // Список дел
    private DayCaseAdapter adapter; // Адаптер для списка
    private ListView listView; // ListView
    private ProgressBar loadingProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Задаем макет для фрагмента, например, list_fragment.xml
        View view = inflater.inflate(R.layout.day_case_list_fragment, container, false);

        // Находим ListView и progresbar в макете фрагмента
        loadingProgressBar = view.findViewById(R.id.loadingProgressBar);
        listView = view.findViewById(R.id.listView);
        // Запускаем анимацию загрузки
        showLoadingAnimation();
        // Создаем список дел (объекты класса DayCase) и добавляем в него элементы
        dayCases = new ArrayList<>();
        dayCases.add(new DayCase("10:00 AM", "Посещение врача", "Запись к врачу", R.drawable.medicine));
        dayCases.add(new DayCase("12:30 PM", "Обед", "Обед с друзьями", R.drawable.food));

        // Создаем адаптер для связывания списка дел с ListView
        adapter = new DayCaseAdapter(requireContext(), dayCases);

        // Устанавливаем адаптер в ListView
        listView.setAdapter(adapter);

        // Устанавливаем обработчик долгого нажатия на элемент списка
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDeleteConfirmationDialog(position);
                return true;
            }
        });
        return view;
    }
    // Метод для отображения ProgressBar и скрытия ListView
    private void showLoadingAnimation() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadingProgressBar.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
            }
        });

        // Создайте и запуск потока для имитации задержки загрузки
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Имитация задержки на загрузку данных (1-2 секунд)
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // По окончании загрузки данных
                // Переключаем видимость ProgressBar и ListView
                hideLoadingAnimation();
            }
        }).start();
    }

    // Метод для скрытия ProgressBar и отображения ListView
    private void hideLoadingAnimation() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadingProgressBar.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
            }
        });
    }

    // Метод для безопасного доступа к интерфейсу из главного потока
    private void runOnUiThread(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }
    public  void addDayCase(DayCase dc){
        dayCases.add(dc);
        adapter.notifyDataSetChanged();

        // Показать анимацию загрузки снова
        showLoadingAnimation();
    }
    private void showDeleteConfirmationDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage("Вы уверены, что хотите удалить этот элемент?")
                .setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Удаление элемента из списка
                        dayCases.remove(position);
                        adapter.notifyDataSetChanged(); // Уведомить адаптер о изменениях в данных

                        // Показать анимацию загрузки снова
                        showLoadingAnimation();
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Отмена удаления
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }
}

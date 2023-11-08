package com.example.lab3;

public class DayCase {
    private String time;
    private String title;
    private String description;
    private int imageResId;

    public DayCase(String time, String title, String description, int imageResId) {
        this.time = time;
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }
}

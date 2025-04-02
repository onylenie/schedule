package com.example.testapp.ui;

public class Day {
    private String dayOfWeek;
    private int dayNumber;

    public Day(String dayOfWeek, int dayNumber) {
        this.dayOfWeek = dayOfWeek;
        this.dayNumber = dayNumber;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public int getDayNumber() {
        return dayNumber;
    }
}

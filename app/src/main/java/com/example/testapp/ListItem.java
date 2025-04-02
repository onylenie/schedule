package com.example.testapp;

public class ListItem {
    private int iconResId;
    private String label;
    private boolean isChecked;

    public ListItem(int iconResId, String label, boolean isChecked) {
        this.iconResId = iconResId;
        this.label = label;
        this.isChecked = isChecked;
    }

    public int getIconResId() {
        return iconResId;
    }

    public String getLabel() {
        return label;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}

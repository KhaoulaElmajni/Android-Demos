package me.elmajni.contactappsqliteroom;

import android.app.Application;

public class ThemeSettings extends Application {
    public static final String PREFERENCES = "preferences";
    public static final String CURRENT_THEME = "currentTheme";
    public static final String LIGHT_THEME = "lightTheme";
    public static final String DARK_THEME = "darkTheme";
    public static final String RED_THEME = "redTheme";
    public static final String BLUE_THEME = "blueTheme";
    public static final String ORANGE_THEME = "orangeTheme";
    public static final String PURPLE_THEME = "purpleTheme";
    public static final String GREEN_THEME = "greenTheme";

    private String currentTheme;

    public String getCurrentTheme() {
        return currentTheme;
    }

    public void setCurrentTheme(String currentTheme) {
        this.currentTheme = currentTheme;
    }
}

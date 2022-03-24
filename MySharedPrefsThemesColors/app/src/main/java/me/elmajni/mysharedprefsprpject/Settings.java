package me.elmajni.mysharedprefsprpject;

import android.app.Application;

public class Settings extends Application {
    public static final String PREFERENCES = "preferences";
    public static final String CUSTOM_THEME = "customTheme";
    public static final String LIGHT_THEME = "lightTheme";
    public static final String DARK_THEME = "darkTheme";
    public static final String RED_THEME = "redTheme";
    public static final String BLUE_THEME = "blueTheme";
    public static final String ORANGE_THEME = "orangeTheme";
    public static final String PURPLE_THEME = "purpleTheme";
    public static final String GREEN_THEME = "greenTheme";

    private String customTheme;

    public String getCustomTheme() {
        return customTheme;
    }

    public void setCustomTheme(String customTheme) {
        this.customTheme = customTheme;
    }
}

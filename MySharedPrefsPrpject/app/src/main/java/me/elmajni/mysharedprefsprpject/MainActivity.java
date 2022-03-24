package me.elmajni.mysharedprefsprpject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    private View parentView;
    private Switch switchTheme;
    private TextView theme, title;
    private Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings = (Settings) getApplication();

        setAllViews();
        loadSharedPreferences();
        initSwitchListener();
    }

    private void setAllViews() {
        parentView = findViewById(R.id.parentView);
        switchTheme = findViewById(R.id.switchTheme);
        theme = findViewById(R.id.theme);
        title = findViewById(R.id.title);
    }

    private void loadSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(Settings.PREFERENCES,MODE_PRIVATE);
        String theme = sharedPreferences.getString(Settings.CUSTOM_THEME,Settings.LIGHT_THEME);
        settings.setCustomTheme(theme);
    }

    private void initSwitchListener() {
        switchTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked)
                    settings.setCustomTheme(Settings.DARK_THEME);
                else
                    settings.setCustomTheme(Settings.LIGHT_THEME);

                SharedPreferences.Editor editor = getSharedPreferences(Settings.PREFERENCES, MODE_PRIVATE).edit();
                editor.putString(Settings.CUSTOM_THEME,settings.getCustomTheme());
                editor.apply();
                updateView();
            }
        });
    }
    private void updateView() {
        final int black = ContextCompat.getColor(this,R.color.black);
        final int white = ContextCompat.getColor(this,R.color.white);

        if (settings.getCustomTheme().equals(Settings.DARK_THEME)){
            title.setTextColor(white);
            theme.setTextColor(white);
            theme.setText("Dark");
            parentView.setBackgroundColor(black);
            switchTheme.setChecked(true);
        }else {
            title.setTextColor(black);
            theme.setTextColor(black);
            theme.setText("Light");
            parentView.setBackgroundColor(white);
            switchTheme.setChecked(false);
        }
    }
}
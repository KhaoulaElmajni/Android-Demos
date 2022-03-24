package me.elmajni.mysharedprefsprpject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private View parentView;
    private RadioGroup radioGroupThemes;
    private RadioButton light;
    private RadioButton dark;
    private RadioButton red;
    private RadioButton blue;
    private RadioButton orange;
    private RadioButton purple;
    private RadioButton green;
    private TextView theme, title;
    private Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings = (Settings) getApplication();

        setAllViews();
        loadSharedPreferences();
        initTheme();
    }

    private void setAllViews() {
        parentView = findViewById(R.id.parentView);
        theme = findViewById(R.id.theme);
        title = findViewById(R.id.title);
        radioGroupThemes = findViewById(R.id.radioGroup_themes);
        light = findViewById(R.id.light);
        dark = findViewById(R.id.dark);
        red = findViewById(R.id.red);
        blue = findViewById(R.id.blue);
        orange = findViewById(R.id.orange);
        purple = findViewById(R.id.purple);
        green = findViewById(R.id.green);
    }

    private void loadSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(Settings.PREFERENCES,MODE_PRIVATE);
        String theme = sharedPreferences.getString(Settings.CUSTOM_THEME,Settings.LIGHT_THEME);
        settings.setCustomTheme(theme);
    }

    private void initTheme() {
        // Checked RadioButton ID.
        int checkedRadioButtonId = radioGroupThemes.getCheckedRadioButtonId();
        settings.setCustomTheme(Settings.LIGHT_THEME);

        // Check which radio button was clicked
        switch(checkedRadioButtonId) {
            case R.id.light:
                settings.setCustomTheme(Settings.LIGHT_THEME);
                    break;
            case R.id.dark:
                settings.setCustomTheme(Settings.DARK_THEME);
                    break;
            case R.id.red:
                settings.setCustomTheme(Settings.RED_THEME);
                break;
            case R.id.blue:
                settings.setCustomTheme(Settings.BLUE_THEME);
                break;
            case R.id.orange:
                settings.setCustomTheme(Settings.ORANGE_THEME);
                break;
            case R.id.purple:
                settings.setCustomTheme(Settings.PURPLE_THEME);
                break;
            case R.id.green:
                settings.setCustomTheme(Settings.GREEN_THEME);
                break;
            default:settings.setCustomTheme(Settings.LIGHT_THEME);
        }
        SharedPreferences.Editor editor = getSharedPreferences(Settings.PREFERENCES, MODE_PRIVATE).edit();
        editor.putString(Settings.CUSTOM_THEME,settings.getCustomTheme());
        editor.apply();
        updateView();
       /* switchTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        });*/
    }
    private void updateView() {
        final int black = ContextCompat.getColor(this,R.color.black);
        final int white = ContextCompat.getColor(this,R.color.white);
        final int red = ContextCompat.getColor(this,R.color.red);
        final int blue = ContextCompat.getColor(this,R.color.blue);
        final int orange = ContextCompat.getColor(this,R.color.orange);
        final int purple = ContextCompat.getColor(this,R.color.purple);
        final int green = ContextCompat.getColor(this,R.color.green);

        switch(settings.getCustomTheme()){
            case Settings.DARK_THEME:
                title.setTextColor(white);
                theme.setTextColor(white);
                theme.setText("Dark");
                parentView.setBackgroundColor(black);
               //radioGroupThemes.check(checkedRadioButtonId);
                break;
            case Settings.LIGHT_THEME:
                title.setTextColor(black);
                theme.setTextColor(black);
                theme.setText("Light");
                parentView.setBackgroundColor(white);
                //radioGroupThemes.check(checkedRadioButtonId);
                break;
            case Settings.RED_THEME:
                title.setTextColor(white);
                theme.setTextColor(white);
                theme.setText("RED");
                parentView.setBackgroundColor(red);
                //radioGroupThemes.check(checkedRadioButtonId);
                break;
            case Settings.BLUE_THEME:
                title.setTextColor(white);
                theme.setTextColor(white);
                theme.setText("BLUE");
                parentView.setBackgroundColor(blue);
                //radioGroupThemes.check(checkedRadioButtonId);
                break;
            case Settings.ORANGE_THEME:
                title.setTextColor(black);
                theme.setTextColor(black);
                theme.setText("ORANGE");
                parentView.setBackgroundColor(orange);
                //radioGroupThemes.check(checkedRadioButtonId);
                break;
            case Settings.PURPLE_THEME:
                title.setTextColor(white);
                theme.setTextColor(white);
                theme.setText("PURPLE");
                parentView.setBackgroundColor(purple);
                //radioGroupThemes.check(checkedRadioButtonId);
                break;
            case Settings.GREEN_THEME:
                title.setTextColor(white);
                theme.setTextColor(white);
                theme.setText("GREEN");
                parentView.setBackgroundColor(green);
                //radioGroupThemes.check(checkedRadioButtonId);
                break;
        }
    }
}
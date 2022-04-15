package me.elmajni.contactappsqliteroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ThemesActivity extends AppCompatActivity {

    private View parentView;
    private RadioGroup radioGroupThemes;
    private RadioButton lightRB;
    private RadioButton darkRB;
    private RadioButton redRB;
    private RadioButton blueRB;
    private RadioButton orangeRB;
    private RadioButton purpleRB;
    private RadioButton greenRB;
    private TextView theme, title, info;
    private ThemeSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme_settings);
        settings = (ThemeSettings) getApplication();

        setAllViews();
        initTheme();
        loadSettings();
    }


    private void setAllViews() {
        parentView = findViewById(R.id.parentView);
        theme = findViewById(R.id.theme);
        title = findViewById(R.id.title);
        radioGroupThemes = findViewById(R.id.radioGroup_themes);
        lightRB = findViewById(R.id.light);
        darkRB = findViewById(R.id.dark);
        redRB = findViewById(R.id.red);
        blueRB = findViewById(R.id.blue);
        orangeRB = findViewById(R.id.orange);
        purpleRB = findViewById(R.id.purple);
        greenRB = findViewById(R.id.green);
        info = findViewById(R.id.info);
    }

    private void loadSettings() {
        SharedPreferences sharedPreferences = getSharedPreferences(ThemeSettings.PREFERENCES,MODE_PRIVATE);
        String theme = sharedPreferences.getString(ThemeSettings.CURRENT_THEME,ThemeSettings.LIGHT_THEME);
        settings.setCurrentTheme(theme);

        switch (theme){
            case ThemeSettings.LIGHT_THEME:
                radioGroupThemes.check(R.id.light);break;
            case ThemeSettings.DARK_THEME:
                radioGroupThemes.check(R.id.dark);break;
            case ThemeSettings.RED_THEME:
                radioGroupThemes.check(R.id.red);break;
            case ThemeSettings.BLUE_THEME:
                radioGroupThemes.check(R.id.blue);break;
            case ThemeSettings.ORANGE_THEME:
                radioGroupThemes.check(R.id.orange);break;
            case ThemeSettings.PURPLE_THEME:
                radioGroupThemes.check(R.id.purple);break;
            case ThemeSettings.GREEN_THEME:
                radioGroupThemes.check(R.id.green);break;
        }

    }

    private void initTheme() {
        // Checked RadioButton ID.
        settings.setCurrentTheme(ThemeSettings.LIGHT_THEME);
        radioGroupThemes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                // Check which radio button was clicked
                switch(checkedId) {
                    case R.id.light:
                        settings.setCurrentTheme(ThemeSettings.LIGHT_THEME);
                        break;
                    case R.id.dark:
                        settings.setCurrentTheme(ThemeSettings.DARK_THEME);
                        break;
                    case R.id.red:
                        settings.setCurrentTheme(ThemeSettings.RED_THEME);
                        break;
                    case R.id.blue:
                        settings.setCurrentTheme(ThemeSettings.BLUE_THEME);
                        break;
                    case R.id.orange:
                        settings.setCurrentTheme(ThemeSettings.ORANGE_THEME);
                        break;
                    case R.id.purple:
                        settings.setCurrentTheme(ThemeSettings.PURPLE_THEME);
                        break;
                    case R.id.green:
                        settings.setCurrentTheme(ThemeSettings.GREEN_THEME);
                        break;
                    default:settings.setCurrentTheme(ThemeSettings.LIGHT_THEME);
                }
                SharedPreferences.Editor editor = getSharedPreferences(ThemeSettings.PREFERENCES, MODE_PRIVATE).edit();
                editor.putString(ThemeSettings.CURRENT_THEME,settings.getCurrentTheme());
                editor.apply();
                changeTheme();
            }
        });
    }
    private void changeTheme() {
        final int black = ContextCompat.getColor(this,R.color.black);
        final int white = ContextCompat.getColor(this,R.color.white);
        final int red = ContextCompat.getColor(this,R.color.red);
        final int blue = ContextCompat.getColor(this,R.color.blue);
        final int orange = ContextCompat.getColor(this,R.color.orange);
        final int purple = ContextCompat.getColor(this,R.color.purple);
        final int green = ContextCompat.getColor(this,R.color.green);

        switch(settings.getCurrentTheme()){
            case ThemeSettings.DARK_THEME:
                title.setTextColor(white);
                theme.setTextColor(white);
                info.setTextColor(white);
                theme.setText("Dark Theme!");
                darkRB.setTextColor(white);
                changeRBColor(white);
                parentView.setBackgroundColor(black);
                break;
            case ThemeSettings.LIGHT_THEME:
                title.setTextColor(black);
                theme.setTextColor(black);
                theme.setText("Light Theme!");
                info.setTextColor(black);
                lightRB.setTextColor(black);
                parentView.setBackgroundColor(white);
                changeRBColor(black);
                break;
            case ThemeSettings.RED_THEME:
                title.setTextColor(white);
                theme.setTextColor(white);
                theme.setText("RED Theme!");
                info.setTextColor(white);
                changeRBColor(white);
                parentView.setBackgroundColor(red);
                break;
            case ThemeSettings.BLUE_THEME:
                title.setTextColor(white);
                theme.setTextColor(white);
                theme.setText("BLUE Theme!");
                info.setTextColor(white);
                changeRBColor(white);
                parentView.setBackgroundColor(blue);
                break;
            case ThemeSettings.ORANGE_THEME:
                title.setTextColor(white);
                theme.setTextColor(white);
                theme.setText("ORANGE Theme!");
                info.setTextColor(white);
                changeRBColor(white);
                parentView.setBackgroundColor(orange);
                break;
            case ThemeSettings.PURPLE_THEME:
                title.setTextColor(white);
                theme.setTextColor(white);
                theme.setText("PURPLE Theme!");
                info.setTextColor(white);
                changeRBColor(white);
                parentView.setBackgroundColor(purple);
                break;
            case ThemeSettings.GREEN_THEME:
                title.setTextColor(white);
                theme.setTextColor(white);
                theme.setText("GREEN Theme!");
                info.setTextColor(white);
                changeRBColor(white);
                parentView.setBackgroundColor(green);
                break;
        }
    }
    void changeRBColor(int color){
        lightRB.setTextColor(color);
        darkRB.setTextColor(color);
        redRB.setTextColor(color);
        blueRB.setTextColor(color);
        orangeRB.setTextColor(color);
        purpleRB.setTextColor(color);
        greenRB.setTextColor(color);
    }

}
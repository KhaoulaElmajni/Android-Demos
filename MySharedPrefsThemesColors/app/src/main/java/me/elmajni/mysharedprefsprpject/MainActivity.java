package me.elmajni.mysharedprefsprpject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
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
    private Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings = (Settings) getApplication();

        setAllViews();
        initTheme();
        loadSharedPreferences();
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

    private void loadSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(Settings.PREFERENCES,MODE_PRIVATE);
        String theme = sharedPreferences.getString(Settings.CUSTOM_THEME,Settings.LIGHT_THEME);
        settings.setCustomTheme(theme);

        switch (theme){
            case Settings.LIGHT_THEME:
                radioGroupThemes.check(R.id.light);break;
            case Settings.DARK_THEME:
                radioGroupThemes.check(R.id.dark);break;
            case Settings.RED_THEME:
                radioGroupThemes.check(R.id.red);break;
            case Settings.BLUE_THEME:
                radioGroupThemes.check(R.id.blue);break;
            case Settings.ORANGE_THEME:
                radioGroupThemes.check(R.id.orange);break;
            case Settings.PURPLE_THEME:
                radioGroupThemes.check(R.id.purple);break;
            case Settings.GREEN_THEME:
                radioGroupThemes.check(R.id.green);break;
        }

    }

    private void initTheme() {
        // Checked RadioButton ID.
        settings.setCustomTheme(Settings.LIGHT_THEME);
        radioGroupThemes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                // Check which radio button was clicked
                switch(checkedId) {
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
            }
        });
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
                info.setTextColor(white);
                theme.setText("Dark");
                darkRB.setTextColor(white);
                changeRBColor(white);
                parentView.setBackgroundColor(black);
               //radioGroupThemes.check(checkedRadioButtonId);
                break;
            case Settings.LIGHT_THEME:
                title.setTextColor(black);
                theme.setTextColor(black);
                theme.setText("Light");
                info.setTextColor(black);
                lightRB.setTextColor(black);
                parentView.setBackgroundColor(white);
                changeRBColor(black);
                //radioGroupThemes.check(checkedRadioButtonId);
                break;
            case Settings.RED_THEME:
                title.setTextColor(white);
                theme.setTextColor(white);
                theme.setText("RED");
                info.setTextColor(white);
                changeRBColor(white);
                parentView.setBackgroundColor(red);
                //radioGroupThemes.check(checkedRadioButtonId);
                break;
            case Settings.BLUE_THEME:
                title.setTextColor(white);
                theme.setTextColor(white);
                theme.setText("BLUE");
                info.setTextColor(white);
                changeRBColor(white);
                parentView.setBackgroundColor(blue);
                //radioGroupThemes.check(checkedRadioButtonId);
                break;
            case Settings.ORANGE_THEME:
                title.setTextColor(white);
                theme.setTextColor(white);
                theme.setText("ORANGE");
                info.setTextColor(white);
                changeRBColor(white);
                parentView.setBackgroundColor(orange);
                //radioGroupThemes.check(checkedRadioButtonId);
                break;
            case Settings.PURPLE_THEME:
                title.setTextColor(white);
                theme.setTextColor(white);
                theme.setText("PURPLE");
                info.setTextColor(white);
                changeRBColor(white);
                parentView.setBackgroundColor(purple);
                //radioGroupThemes.check(checkedRadioButtonId);
                break;
            case Settings.GREEN_THEME:
                title.setTextColor(white);
                theme.setTextColor(white);
                theme.setText("GREEN");
                info.setTextColor(white);
                changeRBColor(white);
                parentView.setBackgroundColor(green);
                //radioGroupThemes.check(checkedRadioButtonId);
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
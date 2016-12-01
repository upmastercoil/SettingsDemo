package tech.android.tcmp13.settingsdemo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by tcmp13-t on 11/30/2016.
 */
public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String IS_HAPPY_KEY = "is_happy";
    public static final String FAVOURITE_CAKE_KEY = "favourite_cake";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);
    }

    @Override
    protected void onResume() {

        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {

        super.onPause();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if (key.equals(IS_HAPPY_KEY)) {
            boolean happy = sharedPreferences.getBoolean(key, false);
            String cake = sharedPreferences.getString(FAVOURITE_CAKE_KEY, getString(R.string.black_forest));
            startGreeting(happy, cake);
        } else if (key.equals(FAVOURITE_CAKE_KEY)) {
            Preference preference = findPreference(key);
            boolean happy = sharedPreferences.getBoolean(IS_HAPPY_KEY, false);
            String cake = sharedPreferences.getString(key, getString(R.string.black_forest));
            preference.setSummary(cake);
            startGreeting(happy, cake);
        }
    }

    public void startGreeting(boolean happy, String cake) {

        greetHello(happy ? "Oh I love meself a piece of dat " + cake : "What is the point of " + cake + " and life...");
    }

    private void greetHello(String greeting) {

        Toast.makeText(this, greeting, Toast.LENGTH_LONG).show();
    }
}

package tech.android.tcmp13.settingsdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreferenceManager.setDefaultValues(this, R.xml.preference, false);
    }

    public void startGreeting(View view) {

        SharedPreferences userPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean happy = userPreferences.getBoolean(SettingsActivity.IS_HAPPY_KEY, false);
        String cake = userPreferences.getString(SettingsActivity.FAVOURITE_CAKE_KEY, getString(R.string.black_forest));
        greetHello(happy ? "Oh I love meself a piece of dat " + cake : "What is the point of " + cake + " and life...");
    }

    private void greetHello(String greeting) {

        Toast.makeText(this, greeting, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

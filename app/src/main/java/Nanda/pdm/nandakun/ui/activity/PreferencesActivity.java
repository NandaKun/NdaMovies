package Nanda.pdm.nandakun.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import Nanda.pdm.nandakun.R;
import Nanda.pdm.nandakun.nanda;
import Nanda.pdm.nandakun.ui.activity.base.ToolbarActivity;
import Nanda.pdm.nandakun.ui.fragment.PreferencesFragment;

/**
 * Class used to store shared preferences of some details of the application
 * Uses a PreferencesFragment
 */
public class PreferencesActivity extends ToolbarActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar.setTitle(R.string.preferences);
        this.enableBackButton();

        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);

        getFragmentManager().beginTransaction()
                            .replace(R.id.content, new PreferencesFragment()).commit();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(getResources().getString(R.string.soon_periodicity))){
            int days = Integer.parseInt(sharedPreferences.getString(key, "7"));
            ((nanda)getApplication()).refreshTheatersAlarm(days);
        }
    }
}

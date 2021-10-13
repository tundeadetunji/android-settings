package com.inovationware.sharedpreferencesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity implements HomePageInputFragment.NoticeDialogListener {

    TextView textHomePage;

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPref = getSharedPreferences(getString(R.string.pref_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        textHomePage = findViewById(R.id.textHomePage);
        //read from shared preferences and set the text by default
        textHomePage.setText(sharedPref.getString("home_page", "Not Set (Click to set it)"));
        textHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePageInputFragment fragment = new HomePageInputFragment();
                fragment.show(getSupportFragmentManager(), "HomeInput");
            }
        });
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Dialog dialogView = dialog.getDialog();
        TextView textView = dialogView.findViewById(R.id.textNewHomePage);
        if (textView.getText().toString().trim().length() < 1) return;
        editor.putString("home_page", textView.getText().toString()).apply();
        textHomePage.setText(textView.getText().toString());
        Toast.makeText(this, "Home Page has been set.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

}
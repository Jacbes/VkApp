package dev.jacbes.vkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.auth).setOnClickListener(view -> {
            Intent token = new Intent(getApplicationContext(), WebActivity.class);
            startActivity(token);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences sharedPref = this.getSharedPreferences("VK_PREF", Context.MODE_PRIVATE);
        String token = sharedPref.getString("TOKEN", "");
        Long date = sharedPref.getLong("DATE", 0L);

        TextView tokenView = findViewById(R.id.token);
        tokenView.setText(token + " " + LocalDate.ofEpochDay(date) + " " + LocalDate.ofEpochDay(date).isBefore(LocalDate.now()));
    }
}
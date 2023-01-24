package dev.jacbes.vkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent info = getIntent();
        String firstName = info.getStringExtra("firstName");
        String lastName = info.getStringExtra("lastName");
        String photoOriginalURL = info.getStringExtra("photoOriginalURL");
        String status = info.getStringExtra("status");
        String domain = info.getStringExtra("domain");
        String mobilePhone = info.getStringExtra("mobilePhone");
        String dataOfBirth = info.getStringExtra("dataOfBirth");

        ((TextView) findViewById(R.id.first_name)).setText(firstName);
        ((TextView) findViewById(R.id.last_name)).setText(lastName);
        ((TextView) findViewById(R.id.status)).setText(status);
        ((TextView) findViewById(R.id.domain)).setText(domain);
        ((TextView) findViewById(R.id.mobilePhone)).setText(mobilePhone);
        ((TextView) findViewById(R.id.dataOfBirth)).setText(dataOfBirth);

        Glide.with(this)
                .load(photoOriginalURL)
                .into((ImageView) findViewById(R.id.avatar));
    }
}
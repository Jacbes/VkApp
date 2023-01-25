package dev.jacbes.vkapp.userscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import dev.jacbes.vkapp.R;
import dev.jacbes.vkapp.authscreen.VKAPIService;
import dev.jacbes.vkapp.model.VKResponseFollowers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UserActivity extends AppCompatActivity {

    Integer followers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent info = getIntent();
        Integer id = info.getIntExtra("id", 0);
        String firstName = info.getStringExtra("firstName");
        String lastName = info.getStringExtra("lastName");
        String photoOriginalURL = info.getStringExtra("photoOriginalURL");
        String status = info.getStringExtra("status");
        String domain = info.getStringExtra("domain");
        String mobilePhone = info.getStringExtra("mobilePhone");
        String dataOfBirth = info.getStringExtra("dataOfBirth");

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.vk.com/method/")
                .build();
        VKAPIService vkapiService = retrofit.create(VKAPIService.class);
        vkapiService.getFollowers(id, getSharedPreferences("VK_PREF", Context.MODE_PRIVATE).getString("TOKEN", "")).enqueue(new Callback<VKResponseFollowers>() {
            @Override
            public void onResponse(@NonNull Call<VKResponseFollowers> call, @NonNull Response<VKResponseFollowers> response) {
                if ((response.body() != null) && (response.body().getResponse() != null)) {
                    followers = response.body().getResponse().getCount();
                    ((TextView) findViewById(R.id.first_name)).setText(firstName);
                    ((TextView) findViewById(R.id.last_name)).setText(lastName);
                    ((TextView) findViewById(R.id.status)).setText(status);
                    ((TextView) findViewById(R.id.domain)).setText(domain);
                    ((TextView) findViewById(R.id.mobilePhone)).setText(mobilePhone);
                    ((TextView) findViewById(R.id.dataOfBirth)).setText(dataOfBirth);
                    ((TextView) findViewById(R.id.followers)).setText(followers.toString());

                    Glide.with(getApplicationContext())
                            .load(photoOriginalURL)
                            .into((ImageView) findViewById(R.id.avatar));
                }
            }

            @Override
            public void onFailure(@NonNull Call<VKResponseFollowers> call, @NonNull Throwable t) {

            }
        });
    }
}
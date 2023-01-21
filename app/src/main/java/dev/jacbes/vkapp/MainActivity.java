package dev.jacbes.vkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import java.time.LocalDate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    static private SharedPreferences sharedPref;
    static private String token;
    static private Integer userId;
    static private Long date;

    /*
        При открытии приложения оно проверяет сохраненные настройки токена.
        Если дата токена не сегодня, то идет переход для получения токена.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = getSharedPreferences("VK_PREF", Context.MODE_PRIVATE);
        token = sharedPref.getString("TOKEN", "");
        userId = sharedPref.getInt("USER_ID", 0);
        date = sharedPref.getLong("DATE", 0L);
        if (LocalDate.ofEpochDay(date).isBefore(LocalDate.now())) {
            Intent token = new Intent(getApplicationContext(), WebActivity.class);
            startActivity(token);
        }
    }

    /*
        При загрузке активити оно пытается отобразить список друзей
        используя токен записанный в Shared Preferences.
        Чтобы получить список друзей отправляется запрос, далее он сериализуется.
        На выходе получается список из пользователей (response.body().getResponse().getItems()),
        из каждого пользователя конкатенируется имя и фамилия и выводится в TextView.
     */
    @Override
    protected void onStart() {
        super.onStart();

        sharedPref = getSharedPreferences("VK_PREF", Context.MODE_PRIVATE);
        token = sharedPref.getString("TOKEN", "");
        userId = sharedPref.getInt("USER_ID", 0);

        TextView tokenView = findViewById(R.id.token);

        if (!token.isEmpty()) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.vk.com/method/")
                    .build();

            VKAPIService vkapiService = retrofit.create(VKAPIService.class);
            vkapiService.getFriends(userId, token).enqueue(new Callback<VKResponse>() {
                @Override
                public void onResponse(Call<VKResponse> call, Response<VKResponse> response) {
                    if (response.body() != null) {
                        StringBuilder stringBuilder = new StringBuilder();
                        response.body().getResponse().getItems().forEach(element -> {
                            stringBuilder.append(element.getFirstName()).append(" ").append(element.getLastName()).append("\n");
                        });
                        tokenView.setText(stringBuilder.toString());
                    }
                }

                @Override
                public void onFailure(Call<VKResponse> call, Throwable t) {
                }
            });
        }
    }
}
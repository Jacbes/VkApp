package dev.jacbes.vkapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import dev.jacbes.vkapp.authscreen.VKAPIService;
import dev.jacbes.vkapp.authscreen.WebActivity;
import dev.jacbes.vkapp.mainscreen.FriendsAdapter;
import dev.jacbes.vkapp.model.VKResponse;
import dev.jacbes.vkapp.model.VKResponseFollowers;
import dev.jacbes.vkapp.model.VKUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<VKUser> friendsList = new LinkedList<>();

    private FriendsAdapter friendsAdapter;

    private SharedPreferences sharedPref;
    private String token;
    private Integer userId;
    private Long date;

    /*
        При открытии приложения оно проверяет сохраненные настройки токена.
        Если дата токена не сегодня, то идет переход для получения токена.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = getSharedPreferences("VK_PREF", Context.MODE_PRIVATE);
        RecyclerView friendListView = findViewById(R.id.friends_list);

        friendsAdapter = new FriendsAdapter(getApplicationContext(), R.layout.friends_list_item, friendsList);
        friendListView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        friendListView.setAdapter(friendsAdapter);

        getInfoFromSharedPref();
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

        getInfoFromSharedPref();

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
                        friendsList.clear();
                        friendsList.addAll(response.body().getResponse().getItems());

                        friendsAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<VKResponse> call, Throwable t) {
                }
            });
        }
    }

    private void getInfoFromSharedPref() {
        token = sharedPref.getString("TOKEN", "");
        userId = sharedPref.getInt("USER_ID", 0);
        date = sharedPref.getLong("DATE", 0L);
    }
}
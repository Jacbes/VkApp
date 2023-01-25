package dev.jacbes.vkapp.userscreen;

import static dev.jacbes.vkapp.MainActivity.friendsList;
import static dev.jacbes.vkapp.MainActivity.token;
import static dev.jacbes.vkapp.MainActivity.vkapiService;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import dev.jacbes.vkapp.R;
import dev.jacbes.vkapp.model.VKResponseFollowers;
import dev.jacbes.vkapp.model.VKUser;
import dev.jacbes.vkapp.tools.DataConverter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserActivity extends AppCompatActivity {

    VKUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent info = getIntent();
        Integer id = info.getIntExtra("id", 0);
        int position = info.getIntExtra("position", 0);

        user = friendsList.get(position);

        vkapiService.getFollowers(id, token).enqueue(followersCallback);
    }

    private final Callback<VKResponseFollowers> followersCallback = new Callback<VKResponseFollowers>() {
        @Override
        public void onResponse(@NonNull Call<VKResponseFollowers> call, @NonNull Response<VKResponseFollowers> response) {
            if ((response.body() != null) && (response.body().getResponse() != null)) {
                String followers = response.body().getResponse().getCount().toString();
                ((TextView) findViewById(R.id.first_name)).setText(user.getFirstName());
                ((TextView) findViewById(R.id.last_name)).setText(user.getLastName());
                ((TextView) findViewById(R.id.status)).setText(user.getStatus());
                ((TextView) findViewById(R.id.domain)).setText(user.getDomain());
                ((TextView) findViewById(R.id.mobilePhone)).setText(user.getMobilePhone());
                if (user.getDataOfBirth() != null) {
                    ((TextView) findViewById(R.id.dataOfBirth)).setText(DataConverter.dateToString(user.getDataOfBirth()));
                }
                ((TextView) findViewById(R.id.followers)).setText(followers);

                Glide.with(getApplicationContext())
                        .load(user.getPhotoOriginalURL())
                        .into((ImageView) findViewById(R.id.avatar));
            }
        }

        @Override
        public void onFailure(@NonNull Call<VKResponseFollowers> call, @NonNull Throwable t) {
        }
    };
}
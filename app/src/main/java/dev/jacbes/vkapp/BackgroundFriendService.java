package dev.jacbes.vkapp;

import static dev.jacbes.vkapp.MainActivity.friendsCallback;
import static dev.jacbes.vkapp.MainActivity.token;
import static dev.jacbes.vkapp.MainActivity.userId;
import static dev.jacbes.vkapp.MainActivity.vkapiService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class BackgroundFriendService extends Service {

    private final Timer timer = new Timer();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        start();
    }

    private void start() {
        timer.scheduleAtFixedRate(new FriendsUpdate(), 0, 2000);
    }

    private static class FriendsUpdate extends TimerTask {
        @Override
        public void run() {
            vkapiService.getFriends(userId, token).enqueue(friendsCallback);
        }
    }
}

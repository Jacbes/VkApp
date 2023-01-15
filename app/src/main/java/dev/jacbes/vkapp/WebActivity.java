package dev.jacbes.vkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.time.LocalDate;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        WebView webView = findViewById(R.id.web_link);
        webView.setWebViewClient(new WebClient(this));
        webView.loadUrl("https://oauth.vk.com/authorize?client_id=51526571&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends&response_type=token&v=5.131");
    }

    static class WebClient extends WebViewClient {

        Activity activity;

        WebClient(Activity activity) {
            this.activity = activity;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

            if (url.contains("access_token=vk")) {
                String token = url.substring(url.indexOf('=') + 1, url.indexOf('&'));

                SharedPreferences sharedPref = activity.getSharedPreferences("VK_PREF", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("TOKEN", token);
                editor.putLong("DATE", LocalDate.now().toEpochDay());
                editor.apply();

                activity.finish();
            }
        }
    }
}
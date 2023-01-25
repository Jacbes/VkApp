package dev.jacbes.vkapp.authscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.time.LocalDate;

import dev.jacbes.vkapp.R;

/*
    Активити для отображения запроса к сервису ВК для получения токена
    и id пользователя.
 */
public class WebActivity extends AppCompatActivity {

    /*
        При создании указывается WebView клиент и открывается ссылка с запросом
        предоставления прав для чтения списка друзей.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        WebView webView = findViewById(R.id.web_link);
        webView.setWebViewClient(new WebClient(this));
        webView.loadUrl("https://oauth.vk.com/authorize?client_id=51526571&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends&response_type=token&v=5.131");
    }

    /*
        Клиент который отображает сайт авторизации и отслеживает получение токена.
     */
    static class WebClient extends WebViewClient {

        Activity activity;

        /*
            В конструкторе сохраняем активити для ее завершения.
         */
        WebClient(Activity activity) {
            this.activity = activity;
        }

        /*
            При загрузке каждой страницы проверяется содержание url, если в нем есть токен то
            из ссылки достается этот токен и id пользователя. Далее данные вместе
            с датой записываются в Shared Preferences, данное активити завершается.
            Дата нужна для отслеживания жизни токена.
         */
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

            if (url.contains("access_token=vk")) {
                String token = url.substring(url.indexOf('=') + 1, url.indexOf('&'));
                int userId = Integer.parseInt(url.substring(url.lastIndexOf('=') + 1));

                SharedPreferences sharedPref = activity.getSharedPreferences("VK_PREF", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("TOKEN", token);
                editor.putInt("USER_ID", userId);
                editor.putLong("DATE", LocalDate.now().toEpochDay());
                editor.apply();

                activity.finish();
            }
        }
    }
}
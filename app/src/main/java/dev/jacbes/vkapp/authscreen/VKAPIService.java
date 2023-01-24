package dev.jacbes.vkapp.authscreen;

import dev.jacbes.vkapp.model.VKResponse;
import dev.jacbes.vkapp.model.VKResponseBodyFollowers;
import dev.jacbes.vkapp.model.VKResponseFollowers;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
    Запрос к VK API для получения списка друзей.
 */
public interface VKAPIService {
    @GET("friends.get?v=5.131&order=hints&fields=domain,bdate,photo_100,photo_200_orig,has_mobile,contacts,online,last_seen,status")
    Call<VKResponse> getFriends(@Query("user_id") Integer idUser, @Query("access_token") String token);

    @GET("users.getFollowers?v=5.131")
    Call<VKResponseFollowers> getFollowers(@Query("user_id") Integer idUser, @Query("access_token") String token);
}

//Для получения параметра город необходимо реализовать новый класс, описывающий объект city
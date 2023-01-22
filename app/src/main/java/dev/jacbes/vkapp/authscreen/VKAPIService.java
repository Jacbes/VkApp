package dev.jacbes.vkapp.authscreen;

import dev.jacbes.vkapp.model.VKResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
    Запрос к VK API для получения списка друзей.
 */
public interface VKAPIService {
    @GET("friends.get?v=5.131&fields=contacts")
    Call<VKResponse> getFriends(@Query("user_id") Integer idUser, @Query("access_token") String token);
}
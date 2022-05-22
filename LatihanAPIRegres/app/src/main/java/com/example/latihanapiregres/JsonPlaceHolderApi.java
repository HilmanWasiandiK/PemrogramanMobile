package com.example.latihanapiregres;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("api/users")
    Call<Page1> getAllUsersByPage(@Query("page") int page);

    @GET("api/users/{id}")
    Call<User1> getAUserById(@Path("id") int id);

    @GET("api/unknown")
    Call<Page2> getAllUsers();

    @GET("api/unknown/{id}")
    Call<User2> getAUser(@Path("id") int id);

    @POST("api/users")
    Call<UserCreate> postAUser(@Body UserCreate user);

    @POST("api/users/{id}")
    Call<UserUpdate> putAUser(
            @Path("id") int id,
            @Body UserUpdate user
    );

    @PATCH("api/users/{id}")
    Call<UserUpdate> patchAUser(
            @Path("id") int id,
            @Body UserUpdate user
    );

    @DELETE("api/users/{id}")
    Call<Void> deleteAUser(@Path("id") int id);

    @POST("api/register")
    Call<RegisterResponse> postSuccessRegister(@Body RegisterLoginRequest request);

    @POST("api/register")
    Call<RegisterResponse> postUnSuccessRegister(@Body RegisterLoginRequest request);

    @POST("api/login")
    Call<LoginResponse> postSuccessLogin(@Body RegisterLoginRequest request);

    @POST("api/login")
    Call<LoginResponse> postUnSuccessLogin(@Body RegisterLoginRequest request);

    @GET("api/users")
    Call<Page1> getAllUsersDelayed(@Query("delay") int delay);

}

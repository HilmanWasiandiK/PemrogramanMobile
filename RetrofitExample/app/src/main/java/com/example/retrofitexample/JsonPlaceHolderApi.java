package com.example.retrofitexample;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {

    //------------------------------------------------------------------------------
    //latihan YT

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComment(@Path("id") int postId);

    @GET("posts")
    Call<List<Post>> getPosts(
            @Query("userId") Integer[] userId,
            @Query("_sort") String sort,
            @Query("-order") String order
    );

    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String, String> parameters);

    @GET
    Call<List<Comment>> getComment(@Url String url);

    @POST("posts")
    Call<Post>createPost(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text
    );

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@FieldMap Map<String, String> fields);

    @Headers({"Static-Header: 123", "Static-Header2: 456"})
    @PUT("posts/{id}")
    Call<Post> putPost(@Header ("Dynamic-Header") String header,
                       @Path("id") int id, @Body Post post);

    @PATCH("posts/{id}")
    Call<Post> patchPost(@HeaderMap Map<String, String> header,
                         @Path("id") int id, @Body Post post);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);

    //------------------------------------------------------------------------------
    //latihan otodidak

    //mengambil semua postingan
    @GET("posts")
    Call<List<Post>> getAllPosts();

    //mengambil sebuah postingan menggunakan path parameter
    @GET("posts/1")
    Call<Post> getAPostByPath();
    @GET("posts/{id}")
    Call<Post> getAPostByPath(@Path("id") Integer id);

    //mengambil sebuah postingan menggunakan query parameter
    @GET("posts")
    Call<List<Post>> getAPostByQuery(@Query("id") int id);

    //mengambil sebuah postingan menggunakan map
    @GET("posts")
    Call<List<Post>> getAPostByMap(@QueryMap Map<String, String> parameters);

    //mengambil postingan menggunakan endpoint
    @GET
    Call<List<Post>> getAllPostByUrl(@Url String endpoint);
    @GET
    Call<Post> getAPostByUrl(@Url String endpoint);

}

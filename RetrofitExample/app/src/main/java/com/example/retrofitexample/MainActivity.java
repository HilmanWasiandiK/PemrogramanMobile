package com.example.retrofitexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textviewResult;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textviewResult = findViewById(R.id.textview_result);

        Gson gson = new GsonBuilder().serializeNulls().create();

        HttpLoggingInterceptor loggingIntercepter = new HttpLoggingInterceptor();
        loggingIntercepter.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @NonNull
                    @Override
                    public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
                        Request OriginalRequest = chain.request();
                        Request newRequest = OriginalRequest.newBuilder()
                                .header("Interceptor-Header", "xyz")
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .addInterceptor(loggingIntercepter)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        //------------------------------------------------------------------------------
        //latihan YT
//        getposts();
//        getComment();
//        createPost();
//        updatePost();
//        deletePost();

        //------------------------------------------------------------------------------
        //latihan otodidak
//        getAllPosts();
        getAPostByPath();
//        getAPostByQuery();
//        getAPostByMap();
//        getAPostByUrl();
//        getAllPostByUrl();
    }

    private void deletePost() {
        Call<Void> call = jsonPlaceHolderApi.deletePost(5);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                textviewResult.setText("Code: " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                textviewResult.setText(t.getMessage());
            }
        });
    }

    private void updatePost() {
        Post post = new Post(12, null, "New Text");
        Map<String, String> headers = new HashMap<>();
        headers.put("Map-Header", "def");
        headers.put("Map-Header2", "ghi");

//        Call<Post> call = jsonPlaceHolderApi.putPost("abc", 5, post);
        Call<Post> call = jsonPlaceHolderApi.patchPost(headers, 5, post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if (!response.isSuccessful()) {
                    textviewResult.setText("Code: " + response.code());
                    return;
                }

                Post postResponse = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "User ID: " + postResponse.getUserId() + "\n";
                content += "Title: " + postResponse.getTitle() + "\n";
                content += "Text: " + postResponse.getText() + "\n\n";

                textviewResult.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textviewResult.setText(t.getMessage());
            }
        });
    }


    //------------------------------------------------------------------------------
    //latihan YT
    private void getposts() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", "1");
        parameters.put("_sort", "id");
        parameters.put("_order", "desc");

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(parameters);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()) {
                    textviewResult.setText("Code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for(Post post : posts) {
                    String content ="";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";

                    textviewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textviewResult.setText(t.getMessage());
            }
        });
    }

    private void createPost() {
        Post post = new Post(23, "new title", "new text");
        Map<String, String> fields = new HashMap<>();
        fields.put("userId", "25");
        fields.put("title", "new title");

        Call<Post> call = jsonPlaceHolderApi.createPost(fields);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()) {
                    textviewResult.setText("Code: " + response.code());
                    return;
                }

                Post postResponse = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "User ID: " + postResponse.getUserId() + "\n";
                content += "Title: " + postResponse.getTitle() + "\n";
                content += "Text: " + postResponse.getText() + "\n\n";

                textviewResult.append(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textviewResult.setText(t.getMessage());
            }
        });
    }

    private void getComment() {
        Call<List<Comment>> call = jsonPlaceHolderApi.getComment("posts/3/comments");
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(!response.isSuccessful()) {
                    textviewResult.setText("Code: " + response.code());
                    return;
                }

                List<Comment> comments = response.body();

                for(Comment comment : comments) {
                    String content = "";
                    content += "ID: " + comment.getId() + "\n";
                    content += "Post ID: " + comment.getPostId() + "\n";
                    content += "Name: " + comment.getName() + "\n";
                    content += "Email: " + comment.getEmail() + "\n";
                    content += "Text: " + comment.getText() + "\n\n";

                    textviewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textviewResult.setText(t.getMessage());
            }
        });
    }


    //------------------------------------------------------------------------------
    //latihan otodidak

    private void getAllPosts() {
        Call<List<Post>> call = jsonPlaceHolderApi.getAllPosts();
        callAllPosts(call);
    }

    private void getAPostByPath() {
//        Call<Post> call = jsonPlaceHolderApi.getAPostByPath();
        Call<Post> call = jsonPlaceHolderApi.getAPostByPath(1);
        callAPost(call);
    }

    private void getAPostByQuery() {
        Call<List<Post>> call = jsonPlaceHolderApi.getAPostByQuery(1);
        callAllPosts(call);
    }


    private void getAPostByMap() {
        Map<String, String> fields = new HashMap<>();
        fields.put("id", "1");
        Call<List<Post>> call = jsonPlaceHolderApi.getAPostByMap(fields);
        callAllPosts(call);
    }

    private void getAPostByUrl() {
        Call<Post> call = jsonPlaceHolderApi.getAPostByUrl("posts/1");
        callAPost(call);
    }

    private void getAllPostByUrl() {
        Call<List<Post>> call = jsonPlaceHolderApi.getAllPostByUrl("posts");
        callAllPosts(call);
    }

    private void callAPost(Call<Post> call) {
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()) {
                    textviewResult.setText("Code: " + response.code());
                    return;
                }

                Post post = response.body();

                String content ="";
                content += "ID: " + post.getId() + "\n";
                content += "User ID: " + post.getUserId() + "\n";
                content += "Title: " + post.getTitle() + "\n";
                content += "Text: " + post.getText() + "\n\n";

                textviewResult.append(content);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textviewResult.setText(t.getMessage());
            }
        });

    }

    private void callAllPosts(Call<List<Post>> call){
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()) {
                    textviewResult.setText("Code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for(Post post : posts) {
                    String content ="";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";

                    textviewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textviewResult.setText(t.getMessage());
            }
        });
    }
}
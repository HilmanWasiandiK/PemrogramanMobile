package com.example.latihanapiregres;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import okhttp3.OkHttpClient;
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

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

//        getAllUsersByPage();
//        getAUserById();
//        getAllUsers();
//        getAUser();
//        postAUser();
//        putpatchAUser();
//        deleteAUser();
//        postRegister();
//        postLogin();
        getAllUsersDelayed();
    }

    private void getAllUsersDelayed() {
        Call<Page1> call = jsonPlaceHolderApi.getAllUsersDelayed(3);
        call.enqueue(new Callback<Page1>() {
            @Override
            public void onResponse(Call<Page1> call, Response<Page1> response) {
                if (!response.isSuccessful()) {
                    textviewResult.setText("Code: " + response.code());
                    return;
                }

                textviewResult.setText("Code: " + response.code() + "\n\n");
                Page1 user = response.body();

                String content = "";
                content += user.toString();

                textviewResult.append(content);
            }

            @Override
            public void onFailure(Call<Page1> call, Throwable t) {
                textviewResult.setText(t.getMessage());
            }
        });
    }

    private void postLogin() {
//        RegisterLoginRequest request = new RegisterLoginRequest("eve.holt@reqres.in", "pistol");
//        Call<LoginResponse> call = jsonPlaceHolderApi.postSuccessLogin(request);
        RegisterLoginRequest request = new RegisterLoginRequest("peter@klaven");
        Call<LoginResponse> call = jsonPlaceHolderApi.postUnSuccessLogin(request);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(!response.isSuccessful()) {
                    textviewResult.setText("Code: " + response.code());
                    return;
                }

                textviewResult.setText("Code: " + response.code() + "\n\n");
                LoginResponse loginResponse = response.body();

                String content = "";
                content += loginResponse.toString();

                textviewResult.append(content);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }

    private void postRegister() {
//        RegisterLoginRequest request = new RegisterLoginRequest("eve.holt@reqres.in", "pistol");
//        Call<RegisterResponse> call = jsonPlaceHolderApi.postSuccessRegister(request);
        RegisterLoginRequest request = new RegisterLoginRequest("eve.holt@reqres.in");
        Call<RegisterResponse> call = jsonPlaceHolderApi.postUnSuccessRegister(request);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(!response.isSuccessful()) {
                    textviewResult.setText("Code: " + response.code());
                    return;
                }

                textviewResult.setText("Code: " + response.code() + "\n\n");
                RegisterResponse registerResponse = response.body();

                String content = "";
                content += registerResponse.toString();

                textviewResult.append(content);
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                textviewResult.setText(t.getMessage());
            }
        });
    }

    private void deleteAUser() {
        Call<Void> call = jsonPlaceHolderApi.deleteAUser(2);
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

    private void putpatchAUser() {
        UserUpdate user = new UserUpdate("morpheus", "resident");
//        Call<UserUpdate> call = jsonPlaceHolderApi.putUserUpdate(2, user);
        Call<UserUpdate> call = jsonPlaceHolderApi.patchAUser(2, user);
        call.enqueue(new Callback<UserUpdate>() {
            @Override
            public void onResponse(Call<UserUpdate> call, Response<UserUpdate> response) {
                if(!response.isSuccessful()) {
                    textviewResult.setText("Code: " + response.code());
                    return;
                }

                textviewResult.setText("Code: " + response.code() + "\n\n");
                UserUpdate postResponse = response.body();

                String content = "";
                content += postResponse.toString();

                textviewResult.append(content);
            }

            @Override
            public void onFailure(Call<UserUpdate> call, Throwable t) {
                textviewResult.setText(t.getMessage());
            }
        });
    }

    private void postAUser() {
        UserCreate user = new UserCreate("morpheus", "leader");
        Call<UserCreate> call = jsonPlaceHolderApi.postAUser(user);
        call.enqueue(new Callback<UserCreate>() {
            @Override
            public void onResponse(Call<UserCreate> call, Response<UserCreate> response) {
                if(!response.isSuccessful()) {
                    textviewResult.setText("Code: " + response.code());
                    return;
                }

                textviewResult.setText("Code: " + response.code() + "\n\n");
                UserCreate postResponse = response.body();

                String content = "";
                content += postResponse.toString();

                textviewResult.append(content);
            }

            @Override
            public void onFailure(Call<UserCreate> call, Throwable t) {
                textviewResult.setText(t.getMessage());
            }
        });
    }

    private void getAUser() {
//        Call<User2> call = jsonPlaceHolderApi.getAUser(2);
        Call<User2> call = jsonPlaceHolderApi.getAUser(23);
        call.enqueue(new Callback<User2>() {
            @Override
            public void onResponse(Call<User2> call, Response<User2> response) {
                if (!response.isSuccessful()) {
                    textviewResult.setText("Code: " + response.code());
                    return;
                }

                textviewResult.setText("Code: " + response.code() + "\n\n");
                User2 user = response.body();

                String content = "";
                content += user.toString();

                textviewResult.append(content);
            }

            @Override
            public void onFailure(Call<User2> call, Throwable t) {
                textviewResult.setText(t.getMessage());
            }
        });
    }

    private void getAllUsers() {
        Call<Page2> call = jsonPlaceHolderApi.getAllUsers();
        call.enqueue(new Callback<Page2>() {
            @Override
            public void onResponse(Call<Page2> call, Response<Page2> response) {
                if (!response.isSuccessful()) {
                    textviewResult.setText("Code: " + response.code());
                    return;
                }

                textviewResult.setText("Code: " + response.code() + "\n\n");
                Page2 user = response.body();

                String content = "";
                content += user.toString();

                textviewResult.append(content);
            }

            @Override
            public void onFailure(Call<Page2> call, Throwable t) {
                textviewResult.setText(t.getMessage());
            }
        });
    }

    private void getAUserById() {
//        Call<User1> call = jsonPlaceHolderApi.getAUserById(2);
        Call<User1> call = jsonPlaceHolderApi.getAUserById(23);
        call.enqueue(new Callback<User1>() {
            @Override
            public void onResponse(Call<User1> call, Response<User1> response) {
                if (!response.isSuccessful()) {
                    textviewResult.setText("Code: " + response.code());
                    return;
                }

                textviewResult.setText("Code: " + response.code() + "\n\n");
                User1 user = response.body();

                String content = "";
                content += user.toString();

                textviewResult.append(content);
            }

            @Override
            public void onFailure(Call<User1> call, Throwable t) {
                textviewResult.setText(t.getMessage());
            }
        });
    }

    private void getAllUsersByPage() {
        Call<Page1> call = jsonPlaceHolderApi.getAllUsersByPage(2);
        call.enqueue(new Callback<Page1>() {
            @Override
            public void onResponse(Call<Page1> call, Response<Page1> response) {
                if (!response.isSuccessful()) {
                    textviewResult.setText("Code: " + response.code());
                    return;
                }

                textviewResult.setText("Code: " + response.code() + "\n\n");
                Page1 user = response.body();

                String content = "";
                content += user.toString();

                textviewResult.append(content);
            }

            @Override
            public void onFailure(Call<Page1> call, Throwable t) {
                textviewResult.setText(t.getMessage());
            }
        });
    }
}
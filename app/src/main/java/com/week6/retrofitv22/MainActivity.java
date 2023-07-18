package com.week6.retrofitv22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.userListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create()).build();

        ResponseAPI responseAPI = retrofit.create(ResponseAPI.class);


        Map<String, String> parameters = new HashMap<>();
        parameters.put("_sort","name");



//Call<List<UserResponse>> user = responseAPI.getUser("name", "desc");

       Call<List<UserResponse>> user = responseAPI.getUser(parameters);

       user.enqueue(new Callback<List<UserResponse>>() {
            @Override
            public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {

                if(!response.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"No Response", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    List<UserResponse> users = response.body();
                    userAdapter = new UserAdapter(users);
                    recyclerView.setAdapter(userAdapter);
                    userAdapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(UserResponse user) {
                            String message = "Clicked user: " + user.getName() + ", ID: " + user.getId();

                            int userID = user.getId();

                            Intent intent = new Intent(MainActivity.this, PostActivity.class);
                            intent.putExtra("userID",userID);
                            startActivity(intent);
                        }
                    });

                }


            }

            @Override
            public void onFailure(Call<List<UserResponse>> call, Throwable t) {
                Log.e("error", t.getMessage());

            }
        });


    }
}
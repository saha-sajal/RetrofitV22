package com.week6.retrofitv22;

import androidx.appcompat.app.AppCompatActivity;
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

public class PostActivity extends AppCompatActivity {

    PostAdapater postAdapater;
    RecyclerView postView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        int userID = getIntent().getIntExtra("userID",0);

        Toast.makeText(getApplicationContext(),""+userID, Toast.LENGTH_LONG).show();

        postView = findViewById(R.id.postListView);


        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create()).build();

        ResponseAPI responseAPI = retrofit.create(ResponseAPI.class);

        Call<List<PostResponse>> post = responseAPI.getPost(userID);

        post.enqueue(new Callback<List<PostResponse>>() {
            @Override
            public void onResponse(Call<List<PostResponse>> call, Response<List<PostResponse>> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"No Response", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {

                    List<PostResponse> posts = response.body();
                    Toast.makeText(getApplicationContext(),"Response"+posts.size(), Toast.LENGTH_LONG).show();
                    postAdapater = new PostAdapater(posts);
                    postView.setAdapter(postAdapater);
                }


            }

            @Override
            public void onFailure(Call<List<PostResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failure", Toast.LENGTH_LONG).show();

            }
        });

    }
}
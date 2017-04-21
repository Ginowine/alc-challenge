package com.alc.github.challenge.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alc.github.challenge.R;
import com.alc.github.challenge.adapter.GitUserAdapter;
import com.alc.github.challenge.model.GitUser;
import com.alc.github.challenge.model.GitUserResponse;
import com.alc.github.challenge.service.GitApiService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String BASE_URL = "https://api.github.com";

    private static Retrofit retrofit = null;
    private RecyclerView recyclerView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        initRetrofitAndGetData();
    }

    private void prepareData(GitUserResponse gitUserResponse){
        recyclerView.setAdapter(new GitUserAdapter(gitUserResponse.getItems(), R.layout.list_item_git_user, getApplication()));

    }

    public void initRetrofitAndGetData() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

    GitApiService gitApiService = retrofit.create(GitApiService.class);

//    Map<String, String> data = new HashMap<>();
//    data.put("location","lagos");
//    data.put("language","java");

    Call<GitUserResponse> call = gitApiService.getLagosJavaUsers("language:java location:lagos");
    call.enqueue(new Callback<GitUserResponse>()
    {
        @Override
        public void onResponse(Call<GitUserResponse> call, Response <GitUserResponse> response){
        GitUserResponse gitUsers = response.body();
            prepareData(gitUsers);

        Log.d(TAG, "Number of movies received: " + gitUsers.size());
    }

        @Override
        public void onFailure (Call < GitUserResponse > call, Throwable t){

    }
    }

    );
}
}

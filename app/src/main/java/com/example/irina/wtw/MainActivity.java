package com.example.irina.wtw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText searchView;
    RecyclerView mRecyclerView;
    MovieAdapter mAdapter;
    private Retrofit retrofit;
    String query;
    //ToDO 2: api_key as string resourse
    //ToDo 3: if it possible to post api_key one time
    //ToDo: make search field to disappear
    //ToDo: bd for favourite movies
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = (EditText) findViewById(R.id.searchEditText);
        btn = (Button) findViewById(R.id.ok_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query = searchView.getText().toString();
                work();
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new MovieAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

    }

    void work(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MoviesApiService service = retrofit.create(MoviesApiService.class);
        service.searchMovie("e9eb5705dd6fbca54e9c6a893676ab3c", query).enqueue(new Callback<Movie.MovieResult>() {
            @Override
            public void onResponse(Call<Movie.MovieResult> call, Response<Movie.MovieResult> response) {
                if (response.isSuccessful()) {
                    Log.i("PROVARETROFIT", "OK");
                    System.out.println("ok");
                    mAdapter.setMovieList(response.body().getResults());
                }else{
                    System.out.println("Unsuccesful 1l "+ response.errorBody());}


            }
            @Override
            public void onFailure(Call<Movie.MovieResult> call, Throwable t) {
                System.out.println("Unsuccesfull 2");

            }
        });
    }

}

package com.example.irina.wtw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText searchView;
    RecyclerView mRecyclerView;
    //ToDO 1: adapter for recyclerview
    //ToDo 2: get api_key
    //ToDo 3: rewrite succesee
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = (EditText) findViewById(R.id.searchEditText);
        btn = (Button) findViewById(R.id.ok_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = searchView.getText().toString();

            }
        });
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.themoviedb.org/3")
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addEncodedQueryParam("api_key", "YOUR_API_KEY");
                    }
                })
                .build();
        MoviesApiService service = restAdapter.create(MoviesApiService.class);
        service.searchMovie(new Callback<Movie.MovieResult>() {
            @Override
            public void success(Movie.MovieResult movieResult, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }


}

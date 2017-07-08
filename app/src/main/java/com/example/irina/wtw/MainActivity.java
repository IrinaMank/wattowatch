package com.example.irina.wtw;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    Button btn;
    SearchView searchView;
    RecyclerView mRecyclerView;
    MovieAdapter mAdapter;
    Toolbar mToolbar;
    private Retrofit retrofit;
    MoviesApiService service;
    Callback<Movie.MovieResult> mCallback;
    String query;
    //ToDo: bd for favourite movies
    //ToDo: on every device
    //ToDo: constraint layout
    //ToDo: how many movies api post
    //ToDO: text in cards: another font, center, ... if no place

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new MovieAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        work();
        service.getPopularMovies(getString(R.string.api_key)).enqueue(mCallback);
    }


    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);

        MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String mquery) {
                query=mquery;
               // work();
                service.searchMovie(getString(R.string.api_key), query).enqueue(mCallback);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                return false;
            }
        });
        MenuItemCompat.setOnActionExpandListener(myActionMenuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                service.getPopularMovies(getString(R.string.api_key)).enqueue(mCallback);
                return true;
            }
        });
        return true;
    }

    void work(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(MoviesApiService.class);
        mCallback = new Callback<Movie.MovieResult>() {
            @Override
            public void onResponse(Call<Movie.MovieResult> call, retrofit2.Response<Movie.MovieResult> response) {
                if (response.isSuccessful()) {
                    mAdapter.setMovieList(response.body().getResults());
                }else{}


            }
            @Override
            public void onFailure(Call<Movie.MovieResult> call, Throwable t) {
                //ToDo: is it neccesary

            }
        };
        service.searchMovie(getString(R.string.api_key), query).enqueue(mCallback);
    }


}

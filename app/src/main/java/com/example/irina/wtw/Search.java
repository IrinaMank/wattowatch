package com.example.irina.wtw;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Irina on 09.07.2017.
 */

public class Search extends Fragment {
    Button btn;
    SearchView searchView;
    RecyclerView mRecyclerView;
    MovieAdapter mAdapter;
    Toolbar mToolbar;
    private Retrofit retrofit;
    MoviesApiService service;
    Callback<Movie.MovieResult> mCallback;
    String query;
    //ToDo: everuthing should be refactr and rebuild!!!
    //ToDo: on every device
    //ToDo: constraint layout
    //ToDo: how many movies api post
    //ToDO: text in cards: another font, center, ... if no place
    //ToDo: moviesdetail into vidjet

    ViewGroup root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.activity_main, null);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mAdapter = new MovieAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        //mToolbar = (Toolbar) root.findViewById(R.id.menu_toolbar);
        //root.setSupportActionBar(mToolbar);
        work();
        service.searchMovie(getString(R.string.api_key), query).enqueue(mCallback);
        service.getPopularMovies(getString(R.string.api_key)).enqueue(mCallback);
        setHasOptionsMenu(true);
        return root;
    }


   @Override
    public void onCreateOptionsMenu( Menu menu, MenuInflater inflate) {
        inflate.inflate(R.menu.menu_toolbar, menu);

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

    }
}

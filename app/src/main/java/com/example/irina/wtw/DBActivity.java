package com.example.irina.wtw;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Irina on 14.07.2017.
 */

public class DBActivity extends Fragment{
    ViewGroup root;
    RecyclerView mRecyclerView;
    MovieAdapter movieAdapter;
    MainActivity mainActivity;
    List<Movie> mList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.db_activity, null);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.db_recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mainActivity = (MainActivity) getActivity();
        movieAdapter = new MovieAdapter(mainActivity);
        mRecyclerView.setAdapter(movieAdapter);
        Cursor cursor = mainActivity.dbAdapter.fetchAllTodos();
        mList = new ArrayList<Movie>();
        if(cursor.moveToFirst()){
            do{
                String title = cursor.getString(cursor.getColumnIndex(mainActivity.dbAdapter.KEY_TITLE));
                String image = cursor.getString(cursor.getColumnIndex(mainActivity.dbAdapter.KEY_IMAGE));
                String summary = cursor.getString(cursor.getColumnIndex(mainActivity.dbAdapter.KEY_SUMMARY));
                Movie mMovie = new Movie(title, image, summary);
                mList.add(mMovie);

            }while (cursor.moveToNext());
        }
        movieAdapter.setMovieList(mList);
        movieAdapter.notifyDataSetChanged();

        return root;
    }


}

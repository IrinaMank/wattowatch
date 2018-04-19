package com.example.irina.wtw.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.irina.wtw.R;
import com.example.irina.wtw.activities.MainActivity;
import com.example.irina.wtw.adapters.DBRecyclerAdapter;
import com.example.irina.wtw.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Irina on 14.07.2017.
 */

public class DBActivity extends android.support.v4.app.Fragment {
    ViewGroup root;
    RecyclerView mRecyclerView;
    DBRecyclerAdapter movieAdapter;
    MainActivity mainActivity;
    List<Movie> mList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.db_activity, null);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.db_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mainActivity = (MainActivity) getActivity();

        Cursor cursor = mainActivity.dbAdapter.fetchAllMovies();
        mList = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                String title = cursor.getString(cursor.getColumnIndex(mainActivity.dbAdapter.KEY_TITLE));
                String image = cursor.getString(cursor.getColumnIndex(mainActivity.dbAdapter.KEY_IMAGE));
                String summary = cursor.getString(cursor.getColumnIndex(mainActivity.dbAdapter.KEY_SUMMARY));
                Movie mMovie = new Movie(title, image, summary);
                mList.add(mMovie);

            }while (cursor.moveToNext());
        }
        movieAdapter = new DBRecyclerAdapter(mainActivity, mList);
        //mRecyclerView.setAdapter(movieAdapter);
        setUpItemTouchHelper();
        //movieAdapter.setMovieList(mList);
        movieAdapter.notifyDataSetChanged();

        return root;
    }


    @Override
    public void onPause()
    {
        super.onPause();
        mainActivity.dbAdapter.deleteAllMovies();
        for (int i = 0; i < mList.size(); i++) {
            mainActivity.dbAdapter.createMovie(mList.get(i).getTitle(), mList.get(i).getDescription(), mList.get(i).getPoster());
        }
    }


    private void setUpItemTouchHelper() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int swipedPosition = viewHolder.getAdapterPosition();
                mList.remove(swipedPosition);
                movieAdapter.notifyDataSetChanged();

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

}

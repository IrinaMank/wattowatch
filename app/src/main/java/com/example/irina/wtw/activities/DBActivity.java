package com.example.irina.wtw.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.irina.wtw.R;
import com.example.irina.wtw.adapters.DBRecyclerAdapter;
import com.example.irina.wtw.model.Want;
import com.example.irina.wtw.services.FirebaseStorage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Irina on 14.07.2017.
 */

//TODO: deleting from cloud


public class DBActivity extends android.support.v4.app.Fragment {
    ViewGroup root;
    RecyclerView mRecyclerView;
    DBRecyclerAdapter movieAdapter;
    MainActivity mainActivity;
    List<Want> mList;
    FirebaseStorage storage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.db_activity, null);
        mRecyclerView = root.findViewById(R.id.db_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mainActivity = (MainActivity) getActivity();
        storage = new FirebaseStorage();
        Cursor cursor = mainActivity.dbAdapter.fetchAllWants();
        mList = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                String title = cursor.getString(cursor.getColumnIndex(mainActivity.dbAdapter.KEY_TITLE));
                String tmdbId = cursor.getString(cursor.getColumnIndex(mainActivity.dbAdapter.KEY_TMDB_ID));
                String date = cursor.getString(cursor.getColumnIndex(mainActivity.dbAdapter.KEY_DATE));
                String userId = cursor.getString(cursor.getColumnIndex(mainActivity.dbAdapter.KEY_USER_ID));
                int id = Integer.parseInt(tmdbId);
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                try {
                    Want mWant = new Want(id, userId, formatter.parse(date), title);
                    mList.add(mWant);
                } catch (Exception e) {}
            }while (cursor.moveToNext());
        }
        movieAdapter = new DBRecyclerAdapter(mainActivity, mList);
        mRecyclerView.setAdapter(movieAdapter);
        setUpItemTouchHelper();
        movieAdapter.notifyDataSetChanged();
        return root;
    }

    @Override
    public void onPause()
    {
        super.onPause();
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
                Want mWant = mList.get(swipedPosition);
                mainActivity.dbAdapter.deleteWant(mWant);
                mList.remove(swipedPosition);
                movieAdapter.notifyDataSetChanged();
                storage.deleteWant(mWant, successListener(), failureListener());

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private OnSuccessListener<Void> successListener() {
        return new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void v) {

            }
        };
    }

    private OnFailureListener failureListener() {
        return new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        };
    }
}

package com.example.irina.wtw;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Irina on 01.07.2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder>{

    private LayoutInflater mInflater;
    private Context mContext;
    private List<Movie> mList;

    public MovieAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mList = new ArrayList<>();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.viewholder_layout, parent, false);
        MovieViewHolder mViewHolder = new MovieViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie mMovie = mList.get(position);
        Picasso.with(mContext).load(mMovie.getPoster()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(mList==null) return 0;
        else return mList.size();
    }

    public void setMovieList(List<Movie> newList){
        mList.clear();
        mList.addAll(newList);
        notifyDataSetChanged();
    }
}

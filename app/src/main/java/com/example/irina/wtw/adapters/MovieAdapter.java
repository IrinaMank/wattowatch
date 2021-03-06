package com.example.irina.wtw.adapters;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.irina.wtw.activities.MovieDetailActivity;
import com.example.irina.wtw.R;
import com.example.irina.wtw.model.Movie;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//ToDo:maybe remove implemets seri-ble
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    private static final  int VIEW_TYPE_EMPTY_LIST_PLACEHOLDER = 0;
    private static final int VIEW_TYPE_OBJECT_VIEW = 1;
    private LayoutInflater mInflater;
    private Context mContext;
    private List<Movie> mList;
    public boolean isClickable = true;

    public MovieAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mList = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.isEmpty()) {
            return VIEW_TYPE_EMPTY_LIST_PLACEHOLDER;
        } else {
            return VIEW_TYPE_OBJECT_VIEW;
        }
    }
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.empty_view_layout, parent, false);
        switch(viewType) {
            case VIEW_TYPE_EMPTY_LIST_PLACEHOLDER:
                view = mInflater.inflate(R.layout.empty_view_layout, parent, false);
                break;
            case VIEW_TYPE_OBJECT_VIEW:
                view = mInflater.inflate(R.layout.viewholder_layout, parent, false);
                break;
        }
        MovieViewHolder mViewHolder = new MovieViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie mMovie = mList.get(position);
        Picasso.with(mContext).load(mMovie.getPoster()).into(holder.imageView);
        holder.textView.setText(mMovie.getTitle());
    }

    @Override
    public int getItemCount() {
        if(mList==null) return 0;
        else return mList.size();
    }

    public Boolean setMovieList(List<Movie> newList){
        mList.clear();
        mList.addAll(newList);
        notifyDataSetChanged();
        if(mList.size()==0) return true;
        return false;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements Serializable {

        private ImageView imageView;
        private TextView textView;


        public MovieViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.poster_imageView);
            textView = (TextView) itemView.findViewById(R.id.title_textView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View vi) {
                    if (mList.contains(mList.get(getLayoutPosition()))) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //Intent i = new Intent(mContext, MovieDetailActivity.class);
                                //i.putExtra("MOVIE_TITLE", mList.get(getLayoutPosition()).getTitle());
                                //i.putExtra("MOVIE_TITLE", (Serializable) mList.get(getLayoutPosition()));
                                Bundle mBundle = new Bundle();
                                mBundle.putSerializable("MOVIE_TITLE", (Serializable) mList.get(getLayoutPosition()));
                                //i.putExtras(mBundle);
                                Fragment toFragment = new MovieDetailActivity();
                                toFragment.setArguments(mBundle);
                                android.support.v4.app.FragmentManager fragmentManager = ((FragmentActivity) mContext).getSupportFragmentManager();
                                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction
                                        .add(R.id.flContent, toFragment)
                                        .addToBackStack(null)
                                        .commit();
                            }
                        }, 300); // <--- Giving time to the ripple effect finish before opening a new activity
                    }
                }
            });
        }
    }

}

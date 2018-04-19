package com.example.irina.wtw.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.irina.wtw.R;
import com.example.irina.wtw.model.Movie;

import java.io.Serializable;
import java.util.List;


public class DBRecyclerAdapter extends RecyclerView.Adapter<DBRecyclerAdapter.MovieViewHolder>{
    private LayoutInflater mInflater;
    private Context mContext;
    private List<Movie> mList;

    public DBRecyclerAdapter(Context context, List<Movie> newList){
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mList = newList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.db_viewholder_layout, parent, false);
        MovieViewHolder mViewHolder = new MovieViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie mMovie = mList.get(position);
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

        private TextView textView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.db_textView);

        }
    }
}

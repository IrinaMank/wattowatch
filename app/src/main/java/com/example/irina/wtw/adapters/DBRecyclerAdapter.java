package com.example.irina.wtw.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.irina.wtw.R;
import com.example.irina.wtw.model.Want;

import java.io.Serializable;
import java.util.List;


public class DBRecyclerAdapter extends RecyclerView.Adapter<DBRecyclerAdapter.WantViewHolder>{
    private LayoutInflater mInflater;
    private List<Want> mList;

    public DBRecyclerAdapter(Context context, List<Want> list){
        mInflater = LayoutInflater.from(context);
        mList = list;
    }

    @Override
    public WantViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.db_viewholder_layout, parent, false);
        return new WantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WantViewHolder holder, int position) {
        Want mWant = mList.get(position);
        holder.textView.setText(mWant.getTitle());
    }

    @Override
    public int getItemCount() {
        if(mList==null) return 0;
        else return mList.size();
    }

    class WantViewHolder extends RecyclerView.ViewHolder implements Serializable {

        private TextView textView;

        WantViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.db_textView);

        }
    }
}

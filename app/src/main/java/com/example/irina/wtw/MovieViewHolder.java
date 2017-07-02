package com.example.irina.wtw;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Irina on 01.07.2017.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder{

    public ImageView imageView;

    public MovieViewHolder(View itemView){
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.poster_imageView);
    }
}

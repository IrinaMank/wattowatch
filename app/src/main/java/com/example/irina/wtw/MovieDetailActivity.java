package com.example.irina.wtw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.design.widget.CollapsingToolbarLayout;

import com.squareup.picasso.Picasso;

import java.io.Serializable;

/**
 * Created by Irina on 07.07.2017.
 */
//ToDo: cool full info
//ToDo: replace ser with parceable
//ToDo: change height of toolbar and text larger and on the top
//ToDo: large poster, margin top and bottom, bottom frame, povorot
    //ToDo: title at the beginning of tv, button to add

public class MovieDetailActivity extends AppCompatActivity {
    private CollapsingToolbarLayout toolbarLayout;
    ImageView image;
    TextView description;
    Toolbar mToolbar;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Intent intent = getIntent();
        Movie mMovie =(Movie) intent.getSerializableExtra("MOVIE_TITLE");

        //toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        //toolbarLayout.setTitle(mMovie.getTitle());

        image = (ImageView) findViewById(R.id.cover);
        Picasso.with(this).load(mMovie.getPoster()).into(image);

        description = (TextView) findViewById(R.id.description);
        description.setText(mMovie.getDescription());

        mToolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        setSupportActionBar(mToolbar);

        mButton = (Button) findViewById(R.id.fab);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add to db
            }
        });
    }

}

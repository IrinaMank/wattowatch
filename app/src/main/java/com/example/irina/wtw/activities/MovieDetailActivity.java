package com.example.irina.wtw.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.design.widget.CollapsingToolbarLayout;

import com.example.irina.wtw.R;
import com.example.irina.wtw.model.Movie;
import com.squareup.picasso.Picasso;

/**
 * Created by Irina on 07.07.2017.
 */
//ToDo: cool full info
//ToDo: replace ser with parceable
//ToDo: change height of menu_toolbar and text larger and on the top
//ToDo: large poster, margin top and bottom, bottom frame, povorot
    //ToDo: title at the beginning of tv, button to add
    //ToDo: rename this to Fragment

public class MovieDetailActivity extends DialogFragment {
    private CollapsingToolbarLayout toolbarLayout;
    ImageView image;
    TextView description;
    Toolbar mToolbar;
    FloatingActionButton mButton;
    ViewGroup root;
    Movie mMovie;
    MainActivity mainActivity;

    static MovieDetailActivity newInstance(int num) {
        MovieDetailActivity f = new MovieDetailActivity();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.activity_movie_detail, null);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mMovie = (Movie) bundle.getSerializable("MOVIE_TITLE");
        }
        if (getActivity() != null)
            mainActivity = (MainActivity ) getActivity();
        View view = inflater.inflate(R.layout.activity_movie_detail, container, false);

        //toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        //toolbarLayout.setTitle(mMovie.getTitle());

        image = (ImageView) root.findViewById(R.id.cover);
        Picasso.with(getContext()).load(mMovie.getBackdrop()).into(image);

        description = (TextView) root.findViewById(R.id.description);
        description.setText(mMovie.getDescription());
        description.setMovementMethod(new ScrollingMovementMethod());
        // mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar_detail);
        //getActivity().setSupportActionBar(mToolbar);

//        mButton = (Button) root.findViewById(R.id.fab);
        /*mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add to db
            }
        });*/

        mButton = (FloatingActionButton) root.findViewById(R.id.fab);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.dbAdapter.createMovie(mMovie.getTitle(), mMovie.getPosterUrl(), mMovie.getDescription());
                dismiss();
            }
        });


        //getDialog().setTitle("Info");
        return root;
    }



}

package com.example.irina.wtw.activities;

import android.content.Context;
import android.support.annotation.NonNull;
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
import android.widget.Toast;

import com.example.irina.wtw.R;
import com.example.irina.wtw.model.Movie;
import com.example.irina.wtw.model.Want;
import com.example.irina.wtw.services.FirebaseStorage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    FirebaseStorage storage;

    static MovieDetailActivity newInstance(int num) {
        return new MovieDetailActivity();
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

        storage = new FirebaseStorage();

        image = root.findViewById(R.id.cover);
        Picasso.with(getContext()).load(mMovie.getBackdrop()).into(image);

        description = root.findViewById(R.id.description);
        description.setText(mMovie.getDescription());
        description.setMovementMethod(new ScrollingMovementMethod());

        mButton = root.findViewById(R.id.fab);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Want want = new Want(mMovie.getId(),  "test_user", new Date(), mMovie.getTitle());

                OnSuccessListener<Void> s = new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void v) {
                        Context context = mainActivity.getApplicationContext();
                        CharSequence text = "Success";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                };
                OnFailureListener f = new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Context context = mainActivity.getApplicationContext();
                        CharSequence text = "Failure";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                };
                mainActivity.dbAdapter.createWant(mMovie.getTitle(), mMovie.getId(), (new Date()), "test_user");

                storage.addWant(want, s, f);
                dismiss();
            }
        });


        //getDialog().setTitle("Info");
        return root;
    }



}

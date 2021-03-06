package com.example.irina.wtw.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.irina.wtw.db.WantsTable;
import com.example.irina.wtw.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    WantsTable dbAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        //set content
        Toolbar mToolbar = findViewById(R.id.include);
        setSupportActionBar(mToolbar);
        dbAdapter = new WantsTable(this);
        dbAdapter.open();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(getBottombarListener());

        //set fragment
        Fragment fragment;
        try {
            fragment = Search.class.newInstance();
        }
        catch (Exception e) {
            Log.d("FRAGMENT", e.getMessage());
            return;
        }
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.flContent, fragment);
        tx.commit();
    }

    //TODO: fix settings
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Listener for bottombar
    private BottomNavigationView.OnNavigationItemSelectedListener getBottombarListener() {
        BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                Class fragmentClass = DBActivity.class;
                switch (item.getItemId()) {
                    case R.id.nav_db:
                        fragmentClass = DBActivity.class;
                        break;
                    case R.id.nav_search:
                        fragmentClass = Search.class;
                        break;
                    case R.id.nav_profile:
                        fragmentClass = ProfileActivity.class;
                        break;
                }
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                }
                catch(Exception e)
                {
                    Log.d("FRAGMENT", e.getMessage());
                    return true;
                }
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.flContent, fragment);
                transaction.commit();
                return false;
            }
        };
        return listener;
    }

    public void signOut() {

        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
    }
}


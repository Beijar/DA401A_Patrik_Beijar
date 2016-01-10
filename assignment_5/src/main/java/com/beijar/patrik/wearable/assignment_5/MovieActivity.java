package com.beijar.patrik.wearable.assignment_5;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Toast;

public class MovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (findViewById(R.id.main_container) != null) {

            if (savedInstanceState != null) {
                return;
            }
            // Create a new Fragment to be placed in the activity layout
            MasterFragment firstFragment = new MasterFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_container, firstFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Functions for selecting Option menu items
        Context context = getApplicationContext();
        switch(item.getItemId()) {
            case R.id.help:
                Toast toast = Toast.makeText(context, "Här finns det inge info!", Toast.LENGTH_LONG);
                toast.show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}

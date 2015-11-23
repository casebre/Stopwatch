package com.example.rafael.casebrestopwatch;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Chronometer;
import android.os.Handler;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    // Start the timer
    public void clickStart(View view) {
        Chronometer chrono = (Chronometer) findViewById(R.id.chronoTimer);
        chrono.setBase(SystemClock.elapsedRealtime());
        chrono.start();
    }

    public void clickPause(View view){
        Chronometer chrono = (Chronometer) findViewById(R.id.chronoTimer);
        chrono.stop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final Integer[] time = {0};

        // Allows code to be ran in delayed way. Controls the timming of events/codes.
        final Handler handler = new Handler();
        final TextView textViewCrono = (TextView) findViewById(R.id.textViewChrono);

        Runnable run = new Runnable() {
            @Override
            public void run() {

                /* Code to be run every time (I am not sure yet, but I believe it should
                 follow some hardware tick
                  */
                time[0]++;
                textViewCrono.setText(Integer.toString(time[0]));
                handler.postDelayed(this, 1000); // in this case, 1 second.

            }
        };

        // initializing runnable
        handler.post(run);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

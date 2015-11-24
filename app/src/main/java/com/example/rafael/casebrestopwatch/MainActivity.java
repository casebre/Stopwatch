package com.example.rafael.casebrestopwatch;

import android.os.Bundle;
import android.os.CountDownTimer;
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
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    SeekBar timerControl;
    CountDownTimer countDownTimer;

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

    public void startCount(View view){
        //SeekBar seekBarCounter = (SeekBar) findViewById(R.id.seekBarStop);

        countDownTimer = new CountDownTimer(timerControl.getProgress() * 1000, 1000) {

            public void onTick(long miliseconds){
                TextView chrono = (TextView) findViewById(R.id.textViewChrono);
                chrono.setText(String.valueOf(miliseconds/1000));
            }

            public void onFinish(){

            }
        }.start();

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

        final TextView textViewChrono = (TextView) findViewById(R.id.textViewChrono);
        timerControl = (SeekBar) findViewById(R.id.seekBarStop);
        timerControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setProgress(progress);

                textViewChrono.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if(countDownTimer != null)
                    countDownTimer.cancel();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });






        /*final Integer[] time = {0};

        // Allows code to be ran in delayed way. Controls the timming of events/codes.
        final Handler handler = new Handler();
        final TextView textViewCrono = (TextView) findViewById(R.id.textViewChrono);

        Runnable run = new Runnable() {
            @Override
            public void run() {

                // Code to be run every time (I am not sure yet, but I believe it should
                 //follow some hardware tick

                time[0]++;
                textViewCrono.setText(Integer.toString(time[0]));
                handler.postDelayed(this, 1000); // in this case, 1 second.

            }
        };

        // initializing runnable
        handler.post(run);*/

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

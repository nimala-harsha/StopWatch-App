package com.app.fiststopwatch;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;

public class StopWatchAct extends AppCompatActivity {

    Button btnstart, btnstop, btnpause;
    ImageView icanchor;
    Animation roundingalone;
    Chronometer timerHere;
    private long pauseoffset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        getSupportActionBar().hide();

        btnstart = findViewById(R.id.btnstart);
        btnstop = findViewById(R.id.btnstop);
        btnpause = findViewById(R.id.btnpause);
        icanchor = findViewById(R.id.icanchor);
        timerHere = findViewById(R.id.timerHere);

        //load Animation
        roundingalone = AnimationUtils.loadAnimation(this, R.anim.roundingaione);

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //passing animation
                icanchor.startAnimation(roundingalone);
                btnpause.animate().alpha(1).translationY(-80).setDuration(300).start();
                btnstop.animate().alpha(1).translationY(-80).setDuration(300).start();
                btnstart.animate().alpha(1).translationY(-80).setDuration(300).start();
                // start time
                timerHere.setBase(SystemClock.elapsedRealtime() - pauseoffset);
                timerHere.start();
            }
        });

        btnpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // stop animation
                icanchor.clearAnimation();
                //stop time
                timerHere.stop();
                pauseoffset = SystemClock.elapsedRealtime() - timerHere.getBase();
            }
        });

        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // stop animation
                icanchor.clearAnimation();
                timerHere.stop();
                timerHere.setBase(SystemClock.elapsedRealtime());
                pauseoffset = 0;


            }
        });
    }
}
package com.example.mplayer;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.scwang.wave.MultiWaveHeader;

import java.io.File;
import java.util.ArrayList;

import android.os.Bundle;

public class ActivityPlayer extends AppCompatActivity {

    Button playBtn;
    Button nextBtn;
    Button prevBtn;
    Button ffBtn;
    Button frBtn;
    //Button shuffleBtn;
    //Button repeatBtn;
    TextView txtsname,txtsstart,txtsstop;
    MultiWaveHeader wave;
    SeekBar seekmusic;
    Thread updateseekbar;

    String sname;
    public static final String EXTRA_NAME="song_name";
    static MediaPlayer mediaPlayer;
    int position;
    ArrayList<File> mySongs;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_playerr);

        wave = (MultiWaveHeader) findViewById(R.id.wave);

        //Buttons
        playBtn = (Button) findViewById(R.id.playbtn);
        //pauseBtn=(Button) findViewById(R.id.pausebtn);
        nextBtn = (Button) findViewById(R.id.btnnext);
        prevBtn = (Button) findViewById(R.id.btnprev);
        ffBtn = (Button) findViewById(R.id.btnforward);
        frBtn = (Button) findViewById(R.id.btnrewind);

        //TextViews and Seekbar
        txtsname = (TextView) findViewById(R.id.txtsn);
        txtsstart = (TextView) findViewById(R.id.txtsstart);
        txtsstop = (TextView) findViewById(R.id.txtsend);
        seekmusic = (SeekBar) findViewById(R.id.seekbar);

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        mySongs = (ArrayList) bundle.getParcelableArrayList("songs");
        String songName = i.getStringExtra("songname");
        position = bundle.getInt("pos", 0);
        txtsname.setSelected(true);
        Uri uri = Uri.parse(mySongs.get(position).toString());
        sname = mySongs.get(position).getName();
        txtsname.setText(sname);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
        mediaPlayer.start();

        updateseekbar = new Thread()
        {
            @Override
            public void run() {
                int totalDuration = mediaPlayer.getDuration();
                int currentPosition = 0;

                while(currentPosition < totalDuration){
                    try{
                        sleep(500);
                        currentPosition = mediaPlayer.getCurrentPosition();
                        seekmusic.setProgress(currentPosition);
                    }
                    catch(InterruptedException | IllegalStateException e){
                        e.printStackTrace();
                    }
                }

            }
        };

        seekmusic.setMax(mediaPlayer.getDuration());
        updateseekbar.start();
        seekmusic.getProgressDrawable().setColorFilter(getResources().getColor(R.color.gold_metallic),PorterDuff.Mode.MULTIPLY);
        seekmusic.getThumb().setColorFilter(Color.MAGENTA, PorterDuff.Mode.SRC_IN);
        seekmusic.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar,int i,boolean b){

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar){

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar){
                mediaPlayer.seekTo(seekBar.getProgress());

            }
        });

        String endTime  = createTime(mediaPlayer.getDuration());
        txtsstop.setText(endTime);

        final Handler handler = new Handler();
        final int delay = 1000;

        handler.postDelayed( new Runnable(){
            @Override
            public void run(){
                String currentTime = createTime(mediaPlayer.getCurrentPosition());
                txtsstart.setText(currentTime);
                handler.postDelayed(this,delay);
            }
        },delay);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    playBtn.setBackgroundResource(R.drawable.ic_baseline_play_arrow_10);
                    mediaPlayer.pause();
                } else {
                    playBtn.setBackgroundResource(R.drawable.ic_baseline_pause_10);
                    mediaPlayer.start();
                }
            }
        });

        /*
        No Title Bar
        getSupportActionBar().setTitle("Now Playing");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        */

        //Wave
        wave.setVelocity(1);
        wave.setProgress(1);
        wave.isRunning();
        wave.setGradientAngle(45);
        wave.setWaveHeight(40);
        wave.setStartColor(Color.MAGENTA);
        wave.setCloseColor(Color.parseColor("#d4af37"));/**/

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
                position = ((position + 1) % mySongs.size());
                Uri u = Uri.parse(mySongs.get(position).toString());
                mediaPlayer = MediaPlayer.create(getApplicationContext(), u);
                sname = mySongs.get(position).getName();
                txtsname.setText(sname);
                mediaPlayer.start();
                playBtn.setBackgroundResource(R.drawable.ic_baseline_pause_10);
            }

        });
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                position = ((position - 1) < 0) ? (mySongs.size() - 1) : (position - 1);
                Uri u = Uri.parse(mySongs.get(position).toString());
                mediaPlayer = MediaPlayer.create(getApplicationContext(), u);
                sname = mySongs.get(position).getName();
                txtsname.setText(sname);
                mediaPlayer.start();
                playBtn.setBackgroundResource(R.drawable.ic_baseline_pause_10);
            }
        });

        ffBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+10000);
                }

            }
        });

        frBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-10000);
                }

            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                nextBtn.performClick();
            }
        });

    }




    public String createTime(int duration){
        String  time = " ";
        int min = duration/1000/60;
        int sec = duration/1000%60;
        time +=min+":";

        if(sec <10)
        {
            time+= "0";
        }
        time+=sec;
        return time;

    }

}
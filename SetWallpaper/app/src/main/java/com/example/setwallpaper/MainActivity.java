package com.example.setwallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static android.service.controls.ControlsProviderService.TAG;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn;
    boolean running = false;
    int [] imagesArray = new int []{R.drawable.pic,R.drawable.beautiful_scenery,R.drawable.abc,R.drawable.plant,R.drawable.sun};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btnchng);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(!running)
        {
            new Timer().schedule(new MyTimer(),0,30000);
            running=false;
        }
    }
    class MyTimer extends TimerTask
    {
        public void run()
        {
            try {
                {
                    WallpaperManager wallpaperManager = WallpaperManager.getInstance(getBaseContext());
                    Random r = new Random();
                    wallpaperManager.setBitmap(BitmapFactory.decodeResource(getResources(), imagesArray[r.nextInt(6)]));
                    //Log.d(TAG, "loadUserData() after increment is" + i);
                }
            }
            catch (Exception e){}
        }

    }
}
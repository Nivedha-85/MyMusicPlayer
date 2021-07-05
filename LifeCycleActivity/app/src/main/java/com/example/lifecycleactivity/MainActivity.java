package com.example.lifecycleactivity;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String disp ="Activity Life Cycle";
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(disp,"OnCreate Executed");

        btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(disp,"onRestart Executed");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(disp,"onStart Executed");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(disp,"onStop Executed");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(disp,"onPostResume Executed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(disp,"onPause Executed");
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getBaseContext(),"You pressed submit button!",Toast.LENGTH_LONG).show();

    }
}
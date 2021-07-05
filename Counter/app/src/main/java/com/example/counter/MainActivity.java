package com.example.counter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView cnt;
    Button btnstart,btnstop;
    int counter=0;
    boolean running=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cnt=findViewById(R.id.label);
        btnstart=findViewById(R.id.button1);
        btnstop=findViewById(R.id.button2);
        btnstart.setOnClickListener(this);
        btnstop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btnstart))
        {
            running=true;
            new Mycounter().start();
        }
        else
        {
            running=false;
        }
    }
    Handler handler= new Handler(){
        public void handleMessage(Message counter)
        {
            cnt.setText("Counter value: "+String.valueOf(counter.what));
        }};
    class Mycounter extends Thread
    {
        public void run()
        {
            while(running)
            {
                counter++;
                handler.sendEmptyMessage(counter);
                try {
                    Thread.sleep(500);
                }
                catch(Exception e){}
            }
        }
    }
}
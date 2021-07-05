package com.example.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listview;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listview = (ListView) findViewById(R.id.listview1);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        adapter.add("Android Lab");
        adapter.add(" CGV Lab");
        adapter.add("SS Lab");
        adapter.add("WTA lab");
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    String item = (String) adapter.getItem(position);
        Toast.makeText(getBaseContext(),"You clicked on :"+item,Toast.LENGTH_LONG).show();
        if(position==0)
        {
            Intent intent = new Intent(MainActivity.this,MainActivity2.class);
            startActivity(intent);
        }
    }
}
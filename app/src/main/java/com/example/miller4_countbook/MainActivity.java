package com.example.miller4_countbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import com.google.gson.Gson;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private ArrayList<Counter> counters;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        counters = CounterArraySingleton.getInstance().getCounters();
        ListView listview = (ListView) findViewById(R.id.CounterList);
        listview.setOnItemClickListener(this);

        ArrayAdapter<Counter> counterAdapter = new ArrayAdapter<Counter>(this, android.R.layout.simple_list_item_1, counters);

        listview.setAdapter(counterAdapter);
    }


    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
//        Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
        Intent intent = new Intent();
        intent.setClass(this, ListItemDetailActivity.class);
        intent.putExtra("position", position);
        // Or / And
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void addCounter(View view) {
        Intent intent = new Intent(this, AddNewCounterActivity.class);
        startActivity(intent);

    }

}

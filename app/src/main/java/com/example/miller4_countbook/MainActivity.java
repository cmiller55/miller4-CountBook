package com.example.miller4_countbook;

import android.app.Activity;
import android.content.Context;
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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private ArrayList<Counter> counters;
    private static final String FILENAME = "file.sav";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        //counters = CounterArraySingleton.getInstance().getCounters();
        loadFromFile();
        CounterArraySingleton.getInstance().setCounters(counters);
        ListView listview = (ListView) findViewById(R.id.CounterList);
        listview.setOnItemClickListener(this);

        ArrayAdapter<Counter> counterAdapter = new ArrayAdapter<Counter>(this, android.R.layout.simple_list_item_1, counters);

        listview.setAdapter(counterAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveInFile();
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

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(counters,writer);
            writer.flush();

            fos.close();
        } catch (FileNotFoundException e) {

            throw new RuntimeException();
        } catch (IOException e) {
           
            throw new RuntimeException();
        }
    }

    private void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Counter>>() {}.getType();
            counters = gson.fromJson(in, listType);


        } catch (FileNotFoundException e) {

            counters = new ArrayList<Counter>();
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }
}

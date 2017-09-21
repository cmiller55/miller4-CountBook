package com.example.miller4_countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Date;


public class ListItemDetailActivity extends AppCompatActivity {

    private Counter selectedCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_detail);

        Intent intent = getIntent();
        Integer position = intent.getIntExtra("position", 0);
        selectedCounter = CounterArraySingleton.getInstance().getCounters().get(position);

        EditText nameText = (EditText) findViewById(R.id.EditNameField);
        nameText.setText(selectedCounter.getName());
    }

    public void incrementCounter(View v) {
        selectedCounter.setCurrentValue(selectedCounter.getCurrentValue() + 1);
        selectedCounter.setDate(new Date());
    }

    public void decrementCounter(View v) {
        selectedCounter.setCurrentValue(selectedCounter.getCurrentValue() - 1);
        selectedCounter.setDate(new Date());
    }
}

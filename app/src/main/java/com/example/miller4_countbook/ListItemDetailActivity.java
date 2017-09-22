package com.example.miller4_countbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ListItemDetailActivity extends AppCompatActivity {

    private Counter selectedCounter;
    private static final String FILENAME = "file.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_detail);

        Intent intent = getIntent();
        Integer position = intent.getIntExtra("position", 0);
        selectedCounter = CounterArraySingleton.getInstance().getCounters().get(position);

        updateAllFields();

    }

    public void updateAllFields(){
        updateNameField();
        updateDateField();
        updateCurrentValueField();
        updateInitialValueField();
        updateCommentField();
    }

    public void updateNameField(){
        EditText editText = (EditText) findViewById(R.id.EditNameField);
        editText.setText(selectedCounter.getName());
    }

    public void updateDateField() {
        TextView editText = (TextView) findViewById(R.id.DateField);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormatter.format(selectedCounter.getDate());
        editText.setText(formattedDate);
    }

    public void updateCurrentValueField() {
        EditText editText = (EditText) findViewById(R.id.EditCurrentValueField);
        editText.setText(selectedCounter.getCurrentValue().toString());
    }

    public void updateInitialValueField() {
        EditText editText = (EditText) findViewById(R.id.EditInitialValueField);
        editText.setText(selectedCounter.getInitialValue().toString());
    }

    public void updateCommentField() {
        EditText editText = (EditText) findViewById(R.id.EditCommentField);
        editText.setText(selectedCounter.getComment());
    }

    public void incrementCounter(View v) {
        selectedCounter.setCurrentValue(selectedCounter.getCurrentValue() + 1);
        selectedCounter.setDate(new Date());
        updateDateField();
        updateCurrentValueField();
    }

    public void decrementCounter(View v) {
        if(selectedCounter.getCurrentValue()>0) {
            selectedCounter.setCurrentValue(selectedCounter.getCurrentValue() - 1);
            selectedCounter.setDate(new Date());
            updateDateField();
            updateCurrentValueField();
        }
    }

    public void updateCounter(View v) {
        EditText editText = (EditText) findViewById(R.id.EditCurrentValueField);
        if(!(editText.getText().toString().equals(selectedCounter.getCurrentValue().toString()))){
            selectedCounter.setCurrentValue(Integer.parseInt(editText.getText().toString()));
            selectedCounter.setDate(new Date());
        }
        editText = (EditText) findViewById(R.id.EditNameField);
        selectedCounter.setName(editText.getText().toString());
        editText = (EditText) findViewById(R.id.EditInitialValueField);
        selectedCounter.setInitialValue(Integer.parseInt(editText.getText().toString()));
        editText = (EditText) findViewById(R.id.EditCommentField);
        selectedCounter.setComment(editText.getText().toString());

        saveInFile();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void resetCounter(View v) {
        if(!(selectedCounter.getCurrentValue().equals(selectedCounter.getInitialValue()))){
            selectedCounter.setCurrentValue(selectedCounter.getInitialValue());
            selectedCounter.setDate(new Date());
        }

        saveInFile();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void deleteCounter(View v) {
        CounterArraySingleton.getInstance().getCounters().remove(selectedCounter);

        saveInFile();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(CounterArraySingleton.getInstance().getCounters(),writer);
            writer.flush();

            fos.close();
        } catch (FileNotFoundException e) {

            throw new RuntimeException();
        } catch (IOException e) {

            throw new RuntimeException();
        }
    }
}


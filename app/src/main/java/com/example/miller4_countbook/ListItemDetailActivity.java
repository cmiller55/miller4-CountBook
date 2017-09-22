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

// activity for viewing and editing details of a counter
public class ListItemDetailActivity extends AppCompatActivity {

    private Counter selectedCounter;
    private static final String FILENAME = "file.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_detail);

        // used the indentifiers stored to retrive the selected counter from the singleton
        Intent intent = getIntent();
        Integer position = intent.getIntExtra("position", 0);
        selectedCounter = CounterArraySingleton.getInstance().getCounters().get(position);

        // initialize all the data fields with the current attributes of the counter
        updateAllFields();

    }

    // simple holder method intended to save lines, though end code design made it underused
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

    // extra condition check for the date since the date is only modified when the current value changes
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

    // increment method is linked to increment button in xml file
    public void incrementCounter(View v) {
        selectedCounter.setCurrentValue(selectedCounter.getCurrentValue() + 1);
        selectedCounter.setDate(new Date());
        //updateDateField();
        updateCurrentValueField();
    }

    // decrement method is linked to increment button in xml file
    public void decrementCounter(View v) {
        if(selectedCounter.getCurrentValue()>0) {
            selectedCounter.setCurrentValue(selectedCounter.getCurrentValue() - 1);
            selectedCounter.setDate(new Date());
            //updateDateField();
            updateCurrentValueField();
        }
    }

    // method for updating the counter attributes using the values entered into the fields - wanted
    // to use updateAllFields but current value condition check on date prevented it
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

        // upon finishing updating the counter, save to the file and return to main activity
        saveInFile();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // method to reset the counters current value - another condition check for modifying the date
    public void resetCounter(View v) {
        if(!(selectedCounter.getCurrentValue().equals(selectedCounter.getInitialValue()))){
            selectedCounter.setCurrentValue(selectedCounter.getInitialValue());
            selectedCounter.setDate(new Date());
        }

        // save the counters and restart the main activity
        saveInFile();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // simple delete method - remove counter from the singleton, save and return to main activity
    public void deleteCounter(View v) {
        CounterArraySingleton.getInstance().getCounters().remove(selectedCounter);

        saveInFile();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // the same lab 3 save function from main activity but with a minor alteration, the counters are pulled from the
    // singleton as they are not already present. Though having a similar save function three times
    // in the app is redundant, issues with openFileOutput prevented any attempts at fixing this
    // redundancy - the load function did not need to be reproduced as load is always called upon
    // returning to the main activity
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


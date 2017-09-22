package com.example.miller4_countbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

// activity for adding new counters to the list
public class AddNewCounterActivity extends AppCompatActivity {

    private static final String FILENAME = "file.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_counter);

    }

    // create a new counter from the information  entered into the fields - if a required field is
    // left empty, a default value is given which the user can edit in the edit menu
    public void createCounter(View v) {
        String name;
        Integer initialValue;
        String comment;
        EditText editText = (EditText) findViewById(R.id.NewNameField);
        if(isFieldEmpty(editText)){
            name = "NoNameGiven";
        } else {
            name = editText.getText().toString();
        }
        editText = (EditText) findViewById(R.id.NewValueField);
        if(isFieldEmpty(editText)) {
            initialValue = 0;
        } else {
            initialValue = Integer.parseInt(editText.getText().toString());
        }
        // no field check for comment as it is an optional field
        editText = (EditText) findViewById(R.id.NewCommentField);
        comment = editText.getText().toString();

        // create the new counter and insert it into the singleton, then save and return to main
        CounterArraySingleton.getInstance().getCounters().add(new Counter(name, initialValue, comment));
        saveInFile();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // simple check for empty field - removes whitespace and checks for actual characters
    private boolean isFieldEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }

    // lab 3 save function with the same modification as in ListItemDetailActivity
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

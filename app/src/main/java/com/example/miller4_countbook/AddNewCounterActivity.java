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

public class AddNewCounterActivity extends AppCompatActivity {

    private static final String FILENAME = "file.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_counter);


    }

    public void createCounter(View v) {
        EditText editText = (EditText) findViewById(R.id.NewNameField);
        String name = editText.getText().toString();
        editText = (EditText) findViewById(R.id.NewValueField);
        Integer initialValue = Integer.parseInt(editText.getText().toString());
        editText = (EditText) findViewById(R.id.NewCommentField);
        String comment = editText.getText().toString();
        CounterArraySingleton.getInstance().getCounters().add(new Counter(name, initialValue, comment));

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

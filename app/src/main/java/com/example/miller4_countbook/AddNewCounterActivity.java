package com.example.miller4_countbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddNewCounterActivity extends AppCompatActivity {

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
    }

}

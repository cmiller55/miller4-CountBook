package com.example.miller4_countbook;

import java.util.ArrayList;

/**
 * Created by Cole on 2017-09-18.
 */


// https://stackoverflow.com/questions/32466044/edit-array-from-another-activity

public final class CounterArraySingleton {
    private static final CounterArraySingleton SELF = new CounterArraySingleton();
    private ArrayList<Counter> counters = new ArrayList<Counter>();

    private CounterArraySingleton() {}

    public static CounterArraySingleton getInstance() {
       return SELF;
    }

    public ArrayList<Counter> getCounters() {
        return counters;
    }
}


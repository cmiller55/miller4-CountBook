package com.example.miller4_countbook;

import java.util.ArrayList;

/**
 * Created by Cole on 2017-09-18.
 */

// note - idea and implementation for singleton inspired by the following link:
// https://stackoverflow.com/questions/32466044/edit-array-from-another-activity

// purpose of singleton is to provide a single instance of an object that holds the counter list
// and provides easy access to the counters for the entire app
public final class CounterArraySingleton {
    private static final CounterArraySingleton SELF = new CounterArraySingleton();
    private ArrayList<Counter> counters;

    // empty private constructor to ensure only one instance exists
    private CounterArraySingleton() {}

    // retrieve existing instance of singleton from any location in app
    public static CounterArraySingleton getInstance() {
       return SELF;
    }

    // after instance is obtained, get counter array held in singleton
    public ArrayList<Counter> getCounters() {
        return counters;
    }

    // used to write counters from loaded file to singleton
    public void setCounters(ArrayList<Counter> counterList){
        this.counters = counterList;
    }

}


package com.example.miller4_countbook;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Cole on 2017-09-16.
 */

// class for counter objects and related methods
public class Counter {

    private String name;
    private Date date;
    private Integer currentValue;
    private Integer initialValue;
    private String comment;

// constructor for counter object - no comment is represented by an empty string for this.comment
    public Counter(String name, Integer initialValue, String comment) {
        this.name = name;
        this.date = new Date();
        this.currentValue = initialValue;
        this.initialValue = initialValue;
        this.comment = comment;
    }

    // getters and setters - basic encapsulation
    public Integer getCurrentValue(){
        return this.currentValue;
    }

    public String getName(){
        return this.name;
    }

    public Date getDate(){
        return this.date;
    }

    public Integer getInitialValue(){
        return this.initialValue;
    }

    public String getComment(){
        return this.comment;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public void setInitialValue(Integer initialValue){
        this.initialValue = initialValue;
    }

    public void setCurrentValue(Integer currentValue){
        this.currentValue = currentValue;
    }

    public void setComment(String comment){ this.comment = comment; }

    // toString method for counter object - used when counters are displayed in the main list
    @Override
    public String toString() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormatter.format(this.getDate());
        return (name +":   " + formattedDate + "   -   " + this.getCurrentValue().toString());
    }

}

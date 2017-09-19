package com.example.miller4_countbook;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Cole on 2017-09-16.
 */

public class Counter {

    private String name;
    private Date date;
    private Integer currentValue;
    private Integer initialValue;
    private String comment;

    public Counter(String name, Integer initialValue) {
        this.name = name;
        this.date = new Date();
        this.currentValue = initialValue;
        this.initialValue = initialValue;
        this.comment = "";
    }

    public Counter(String name, Integer initialValue, String comment) {
        this.name = name;
        this.date = new Date();
        this.currentValue = initialValue;
        this.initialValue = initialValue;
        this.comment = comment;
    }

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

    @Override
    public String toString() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormatter.format(this.getDate());
        return (name +":   " + formattedDate + "   -   " + this.getCurrentValue().toString());
    }

}

package com.example.assignment1;

import java.util.Date;

public class DiaryEntry {
    private String breakfast;
    private String lunch;
    private String dinner;
    private String snacks;
    private String weightlifting;
    private String cardio;
    private String mixed;
    private Date date;



    public DiaryEntry(String breakfast, String lunch, String dinner, String snacks, String weightlifting, String cardio, String mixed, Date date){
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.snacks = snacks;
        this.weightlifting = weightlifting;
        this.cardio = cardio;
        this.mixed = mixed;
        this.date = date;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public void setSnacks(String snacks) {
        this.snacks = snacks;
    }

    public void setWeightlifting(String weightlifting) {
        this.weightlifting = weightlifting;
    }

    public void setCardio(String cardio) {
        this.cardio = cardio;
    }

    public void setMixed(String mixed) {
        this.mixed = mixed;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public String getSnacks() {
        return snacks;
    }

    public String getCardio() {
        return cardio;
    }

    public String getMixed() {
        return mixed;
    }

    public String getWeightlifting() {
        return weightlifting;
    }

    public Date getDate() {
        return date;
    }

    public String getFoodKJ() {
        return (Double.parseDouble(getBreakfast()) + Double.parseDouble(getLunch()) + Double.parseDouble(getDinner()) + Double.parseDouble(getSnacks())) + "";
    }

    public String getExerciseKJ() {
        return (Double.parseDouble(getWeightlifting()) + Double.parseDouble(getCardio()) + Double.parseDouble(getMixed())) + "";
    }

    public String getNKI() {
        return (Double.parseDouble(getFoodKJ()) - Double.parseDouble(getExerciseKJ())) + "";
    }
}

package com.example.template.ipo;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class CTime {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int seconds;
    private LocalDateTime locDat;
    public int getValue(){
        return year*month*day*hour*seconds;
    }
    public void setCurrentTime(){
        this.year = locDat.getYear();
        this.month = locDat.getMonthValue();
        this.day = locDat.getDayOfMonth();
        this.hour = locDat.getHour();
        this.seconds = locDat.getSecond();
    }
    public CTime(int year, int month, int day, int hour, int seconds) {
        if(year<0){
            this.year = 0;
        }else{
        this.year = year;}
        if(month>12){
            this.month = 12;
        }
        if(month<0){
            this.month = 0;
        }
        else{
        this.month = month;}
        if(day>31){
            this.day = 31;
        }
        if(day<0){
            this.day = 0;
        }else{
            this.day = day;
        }
        if(hour>59){
            this.day = 59;
        }
        if(hour<0){
            this.hour = 0;
        }else{
            this.hour = hour;
        }
        if(seconds>59){
            this.seconds = 59;
        }
        if(seconds<0){
            this.seconds = 0;
        }else{
            this.seconds = seconds;
        }
    }
    public CTime(int year, int month, int day) {
        if(year<0){
            this.year = 0;
        }else{
            this.year = year;}
        if(month>12){
            this.month = 12;
        }
        if(month<0){
            this.month = 0;
        }
        else{
            this.month = month;}
        if(day>31){
            this.day = 31;
        }
        if(day<0){
            this.day = 0;
        }else{
            this.day = day;
        }
    }

}

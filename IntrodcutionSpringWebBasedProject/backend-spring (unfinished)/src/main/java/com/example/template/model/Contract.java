package com.example.template.model;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Contract {

    private double basic;
    private double overhours;
    private double holidays;
    private Date begin;
    private Date end;
    private boolean DateHasEnd = false;

    public void setHasEnd(boolean hasend, Date dEnd){
        DateHasEnd = hasend;
        if(hasend){
            end = dEnd;
        }
    }


    public Contract(){

    }

    public double getBasic() {
        return basic;
    }

    public void setBasic(double basic) {
        this.basic = basic;
    }

    public double getOverhours() {
        return overhours;
    }

    public void setOverhours(double overhours) {
        this.overhours = overhours;
    }

    public double getHolidays() {
        return holidays;
    }

    public void setHolidays(double holidays) {
        this.holidays = holidays;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public boolean isDateHasEnd() {
        return DateHasEnd;
    }

    public void setDateHasEnd(boolean dateHasEnd) {
        DateHasEnd = dateHasEnd;
    }


}

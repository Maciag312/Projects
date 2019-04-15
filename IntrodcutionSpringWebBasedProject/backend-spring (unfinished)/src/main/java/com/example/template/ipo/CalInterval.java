package com.example.template.ipo;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class CalInterval {
    private Calendar Begin;

    private Calendar Finish;


    public CalInterval(Calendar begin, Calendar finish) {
        Begin = begin;
        Finish = finish;
    }

    public Calendar getStart() {
        return Begin;
    }

    public void setStart(Calendar begin) {
        Begin = begin;
    }

    public Calendar getFinish() {
        return Finish;
    }

    public void setFinish(Calendar finish) {
        Finish = finish;
    }
}

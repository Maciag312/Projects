package com.example.template.model;


import com.example.template.ipo.CTime;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
public class CTask {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "employee_id")
    private Long employee_id;

    private Calendar start;
    private Calendar finish;
    private String description;

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public Calendar getFinish() {
        return finish;
    }

    public void setFinish(Calendar finish) {
        this.finish = finish;
    }

    public CTask(Calendar start, Calendar finish, String description, Long eId) {
        this.description = description;
        this.finish=finish;
        this.start=start;
        this.employee_id = eId;
    }

    @Override
    public String toString(){
        return String.format("Tasks[id=%d, start='%s', finish='%s', descirption='%s']",
                id, this.id, this.start, this.description);
    }
}

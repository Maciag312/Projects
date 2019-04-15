package com.example.template.model;


import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
public class CEvent {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "employee_id")
    private Long employee_id;

    private Calendar start;
    private Calendar finish;
    private String description;

    public CEvent(Calendar start, Calendar finish, String description, Long eId) {
        this.description = description;
        this.finish=finish;
        this.start=start;
        this.employee_id = eId;
    }

    @Override
    public String toString(){
        return String.format("Event[id=%d, start='%s', finish='%s', descirption='%s']",
                id, this.id, this.start, this.description);
    }
}

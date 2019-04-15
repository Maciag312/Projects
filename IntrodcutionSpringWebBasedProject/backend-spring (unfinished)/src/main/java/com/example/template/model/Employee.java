package com.example.template.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="employee_id")
    private Long id;

    private Date StartWorkingInCompany;
    private int DaysWithoutSickness;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "emloyee_id")
    private List<CTask> cTaskList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "emloyee_id")
    private List<CEvent> cEventList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "emloyee_id")
    private List<CSickleave> cSickleaveList;

    private String lastname;
    private String surname;
    private String Adress;
    private String Nationality;
    private String Description;
    private String Position;
    private boolean isFired = false;
    private Contract contract;
    public Employee(){

    }
    public Employee(String lastname, String surname, String adress, String nationality, String description, String position, Contract contract) {

        this.lastname = lastname;
        this.surname = surname;
        Adress = adress;
        Nationality = nationality;
        Description = description;
        Position = position;
        this.contract = contract;
    }

    public List<CTask> getcTaskList() {
        return cTaskList;
    }

    public void setcTaskList(List<CTask> cTaskList) {
        this.cTaskList = cTaskList;
    }

    public List<CEvent> getcEventList() {
        return cEventList;
    }

    public void setcEventList(List<CEvent> cEventList) {
        this.cEventList = cEventList;
    }

    public List<CSickleave> getcSickleaveList() {
        return cSickleaveList;
    }

    public void setcSickleaveList(List<CSickleave> cSickleaveList) {
        this.cSickleaveList = cSickleaveList;
    }

    public boolean isFired() {
        return isFired;
    }

    public void setFired(boolean fired) {
        isFired = fired;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartWorkingInCompany() {
        return StartWorkingInCompany;
    }

    public void setStartWorkingInCompany(Date startWorkingInCompany) {
        StartWorkingInCompany = startWorkingInCompany;
    }

    public int getDaysWithoutSickness() {
        return DaysWithoutSickness;
    }

    public void setDaysWithoutSickness(int daysWithoutSickness) {
        DaysWithoutSickness = daysWithoutSickness;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}


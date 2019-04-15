package com.company;

import java.util.Comparator;

public class RequestDynamic extends Request implements Comparable<RequestDynamic> {
    int time = 0;
    int deadline = 0;
    int Earliest = 0;
    public RequestDynamic(int position, int time, int deadline){
        super(position);
        this.time = time;
        this.deadline = deadline;
    }

    public void SetEarliest(int time){
        this.Earliest = deadline-time;
    }
    public void load(int position, int time, int deadline){
        this.executeable = true;
        this.Position = position;
        this.time = time;
        this.deadline = deadline;

    }
    public boolean execute(){
        if(executeable==true && time==0){
            return false;
        }
        time = 0;
        executeable = false;
        return true;
    }

    @Override
    public int compareTo(RequestDynamic o) {
        return this.Earliest -((RequestDynamic) o).Earliest;
    }


}

package com.company;
public class Process {
    private int arrivalTime;
    private int executionTime;
    private int burstTime;
    private boolean start = false;
    private String name = "P";
    private int waitingTime = 0;
    private char[] ArrProc = new char[1000]; // 1 non , 2 wait , 3 executed

    public Process(int arrivalTime, int executionTime, int id) {
        this.arrivalTime = arrivalTime;
        this.executionTime = executionTime;
        this.name += Integer.toString(id);
        this.burstTime = arrivalTime + executionTime;
        for(int i = 0; i<ArrProc.length; i++){
            ArrProc[i] = ' ';
        }
    }
    public String getName(){
        return name;
    }

    public int getExecutionTime() {
        return executionTime;
    }
    public void execute1ms(int time2){
        ArrProc[time2] = 'B';
        executionTime -= 1;
        start = true;
    }


    public void Print(){
        System.out.print(name + " |");
        for(int i=0; i<ArrProc.length; i++){
            System.out.print(ArrProc[i]);
        }
        System.out.println();
    }
    public boolean ifAvail(int time){
        if(time>=arrivalTime){
            return true;
        }
        return false;
    }
    public boolean IsFinished(){
        if(executionTime>0){
            return false;
        }
        return true;
    }
    public void wait1ms(int time2){
        ArrProc[time2] = 'W';
        waitingTime += 1;
    }

    public int getBurstTime(){
        return burstTime;
    }
    public int getWaitingTime() {
        return waitingTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }
}
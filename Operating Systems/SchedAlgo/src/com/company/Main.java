package com.company;
import java.util.*;

public class Main {
    private static double averageWaitingTime = 0;
    private static double FIFO(ArrayList<Process> processes1) {
        int SumExeTime = 0;

        Collections.sort(processes1, new Comparator<Process>(){
            public int compare(Process p1, Process p2){
                return p1.getArrivalTime()-p2.getArrivalTime();
            }
        });
        for(Process prs: processes1){
            SumExeTime += prs.getExecutionTime();
        }
        int q = 1;
        int time = 0;
        boolean executedInThatSec = false;
        Iterator itr = processes1.iterator();
        while(SumExeTime!=0){
            executedInThatSec = false;
            for(int i = 0; i<processes1.size(); i++){
                if(processes1.get(i).ifAvail(time) && !processes1.get(i).IsFinished()){
                    if(!executedInThatSec){
                        processes1.get(i).execute1ms(time);
                        SumExeTime -= 1;
                        executedInThatSec = true;
                    }else{
                        processes1.get(i).wait1ms(time);
                    }
                }
            }
            time+=1;
        }
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("First-in First-out Algorithm");
        for(Process proc: processes1){
            averageWaitingTime += proc.getWaitingTime();
            proc.Print();
        }
        averageWaitingTime = (double)averageWaitingTime/processes1.size();

        System.out.print("AverageWaitingTime = " + averageWaitingTime);

        return averageWaitingTime;
    }

    public static double RoundRobin(int quantum, ArrayList<Process> processes1) {
        int SumExeTime = 0;
        int SumExeTimeC = 0;
        for(Process prs: processes1){
            SumExeTime += prs.getExecutionTime();
        }
        SumExeTimeC = SumExeTime;
        int time = 0;
        int exectime = 0;
        while(SumExeTime!=0){
            for (int j = 0; j < processes1.size(); j++) {
                for (int i = 0; (i < quantum && !processes1.get(j).IsFinished()) ||  ((exectime <= quantum &&!processes1.get(j).IsFinished())) ; i++) {
                    if(processes1.get(j).ifAvail(time)) {
                        processes1.get(j).execute1ms(time);
                        SumExeTime--;
                        exectime +=1;
                        for(int k = 0; k<processes1.size(); k++){
                            if(k!=j){
                                if(processes1.get(k).ifAvail(time)&&!processes1.get(k).IsFinished()){
                                    processes1.get(k).wait1ms(time);
                                }
                            }
                        }
                    }
                    time += 1;
                }
            }
        }
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Round Robin Algorithm");
        for(Process proc: processes1){
            averageWaitingTime += proc.getWaitingTime();
            proc.Print();
        }
        averageWaitingTime = (double)averageWaitingTime/processes1.size();

        System.out.print("AverageWaitingTime = " + averageWaitingTime);
        return averageWaitingTime;
    }

    public static double SJF(ArrayList<Process> processes1) {

        int time = 0;
        int Sum = 0;
        for(Process prs: processes1){
            Sum += prs.getExecutionTime();
        }
        Process theShortestAvail = new Process(0,0, 0);
        processes1.sort(Comparator.comparing(Process::getArrivalTime).thenComparing(Process::getExecutionTime));
        boolean save = true;
        while(Sum!=(time-1)){
            theShortestAvail = new Process(0,0, 0);
            for(int i=0;i<processes1.size();i++){
                if(processes1.get(i).ifAvail(time)&&!processes1.get(i).IsFinished()){
                    if(save){
                        theShortestAvail =  processes1.get(i);
                        save = false;

                    }
                    if(theShortestAvail.getExecutionTime()>processes1.get(i).getExecutionTime()){
                        theShortestAvail = processes1.get(i);
                    }
                }
            }
            int b = processes1.indexOf(theShortestAvail);
            int ext = theShortestAvail.getExecutionTime();
            for(int a = 0; a < ext;a++){

                processes1.get(b).execute1ms(time+a);
                for(int c=0; c<processes1.size(); c++){
                    if(processes1.get(c).ifAvail(time+a) && !processes1.get(c).IsFinished()){
                        if(c!=b) {
                            processes1.get(c).wait1ms(time+a);
                        }
                    }
                }
            }
            save = true;
            if(ext == 0){
                time+=1;
            }else{
                time+=ext;
            }

        }
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Short Job Next Non-Preemptive Algorithm");
        for(Process proc: processes1){
            averageWaitingTime += proc.getWaitingTime();
            proc.Print();
        }
        averageWaitingTime = (double)averageWaitingTime/processes1.size();

        System.out.print("AverageWaitingTime = " + averageWaitingTime);

        return averageWaitingTime;
    }
    public static double SJFP(ArrayList<Process> processArrayList){
        Collections.sort(processArrayList, new Comparator<Process>(){
            public int compare(Process pr1, Process pr2){
                return pr1.getBurstTime()-pr2.getBurstTime();
            }
        });
        int sum = 0;
        for(Process prc: processArrayList){
            sum += prc.getExecutionTime();
        }
        int time = 0;
        while(time<sum) {
            ArrayList<Process> procesAR = new ArrayList<>();
            for (int i = 0; i < processArrayList.size(); i++) {
                if (!processArrayList.get(i).IsFinished() && processArrayList.get(i).ifAvail(time)) {
                    procesAR.add(processArrayList.get(i));
                }
            }
            procesAR.sort(Comparator.comparing(Process::getExecutionTime));
            if(!procesAR.isEmpty()){
                Process proc = procesAR.get(0);
                int a = 0;
                int b = processArrayList.indexOf(proc);
                processArrayList.get(b).execute1ms(time);
                for(Process ppp: procesAR){

                    if(a!=0){
                        int c = processArrayList.indexOf(ppp);
                        processArrayList.get(c).wait1ms(time);
                    }
                    a++;

                }
            }
            time++;
        }

        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Short Job Next Preemptive Algorithm");
        for(Process proc: processArrayList){
            averageWaitingTime += proc.getWaitingTime();
            proc.Print();
        }
        averageWaitingTime = (double)averageWaitingTime/processArrayList.size();

        System.out.print("AverageWaitingTime = " + averageWaitingTime);
        return averageWaitingTime;
    }
    public static ArrayList<Process> initProces(int[] ArrivalTime, int[] BurstTime, int howMany){
        ArrayList<Process> proc = new ArrayList<>();
        for(int i=0; i<howMany; i++){
            proc.add(new Process(ArrivalTime[i], BurstTime[i], i));
        }
        return proc;
    }
    public static void main(String args[]) {
        ArrayList<Process> prs = new ArrayList<>();
        int ArrivalTime[] = {1,2,3,8,4,3,3,5,6,12,20,13,14,15,16,18,20,23,13,26,28,29,30,15,13,20,25,30,32,30,31,32,34,35,36,37,38,39,40};
        int BursTime[] = {6,2,6,2,4,6,3,4,2,6,3,3,4,12,3,4,2,8,6,3,4,5,5,5,2,5,3,3,4,3,3,4,4,4,3,3,5,2,5};
        int howMany = BursTime.length;

        ArrayList<Process> Rr = initProces(ArrivalTime, BursTime, howMany);
        ArrayList<Process> sjf = initProces(ArrivalTime, BursTime, howMany);
        ArrayList<Process> fifo = initProces(ArrivalTime, BursTime, howMany);
        ArrayList<Process> sjfp = initProces(ArrivalTime, BursTime, howMany);


        double theFastest;
        int quantum = 3;
        double RRtime = RoundRobin(quantum, Rr);
        double SJFtime = SJF(sjf);
        double SJFPtime = SJFP(sjfp);
        double FIFOtime = FIFO(fifo);
        System.out.println(" ");

        System.out.println(" ");
        System.out.println("W-Waiting Time, B-Burst Time");

        System.out.println(" ");
        System.out.println(" ");

        System.out.println("Comparing the results: ");
        System.out.println(" ");

        System.out.println("For given processes Shortest Job First Non-Preemtive| AverageTime = "+SJFtime);
        System.out.println("For given processes Round Robin and quantum = " + quantum+" | AverageTime = "+ RRtime);
        System.out.println("For given processes Shortest Job First Preemtive| AverageTime = "+SJFPtime);
        System.out.println("For given processes First-In First-Out| AverageTime = "+FIFOtime);
        System.out.println(" ");

        double max = Math.max(RRtime, SJFtime);
        if(max<Math.max(SJFtime, SJFPtime)){
            max = Math.max(SJFtime, SJFPtime);
        }
        if(max<Math.max(SJFPtime, FIFOtime)){
            max = Math.max(SJFPtime, FIFOtime);
        }
        double min = Math.min(RRtime, SJFtime);
        if(min>Math.min(SJFtime, SJFPtime)){
            min = Math.min(SJFtime, SJFPtime);
        }
        if(min<Math.min(SJFPtime, FIFOtime)){
            min = Math.min(SJFPtime, FIFOtime);
        }
        if(min==SJFtime){
            System.out.println("Shortest Job First Non-preemtive is the fastest for given processes| AverageTime = "+SJFPtime);
            System.out.println("and its from slowest " + (max-min) + " faster" );
        }
        if(min==SJFPtime){
            System.out.println("Shortest Job First preemtive is the fastest for given processes| AverageTime = "+SJFPtime);
            System.out.println("and its from slowest " + (max-min) + " faster" );
        }
        if(min==RRtime){
            System.out.println("Round Robin is the fastest for given processes| AverageTime = "+SJFPtime);
            System.out.println("and its from slowest " + (max-min) + " faster" );
        }
        if(min==FIFOtime){
            System.out.println("First-in first-out is the fastest for given processes| AverageTime = "+SJFPtime);
            System.out.println("and its from slowest " + (max-min) + " faster" );
        }


    }
}

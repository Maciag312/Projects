package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Disk {
    int headPos = 80;
    int track = 0;
    ArrayList<Request> requests = new ArrayList<>();
    ArrayList<RequestDynamic> requestsD = new ArrayList<>();

    public void LoadRequest(ArrayList<Request> req){
        requests = req;
    }
    public void LoadRequesDt(ArrayList<RequestDynamic> req){
        requestsD = req;
    }
    public void run(){

    }
    public void changePos(int pos){
        int cur = headPos;
        headPos = pos;
        System.out.print(" HP: "+headPos);
        track += Math.abs((cur-headPos));
    }
    public int distance(Request req){
        return Math.abs((headPos-req.Position));
    }
    public int distanceD(Request req){
        return Math.abs((headPos-req.Position));
    }
    public void clearTracker(){
        track = 0;
    }
    public void setHeadPos(int i){
        headPos = i;
    }
    public boolean checkIfAllExecuted(ArrayList<Request> requests){
        for(Request re: requests){
            if(re.executeable!=false){
                return false;
            }
        }
        return true;
    }
    public boolean checkIfAllExecutedD(ArrayList<RequestDynamic> requests){
        for(Request re: requests){
            if(re.executeable!=false){
                return false;
            }
        }
        return true;
    }
    public void fcfo(ArrayList<Request> reques){
        for(Request re: reques ){
            changePos(re.Position);
            re.execute();
        }
        System.out.println("\n For First-Come First-Out algorithm track of disk head equals " + track + "\n");
        setHeadPos(80);
        clearTracker();
    }
    public void scan(ArrayList<Request> reques, int startFrom){
        headPos = startFrom;
        int curpos = headPos;
        int from = headPos;
        while(curpos!=0){
            for(int i = 0; i<reques.size(); i++){
                if(reques.get(i).Position == curpos){
                    changePos(reques.get(i).Position);
                    reques.get(i).execute();
                }
            }

            curpos--;
        }
        changePos(0);
        while(!checkIfAllExecuted(reques)){
            from++;
            for(int i = 0; i<reques.size(); i++){
                if(reques.get(i).Position == from){
                    changePos(reques.get(i).Position);
                    reques.get(i).execute();
                }
            }

        }
        System.out.println("\n For SCAN algorithm track of disk head equals " + track + "\n");
        setHeadPos(80);
        clearTracker();
    }
    public void cscan(ArrayList<Request> reques, int startFrom, int max) {
        headPos = startFrom;
        int curpos = headPos;
        while (curpos != max) {
            for (int i = 0; i < reques.size(); i++) {
                if (reques.get(i).Position == curpos) {
                    changePos(reques.get(i).Position);
                    reques.get(i).execute();
                }
            }
            curpos++;
        }
        headPos = 0;
        System.out.print(" HP: "+headPos);
        int from = headPos;
        while (!checkIfAllExecuted(reques)) {
            for (int i = 0; i < reques.size(); i++) {
                if (reques.get(i).Position == from) {
                    changePos(reques.get(i).Position);
                    reques.get(i).execute();
                }
            }
            from++;
        }
        System.out.println("\n For C-SCAN algorithm track of disk head equals " + track + "\n");
        setHeadPos(80);
        clearTracker();
    }
    public void sstf(ArrayList<Request> reques){
        int a = 0;
        while(!checkIfAllExecuted(reques)){
            ArrayList<Request> reqAva = new ArrayList<>();
            for(int c = 0; c<reques.size();c++){
                if(reques.get(c).executeable){
                    reqAva.add(reques.get(c));
                }
            }
            if(reqAva.size()>=1) {
                Request toCompare = reqAva.get(0);
                if (reqAva.size() >= 2) {

                    for (int i = 1; i < reqAva.size(); i++) {
                        if (distance(toCompare) > distance(reqAva.get(i))) {
                            toCompare = reqAva.get(i);
                        }
                    }
                }
                int g = reques.indexOf(toCompare);
                changePos(reques.get(g).Position);
                reques.get(g).execute();
            }

         }
        System.out.println("\n For SSTF algorithm track of disk head equals " + track + "\n");
        setHeadPos(80);
        clearTracker();
    }

    public void edf(ArrayList<RequestDynamic> reques){
        int time = 0;
        ArrayList<RequestDynamic> ReqAvai = new ArrayList<>();
        ArrayList<RequestDynamic> Executed = new ArrayList<>();

        while(true){
            ReqAvai.clear();
            for(int a=0; a<reques.size();a++){
                reques.get(a).SetEarliest(time);
            }
            for(RequestDynamic req: reques){
                if(req.executeable&&req.Earliest>0){
                    ReqAvai.add(req);
                }
            }
            if(ReqAvai.size()==0)
                break;
            Collections.sort(ReqAvai);
            time += ReqAvai.get(0).time;
            changePos(ReqAvai.get(0).Position);
            Executed.add(ReqAvai.get(0));
            ReqAvai.get(0).execute();

        }
        System.out.println("\n For EDF algorithm track of disk head equals " + track + "\n");

        System.out.println("Processes executed:  ");
        for(int i = 0; i<reques.size(); i++){
            if(Executed.contains(reques.get(i))){
                System.out.print("P"+ i + " ");
            }
        }
        if(reques.size()>Executed.size()){
            System.out.println("\nFollwing processes couldnt meet deadline: ");
            for(int i = 0; i<reques.size(); i++){
                if(!Executed.contains(reques.get(i))){
                    System.out.print("P"+ i + " ");
                }
            }
            System.out.println("\n");



        }
        setHeadPos(80);
        clearTracker();


    }

    public void fdscan(ArrayList<RequestDynamic> reques){
        int time = 0;
        ArrayList<RequestDynamic> ReqAvai = new ArrayList<>();
        ArrayList<RequestDynamic> Executed = new ArrayList<>();
        while(true){
            ReqAvai.clear();
            for(int a=0; a<reques.size();a++){
                reques.get(a).SetEarliest(time);
            }
            for(RequestDynamic req: reques){
                if(req.executeable&&req.Earliest>0){
                    ReqAvai.add(req);
                }
            }
            if(ReqAvai.size()==0)
                break;
            Collections.sort(ReqAvai);
            while(headPos!=ReqAvai.get(0).Position){
                if(ReqAvai.get(0).Position>headPos){
                    headPos++;
                    track++;}
                else {
                    headPos--;
                    track++;
                }

                for(int i = 1; i<ReqAvai.size(); i++){
                    if(ReqAvai.get(i).Position==headPos){
                        changePos(ReqAvai.get(i).Position);
                        ReqAvai.get(i).execute();
                        Executed.add(ReqAvai.get(i));

                    }
                }
            }
            time += ReqAvai.get(0).time;
            changePos(ReqAvai.get(0).Position);
            Executed.add(ReqAvai.get(0));
            ReqAvai.get(0).execute();

        }
        System.out.println("\n For FD-SCAN algorithm track of disk head equals " + track + "\n");

        System.out.println("Processes executed:  ");
        for(int i = 0; i<reques.size(); i++){
            if(Executed.contains(reques.get(i))){
                System.out.print("P"+ i + " ");
            }
        }
        if(reques.size()>Executed.size()){
            System.out.println("\nFollwing processes couldnt meet deadline: ");
            for(int i = 0; i<reques.size(); i++){
                if(!Executed.contains(reques.get(i))){
                    System.out.print("P"+ i + " ");
                }
            }
            System.out.println("\n");



        }
        setHeadPos(80);
        clearTracker();

    }
    public void loadList(ArrayList<Request> res){
        for(int i =0; i<res.size(); i++){
            res.get(i).executeable = true;
        }
    }
    public void loadListD(ArrayList<RequestDynamic> res){
        for(int i =0; i<res.size(); i++){
            res.get(i).executeable = true;
        }
    }

    public void generate(ArrayList<Request> requestArrayList, ArrayList<RequestDynamic> requestDynamics){
        for(int a = 1; a<100; a++){
            double rand = Math.random();

            requestArrayList.add((new Request((int) (rand*200)+1 )));
            requestDynamics.add((new RequestDynamic((int)(rand*200)+1, (int)(rand*4)+1, 2*a)));
        }
    }

    public void generateD(ArrayList<RequestDynamic> requestDynamicArrayList){
        for(int a = 1; a<21; a++) {
            requestDynamicArrayList.add(new RequestDynamic(2*a, 3, 4+2*a));
            requestDynamicArrayList.add(new RequestDynamic(2*a+2, 3, 8+2*a));
            requestDynamicArrayList.add(new RequestDynamic(2*a+4, 5, 20+2*a));
            requestDynamicArrayList.add(new RequestDynamic(2*a+6, 4, 4+2*a));
            requestDynamicArrayList.add(new RequestDynamic(2*a+8, 4, 11+2*a));
        }
    }
    public Disk(int a){

        ArrayList<Request> requestArrayList = new ArrayList<>();
        ArrayList<RequestDynamic> requestDynamicArrayList = new ArrayList<>();

        generate(requestArrayList, requestDynamicArrayList);



        switch (a) {
            case 1: {
                fcfo(requestArrayList);loadList(requestArrayList);
                sstf(requestArrayList);loadList(requestArrayList);
                scan(requestArrayList,80);loadList(requestArrayList);
                cscan(requestArrayList,80, 200);loadList(requestArrayList);
                break;
            }
            case 2: {
                edf(requestDynamicArrayList); loadListD(requestDynamicArrayList);
                break;
            }
            case 3:{
                fdscan(requestDynamicArrayList);
                loadListD(requestDynamicArrayList);
                break;
            }
        }
    }



}

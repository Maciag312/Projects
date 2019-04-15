package com.company;

public class Request {
    public boolean executeable;
    public int Position;
    public Request( int position){
        this.executeable = true;
        this.Position = position;
    }
    public void load(int position){
        this.executeable = true;
        this.Position = position;
    }
    public boolean execute(){
        if(executeable!=false){
            executeable = false;
            return true;
        }
        return false;
    }
}

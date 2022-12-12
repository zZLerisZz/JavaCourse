package ru.croc.Task15;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private int botLine;
    private int upLine;
    public static final int MAX_AGE = 123;
    List<Human> resp;
    public Group(int parbline, int paruline){
        resp = new ArrayList<>();
        botLine = parbline;
        upLine = paruline;
    }
    public void fillResp(List<Human> data){
        for(var it : data){
            if(it.getAge() <= upLine && it.getAge() >= botLine)
                resp.add(it);
        }
        resp.sort(Human::compareTo);
    }
    @Override
    public String toString(){
        if(!resp.isEmpty()){
            String msg;
            if(upLine == MAX_AGE)
                msg = botLine + "+: ";
            else
                msg = botLine + "-" + upLine + ": ";
            for(var it : resp){
                msg += it.toString() + ", ";
            }
            return msg.substring(0, msg.lastIndexOf(",")) + "\n";
        }
        return "";
    }
}

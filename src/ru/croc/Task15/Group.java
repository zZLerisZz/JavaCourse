package ru.croc.Task15;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private int bline;
    private int uline;
    public static final int MAX_AGE = 123;
    List<Human> resp;
    public Group(int _bline, int _uline){
        resp = new ArrayList<>();
        bline = _bline;
        uline = _uline;
    }
    public void fillResp(List<Human> data){
        for(var it : data){
            if(it.getAge() <= uline && it.getAge() >= bline)
                resp.add(it);
        }
        resp.sort(Human::compareTo);
    }
    @Override
    public String toString(){
        if(!resp.isEmpty()){
            String msg;
            if(uline == MAX_AGE)
                msg = bline + "+: ";
            else
                msg = bline + "-" + uline + ": ";
            for(var it : resp){
                msg += it.toString() + ", ";
            }
            return msg.substring(0, msg.lastIndexOf(",")) + "\n";
        }
        return "";
    }
}

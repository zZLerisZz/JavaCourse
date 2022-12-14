package ru.croc.Task16;

import java.util.List;

public class Client {
    private final double xcord;
    private final double ycord;
    private final String type;
    private final List<String> wlist;
    public Client(double x, double y, String _type, List<String> wishes){
        this.xcord = x;
        this.ycord = y;
        this.type = _type;
        this.wlist = wishes;
    }
    public List<String> getWlist(){
        return wlist;
    }
    public double getXcord(){
        return xcord;
    }
    public double getYcord() {
        return ycord;
    }
    @Override
    public String toString(){
        return "[" + xcord + "; " + ycord + "]-" + type + "-" + wlist;
    }
    public String getType(){
        return type;
    }
}

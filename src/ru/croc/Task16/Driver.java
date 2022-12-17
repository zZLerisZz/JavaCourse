package ru.croc.Task16;

import java.util.Comparator;
import java.util.List;

public class Driver {
    private final String name;
    private final double xcord;
    private final double ycord;
    private final Car c;
    public Driver(String _name, double x, double y, Car _c){
        this.name = _name;
        this.xcord = x;
        this.ycord = y;
        this.c = _c;
    }
    @Override
    public String toString(){
        return name + ", [" + xcord + "; " + ycord + "]: " + c;
    }
    public List<String> getFacilities(){
        return c.getFacilities();
    }
    public double getXcord(){
        return xcord;
    }
    public double getYcord() {
        return ycord;
    }
    public String getType(){
        return c.getType();
    }
    public int getNum(){
        return c.getNum();
    }
    public static Comparator<Driver> driverComparator = (d1, d2) -> {
        if (d1.getNum() == d2.getNum()) {
            return 0;
        } else if (d1.getNum() > d2.getNum()) {
            return 1;
        } else if (d1.getNum() < d2.getNum()) {
            return -1;
        }
        return 0;
    };
}

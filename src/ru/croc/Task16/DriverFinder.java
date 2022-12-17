package ru.croc.Task16;

import java.util.List;

public class DriverFinder {
    public Driver findDriver(List<Driver> drivers, Client cl){
        Driver d = null;
        double ras = -1;
        boolean fl;
        for (var it : drivers){
            fl = true;
            for(var item : cl.getWlist()){
                if(!it.getFacilities().contains(item)) {
                    fl = false;
                    break;
                }
            }
            if(fl && it.getType().equals(cl.getType()) && (ras < 0 || findDistance(it, cl) < ras)){
                ras = findDistance(it, cl);
                d = it;
            }
        }
        return d;
    }
    private double findDistance(Driver d, Client cl){
        return Math.sqrt(Math.pow(d.getXcord() - cl.getXcord(), 2) + Math.pow(d.getYcord() - cl.getYcord(), 2));
    }
}

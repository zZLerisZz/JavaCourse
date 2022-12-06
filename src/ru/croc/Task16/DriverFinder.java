package ru.croc.Task16;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DriverFinder {
    List<Driver> drivers;
    Client cl;
    public DriverFinder(String fpath, Client _cl) throws Exception{
        cl = _cl;
        drivers = new ArrayList<>();
        Scanner scan = null;
        FileReader fl = null;
        fl = new FileReader(fpath);
        scan = new Scanner(fl);
        List<String> str = new ArrayList<>();
        while (scan.hasNextLine())
            str.add(scan.nextLine());
        String[] nstr = str.toArray(new String[str.size()]);
        for(int i = 0; i < str.size(); i++){
            String[] cstr = nstr[i].split(";");
            drivers.add(new Driver(cstr[0], Double.parseDouble(cstr[1]), Double.parseDouble(cstr[2]),
                    new Car(Integer.parseInt(cstr[3]), cstr[4], cstr[5],
                            new ArrayList<>(Arrays.asList(Arrays.copyOfRange(cstr, 6, cstr.length))))));
        }
        drivers.sort(Driver.driverComparator);
        scan.close();
        fl.close();
    }
    public void findDriver(){
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
            if(fl && it.getType().equals(cl.getType()) && (ras < 0 || findDistance(it) < ras)){
                ras = findDistance(it);
                d = it;
            }
        }
        if(d == null)
            System.out.println("Подходящий таксист не найден.");
        else
            System.out.println("Вам подходит: " + d);
    }
    private double findDistance(Driver d){
        return Math.sqrt(Math.pow(d.getXcord() - cl.getXcord(), 2) + Math.pow(d.getYcord() - cl.getYcord(), 2));
    }
}

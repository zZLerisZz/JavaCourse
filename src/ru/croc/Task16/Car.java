package ru.croc.Task16;

import java.util.List;

public class Car {
    private final int num;
    private final String model;
    private final String type;
    private final List<String> facilities;
    public Car(int _num, String _model, String _type, List<String> _fac){
        this.num = _num;
        this.model = _model;
        this.type = _type;
        this.facilities = _fac;
    }
    @Override
    public String toString(){
        return model + "-" + type + "-" + num + "-" + facilities;
    }

    public List<String> getFacilities(){
        return facilities;
    }
    public String getType(){
        return type;
    }
    public int getNum(){
        return num;
    }
}

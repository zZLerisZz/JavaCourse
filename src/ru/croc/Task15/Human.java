package ru.croc.Task15;

public class Human implements Comparable<Human> {
    private String nameData;//ФИО
    private int age;
    public Human(String data){
        this.age = Integer.parseInt(data.substring(data.indexOf(",") + 1));
        this.nameData = data.substring(0, data.indexOf(","));
    }
    @Override
    public String toString(){
        return nameData + " (" + age + ")";
    }
    @Override
    public int compareTo(Human h){
        if(this.age == h.age)
            return this.nameData.toLowerCase().compareTo(h.nameData.toLowerCase());
        return h.age - this.age;
    }
    public int getAge(){
        return age;
    }
}

package ru.croc.Task15;

public class Human implements Comparable<Human> {
    private String snp;//ФИО
    private int age;
    public Human(String data){
        this.age = Integer.parseInt(data.substring(data.indexOf(",") + 1));
        this.snp = data.substring(0, data.indexOf(","));
    }
    @Override
    public String toString(){
        return snp + " (" + age + ")";
    }
    @Override
    public int compareTo(Human h){
        if(this.age == h.age)
            return this.snp.toLowerCase().compareTo(h.snp.toLowerCase());
        return h.age - this.age;
    }
    public int getAge(){
        return age;
    }
}

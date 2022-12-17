package ru.croc.Task16;

public class MyExceptions {
    public static class NoSuchFacility extends Exception{
        public NoSuchFacility(){
            super("Условия заполнены неверно.");
        }
    }
    public static class NoSuchComfortClass extends Exception{
        public NoSuchComfortClass(){
            super("Класс комфорта заполнен неверно");
        }
    }
}

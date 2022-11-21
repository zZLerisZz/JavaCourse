package ru.croc.Task10;

import java.util.Calendar;

public class Product implements Runnable{
    private String pname, wname;
    private double cprice;
    private Calendar ts, te;

    public Product(String it, Calendar st, Calendar end){
        pname = it;
        ts = st;
        te = end;
        cprice = 0;
        wname = "";
    }

    private void makeBet(String name, double price){
        wname = name;
        cprice = price;
    }

    public boolean stillGoing(){
        return Calendar.getInstance().getTimeInMillis() >= ts.getTimeInMillis()
                && Calendar.getInstance().getTimeInMillis() <= te.getTimeInMillis();
    }

    public void run(){
        synchronized (this){
            int menu = 0;
            while (stillGoing() && menu != 2){
                System.out.println("Наименование товара: " + pname);
                System.out.println("Текущая цена за лот: " + cprice);
                System.out.println("Текущий победитель: " + wname);
                System.out.println("1.Повысить ставку");
                System.out.println("2.Продолжить наблюдать");
                do {
                    menu = Task10.checkInt("Ваш выбор", Task10.scanner);
                }while(menu < 1 || menu > 2);
                if(menu == 1){
                    String nm = "";
                    do{
                        System.out.print("Введите своё имя: ");
                        nm = Task10.scanner.nextLine();
                    }while (nm.equals(""));
                    double pr = Task10.checkDouble("Ваша цена за лот", Task10.scanner);
                    if(pr > cprice)
                        makeBet(nm, pr);
                    menu = 2;
                }
            }
            if(!stillGoing())
                System.out.println("Лот продан по цене - " + cprice + ", победитель - " + wname);
        }
    }
}

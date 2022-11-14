package ru.croc.Task8;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Task8 {
    public static void main(String[] args) {
        double cost;
        Scanner sc = new Scanner(System.in);
        NumberFormat formNum = NumberFormat.getCurrencyInstance(Locale.CHINA);
        do{
            try {
                System.out.print("Введите вещественное число: ");
                cost = sc.nextDouble();

            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
                cost = -1;
            }
        }while(cost < 0);
        int menu = 0;
        System.out.println("1.Корея");
        System.out.println("2.Китай");
        System.out.println("3.Япония");
        boolean fl = false;
        do{
            System.out.print("Введите ваш выбор: ");
            if(!sc.hasNextInt()){
                System.out.println("Введено неверное значение.");
                sc.nextLine();
            }
            else{
                fl = true;
                menu = sc.nextInt();
            }
        }while (!fl || menu < 1 || menu > 3);
        switch (menu){
            case 1:
                formNum = NumberFormat.getCurrencyInstance(Locale.KOREA);
                break;
            case 2:
                formNum= NumberFormat.getCurrencyInstance(Locale.CHINA);
                break;
            case 3:
                formNum = NumberFormat.getCurrencyInstance(Locale.JAPAN);
                break;
        }
        System.out.println("Результат: " + formNum.format(cost));
        sc.close();
    }
}

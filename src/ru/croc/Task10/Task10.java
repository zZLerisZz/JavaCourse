package ru.croc.Task10;

import java.util.Calendar;
import java.util.Scanner;

public class Task10 {
    public static Scanner scanner = new Scanner(System.in);

    static int checkInt(String str, Scanner sc){
        int data = 0;
        boolean fl = false;
        do{
            System.out.print(str + ": ");
            fl = sc.hasNextInt();
            if (fl) {
                data = sc.nextInt();
                sc.nextLine();
            }
            else {
                System.out.println("Введено неправильное значение.");
                sc.nextLine();
            }
        }while(!fl);
        return data;
    }

    static double checkDouble(String str, Scanner sc){
        double data = 0;
        boolean fl = false;
        do{
            System.out.print(str + ": ");
            fl = sc.hasNextInt();
            if (fl) {
                data = sc.nextDouble();
                sc.nextLine();
            }
            else {
                System.out.println("Введено неправильное значение.");
                sc.nextLine();
            }
        }while(!fl);
        return data;
    }

    public static void main(String[] args) throws InterruptedException {
        int menu;
        do{
            System.out.println("1.Создать аукцион");
            System.out.println("2.Выход");
            do{
                menu = checkInt("Ваш выбор", scanner);
            }while (menu < 1 || menu > 2);
            if(menu == 1){
                String prn = "";
                Calendar cs, ce;
                int cb, sec;
                Product pr = null;
                Thread[] trs = null;
                do {
                    System.out.print("Введите наименование товара: ");
                    prn = scanner.nextLine();
                }while (prn.equals(""));
                do {
                    cb = checkInt("Количество покупателей", scanner);
                }while (cb <= 0);
                trs = new Thread[cb];
                do {
                    sec = checkInt("Длительность аукциона в секундах", scanner);
                }while (sec <= 0);
                cs = Calendar.getInstance(); ce = Calendar.getInstance();
                ce.add(Calendar.SECOND, sec);
                pr = new Product(prn, cs, ce);
                for (int i = 0; i < cb; i++){
                    trs[i] = new Thread(pr);
                    trs[i].start();
                }
                System.out.println("ТОРГИ НАЧАЛИСЬ");
                for (int i = 0; i < cb; i++)
                    trs[i].join();
                System.out.println("КОНЕЦ ТОРГОВ");
                System.out.println("Победитель - " + pr.getWinner());
                for (int i = 0; i < cb; i++)
                    trs[i].interrupt();
            }
        }while (menu != 2);
    }
}

package ru.croc.Task2;
import java.util.Scanner;

public class Task2 {
    public static double ArSum(double a1, double d, int n){//функция для вычисления суммы, ничего интересного - просто цикл
        double s = 0;
        for(int i = 0; i < n; i++)
            s += a1 + d * i;
        return s;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);//создание сканера
        boolean fl = false;//флажок для проверки при вводе, может и не понадобиться
        double a = 0, d = 0;
        int n = 0;
        if(args.length == 3){ //если в программу введены аргументы, тогда просто их парсим
            a = Double.parseDouble(args[0]);
            d = Double.parseDouble(args[1]);
            n = Integer.parseInt(args[2]);
        }
        else {//считывание нужных данных с проверкой ввода
            do {
                System.out.print("Введите первый член прогрессии: ");
                fl = in.hasNextDouble();//проверка, что там то, что нам надо...
                if (fl)
                    a = in.nextDouble();//берем данные из сканера
                else {
                    System.out.println("Введено неправильное значение.");
                    in.nextLine();//перевод сканера на новую строку, как я понял отчистить поток ввода нельзя, так что так
                }
            } while (!fl);
            do {
                System.out.print("Введите шаг прогрессии: ");
                fl = in.hasNextDouble();
                if (fl)
                    d = in.nextDouble();
                else {
                    System.out.println("Введено неправильное значение.");
                    in.nextLine();
                }
            } while (!fl);
            do {
                System.out.print("Введите количество членов прогрессии: ");
                fl = in.hasNextInt();
                if (fl)
                    n = in.nextInt();
                else {
                    System.out.println("Введено неправильное значение.");
                    in.nextLine();
                }
            } while (!fl);
        }
        double sum = ArSum(a, d, n);//вызываем функцию для подсчёта результата
        System.out.println("Sum: " + sum);
        in.close();//закрываем сканер... А он автоматически коллектором не закрывается?
    }
}

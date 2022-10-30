package ru.croc.Task3;
import java.util.Scanner;


public class Task3 {
    public static class Point{//класс для координат точек
        double x, y;
    }

    public static class Triangle{
        private Point a = new Point(), b = new Point(), c = new Point();//их можно было сделать открытыми, но дурацкая привычка...
        public void InputPoints(double ax, double ay, double bx, double by, double cx, double cy){
            a.x = ax;
            a.y = ay;
            b.x = bx;
            b.y = by;
            c.x = cx;
            c.y = cy;
        }
        public double GetSq(){
            double ab = Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));//вычисление трёх сторон треугольника через длины векторов
            double ac = Math.sqrt((a.x -c.x) * (a.x - c.x) + (a.y - c.y) * (a.y - c.y));
            double bc = Math.sqrt((c.x - b.x) * (c.x - b.x) + (c.y - b.y) * (c.y - b.y));
            double p = (ab + ac + bc) / 2;//получение полупериметра
            return Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));//получение самой площади
        }
    }

    public static void main(String[] args) {
        boolean fl = false;
        Scanner in = new Scanner(System.in);
        Triangle tr = new Triangle();
        if(args.length == 6){//получаем координаты из аргументов или вводим их ручками
            double _ax = Double.parseDouble(args[0]);
            double _ay = Double.parseDouble(args[1]);
            double _bx = Double.parseDouble(args[2]);
            double _by = Double.parseDouble(args[3]);
            double _cx = Double.parseDouble(args[4]);
            double _cy = Double.parseDouble(args[5]);
            tr.InputPoints(_ax, _ay, _bx, _by, _cx, _cy);
        }
        else{
            double _ax = 0, _ay = 0, _bx = 0, _by = 0, _cx = 0, _cy = 0;
            do {
                System.out.print("Введите A(x): ");
                fl = in.hasNextDouble();//проверка, что там то, что нам надо...
                if (fl)
                    _ax = in.nextDouble();//берем данные из сканера
                else {
                    System.out.println("Введено неправильное значение.");
                    in.nextLine();//перевод сканера на новую строку, как я понял отчистить поток ввода нельзя, так что так
                }
            } while (!fl);
            do {
                System.out.print("Введите A(y): ");
                fl = in.hasNextDouble();
                if (fl)
                    _ay = in.nextDouble();
                else {
                    System.out.println("Введено неправильное значение.");
                    in.nextLine();
                }
            } while (!fl);
            do {
                System.out.print("Введите B(x): ");
                fl = in.hasNextDouble();
                if (fl)
                    _bx = in.nextDouble();
                else {
                    System.out.println("Введено неправильное значение.");
                    in.nextLine();
                }
            } while (!fl);
            do {
                System.out.print("Введите B(y): ");
                fl = in.hasNextDouble();
                if (fl)
                    _by = in.nextDouble();
                else {
                    System.out.println("Введено неправильное значение.");
                    in.nextLine();
                }
            } while (!fl);
            do {
                System.out.print("Введите C(x): ");
                fl = in.hasNextDouble();
                if (fl)
                    _cx = in.nextDouble();
                else {
                    System.out.println("Введено неправильное значение.");
                    in.nextLine();
                }
            } while (!fl);
            do {
                System.out.print("Введите C(y): ");
                fl = in.hasNextDouble();
                if (fl)
                    _cy = in.nextDouble();
                else {
                    System.out.println("Введено неправильное значение.");
                    in.nextLine();
                }
            } while (!fl);
            tr.InputPoints(_ax, _ay, _bx, _by, _cx, _cy);
        }
        double sq = tr.GetSq();
        System.out.printf("Площадь треугольника: %.1f", sq);
        in.close();
    }
}

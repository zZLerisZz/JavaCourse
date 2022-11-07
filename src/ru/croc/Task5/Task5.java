package ru.croc.Task5;

import java.util.Scanner;

public class Task5 {
    public static Scanner s = new Scanner(System.in);
    static double CheckDouble(String str){
        double data = 0;
        boolean fl = false;
        do {
            System.out.print(str + ": ");
            fl = s.hasNextDouble();
            if (fl)
                data = s.nextDouble();
            else {
                System.out.println("Введено неправильное значение.");
                s.nextLine();
            }
        }while(!fl);
        return data;
    }
    static int CheckInt(String str){
        int data = 0;
        boolean fl = false;
        do{
            System.out.print(str + ": ");
            fl = s.hasNextInt();
            if (fl)
                data = s.nextInt();
            else {
                System.out.println("Введено неправильное значение.");
                s.nextLine();
            }
        }while(!fl);
        return data;
    }
    public interface Movable {
        void move(int dx, int dy);
    }
    public abstract static class Figure implements Movable{
        protected double x, y;//стандартные корды
        public abstract boolean PointIn(double _x, double _y);
    }

    public static class Rectangle extends Figure{
        private double x1, y1;//корды правого верхнего угла
        public Rectangle(double...cords){
            super.x = cords[0];
            super.y = cords[1];
            this.x1 = cords[2];
            this.y1 = cords[3];
        }
        @Override
        public String toString(){
            return "Rectangle "+ x+","+y+"), "+"("+ x1+","+ y1+"): ";
        }
        public void move(int dx, int dy){
            super.x += dx;
            super.y += dy;
            this.x1 += dx;
            this.y1 += dy;
        }
        public boolean PointIn(double _x, double _y){
            return(_x >= super.x && _x <= x1 && _y >= super.y && _y <= y1);
        }
    }

    public static class Circle extends Figure{
        private double r;
        public Circle(double...cords){
            super.x = cords[0];
            super.y = cords[1];
            this.r = cords[2];
        }
        @Override
        public String toString()
        {
            return "Circle ("+x+","+y+"), "+r+": ";
        }
        public void move(int dx, int dy){
            super.x += dx;
            super.y += dy;
        }
        public boolean PointIn(double x, double y) {
            return (x - super.x) * (x - super.x) + (y - super.y) * (y - super.y) <= r * r;
        }
    }
    public static class Annotation{
        private Figure figure;
        private String sign;
        private Annotation(Figure f, String _sign){
            figure = f;
            sign = _sign;
        }
        public static Annotation createAnn(int idF, String s, double...cords){
            Figure tempF;
            if(idF == 1){
                if(cords.length != 4) {
                    System.out.println("Координаты прямоугольника неверно введены.");
                    return null;
                }
                if(cords[0] >= cords[2] ||cords[1] >= cords[3]){
                    System.out.println("Координаты прямоугольника неверно введены.");
                    return null;
                }
                tempF = new Rectangle(cords);
            }
            else if(idF == 2){
                if(cords.length != 3) {
                    System.out.println("Координаты круга неверно введены.");
                    return null;
                }
                if(cords[2] <= 0){
                    System.out.println("Координаты круга неверно введены.");
                    return null;
                }
                tempF = new Circle(cords);
            }
            else{
                System.out.println("Выбрана неверная фигура.");
                return null;
            }
            return new Annotation(tempF, s);
        }
        @Override
        public String toString(){
            return figure.toString() + sign;
        }

        public void Move(int _dx, int _dy){
            figure.move(_dx, _dy);
        }

        public boolean findPoint(double x, double y) {
            return figure.PointIn(x,y);
        }

        public String GetSign(){
            return sign;
        }
    }
    static class AnnotatedImage {

        private final String imagePath;

        private final Annotation[] annotations;



        public AnnotatedImage(String imagePath,
                              Annotation... annotations) {
            this.imagePath = imagePath;
            this.annotations = annotations;
        }

        public String getImagePath() {
            return this.imagePath;
        }

        public Annotation[] getAnnotations() {
            return this.annotations;
        }

        public Annotation findByPoint(int x, int y) {
            for(Annotation annotation: annotations) {
                if(annotation != null && annotation.findPoint(x,y)) {
                    return annotation;
                }
            }
            return null;
        }
        public Annotation findByLabel(String label) {
            for(Annotation annotation: annotations) {
                if(annotation != null && annotation.GetSign().contains(label)) {
                    return annotation;
                }
            }
            return null;
        }

    }
    public static void main(String[] args) {
        double[] RCords = new double[4];
        double[] CCords = new double[3];
        int colA, top = 0;
        colA = CheckInt("Количество аннотаций");
        Annotation[] ArAn = new Annotation[colA];
        int menu = 0;
        do{
            System.out.println("1.Добавить фигуру с аннотацией.");
            System.out.println("2.Вывести данные о фигуре с аннотацией");
            System.out.println("3.Переместить фигуру");
            System.out.println("4.Перейти к тестированию дополнений");
            do {
                menu = CheckInt("Ваш выбор");
            }while(menu < 1 || menu > 4);
            switch (menu){
                case 1:{
                    if(top == colA)
                        System.out.println("Массив заполнен.");
                    else{
                        System.out.println("1 - прямоугольник, 2 - круг");
                        int id;
                        do {
                            id = CheckInt("Ваш выбор");
                        }while(id < 1 || id > 2);
                        if(id == 1){
                            RCords[0] = CheckDouble("X левого угла");
                            RCords[1] = CheckDouble("Y левого угла");
                            RCords[2] = CheckDouble("X правого угла");
                            RCords[3] = CheckDouble("Y правого угла");
                            System.out.print("Аннотация: ");
                            s.nextLine();
                            String st = s.nextLine();
                            ArAn[top++] = Annotation.createAnn(id, st, RCords);
                        }
                        else{
                            CCords[0] = CheckDouble("X центра");
                            CCords[1] = CheckDouble("Y центра");
                            CCords[2] = CheckDouble("Радиус");
                            System.out.print("Аннотация: ");
                            s.nextLine();
                            String st = s.nextLine();
                            ArAn[top++] = Annotation.createAnn(id, st, CCords);
                        }
                    }
                }break;
                case 2:{
                    if(top == 0)
                        System.out.println("Массив пуст.");
                    else{
                        int ind = CheckInt("Индекс интересующего элемента");
                        if(ind >= top){
                            System.out.println("Индекс выходит за заполненный массив.");
                        }
                        else
                            System.out.println(ArAn[ind]);
                    }
                }break;
                case 3:{
                    if(top == 0)
                        System.out.println("Массив пуст.");
                    else{
                        int ind = CheckInt("Индекс интересующего элемента");
                        if(ind >= top){
                            System.out.println("Индекс выходит за заполненный массив.");
                        }
                        else{
                            int vx = CheckInt("Скорость по оси X");
                            int vy = CheckInt("Скорость по оси Y");
                            ArAn[ind].Move(vx, vy);
                        }
                    }
                }break;
            }
        }while (menu != 4);
        if(top == 0)
            System.out.println("Массив не был заполнен.");
        else {
            System.out.print("Введите путь до картинки: ");
            s.nextLine();
            String impass = s.nextLine();
            AnnotatedImage animg = new AnnotatedImage(impass, ArAn);
            do{
                System.out.println("1.Найти фигуру по точке.");
                System.out.println("2.Найти фигуру по аннотации.");
                System.out.println("3.Выход");
                do {
                    menu = CheckInt("Ваш выбор");
                }while(menu < 1 || menu > 3);
                switch (menu){
                    case 1:{
                        int xp = CheckInt("Координата X точки");
                        int yp = CheckInt("Координата Y точки");
                        Annotation tAn = animg.findByPoint(xp, yp);
                        if(tAn == null)
                            System.out.println("Нет подходящей фигуры.");
                        else
                            System.out.println(tAn);
                    }break;
                    case 2:{
                        System.out.print("Аннотация для поиска: ");
                        s.nextLine();
                        String sp = s.nextLine();
                        Annotation tAn = animg.findByLabel(sp);
                        if(tAn == null)
                            System.out.println("Нет подходящей фигуры.");
                        else
                            System.out.println(tAn);
                    }break;
                }
            }while(menu != 3);
        }
        s.close();
    }
}

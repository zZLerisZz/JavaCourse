package ru.croc.Task5;

import java.util.Scanner;

public class Task5 {
    public static Scanner s = new Scanner(System.in);
    static double checkDouble(String str){
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
    static int checkInt(String str){
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
        public abstract boolean pointIn(double _x, double _y);
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
            return "Rectangle ("+ x+","+y+"), "+"("+ x1+","+ y1+"): ";
        }
        public void move(int dx, int dy){
            super.x += dx;
            super.y += dy;
            this.x1 += dx;
            this.y1 += dy;
        }
        public boolean pointIn(double _x, double _y){
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
        public boolean pointIn(double x, double y) {
            return (x - super.x) * (x - super.x) + (y - super.y) * (y - super.y) <= r * r;
        }
    }
    public static class Annotation{
        private Figure figure;
        private String sign;
        public Annotation(Figure f, String _sign){
            figure = f;
            sign = _sign;
        }

        @Override
        public String toString(){
            return figure.toString() + sign;
        }

        public void move(int _dx, int _dy){
            figure.move(_dx, _dy);
        }

        public boolean findPoint(double x, double y) {
            return figure.pointIn(x,y);
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
        colA = checkInt("Количество аннотаций");
        Annotation[] ArAn = new Annotation[colA];
        int menu = 0;
        do{
            System.out.println("1.Добавить фигуру с аннотацией.");
            System.out.println("2.Вывести данные о фигуре с аннотацией");
            System.out.println("3.Переместить фигуру");
            System.out.println("4.Перейти к тестированию дополнений");
            do {
                menu = checkInt("Ваш выбор");
            }while(menu < 1 || menu > 4);
            switch (menu){
                case 1:{
                    if(top == colA)
                        System.out.println("Массив заполнен.");
                    else{
                        System.out.println("1 - прямоугольник, 2 - круг");
                        int id;
                        do {
                            id = checkInt("Ваш выбор");
                        }while(id < 1 || id > 2);
                        if(id == 1){
                            RCords[0] = checkDouble("X левого угла");
                            RCords[1] = checkDouble("Y левого угла");
                            RCords[2] = checkDouble("X правого угла");
                            RCords[3] = checkDouble("Y правого угла");
                            System.out.print("Аннотация: ");
                            s.nextLine();
                            String st = s.nextLine();
                            ArAn[top++] = new Annotation(new Rectangle(RCords), st);
                        }
                        else{
                            CCords[0] = checkDouble("X центра");
                            CCords[1] = checkDouble("Y центра");
                            CCords[2] = checkDouble("Радиус");
                            System.out.print("Аннотация: ");
                            s.nextLine();
                            String st = s.nextLine();
                            ArAn[top++] = new Annotation(new Circle(CCords), st);
                        }
                    }
                }break;
                case 2:{
                    if(top == 0)
                        System.out.println("Массив пуст.");
                    else{
                        int ind = checkInt("Индекс интересующего элемента");
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
                        int ind = checkInt("Индекс интересующего элемента");
                        if(ind >= top){
                            System.out.println("Индекс выходит за заполненный массив.");
                        }
                        else{
                            int vx = checkInt("Скорость по оси X");
                            int vy = checkInt("Скорость по оси Y");
                            ArAn[ind].move(vx, vy);
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
                    menu = checkInt("Ваш выбор");
                }while(menu < 1 || menu > 3);
                switch (menu){
                    case 1:{
                        int xp = checkInt("Координата X точки");
                        int yp = checkInt("Координата Y точки");
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

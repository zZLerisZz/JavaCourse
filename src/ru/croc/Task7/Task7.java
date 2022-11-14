package ru.croc.Task7;

import java.util.Scanner;

import static java.lang.Math.abs;

public class Task7 {
    public static Scanner sc = new Scanner(System.in);
    static int checkInt(String str){
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
    public static int top = 0;
    public static class IllegalPositionException extends Exception {
        public IllegalPositionException(String message)
        {
            super(message);
        }
    }
    public static class IllegalMoveException extends Exception {
        public IllegalMoveException(Chess prevPos,Chess curPos)
        {
            super("Конь так не ходит: " + prevPos + " -> "+ curPos);
        }
    }
    public static class Chess extends Exception{
        private int x,y;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public static Chess createChess(String pos) throws IllegalPositionException {
            if (pos.length() < 2) {
                throw new IllegalPositionException("Создана невозможная позиция: " + pos);
            }
            int x = pos.charAt(0) - 'a';
            int y = pos.charAt(1) - '0' - 1;
            if (x < 0 || y < 0 || x > 7 || y > 7) {
                IllegalPositionException e = new IllegalPositionException("Создана невозможная позиция: " + pos);
                System.out.println(e.getMessage());
                return null;

            }
            return new Chess(x,y);
        }

        private Chess(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString()
        {
            char x = (char)('a' + this.x);
            String pos = "" + x + (1 + this.y);
            return pos;
        }
    }
    public static boolean checkKnight(Chess[] arr) throws IllegalMoveException
    {
        if(top <= 1) {
            System.out.println("Недостаточно заполненных полей.");
            return false;
        }
        Chess prevPos = arr[0];
        for(int i = 1; i < top; i++)
        {
            if(!((abs(arr[i].getY() - prevPos.getY()) == 2 && abs(arr[i].getX() - prevPos.getX()) == 1) ||
                    (abs(arr[i].getX() - prevPos.getX()) == 2 && abs(arr[i].getY() - prevPos.getY()) == 1))) {
                throw new IllegalMoveException(prevPos, arr[i]);
            }
            prevPos = arr[i];
        }
        return true;
    }
    public static void printArr(Chess[] arr)
    {
        if(top == 0){
            System.out.println("Недостаточно заполненных полей.");
            return;
        }
        for(int i = 0; i < top; i++)
            System.out.println(arr[i]);
    }
    public static void main(String[] args){
        Chess[] arrCh = new Chess[64];
        String pos = "";
        int menu = 0;
        if(args != null) {
            for(int i = 0; i < args.length; i++)
            {
                try {
                    arrCh[top++] = Chess.createChess(args[i]);
                } catch (IllegalPositionException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        do{
            System.out.println("1.Добавить путевое поле");
            System.out.println("2.Проверить путь коня");
            System.out.println("3.Вывести введенные поля");
            System.out.println("4.Удалить последнее поле");
            System.out.println("5.Выход");
            do{
                menu = checkInt("Ваш выбор");
            }while (menu < 1 || menu > 5);
            switch (menu) {
                case 1: {
                    System.out.print("Введите поле для заполнения: ");
                    try {
                        if (!((arrCh[top] = Chess.createChess(sc.nextLine())) == null)) {
                            top++;
                        }
                    } catch (IllegalPositionException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 2: {
                    try {
                        if(checkKnight(arrCh))
                            System.out.println("OK");
                    } catch (IllegalMoveException e) {
                        System.out.println(e.getMessage());
                    }
                }break;
                case 3:
                    printArr(arrCh);
                    break;
                case 4:{
                    if(top != 0)
                        top -= 1;
                    else {
                        System.out.println("Нет заполненных полей.");
                    }
                }break;
            }
        }while(menu != 5);
    }
}

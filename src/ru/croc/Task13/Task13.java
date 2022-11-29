package ru.croc.Task13;

import java.util.Scanner;

public class Task13 {
    public static void main(String[] args) throws Exception {
        Recommendations r = new Recommendations();
        Scanner scanner = new Scanner(System.in);
        String fpath = "";
        System.out.print("Введите имя файла со списком фильмов: ");
        fpath = scanner.nextLine();
        r.fillFilms(fpath);
        System.out.print("Введите имя файла с историей просмотров: ");
        fpath = scanner.nextLine();
        r.fillHistory(fpath);
        System.out.print("Введите номера просмотренных вами фильмов через запятую: ");
        fpath = scanner.nextLine();
        r.getRecommendation(fpath);
    }
}

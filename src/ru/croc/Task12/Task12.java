package ru.croc.Task12;

import java.util.*;

public class Task12 {
    public static void main(String[] args) throws Exception {
        Comments c = new Comments();
        Scanner scanner = new Scanner(System.in);
        String fpath = "";
        System.out.print("Введите имя файла с комментариями: ");
        fpath = scanner.nextLine();
        c.fillComments(fpath);
        System.out.print("Введите имя файла с черным списком: ");
        fpath = scanner.nextLine();
        c.fillBlackList(fpath);
        System.out.print("Введите имя файла для записи результатов: ");
        fpath = scanner.nextLine();
        c.filter();
        c.writeResults(fpath);
        scanner.close();
    }
}

package ru.croc.Task14;

import java.util.Scanner;

public class Task14 {
    public static void main(String[] args) throws Exception {
        Comments c = new Comments();
        Scanner scanner = new Scanner(System.in);
        String filePath = "";
        System.out.print("Введите имя файла с комментариями: ");
        filePath = scanner.nextLine();
        c.fillComments(filePath);
        System.out.print("Введите имя файла с черным списком: ");
        filePath = scanner.nextLine();
        c.fillFilter(filePath);
        System.out.print("Введите имя файла для записи результатов: ");
        filePath = scanner.nextLine();
        c.createCensoredComments();
        c.writeResults(filePath);
        scanner.close();
    }
}

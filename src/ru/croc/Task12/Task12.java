package ru.croc.Task12;

import java.util.*;

public class Task12 {
    public static void main(String[] args) throws Exception {
        Comments c = new Comments();
        List<String> comments = new ArrayList<>();
        Set<String> badwords = new HashSet<>();
        Scanner scanner = new Scanner(System.in);
        String fpath = "";
        System.out.print("Введите имя файла с комментариями: ");
        fpath = scanner.nextLine();
        c.fillComments(comments, fpath);
        System.out.print("Введите имя файла с черным списком: ");
        fpath = scanner.nextLine();
        c.fillVlackList(badwords, fpath);
        System.out.print("Введите имя файла для записи результатов: ");
        fpath = scanner.nextLine();
        c.filterComments(comments, badwords);
        c.writeResults(comments, fpath);
        scanner.close();
    }
}

package ru.croc.Task15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Task15 {
    public static void main(String[] args) {
        int temp = 0;
        List<Group> groups = new ArrayList<>();
        List<Human> humans = new ArrayList<>();
        for(var it : args){
            groups.add(new Group(temp, Integer.parseInt(it)));
            temp = Integer.parseInt(it) + 1;
        }
        groups.add(new Group(temp, Group.MAX_AGE));
        Collections.reverse(groups);
        System.out.println("Группы успешно созданы - введите опрошенных.");
        Scanner sc = new Scanner(System.in);
        String str;
        while(!(str = sc.nextLine()).equals("END"))
            humans.add(new Human(str));
        for (var it : groups) {
            it.fillResp(humans);
            System.out.print(it);
        }
    }
}

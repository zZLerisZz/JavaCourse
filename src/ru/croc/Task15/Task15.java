package ru.croc.Task15;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task15 {
    public static void main(String[] args) {
        int temp = 0;
        List<Group> gr = new ArrayList<>();
        List<Human> hm = new ArrayList<>();
        for(var it : args){
            gr.add(new Group(temp, Integer.parseInt(it)));
            temp = Integer.parseInt(it);
        }
        gr.add(new Group(temp, Group.MAX_AGE));
        System.out.println("Группы успешно созданы - введите опрошенных.");
        Scanner sc = new Scanner(System.in);
        String str;
        while(!(str = sc.nextLine()).equals("END"))
            hm.add(new Human(str));
        for (var it : gr) {
            it.fillResp(hm);
            System.out.print(it);
        }
        sc.close();
    }
}

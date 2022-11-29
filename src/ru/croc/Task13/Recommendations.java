package ru.croc.Task13;

import java.io.FileReader;
import java.util.*;

public class Recommendations {
    private List<String> films;
    private Set<String> history;
    public Recommendations(){
        films = new ArrayList<>();
        history = new HashSet<>();
    }
    public void fillFilms(String path) throws Exception{
        Scanner scan = null;
        FileReader fl = null;
        fl = new FileReader(path);
        scan = new Scanner(fl);
        while(scan.hasNextLine()){
            String[] str = scan.nextLine().split(",");
            films.add(str[1]);
        }
        scan.close();
        fl.close();
    }
    public void fillHistory(String path) throws Exception{
        Scanner sc = null;
        FileReader fl = null;
        fl = new FileReader(path);
        sc = new Scanner(fl);
        while(sc.hasNextLine()){
            history.add(sc.nextLine());
        }
        sc.close();
        fl.close();
    }
    public void getRecommendation(String userhistory){
        int[] cw = new int[films.size()];
        int[] marks = new int[films.size()];
        String[] nums = userhistory.split(",");
        int ccount, rindex = 0, rcount = 0;
        for (var item:history){
            String[] fs = item.split(",");
            for (var it:fs)
                cw[Integer.parseInt(it) - 1] += 1;
            ccount = 0;
            for(var it:nums){
                marks[Integer.parseInt(it) - 1] = -999;
                if (item.contains(it)) {
                    ccount += 1;
                }
            }
            int cof = 0;
            if(fs.length % 2 == 0)
                cof = fs.length / 2;
            else
                cof = fs.length / 2 + 1;
            if(ccount >= cof){
                for (var itr:fs){
                    if(!userhistory.contains(itr))
                        marks[Integer.parseInt(itr) - 1] += 1;
                }
            }
        }
        for(int i = 0; i < cw.length; i++){
            if(cw[i] > rcount && marks[i] > 0){
                rcount = cw[i];
                rindex = i;
            }
        }
        System.out.println("Вам рекоманедовано: " + films.toArray()[rindex]);
    }
}

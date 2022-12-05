package ru.croc.Task13;

import java.io.FileReader;
import java.util.*;

public class Recommendations {
    private List<String> films = new ArrayList<>();
    private Set<String> history = new HashSet<>();
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
        List<String> nums = new ArrayList<>(Arrays.stream(userhistory.split(",")).toList());
        int cof = 0;
        if(nums.size() % 2 == 0)
            cof = nums.size() / 2;
        else
            cof = nums.size() / 2 + 1;
        int ccount, rindex = 0, rcount = 0;
        for (var item:history){
            List<String> fs =new ArrayList<>( Arrays.stream(item.split(",")).toList());
            for (var it:fs)
                cw[Integer.parseInt(it) - 1] += 1;
            ccount = 0;
            for(var it:nums){
                marks[Integer.parseInt(it) - 1] = -999;
                if (item.contains(it)) {
                    ccount += 1;
                }
            }
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

package ru.croc.Task12;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Comments implements BlackListFilter{
    public void fillComments(List<String> comments, String filepath) throws Exception{
        Scanner sc = null;
        FileReader fl = null;
        fl = new FileReader(filepath);
        sc = new Scanner(fl);
        while(sc.hasNextLine())
            comments.add(sc.nextLine());
        sc.close();
        fl.close();
    }
    public void fillVlackList(Set<String> badwords, String filepath) throws Exception{
        Scanner scan = null;
        FileReader fl = null;
        fl = new FileReader(filepath);
        scan = new Scanner(fl);
        while(scan.hasNextLine())
            badwords.add(scan.nextLine());
        scan.close();
        fl.close();
    }
    public void writeResults(List<String> comments, String filepath) throws Exception{
        FileOutputStream fo = new FileOutputStream(filepath);
        for(var it:comments) {
            String str = it + '\n';
            fo.write(str.getBytes());
        }
        fo.flush();
        fo.close();
    }
    @Override
    public void filterComments(List<String> comments, Set<String> blackList) {
        List<String> badcomments = new ArrayList<>();
        for(var item:comments){
            for(var it:blackList){
                if(item.contains(it))
                    badcomments.add(item);
            }
        }
        comments.removeAll(badcomments);
    }
}

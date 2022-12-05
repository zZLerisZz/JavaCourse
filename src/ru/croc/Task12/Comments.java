package ru.croc.Task12;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.*;

public class Comments{
    private List<String> comments = new ArrayList<>();
    private Set<String> badwords = new HashSet<>();
    private Filter filt = new Filter();
    public void fillComments(String filepath) throws Exception{
        Scanner sc = null;
        FileReader fl = null;
        fl = new FileReader(filepath);
        sc = new Scanner(fl);
        while(sc.hasNextLine())
            comments.add(sc.nextLine());
        sc.close();
        fl.close();
    }
    public void fillBlackList(String filepath) throws Exception{
        Scanner scan = null;
        FileReader fl = null;
        fl = new FileReader(filepath);
        scan = new Scanner(fl);
        while(scan.hasNextLine())
            badwords.add(scan.nextLine());
        scan.close();
        fl.close();
    }
    public void writeResults(String filepath) throws Exception{
        FileOutputStream fo = new FileOutputStream(filepath);
        for(var it:comments) {
            String str = it + '\n';
            fo.write(str.getBytes());
        }
        fo.flush();
        fo.close();
    }

    public void filter(){
        filt.filterComments(comments, badwords);
    }
}

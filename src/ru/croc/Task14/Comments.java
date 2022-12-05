package ru.croc.Task14;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.*;
import java.util.function.Predicate;

public class Comments{
    private List<String> comments;
    private List<String> censcomments;
    Set<String> badwords;
    Predicate<String> censor = new Predicate<String>() {
        @Override
        public boolean test(String comment) {//функция тест должна меняться в зависимости от объекта
            for(var badword : badwords)
            {
                if(comment.toLowerCase().contains(badword)) {
                    return true;
                }
            }
            return false;
        }
    };
    private Filter filt = new Filter();

    public Comments(){
        comments = new ArrayList<>();
        badwords = new HashSet<>();
    }
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
        for(var it:censcomments) {
            String str = it + '\n';
            fo.write(str.getBytes());
        }
        fo.flush();
        fo.close();
    }

    public void filter(){
        censcomments = filt.filterComments(comments, censor);
    }
}

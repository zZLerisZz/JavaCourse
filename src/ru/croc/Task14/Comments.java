package ru.croc.Task14;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.*;
import java.util.function.Predicate;

public class Comments{
    private List<String> comments;
    private List<String> censoredComments;
    private Filter<String> filt = new Filter<String>();

    public Comments(){
        comments = new ArrayList<>();
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
    public void fillFilter(String filepath) throws Exception {
        filt.fillBlackList(filepath);
    }
    public void writeResults(String filepath) throws Exception{
        FileOutputStream fo = new FileOutputStream(filepath);
        for(var it:censoredComments) {
            String str = it + '\n';
            fo.write(str.getBytes());
        }
        fo.flush();
        fo.close();
    }
    Predicate<String> censor = new Predicate<String>() {
        Set<String> badWords = filt.getBadWords();
        @Override
        public boolean test(String comment) {//функция тест должна меняться в зависимости от объекта
            for(var badWord : badWords) {
                if(comment.toLowerCase().contains(badWord.toLowerCase())) {
                    return true;
                }
            }
            return false;
        }
    };

    public void createCensoredComments(){
        censoredComments = filt.censorComments(comments, censor);
    }
}

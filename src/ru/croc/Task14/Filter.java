package ru.croc.Task14;

import java.io.FileReader;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Predicate;

public class Filter<T> implements BlackListFilter<T>{
    Set<String> badWords = new HashSet<>();
    public Set<String> getBadWords(){
        return badWords;
    }
    public void fillBlackList(String filepath) throws Exception{
        Scanner scan = null;
        FileReader fl = null;
        fl = new FileReader(filepath);
        scan = new Scanner(fl);
        while(scan.hasNextLine())
            badWords.add(scan.nextLine());
        scan.close();
        fl.close();
    }
    public List<T> censorComments(List<T> commentsToCensor, Predicate<T> censor){
        return filterComments(commentsToCensor, censor);
    }
}

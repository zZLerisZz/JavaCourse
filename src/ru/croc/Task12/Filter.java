package ru.croc.Task12;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Filter implements BlackListFilter{
    @Override
    public void filterComments(List<String> comments, Set<String> blackList) {
        List<String> badcomments = new ArrayList<>();
        for(var item:comments){
            for(var it:blackList){
                if(item.toLowerCase().contains(it.toLowerCase()))
                    badcomments.add(item);
            }
        }
        comments.removeAll(badcomments);
    }
}

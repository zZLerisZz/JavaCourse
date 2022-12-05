package ru.croc.Task14;

import java.util.ArrayList;
import java.util.function.Predicate;


public interface BlackListFilter<T> {
    default ArrayList<T> filterComments(Iterable<T> comments, Predicate<T> blackList)
    {
        ArrayList<T> res = new ArrayList<>();
        for(var item:comments)
        {
            if(!blackList.test(item))
            {
                res.add((T) item);
            }
        }
        return res;
    }
}

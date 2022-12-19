package ru.croc.Task17;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.List;

public class Task17 {
    public static void main(String[] args) throws FileNotFoundException, SQLException {
        BufferedReader r = new BufferedReader(new FileReader(args[0]));
        List<String> dataToParse = r.lines().toList();
        OrderDataBase orderDataBase = new OrderDataBase(dataToParse);
        ItemDataBase itemDataBase = new ItemDataBase(dataToParse);
        DataBaseWriter wr = new DataBaseWriter("jdbc:h2:/DataBase/shopDataBaseRegenerateFff", "sa", "");
        wr.fillDataBase(orderDataBase, itemDataBase);
    }
}

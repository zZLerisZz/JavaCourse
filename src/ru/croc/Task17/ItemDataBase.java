package ru.croc.Task17;

import java.util.ArrayList;
import java.util.List;

public class ItemDataBase {
    private final List<Item> itemList;
    public ItemDataBase(List<String> stringsToParse){
        itemList = new ArrayList<>();
        fillItemList(stringsToParse);
    }
    private void fillItemList(List<String> stringsToParse){
        for (var it : stringsToParse){
            boolean fl = true;
            String[] data = it.split(",");
            for (var item : itemList)
                if(item.getVendorCode().equals(data[2])){
                    fl = false;
                    break;
                }
            if(fl)
                itemList.add(new Item(data[2], data[3],Integer.parseInt(data[4])));
        }
    }

    public List<Item> getItemList() {
        return itemList;
    }
}

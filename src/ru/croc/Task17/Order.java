package ru.croc.Task17;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final int numberOfOrder;
    private final String userName;
    private final List<Item> itemList;
    public Order(int num, String login, Item item){
        numberOfOrder = num;
        userName = login;
        itemList = new ArrayList<>();
        itemList.add(item);
    }

    public int getNumberOfOrder() {
        return numberOfOrder;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public String getUserName() {
        return userName;
    }
    public String getVendorCodes(){
        String str = "";
        for(var it : itemList)
            str += it.getVendorCode() + " ";
        return str;
    }
}

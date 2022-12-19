package ru.croc.Task17;
public class Item {
    private final String vendorCode;
    private final String itemName;
    private final int price;
    public Item(String code, String name, int cost){
        vendorCode = code;
        itemName = name;
        price = cost;
    }

    public String getItemName() {
        return itemName;
    }

    public int getPrice() {
        return price;
    }

    public String getVendorCode() {
        return vendorCode;
    }
}

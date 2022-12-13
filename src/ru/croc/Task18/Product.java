package ru.croc.Task18;
public class Product {
    private String vendorCode;
    private String name;
    private int price;
    public Product(String vCode, String nameOfProduct, int cost){
        vendorCode = vCode;
        name = nameOfProduct;
        price = cost;
    }
    public String getVendorCode(){
        return vendorCode;
    }
    public String getName(){
        return name;
    }
    public int getPrice(){
        return price;
    }
    @Override
    public String toString(){
        return "Vendor Code: " + vendorCode + "; Name of product: " + name + "; Price: " + price;
    }
}

package ru.croc.Task18;
public class Order {
    private int num;
    private String vendorCode;
    private String username;
    Order(int numberOfOrder, String codeOfPRoduct, String buyername){
        num = numberOfOrder;
        vendorCode = codeOfPRoduct;
        username = buyername;
    }
    public String getVendorCode(){
        return vendorCode;
    }
    public int getNum(){
        return num;
    }
    public String getUsername(){
        return username;
    }
    @Override
    public String toString(){
        return "Number of order: " + num + "; Vendor code of product: " + vendorCode + "; Username: " + username;
    }
}

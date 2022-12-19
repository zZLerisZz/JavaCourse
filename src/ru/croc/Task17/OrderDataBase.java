package ru.croc.Task17;
import java.util.ArrayList;
import java.util.List;

public class OrderDataBase {
    private final List<Order> orderList;
    public OrderDataBase(List<String> data){
        orderList = new ArrayList<>();
        fillOrders(data);
    }
    private void fillOrders(List<String> data){
        for(var it : data){
            String[] orderData = it.split(",");
            boolean fl = true;
            for(var order : orderList){
                if(order.getNumberOfOrder() == Integer.parseInt(orderData[0])){
                    order.getItemList().add(new Item(orderData[2], orderData[3], Integer.parseInt(orderData[4])));
                    fl = false;
                    break;
                }
            }
            if(fl){
                orderList.add(new Order(Integer.parseInt(orderData[0]), orderData[1],
                        new Item(orderData[2], orderData[3], Integer.parseInt(orderData[4]))));
            }
        }
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}

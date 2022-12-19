package ru.croc.Task17;

import java.sql.*;

public class DataBaseWriter {
    private final Connection conn;
    public DataBaseWriter(String url, String username, String password) throws SQLException {
        conn = DriverManager.getConnection(url, username, password);
    }
    public void fillDataBase(OrderDataBase orderBaseToWrite, ItemDataBase itemBaseToWrite) throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("create table if not exists items(vendorCode varchar primary key, " +
                    "item_name varchar not null, price int not null)");
        statement.execute("create table if not exists orders(num int primary key, " +
                    "vendor_Codes varchar not null, buyer varchar not null)");
        statement.execute("delete from items");
        statement.execute("delete from orders");
        ordersDataBaseWrite(orderBaseToWrite, statement);
        itemsDataBaseWrite(itemBaseToWrite, statement);
    }

    private void ordersDataBaseWrite(OrderDataBase orderBaseToWrite, Statement statement) throws SQLException {
        for(var it:orderBaseToWrite.getOrderList()){
            statement.execute("INSERT INTO orders VALUES" + "(" + it.getNumberOfOrder() + ", '"
                    + it.getVendorCodes() + "', '" + it.getUserName() + "')");
        }
    }
    private void itemsDataBaseWrite(ItemDataBase itemDataBase, Statement statement) throws SQLException {
        for(var it : itemDataBase.getItemList())
            statement.execute("INSERT INTO items VALUES" + "('" + it.getVendorCode() + "', '" +
                    it.getItemName() + "', " + it.getPrice() + ")");
    }
}

package ru.croc.Task17;

import java.sql.*;

public class DataBaseWriter {
    private final Connection conn;
    public DataBaseWriter(String url, String username, String password) throws SQLException {
        conn = DriverManager.getConnection(url, username, password);
    }
    public void fillDataBase(OrderDataBase baseToWrite) throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("create table if not exists items(vendorCode varchar primary key, " +
                    "item_name varchar not null, price int not null)");
        statement.execute("create table if not exists orders(num int primary key, " +
                    "vendor_Codes varchar not null, buyer varchar not null)");
        statement.execute("delete from items");
        statement.execute("delete from orders");
        for(var it:baseToWrite.getOrderList()){
            statement.execute("INSERT INTO orders VALUES" + "(" + it.getNumberOfOrder() + ", '"
                        + it.getVendorCodes() + "', '" + it.getUserName() + "')");
            for(var item : it.getItemList())
                if(isUnique(conn, item.getVendorCode())){
                    statement.execute("INSERT INTO items VALUES" + "('" + item.getVendorCode() + "', '" +
                        item.getItemName() + "', " + item.getPrice() + ")");
                }
        }
    }
    private boolean isUnique(Connection c, String vcode) throws SQLException {
        String select = "SELECT * FROM items WHERE vendorCode = '" + vcode + "'";
        Statement statement = c.createStatement();
        ResultSet result = statement.executeQuery(select);
        if (result.next()) {
            return false;
        }
        else {
            return true;
        }
    }
}

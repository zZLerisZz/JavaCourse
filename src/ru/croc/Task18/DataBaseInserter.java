package ru.croc.Task18;
import java.sql.*;
import java.util.List;

public class DataBaseInserter {
    public void fillDataBase(String filepath, String username, String password, List<String> data){
        try(Connection conn = DriverManager.getConnection(filepath, username, password)){
            Statement statement = conn.createStatement();
            statement.execute("create table if not exists products(vendorCode varchar not null, " +
                    "productName varchar not null, price int not null)");
            statement.execute("create table if not exists orders(num int not null, " +
                    "vendorCode varchar not null, buyer varchar not null)");
            statement.execute("delete from products");
            statement.execute("delete from orders");
            for(var it:data){
                String[] temp = it.split(",");
                int num = Integer.parseInt(temp[0]);
                String name = temp[1];
                String vendor = temp[2];
                String item = temp[3];
                int price = Integer.parseInt(temp[4]);
                statement.execute("INSERT INTO orders VALUES" + "(" + num + ", '"  + vendor + "', '" + name + "')");
                if(isUnique(conn, vendor)){
                    statement.execute("INSERT INTO products VALUES" + "('" + vendor + "', '" + item + "', " + price + ")");
                }
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean isUnique(Connection c, String vcode){
        String select = "SELECT * FROM products WHERE vendorCode = '" + vcode + "'";
        try (Statement statement = c.createStatement()){
            try (ResultSet result = statement.executeQuery(select)) {
                if (result.next()) {
                    return false;
                }
                else {
                    return true;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

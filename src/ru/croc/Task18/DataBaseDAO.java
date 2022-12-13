package ru.croc.Task18;
import java.sql.*;
import java.util.List;

public class DataBaseDAO {
    private Connection conn;
    public DataBaseDAO(String filepath, String username, String password){
        try {
            conn = DriverManager.getConnection(filepath, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Product findProduct(String productCode){
        try (Statement statement = conn.createStatement()){
            try (ResultSet result = statement.executeQuery("SELECT * FROM products WHERE vendorCode = '"
                    + productCode + "'")) {
                if (result.next()) {
                    return new Product(result.getString("vendorCode"),
                            result.getString("productName"), result.getInt("price"));
                }
                else {
                    return null;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Product createProduct(Product product) throws DBExceptions.ProductAlreadyExistsException{
        try{
            if(findProduct(product.getVendorCode()) != null)
                throw new DBExceptions.ProductAlreadyExistsException("Введенный вами продукт уже существует.");
            Statement statement = conn.createStatement();
            statement.execute("INSERT INTO products VALUES" + "('" +
                    product.getVendorCode() + "', '" + product.getName() + "', " + product.getPrice() + ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public Product updateProduct(Product product) throws DBExceptions.NoSuchProductException{
        try{
            if(findProduct(product.getVendorCode()) == null)
                throw new DBExceptions.NoSuchProductException("Введенного вами продукта не существует.");
            Statement statement = conn.createStatement();
            statement.execute("update products set productName = '" +
                    product.getName() + "', price = '" +
                    product.getPrice() + "' where vendorCode = '" +
                    product.getVendorCode() + "'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public void deleteProduct(String productCode) throws DBExceptions.NoSuchProductException {
        try{
            if(findProduct(productCode) == null)
                throw new DBExceptions.NoSuchProductException("Введенного вами продукта не существует.");
            Statement statement = conn.createStatement();
            statement.execute("delete from products where vendorCode = '" + productCode + "'");
            try (ResultSet result = statement.executeQuery("SELECT * FROM orders WHERE vendorCode = '"
                    + productCode + "'")) {
                if (result.next())
                    statement.execute("delete from orders where vendorCode = '" + productCode + "'");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Order createOrder(String userLogin, List<Product> products) throws DBExceptions.ProductAlreadyExistsException {
        try{
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT max(num) FROM orders");
            int numberOfCurOrder = 0;
            if(result.next())
                numberOfCurOrder = result.getInt("MAX(num)") + 1;
            for(var it:products){
                if(findProduct(it.getVendorCode()) == null)
                    it = createProduct(it);
                statement.execute("INSERT INTO orders VALUES" + "(" +
                        numberOfCurOrder + ", '" + it.getVendorCode() + "', '" + userLogin + "')");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}

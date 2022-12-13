package ru.croc.Task18;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Task18 {
    public static void main(String[] args) throws FileNotFoundException, DBExceptions.ProductAlreadyExistsException, SQLException, DBExceptions.NoSuchProductException {
        if(args.length == 1) {
            BufferedReader r = new BufferedReader(new FileReader(args[0]));
            List<String> databaseData = r.lines().toList();
            DataBaseInserter wr = new DataBaseInserter();
            wr.fillDataBase("jdbc:h2:/DataBase/shopDataBaseRegenerateFff20", "sa", "", databaseData);
        }
        DataBaseDAO dao = new DataBaseDAO("jdbc:h2:/DataBase/shopDataBaseRegenerateFff20", "sa", "");
        Product test = dao.createProduct(new Product("Т6", "колода карт", 120));
        dao.updateProduct(new Product("Т6", "колода вееров", 121));
        dao.deleteProduct("Т2");
        List<Product> products= new ArrayList<Product>();
        products.add(new Product("Т8","зубочистка",5));
        products.add(new Product("Т9","труп мухи",1000));
        products.add(test);
        dao.createOrder("Leris",products);
        dao.destroyConnection();
    }
}

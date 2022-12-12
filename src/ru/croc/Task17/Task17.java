import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class Task17 {
    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader r = new BufferedReader(new FileReader(args[0]));
        List<String> databaseData = r.lines().toList();
        DataBaseWriter wr = new DataBaseWriter();
        wr.fillDataBase("jdbc:h2:/DataBase/shopDataBaseRegenerateFff", "sa", "", databaseData);
    }
}

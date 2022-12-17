package ru.croc.Task16;


/* так, я также прикрепил уже заполненный файлик с таксистами, правила заполнения следующие:
*   1 строка - 1 таксист, порядок следования данных - имя, x, y, номер машины(целое число),
* Марка машины, класс комфорта, дополнительные требования. Данные разделяются ;*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task16 {
    private static final boolean TEST = false;
    //Если TEST false, то идет считывания водителей из файла, имитирую работу с БД,
    // true - водители создаются известными значениями для теста
    public static void main(String[] args) throws Exception {
        DriverFinder df = new DriverFinder();
        DriversBase driversBase;
        Client client;
        if(args.length >= 4) {
            List<String> fac = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(args, 3, args.length)));
            if(!Checkers.FacilitiesChecker.checkFacilities(fac))
                throw new MyExceptions.NoSuchFacility();
            if(!Checkers.ComfortClassChecker.checkComfortClass(args[2]))
                throw new MyExceptions.NoSuchComfortClass();
            client = new Client(Double.parseDouble(args[0].substring(0, args[0].lastIndexOf(","))),
                    Double.parseDouble(args[1]), args[2], fac);
        }
        else{
            throw new Exception("Недостаточно входных данных в аргументах программы.");
        }
        if(TEST) {
            driversBase = new DriversBase();
        }
        else{
            driversBase = new DriversBase("Drivers.txt");
        }
        System.out.println(df.findDriver(driversBase.getDrivers(), client));
    }
}

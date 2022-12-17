package ru.croc.Task16;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DriversBase {
    private final List<Driver> drivers;
    public DriversBase(String fpath) throws FileNotFoundException, MyExceptions.NoSuchFacility, MyExceptions.NoSuchComfortClass {
        drivers = new ArrayList<>();
        Scanner scan = null;
        FileReader fl = null;
        fl = new FileReader(fpath);
        scan = new Scanner(fl);
        List<String> str = new ArrayList<>();
        while (scan.hasNextLine())
            str.add(scan.nextLine());
        String[] nstr = str.toArray(new String[str.size()]);
        for(int i = 0; i < str.size(); i++){
            String[] cstr = nstr[i].split(";");
            List<String> fac = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(cstr, 6, cstr.length)));
            if(!Checkers.FacilitiesChecker.checkFacilities(fac))
                throw new MyExceptions.NoSuchFacility();
            if(!Checkers.ComfortClassChecker.checkComfortClass(cstr[5]))
                throw new MyExceptions.NoSuchComfortClass();
            drivers.add(new Driver(cstr[0], Double.parseDouble(cstr[1]), Double.parseDouble(cstr[2]),
                    new Car(Integer.parseInt(cstr[3]), cstr[4], cstr[5], fac)));
        }
        drivers.sort(Driver.driverComparator);
    }
    public DriversBase(){
        drivers = new ArrayList<>();
        drivers.add(new Driver("Ахмед", 12.134, 13.147,
                new Car(19822, "Лада", "Эконом",
                        new ArrayList<>(Arrays.asList("Большой багажник")))));
        drivers.add(new Driver("Влад", -12.134, 99.147,
                new Car(20022, "Рено", "Комфорт",
                        new ArrayList<>(Arrays.asList("Детское кресло")))));
        drivers.add(new Driver("Руслан", 0, 0,
                new Car(19822, "Мерседес", "Бизнес",
                        new ArrayList<>(Arrays.asList("Сиденья с подогревом", "Детское кресло")))));
    }
    public List<Driver> getDrivers(){
        return drivers;
    }
}

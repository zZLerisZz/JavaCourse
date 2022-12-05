package ru.croc.Task16;


/* так, я также прикрепил уже заполненный файлик с таксистами, правила заполнения следующие:
*   1 строка - 1 таксист, порядок следования данных - имя, x, y, номер машины(целое число),
* Марка машины, класс комфорта, дополнительные требования. Данные разделяются ; пробелы заменяются на -
*  По поводу клиента - x y желаемые условия, разные условия через пробел, а если условие из двух слов, то пробелы заменяются на -
* пример: "Детское-кресло". спасибо, что прочитали*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Task16 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите имя файла с данными таксистов: ");
        String fp = sc.nextLine();
        DriverFinder df = new DriverFinder(fp,
                new Client(Double.parseDouble(args[0].substring(0, args[0].lastIndexOf(","))),
                        Double.parseDouble(args[1]), args[2],
                        new ArrayList<>(Arrays.asList(Arrays.copyOfRange(args, 3, args.length)))));
        df.findDriver();
    }
}

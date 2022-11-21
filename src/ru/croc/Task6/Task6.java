package ru.croc.Task6;

import java.io.FileReader;
import java.util.Scanner;

public class Task6 {
    public static String readFile(String fileName) throws Exception
    {
        Scanner sc = null;
        FileReader fr = null;
        fr = new FileReader(fileName);
        sc = new Scanner(fr);
        String str = "";
        while(sc.hasNextLine())
           str += sc.nextLine() + '\n';

        sc.close();
        fr.close();
        return deleteComments(str);
    }

    public static String deleteComments(String source){
        String[] temp = source.split("\n");
        for(int i = 0; i < temp.length; i++){
            if(temp[i].contains("//")) {
                temp[i] = temp[i].substring(0, temp[i].indexOf("//"));
            }
            else if(temp[i].contains("/*")){
                int endInd = temp[i].indexOf("*/");
                if(endInd != -1)
                    temp[i] = temp[i].substring(0, temp[i].indexOf("/*")) + temp[i].substring(temp[i].indexOf("*/") + 2, temp[i].length());
                else {
                    temp[i] = temp[i].substring(0, temp[i].indexOf("/*"));
                    do {
                        i++;
                        endInd = temp[i].indexOf("*/");
                        if(endInd == -1)
                            temp[i] = "";
                    } while (endInd == -1 && i < temp.length);
                    temp[i] = temp[i].substring(temp[i].indexOf("*/") + 2, temp[i].length());
                }
            }
        }
        String result = String.join("\n",temp);
        return result;
    }

    public static void main(String[] args) {
        String text, fName;
        Scanner s = new Scanner(System.in);
        System.out.print("Введите имя обрабатываемого файла:");
        fName = s.nextLine();
        s.close();
        try {
            text = readFile(fName);
            System.out.println(text);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}

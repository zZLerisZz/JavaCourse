//package ru.croc.Task9;
//ответ - passwrd

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Task9 {
    static int checkInt(String str, Scanner sc){
        int data = 0;
        boolean fl = false;
        do{
            System.out.print(str + ": ");
            fl = sc.hasNextInt();
            if (fl) {
                data = sc.nextInt();
                sc.nextLine();
            }
            else {
                System.out.println("Введено неправильное значение.");
                sc.nextLine();
            }
        }while(!fl);
        return data;
    }
    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    private static String toHexString(byte[] bytes) {
        StringBuilder hex = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            hex.append(HEX_DIGITS[(b & 0xff) >> 4]);
            hex.append(HEX_DIGITS[b & 0x0f]);
        }
        return hex.toString();
    }

    public static String hashPassword(String password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        digest.update(password.getBytes());
        byte[] bytes = digest.digest();
        return toHexString(bytes);
    }

    public static void main(String[] args) {
        int trnum = 0;
        long comc = (long)Math.pow(26, 7);
        String hash = "";
        if(args.length == 2){
            trnum = Integer.parseInt(args[0]);
            hash = args[1];
        }
        else{
            Scanner sc = new Scanner(System.in);
            trnum = checkInt("Количество потоков", sc);
            do{
                System.out.print("Введите хэш пароля: ");
                hash = sc.nextLine();
            }while (hash.equals(""));
            PassFinder.SetGoal(hash);
            sc.close();
        }
        System.out.print("Искомый пароль: ");
        Thread[] trs = new Thread[trnum];
        for(int i = 0; i < trnum; i++){
            trs[i] = new Thread(new PassFinder((comc * i) / trnum, (comc * (i + 1) / trnum)));
            trs[i].start();
        }
        for(int i = 0; i < trnum; i++){
            try {
                trs[i].join();
            }
            catch (InterruptedException exc){
                System.out.println(exc);
            }
        }
    }
}

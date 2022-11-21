package ru.croc.Task9;
public class PassFinder implements Runnable{
    private long start, end;
    public static volatile boolean fl = false;
    private static String goal_pass;

    public PassFinder(long s, long e){
        this.start = s;
        this.end = e;
    }

    public static void SetGoal(String p){
        goal_pass = p;
    }

    public void run(){
        for(long i = this.start; i < this.end && !fl; i++){
            String curps = getCurPas(i);
            if(Task9.hashPassword(curps).equals(goal_pass)){
                fl = true;
                System.out.print(curps);
            }
        }
    }

    private String getCurPas(long comb){
        String ps = "";
        for(int i = 0; i < 7; i++){
            ps += (char)('a' + comb % 26);
            comb /= 26;
        }
        return ps;
    }
}

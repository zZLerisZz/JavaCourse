package ru.croc.Task16;

import java.util.List;

public class Checkers {
    public static class FacilitiesChecker{
        public static boolean checkFacilities(List<String> fac){
            for(var it: fac)
                if(!FullConditions.FullFacilities.getFullFacilities().contains(it))
                    return false;
            return true;
        }
    }
    public static class ComfortClassChecker{
        public static boolean checkComfortClass(String curClass){
            return FullConditions.FullComfortClasses.getFullComfortClasses().contains(curClass);
        }
    }
}

package ru.croc.Task16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FullConditions {
    public static class FullFacilities{
        private final static List<String> fullFacilities = new ArrayList<>(Arrays.asList("Детское кресло", "Большой багажник",
                "Сиденья с подогревом", "Места для животных"));
        public static List<String> getFullFacilities(){
            return fullFacilities;
        }
    }
    public static class FullComfortClasses{
        private final static List<String> fullComfortClasses = new ArrayList<>(Arrays.asList("Эконом", "Бизнес", "Комфорт"));
        public static List<String> getFullComfortClasses(){
            return fullComfortClasses;
        }
    }
}

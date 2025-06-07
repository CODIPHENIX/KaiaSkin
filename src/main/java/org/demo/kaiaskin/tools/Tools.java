package org.demo.kaiaskin.tools;

public class Tools {
    public static boolean isInvalide(String value){
        return value == null || value.isBlank();
    }
    public static <T extends Enum<T>> boolean isValidEnum(Class<T> enumClass, String value) {

        try {
            Enum.valueOf(enumClass, value.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}

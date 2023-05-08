/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Raul
 */
public class ModelColumnMapper {

    public static String toSnakeCase(String camelCase) {
        return camelCase.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }

    public static Map<String, String> getColumns(Class<?> clazz) {
        Map<String, String> columns = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            String columnName = field.getName();
            columns.put(field.getName(), toSnakeCase(columnName));
        }
        return columns;
    }

}

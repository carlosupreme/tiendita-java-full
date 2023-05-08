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
 * Esta clase mapea los nombres de atributos de una clase
 * y los convierte de camelCase a snake_case.
 * 
 * @author Raul
 */
public class ModelColumnMap {

    public static Map<String, String> getColumns(Class<?> clazz) {
        Map<String, String> columns = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            String columnName = field.getName();
            columnName = columnName.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
            columns.put(field.getName(), columnName);
        }
        return columns;
    }

}

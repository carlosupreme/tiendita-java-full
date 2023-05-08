/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Esta clase mapea los nombres de atributos de una clase
 * a nombres de columnas de base de datos incluyendo su
 * respectivo valor. Debido a las convenciones, los atributos
 * se convierten de camelCase a snake_case. Por ejemplo, convierte 
 * "productoId" a "producto_id".
 * 
 * @author Raul
 */
public class ModelColumnMap {

    public static Map<String, Object> getColumns(Object obj) throws IllegalArgumentException, IllegalAccessException {
        Class<?> clazz = obj.getClass();
        
        Map<String, Object> columnas = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            
            String nombreAtributo = field.getName();
            nombreAtributo = nombreAtributo
                    .replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
            
            Object fieldValue = field.get(obj);
            
            columnas.put(nombreAtributo, fieldValue);
        }
        return columnas;
    }

}

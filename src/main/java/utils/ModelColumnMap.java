/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 *
 * Esta clase mapea los nombres de atributos de una clase
 * a nombres de columnas de base de datos, lo que significa
 * que debido a las convenciones, los convierte de camelCase 
 * a snake_case.
 * Por ejemplo, convierte "productoId" a "producto_id".
 * 
 * @author Raul
 */
public class ModelColumnMap {

    public static ArrayList<String> getColumns(Class<?> clazz) {
        ArrayList<String> columnas = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            String nombreAtributo = field.getName();
            nombreAtributo = nombreAtributo
                    .replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
            columnas.add(nombreAtributo);
        }
        return columnas;
    }
    
    /*public static String getAttrColumnValues() {
        
        ArrayList<String> columnas = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            String nombreAtributo = field.getName();
            nombreAtributo = nombreAtributo
                    .replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
            columnas.add(nombreAtributo);
        }
        return columnas;
        
    }*/

}

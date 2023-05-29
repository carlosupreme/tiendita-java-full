/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package exceptions;

/**
 *
 * @author Carlos
 */
public class ValidationModelException extends RuntimeException {

    /**
     * Creates a new instance of <code>ValidationModelException</code> without
     * detail message.
     */
    public ValidationModelException() {
    }

    /**
     * Constructs an instance of <code>ValidationModelException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ValidationModelException(String msg) {
        super(msg);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package helpers;

import java.awt.event.ActionEvent;
import javax.swing.JDialog;

/**
 *
 * @author carlos
 */
public interface Confirmation {

    public void onConfirm(JDialog component, ActionEvent evt);

    public void onCancel(JDialog component, ActionEvent evt);
}

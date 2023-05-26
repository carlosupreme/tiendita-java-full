package views.inventario;

import exceptions.ValidationModelException;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import models.Producto;

/**
 *
 * @author Carlos
 */
public class RealTimeValidator {

    private ArrayList<HashMap<JTextField, JLabel>> textInputs;
    private ArrayList<HashMap<JTextField, JLabel>> numericInputs;

    public void setTextInputs(ArrayList<HashMap<JTextField, JLabel>> textInputs) {
        this.textInputs = textInputs;
    }

    public void setNumericInputs(ArrayList<HashMap<JTextField, JLabel>> numericInputs) {
        this.numericInputs = numericInputs;
    }

    public void addTextValidation() {
        for (HashMap<JTextField, JLabel> inputLabel : textInputs) {
            inputLabel.keySet().forEach(input -> {
                input.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        validateTextField();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        validateTextField();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        validateTextField();
                    }

                    private void validateTextField() {
                        String text = input.getText();

                        try {
                            Producto.esTextoValido(text);
                            input.setBorder(new LineBorder(Color.GRAY));
                            inputLabel.get(input).setText("");
                        } catch (ValidationModelException ex) {
                            input.setBorder(new LineBorder(Color.RED));
                            inputLabel.get(input).setText(ex.getMessage());
                        }
                    }
                });
            });

        }
    }
}

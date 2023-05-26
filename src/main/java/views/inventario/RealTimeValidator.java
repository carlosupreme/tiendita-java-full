package views.inventario;

import exceptions.ValidationModelException;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
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

    private HashMap<JTextField, JLabel> textInputs;
    private HashMap<JTextField, JLabel> numericInputs;

    public void setTextInputs(HashMap<JTextField, JLabel> textInputs) {
        this.textInputs = textInputs;
    }

    public void setNumericInputs(HashMap<JTextField, JLabel> numericInputs) {
        this.numericInputs = numericInputs;
    }

    public void addTextValidation() {
        for (Map.Entry<JTextField, JLabel> entry : textInputs.entrySet()) {
            JTextField input = entry.getKey();
            JLabel labelError = entry.getValue();

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
                        labelError.setText("");
                    } catch (ValidationModelException ex) {
                        input.setBorder(new LineBorder(Color.RED));
                        labelError.setText(ex.getMessage());
                    }
                }
            });

        }
    }

    public void addNumericValidation() {
        for (Map.Entry<JTextField, JLabel> entry : numericInputs.entrySet()) {
            JTextField input = entry.getKey();
            JLabel labelError = entry.getValue();

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
                    try {
                        Double n = Double.valueOf(input.getText());
                        Producto.esDineroValido(n);
                        input.setBorder(new LineBorder(Color.GRAY));
                        labelError.setText("");
                    } catch (ValidationModelException ex) {
                        input.setBorder(new LineBorder(Color.RED));
                        labelError.setText(ex.getMessage());
                    } catch (NumberFormatException ex) {
                        input.setBorder(new LineBorder(Color.RED));
                        labelError.setText("Ingresa solo numeros");
                    }
                }
            });

        }
    }

    public void addCodigoBarrasValidation(JTextField input, JLabel labelError) {
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
                String n = input.getText();
                try {
                    Producto.esCodigoValido(n);
                    input.setBorder(new LineBorder(Color.GRAY));
                    labelError.setText("");
                } catch (ValidationModelException ex) {
                    input.setBorder(new LineBorder(Color.RED));
                    labelError.setText(ex.getMessage());
                }
            }
        });

    }

}

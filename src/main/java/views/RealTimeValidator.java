package views;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class RealTimeValidator {

    private static Border defaultBorder = new LineBorder(Color.GRAY);
    private static Border errorBorder = new LineBorder(Color.RED);

    private static List<JTextField> fields = new ArrayList<>();
    private static List<ValidationRule> validationRules = new ArrayList<>();

    public static void addValidation(JTextField textField, ValidationRule validationRule) {
        fields.add(textField);
        validationRules.add(validationRule);

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validateField(textField);
            }

        });
    }

    private static void validateField(JTextField textField) {
        int index = fields.indexOf(textField);
        if (index != -1 && index < validationRules.size()) {
            ValidationRule validationRule = validationRules.get(index);
            JLabel errorLabel = validationRule.getErrorLabel();
            ValidationResult validationResult = validationRule.validate(textField.getText());

            if (validationResult.isValid()) {
                textField.setBorder(defaultBorder);
                errorLabel.setText("");
            } else {
                textField.setBorder(errorBorder);
                errorLabel.setText(validationResult.getMessage());
            }
        }
    }
}

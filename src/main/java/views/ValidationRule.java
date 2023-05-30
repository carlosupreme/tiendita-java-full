package views;

import exceptions.ValidationModelException;
import java.awt.Color;
import java.util.function.Predicate;
import javax.swing.JLabel;

public class ValidationRule {
    
    private final Predicate<String> validationRule;
    private final JLabel errorLabel;
    
    public ValidationRule(Predicate<String> validationRule, JLabel errorLabel) {
        this.validationRule = validationRule;
        
        errorLabel.setForeground(Color.RED);
        this.errorLabel = errorLabel;
        
    }
    
    public ValidationResult validate(String text) {
        boolean isValid = false;
        String message = "";
        try {
            isValid = validationRule.test(text);
        } catch (ValidationModelException ex) {
            message = ex.getMessage();
        }
        return new ValidationResult(isValid, message);
    }
    
    public JLabel getErrorLabel() {
        return errorLabel;
    }
}

package com.ip.domain.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Override
    public void initialize(Password constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null)
            return false;

        // one number one lower case and one uppercase
        return Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", value);
    }
}

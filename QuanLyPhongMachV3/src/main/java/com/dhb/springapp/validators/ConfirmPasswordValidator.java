package com.dhb.springapp.validators;


import com.dhb.springapp.modelview.AddDoctor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, Object> {
    @Override
    public void initialize(ConfirmPassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        AddDoctor doctor = (AddDoctor) value;
        return doctor.getPassword().equals(doctor.getConfirmPassword());
    }
}

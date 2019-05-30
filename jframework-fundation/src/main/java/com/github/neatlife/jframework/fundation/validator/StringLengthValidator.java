package com.github.neatlife.jframework.fundation.validator;

import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author suxiaolin
 */
public class StringLengthValidator implements ConstraintValidator<StringLength, CharSequence> {

    private int min;
    private int max;

    @Override
    public void initialize(StringLength parameters) {
        min = parameters.min();
        max = parameters.max();
    }

    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {
        if (ObjectUtils.isEmpty(charSequence)) {
            return true;
        }
        int length = charSequence.length();
        return length >= min && length <= max;
    }

}

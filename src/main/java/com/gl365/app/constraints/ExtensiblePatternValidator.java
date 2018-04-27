package com.gl365.app.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.regex.Pattern;

/**
 * @author Chen, Zhuhui
 */
public class ExtensiblePatternValidator<T extends Annotation> implements ConstraintValidator<T, CharSequence> {

    private final Pattern pattern;

    public ExtensiblePatternValidator(Pattern pattern) {
        this.pattern = pattern;
    }

    public ExtensiblePatternValidator(String regexp) {
        this.pattern = Pattern.compile(regexp);
    }

    /**
     * Initializes the validator in preparation for
     * {@link #isValid(Object, ConstraintValidatorContext)} calls.
     * The constraint annotation for a given constraint declaration
     * is passed.
     * <p/>
     * This method is guaranteed to be called before any use of this instance for
     * validation.
     *
     * @param constraintAnnotation annotation instance for a given constraint declaration
     */
    @Override
    public void initialize(T constraintAnnotation) {

    }

    /**
     * Implements the validation logic.
     * The state of {@code value} must not be altered.
     * <p/>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param value   object to validate
     * @param context context in which the constraint is evaluated
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if ( value == null || value.length() == 0) {
            return true;
        }
        return pattern.matcher(value).matches();
    }
}

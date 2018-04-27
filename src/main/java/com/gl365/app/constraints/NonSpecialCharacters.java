package com.gl365.app.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Chen, Zhuhui
 */
@Documented
@Constraint(validatedBy = { NonSpecialCharactersValidator.class })
@Target({ ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NonSpecialCharacters {

    String message() default "{NonSpecialCharacters}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

package org.launchcode.Competrack.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;
import java.lang.annotation.*;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {URLValidator.class}
)

public @interface URLValidation {
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

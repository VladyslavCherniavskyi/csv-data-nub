package com.csvdatanub.validation.annotation;

import com.csvdatanub.validation.FileValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileValidator.class)
public @interface File {
    String message() default "Invalid MultipartFile";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}


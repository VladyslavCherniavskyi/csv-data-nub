package com.csvdatanub.validation;

import com.csvdatanub.validation.annotation.File;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public class FileValidator implements ConstraintValidator<File, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        return Optional.ofNullable(file)
                .filter(multipartFile -> !multipartFile.isEmpty())
                .map(MultipartFile::getOriginalFilename)
                .filter(StringUtils::hasText)
                .isPresent();
    }

}

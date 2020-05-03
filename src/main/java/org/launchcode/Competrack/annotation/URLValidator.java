package org.launchcode.Competrack.annotation;


import org.launchcode.Competrack.data.CompanyDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.MalformedURLException;
import java.net.URL;

public class URLValidator implements ConstraintValidator<URLValidation, String>{

    @Autowired
    public CompanyDetailsRepository companyDetailsRepository;



    @Override
    public void initialize(URLValidation constraintAnnotation) {


    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {


            new URL(s).toURI();
            return true;
        }
        catch (MalformedURLException e) {
            System.out.println("MalformedURLException");
            return false;
        }
        catch (Exception e) {
            System.out.println("All other exception");
            return false;
        }
    }
}


package com.sandiso.banking.system.contstraint;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }
    // https://www.javaguides.net/2018/10/user-registration-module-using-springboot-springmvc-springsecurity-hibernate5-thymeleaf-mysql.html
    @Override
    public boolean isValid(final Object object, final ConstraintValidatorContext constraintValidatorContext) {
        try {
            final Object firstObj = BeanUtils.getProperty(object, firstFieldName);
            final Object secondObj = BeanUtils.getProperty(object, secondFieldName);
            return  firstObj == null && secondObj == null || firstObj != null
                    && firstObj.equals(secondObj);
        } catch (final Exception ignore){}
        return true;
    }
}

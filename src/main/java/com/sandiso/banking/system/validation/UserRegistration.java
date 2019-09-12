package com.sandiso.banking.system.validation;

import com.sandiso.banking.system.contstraint.FieldMatch;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/*
 * We use the UserRegistrationDto to validate the user registration form.
 * This DTO is annotated using Hibernate-Validation annotations which validate
 * trivial fields on empty and our own custom @FieldMatch annotations which
 * validates if the password is equal to the confirm password and the email
 * address field is equal to the confirm email address field.
 */


@FieldMatch.List({
        @FieldMatch(first = "password",
                second = "confirmPassword",
                message = "The password fields must match"),
        @FieldMatch(first = "email", second = "confirmEmail",
                message = "The email field must match")
})
public class UserRegistration {

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String surname;
    @NotEmpty
    private String password;
    @NotEmpty
    private String confirmPassword;
    @Email
    @NotEmpty
    private String email;
    @Email
    @NotEmpty
    private String confirmEmail;
    @NotEmpty
    private String cellphone;
    @NotEmpty
    private String accountNumber;

    @AssertTrue
    private Boolean terms;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Boolean getTerms() {
        return terms;
    }

    public void setTerms(Boolean terms) {
        this.terms = terms;
    }
}

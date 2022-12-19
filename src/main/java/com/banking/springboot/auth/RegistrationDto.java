package com.banking.springboot.auth;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.banking.springboot.validation.EmailUnique;
import com.banking.springboot.validation.FieldsMatch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@FieldsMatch(fieldOne = "password", fieldTwo = "confirmPassword", message = "The password fields must match.")
public class RegistrationDto {
    private Integer id;

    @NotEmpty(message = "Email is required.")
    @Length(max = 256, message = "Email must be less than 256 characters.")
    @EmailUnique
    private String email;

    @NotEmpty(message = "Password is required.")
    @Pattern(regexp = "^[a-zA-Z0-9!@#]+$", message = "Password can only contain lowercase, uppercase, and special characters")
    @Length(min = 8, message = "Password must be at least 8 characters.")
    @Length(max = 25, message = "Password must be shorter than 25 characters.")
    private String password;

    @NotEmpty(message = "Confirm password is required.")
    private String confirmPassword;

    @NotEmpty(message = "First name is required.")
    @Length(max = 45, message = "First name must be less than 45 characters.")
    private String firstName;

    @NotEmpty(message = "Last name is required.")
    @Length(max = 45, message = "Last name must be less than 45 characters.")
    private String lastName;

    @NotEmpty(message = "Address is required.")
    @Length(max = 45, message = "Address must be less than 45 characters.")
    private String address;

    @NotEmpty(message = "City is required.")
    @Length(max = 45, message = "City must be less than 45 characters.")
    private String city;

    @NotEmpty(message = "State is required.")
    @Length(max = 45, message = "State must be less than 45 characters.")
    private String state;

    @NotEmpty(message = "Zip code is required.")
    @Length(max = 5, message = "Zip code must be no more than 5 characters.")
    @Length(min = 5, message = "Zip code must be not be less than 5 characters")
    private String zip;

    @NotEmpty(message = "Phone number is required.")
    @Length(max = 10, message = "Phone number must be no more than 10 characters.")
    @Length(min = 10, message = "Phone number must not be less than 10 characters")
    private String phone;

    private String avatar;
}

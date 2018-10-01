package com.kamai.antiauction.model;
import com.kamai.antiauction.model.transfer.validation.UserValidation;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class User {

    @Null (groups = {UserValidation.New.class}, message = "For new user id must be null")
    @NotNull (groups = {UserValidation.Exist.class}, message = "Id cannot be null")
    private Long id;

    @NotNull (groups = {UserValidation.New.class, UserValidation.UpdateMainUserInformation.class}, message = "Name cannot be null")
    @Max(value=64, message = "Too long value")
    private String name;

    @NotNull (groups = {UserValidation.New.class, UserValidation.UpdateMainUserInformation.class}, message = "Surname cannot be null")
    @Max(value=64, message = "Too long value")
    private String surname;

    @NotNull (groups = {UserValidation.New.class, UserValidation.UpdateMainUserInformation.class}, message = "Login cannot be null")
    @Max(value=64, message = "Too long value")
    private String login;

    @NotNull (groups = {UserValidation.New.class}, message = "Email cannot be null")
    @Email (groups = {UserValidation.New.class}, message = "Invalid email")
    @Max(value=320, message = "Too long value")
    private String email;

    @NotNull (groups = {UserValidation.New.class, UserValidation.UpdatePassword.class}, message = "Password cannot be null")
    @Null (groups = {UserValidation.UpdateMainUserInformation.class}, message = "You can`t update a password")
    @Max(value=128, message = "Too long value")
    private String password;

    @Past
    private LocalDate birth;

    private Boolean sex;

    @Max(value=16, message = "Too long value")
    private String phone;

    @NotNull (groups = {UserValidation.UpdatePhoto.class}, message = "Image link cannot be null")
    @Max(value=2083, message = "Too long value")
    private String image;
    
    private LocalDate regDate;

    @NotNull (groups = {UserValidation.UpdateUserNotifications.class}, message = "Notification settings cannot be null")
    private Boolean notificationSettIsOn;

}

package com.kamai.antiauction.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.kamai.antiauction.model.transfer.validation.UserValidation;
import com.kamai.antiauction.model.transfer.view.View;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class User {

    @JsonView({View.IdView.class})
    @Null (groups = {UserValidation.New.class}, message = "For new user id must be null")
    @NotNull (groups = {UserValidation.Exist.class}, message = "Id cannot be null")
    private Long id;

    @JsonView({View.ShortView.class})
    @NotNull (groups = {UserValidation.New.class, UserValidation.UpdateMainUserInformation.class}, message = "Name cannot be null")
    @Max(value=64, message = "Too long value")
    private String name;

    @JsonView({View.ShortView.class})
    @NotNull (groups = {UserValidation.New.class, UserValidation.UpdateMainUserInformation.class}, message = "Surname cannot be null")
    @Max(value=64, message = "Too long value")
    private String surname;

    @JsonView({View.ShortView.class})
    @NotNull (groups = {UserValidation.New.class, UserValidation.UpdateMainUserInformation.class}, message = "Login cannot be null")
    @Max(value=64, message = "Too long value")
    private String login;

    @JsonView({View.FullView.class})
    @NotNull (groups = {UserValidation.New.class}, message = "Email cannot be null")
    @Email (groups = {UserValidation.New.class}, message = "Invalid email")
    @Max(value=320, message = "Too long value")
    private String email;

    @NotNull (groups = {UserValidation.New.class, UserValidation.UpdatePassword.class}, message = "Password cannot be null")
    @Null (groups = {UserValidation.UpdateMainUserInformation.class}, message = "You can`t update a password")
    @Max(value=128, message = "Too long value")
    private String password;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @JsonView({View.FullView.class})
    @Past
    private LocalDate birth;

    @JsonView({View.FullView.class})
    private Boolean sex;

    @JsonView({View.FullView.class})
    @Max(value=16, message = "Too long value")
    private String phone;

    @JsonView({View.FullView.class})
    @NotNull (groups = {UserValidation.UpdatePhoto.class}, message = "Image link cannot be null")
    @Max(value=2083, message = "Too long value")
    private String image;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @JsonView({View.FullView.class})
    private LocalDate regDate;

    @JsonView({View.FullView.class})
    @NotNull (groups = {UserValidation.UpdateUserNotifications.class}, message = "Notification settings cannot be null")
    @NotNull (groups = {UserValidation.New.class}, message = "Notification settings must be null")
    private Boolean notificationSettIsOn;

}

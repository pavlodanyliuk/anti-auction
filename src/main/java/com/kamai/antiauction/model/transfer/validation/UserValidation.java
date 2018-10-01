package com.kamai.antiauction.model.transfer.validation;

public class UserValidation {

    /**
     * The interface marks a validation field like a field for new user
     */
    public interface New {}

    /**
     * The interface marks a validation field like a field for already existing  user
     */
    public interface Exist {}

    /**
     * The interface marks a validation field like a field for updating main user information
     */
    public interface UpdateMainUserInformation extends Exist{}

    /**
     * The interface marks a validation field like a field for updating users password
     */
    public interface UpdatePassword extends Exist{}

    /**
     * The interface marks a validation field like a field for updating users notification settings
     */
    public interface UpdateUserNotifications extends Exist{}

    /**
     * The interface marks a validation field like a field for updating users photo
     */
    public interface UpdatePhoto extends Exist{}


}

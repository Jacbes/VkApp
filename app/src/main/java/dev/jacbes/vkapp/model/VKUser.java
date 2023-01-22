package dev.jacbes.vkapp.model;

import com.google.gson.annotations.SerializedName;

/*
    Класс друга, состоящий и имени и фамилии.
 */
public class VKUser {
    @SerializedName("first_name")
    String firstName;

    @SerializedName("last_name")
    String lastName;

    public VKUser() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

package dev.jacbes.vkapp.model;

import com.google.gson.annotations.SerializedName;

/*
    Класс друга, состоящий из полей: имени, фамилии, номера телефоно и статуса онлайна.
 */
public class VKUser {
    @SerializedName("first_name")
    String firstName;

    @SerializedName("last_name")
    String lastName;

    String numberPhone;

    @SerializedName("online")
    int onlineStatus;

    @SerializedName("photo_100")
    String photoURL;

    public VKUser() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public int getOnlineStatus() {
        return onlineStatus;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public void setOnlineStatus(int onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }
}

package dev.jacbes.vkapp.model;

import com.google.gson.annotations.SerializedName;

/*
    Класс друга, состоящий из полей: имени, фамилии, номера телефоно и статуса онлайна.
 */
public class VKUser {

    @SerializedName("domain")
    String domain;
    @SerializedName("first_name")
    String firstName;

    @SerializedName("last_name")
    String lastName;

    @SerializedName("bdate")
    String dataOfBirth;

    @SerializedName("city")
    String city;

    @SerializedName("mobile_phone")
    String mobilePhone;

    @SerializedName("online")
    int onlineStatus;

    @SerializedName("photo_100")
    String photoURL;

    @SerializedName("photo_200_orig")
    String photoOriginalURL;

    @SerializedName("status")
    String status;


    public VKUser() {
    }

    public String getDomain() {
        return domain;
    }

    public String getDataOfBirth() {
        return dataOfBirth;
    }

    public String getCity() {
        return city;
    }

    public String getStatus() {
        return status;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public int getOnlineStatus() {
        return onlineStatus;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public String getPhotoOriginalURL() {
        return photoOriginalURL;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setDataOfBirth(String dataOfBirth) {
        this.dataOfBirth = dataOfBirth;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void setOnlineStatus(int onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public void setPhotoOriginalURL(String photoOriginalURL) {
        this.photoOriginalURL = photoOriginalURL;
    }
}

package dev.jacbes.vkapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
    Классы сериализации ответа от VK API.
 */
class VKResponse {
    VKResponseBody response;

    VKResponse() {
    }

    public VKResponseBody getResponse() {
        return response;
    }

    public void setResponse(VKResponseBody response) {
        this.response = response;
    }
}

class VKResponseBody {
    Integer count;
    List<VKUser> items;

    VKResponseBody() {
    }

    public Integer getCount() {
        return count;
    }

    public List<VKUser> getItems() {
        return items;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setItems(List<VKUser> items) {
        this.items = items;
    }
}

class VKUser {
    @SerializedName("first_name")
    String firstName;

    @SerializedName("last_name")
    String lastName;

    VKUser() {
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